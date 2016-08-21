package com.rank.beer.rankingbeers;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rank.beer.rankingbeers.db.DbFields;
import com.rank.beer.rankingbeers.db.DbHelper;
import com.rank.beer.rankingbeers.db.DbQueries;
import com.rank.beer.rankingbeers.utils.BeerUtil;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public class AddBeerActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String LISTVIEW_ACTIVITY = ".ListBeerActivity";
    private byte[] bmpArray;
    private boolean editMode = false;
    private String editingId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beer);
        checkIfEditMode();


        initPhoto(null);
    }

    private void checkIfEditMode() {
        if (null != getCallingActivity()) {
            String callingClassName = getCallingActivity().getShortClassName();
            if (callingClassName != null && callingClassName.equals(LISTVIEW_ACTIVITY)) {
                editMode = true;
                handleEditMode();
            }
        }
    }

    private void handleEditMode() {
        Intent editActivity = getIntent();
        editingId = (String) editActivity.getExtras().get("idToEdit");
        if (editingId != null) {

            Map<String, View> fields = BeerUtil.initFields(this);


            Cursor cr = null;
            DbHelper dbHelp;
            dbHelp = new DbHelper(this);
            try {
                SQLiteDatabase bd = dbHelp.getReadableDatabase();
                cr = bd.rawQuery(DbQueries.GET_BEERS_BY_ID.replace("?", editingId), null);
                while (cr.moveToNext()) {
                    for (DbFields dbf : DbFields.values()) {
                        if (dbf.getType().equals(DbFields.DEFAULT_DISPLAY_TYPE)) {
                            ((TextView) fields.get(dbf.toString())).setText(cr.getString(cr.getColumnIndex(dbf.toString())));
                        } else {
                             Bitmap bm = BeerUtil.initPhoto(cr.getBlob(cr.getColumnIndex(dbf.toString())));
                            ((ImageView) fields.get(dbf.toString())).setImageBitmap(bm);
                        }
                    }
                }

            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            } finally {
                dbHelp.close();
                if (cr != null) {
                    cr.close();
                }
            }


        }
    }


    private void initPhoto(Bitmap bmap) {
        ImageView iv = (ImageView) findViewById(R.id.photo);
        if (bmap == null) {
            iv.setImageResource(R.mipmap.beer_bottle_default);
        } else {
            iv.setImageBitmap(bmap);
        }
    }

    //handle captured photo
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            initPhoto(imageBitmap);

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            if (imageBitmap != null) {
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            }
            bmpArray = bos.toByteArray();
        }
    }

    //should be boolean with errors handling support!
    private void saveAndExit(Map<String, View> editTextData) {
        DbHelper dbh = new DbHelper(this);
        ContentValues values = new ContentValues();
        SQLiteDatabase bd = dbh.getWritableDatabase();
        for (DbFields dbf : DbFields.values()) {
            if (dbf.getType().equals(DbFields.DEFAULT_DISPLAY_TYPE)) {
                values.put(dbf.toString(), ((EditText) editTextData.get(dbf.toString())).getText().toString());
            } else {
                values.put(dbf.toString(), bmpArray);
            }
        }
        if (editMode) {

            bd.update(DbHelper.BEERS_TABLE_NAME, values, "id = " + editingId, null);
            finish();
        } else {
            bd.insertOrThrow(DbHelper.BEERS_TABLE_NAME, null, values);
            finish();
        }
    }


    //run camera to capture photo
    public void takePhoto(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void save(View v) {
        saveAndExit(BeerUtil.initFields(this));
    }

    public void cancel(View v) {
        finish();
    }
}

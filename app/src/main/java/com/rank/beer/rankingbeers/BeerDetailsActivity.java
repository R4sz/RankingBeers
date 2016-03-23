package com.rank.beer.rankingbeers;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rank.beer.rankingbeers.db.DbFields;
import com.rank.beer.rankingbeers.db.DbHelper;

import java.util.HashMap;
import java.util.Map;

public class BeerDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);
        initElements();
    }

    //TODO move initElemements and AddBeeActivity.initFields to static common util class
    private void initElements() {
        Map<String, View> txtViewData = new HashMap<>();
        for (DbFields dbf : DbFields.values()) {
            txtViewData.put(dbf.toString(), (View) findViewById(dbf.getViewId()));
        }
        Intent ListBeer = getIntent();
        String query = "SELECT * from beers where id = ?";
        fillView(query.replace("?", ListBeer.getStringExtra("id")), txtViewData);
    }

    private void fillView(String query, Map<String, View> txtViewData) {
        Cursor cr = null;
        DbHelper dbHelp;
        dbHelp = new DbHelper(this);
        try {
            SQLiteDatabase bd = dbHelp.getReadableDatabase();
            cr = bd.rawQuery(query, null);
            while (cr.moveToNext()) {
                for (DbFields dbf : DbFields.values()) {
                    if (dbf.getType().equals(DbFields.DEFAULT_DISPLAY_TYPE)) {
                        ((TextView) txtViewData.get(dbf.toString())).setText(cr.getString(cr.getColumnIndex(dbf.toString())));
                    } else {
                        Bitmap bm = initPhoto(cr.getBlob(cr.getColumnIndex(dbf.toString())));
                        ((ImageView) txtViewData.get(dbf.toString())).setImageBitmap(bm);
                    }
                }
            }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            dbHelp.close();
        }
    }

    private Bitmap initPhoto(byte[] blobArray) {
        return BitmapFactory.decodeByteArray(blobArray, 0, blobArray.length);


    }

}


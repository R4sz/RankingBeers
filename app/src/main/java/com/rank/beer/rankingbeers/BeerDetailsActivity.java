package com.rank.beer.rankingbeers;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rank.beer.rankingbeers.db.DbFields;
import com.rank.beer.rankingbeers.db.DbHelper;
import com.rank.beer.rankingbeers.db.DbQueries;
import com.rank.beer.rankingbeers.utils.BeerUtil;

import java.util.HashMap;
import java.util.Map;

public class BeerDetailsActivity extends AppCompatActivity {

    private Drawable layoutDrawable;
    private boolean imageScaled;
    private LinearLayout layout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);
        initElements();
    }

    private void initElements() {
        Intent ListBeer = getIntent();
        fillView(DbQueries.GET_BEERS_BY_ID.replace("?", ListBeer.getStringExtra("id")), BeerUtil.initFields(this));
    }

    @SuppressWarnings("TryFinallyCanBeTryWithResources")
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
                        Bitmap bm = BeerUtil.initPhoto(cr.getBlob(cr.getColumnIndex(dbf.toString())));
                        ((ImageView) txtViewData.get(dbf.toString())).setImageBitmap(bm);
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

    public void onImageClick(View v) {
        if (layout == null) {
            setLinearLayout();
        }
        final ImageView img1 = (ImageView) findViewById(R.id.photo);
        layoutDrawable = layout.getBackground();
        layout.setBackground(img1.getDrawable());
        imageScaled = true;
    }

    public void photoExit(View v) {
        if (imageScaled) {
            Toast.makeText(this, "exit from photo", Toast.LENGTH_LONG).show();
            layout.setBackground(layoutDrawable);
            imageScaled = false;
        }
    }

    private void setLinearLayout() {
        layout = (LinearLayout) findViewById(R.id.detailsLayer);
    }

}


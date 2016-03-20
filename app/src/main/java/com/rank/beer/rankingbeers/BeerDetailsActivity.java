package com.rank.beer.rankingbeers;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rank.beer.rankingbeers.R;
import com.rank.beer.rankingbeers.db.DbFields;
import com.rank.beer.rankingbeers.db.DbHelper;
import com.rank.beer.rankingbeers.repo.BeerRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeerDetailsActivity extends AppCompatActivity {

    //TODO move to query class
    private String query = "SELECT * from beers where id = ?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer_details);
        initElements();
    }

    private void initElements() {
        Map<String, TextView> txtViewData = new HashMap<>();
        for (DbFields dbf : DbFields.values()) {
            txtViewData.put(dbf.toString(), (TextView) findViewById(dbf.getViewId()));
        }
        Intent ListBeer = getIntent();
        fillView(query.replace("?", ListBeer.getStringExtra("id")), txtViewData);
    }

    private void fillView(String query, Map<String, TextView> txtViewData) {
        Cursor cr = null;
        DbHelper dbHelp;
        dbHelp = new DbHelper(this);
        try {
            SQLiteDatabase bd = dbHelp.getReadableDatabase();
            cr = bd.rawQuery(query, null);
            while (cr.moveToNext()) {
                for (DbFields dbf : DbFields.values()) {
                    txtViewData.get(dbf.toString()).setText(cr.getString(cr.getColumnIndex(dbf.toString())));
                }
            }

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            dbHelp.close();
        }
    }

}


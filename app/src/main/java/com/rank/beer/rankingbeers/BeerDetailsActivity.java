package com.rank.beer.rankingbeers;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rank.beer.rankingbeers.R;
import com.rank.beer.rankingbeers.db.DbHelper;
import com.rank.beer.rankingbeers.repo.BeerRepo;

import java.util.ArrayList;
import java.util.List;

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
        Intent ListBeer = getIntent();
        fillView(getData(query.replace("?", ListBeer.getStringExtra("id"))));
    }

    private void fillView(BeerRepo data) {
        //TO DO
    }

    private BeerRepo getData(String query) {
        BeerRepo beerDetails = null;

        DbHelper dbHelp;
        dbHelp = new DbHelper(this);
        try {
            SQLiteDatabase bd = dbHelp.getReadableDatabase();
            Cursor cr = bd.rawQuery(query, null);
            while (cr.moveToNext()) {
                beerDetails = new BeerRepo(cr);
            }

        } finally {
            dbHelp.close();
        }

        return beerDetails;
    }


}


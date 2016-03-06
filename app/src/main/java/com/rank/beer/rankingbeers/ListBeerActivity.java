package com.rank.beer.rankingbeers;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.rank.beer.rankingbeers.MainActivity;
import com.rank.beer.rankingbeers.R;
import com.rank.beer.rankingbeers.adapter.ListBeerAdapter;
import com.rank.beer.rankingbeers.db.DbHelper;
import com.rank.beer.rankingbeers.repo.BeerRepo;

import java.util.ArrayList;
import java.util.List;

public class ListBeerActivity extends AppCompatActivity {

    private static final String BEER_LIST_QUERY = "SELECT beerName, price, composition, alcContent FROM beers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_beer);
        getListViewData(BEER_LIST_QUERY);
    }


    private void initListView(List<BeerRepo> beersList) {
        ListView list = (ListView) findViewById(R.id.ListView01);
        list.setClickable(true);

        ListBeerAdapter adapter = new ListBeerAdapter(this, beersList);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
               // Intent td = new Intent(getApplicationContext(), MainActivity.class);
                //td.putExtra("position", position);
               // startActivity(td);
            }
        });
        list.setAdapter(adapter);
        registerForContextMenu(list);
    }

    private List<BeerRepo> getListViewData(String query) {
        List<BeerRepo> listOfBeers = new ArrayList<BeerRepo>();

        DbHelper dbHelp;
        dbHelp = new DbHelper(this);
        try {
            SQLiteDatabase bd = dbHelp.getReadableDatabase();
            Cursor cr = bd.rawQuery(query, null);
            while (cr.moveToNext()) {
                listOfBeers.add(new BeerRepo(cr));
            }

        } finally {
            dbHelp.close();
        }

    }
}

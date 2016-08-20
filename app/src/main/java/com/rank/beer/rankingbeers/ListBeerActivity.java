package com.rank.beer.rankingbeers;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.rank.beer.rankingbeers.adapter.ListBeerAdapter;
import com.rank.beer.rankingbeers.db.DbHelper;
import com.rank.beer.rankingbeers.db.DbQueries;
import com.rank.beer.rankingbeers.repo.BeerRepo;
import com.rank.beer.rankingbeers.utils.BeerUtil;

import java.util.ArrayList;
import java.util.List;

public class ListBeerActivity extends AppCompatActivity {


    private static final int EDIT = 0;
    private static final int DELETE = 1;
    private ListBeerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_beer);
        initListView(getListViewData(DbQueries.GET_BEERS_FOR_LIST));
    }


    private void initListView(final List<BeerRepo> beersList) {
        ListView list = (ListView) findViewById(R.id.ListView01);
        list.setClickable(true);

        adapter = new ListBeerAdapter(this, beersList);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View view, int position, long index) {
                Intent td = new Intent(getApplicationContext(), BeerDetailsActivity.class);
                td.putExtra("id", beersList.get(position).getId());
                startActivity(td);
            }
        });
        list.setAdapter(adapter);
        registerForContextMenu(list);
    }

    private List<BeerRepo> getListViewData(String query) {
        List<BeerRepo> listOfBeers = new ArrayList<>();

        DbHelper dbHelp;
        dbHelp = new DbHelper(this);
        Cursor cr = null;
        try {
            SQLiteDatabase bd = dbHelp.getReadableDatabase();
            cr = bd.rawQuery(query, null);
            while (cr.moveToNext()) {
                listOfBeers.add(new BeerRepo(cr));
            }
        } finally {
            dbHelp.close();
            if (cr != null) {
                cr.close();
            }
        }

        return listOfBeers;
    }


    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if (v.getId() == R.id.ListView01) {
            menu.setHeaderTitle("Opcje...");
            String[] menuItems = getResources().getStringArray(R.array.menu);
            for (int i = 0; i < menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }
        }
    }

    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int menuItemIndex = item.getItemId();

        switch (menuItemIndex) {
            case EDIT:
                break;

            case DELETE:
                BeerUtil.deleteEntry(this, getEntryId(info.position));
                adapter.getData().remove(info.position);
                adapter.notifyDataSetChanged();
                break;

        }

        return true;
    }

    private String getEntryId(int position) {
        BeerRepo item = (BeerRepo) adapter.getItem(position);
        return item.getId();
    }


}

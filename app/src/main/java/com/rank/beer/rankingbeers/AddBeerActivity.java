package com.rank.beer.rankingbeers;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.rank.beer.rankingbeers.db.DbFields;
import com.rank.beer.rankingbeers.db.DbHelper;

import java.util.HashMap;
import java.util.Map;

public class AddBeerActivity extends AppCompatActivity {

    private final Map<String, EditText> editTxtData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beer);

    }

    public void save(View v) {
        initFields();
        saveAndExit();
    }

    //TODO will change (or maybe not) during refactor DbHelper (add enum)
    private void initFields() {
        editTxtData.put(DbFields.BEER_NAME.toString(), (EditText) findViewById(R.id.beerNameInp));
        editTxtData.put(DbFields.BEER_TYPE.toString(), (EditText) findViewById(R.id.beerTypeInp));
        editTxtData.put(DbFields.ALC_CONTENT.toString(), (EditText) findViewById(R.id.alcContentInp));
        editTxtData.put(DbFields.EXT_CONTENT.toString(), (EditText) findViewById(R.id.extContentInp));
        editTxtData.put(DbFields.COMPOSITION.toString(), (EditText) findViewById(R.id.compositionInp));
        editTxtData.put(DbFields.PRICE.toString(), (EditText) findViewById(R.id.priceInp));
        editTxtData.put(DbFields.UNIT_CAPACITY.toString(), (EditText) findViewById(R.id.unitCapacityInp));
        editTxtData.put(DbFields.BARCODE.toString(), (EditText) findViewById(R.id.barcodeInp));
        editTxtData.put(DbFields.BUY_PLACE.toString(), (EditText) findViewById(R.id.buyPlaceInp));
        editTxtData.put(DbFields.COMMENT.toString(), (EditText) findViewById(R.id.commentInp));

    }

    //should be boolean with errors handling support!
    private void saveAndExit() {
        DbHelper dbh = new DbHelper(this);
        ContentValues values = new ContentValues();
        SQLiteDatabase bd = dbh.getWritableDatabase();
        for (DbFields dbf : DbFields.values()) {
            values.put(dbf.toString(), editTxtData.get(dbf.toString()).getText().toString());
        }
        bd.insertOrThrow(DbHelper.BEERS_TABLE_NAME, null, values);
        finish();
    }

    public void cancel(View v) {
        finish();
    }
}

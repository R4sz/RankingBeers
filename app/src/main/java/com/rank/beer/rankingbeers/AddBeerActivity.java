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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beer);
    }

    public void save(View v) {
        saveAndExit(initFields());
    }

    private Map<String, EditText> initFields() {
        Map<String, EditText> editTxtData = new HashMap<>();
        for (DbFields dbf : DbFields.values()) {
            editTxtData.put(dbf.toString(), (EditText) findViewById(dbf.getEdTxtId()));
        }
        return editTxtData;

    }

    //should be boolean with errors handling support!
    private void saveAndExit(Map<String, EditText> editTextData) {
        DbHelper dbh = new DbHelper(this);
        ContentValues values = new ContentValues();
        SQLiteDatabase bd = dbh.getWritableDatabase();
        for (DbFields dbf : DbFields.values()) {
            values.put(dbf.toString(), editTextData.get(dbf.toString()).getText().toString());
        }
        bd.insertOrThrow(DbHelper.BEERS_TABLE_NAME, null, values);
        finish();
    }

    public void cancel(View v) {
        finish();
    }
}

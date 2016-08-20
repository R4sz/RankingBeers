package com.rank.beer.rankingbeers.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

/**
 * Created by rasz on 2016-03-05.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "rankingBeers_databaseTmp2.db";
    static final String COMMA = ", ";
    public static final String BEERS_TABLE_NAME = "beers";
    static final String CREATE_TABLE = "CREATE TABLE " + BEERS_TABLE_NAME + " (";
    static final String ID_COL_INIT = "id INTEGER PRIMARY KEY,";
    static final String CLOSE_QUERY = ");";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(getSqlCreateDbQuery());
    }

    @NonNull
    private String getSqlCreateDbQuery() {
        StringBuilder query = new StringBuilder();
        query.append(CREATE_TABLE);
        query.append(ID_COL_INIT);
        for (DbFields dbf : DbFields.values()) {
            query.append(dbf.toString()).append(" ").append(dbf.getDataType()).append(COMMA);
        }
        int ind = query.lastIndexOf(COMMA);
        return (query.replace(ind, ind + 1, CLOSE_QUERY)).toString();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BEERS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

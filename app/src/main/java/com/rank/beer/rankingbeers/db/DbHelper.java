package com.rank.beer.rankingbeers.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rasz on 2016-03-05.
 */

public class DbHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "rankingBeers_database.db";
    //TODO
    //some nice refactor - move this shit to enum..
    public static final String BEERS_TABLE_NAME = "beers";
    static final String COMMA = ", ";
    static final String OPTIONS_TEXT = "TEXT";
    static final String OPTIONS_INTEGER = "INTEGER";
    static final String OPTIONS_DOUBLE = "DOUBLE";
    static final String OPTIONS_LONG = "LONG";

    static final String ID_COL = "id";
    public static final String BEER_NAME = "beerName";
    public static final String BEER_TYPE = "beerType";
    public static final String ALC_CONTENT = "alcContent";
    public static final String EXT_CONTENT = "extContent";
    public static final String COMPOSITION = "composition";
    public static final String PRICE = "price";
    public static final String UNIT_CAPACITY = "unitCapacity";
    public static final String BARCODE = "barcode";
    public static final String BUY_PLACE = "buyPlace";
    public static final String COMMENT = "comment";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + BEERS_TABLE_NAME + " ("
                        + ID_COL + " INTEGER PRIMARY KEY, "
                        + BEER_NAME + " " + OPTIONS_TEXT + COMMA
                        + BEER_TYPE + " " + OPTIONS_TEXT + COMMA
                        + ALC_CONTENT + " " + OPTIONS_DOUBLE + COMMA
                        + EXT_CONTENT + " " + OPTIONS_DOUBLE + COMMA
                        + COMPOSITION + " " + OPTIONS_TEXT + COMMA
                        + PRICE + " " + OPTIONS_DOUBLE + COMMA
                        + UNIT_CAPACITY + " " + OPTIONS_DOUBLE + COMMA
                        + BARCODE + " " + OPTIONS_LONG + COMMA
                        + BUY_PLACE + " " + OPTIONS_TEXT + COMMA
                        + COMMENT + " " + OPTIONS_TEXT + " );"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + BEERS_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

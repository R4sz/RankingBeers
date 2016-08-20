package com.rank.beer.rankingbeers.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.rank.beer.rankingbeers.db.DbHelper;
import com.rank.beer.rankingbeers.db.DbQueries;

/**
 * Created by rasz on 2016-07-31.
 */
public class BeerUtil {
    public static DbHelper dbHelper;



    public static Bitmap initPhoto(byte[] blobArray) {
        return BitmapFactory.decodeByteArray(blobArray, 0, blobArray.length);
    }

    public static void deleteEntry(Context ctx, String id) {
        dbHelper = new DbHelper(ctx);
        SQLiteDatabase bd = dbHelper.getReadableDatabase();
        bd.execSQL(DbQueries.DELETE_ITEM.replace("?", id));
        bd.close();
    }
}

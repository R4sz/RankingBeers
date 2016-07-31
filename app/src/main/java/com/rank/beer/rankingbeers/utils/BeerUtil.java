package com.rank.beer.rankingbeers.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by rasz on 2016-07-31.
 */
public class BeerUtil {


    public static Bitmap initPhoto(byte[] blobArray) {
        return BitmapFactory.decodeByteArray(blobArray, 0, blobArray.length);


    }
}

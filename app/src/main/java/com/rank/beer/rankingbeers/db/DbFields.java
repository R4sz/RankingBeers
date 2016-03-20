package com.rank.beer.rankingbeers.db;

import android.widget.EditText;

import com.rank.beer.rankingbeers.R;

/**
 * Created by rasz on 2016-03-05.
 */
public enum DbFields {
    BEER_NAME("beerName", DbFields.OPTIONS_TEXT, R.id.beerNameInp),
    BEER_TYPE("beerType", DbFields.OPTIONS_TEXT, R.id.beerTypeInp),
    ALC_CONTENT("alcContent", DbFields.OPTIONS_DOUBLE, R.id.alcContentInp),
    EXT_CONTENT("extContent", DbFields.OPTIONS_DOUBLE, R.id.extContentInp),
    COMPOSITION("composition", DbFields.OPTIONS_TEXT, R.id.compositionInp),
    PRICE("price", DbFields.OPTIONS_DOUBLE, R.id.priceInp),
    UNIT_CAPACITY("unitCapacity", DbFields.OPTIONS_DOUBLE, R.id.unitCapacityInp),
    BARCODE("barcode", DbFields.OPTIONS_LONG, R.id.barcodeInp),
    BUY_PLACE("buyPlace", DbFields.OPTIONS_TEXT, R.id.buyPlaceInp),
    COMMENT("comment", DbFields.OPTIONS_TEXT, R.id.commentInp),
    PHOTO("photo", DbFields.OPTIONS_BLOB, R.id.photo, DbFields.IMAGEVIEW_DISPLAY_TYPE);

    private final String txt;
    private final String dataType;
    private int viewId;

    //editText is default display type
    private String displayType = DEFAULT_DISPLAY_TYPE;

    static final String OPTIONS_TEXT = "TEXT";
    static final String OPTIONS_INTEGER = "INTEGER";
    static final String OPTIONS_DOUBLE = "DOUBLE";
    static final String OPTIONS_LONG = "LONG";
    static final String OPTIONS_BLOB = "BLOB";

    static final String IMAGEVIEW_DISPLAY_TYPE = "imageView";
    public static final String DEFAULT_DISPLAY_TYPE = "editText";

    DbFields(final String txt, final String dataType, final int viewId) {
        this.txt = txt;
        this.dataType = dataType;
        this.viewId= viewId;
    }


    DbFields(final String txt, final String dataType, final int viewId, String displayType) {
        this(txt, dataType, viewId);
        this.displayType = displayType;
    }


    public String toString() {
        return txt;
    }

    public String getDataType() {
        return dataType;
    }

    public int getViewId() {
        return viewId;
    }

    public String getType() {
        return displayType;
    }
}

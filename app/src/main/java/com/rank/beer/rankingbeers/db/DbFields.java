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
    COMMENT("comment", DbFields.OPTIONS_TEXT, R.id.commentInp);

    private final String txt;
    private final String dataType;
    private int edTxtId;

    static final String OPTIONS_TEXT = "TEXT";
    static final String OPTIONS_INTEGER = "INTEGER";
    static final String OPTIONS_DOUBLE = "DOUBLE";
    static final String OPTIONS_LONG = "LONG";

    DbFields(final String txt, final String dataType, final int edTxtId) {
        this.txt = txt;
        this.dataType = dataType;
        this.edTxtId = edTxtId;
    }


    public String toString() {
        return txt;
    }

    public String getDataType() {
        return dataType;
    }

    public int getEdTxtId() {
        return edTxtId;
    }
}

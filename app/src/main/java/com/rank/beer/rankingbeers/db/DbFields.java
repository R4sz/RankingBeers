package com.rank.beer.rankingbeers.db;

/**
 * Created by rasz on 2016-03-05.
 */
public enum DbFields {
    BEER_NAME("beerName", DbFields.OPTIONS_TEXT),
    BEER_TYPE("beerType", DbFields.OPTIONS_TEXT),
    ALC_CONTENT("alcContent", DbFields.OPTIONS_DOUBLE),
    EXT_CONTENT("extContent", DbFields.OPTIONS_DOUBLE),
    COMPOSITION("composition", DbFields.OPTIONS_TEXT),
    PRICE("price", DbFields.OPTIONS_DOUBLE),
    UNIT_CAPACITY("unitCapacity", DbFields.OPTIONS_DOUBLE),
    BARCODE("barcode", DbFields.OPTIONS_LONG),
    BUY_PLACE("buyPlace", DbFields.OPTIONS_TEXT),
    COMMENT("comment", DbFields.OPTIONS_TEXT);

    private final String txt;
    private final String dataType;

    static final String OPTIONS_TEXT = "TEXT";
    static final String OPTIONS_INTEGER = "INTEGER";
    static final String OPTIONS_DOUBLE = "DOUBLE";
    static final String OPTIONS_LONG = "LONG";

    DbFields(final String txt, final String dataType) {
        this.txt = txt;
        this.dataType = dataType;
    }


    public String toString() {
        return txt;
    }

    public String getDataType() {
        return dataType;
    }
}

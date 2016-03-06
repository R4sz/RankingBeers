package com.rank.beer.rankingbeers.db;

/**
 * Created by rasz on 2016-03-05.
 */
public enum DbFields {
    BEER_NAME("beerName"),
    BEER_TYPE("beerType"),
    ALC_CONTENT("alcContent"),
    EXT_CONTENT("extContent"),
    COMPOSITION("composition"),
    PRICE("price"),
    UNIT_CAPACITY("unitCapacity"),
    BARCODE("barcode"),
    BUY_PLACE("buyPlace"),
    COMMENT("comment");

    private final String txt;

    DbFields(final String txt) {
        this.txt = txt;
    }

    public String toString() {
        return txt;
    }
}

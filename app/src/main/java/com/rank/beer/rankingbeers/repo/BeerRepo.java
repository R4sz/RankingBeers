package com.rank.beer.rankingbeers.repo;

import android.database.Cursor;

/**
 * Created by rasz on 2016-03-06.
 */
public class BeerRepo {
    private String price;
    private String composition;
    private String alcContent;

    public BeerRepo(Cursor cr) {
        this.name = cr.getString(0);
        this.price = cr.getString(1);
        this.composition = cr.getString(2);
        this.alcContent = cr.getString(3);
    }

    private String name;

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getComposition() {
        return composition;
    }

    public String getAlcContent() {
        return alcContent;
    }
}

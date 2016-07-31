package com.rank.beer.rankingbeers.db;

/**
 * Created by rasz on 2016-07-19.
 */
public class DbQueries {
    public static final String GET_BEERS_BY_ID = "SELECT * from beers where id = ?";

    //TODO decrease numbers of arguments + repo for list !
    public static final String GET_BEERS_FOR_LIST = "SELECT id, beerName, price, composition, alcContent, photo FROM beers";
}

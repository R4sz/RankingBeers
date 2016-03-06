package com.rank.beer.rankingbeers.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.rank.beer.rankingbeers.ListBeerActivity;
import com.rank.beer.rankingbeers.repo.BeerRepo;

import java.util.List;

/**
 * Created by rasz on 2016-03-06.
 */
public class ListBeerAdapter extends BaseAdapter {
    private Context context;
    private BeerRepo entry;
    private List<BeerRepo> listOfBeers;



    public ListBeerAdapter() {
    }

    public ListBeerAdapter(Context context, List<BeerRepo> listOfBeers) {
        this.context = context;
        this.listOfBeers = listOfBeers;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return listOfBeers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}

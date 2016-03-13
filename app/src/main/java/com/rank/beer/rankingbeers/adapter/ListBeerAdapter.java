package com.rank.beer.rankingbeers.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rank.beer.rankingbeers.R;
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
        return listOfBeers.size();
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
        entry = listOfBeers.get(position);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.beer_listing, null);
        }

        TextView name = (TextView) convertView.findViewById(R.id.beerNameOnList);
        name.setText(entry.getName());

        TextView composition = (TextView) convertView.findViewById(R.id.compositionOnList);
        composition.setText(entry.getComposition());

        TextView alcContent = (TextView) convertView.findViewById(R.id.alcOnList);
        alcContent.setText(entry.getAlcContent());

        TextView price = (TextView) convertView.findViewById(R.id.priceOnList);
        price.setText(entry.getPrice());

        return convertView;
    }
}

package com.rank.beer.rankingbeers;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected static SparseArray<Class<? extends Activity>> buttonConfig = new SparseArray<Class<? extends Activity>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonConfig.append(R.id.addBeer, AddBeerActivity.class);
        initButtons();

    }

    public void onClick(View v) {
        if (v.getId() == R.id.exit) {
            moveTaskToBack(true);
            return;
        }

        Intent i = new Intent(this, buttonConfig.get(v.getId()));
        startActivity(i);
    }

    void initButtons() {
        int key = 0;
        View view = null;
        for (int i = 0; i < buttonConfig.size(); i++) {
            key = buttonConfig.keyAt(i);
            view = findViewById(key);
            view.setOnClickListener(this);
        }

        view = findViewById(R.id.exit);
        view.setOnClickListener((View.OnClickListener) this);
    }

}


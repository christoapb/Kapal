package com.github.brnunes.swipeablecardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class HullConstruction extends AppCompatActivity {
    private ArrayList<String> mItems;
    private ListView mainListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hull_construction);

    }
}

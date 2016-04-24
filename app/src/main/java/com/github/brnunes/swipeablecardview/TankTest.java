package com.github.brnunes.swipeablecardview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class TankTest extends AppCompatActivity {

    private Procurement.CardViewAdapter mAdapter;
    private ArrayList mItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tank_test);
        mItems = new ArrayList<>(30);
        for (int a = 1; a<30; a++)
        {
            mItems.add(String.format("Description %d", a));
        }




    }
}

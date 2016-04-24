package com.github.brnunes.swipeablecardview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by christoap on 19/04/2016.
 */
public class ChooseShips extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);

        String[] kapal = {"Kapal Kecil","Kapal Sedang","Kapal Besar"};
        ListAdapter shipAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, kapal);
        ListView shipListView = (ListView) findViewById(R.id.list_view);
        shipListView.setAdapter(shipAdapter);

        shipListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        startActivity(new Intent(ChooseShips.this, MainActivity.class));
                    }
                }
        );

    }
}

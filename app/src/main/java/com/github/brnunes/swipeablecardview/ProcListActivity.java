package com.github.brnunes.swipeablecardview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProcListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proc_list);
        Button button1 = (Button)findViewById(R.id.buttona);
        Button button2 = (Button)findViewById(R.id.buttonb);
        Button button3 = (Button)findViewById(R.id.buttonc);
        Button button5 = (Button)findViewById(R.id.buttonback);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //onItemTouchListener.onContractClick(v, getLayoutPosition());
                startActivity(new Intent(ProcListActivity.this, ProcPipaForm.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //onItemTouchListener.onContractClick(v, getLayoutPosition());
                startActivity(new Intent(ProcListActivity.this, ProcPelatForm.class));
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //onItemTouchListener.onContractClick(v, getLayoutPosition());
                startActivity(new Intent(ProcListActivity.this, ProcProfilForm.class));
            }
        });

        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //onItemTouchListener.onContractClick(v, getLayoutPosition());
                startActivity(new Intent(ProcListActivity.this, ProcActivity.class));
            }
        });
    }
}

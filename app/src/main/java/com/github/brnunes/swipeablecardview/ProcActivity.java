package com.github.brnunes.swipeablecardview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProcActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proc);
        Button button1 = (Button)findViewById(R.id.buttonhc);
        Button button2 = (Button)findViewById(R.id.buttonho);
        Button button3 = (Button)findViewById(R.id.buttonmo);
        Button button4 = (Button)findViewById(R.id.buttoneo);
        Button button5 = (Button)findViewById(R.id.buttonback);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //onItemTouchListener.onContractClick(v, getLayoutPosition());
                startActivity(new Intent(ProcActivity.this, ProcListActivity.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //onItemTouchListener.onContractClick(v, getLayoutPosition());
                startActivity(new Intent(ProcActivity.this, ProcListActivity.class));
            }
        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //onItemTouchListener.onContractClick(v, getLayoutPosition());
                startActivity(new Intent(ProcActivity.this, ProcListActivity.class));
            }
        });
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //onItemTouchListener.onContractClick(v, getLayoutPosition());
                startActivity(new Intent(ProcActivity.this, ProcListActivity.class));
            }
        });
        button5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //onItemTouchListener.onContractClick(v, getLayoutPosition());
                startActivity(new Intent(ProcActivity.this, MainActivity.class));
            }
        });
    }
}

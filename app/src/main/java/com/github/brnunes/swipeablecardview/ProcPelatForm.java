package com.github.brnunes.swipeablecardview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProcPelatForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proc_pelat_form);
        Button button1 = (Button)findViewById(R.id.submit);
        Button button2 = (Button)findViewById(R.id.main);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //onItemTouchListener.onContractClick(v, getLayoutPosition());
                startActivity(new Intent(ProcPelatForm.this, MainActivity.class));
            }
        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //onItemTouchListener.onContractClick(v, getLayoutPosition());
                startActivity(new Intent(ProcPelatForm.this, ProcActivity.class));
            }
        });
    }
}

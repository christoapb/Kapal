package com.github.brnunes.swipeablecardview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Procurement extends AppCompatActivity {
    private CardViewAdapter mAdapter;
    private ArrayList<String> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        mItems = new ArrayList<>(30);
        for (int i = 1; i <= 30; i++) {
            mItems.add(String.format("Mat/EQ %02d", i));
        }

        OnItemTouchListener itemTouchListener = new OnItemTouchListener() {
            @Override
            public void onCardViewTap(View view, int position) {
                //Toast.makeText(Procurement.this, "Tapped " + mItems.get(position), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onContractClick(View view, int position) {
                //Toast.makeText(Procurement.this, "Checking Contract in" + mItems.get(position) + "..", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onShippingClick(View view, int position) {
                //Toast.makeText(Procurement.this, "Checking Shipping in " + mItems.get(position) + "..", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onArrivalClick(View view, int position) {
                //Toast.makeText(Procurement.this, "Checking Arrival At Yard in " + mItems.get(position) + "..", Toast.LENGTH_SHORT).show();
            }
            public void onMatIdentClick(View view, int position) {
                //Toast.makeText(Procurement.this, "Checking Material Identification in " + mItems.get(position) + "..", Toast.LENGTH_SHORT).show();
            }
        };

        mAdapter = new CardViewAdapter(mItems, itemTouchListener);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }

    /**
     * Interface for the touch events in each item
     */
    public interface OnItemTouchListener {
        void onCardViewTap (View view, int position);

        void onContractClick(View view, int position);

        void onShippingClick(View view, int position);

        void onArrivalClick(View view, int position);

        void onMatIdentClick(View view, int position);
    }

    /**
     * A simple adapter that loads a CardView layout with one TextView and two Buttons, and
     * listens to clicks on the Buttons or on the CardView
     */
    public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.ViewHolder> {
        private List<String> cards;
        private OnItemTouchListener onItemTouchListener;

        public CardViewAdapter(List<String> cards, OnItemTouchListener onItemTouchListener) {
            this.cards = cards;
            this.onItemTouchListener = onItemTouchListener;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view_procurement, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.title.setText(cards.get(i));
        }

        @Override
        public int getItemCount() {
            return cards == null ? 0 : cards.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView title;
            private Button button1;
            private Button button2;
            private Button button3;
            private Button button4;

            public ViewHolder(View itemView) {
                super(itemView);
                title = (TextView) itemView.findViewById(R.id.card_view_title);
                button1 = (Button) itemView.findViewById(R.id.button_proc_contract);
                button2 = (Button) itemView.findViewById(R.id.button_proc_shipping);
                button3 = (Button) itemView.findViewById(R.id.button_proc_arrival);
                button4 = (Button) itemView.findViewById(R.id.button_proc_matident);

                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //onItemTouchListener.onContractClick(v, getLayoutPosition());
                        startActivity(new Intent(Procurement.this, ProcContractForm.class));
                    }
                });

                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //onItemTouchListener.onShippingClick(v, getLayoutPosition());
                        startActivity(new Intent(Procurement.this, ProcShippingForm.class));
                    }
                });

                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //onItemTouchListener.onArrivalClick(v, getLayoutPosition());
                        startActivity(new Intent(Procurement.this, ProcArrivalForm.class));
                    }
                });

                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // onItemTouchListener.onMatIdentClick(v, getLayoutPosition());
                        startActivity(new Intent(Procurement.this, ProcMatIdentForm.class));
                    }
                });


                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemTouchListener.onCardViewTap(v, getLayoutPosition());
                    }
                });
            }
        }
    }
}

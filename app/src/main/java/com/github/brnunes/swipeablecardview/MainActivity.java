package com.github.brnunes.swipeablecardview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    View utama;
    FragmentManager fragmentManager;

    private TextView vesselname;
    private TextView imonumber;
    private TextView grosstonnage;
    private TextView registeredowner;
    private TextView society;
    private TextView flag;
    private TextView shippingroute;
    private TextView length;
    private TextView breadth;
    private TextView high;
    private TextView owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        //Toast.makeText(getApplicationContext(), "situ", Toast.LENGTH_LONG).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        vesselname = (TextView) findViewById(R.id.vesselname);
        imonumber = (TextView) findViewById(R.id.imonumber);
        grosstonnage = (TextView) findViewById(R.id.grosstonnage);
        registeredowner = (TextView) findViewById(R.id.registeredowner);
        society = (TextView) findViewById(R.id.society);
        flag = (TextView) findViewById(R.id.flag);
        shippingroute = (TextView) findViewById(R.id.shiipingroute);
        length = (TextView) findViewById(R.id.length);
        breadth = (TextView) findViewById(R.id.breadth);
        high = (TextView) findViewById(R.id.high);
        owner = (TextView) findViewById(R.id.owner);
        setSupportActionBar(toolbar);
        utama = findViewById(R.id.content_utama);

        show("01");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (getIntent().getSerializableExtra("state") != null) {
            String name = "nav_" + getIntent().getSerializableExtra("state").toString();
            int resId = getResources().getIdentifier(name, "id", this.getPackageName());
            utama.setVisibility(View.GONE);
            fragmentManager.beginTransaction().
                    replace(R.id.flMain, getFragment(resId)).
                    commit();
        }

    }

    private void show(final String id){
        String URL = Config.DESC_URL + id;
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                       // Toast.makeText(MainActivity.this, "siti", Toast.LENGTH_LONG).show();
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONObject reslut = jsonResponse.getJSONObject("result");
                            //Toast.makeText(MainActivity.this, "siti", Toast.LENGTH_LONG).show();
                            String code = reslut.getString("code");
                            //String data = reslut.getString("data");
                            //Toast.makeText(MainActivity.this, "sini", Toast.LENGTH_LONG).show();
                            if (code.matches("200")){
                                //Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
                                //session.isLoggedIn();
                                //session.createLoginSession(email, password);
//                                Intent intent = new Intent(Login.this, ChooseShips.class);
//                                startActivity(intent);
                                showJSON(response);



                            }
                            else {
                                Toast.makeText(MainActivity.this, "Show gagal", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "catch gagal", Toast.LENGTH_LONG).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                })/*{
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request
                params.put(Config.KEY_ID, id);

                //returning parameter
                return params;
            }
        }*/;

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showJSON(String response){
        String name="";
        String imo="";
        String gross = "";
        String regis="";
        String clas="";
        String fl="";
        String shipping="";
        String leng="";
        String bread="";
        String hig="";
        String nameofsurveyor="";
//        Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject result = jsonObject.getJSONObject(Config.JSON_ARRAY);
            JSONArray collegeData = result.getJSONArray("data");
            JSONObject hore = collegeData.getJSONObject(0);
            name = hore.getString(Config.KEY_NAME);
            imo = hore.getString(Config.KEY_IMO);
            gross = hore.getString(Config.KEY_GROSS);
            regis = hore.getString(Config.KEY_REG);
            clas = hore.getString(Config.KEY_CLASS);
            fl = hore.getString(Config.KEY_FLAG);
            shipping = hore.getString(Config.KEY_SHIP);
            leng = hore.getString(Config.KEY_L);
            bread = hore.getString(Config.KEY_B);
            hig = hore.getString(Config.KEY_H);
            nameofsurveyor = hore.getString(Config.KEY_NAMES);
            //Toast.makeText(MainActivity.this, name, Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            Toast.makeText(MainActivity.this, "gagal", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        vesselname.setText(name);
        imonumber.setText(imo);
        grosstonnage.setText(gross);
        registeredowner.setText(regis);
        society.setText(clas);
        flag.setText(fl);
        shippingroute.setText(shipping);
        length.setText(leng);
        breadth.setText(bread);
        high.setText(hig);
        owner.setText(nameofsurveyor);
        //Toast.makeText(MainActivity.this, "end", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

   /* private void selectItem (int position)
    {
        switch (position)
        {
            case 1:
                Intent document = new Intent(MainActivity.this, Document.class);
                startActivity(document);
                break;
            case 2:
                Intent procurement = new Intent(MainActivity.this, Procurement.class);
                startActivity(procurement);
                break;
            default:
        }
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();
        fragment = getFragment(id);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if (fragment != null) {
            utama.setVisibility(View.GONE);
            fragmentManager.beginTransaction().
                    replace(R.id.flMain, fragment).
                    commit();
        }
        return true;
    }

    private Fragment getFragment(int id) {

        if (id == R.id.nav_doc) {
            return Document.newInstance(null, null);

        } else if (id == R.id.nav_procurement) {
            startActivity(new Intent(MainActivity.this, ProcActivity.class));

        } else if (id == R.id.nav_hull) {
            return Hull.newInstance(null, null);

        } else if (id == R.id.nav_machinery) {
            return MachineryElectrical.newInstance(null, null);

        } else if (id == R.id.nav_outfitting) {
            return PipingOutfitting.newInstance(null, null);

        } else if (id == R.id.nav_launching) {
            return Launching.newInstance(null, null);
        } else if (id == R.id.nav_inclining) {
            startActivity(new Intent(MainActivity.this, Inclining.class));
        } else if (id == R.id.nav_commisioning) {
            return ComSeaTrials.newInstance(null,null);
        } else if (id == R.id.nav_meeting) {
            startActivity(new Intent(MainActivity.this,Meeting.class));

        }

        return null;
    }
}


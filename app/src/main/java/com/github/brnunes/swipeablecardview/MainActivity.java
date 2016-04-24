package com.github.brnunes.swipeablecardview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    View utama;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        utama = findViewById(R.id.content_utama);

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
            startActivity(new Intent(MainActivity.this, Procurement.class));

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


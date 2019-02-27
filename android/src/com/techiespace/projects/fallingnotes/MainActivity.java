package com.techiespace.projects.fallingnotes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.techiespace.projects.fallingnotes.Database.AppDatabase;
import com.techiespace.projects.fallingnotes.Database.AppExecutors;
import com.techiespace.projects.fallingnotes.Database.Level;
import com.techiespace.projects.fallingnotes.Database.databaseHandler;
import com.techiespace.projects.fallingnotes.fragments.MidiPlayerFragment;
import com.techiespace.projects.fallingnotes.fragments.ScaleFragment;

import java.util.List;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AppDatabase mDb;
    private databaseHandler dbhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Quick Practice", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();

        });


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Setting Up the database
        mDb = AppDatabase.getInstance(getApplicationContext());
/*
        dbhandler = new databaseHandler(this);

        dbhandler.loadDatabase();*/



        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.screen_area, new DashboardFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }

        displayLevelTable();


    }

    private void displayLevelTable() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                List<Level> level = mDb.levelDao().loadAllLevels();

                for(Level levelInstance : level)
                System.out.println(levelInstance.getLevel_name());

            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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

        Fragment fragment = null;
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_dashboard) {
            fragment = new DashboardFragment();

        } else if (id == R.id.nav_scales) {
            fragment = new ScaleFragment();
        } else if (id == R.id.nav_modes) {

        }else if(id == R.id.nav_midi){
            fragment = new MidiPlayerFragment();
        }
        else if (id == R.id.nav_basic_chord) {
//            fragment = new MidiPlayerFragment();
        } else if (id == R.id.nav_inv_chord) {
//            fragment = new LocalDeviceMidiFragment();
        } else if (id == R.id.nav_adv_chord) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.screen_area, fragment).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

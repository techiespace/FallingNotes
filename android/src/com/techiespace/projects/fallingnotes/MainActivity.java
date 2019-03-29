package com.techiespace.projects.fallingnotes;

import android.Manifest;
import android.content.pm.PackageManager;
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
import com.techiespace.projects.fallingnotes.fragments.ChordFragment;
import com.techiespace.projects.fallingnotes.fragments.MidiPlayerFragment;
import com.techiespace.projects.fallingnotes.fragments.ScaleFragment;

import java.util.List;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AppDatabase mDb;
    private databaseHandler dbhandler;
    private static final int REQUEST_RECORD_AUDIO = 13;
    private static final int REQUEST_READ_EXTERNAL_STORAGE = 12;


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
        mDb = AppDatabase.getInstance(this);
/*
        dbhandler = new databaseHandler(this);

        dbhandler.loadDatabase();*/


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.screen_area, new DashboardFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_dashboard);
        }

        setActionBarTitle("ChordSwift");
        displayLevelTable();
        handlePermissions();
    }

    //#TODO: Breaks when user manually removes permissions or denies permission
    private void handlePermissions() {
        requestMicrophonePermission();
        requestStoragePermission();
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_READ_EXTERNAL_STORAGE);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
    }

    private void requestMicrophonePermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},
                        REQUEST_RECORD_AUDIO);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }
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
        } else if (id == R.id.nav_midi) {
            fragment = new MidiPlayerFragment();
        }
        else if (id == R.id.nav_basic_chord) {
            fragment = new ChordFragment();
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

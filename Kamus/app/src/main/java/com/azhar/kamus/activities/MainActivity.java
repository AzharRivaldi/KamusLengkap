package com.azhar.kamus.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.azhar.kamus.R;
import com.azhar.kamus.fragment.FragmentIndonesiaInggris;
import com.azhar.kamus.fragment.FragmentInggrisIndonesia;
import com.google.android.material.navigation.NavigationView;

/**
 * Created by Azhar Rivaldi on 31-03-2020.
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displaySelectedScreen(R.id.nav_indo_inggris);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        displaySelectedScreen(item.getItemId());

        return true;
    }

    public void displaySelectedScreen(int itemId) {

        Fragment fragment = null;
        switch (itemId) {
            case R.id.nav_indo_inggris:
                fragment = new FragmentIndonesiaInggris();
                break;
            case R.id.nav_inggris_indo:
                fragment = new FragmentInggrisIndonesia();
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

}

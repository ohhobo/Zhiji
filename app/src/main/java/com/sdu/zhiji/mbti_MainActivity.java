package com.sdu.zhiji;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class mbti_MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button btnStartShort;
    private Button btnStartLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mbti_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.mbti_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnStartLong = (Button)findViewById(R.id.main_btn_start_long);
        btnStartShort = (Button)findViewById(R.id.main_btn_start_short);

        btnStartShort.setOnClickListener(view -> {
            Intent intent = new Intent(mbti_MainActivity.this, mbti_PrewordActivity.class);
            intent.putExtra(mbti_TestQuestionActivity.MODE, mbti_TestQuestionActivity.MODE_SHORT);
            startActivity(intent);
        });
        btnStartLong.setOnClickListener(view -> {
            Intent intent = new Intent(mbti_MainActivity.this, mbti_PrewordActivity.class);
            intent.putExtra(mbti_TestQuestionActivity.MODE, mbti_TestQuestionActivity.MODE_LONG);
            startActivity(intent);
        });
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
        getMenuInflater().inflate(R.menu.mbti_main, menu);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_short_test) {
            Intent intent = new Intent(mbti_MainActivity.this, mbti_PrewordActivity.class);
            intent.putExtra(mbti_TestQuestionActivity.MODE, mbti_TestQuestionActivity.MODE_SHORT);
            startActivity(intent);
        } else if (id == R.id.nav_long_test) {
            Intent intent = new Intent(mbti_MainActivity.this, mbti_PrewordActivity.class);
            intent.putExtra(mbti_TestQuestionActivity.MODE, mbti_TestQuestionActivity.MODE_LONG);
            startActivity(intent);
        } else if (id == R.id.nav_results) {
            Intent intent = new Intent(mbti_MainActivity.this, mbti_ResultsListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_about_test) {
            Intent intent = new Intent(this, mbti_AboutTestActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_descriptions) {
            Intent intent = new Intent(this, mbti_DescriptorsListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_person_types) {
            Intent intent = new Intent(this, mbti_PersonTypesListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_about_app) {
            Intent intent = new Intent(this, mbti_AboutAppActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

package com.wordpress.profoundengineering.piledesign;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame,new QpMeyerhofsMethod()).commit();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        FragmentManager optionsfragmentManager = getFragmentManager();
        //noinspection SimplifiableIfStatement
        if (id == R.id.how_to_use) {
            optionsfragmentManager.beginTransaction().replace(R.id.content_frame,new HowtoUse()).commit();
            return true;
        }
        if (id == R.id.about_app) {
            optionsfragmentManager.beginTransaction().replace(R.id.content_frame,new AboutApp()).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_qp_meyerhof) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new QpMeyerhofsMethod()).commit();
        } else if (id == R.id.nav_qp_vesic) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new QpVesicsMethod()).commit();
        } else if (id == R.id.nav_qp_coyle_and_castello) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new QpCoyleandCastellosMethod()).commit();
        } else if (id == R.id.nav_qs_meyerhof) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new QsMeyerhofsMethod()).commit();
        } else if (id == R.id.nav_qs_coyle_and_castello) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new QsCoyleandCastellosMethod()).commit();
        } else if (id == R.id.nav_qs_alpha) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new QsAlphaMethod()).commit();
        } else if (id == R.id.nav_qs_lambda) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new QsLambdaMethod()).commit();
        } else if (id == R.id.nav_qs_beta) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new QsBetaMethod()).commit();
        } else if (id == R.id.nav_qp_rock) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new QpRock()).commit();
        } else if (id == R.id.nav_group_efficiency) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new GroupEfficiency()).commit();
        } else if (id == R.id.nav_group_capacity_saturated_clay) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new GroupCapacitySaturatedClay()).commit();
        } else if (id == R.id.nav_group_settlement) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new GroupSettlement()).commit();
        } else if (id == R.id.nav_negative_skin_friction) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new NegativeSkinFriction()).commit();
        } else if (id == R.id.nav_cantilever_in_sand) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new CantileverSheetPlieInSand()).commit();
        } else if (id == R.id.nav_cantilever_in_clay) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new CantileverSheetPlieInClay()).commit();
        } else if (id == R.id.nav_anchored_in_sand) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new AnchoredSheetPlieInSand()).commit();
        } else if (id == R.id.nav_anchored_in_clay) {
            fragmentManager.beginTransaction().replace(R.id.content_frame,new AnchoredSheetPlieInClay()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

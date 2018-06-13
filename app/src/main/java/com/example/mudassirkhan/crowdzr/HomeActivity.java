package com.example.mudassirkhan.crowdzr;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mudassirkhan.crowdzr.adapter.HomePagerAdapter;
import com.example.mudassirkhan.crowdzr.ui.home.RequestDetailFragment;
import com.example.mudassirkhan.crowdzr.ui.home.RequestsFragment;
import com.example.mudassirkhan.crowdzr.ui.stack.StacksFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        RequestsFragment.OnFragmentInteractionListener,StacksFragment.OnFragmentInteractionListener {

    //Create these objects above OnCreate()of your main activity
    TextView menu_inbox,menu_notification,menu_favourite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        ViewPager viewPager=(ViewPager)findViewById(R.id.viewPagerHome);
        HomePagerAdapter homePagerAdapter=new HomePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(homePagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//These lines should be added in the OnCreate() of your main activity
        menu_inbox=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_inbox));
        menu_notification=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_notification));
        menu_favourite=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
                findItem(R.id.nav_favourite));
//This method will initialize the count value
        initializeCountDrawer();
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
        getMenuInflater().inflate(R.menu.home, menu);
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

    private void initializeCountDrawer(){

        //Gravity property aligns the text
        menu_inbox.setGravity(Gravity.CENTER_VERTICAL);
        menu_inbox.setTypeface(null, Typeface.BOLD);
        menu_inbox.setTextColor(getResources().getColor(R.color.colorAccent));
        menu_inbox.setText("02");
        menu_notification.setGravity(Gravity.CENTER_VERTICAL);
        menu_notification.setTypeface(null,Typeface.BOLD);
        menu_notification.setTextSize(20);
        menu_notification.setPadding(20,0,20,0);
        menu_notification.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        menu_notification.setTextColor(getResources().getColor(R.color.colorWhite));
        //count is added
        menu_notification.setText("12");
        menu_favourite.setGravity(Gravity.CENTER_VERTICAL);
        menu_favourite.setTypeface(null,Typeface.BOLD);
        menu_favourite.setPadding(20,0,20,0);
        menu_favourite.setBackgroundResource(R.drawable.menu_back);
        menu_favourite.setTextSize(20);

        menu_favourite.setTextColor(getResources().getColor(R.color.colorAccent));
        menu_favourite.setText("04");
        menu_favourite.setTextColor(getResources().getColor(R.color.colorWhite));
//        menu_favourite.getLayoutParams().height=50;
        //LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) menu_favourite.getLayoutParams();
        //params.height = 70;
        //menu_favourite.setLayoutParams(params);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_inbox) {

        } else if (id == R.id.nav_notification) {

        } else if (id == R.id.nav_favourite) {

        } else if (id == R.id.nav_shortcut) {

        } else if (id == R.id.nav_profile) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Fragment fragment = new RequestDetailFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentRequest, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}

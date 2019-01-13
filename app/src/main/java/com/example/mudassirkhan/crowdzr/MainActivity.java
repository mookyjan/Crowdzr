package com.example.mudassirkhan.crowdzr;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mudassirkhan.crowdzr.adapter.HomePagerAdapter;

public class MainActivity extends BaseActivity{

    LinearLayout linearLayoutInbox,linearLayoutNotification,linearLayoutFavorite;
    TextView menu_inbox,menu_notification,menu_favourite;
    public TabLayout tabLayout;
    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_main);
        //super.addContentView(R.layout.activity_main);
        setContentView(getContentView());
        initViews();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

    }

    public void initViews(){
        tabLayout=(TabLayout)findViewById(R.id.tab_layout);
        viewPager=(ViewPager)findViewById(R.id.viewPagerHome);
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

//        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        viewAppBar=(View)findViewById(R.id.app_bar_home);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
////        if (savedInstanceState == null) {
////            navItemIndex = 0;
////            CURRENT_TAG = TAG_HOME;
////            getHomeFragment();
////        }
//
////These lines should be added in the OnCreate() of your main activity
////        menu_inbox=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
////                findItem(R.id.nav_inbox));
//        //TODO change other menu item also if need
//        linearLayoutInbox = (LinearLayout) navigationView.getMenu().findItem(R.id.nav_inbox).getActionView();
//        menu_inbox=linearLayoutInbox.findViewById(R.id.txt_menu);
//        linearLayoutNotification=(LinearLayout)navigationView.getMenu().findItem(R.id.nav_notification).getActionView();
//        menu_notification=(TextView) linearLayoutNotification.findViewById(R.id.txt_menu);
//        linearLayoutFavorite=(LinearLayout)navigationView.getMenu().findItem(R.id.nav_favourite).getActionView();
//        menu_favourite=(TextView)linearLayoutFavorite.findViewById(R.id.txt_menu);
//        menu_notification=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
//                findItem(R.id.nav_notification));
//        menu_favourite=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
//                findItem(R.id.nav_favourite));
        // This method will initialize the count value
       // initializeCountDrawer();
    }

    private void initializeCountDrawer(){

        //Gravity property aligns the text
//        menu_inbox.setGravity(Gravity.CENTER_VERTICAL);
//        menu_inbox.setTypeface(null, Typeface.BOLD);
//        menu_inbox.setTextColor(getResources().getColor(R.color.colorAccent));
//        menu_inbox.setText("02");
        menu_notification.setGravity(Gravity.CENTER);
        menu_notification.setTypeface(null, Typeface.BOLD);
        menu_notification.setTextSize(15);
        menu_notification.setPadding(20,0,20,0);
        menu_notification.setBackgroundResource(R.drawable.menu_back);
        menu_notification.setTextColor(getResources().getColor(R.color.colorWhite));
        GradientDrawable background = (GradientDrawable) menu_notification.getBackground();
        //  menu_notification.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        background.setColor(getResources().getColor(R.color.colorAccent));
        //count is added
        menu_notification.setText("12");
        menu_favourite.setGravity(Gravity.CENTER);
        menu_favourite.setTypeface(null,Typeface.BOLD);
        menu_favourite.setPadding(20,0,20,0);
        menu_favourite.setBackgroundResource(R.drawable.menu_back);
        menu_favourite.setTextSize(15);
        menu_favourite.setText("04");
        menu_favourite.setTextColor(getResources().getColor(R.color.colorWhite));
//        menu_favourite.getLayoutParams().height=50;
        //LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) menu_favourite.getLayoutParams();
        //params.height = 70;
        //menu_favourite.setLayoutParams(params);
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

}

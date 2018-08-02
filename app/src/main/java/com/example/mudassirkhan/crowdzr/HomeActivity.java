package com.example.mudassirkhan.crowdzr;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
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
import com.example.mudassirkhan.crowdzr.ui.BackableFragment;
import com.example.mudassirkhan.crowdzr.ui.favorite.FavoriteFragment;
import com.example.mudassirkhan.crowdzr.ui.home.RequestDetailFragment;
import com.example.mudassirkhan.crowdzr.ui.home.RequestsFragment;
import com.example.mudassirkhan.crowdzr.ui.inbox.InboxFragment;
import com.example.mudassirkhan.crowdzr.ui.profile.ProfileFragment;
import com.example.mudassirkhan.crowdzr.ui.request.AddRequestFragment;
import com.example.mudassirkhan.crowdzr.ui.sales.ManageSalesFragment;
import com.example.mudassirkhan.crowdzr.ui.stack.MyStacksFragment;
import com.example.mudassirkhan.crowdzr.ui.stack.StacksFragment;
import com.example.mudassirkhan.crowdzr.ui.user.UserDetailFragment;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        RequestsFragment.OnFragmentInteractionListener,StacksFragment.OnFragmentInteractionListener {

    //Create these objects above OnCreate()of your main activity
    LinearLayout linearLayoutInbox,linearLayoutNotification,linearLayoutFavorite;
    TextView menu_inbox,menu_notification,menu_favourite;
   public TabLayout tabLayout;
   public ViewPager viewPager;
   public Toolbar toolbar;
   public View viewAppBar;
    DrawerLayout drawer;
    // index to identify current nav menu item
    public static int navItemIndex = 0;
    // tags used to attach the fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_INBOX = "inbox";
    private static final String TAG_FAVORITES = "favorites";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

         drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        viewAppBar=(View)findViewById(R.id.app_bar_home);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        if (savedInstanceState == null) {
//            navItemIndex = 0;
//            CURRENT_TAG = TAG_HOME;
//            getHomeFragment();
//        }

//These lines should be added in the OnCreate() of your main activity
//        menu_inbox=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
//                findItem(R.id.nav_inbox));
        //TODO change other menu item also if need
        linearLayoutInbox = (LinearLayout) navigationView.getMenu().findItem(R.id.nav_inbox).getActionView();
        menu_inbox=linearLayoutInbox.findViewById(R.id.txt_menu);
        linearLayoutNotification=(LinearLayout)navigationView.getMenu().findItem(R.id.nav_notification).getActionView();
        menu_notification=(TextView) linearLayoutNotification.findViewById(R.id.txt_menu);
        linearLayoutFavorite=(LinearLayout)navigationView.getMenu().findItem(R.id.nav_favourite).getActionView();
        menu_favourite=(TextView)linearLayoutFavorite.findViewById(R.id.txt_menu);
//        menu_notification=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
//                findItem(R.id.nav_notification));
//        menu_favourite=(TextView) MenuItemCompat.getActionView(navigationView.getMenu().
//                findItem(R.id.nav_favourite));
        // This method will initialize the count value
        initializeCountDrawer();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            // if there is a fragment and the back stack of this fragment is not empty,
//            // then emulate 'onBackPressed' behaviour, because in default, it is not working
            FragmentManager fm = getSupportFragmentManager();
            for (Fragment frag : fm.getFragments()) {
                if (frag.isVisible()) {
                    FragmentManager childFm = frag.getChildFragmentManager();
                    if (childFm.getBackStackEntryCount() > 0) {
                        childFm.popBackStack();
                        return;
                    }
                }
            }

     //       tellFragments();
//            super.onBackPressed();

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
//        menu_inbox.setGravity(Gravity.CENTER_VERTICAL);
//        menu_inbox.setTypeface(null, Typeface.BOLD);
//        menu_inbox.setTextColor(getResources().getColor(R.color.colorAccent));
//        menu_inbox.setText("02");
        menu_notification.setGravity(Gravity.CENTER);
        menu_notification.setTypeface(null,Typeface.BOLD);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            navItemIndex=0;
            CURRENT_TAG=TAG_HOME;
            Intent myIntent=new Intent(HomeActivity.this,HomeActivity.class);
            startActivity(myIntent);
           // recreate();
            // Handle the camera action
        } else if (id == R.id.nav_inbox) {
            navItemIndex=1;
            CURRENT_TAG=TAG_INBOX;
            InboxFragment inboxFragment=new InboxFragment();
            goToFragment(inboxFragment);

        } else if (id == R.id.nav_notification) {
            navItemIndex=2;
            CURRENT_TAG=TAG_NOTIFICATIONS;

        } else if (id == R.id.nav_favourite) {
            FavoriteFragment favoriteFragment=new FavoriteFragment();
            goToFragment(favoriteFragment);

        } else if (id == R.id.nav_shortcut) {

        } else if (id == R.id.nav_profile) {
            ProfileFragment profileFragment=new ProfileFragment();
            goToFragment(profileFragment);
        }
        else if (id==R.id.nav_requests){
            AddRequestFragment profileFragment=new AddRequestFragment();
            goToFragment(profileFragment);
        }else if (id==R.id.nav_manage_sales){
            ManageSalesFragment manageSalesFragment=new ManageSalesFragment();
            goToFragment(manageSalesFragment);
        }else if (id==R.id.nav_my_stacks){
            MyStacksFragment myStacksFragment=new MyStacksFragment();
            goToFragment(myStacksFragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Fragment getHomeFragment(){
        switch (navItemIndex){
            case 0:
                //home
                 StacksFragment stacksFragment=new StacksFragment();
                 return stacksFragment;
            case 1:
                InboxFragment inboxFragment=new InboxFragment();
                return inboxFragment;

            case 2:
                FavoriteFragment favoriteFragment=new FavoriteFragment();
                return favoriteFragment;

            case 3:
                RequestsFragment requestsFragment=new RequestsFragment();
                return requestsFragment;

                default:
                    return new RequestsFragment();
        }
    }

//    @Override
//    public void onBackPressed() {
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawers();
//            return;
//        }
//
//        // This code loads home fragment when back key is pressed
//        // when user is in other fragment than home
////        if (shouldLoadHomeFragOnBackPress) {
////            // checking if user is on other navigation menu
////            // rather than home
////            if (navItemIndex != 0) {
////                navItemIndex = 0;
////                CURRENT_TAG = TAG_HOME;
////                loadHomeFragment();
////                return;
////            }
//      //  }
//
//        super.onBackPressed();
//    }

    public void goToFragment(Fragment fragmentArg){

        Fragment fragment = fragmentArg;
        String backStateName = fragment.getClass().getName();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerHome, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Fragment fragment = new RequestDetailFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        String backStateName = fragment.getClass().getName();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentRequest, fragment);
        fragmentTransaction.addToBackStack(backStateName);
        fragmentTransaction.commit();
    }




    private void tellFragments() {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment f : fragments) {
            if (f != null && f instanceof BackableFragment)
                ((BackableFragment) f).onBackButtonPressed();
            break;
        }
    }
}

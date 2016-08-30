package com.sharity.itu.sharity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Madhura on 8/16/2016.
 */
public class HomePage extends AppCompatActivity implements OnClickListener {

    TextView name;
    Button nBtn;
    Button ProfBtn;
    Button ReqBtn;
    Button HistBtn;

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private Toolbar toolbar;

    //Declare Titles And Icons For Our Navigation Drawer List View
    //This Icons And Titles are contained in an Array

    String TITLES[] = {"New Request","Edit Profile","History","Home", "Logout"};
    int ICONS[] = {R.drawable.ic_queue,R.drawable.ic_person_outline,R.drawable.ic_archive,R.drawable.ic_home,R.drawable.ic_power};

    String NAME = "";
    String EMAIL = "";

    int PROFILE = R.drawable.app_icon;

    RecyclerView mRecyclerView;                           // Declare RecyclerView
    RecyclerView.Adapter mAdapter;                        // Declare Adapter For Recycler View
    RecyclerView.LayoutManager mLayoutManager;            // Declare Layout Manager as a linear layout manager
    DrawerLayout Drawer;                                  // Declare DrawerLayout

    ActionBarDrawerToggle mDrawerToggle;                  // Declare Action Bar Drawer Toggle


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_tab);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);                   // Setting toolbar as the ActionBar with setSupportActionBar() call

        Bundle bundle = getIntent().getExtras();
        NAME = bundle.getString("NAME");
        EMAIL = bundle.getString("EMAIL");


        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView); // Assigning the RecyclerView Object to the xml View
        mRecyclerView.setHasFixedSize(true);                            // Letting the system know that the list objects are of fixed size

        mAdapter = new MyAdapter(TITLES,ICONS,NAME,EMAIL,PROFILE,this);       // Creating the Adapter of MyAdapter class(which we are going to see in a bit)
                                                                            // And passing the titles,icons,header view name, header view email,
                                                                            // and header view profile picture

        mRecyclerView.setAdapter(mAdapter);                              // Setting the adapter to RecyclerView


        mLayoutManager = new LinearLayoutManager(this);                 // Creating a layout Manager

        mRecyclerView.setLayoutManager(mLayoutManager);                 // Setting the layout Manager


        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);        // Drawer object Assigned to the view
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,R.string.openDrawer,R.string.closeDrawer){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // code here will execute once the drawer is opened( As I dont want anything happened whe drawer is
                // open I am not going to put anything here)
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                // Code here will execute once drawer is closed
            }



        }; // Drawer Toggle Object Made
        Drawer.setDrawerListener(mDrawerToggle); // Drawer Listener set to the Drawer toggle
        mDrawerToggle.syncState();               // Finally we set the drawer toggle sync State

        //Creating tabbed layout
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), NAME);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);

        final TabLayout.Tab HighTab = tabLayout.newTab();
        final TabLayout.Tab MediumTab = tabLayout.newTab();
        final TabLayout.Tab LowTab = tabLayout.newTab();

        HighTab.setText("High");
        MediumTab.setText("Medium");
        LowTab.setText("Low");

        tabLayout.addTab(HighTab, 0);
        tabLayout.addTab(MediumTab, 1);
        tabLayout.addTab(LowTab, 2);

        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.tab1_selector));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                int position = tab.getPosition();

                if(position == 0){
                    tabLayout.setTabTextColors(ContextCompat.getColorStateList(HomePage.this, R.color.tab1_selector));
                    viewPager.setCurrentItem(tab.getPosition());
                }
                if(position == 1){
                    tabLayout.setTabTextColors(ContextCompat.getColorStateList(HomePage.this, R.color.tab2_selector));
                    viewPager.setCurrentItem(tab.getPosition());
                }
                if(position == 2){
                    tabLayout.setTabTextColors(ContextCompat.getColorStateList(HomePage.this, R.color.tab3_selector));
                    viewPager.setCurrentItem(tab.getPosition());
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
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

    @Override
    public void onClick(View v) {


    }

    public class CustomView extends View
    {
        public CustomView(Context context)
        {
            super(context, null);
        }
    }
}

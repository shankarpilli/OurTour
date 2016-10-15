package com.versatilemobitech.ourtour.activities;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.versatilemobitech.ourtour.R;
import com.versatilemobitech.ourtour.adapters.LeftMenuAdapter;
import com.versatilemobitech.ourtour.fragments.AboutUsFragment;
import com.versatilemobitech.ourtour.fragments.ContctUsFragment;
import com.versatilemobitech.ourtour.fragments.DiclaimerFragment;
import com.versatilemobitech.ourtour.fragments.HelpFragment;
import com.versatilemobitech.ourtour.fragments.HomeFragment;
import com.versatilemobitech.ourtour.fragments.TermsAndConditionsFragment;
import com.versatilemobitech.ourtour.utils.Utility;

import java.util.ArrayList;

/**
 * Created by Rev's Nani on 13-10-2016.
 */
public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout drawerHomeLayout;
    private ImageView imgLeftDrawerIcon;
    private ListView lvLeftDrawer;

    private ArrayList<String> mSideMenuItemNamesList;
    private int[] mSideMenuItemIconsList = {R.drawable.side_menu_home, R.drawable.side_menu_abou_us,
            R.drawable.side_menu_contact_us,R.drawable.side_menu_terms_conditions,
            R.drawable.side_menu_diclaimer,R.drawable.side_menu_help};

    private LeftMenuAdapter mLeftMenuAdapter= null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        initUI();
        Utility.navigateDashBoardFragment(new HomeFragment(), HomeFragment.TAG, null, DashboardActivity.this);
    }

    private void initUI() {

        drawerHomeLayout = (DrawerLayout)findViewById(R.id.drawer_home_layout);
        imgLeftDrawerIcon = (ImageView)findViewById(R.id.iv_left_drawer_icon);
        lvLeftDrawer = (ListView)findViewById(R.id.lv_left_drawer);

        setData();
    }

    private void setData() {
        mSideMenuItemNamesList = new ArrayList<>();
        mSideMenuItemNamesList.add("Home");
        mSideMenuItemNamesList.add("About Us");
        mSideMenuItemNamesList.add("Contact us");
        mSideMenuItemNamesList.add("Terms and conditions");
        mSideMenuItemNamesList.add("Disclaime");
        mSideMenuItemNamesList.add("Help");

        mLeftMenuAdapter = new LeftMenuAdapter(this,mSideMenuItemNamesList,mSideMenuItemIconsList);

        lvLeftDrawer.setAdapter(mLeftMenuAdapter);
        setHeaderFooterToListView(lvLeftDrawer);
        
        lvLeftDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
                drawerHomeLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        drawerHomeLayout.closeDrawers();
                        navigateScreens(position);
                    }
                }, 300);
            }
        });
    }

    private void navigateScreens(int position) {
        switch (position) {
            case 0:
                Utility.navigateDashBoardFragment(new HomeFragment(), HomeFragment.TAG, null, DashboardActivity.this);
                break;
            case 1:
                Utility.navigateDashBoardFragment(new AboutUsFragment(), AboutUsFragment.TAG, null, DashboardActivity.this);
                break;
            case 2:
                Utility.navigateDashBoardFragment(new ContctUsFragment(), ContctUsFragment.TAG, null, DashboardActivity.this);
                break;
            case 3:
                Utility.navigateDashBoardFragment(new TermsAndConditionsFragment(), TermsAndConditionsFragment.TAG, null, DashboardActivity.this);
                break;
            case 4:
                Utility.navigateDashBoardFragment(new DiclaimerFragment(), DiclaimerFragment.TAG, null, DashboardActivity.this);
                break;
            case 5:
                Utility.navigateDashBoardFragment(new HelpFragment(), HelpFragment.TAG, null, DashboardActivity.this);
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.drawer_home_layout:
                drawerHomeLayout.openDrawer(GravityCompat.START);
                break;
        }
    }
    private void setHeaderFooterToListView(ListView list_view) {
        LinearLayout layout_list_header = (LinearLayout) getLayoutInflater().inflate(R.layout.
                layout_list_header, null);
        list_view.addHeaderView(layout_list_header);

    }
}

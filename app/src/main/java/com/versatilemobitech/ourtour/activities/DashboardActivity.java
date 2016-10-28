package com.versatilemobitech.ourtour.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.versatilemobitech.ourtour.R;
import com.versatilemobitech.ourtour.adapters.LeftMenuAdapter;
import com.versatilemobitech.ourtour.fragments.AboutUsFragment;
import com.versatilemobitech.ourtour.fragments.AddCarFragment;
import com.versatilemobitech.ourtour.fragments.ContctUsFragment;
import com.versatilemobitech.ourtour.fragments.DiclaimerFragment;
import com.versatilemobitech.ourtour.fragments.HelpFragment;
import com.versatilemobitech.ourtour.fragments.HomeFragment;
import com.versatilemobitech.ourtour.fragments.TermsAndConditionsFragment;
import com.versatilemobitech.ourtour.fragments.VehicleRegistrationFragment;
import com.versatilemobitech.ourtour.fragments.VendorPriceFragment;
import com.versatilemobitech.ourtour.fragments.VendorRegistrationFragment;
import com.versatilemobitech.ourtour.utils.Constants;
import com.versatilemobitech.ourtour.utils.Utility;

import java.util.ArrayList;

/**
 * Created by Rev's Nani on 13-10-2016.
 * Edited by Shankar Pilli 25-10-2016
 */
public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    public DrawerLayout mDrawerLayout;
    private ImageView imgLeftDrawerIcon;
    private ListView lvLeftDrawer;
    public static TextView txt_our_tour;
    public static TextView txt_vendor_id;
    private Bundle mBundle;

    private ArrayList<String> mSideMenuItemNamesList;
    private int[] mSideMenuItemIconsList = {R.drawable.side_menu_home, R.drawable.side_menu_abou_us,
            R.drawable.side_menu_contact_us, R.drawable.side_menu_terms_conditions,
            R.drawable.side_menu_diclaimer, R.drawable.side_menu_help};

    private LeftMenuAdapter mLeftMenuAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.activity_dash_board);
        initUI();
        mBundle = new Bundle();
        mBundle.putString("Home", "Home");
        Utility.navigateDashBoardFragment(new HomeFragment(), HomeFragment.TAG, mBundle, DashboardActivity.this);
    }

    private void initUI() {

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_home_layout);
        imgLeftDrawerIcon = (ImageView) findViewById(R.id.iv_left_drawer_icon);
        lvLeftDrawer = (ListView) findViewById(R.id.list_home_left_drawer);
        txt_our_tour = (TextView) findViewById(R.id.txt_our_tour);
        setData();

        imgLeftDrawerIcon.setOnClickListener(this);
    }

    private void setData() {
        mSideMenuItemNamesList = new ArrayList<>();
        mSideMenuItemNamesList.add("Home");
        mSideMenuItemNamesList.add("About Us");
        mSideMenuItemNamesList.add("Contact us");
        mSideMenuItemNamesList.add("Terms and conditions");
        mSideMenuItemNamesList.add("Disclaimer");
        mSideMenuItemNamesList.add("Help");

        mLeftMenuAdapter = new LeftMenuAdapter(this, mSideMenuItemNamesList, mSideMenuItemIconsList);
        lvLeftDrawer.setAdapter(mLeftMenuAdapter);
        setHeaderFooterToListView(lvLeftDrawer);

        lvLeftDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                mDrawerLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mDrawerLayout.closeDrawers();
                        navigateScreens(position);
                    }
                }, 300);
            }
        });
    }

    private void navigateScreens(int position) {
        mBundle = new Bundle();
        switch (position) {
            case 1:
                mBundle.putString("Home", "Home");
                Utility.navigateDashBoardFragment(new HomeFragment(), HomeFragment.TAG, mBundle, DashboardActivity.this);
                break;
            case 2:
                mBundle.putString("AboutUs", "About Us");
                Utility.navigateDashBoardFragment(new AboutUsFragment(), AboutUsFragment.TAG, mBundle, DashboardActivity.this);
                break;
            case 3:
                mBundle.putString("ContactUs", "Contact Us");
                Utility.navigateDashBoardFragment(new ContctUsFragment(), ContctUsFragment.TAG, mBundle, DashboardActivity.this);
                break;
            case 4:
                mBundle.putString("TermsAndConditions", "Terms And Conditions");
                Utility.navigateDashBoardFragment(new TermsAndConditionsFragment(), TermsAndConditionsFragment.TAG, mBundle, DashboardActivity.this);
                break;
            case 5:
                mBundle.putString("Diclaimer", "Diclaimer");
                Utility.navigateDashBoardFragment(new DiclaimerFragment(), DiclaimerFragment.TAG, mBundle, DashboardActivity.this);
                break;
            case 6:
                mBundle.putString("Help", "Help");
                Utility.navigateDashBoardFragment(new HelpFragment(), HelpFragment.TAG, mBundle, DashboardActivity.this);
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left_drawer_icon:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
    }

    private void setHeaderFooterToListView(ListView list_view) {
        LinearLayout layout_list_header = (LinearLayout) getLayoutInflater().inflate(R.layout.
                layout_list_header, null);
        txt_vendor_id = (TextView) layout_list_header.findViewById(R.id.txt_vendor_id);
        txt_vendor_id.setTypeface(Utility.setTypeFace_Roboto_Bold(this));
        txt_vendor_id.setText("VENDOR ID: " + Utility.getSharedPrefStringData(this, Constants.VENDOR_ID));
        list_view.addHeaderView(layout_list_header);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            FragmentManager.BackStackEntry backEntry = getSupportFragmentManager()
                    .getBackStackEntryAt(
                            getSupportFragmentManager()
                                    .getBackStackEntryCount() - 1);
            String tagName = backEntry.getName();
            if (tagName.equals(HomeFragment.TAG)) {
                finishAffinity();
            }
            if (tagName.equals(AddCarFragment.TAG)) {
                VendorRegistrationFragment.vendorModel = null;
                VehicleRegistrationFragment.vehicleRegistration = null;
                VendorPriceFragment.mVehiclePricing = null;
                super.onBackPressed();
            } else {
                super.onBackPressed();
            }
        }
    }
}

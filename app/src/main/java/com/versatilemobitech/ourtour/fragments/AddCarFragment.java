package com.versatilemobitech.ourtour.fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.versatilemobitech.ourtour.R;
import com.versatilemobitech.ourtour.activities.DashboardActivity;
import com.versatilemobitech.ourtour.adapters.ViewPagerAdapter;
import com.versatilemobitech.ourtour.utils.Utility;


/**
 * Created by Shankar Pilli.
 */
public class AddCarFragment extends Fragment {

    public static final String TAG = "AddCarFragment";
    private DashboardActivity mParent;
    private Toolbar mToolbar;
    private View rootView;
    public static TabLayout tabLayout;
    public static ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParent = (DashboardActivity) getActivity();
        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mToolbar != null) {
            mToolbar.setVisibility(View.VISIBLE);
        }
        if (rootView != null) {
            return rootView;
        }
        rootView = inflater.inflate(R.layout.fragment_add_car, container, false);
        initUI();
        return rootView;
    }

    private void initUI() {
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(0);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFrag(new VendorRegistrationFragment(), "ONE");
        adapter.addFrag(new VehicleRegistrationFragment(), "TWO");
        adapter.addFrag(new VendorPriceFragment(), "THREE");
        viewPager.setAdapter(adapter);
    }

    private void setupTabIcons() {

        LinearLayout tabOne = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        TextView textview = (TextView) tabOne.findViewById(R.id.txt_image);
        ImageView img_icon = (ImageView) tabOne.findViewById(R.id.img_icon);
        textview.setText(Utility.getResourcesString(getActivity(), R.string.txt_vendor_registration));
        textview.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        img_icon.setImageDrawable(getResources().getDrawable(R.drawable.layer_selected));
        tabLayout.getTabAt(0).setCustomView(tabOne);

        LinearLayout tabTwo = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        TextView text_view_two = (TextView) tabTwo.findViewById(R.id.txt_image);
        ImageView img_icon_two = (ImageView) tabTwo.findViewById(R.id.img_icon);
        text_view_two.setText(Utility.getResourcesString(getActivity(), R.string.txt_vehicle_registration));
        text_view_two.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        img_icon_two.setImageDrawable(getResources().getDrawable(R.drawable.layer_not_selected));
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        LinearLayout tabThree = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.custom_tab, null);
        TextView text_view_three = (TextView) tabThree.findViewById(R.id.txt_image);
        ImageView img_icon_three = (ImageView) tabThree.findViewById(R.id.img_icon);
        text_view_three.setText(Utility.getResourcesString(getActivity(), R.string.txt_vendor_price));
        text_view_three.setTypeface(Utility.setTypeFace_Roboto_Regular(getActivity()));
        img_icon_three.setImageDrawable(getResources().getDrawable(R.drawable.layer_not_selected));
        tabLayout.getTabAt(2).setCustomView(tabThree);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LinearLayout selectedTab = (LinearLayout) tab.getCustomView();
                /*selectedTab.setLayoutParams(new
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 45));*/
                TextView textview = (TextView) selectedTab.findViewById(R.id.txt_image);
                ImageView img_icon = (ImageView) selectedTab.findViewById(R.id.img_icon);
                if (tab.getPosition() == 0) {
                    mParent.txt_our_tour.setText("Vendor Registration");
                    img_icon.setImageDrawable(getResources().getDrawable(R.drawable.layer_selected));
                    viewPager.setCurrentItem(0);
                } else if (tab.getPosition() == 1) {
                    img_icon.setImageDrawable(getResources().getDrawable(R.drawable.layer_selected));
                    if (VendorRegistrationFragment.vendorModel == null) {
                        mParent.txt_our_tour.setText("Vendor Registration");
                        tabLayout.getTabAt(0).select();
                        Utility.showToastMessage(getActivity(), "Please fill Vendor Registration");
                    } else {
                        mParent.txt_our_tour.setText("Vehicle Registration");
                        viewPager.setCurrentItem(1);
                    }
                } else {
                    //viewPager.setCurrentItem(2);
                    img_icon.setImageDrawable(getResources().getDrawable(R.drawable.layer_selected));
                    if (VendorRegistrationFragment.vendorModel == null) {
                        mParent.txt_our_tour.setText("Vendor Registration");
                        tabLayout.getTabAt(0).select();
                        Utility.showToastMessage(getActivity(), "Please fill Vendor Registration");
                    } else if (VehicleRegistrationFragment.vehicleRegistration == null) {
                        mParent.txt_our_tour.setText("Vehicle Registration");
                        tabLayout.getTabAt(1).select();
                        Utility.showToastMessage(getActivity(), "Please fill Vehicle Registration");
                    } else {
                        mParent.txt_our_tour.setText("Vendor Price");
                        viewPager.setCurrentItem(2);
                    }
                }
                textview.setTextColor(getResources().getColor(R.color.blackColor));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LinearLayout selectedTab = (LinearLayout) tab.getCustomView();
                /*selectedTab.setLayoutParams(new
                        LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 45));*/
                TextView textview = (TextView) selectedTab.findViewById(R.id.txt_image);
                ImageView img_icon = (ImageView) selectedTab.findViewById(R.id.img_icon);
                if (tab.getPosition() == 0) {
                    img_icon.setImageDrawable(getResources().getDrawable(R.drawable.layer_not_selected));
                } else if (tab.getPosition() == 1) {
                    img_icon.setImageDrawable(getResources().getDrawable(R.drawable.layer_not_selected));
                } else {
                    img_icon.setImageDrawable(getResources().getDrawable(R.drawable.layer_not_selected));
                }
                textview.setTextColor(getResources().getColor(R.color.blackColor));
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        tabLayout.getTabAt(2).select();
        tabLayout.getTabAt(1).select();
        tabLayout.getTabAt(0).select();
    }
}

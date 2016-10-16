package com.versatilemobitech.ourtour.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.versatilemobitech.ourtour.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VendorPriceFragment extends Fragment {
    private EditText et_driver_owner_name;


    public VendorPriceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vendor_price, container, false);
    }

}

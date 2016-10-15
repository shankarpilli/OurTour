package com.versatilemobitech.ourtour.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.versatilemobitech.ourtour.R;

import java.util.ArrayList;

/**
 * Created by Rev's Nani on 14-10-2016.
 */

public class LeftMenuAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    ArrayList<String> mSideMenuItemNamesList;
    int[] mSideMenuItemIconsList;

    public LeftMenuAdapter(Context mContext, ArrayList<String> mSideMenuItemNamesList, int[] mSideMenuItemIconsList) {
        this.mContext = mContext;
        this.mSideMenuItemIconsList = mSideMenuItemIconsList;
        this.mSideMenuItemNamesList = mSideMenuItemNamesList;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return mSideMenuItemNamesList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LeftMenuItemHolder mLeftMenuItemHolder = null;

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_left_menu,
                    null);
            mLeftMenuItemHolder = new LeftMenuItemHolder();
            mLeftMenuItemHolder.txt_left_drawer_text = (TextView) convertView.findViewById(R.id.txt_left_drawer_text);
            mLeftMenuItemHolder.iv_left_drawer_icon = (ImageView) convertView.findViewById(R.id.iv_left_drawer_icon);
            convertView.setTag(mLeftMenuItemHolder);
        } else {
            mLeftMenuItemHolder = (LeftMenuItemHolder) convertView.getTag();
        }
        mLeftMenuItemHolder.txt_left_drawer_text.setText(mSideMenuItemNamesList.get(position));
        mLeftMenuItemHolder.iv_left_drawer_icon.setImageResource(mSideMenuItemIconsList[position]);
        return convertView;
    }
    private class LeftMenuItemHolder {
        private TextView txt_left_drawer_text;
        private ImageView iv_left_drawer_icon;
    }
}

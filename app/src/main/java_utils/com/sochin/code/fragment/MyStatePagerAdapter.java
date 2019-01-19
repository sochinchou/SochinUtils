package com.sochin.code.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2019/1/2.
 */

public class MyStatePagerAdapter extends FragmentStatePagerAdapter{

    private List<MyBaseFragment> mFragmentList;

    public MyStatePagerAdapter(FragmentManager fm, List<MyBaseFragment> fragments) {
        super(fm);
        mFragmentList = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}

package com.sochin.test.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.sochin.R;
import com.sochin.code.fragment.MyBaseFragment;

/**
 * Created by Administrator on 2018/5/7.
 */

public class FragmentGreen extends MyBaseFragment {
	@Override
	protected int getContentLayout() {
		return R.layout.fragment_green;
	}


	@Override
	public String getFragmentTag() {
		return "green";
	}

	@Override
	public void onResume() {
		super.onResume();

		FragmentManager fragmentManager = getChildFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.fl, new FragmentBlue())
				.commit();

		Log.d(TAG, "||||||||||||||||||||||| onResume() >>>");
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);


	}
}

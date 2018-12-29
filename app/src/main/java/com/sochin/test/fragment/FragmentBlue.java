package com.sochin.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sochin.R;
import com.sochin.code.fragment.MyBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/5/7.
 */

public class FragmentBlue extends MyBaseFragment {

	private long time = 0;
	@Override
	protected int getContentLayout() {
		return R.layout.fragment_blue;
	}


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	time = System.currentTimeMillis();

		View result = super.onCreateView(inflater, container, savedInstanceState);


		return result;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public String getFragmentTag() {
		return "blue";
	}


	@Override
	public void onStart() {
		super.onStart();

	}

	@Override
	public void onResume() {
		Log.d(TAG, "////////");
		super.onResume();

		time = System.currentTimeMillis();
		for(int i = 0; i < 50000; i++ ){
			for(int j = 0; j< 50000; j++){

			}
		}
		Log.d(TAG, "//////////////////////////");
	}
}

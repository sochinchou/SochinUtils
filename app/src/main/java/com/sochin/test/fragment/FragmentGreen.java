package com.sochin.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sochin.R;
import com.sochin.code.fragment.MyBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/7.
 */

public class FragmentGreen extends MyBaseFragment{

	@Override
	protected int getContentLayout() {
		return R.layout.fragment_green;
	}

	@Override
	public String getFragmentTag() {
		return "blue";
	}

	@Override
	public void initViews() {
		ButterKnife.bind(this, mRootView);



	}

	@Override
	public void initBeforeView() {

	}

	@Override
	public void destroyViews() {
//		radioGroupMusic.clearCheck();
//		radioGroupMusic.setOnCheckedChangeListener(null);
	}


	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

//		Log.d(TAG, "initViews() >>> clear");




	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}


	@Override
	public void onResume() {
		super.onResume();

	}





}

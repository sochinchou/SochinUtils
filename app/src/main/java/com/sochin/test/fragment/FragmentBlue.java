package com.sochin.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sochin.R;
import com.sochin.code.fragment.MyBaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/5/7.
 */

public class FragmentBlue extends MyBaseFragment{


	@BindView(R.id.txt)
	TextView txt;

	@Override
	protected int getContentLayout() {
		return R.layout.fragment_blue;
	}

	@Override
	public String getFragmentTag() {
		return "blue";
	}

	@Override
	public void initViews() {
		ButterKnife.bind(this, mRootView);
		txt.setFocusable(true);
		txt.setFocusableInTouchMode(true);
		txt.setOnTouchListener(new View.OnTouchListener(){
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				txt.requestFocus();
				return false;
			}
		});

	}

	@Override
	public void initBeforeView() {

	}

	@Override
	public void destroyViews() {
//		radioGroupMusic.clearCheck();
	}


}

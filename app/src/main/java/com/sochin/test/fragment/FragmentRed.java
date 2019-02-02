package com.sochin.test.fragment;

import com.sochin.R;
import com.sochin.code.fragment.MyBaseFragment;

/**
 * Created by Administrator on 2018/5/7.
 */

public class FragmentRed extends MyBaseFragment {

	@Override
	protected int getContentLayout() {
		return R.layout.fragment_red;
	}

	@Override
	public String getFragmentTag() {
		return "red";
	}

	@Override
	public void initViews() {

	}

	@Override
	public void initBeforeView() {

	}

	@Override
	public void destroyViews() {

	}
}

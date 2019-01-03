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
	public String getFragmentTag() {
		return "blue";
	}
}

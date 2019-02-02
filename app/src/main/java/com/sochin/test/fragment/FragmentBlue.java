package com.sochin.test.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
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

public class FragmentBlue extends MyBaseFragment implements RadioGroup.OnCheckedChangeListener{


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

//		radioGroupMusic.check(R.id.radioMyMusic);
		radioGroupMusic.setOnCheckedChangeListener(this);
	}

	@Override
	public void initBeforeView() {

	}

	@Override
	public void destroyViews() {
//		radioGroupMusic.clearCheck();
	}

	@BindView(R.id.radioGroupMusic)
	RadioGroup radioGroupMusic;
	@BindView(R.id.radioBtMusic)
	RadioButton radioBtMusic;
	@BindView(R.id.radioUsbMusic)
	RadioButton radioUsbMusic;
	@BindView(R.id.radioMyMusic)
	RadioButton radioMyMusic;

	//************************************************
	//
	//************************************************
	@Override
	public void onCheckedChanged(RadioGroup radioGroup, int id) {

		Log.d(TAG, "XXXXXXXXXX radioGroup = " + radioGroup + " id = " + id);
		switch (id){
			case R.id.radioBtMusic:
				Log.d(TAG,"id = BtMusic");
//				mFragment.getParent().switchMusicFragment(FragmentFactory.FRAGMENT_BT_MUSIC_PLAYING, null);
				break;

			case R.id.radioUsbMusic:
				Log.d(TAG,"id = UsbMusic");
//                mFragment.getParent().switchMusicFragment(FragmentFactory.FRAGMENT_USB_MUSIC_PLAYING, null);
				break;

			case R.id.radioMyMusic:
				Log.d(TAG,"id = MyMusic");
//				mFragment.getParent().switchMusicFragment(FragmentFactory.FRAGMENT_MY_MUSIC_PLAYING, null);
				break;
		}
	}
}

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

public class FragmentGreen extends MyBaseFragment implements RadioGroup.OnCheckedChangeListener{


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


//		radioGroupMusic.check(R.id.radioUsbMusic);


		btnClear.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				radioGroupMusic.clearCheck();
			}
		});
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
		radioGroupMusic.setOnCheckedChangeListener(null);
		radioGroupMusic.check(R.id.radioUsbMusic);
//		Log.d(TAG, "initViews() >>> clear");




	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

	}


	@Override
	public void onResume() {
		super.onResume();
		radioGroupMusic.setOnCheckedChangeListener(this);
	}

	@BindView(R.id.radioGroupMusic)
	RadioGroup radioGroupMusic;
	@BindView(R.id.radioBtMusic)
	RadioButton radioBtMusic;
	@BindView(R.id.radioUsbMusic)
	RadioButton radioUsbMusic;
	@BindView(R.id.radioMyMusic)
	RadioButton radioMyMusic;

	@BindView(R.id.btnClear)
	Button btnClear;
	//************************************************
	//
	//************************************************
	@Override
	public void onCheckedChanged(RadioGroup radioGroup, int id) {
		Log.d(TAG, "|||||||||| radioGroup = " + radioGroup + " id = " + id);
		switch (id){
			case R.id.radioBtMusic:
				Log.d(TAG,"id = BtMusic");
//				mFragment.getParent().switchMusicFragment(FragmentFactory.FRAGMENT_BT_MUSIC_PLAYING, null);
				break;

			case R.id.radioUsbMusic:
				Log.d(TAG,"id = UsbMusic && checked = " + radioUsbMusic.isChecked());
//                mFragment.getParent().switchMusicFragment(FragmentFactory.FRAGMENT_USB_MUSIC_PLAYING, null);
				break;

			case R.id.radioMyMusic:
				Log.d(TAG,"id = MyMusic && checked = " + radioMyMusic.isChecked());
//				mFragment.getParent().switchMusicFragment(FragmentFactory.FRAGMENT_MY_MUSIC_PLAYING, null);
				break;

		}
	}



}

package com.sochin.code.activity;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application{

	private static final String TAG = "MyApplication";

	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate() >>>>>");
		super.onCreate();
	}

}
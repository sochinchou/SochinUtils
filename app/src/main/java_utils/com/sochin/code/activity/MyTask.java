package com.sochin.code.activity;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

public class MyTask extends AsyncTask<Void, Void, Void> {

	private static final String TAG = "MyTask";

	@Override
	protected void onPreExecute() {
		Log.d(TAG, "onPreExecute() >>>");
		super.onPreExecute();
	}
	
	@Override
	protected Void doInBackground(Void... params) {
		Log.d(TAG, "doInBackground() >>>");
		return null;
	}

	@Override
	protected void onProgressUpdate(Void... values) {
		Log.d(TAG, "onProgressUpdate() >>>");
		super.onProgressUpdate(values);
	}
	
	@Override
	protected void onPostExecute(Void result) {
		Log.d(TAG, "onPostExecute() >>>");
		super.onPostExecute(result);
	}

	@Override
	protected void onCancelled() {	
		Log.d(TAG, "onCancelled() >>>");
		super.onCancelled();
	}
}
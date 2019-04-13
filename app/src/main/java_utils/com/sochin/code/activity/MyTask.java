package com.sochin.code.activity;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

public class MyTask extends AsyncTask<String, Integer, Double> {

	private static final String TAG = "MyTask";

	@Override
	protected void onPreExecute() {
		Log.d(TAG, "onPreExecute() >>>");
		super.onPreExecute();
	}
	
	@Override
	protected Double doInBackground(String... params) {
		Log.d(TAG, "doInBackground() >>>");
		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		Log.d(TAG, "onProgressUpdate() >>>");
		super.onProgressUpdate(values);
	}
	
	@Override
	protected void onPostExecute(Double result) {
		Log.d(TAG, "onPostExecute() >>>");
		super.onPostExecute(result);
	}

	@Override
	protected void onCancelled() {	
		Log.d(TAG, "onCancelled() >>>");
		super.onCancelled();
	}
}
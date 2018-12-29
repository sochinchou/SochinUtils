package com.sochin.code.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyBroadcastReceiver extends BroadcastReceiver {

	private static final String TAG = "MyBroadcastReceiver";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "onReceive() >>>>> action = " + intent.getAction());
		String action = intent.getAction();
		
		if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
			Intent myIntent = new Intent();
			myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// context.startService(new Intent(context, MyService.class));
//			context.startActivity(new Intent(context, MyActivity.class));
		}
	}
}
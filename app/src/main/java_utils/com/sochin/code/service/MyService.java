package com.sochin.code.service;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

public class MyService extends Service{

	private static final String TAG = "MyService";
	private Context mContext;
	
	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate () >>>>>");
		super.onCreate();
		mContext = this;
				
		// broadcast receiver
		IntentFilter filter = new IntentFilter();
		filter.addAction(ACTION1);
		registerReceiver(mBroadcastReceiver, filter);
		
		// bind service
		Intent intent = new Intent(this, MyService.class);
		bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand() >>>>>");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy () >>>>>");
		super.onDestroy();
		unbindService(mServiceConnection);
		unregisterReceiver(mBroadcastReceiver);
	}

	@Override
	public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind() >>>>>");
		return mBinder;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Log.d(TAG, "onUnbind() >>>>>");
		return super.onUnbind(intent);
	}
	
	// ************************************************************
	// Methods
	// ************************************************************
	
	
	
	
	// ************************************************************
	// Binder
	// ************************************************************
	private final IBinder mBinder = new MyBinder();
	
	public class MyBinder extends Binder{
		public MyService getService(){
			return MyService.this;
		}
	}
	
	// ************************************************************
	// ServiceConnection
	// ************************************************************
	private ServiceConnection mServiceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceConnected(ComponentName arg0, IBinder binder) {
			Log.d(TAG, "onServiceConnected() >>>");
			
		}
		
		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			Log.d(TAG, "onServiceDisconnected() >>>");
			
		}
		
	};
	
	// ************************************************************
	// BroadcastReceiver
	// ************************************************************
	private static final String ACTION1 = "action1";
	
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive() >>> action = " + intent.getAction());
            String action = intent.getAction();
            
            if(action.equals(ACTION1)){
            	
            }
        }
    };
    
    
	// ************************************************************
	// Handler
	// ************************************************************
	private static final int MSG_HANDLER1 = 100;
	private static final int MSG_HANDLER2 = 101;
	
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO
			switch (msg.what) {
			case MSG_HANDLER1:
				break;

			case MSG_HANDLER2:
				break;
			}
		}
	};

}

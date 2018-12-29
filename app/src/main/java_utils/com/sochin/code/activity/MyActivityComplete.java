package com.sochin.code.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;


import com.sochin.R;
import com.sochin.code.service.MyService;


public class MyActivityComplete extends AppCompatActivity implements OnClickListener {

    private static final String TAG = "MyActivityComplete";

    private Context mContext;

    private SharedPreferences mSharedPreferences;
    private Editor mEditor;
    private ProgressDialog mProgressDialog;

    private MyService mService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() >>>>>");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        init();
        initLayout();
        // TODO
    }

    private void init() {
        Log.d(TAG, "init() >>>>>");

        mContext = this;
        mSharedPreferences = getSharedPreferences("pref_name", 0);
        mEditor = mSharedPreferences.edit();
        mProgressDialog = new ProgressDialog(mContext);
        // TODO

        // broadcast receiver
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION1);
        registerReceiver(mBroadcastReceiver, filter);

        // bind service
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    // ************************************************************
    // Layout
    // ************************************************************
    // private TextView txt1;
    // private EditText edt1;
    // private Button btn1;
    // private ImageView img1;
    // private ImageButton imgBtn1;
    // private ListView listView1;
    // private LinearLayout ll1;
    // private RelativeLayout rl1
    // private FrameLayout fl1;

    private void initLayout() {
        Log.d(TAG, "initLayout() >>>>>");

        // txt1 = (TextView)findViewById(R.id.txt1);
        // edt1 = (EditText)findViewById(R.id.edt1);
        // btn1 = (Button)findViewById(R.id.btn1);
        // img1 = (ImageView)findViewById(R.id.img1);
        // imgBtn1 = (ImageButton)findViewById(R.id.imgBtn1);
        // listView1 = (ListView)findViewById(R.id.list1);
        // ll1 = (LinearLayout)findViewById(R.id.ll1);
        // rl1 = (RelativeLayout)findViewById(R.id.rl1);
        // fl1 = (FrameLayout)findViewById(R.id.fl1);
        // TODO
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume() >>>>>");
        super.onResume();
        // TODO
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause() >>>>>");
        super.onPause();
        // TODO
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy() >>>>>");
        super.onDestroy();
        // TODO
        unbindService(mServiceConnection);
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed() >>>>>");
        super.onBackPressed();
        // TODO
    }


    // ************************************************************
    // Methods
    // ************************************************************


    // ************************************************************
    // OnClickListener
    // ************************************************************
    @Override
    public void onClick(View v) {
        // TODO
        /*
		switch (v.getId()) {
		case R.id.btn1:
			break;
			
		case R.id.btn2:
			break;
			
		case R.id.btn3:
			break;
		}
	*/
    }


    // ************************************************************
    // AsyncTask
    // ************************************************************
    private class MyTask extends AsyncTask<Void, Void, Void> {

        private static final String TAG = "MyTask";

        @Override
        protected void onPreExecute() {
            Log.d(TAG, "onPreExecute() >>>");
            super.onPreExecute();
            mProgressDialog.setMessage(getString(android.R.string.ok));
            mProgressDialog.show();
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
            if (mProgressDialog.isShowing())
                mProgressDialog.dismiss();
        }

        @Override
        protected void onCancelled() {
            Log.d(TAG, "onCancelled() >>>");
            super.onCancelled();
        }
    }

    // ************************************************************
    // ServiceConnection
    // ************************************************************
    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName arg0, IBinder binder) {
            Log.d(TAG, "onServiceConnected() >>>");
            mService = ((MyService.MyBinder) binder).getService();

        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d(TAG, "onServiceDisconnected() >>>");
            mService = null;
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

            if (action.equals(ACTION1)) {

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

            switch (msg.what) {
                case MSG_HANDLER1:
                    break;

                case MSG_HANDLER2:
                    break;
            }
        }
    };
}
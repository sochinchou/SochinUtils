package com.sochin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.sochin.test.fragment.ActivityTestFragment;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "Second";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() >>>>>");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION2);
        registerReceiver(mBroadcastReceiver, filter);
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
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, "onBackPressed() >>>>>");
        super.onBackPressed();
        // TODO
    }

    // ************************************************************
    // BroadcastReceiver
    // ************************************************************
    private static final String ACTION1 = "action1";
    private static final String ACTION2 = "action2";

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive() >>> action = " + intent.getAction());
            String action = intent.getAction();

            if (action.equals(ACTION2)) {

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
    public void onBtn0(View view){
//        Intent intent = new Intent(this, ActivityTestFragment.class);
//        startActivity(intent);
        Intent intent = new Intent(ACTION1);
        sendBroadcast(intent);
    }

    public void onBtn1(View view){

    }

    public void onBtn2(View view){

    }

    public void onBtn3(View view){

    }

    public void onBtn4(View view){

    }

    public void onBtn5(View view){
    }

    public void onBtn6(View view){
    }

    public void onBtn7(View view){

    }

    public void onBtn8(View view){

    }

    public void onBtn9(View view){

    }
}

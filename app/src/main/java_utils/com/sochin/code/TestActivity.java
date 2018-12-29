package com.sochin.code;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import com.sochin.R;



public class TestActivity extends AppCompatActivity{

    private static final String TAG = "ActivityTestFragment";
    private static final String PREFIX = "|||||||||| ";

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private LinearLayout linearLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, PREFIX + "onCreate() >>>>>");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION1);
        registerReceiver(mBroadcastReceiver, filter);

    }

    @Override
    protected void onStart() {
        Log.d(TAG, PREFIX + "onStart() >>>>>");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, PREFIX + "onResume() >>>>>");
        super.onResume();
        // TODO
    }

    @Override
    protected void onPause() {
        Log.d(TAG, PREFIX + "onPause() >>>>>");
        super.onPause();
        // TODO
    }

    @Override
    protected void onStop() {
        Log.d(TAG, PREFIX + "onStop() >>>>>");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, PREFIX + "onDestroy() >>>>>");
        super.onDestroy();
        // TODO
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG, PREFIX + "onBackPressed() >>>>>");
        super.onBackPressed();
        // TODO
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, PREFIX + "onSaveInstanceState() >>>>>");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d(TAG, PREFIX + "onRestoreInstanceState() >>>>>");
        super.onRestoreInstanceState(savedInstanceState);
    }


    // ************************************************************
    // BroadcastReceiver
    // ************************************************************
    private static final String ACTION1 = "action1";



    public void onBtn0(View view){

    }

    public void onBtn1(View view){

    }

    public void onBtn2(View view){

    }

    public void onBtn3(View view){

    }

    public void onBtn4(View view){

    }

    //********************************
    //********************************

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
                    Log.d(TAG, "========== handleMessage(1) >>>");

                    break;

                case MSG_HANDLER2:
                    Log.d(TAG, "========== handleMessage(2) >>>");

                    break;
            }
        }
    };

}

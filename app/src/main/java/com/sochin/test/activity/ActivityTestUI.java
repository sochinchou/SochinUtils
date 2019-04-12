package com.sochin.test.activity;

import android.app.Dialog;
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
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.sochin.R;
import com.sochin.code.dialog.CustomDialog;
import com.sochin.code.dialog.MyDialogSimple;
import com.sochin.code.recyclerview.ItemInfo;
import com.sochin.code.recyclerview.MyBaseAdapter;
import com.sochin.code.recyclerview.MyBaseAdapterSimple;
import com.sochin.code.utils.FileUtils;
import com.sochin.test.fragment.FragmentDialog;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/1/10.
 */

public class ActivityTestUI extends AppCompatActivity {
    private static final String TAG = "ActivityTestUI";
    private static final String PREFIX = "|||||||||| ";

    private static final String SD = "/storage/sdcard";
    private static final String SD_ = "/storage/sdcard/";
    private static final String MUSIC = "/storage/sdcard/Music";
    private static final String MUSIC_ = "/storage/sdcard/Music/new/";

    @BindView(R.id.list1)
    ListView listView;
    @BindView(R.id.txt1)
    TextView txt1;
    MyBaseAdapterSimple mAdapter;

    private boolean isScrolled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, PREFIX + "onCreate() >>>>>");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ui);

        ButterKnife.bind(this);

        ArrayList<ItemInfo> data = new ArrayList<>();
        for(int i = 0; i < 100; i ++){
            data.add(new ItemInfo("AAAAAAAAAAAName " + String.valueOf(i),i, null));
        }
        mAdapter = new MyBaseAdapterSimple(this, data);
        listView.setAdapter(mAdapter);


        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d(TAG, PREFIX + "onScrollStateChanged() >>> scrollState = " + scrollState);

                if(scrollState == 0){
                    selectionAdjust();
                    isScrolled = false;
                }else if(scrollState == 1){
                    isScrolled = true;
                }else if(scrollState == 2){
                    isScrolled = true;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.d(TAG, PREFIX + "onScroll() >>> firstVisibleItem = " + firstVisibleItem
                + ", visibleItemCount = " + visibleItemCount + ", totalItemCount = " + totalItemCount);

                mLastScrollY = getTopItemScrollY(view);
                mPreviousFirstVisibleItem = firstVisibleItem;

                Log.d(TAG, "|||||||||||||| scrollY = " + view.getScrollY());
                View child = view.getChildAt(0);
                if(child != null){
                    Log.d(TAG, "|||||||||||||| top = " + child.getTop() + " scrollY = " + child.getScrollY());
                }
            }
        });



        listView.setFastScrollEnabled(true);
//
//        txt1.setText("AAAAAAAAAAAAAAAAAAAAA");
//        IntentFilter filter = new IntentFilter();
////        filter.addAction(ACTION1);
//        registerReceiver(mBroadcastReceiver, filter);

    }

    private int mLastScrollY;
    private int mPreviousFirstVisibleItem;
    private int mListItemHeight;

    private void selectionAdjust() {
        int position = mPreviousFirstVisibleItem;
        if (Math.abs(mLastScrollY) > mListItemHeight / 2) {
            position += 1;
        }
        Log.d(TAG, "selectionAdjust() >>> mListItemHeight = " + mListItemHeight + " position = " + position);
//        listView.setSelection(position);

        final int pos = position;
        listView.post(new Runnable() {
            @Override
            public void run() {
                listView.smoothScrollToPosition(pos);
            }
        });

    }

    private int getTopItemScrollY(AbsListView listView) {
        View topChild = listView.getChildAt(0);
        if (topChild != null) {
            mListItemHeight = topChild.getHeight();
            return topChild.getTop();
        } else {
            return 0;
        }
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


    //********************************************
    public void onBtn0(View view){
//        new FragmentDialog().show(getSupportFragmentManager(), null);

        Dialog dialog = new MyDialogSimple(this);
        dialog.setCancelable(false);
        dialog.show();
    }

    public void onBtn1(View view){
        String path = MUSIC + "/zhoushaoqing.txt";
        Log.d(TAG, "name = " + FileUtils.getSizeString(1024 * 1024 * 100 + 1023, 3));
    }

    public void onBtn2(View view){
        String path = MUSIC + "/zhoushaoqing.txt";
        Log.d(TAG, "name = " + FileUtils.getSizeString(1024L * 1024 * 1024 * 3 + 4536, 3));
    }


    //********************************************
    public void onBtn3(View view){

    }

    public void onBtn4(View view){

    }


    public void onBtn5(View view){

    }


    //********************************************
    public void onBtn6(View view){

    }

    public void onBtn7(View view){

    }

    public void onBtn8(View view){

    }


    //********************************************
    public void onBtn9(View view){

    }

    public void onBtn10(View view){

    }

    public void onBtn11(View view){

    }





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
                    Log.d(TAG, "========== handleMessage(1) >>>");

                    break;

                case MSG_HANDLER2:
                    Log.d(TAG, "========== handleMessage(2) >>>");

                    break;
            }
        }
    };



}

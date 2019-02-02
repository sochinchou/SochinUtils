package com.sochin.test.fragment;


import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.sochin.R;
import com.sochin.SecondActivity;
import com.sochin.code.fragment.MyBaseFragment;

import java.lang.ref.WeakReference;
import java.util.List;

public class ActivityTestFragment extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener{

    private static final String TAG = "ActivityTestFragment";
    private static final String PREFIX = "|||||||||| ";

    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    private LinearLayout linearLayout;
    private MyBaseFragment fragmentRed, fragmentGreen,fragmentBlue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, PREFIX + "onCreate() >>>>>");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        fragmentRed = new FragmentRed();
        fragmentGreen = new FragmentGreen();
        fragmentBlue = new FragmentBlue();

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.addOnBackStackChangedListener(this);

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
    protected void onNewIntent(Intent intent) {
        Log.d(TAG, PREFIX + "onNewIntent() >>>>>");
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void onPostResume() {
        Log.d(TAG, PREFIX + "onPostResume() >>>>>");
        super.onPostResume();
        setIntent(null);
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

    @Override
    public void onBackStackChanged() {
//        Log.d(TAG, "onBackStackChanged() >>>>>");
    }

    private int index = 1;
    public void initFragment(){
        Log.d(TAG, "---------- initFragment() >>> ");
//        if(isStateSaved){
            if(index == 4){
                index = 1;
            }
            replaceLeftFragment(index ++);
//        }
    }

    public void replaceLeftFragment(int index){
        MyBaseFragment fragment = FragmentFactory.getFragmentbyIndex(index, null);
        if(fragment != null){
            String tag = fragment.getFragmentTag();
            int result = mFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, fragment, tag)
//                    .addToBackStack(tag)
                    .commit();
//                    .commitAllowingStateLoss();
            Log.d(TAG, "replaceLeftFragment(int) >>> result = " + result);

        }
    }

    public void replaceLeftFragment(MyBaseFragment fragment){

        if(fragment != null){
            String tag = fragment.getFragmentTag();
            int result = mFragmentManager.beginTransaction()
                    .setCustomAnimations(R.anim.anim_right_in, 0, R.anim.anim_right_in, 0)
                    .replace(R.id.frameLayout, fragment, tag)
//                    .addToBackStack(tag)
//                    .commit();
            .commitAllowingStateLoss();
            Log.d(TAG, "replaceLeftFragment(MyBaseFragment) >>> result = " + result);
        }
    }

    public void skyPopBackStack(){
        Log.d(TAG, "skyPopBackStack() >>>");
        mFragmentManager.popBackStack();
    }

    public void skyPopBackStack(int id){
        mFragmentManager.popBackStackImmediate(id, 0);
//        mFragmentManager.popBackStackImmediate(id, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void skyPopBackStack(String tag){
        mFragmentManager.popBackStackImmediate(tag, 0);
//        mFragmentManager.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    public void skyPopBackStackStep(String tag){
        int count = 0;
        while((count = mFragmentManager.getBackStackEntryCount()) > 0){
            String strTag = mFragmentManager.getBackStackEntryAt(count -1).getName();
            if(!strTag.equals(tag)) {
                mFragmentManager.popBackStackImmediate();
            }else{
                return;
            }
        }
    }

    public void skyPopBackStackToRoot(){
        while (mFragmentManager.getBackStackEntryCount() > 0){
            mFragmentManager.popBackStack();
        }
    }

    public void addFragmentLeft(int index){
        MyBaseFragment fragment = FragmentFactory.getFragmentbyIndex(index, null);
        if(fragment != null){
            String tag = fragment.getFragmentTag();
            int result = mFragmentManager.beginTransaction()
                    .add(R.id.frameLayout, fragment, tag)
                    .addToBackStack(tag)
                    .commit();
            Log.d(TAG, "addFragmentLeft() >>> result = " + result);
        }
    }

    public void addFragmentLeft(MyBaseFragment fragment){
        if(fragment != null){
            String tag = fragment.getFragmentTag();
            int result = mFragmentManager.beginTransaction()
                    .add(R.id.frameLayout, fragment, tag)
                    .addToBackStack(tag)
                    .commit();
            Log.d(TAG, "addFragmentRight(MyBaseFragment) >>> result = " + result);
        }
    }

    public Fragment findFragmentById(int id){

        Fragment fragment = mFragmentManager.findFragmentById(id);
        Log.d(TAG, "findById() >>> fragment = " + fragment);
        return fragment;
    }

    public Fragment findFragmentByTag(String tag){

        Fragment fragment = mFragmentManager.findFragmentByTag(tag);
        Log.d(TAG, "findByTag() >>> fragment = " + fragment);
        return fragment;
    }

    public FragmentManager.BackStackEntry getBackStackEntry(){
        int count = mFragmentManager.getBackStackEntryCount();
        Log.d(TAG, "getBackStackEntry() >>> count = " + count);
        if(count > 0){
            FragmentManager.BackStackEntry entry = mFragmentManager.getBackStackEntryAt(count - 1);
            Log.d(TAG, "getBackStackEntry() >>> entry id  = " + entry.getId() + ", entry name = " + entry.getName());
            return entry;
        }
        return null;
    }

    public void printFragments(){
        List<Fragment> list = mFragmentManager.getFragments();
        for(Fragment fragment : list){
            Log.d(TAG, "printFragments() >>> fragment = " + fragment);
        }
    }

    public void printFragment(MyBaseFragment fragment){
        Log.d(TAG,  "fragment = " + fragment.getInfo() );
    }

    // ************************************************************
    // BroadcastReceiver
    // ************************************************************
    private static final String ACTION1 = "action1";
    private static final String ACTION2 = "action2";



    public void onBtn0(View view){
//        replaceLeftFragment(fragmentRed);
        replaceLeftFragment(fragmentRed);
    }

    public void onBtn1(View view){
//        replaceLeftFragment(fragmentGreen);
        replaceLeftFragment(fragmentGreen);
    }

    public void onBtn2(View view){
        replaceLeftFragment(fragmentBlue);
    }

    public void onBtn3(View view){
//        findFragmentById(R.id.frameLayout);
        printFragment(fragmentRed);
//        skyPopBackStack("red");
    }

    public void onBtn4(View view){
        skyPopBackStack();
//        printFragments();
//        skyPopBackStackStep("red");
    }

    //********************************
    //********************************


    public void onBtn5(View view){
//        findFragmentByTag("red");
//        startActivity(new Intent(this, SecondActivity.class));
//        mHandler.sendEmptyMessageDelayed(MSG_HANDLER1, 2000);

        mFragmentManager.beginTransaction().detach(fragmentRed).commit();

//        Intent intent = new Intent(this, ActivityTestFragment.class);
//        startActivity(intent);

    }

    public void onBtn6(View view){
//        findFragmentByTag("green");
//        startActivity(new Intent(this, SecondActivity.class));
//        mHandler.sendEmptyMessageDelayed(MSG_HANDLER2, 2000);
//        MyBaseFragment fragment = (MyBaseFragment)findFragmentByTag("red");
        mFragmentManager.beginTransaction().attach(fragmentRed).commit();

    }

    public void onBtn7(View view){
//        findFragmentByTag("blue");
//        startActivity(new Intent(this, SecondActivity.class));
//        MyBaseFragment fragment = (MyBaseFragment)findFragmentByTag("red");
//        mFragmentManager.beginTransaction().detach(fragment).commit();
        mFragmentManager.beginTransaction().hide(fragmentRed).commit();


    }

    public void onBtn8(View view){
//        MyBaseFragment fragment = (MyBaseFragment)findFragmentByTag("red");
//        mFragmentManager.beginTransaction().show(fragment).commit();
//        replaceLeftFragment(fragmentGreen);
        mFragmentManager.beginTransaction().show(fragmentRed).commit();

    }

    public void onBtn9(View view){
//        getBackStackEntry();
//        MyBaseFragment fragment = (MyBaseFragment)findFragmentByTag("red");
//        mFragmentManager.beginTransaction().hide(fragment).commit();
        startActivity(new Intent(this, SecondActivity.class));
//        mHandler.sendEmptyMessageDelayed(MSG_HANDLER2, 2000);
//        ObjectAnimator.ofFloat(linearLayout,"TranslationX", 0, 400).start();
    }


    public void startHomeWithFragment() {
        Log.d(TAG, "startHomeWithFragment() >>>");
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.setClassName("com.sochin", "com.sochin.ActivityTestFragment");
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.d(TAG, "startHomeWithFragment() >>> [ActivityNotFoundException]");
        }
    }


    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "onReceive() >>> action = " + intent.getAction());
            String action = intent.getAction();

            if (action.equals(ACTION1)) {
//                startHomeWithFragment();
                replaceLeftFragment(fragmentGreen);
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
//                    skyPopBackStack();
//                    fragmentRed.setArguments(new Bundle());
                    printFragment(fragmentRed);
//                    replaceLeftFragment(fragmentGreen);
                    break;

                case MSG_HANDLER2:
                    Log.d(TAG, "========== handleMessage(2) >>>");
//                    replaceLeftFragment(2);
                    fragmentRed.setArguments(new Bundle());
                    break;
            }
        }
    };

}

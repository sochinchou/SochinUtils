package com.sochin.code.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class MyBaseFragment extends Fragment {

    protected String TAG = getClass().getSimpleName();

    protected View mRootView;
    protected IFragmentActivity mActivity;
    protected Context mContext;


    public abstract void initViews();
    public abstract void initBeforeView();
    public abstract void destroyViews();



    @Override
    public void onAttach(Context context) {
        Log.d(TAG, "onAttach() >>>");
        super.onAttach(context);
        mContext = context;
        if(context instanceof IFragmentActivity){
            mActivity = (IFragmentActivity)context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate() >>>");
        super.onCreate(savedInstanceState);
        initBeforeView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	Log.d(TAG, "onCreateView() >>>");
        mRootView = inflater.inflate(getContentLayout(), container, false);
        initViews();
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated() >>>");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated() >>>");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
//      Log.d(TAG, "onStart() >>>");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(TAG, "onResume() >>>");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause() >>>");
        super.onPause();
    }

    @Override
    public void onStop() {
//      Log.d(TAG, "onStop() >>>");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
      Log.d(TAG, "onDestroyView() >>>");
        destroyViews();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() >>>");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(TAG, "onDetach() >>>");
        super.onDetach();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.d(TAG, "setUserVisibleHint() >>> isVisibleToUser = " + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        Log.d(TAG, "onHiddenChanged() >>> hidden = " + hidden);
        super.onHiddenChanged(hidden);

    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    protected abstract int getContentLayout();

    public String getFragmentTag(){
        return null;
    }

    public String getInfo(){
        return super.toString()
                + " ||| host = " + getHost()
                + " isStateSaved = " + isStateSaved()
                + " isAdded = " + isAdded()
                + " isDetach = " + isDetached()
                + "\n isHidden = " + isHidden()
                + " isVisible = " + isVisible()
                + " isResumed = " + isResumed()
                + " id = " + getId()
                + " tag = " + getTag();

    }
}
package com.sochin.code.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

/**
 * Created by Administrator on 2018/12/11.
 */

public class MyDialogSimple extends Dialog {
    private static final String TAG = "MyDialogSimple";

    public MyDialogSimple(@NonNull Context context) {
        super(context);
    }

    public MyDialogSimple(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "show() >>>");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "show() >>>");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "show() >>>");
        super.onStop();
    }

    @Override
    public void show() {
        Log.d(TAG, "show() >>>");
        super.show();
    }

    @Override
    public void dismiss() {
        Log.d(TAG, "show() >>>");
        super.dismiss();
    }
}

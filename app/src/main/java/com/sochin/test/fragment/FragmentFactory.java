package com.sochin.test.fragment;

import android.os.Bundle;
import android.util.SparseArray;

import com.sochin.code.fragment.MyBaseFragment;

/**
 * Created by SDT14077 on 2018/1/15.
 */

public class FragmentFactory {

    public static final int FRAGMENT_RED = 1;
    public static final int FRAGMENT_GREEN = 2;
    public static final int FRAGMENT_BLUE = 3;


    private static SparseArray<MyBaseFragment> fragmentMap = new SparseArray<MyBaseFragment>();

    public static MyBaseFragment getFragmentbyIndex(int index, Bundle bundle) {
        MyBaseFragment instance = null;

        instance = fragmentMap.get(index);
        if (instance != null) {
            instance.setArguments(bundle);
            return instance;
        }

        //内存中没有，则重新创建
        switch (index) {

            case FRAGMENT_RED:
                instance = new FragmentRed();
                break;

            case FRAGMENT_GREEN:
                instance = new FragmentGreen();
                break;

            case FRAGMENT_BLUE:
                instance = new FragmentBlue();
                break;
            
            default:
                break;
        }
        // 保存到内存中
        if (instance != null) {
            instance.setArguments(bundle);
            fragmentMap.put(index, instance);
        }
        return instance;
    }


}

package com.sochin.code.recyclerview;

import android.graphics.drawable.Drawable;

/**
 * Created by Administrator on 2019/1/29.
 */

public class ItemInfo {
    public String mName="";
    public int mValue;
    public Drawable mIcon=null;

    public ItemInfo(String name, int value, Drawable drawable){
        this.mName = name;
        this.mValue = value;
        this.mIcon = drawable;
    }

    @Override
    public boolean equals(Object o) {
        ItemInfo info = (ItemInfo)o;
        return mValue == info.mValue;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public boolean isChecked = false;
    public boolean isChecked(){
        return isChecked;
    }

    public void setChecked(boolean isChecked){
        this.isChecked = isChecked;
    }
}

package com.sochin.code.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * Created on 2018/10/13.
 */

public class MarqueeTextView extends AppCompatTextView {

    private String mLastText;

    public MarqueeTextView(Context context) {
        super(context);
        initMarquee();
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMarquee();
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMarquee();
    }

    private void initMarquee() {
        setSingleLine(true);
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        setMarqueeRepeatLimit(-1);  //marquee_forever
    }

    public boolean isFocused() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {

    }

    @Override
    public void setText(CharSequence text, BufferType type) {

        if (mLastText == null) {
            mLastText = text.toString();
            super.setText(text, type);
            return;
        }

        if (mLastText.equals(text.toString())) {
            return;
        }

        mLastText = text.toString();

        super.setText(text, type);
    }
}

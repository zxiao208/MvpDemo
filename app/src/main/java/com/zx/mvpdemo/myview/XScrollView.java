package com.zx.mvpdemo.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class XScrollView extends ScrollView {
    OnScrollToBottomListener mOnScrollToBottomListener=null;
    public XScrollView(Context context) {
        super(context);
    }

    public XScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
        if (scrollY != 0 && null != mOnScrollToBottomListener) {
            mOnScrollToBottomListener.onScrollBottomListener(clampedY);
        }
    }

    public void setOnScrollToBottomLintener(OnScrollToBottomListener onScrollToBottomLintener){
        this.mOnScrollToBottomListener=onScrollToBottomLintener;
    }

    public interface OnScrollToBottomListener{
        void  onScrollBottomListener(boolean clampedY);
    }
}

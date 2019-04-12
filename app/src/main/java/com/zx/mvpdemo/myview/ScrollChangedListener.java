package com.zx.mvpdemo.myview;

public interface ScrollChangedListener {
    //mCurrentViewIndex 表示当前是上半部分还是下半部分
    void onScrollChanged(int mCurrentViewIndex);
}

package com.zx.mvpdemo.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zx.mvpdemo.R;
import com.zx.mvpdemo.myview.ScrollChangedListener;
import com.zx.mvpdemo.myview.ScrollViewContainer;
import com.zx.mvpdemo.myview.XScrollView;

public class SlideMore2Activity extends Activity implements ScrollChangedListener {
    //上拉组件
    ScrollViewContainer mScrollView;
    //上拉部分
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mscrollview);
        mScrollView = (ScrollViewContainer) findViewById(R.id.scrollview);
        mScrollView.setScrollChangedListener(this);
    }


    @Override
    public void onScrollChanged(int mCurrentViewIndex) {
        if(mCurrentViewIndex == 0){
            //TODO 修改textview的文字及图片方向
//            ((TextView)mGoToPicView.getChildAt(1)).setText("上拉查看图文详情");
        }else {
//            ((TextView)mGoToPicView.getChildAt(1)).setText("下滑查看商品详情");
        }

    }
}

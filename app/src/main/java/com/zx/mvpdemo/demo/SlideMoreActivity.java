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

import com.zx.mvpdemo.R;
import com.zx.mvpdemo.myview.XScrollView;

public class SlideMoreActivity extends Activity {
    //上拉组件
     static final String TAG="SlideMoreActivity";
    XScrollView mXscrollview;
    LinearLayout scrollContainer;
    ScrollView svBottomDetails;
    LinearLayout llDownScroll;
    RelativeLayout scrollContainer2;
    //上拉部分
    private Rect rect = null;//记录scrollView包裹组件的位置
    private int fullScroll;//scrollView滚动到底部时ScrollView的scrollY值
    private float mDeltaY;//（上拉模块）拖动的距离
    private int downScrollY;//开始拖动的ScrollView的scrollY值
    private float startY;//开始拖动的MotionEvent的y值

    //下拉部分
    private float startY2;
    private float downScrollY2;
    private float mDeltaY2;
    private boolean touched;//是否触摸，在ACTION_MOVE中做标记，记录按下时需要的值
    private RelativeLayout.LayoutParams mLayoutParams;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slidemore);
        mXscrollview=findViewById(R.id.xscrollview);
        scrollContainer=findViewById(R.id.scrollContainer);
        scrollContainer2=findViewById(R.id.scrollContainer2);
        svBottomDetails=findViewById(R.id.svBottomDetails);
        llDownScroll=findViewById(R.id.llDownScroll);
        initPullUp();
        initPullDown();


    }

    /*******************监听详情的隐藏和显示*******************/

    private OnGoodsDetailsListener mOnGoodsDetailsListener;

    public void setOnGoodsDetailsListener(OnGoodsDetailsListener onGoodsDetailsListener) {
        mOnGoodsDetailsListener = onGoodsDetailsListener;
    }

    public interface OnGoodsDetailsListener{
        void onShow(boolean showDetails);
    }

    //下拉隐藏详情
    private void initPullDown() {
        mLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        svBottomDetails.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        //可能的冲突，改为在ACTION_MOVE中获取
                        break;

                    case MotionEvent.ACTION_UP:

                        //向下拖动距离大于100
                        if (mDeltaY2 > 120 && downScrollY2 == 0) {
                            Log.d(TAG, "onTouch: 隐藏详情了");

                            //显示上部
                            mXscrollview.setVisibility(View.VISIBLE);
                            mXscrollview.smoothScrollTo(0, fullScroll);//滚动到底部
                            //隐藏详情
                            svBottomDetails.setVisibility(View.GONE);

                            //还原标题-->商品 详情 评价
                            //修改返回按键和小箭头的事件->点击结束act
                            //将状态传到activity，改变标题
//                            mOnGoodsDetailsListener.onShow(false);
                        }

//                      //恢复原有marginTop高度，隐藏头
                        mLayoutParams.setMargins(0, (int) getResources().getDimension(R.dimen.pulldown_head_margin), 0, 0);
                        llDownScroll.setLayoutParams(mLayoutParams);

                        //重置
                        mDeltaY2 = 0;
                        downScrollY2 = 0;
                        startY2 = 0;
                        touched = false;

                        break;
                    case MotionEvent.ACTION_MOVE:
                        //从顶部开始的滑动，且向下滑

                        //在此记录按下位置，取代ACTION_DOWN中
                        if (!touched) {
                            startY2 = event.getY();
                            downScrollY2 = svBottomDetails.getScrollY();
                            Log.d(TAG, "onTouch: startY2 = " + startY2 + " , downScrollY2 = " + downScrollY2);
                        }
                        touched = true;
                        mDeltaY2 = 0.5f * (event.getY() - startY2);
                        Log.d(TAG, "onTouch: downScrollY2 = " + downScrollY2);
                        Log.d(TAG, "onTouch: startY2 = " + startY2);
                        Log.d(TAG, "onTouch: mDeltaY2 = " + mDeltaY2);

                        if (downScrollY2 == 0 && mDeltaY2 > 0) {
                            //计算marginTop高度，动态显示头高度
                            int top = (int) (-120 + mDeltaY2);
                            Log.d(TAG, "onTouch: marginTop = " + top);
                            mLayoutParams.setMargins(0, top, 0, 0);
                            llDownScroll.setLayoutParams(mLayoutParams);
                        }

                        break;
                    default:
                        break;
                }
                return false;
            }
        });
    }


    /**
     * 上拉查看详情
     */
    @SuppressLint("ClickableViewAccessibility")
    private void initPullUp() {

        //这一步操作为，获取ScrollView的完全高度，在上拉时，判断是否从最底部开始
        mXscrollview.setOnScrollToBottomLintener(new XScrollView.OnScrollToBottomListener() {
            @Override
            public void onScrollBottomListener(boolean isBottom) {
                if (isBottom) {
                    fullScroll = mXscrollview.getScrollY();
                    Log.d(TAG, "onScrollBottomListener: scrollY = " + fullScroll);
                }
            }
        });

        mXscrollview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //记录按下时的Y值
                        startY = event.getY();
                        downScrollY = mXscrollview.getScrollY();
                        Log.d(TAG, "onTouch: startY = " + startY + " , downScrollY = " + downScrollY);
                        if (rect == null) {
                            rect = new Rect(scrollContainer.getLeft(), scrollContainer.getTop(), scrollContainer.getRight(), scrollContainer.getBottom());
                        }

                        break;

                    case MotionEvent.ACTION_UP:

                        //拖动距离大于120
                        if (Math.abs(mDeltaY) > 120) {
                            Log.d(TAG, "onTouch: 显示详情了");
                            //显示详情
                            svBottomDetails.setVisibility(View.VISIBLE);
                            svBottomDetails.smoothScrollTo(0, 0);
                            //隐藏上部
                            mXscrollview.setVisibility(View.GONE);

                            //改变标题-->图文详情
                            //修改返回按键和小箭头的事件->点击还原
                            //将状态传到activity，改变标题
//                            mOnGoodsDetailsListener.onShow(true);
                        }

                        // 恢复原有高度
                        if (rect != null) {
                            scrollContainer.layout(rect.left, rect.top, rect.right, rect.bottom);
                            Log.d(TAG, "onTouch: 松手了");
                        }
                        //重置
                        mDeltaY = 0;
                        downScrollY = 0;
                        startY = 0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //downScrollY != 0：不是从顶部开始的滑动；fullScroll不为0且不为负值(bug?)
                        if (downScrollY != 0 && fullScroll > 0 && downScrollY >= fullScroll - 20) {
                            //deltaY<0，向上滑动
                            mDeltaY = 0.5f * (event.getY() - startY);
                            Log.d(TAG, "onTouch: downScrollY = " + downScrollY);
                            Log.d(TAG, "onTouch: fullScroll = " + fullScroll);
                            Log.d(TAG, "onTouch: mDeltaY = " + mDeltaY);
                            if (rect != null) {
                                scrollContainer.layout(rect.left, (int) (rect.top + mDeltaY), rect.right, (int) (rect.bottom + mDeltaY));
                            }
                        }

                        break;
                    default:
                        break;
                }
                return false;
            }
        });

    }

}

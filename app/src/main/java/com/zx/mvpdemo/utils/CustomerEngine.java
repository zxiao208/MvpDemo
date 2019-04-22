package com.zx.mvpdemo.utils;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.WindowManager;

import com.zx.mvpdemo.demo.CustomDisplay;

public class CustomerEngine {
    // 获取设备上的屏幕
    private DisplayManager mDisplayManager;// 屏幕管理器
    private Display[] displays;// 屏幕数组
    private CustomDisplay mCustomerDisplay;   //（继承Presentation）

    private static CustomerEngine instance;

    /**
     * 单例模式，创建的时候把界面绑定到第二个屏幕中
     * @param context 这里需要传入getApplicationContext(),就能实现全局双屏异显
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static CustomerEngine getInstance(Context context){
        if(instance == null){
            instance = new CustomerEngine(context);
        }
        return instance;
    }

    public static void colose(){
        instance = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private CustomerEngine(Context context){
        mDisplayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        displays = mDisplayManager.getDisplays();
        if (null == mCustomerDisplay && displays.length > 1) {
            mCustomerDisplay =  new CustomDisplay(context, displays[1]);// displays[1]是副屏
            mCustomerDisplay.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            mCustomerDisplay.show();
        }
    }

    /**
     * 显示客户购买的商品集合
     */
    public void setProductList(String str){
        if(mCustomerDisplay != null){

        }
    }
}

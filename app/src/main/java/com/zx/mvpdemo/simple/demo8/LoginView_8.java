package com.zx.mvpdemo.simple.demo8;


import com.zx.mvpdemo.simple.demo8.base.BaseView_8;

/**
 * 作者: Dream on 2017/8/28 21:52
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//用于 V 层和 M 层交互的接口
public interface LoginView_8 extends BaseView_8 {
    void onLoginResult(String result);
}

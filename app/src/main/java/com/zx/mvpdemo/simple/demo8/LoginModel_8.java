package com.zx.mvpdemo.simple.demo8;


import com.zx.mvpdemo.utils.HttpTask;
import com.zx.mvpdemo.utils.HttpUtils;

/**
 * 作者: Dream on 2017/8/28 21:49
 * QQ:510278658
 * E-mail:510278658@qq.com
 */

//M层
public class LoginModel_8 {

    public void login(String username, String password,final HttpUtils.OnHttpResultListener onHttpResultListener){
        HttpTask httpTask = new HttpTask(new HttpUtils.OnHttpResultListener() {
            @Override
            public void onResult(String result) {
                //分层：数据库模块、网络请求模块、文件操作模块等等...
                onHttpResultListener.onResult(result);
            }
        });
        httpTask.execute("http://192.168.7.190:8080/SSM-Xml/test/login.do", username, password);
    }

}

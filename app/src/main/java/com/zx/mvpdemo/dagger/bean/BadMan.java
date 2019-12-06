package com.zx.mvpdemo.dagger.bean;

import android.app.Activity;
import android.widget.Toast;

import java.util.Map;

import javax.inject.Inject;

public class BadMan {
    @Inject
    public BadMan() {
    }
    public void hello(Activity activity){
        Toast.makeText(activity,"我是坏银",Toast.LENGTH_LONG).show();
    }
    public String run(){
        return "police调用我run方法";
    }
}

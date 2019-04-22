package com.zx.mvpdemo.demo;

import android.annotation.TargetApi;
import android.app.Presentation;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;

import com.zx.mvpdemo.R;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
public class CustomDisplay extends Presentation {
    public CustomDisplay(Context outerContext, Display display) {
        super(outerContext, display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seconddisplay);

    }
}

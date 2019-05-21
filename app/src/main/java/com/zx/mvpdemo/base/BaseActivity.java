package com.zx.mvpdemo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BaseActivity extends AppCompatActivity {
    protected Unbinder mBinder;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        mBinder=ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinder.unbind();
    }
}

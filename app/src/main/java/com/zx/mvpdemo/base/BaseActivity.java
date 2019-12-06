package com.zx.mvpdemo.base;

import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
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

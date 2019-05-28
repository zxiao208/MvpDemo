package com.zx.mvpdemo.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.zx.mvpdemo.R;
import com.zx.mvpdemo.base.BaseActivity;
import com.zx.mvpdemo.dagger.bean.Police;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Dagger2Demo extends BaseActivity {
    @Inject
    Police police;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);

    }
}

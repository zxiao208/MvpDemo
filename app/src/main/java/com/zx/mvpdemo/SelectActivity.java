package com.zx.mvpdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zx.mvpdemo.base.BaseActivity;
import com.zx.mvpdemo.demo.RetrofitDemo;
import com.zx.mvpdemo.demo.RxjavaDemoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SelectActivity extends BaseActivity {

    @BindView(R.id.btn_rxjava)
    Button btnRxjava;
    @BindView(R.id.btn_retrofit)
    Button btnRetrofit;
    @BindView(R.id.btn_main)
    Button btnMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        ButterKnife.bind(this);

    }
    @OnClick({R.id.btn_rxjava, R.id.btn_retrofit, R.id.btn_main})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_rxjava:
                Intent intent = new Intent(this, RxjavaDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_retrofit:
                Intent intent2 = new Intent(this, RetrofitDemo.class);
                startActivity(intent2);
                break;
            case R.id.btn_main:
                Intent intent3 = new Intent(this, MainActivity.class);
                startActivity(intent3);
                break;
        }
    }
}

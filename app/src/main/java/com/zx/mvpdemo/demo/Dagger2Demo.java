package com.zx.mvpdemo.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.zx.mvpdemo.R;
import com.zx.mvpdemo.base.BaseActivity;
import com.zx.mvpdemo.dagger.bean.BadMan;
import com.zx.mvpdemo.dagger.bean.Police;
import com.zx.mvpdemo.dagger.component.DaggerOkHttpComponent;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Dagger2Demo extends BaseActivity {
    private final String TAG = "Dagger2Demo";

    @Inject
    Police police;
    @Inject
    BadMan badMan;
    @Inject
    OkHttpClient okHttpClient;
    @BindView(R.id.dagger_tv1)
    TextView daggerTv1;
    @BindView(R.id.dagger_tv2)
    TextView daggerTv2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger2);
        ButterKnife.bind(this);
        DaggerOkHttpComponent.create().inject(this);
        Log.i(TAG, "onCreate: " + police.killing());
        badMan.hello(this);
        daggerTv1.setText(police.useBadMan());

        Request request = new Request.Builder().url("https://www.baidu.com/s?wd=世界上最帅的人").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        daggerTv2.setText("查无此人");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                if(response.body()!=null){
                                    daggerTv2.setText(response.body().string());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            }
        });
    }
}

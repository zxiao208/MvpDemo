package com.zx.mvpdemo.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zx.mvpdemo.R;
import com.zx.mvpdemo.base.BaseActivity;
import com.zx.mvpdemo.simple.retrofitutil.APIService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;

public class RetrofitDemo extends BaseActivity {

    @BindView(R.id.ac_retrofit_tvget)
    TextView acRetrofitTvget;
    @BindView(R.id.ac_retrofit_btnget)
    Button acRetrofitBtnget;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

    }

    @OnClick({R.id.ac_retrofit_tvget, R.id.ac_retrofit_btnget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_retrofit_tvget:
                break;
            case R.id.ac_retrofit_btnget:

                break;
        }
    }

    private void retrofitGet(){
        String BASE_URL = "http://qt.qq.com/php_cgi/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).build();
    }
}

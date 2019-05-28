package com.zx.mvpdemo.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.zx.mvpdemo.R;
import com.zx.mvpdemo.base.BaseActivity;
import com.zx.mvpdemo.simple.retrofitutil.APIService;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
        inithttplog();
    }

    private void inithttplog(){

    }

    @OnClick({R.id.ac_retrofit_tvget, R.id.ac_retrofit_btnget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ac_retrofit_tvget:

                break;
            case R.id.ac_retrofit_btnget:
                retrofitGet();
                break;
        }
    }

    private void retrofitGet(){
        /*
         **打印retrofit信息部分
         */
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                //打印retrofit日志
                Log.e("RetrofitLog","retrofitBack = "+message);
            }
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()//okhttp设置部分，此处还可再设置网络参数
                .addInterceptor(loggingInterceptor)
                .build();
        String BASE_URL = "http://qt.qq.com/php_cgi/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client).build();
        APIService apiService = retrofit.create(APIService.class);
        apiService.getNewsInfo("12", "0", "android", "9724")
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                String string = response.body().string();
                                Log.e("xyh", "onResponse: " + string);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("xyh", "onFailure: " + t.getMessage());
                    }
                });

    }
}

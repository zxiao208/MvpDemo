package com.zx.mvpdemo.dagger.module;

import com.zx.mvpdemo.dagger.bean.Police;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class MainModule {

    @Singleton //单利模式标记
    @Provides //对外提供对象
    OkHttpClient okHttpClientProvider() {
        return new OkHttpClient();
    }
}

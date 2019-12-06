package com.zx.mvpdemo.dagger.component;

import com.zx.mvpdemo.dagger.module.MainModule;
import com.zx.mvpdemo.demo.Dagger2Demo;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class})
public interface OkHttpComponent {
    void inject(Dagger2Demo dagger2Demo);
}

package com.zx.mvpdemo.dagger.module;

import dagger.Module;

@Module
public class PoliceModule {
    private String  name;

    public PoliceModule(String name){
        this.name=name;
    }



}

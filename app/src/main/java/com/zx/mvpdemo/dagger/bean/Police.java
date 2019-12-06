package com.zx.mvpdemo.dagger.bean;

import com.zx.mvpdemo.dagger.present.Shotting;

import javax.inject.Inject;

public class Police  {
    BadMan badMan;
    @Inject
    public Police(BadMan badMan) {
        this.badMan=badMan;
    }

    public String useBadMan(){
        return badMan.run();
    }
    public String killing() {
        return "bill bill";
    }


}

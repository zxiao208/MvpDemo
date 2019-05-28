package com.zx.mvpdemo.dagger.bean;

import com.zx.mvpdemo.dagger.present.Shotting;

import javax.inject.Inject;

public class Police implements Shotting {
    BadMan badMan;
    @Inject
    public Police(BadMan badMan) {
        this.badMan = badMan;
    }

    @Override
    public String killing() {
        return "bill bill";
    }


}

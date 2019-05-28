package com.zx.mvpdemo.dagger.bean;

import java.util.Map;

import javax.inject.Inject;

public class BadMan {
    private Map<String,Boolean> badmans;
    public BadMan( Map<String,Boolean> badmans){
        this.badmans = badmans;
    }

    public void setBadmans(Map<String, Boolean> badmans) {
        this.badmans = badmans;
    }
}

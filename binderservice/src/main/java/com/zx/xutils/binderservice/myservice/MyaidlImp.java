package com.zx.xutils.binderservice.myservice;

import android.os.RemoteException;

import com.zx.xutils.binderservice.IMyAidlInterface;

public class MyaidlImp extends IMyAidlInterface.Stub {
    String name ="smaa";
    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public void setName(String name) throws RemoteException {
        this.name=name;
    }
}

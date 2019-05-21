package com.zx.xutils.binderservice.myservice;

import android.os.IBinder;
import android.os.RemoteException;

import com.zx.xutils.binderservice.INewBinderService;

public class BindAidlImp implements INewBinderService {

    @Override
    public String getAge() throws RemoteException {
        return null;
    }

    @Override
    public IBinder asBinder() {
        return null;
    }
}

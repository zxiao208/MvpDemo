package com.zx.mvpdemo.demo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zx.mvpdemo.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class RxjavaDemoActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    //创建
    Observable observable = null;
    Observer<String> observer = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjavademo);
        init();
        observable = Observable.just("1", "2", "3");
        //创建observer观察者
        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
                Log.i("Rxjava", "onNext: " + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i("Rxjava", "onComplete: " + "");
            }
        };

        //比observer多onstart();它会在 subscribe 刚开始，而事件还未发送之前被调用，可以用于做一些准备工作
        // unsubscribe()取消订阅
        final Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };

    }


    private void myobservable() {
        observable.subscribe(observer);
    }

    private void init() {
        button = findViewById(R.id.startdingyue);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startdingyue:
                myobservable();
                break;
            default:
                break;
        }
    }
}

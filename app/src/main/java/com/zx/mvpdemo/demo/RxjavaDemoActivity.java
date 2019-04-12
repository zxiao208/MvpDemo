package com.zx.mvpdemo.demo;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
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
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class RxjavaDemoActivity extends AppCompatActivity implements View.OnClickListener {
    Button button,button2,button3;
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
    private void rxjava2(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.e("RXjava", "Observable emit 1" + "\n");
                emitter.onNext(1);
                Log.e("RXjava", "Observable emit 2" + "\n");
                emitter.onNext(2);
                Log.e("RXjava", "Observable emit 3" + "\n");
                emitter.onNext(3);
                emitter.onComplete();
                Log.e("RXjava", "Observable emit 4" + "\n" );
                emitter.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            // 第二步：初始化Observer
            private int i;
            private Disposable mDisposable;
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
//                    mDisposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("RXjava", "onError : value : " + e.getMessage() + "\n" );
            }

            @Override
            public void onComplete() {
                Log.e("RXjava", "onComplete" + "\n" );
            }
        });
    }

    /**
     *简单地说，subscribeOn() 指定的就是发射事件的线程，observerOn 指定的就是订阅者接收事件的线程。
     * 多次指定发射事件的线程只有第一次指定的有效，也就是说多次调用 subscribeOn() 只有第一次的有效，其余的会被忽略。
     * 但多次指定订阅者接收线程是可以的，也就是说每调用一次 observerOn()，下游的线程就会切换一次。
     */
    private void rxjava3(){
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                    emitter.onNext(1);
                    emitter.onNext(2);
                    emitter.onNext(3);
                    emitter.onComplete();
                    emitter.onNext(4);
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i("rxjava3", "accept1: "+integer);
                    }
                })
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i("rxjava3", "accept2: "+integer);
                    }
                });

    }
    private void init() {
        button = findViewById(R.id.startdingyue);
        button2=findViewById(R.id.startdingyue2);
        button3=findViewById(R.id.startdingyue3);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    private void rxjava4(){

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startdingyue:
                myobservable();
                break;
            case R.id.startdingyue2:
                rxjava2();
                break;
            case R.id.startdingyue3:
                rxjava3();
                break;
            default:
                break;
        }
    }
}

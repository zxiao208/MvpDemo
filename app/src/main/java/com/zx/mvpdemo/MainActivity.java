package com.zx.mvpdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zx.mvpdemo.R;
import com.zx.mvpdemo.framework.ContentView;
import com.zx.mvpdemo.framework.Event;
import com.zx.mvpdemo.framework.InjectUtils;
import com.zx.mvpdemo.framework.ViewInject;
import com.zx.mvpdemo.simple.demo8.LoginPresenter_8;
import com.zx.mvpdemo.simple.demo8.LoginView_8;
import com.zx.mvpdemo.simple.demo8.base.BaseActivity_8;


@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity_8<LoginView_8, LoginPresenter_8> implements LoginView_8 {

    @ViewInject(R.id.bt_1)
    private Button bt_1;

    @ViewInject(R.id.bt_2)
    private Button bt_2;
//    private LoginPresenter_4 presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtils.inject(this);
    }

    //2.0版本->写法
//    @OnClick({R.id.tv_text, R.id.tv_text_a})
//    public void click(View v){
//        Toast.makeText(this,"点击了TextView", Toast.LENGTH_LONG).show();
//    }

    //3.0版本->写法
    @Event(
            value = {R.id.bt_1, R.id.bt_2},
            type = View.OnClickListener.class,
            setter = "setOnClickListener",
            method = "onClick")
    public void click(View v) {
        if (v.getId() == R.id.bt_1) {
            Toast.makeText(this, "点击了Button->1", Toast.LENGTH_LONG).show();
            loginServer();
        } else if (v.getId() == R.id.bt_2) {
            Toast.makeText(this, "点击了Button->2", Toast.LENGTH_LONG).show();
        }
    }

    //第一步：看一个基本案例？->普通代码实现
//    private void loginServer(){
//        HttpTask httpTask = new HttpTask(new HttpUtils.OnHttpResultListener() {
//            @Override
//            public void onResult(String result) {
//                Toast.makeText(MainActivity.this, "登录结果："+result, Toast.LENGTH_LONG).show();
//            }
//        });
//        httpTask.execute("http://192.168.57.1:8080/Dream_6_19_LoginServer/LoginServlet", "Dream", "123456");
//    }


    //第二步：MVP实现->简单案例->分层次设计-基本实现
    //团队开发
    //V层->MainActivity->LoginView
    //M层->数据层(网络请求、数据库、文件等等...)->LoginModel
    //P层->需要新建->LoginPresenter
    //总结：弊端(类结构复杂了)，优点(结构清晰，维护性强，有利于团队开发，模块维护，功能扩展，降低开发成本)
    //大项目：适合MVP（特别是一个功能界面只有当前这个模块功能，比较适合采用MVP，如果跨模块，相对麻烦一点）
//    private void loginServer() {
//        LoginPresenter_1 presenter = new LoginPresenter_1(this);
//        presenter.login("Dream", "123456");
//    }

//    @Override
//    public void onLoginResult(String result) {
//        Toast.makeText(MainActivity.this, "登录结果：" + result, Toast.LENGTH_LONG).show();
//    }

    //第三步：MVP实现->优化->优化第1步->方法绑定
    //第二步（含义）：构造方法绑定
    //attachView、detachView
    //为什么这么设计？
    //问题：因为当我们的Activity关闭的时候，但是网络请求还正在进行，然而我们希望是不要刷新UI了，因为销毁了，解除UI层和数据层关联？
    //解决方案：方法绑定（绑定、解绑）
    //attachView->绑定
    //detachView->解绑
//    private void loginServer() {
//        presenter = new LoginPresenter_2();
//        presenter.attachView(this);
//        presenter.login("Dream", "123456");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        presenter.detachView();
//    }
//
//    @Override
//    public void onLoginResult(String result) {
//        Toast.makeText(MainActivity.this, "登录结果：" + result, Toast.LENGTH_LONG).show();
//    }


    //   第四步：MVP实现->优化->优化第2步
//   分析问题：现在写一个功能，你觉没什么，
//            但是如果我写了20个类（写了100个类），
//            那么你是不是要绑定（解除绑定）100次？会想死。
//            目的就是为了不需要这么麻烦，统一管理即可（统一绑定）
//   解决方案：抽象类（抽象类->抽取）->BasePresenter
//    private void loginServer() {
//        presenter = new LoginPresenter_3();
//        presenter.attachView(this);
//        presenter.login("Dream", "123456");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        presenter.detachView();
//    }
//
//    @Override
//    public void onLoginResult(String result) {
//        Toast.makeText(MainActivity.this, "登录结果：" + result, Toast.LENGTH_LONG).show();
//    }


    //    第五步：MVP实现->优化->优化第3步->BaseView解决
//    分析问题：BasePresenter类型类死了，
//    如果你有很多的模块，那么没发抽象。然而希望动态，
//    通过BaseView解决。成立
//    private void loginServer() {
//        presenter = new LoginPresenter_4();
//        presenter.attachView(this);
//        presenter.login("Dream", "123456");
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        presenter.detachView();
//    }
//
//    @Override
//    public void onLoginResult(String result) {
//        Toast.makeText(MainActivity.this, "登录结果：" + result, Toast.LENGTH_LONG).show();
//    }


    //    第七步：MVP实现->优化->优化第5步->抽象类BaseActivity
//    分析问题：现在有一个Activity你需要绑定，还好，如果我有30个Activity，
//    50个Fragment，怎么办？代码冗余？
//    解决方案：抽象类->抽象出绑定和解除绑定
//    private void loginServer() {
//        getPresenter().login("Dream", "123456");
//    }
//
//    @Override
//    public LoginPresenter_6 createPresenter() {
//        return new LoginPresenter_6();
//    }
//
//    @Override
//    public LoginView_6 createView() {
//        return this;
//    }
//
//    @Override
//    public void onLoginResult(String result) {
//        Toast.makeText(MainActivity.this, "登录结果：" + result, Toast.LENGTH_LONG).show();
//    }


    //    第八步：MVP实现->优化->优化第6步->BaseActivity中抽象->
//    抽象实现（BasePresneter和BaseView）
//    分析问题：父类BaseActivity写死了，还是没有满足要求？
//    只能够用LoginPresenter_6，然而我们的目的是能够兼容所有的模块
//    解决方案：抽象实现（BasePresneter和BaseView）
//    private void loginServer() {
//        LoginPresenter_7 loginPresenter = (LoginPresenter_7) getPresenter();
//        loginPresenter.login("Dream", "123456");
//    }
//
//    @Override
//    public LoginPresenter_7 createPresenter() {
//        return new LoginPresenter_7();
//    }
//
//    @Override
//    public LoginView_7 createView() {
//        return this;
//    }
//
//    @Override
//    public void onLoginResult(String result) {
//        Toast.makeText(MainActivity.this, "登录结果：" + result, Toast.LENGTH_LONG).show();
//    }


    //    第九步：MVP实现->优化->优化第7步
//    分析问题：还是进行强制类型转换，一个类还好？100个类？有问题？希望结果，不用类型转换
//    解决方案：泛型设计
    private void loginServer() {
        getPresenter().login("Dream", "123456");
    }

    @Override
    public LoginPresenter_8 createPresenter() {
        return new LoginPresenter_8();
    }

    @Override
    public LoginView_8 createView() {
        return this;
    }

    @Override
    public void onLoginResult(String result) {
        Toast.makeText(MainActivity.this, "登录结果：" + result, Toast.LENGTH_LONG).show();
    }

    //很多时候：技术，思路？

}

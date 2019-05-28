package com.zx.mvpdemo.dagger.component;

import com.zx.mvpdemo.demo.Dagger2Demo;
import dagger.Component;


@Component
public interface ActivityComponent {
    void inject(Dagger2Demo dagger2Demo);
}

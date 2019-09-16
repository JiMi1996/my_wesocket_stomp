package com.rabbitmq;


import java.security.Principal;

/**
 * Created by huangrongyou@yixin.im on 2018/7/10.
 */
public class MyPrincipal implements Principal {
    private String name;

    public MyPrincipal(String name){
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
}

package com.rabbitmq;

/**
 * @program: mqtest
 * @description:
 * @author: 李晶敏
 * @create: 2019-09-10 17:23
 **/
public class Greeting {
    private String content;

    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
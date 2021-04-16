package com.example.springbootfirstdemo.learn.moshi;

/**
 * 饿汉式，单例模式
 */
public class Singleton {

    private static  final Singleton singleton = new Singleton();

    private Singleton(){};


    private static Singleton getInstance(){
        return singleton;
    }
}

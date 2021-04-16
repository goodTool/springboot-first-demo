package com.example.springbootfirstdemo.learn.moshi;


/**
 * 懒汉式，单例模式
 * 如果在多线程调用的情况下，第15，16行就容易引发线程安全问题
 */
public class Single {

    private static Single s = null;

    private Single(){};

    public static Single getInstance(){
        if(s == null){
            s = new Single();
        }
        return s;
    }

}

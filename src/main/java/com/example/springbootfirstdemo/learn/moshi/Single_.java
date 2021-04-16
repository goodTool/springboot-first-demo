package com.example.springbootfirstdemo.learn.moshi;

/**
 * 懒汉式单例模式，线程安全
 * 因为每一线程访问同步代码块时，都要对Single.class进行锁判断，相应的就牺牲了一部分性能。
 */

public class Single_ {

    private static Single_ s = null;

    private Single_(){};

    public static Single_ getInstance(){
        if(s == null){
          synchronized (Single_.class){
              if(s == null){
                  s = new Single_();
              }
          }
        }
        return s;
    }

}

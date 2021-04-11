package com.example.springbootfirstdemo.com.test;

import java.util.ArrayList;
import java.util.List;

public class CommonTest {

    public static void main(String[] args) {

        List<String> list = new ArrayList<>();
        list.add("static_date");

        for (String s : list) {
            System.out.println(s.contains("static_month"));

        }


    }
}

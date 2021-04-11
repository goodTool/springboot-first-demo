package com.example.springbootfirstdemo.com.test.wangyiMusic;



import java.io.*;
import java.util.ArrayList;

public class ReadJson {

    public static void main(String[] args) {

        String str= ReadFile("E:\\Desktop\\python参数3.txt");

//        JSONObject.fromObject(str);


    }


    public static String ReadFile(String Path) {
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "gbk");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }



}

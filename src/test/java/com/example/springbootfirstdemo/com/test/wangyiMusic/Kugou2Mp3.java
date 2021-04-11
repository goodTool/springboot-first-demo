package com.example.springbootfirstdemo.com.test.wangyiMusic;

import java.io.*;

public class Kugou2Mp3 {


    /**
     * Hex字符串转byte
     * @param inHex 待转换的Hex字符串
     * @return  转换后的byte
     */
    public static byte hexToByte(String inHex) {
        return (byte) Integer.parseInt(inHex, 16);
    }


    public static void main(String[] args) {
        byte b = hexToByte("0xAC");
        System.out.println(b);



    }


       /* try {
            File file = new File("E:\\KuGou\\Temp\\92d1cab28eb0fc67fcbfaa658991d939.kgtemp");
            FileInputStream stream = new FileInputStream(file);
            File outFile = new File("E:\\KuGou\\Temp\\aa.mp3");
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(outFile));

            int pos = 1024;//从下一个字节开始读
            int len = 15;//读几个字节
            stream.skip(pos); //跳过包含pos的字节数
//            byte[] b = new byte[len];
//            stream.read(b);


            byte[] key ={0xAC, 0xEC, 0xDF, 0x57};
            byte[] buffer = new byte[1024];


           *//* var output = File.OpenWrite(@"d:\test.mp3");//输出文件
            input.Seek(1024, SeekOrigin.Begin);//跳过1024字节的包头
            byte[] buffer = new byte[key.length];*//*
            int length;

            while ((length = stream.read(buffer)) != -1) {
                for (int i = 0; i < length; i++) {
                    int k = key[i];
                    int kh = k >> 4;
                    int kl = k & 0xf;
                    int b = buffer[i];
                    int low = b & 0xf ^ kl;//解密后的低4位
                    int high = (b >> 4) ^ kh ^ low & 0xf;//解密后的高4位
                    buffer[i] = (byte) (high << 4 | low);
                }
                dos.write(buffer, 0, length);
            }
            stream.close();
            dos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/



}
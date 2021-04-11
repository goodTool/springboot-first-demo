package com.example.springbootfirstdemo.com.test.wangyiMusic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.sun.tools.javac.util.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.Jar;
import org.apache.tomcat.jni.FileInfo;
import springfox.documentation.spring.web.json.Json;

import java.io.*;

public class WangyiMp3 {




        public static void main(String[] args) {

                String path = "C:\\Users\\Administrator\\AppData\\Local\\Netease\\CloudMusic\\Cache\\Cache\\";
                File file = new File(path);
                int i =1;
                if (file.exists()) {
                    File[] files = file.listFiles();
                    for (File file2 : files) {
                        // 文件夹
                        if (file2.isDirectory()) {
                            System.out.println("文件夹:" + file2.getAbsolutePath());
                        // 是文件
                        } else {
                            String fileName = file2.getName();
                            String type = fileName.substring(fileName.lastIndexOf(".")+1);
                            if("uc".equals(type)){
                                toMap3(path,fileName);
                            }
                        }
                        System.out.println(i++);
                    }

                } else {
                    System.out.println("文件不存在!");
                }



        }


        public static void toMap3(String path,String fileName){
            DataInputStream dis = null;
            DataOutputStream dos = null;

            String id = fileName.substring(0,fileName.indexOf("-"));
             try{

               String content = null;//网页内容

                int socketTimeout = 5000;//读取数据超时
                int connectTimeout = 5000;//链接超时

                RequestConfig config = RequestConfig.custom()
                        .setConnectTimeout(connectTimeout)
                        .setConnectionRequestTimeout(connectTimeout)
                        .setSocketTimeout(socketTimeout).build();

                CloseableHttpClient httpClient = HttpClientBuilder.create()
                        .setDefaultRequestConfig(config).build();

                HttpGet httpGet = new HttpGet("https://api.imjad.cn/cloudmusic/?type=detail&id="+id);
//                HttpGet httpGet = new HttpGet("https://music.163.com/#/song?id=445546453");


                HttpResponse response = httpClient.execute(httpGet);

                HttpEntity entity = response.getEntity();
                if (entity!=null) {

                    //  content {"songs":[{"name":"挪威的森林","id":27867372,"pst":0,"t":0,"ar":[{"id":5354,"name":"伍佰","tns":[],"alias":[]}],"alia":[],"pop":100.0,"st":0,"rt":"","fee":8,"v":158,"crbt":null,"cf":"","al":{"id":2685336,"name":"生命的现场","picUrl":"https://p1.music.126.net/6Lod8Ys5E6fiKZ9xrqCXug==/109951163100347037.jpg","tns":[],"pic_str":"109951163100347037","pic":109951163100347037},"dt":223529,"h":{"br":320000,"fid":0,"size":8942280,"vd":0.0},"m":{"br":192000,"fid":0,"size":5365386,"vd":0.0},"l":{"br":128000,"fid":0,"size":3576938,"vd":0.0},"a":null,"cd":"1","no":16,"rtUrl":null,"ftype":0,"rtUrls":[],"djId":0,"copyright":1,"s_id":0,"mv":0,"cp":7003,"rtype":0,"rurl":null,"mst":9,"publishTime":1356048000000}],"privileges":[{"id":27867372,"fee":8,"payed":0,"st":0,"pl":128000,"dl":0,"sp":7,"cp":1,"subp":1,"cs":false,"maxbr":320000,"fl":128000,"toast":false,"flag":132,"preSell":false}],"code":200}

                    content = EntityUtils.toString(entity,"UTF-8");
                    EntityUtils.consume(entity);//关闭内容流
                }
                //释放链接
                httpClient.close();

                JSONObject obj = JSONObject.parseObject(content);
                JSONArray arr =(JSONArray) obj.get("songs");
                JSONObject o1 = (JSONObject)arr.get(0);
                // 歌曲名
                String musicName = o1.getString("name");
                JSONArray arr2=(JSONArray) o1.get("ar");
                JSONObject o2 = (JSONObject)arr2.get(0);
                // 演唱人
                String author = o2.getString("name");


                System.out.println("content"+content);



                File inFile = new File(path + fileName);
                //  重命名
                File outFile = new File(path + musicName+"_"+author + ".mp3");


                dis = new DataInputStream(new FileInputStream(inFile));
                dos = new DataOutputStream(new FileOutputStream(outFile));
                byte[] b = new byte[1024];
                int len;
                while ((len = dis.read(b)) != -1) {
                    for (int i = 0; i < len; i++) {
                        b[i] ^= 0xa3;
                    }
                    dos.write(b, 0, len);
                }

                //  删除缓存文件  .uc
                boolean delete = inFile.delete();
                if(delete){
                    System.out.println("删除缓存文件成功。。。");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (dos != null) {
                    try {
                        dos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (dis != null) {
                    try {
                        dis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


            System.out.println("over...");
        }


}

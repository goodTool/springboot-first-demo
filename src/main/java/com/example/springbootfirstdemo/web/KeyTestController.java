package com.example.springbootfirstdemo.web;

import com.example.springbootfirstdemo.bean.User;
import com.example.springbootfirstdemo.myexception.ServiceException;
import com.example.springbootfirstdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/test")
public class KeyTestController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest request;


    /**
     * map参数，测试主键返回
     */
    @RequestMapping("testKey")

    public void testKey(){
        Map<String,Object> map = new HashMap<>();
        map.put("userName","wjk");
        map.put("age",11);
        map.put("passWord","111111");
        userService.testKey(map);


    }




    @RequestMapping("sayHello")
    public String sayHello(){

        System.out.println("sessionId:"+request.getRequestedSessionId());
        return "Hello Word! 8082";
    }
}

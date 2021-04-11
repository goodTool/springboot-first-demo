package com.example.springbootfirstdemo.web;

import com.example.springbootfirstdemo.bean.User;
import com.example.springbootfirstdemo.myexception.GeneralException;
import com.example.springbootfirstdemo.myexception.ResponseCodeEnum;
import com.example.springbootfirstdemo.myexception.WebException;
import com.example.springbootfirstdemo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


/**
 * @USER: wjk
 * @DATE: 2019-6-13
 * @TIME: 15:59
 * @DAY_NAME_SHORT: 
 * 
 **/

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    public HttpServletResponse response;

    @Autowired
    public HttpServletRequest request;

    @Autowired
    private UserService userService;


    @RequestMapping("/login")
    public User login(@RequestParam String userName,
                        @RequestParam String passWord
                        ) throws WebException{

        HashMap<String,String> parm = new HashMap<>();
        if(StringUtils.isBlank(userName)){
            throw new WebException(ResponseCodeEnum.ERROR3);
        }
        parm.put("userName",userName);
        parm.put("passWord",passWord);
        User user1 = userService.getUser(parm);

        request.setAttribute("userInfo",user1);

        return user1;
    }

    /**
     * 暂时没有实现显示出service层定义的异常
     * @param userId
     * @return
     * @throws GeneralException
     */
    @RequestMapping("/getUser")
    public User getUserName(@RequestParam String userId) throws GeneralException {
        User userInfo =(User) request.getAttribute("userInfo");
        if(userInfo == null){
            throw new WebException(ResponseCodeEnum.ERROR1);
        }

        User user = userService.getUserById(Integer.parseInt(userId));


        return user;

    }
}

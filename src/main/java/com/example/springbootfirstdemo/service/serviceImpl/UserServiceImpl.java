package com.example.springbootfirstdemo.service.serviceImpl;

import com.example.springbootfirstdemo.bean.User;
import com.example.springbootfirstdemo.mapper.UserMapper;
import com.example.springbootfirstdemo.myexception.ResponseCodeEnum;
import com.example.springbootfirstdemo.myexception.ServiceException;
import com.example.springbootfirstdemo.myexception.WebException;
import com.example.springbootfirstdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUserById(Integer id) throws ServiceException {
        User user = userMapper.getUserById(id);
        if(user == null){
            throw new ServiceException("-1","用户不存在");
//            throw new ServiceException("用户不存在");
        }

        return user;
    }

    @Override
    public List<User> getUserList() {
        return null;
    }

    @Override
    public User getUser(Map map) throws WebException {

        User user = userMapper.getUser(map);
        if( user == null){
            throw new WebException(ResponseCodeEnum.ERROR2);
        }

        return user;


    }

    @Override
    public void testKey(Map map) {

        userMapper.testKey(map);
    }
}

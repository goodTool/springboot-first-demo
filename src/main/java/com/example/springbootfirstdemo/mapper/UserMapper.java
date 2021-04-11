package com.example.springbootfirstdemo.mapper;

import com.example.springbootfirstdemo.bean.User;
import com.example.springbootfirstdemo.myexception.ServiceException;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface UserMapper {

    User getUser(Map map);

    public List<User> getUserList();

    void testKey(Map map);

    User getUserById(Integer id);



}

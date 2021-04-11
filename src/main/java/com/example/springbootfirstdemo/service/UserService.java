package com.example.springbootfirstdemo.service;

import com.example.springbootfirstdemo.bean.User;
import com.example.springbootfirstdemo.myexception.ServiceException;
import com.example.springbootfirstdemo.myexception.WebException;

import java.util.List;
import java.util.Map;

public interface UserService  {

    User getUserById(Integer id) throws ServiceException;

    public List<User> getUserList() ;


    User getUser(Map map) throws WebException;

    void testKey(Map map);


}

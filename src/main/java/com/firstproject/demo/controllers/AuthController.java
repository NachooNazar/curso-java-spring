package com.firstproject.demo.controllers;

import com.firstproject.demo.dao.UserDao;
import com.firstproject.demo.models.User;
import com.firstproject.demo.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtutil;

    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String loginUser(@RequestBody User user) {
        User userLogued = userDao.getUserByCredentials(user);
        if(userLogued != null){

            String tokenJWT = jwtutil.create(String.valueOf(userLogued.getId()), userLogued.getEmail());

            return tokenJWT;
        }
        return null;

    }
}

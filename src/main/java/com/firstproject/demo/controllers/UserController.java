package com.firstproject.demo.controllers;

import com.firstproject.demo.dao.UserDao;
import com.firstproject.demo.models.User;
import com.firstproject.demo.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    private boolean validateToken(String tokenJWT){
        String idUser = jwtUtil.getKey(tokenJWT);
        return idUser != null;
    }

    @RequestMapping(value = "api/users", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user) {
        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon.hash(1,1024,1,user.getPassword());
        user.setPassword(hash);
        userDao.registerUser(user);
    }

    @RequestMapping(value = "api/users", method = RequestMethod.GET)
    public List<User> getUsers(@RequestHeader(value = "Authorization") String tokenJWT) {
        if(!validateToken(tokenJWT)) return null;
        return userDao.getUsers();
    }


    @RequestMapping(value = "api/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestHeader(value = "Authorization") String tokenJWT, @PathVariable Long id) {
        if(!validateToken(tokenJWT)) return;
       userDao.userDelete(id);
    }

}

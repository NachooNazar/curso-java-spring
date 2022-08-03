package com.firstproject.demo.dao;

import com.firstproject.demo.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.data.annotation.Persistent;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
//pUDE ACCEDER AL REPO DE LA BASE DE DATOS
@Transactional
//La forma en la que va a tratar las consultas de SQL

public class UserDaoImp implements UserDao{

    @PersistenceContext
    EntityManager manager;
    //Sirve para hacer la conexion con la bd

    @Override
    public List<User> getUsers() {
        String query = "FROM User";
        return manager.createQuery(query).getResultList();
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void userDelete(Long id) {
        //UNA FORMA DE HACERLO
        User user = manager.find(User.class,id);
        manager.remove(user);
    }

    @Override
    public void registerUser(User user) {
        manager.merge(user);
    }

    @Override
    public User getUserByCredentials(User user) {
        String query = "FROM User WHERE email = :email";

         List<User> list = manager.createQuery(query)
                 .setParameter("email", user.getEmail())
                 .getResultList();
            if(list.isEmpty()) {
                return null;
            }
            String passHashed = list.get(0).getPassword();
            Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            if(argon.verify(passHashed,user.getPassword())){
                return list.get(0);
            }
            return null;
    }

}



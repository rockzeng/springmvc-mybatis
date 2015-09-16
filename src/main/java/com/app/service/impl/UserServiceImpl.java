package com.app.service.impl;

import com.app.dao.UserDao;
import com.app.model.User;
import com.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhangminjie on 15/5/17.
 */
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao;
    @Override
    public User selectByPrimaryKey(int id) {
        return userDao.selectByPrimaryKey(id);
    }
}

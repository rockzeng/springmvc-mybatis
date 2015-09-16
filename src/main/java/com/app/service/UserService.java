package com.app.service;

import com.app.model.User;
//import org.slf4j.impl.StaticLoggerBinder;
/**
 * Created by zhangminjie on 15/5/17.
 */

public interface UserService {

    User selectByPrimaryKey(int id);
}

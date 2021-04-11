package com.xtei.tailorsys.service;

import com.xtei.tailorsys.entity.User;
import com.xtei.tailorsys.util.pagehelper.PageResult;


/**
 * FileName: UserService
 * Author: Li Zihao
 * Date: 2021/3/2 12:20
 * Description:
 */

public interface UserService {

    User findByUsernameAndPassword(String username,String password);
    User findByUsername(String username);
    int insertUser(User user);

    int updateUserStatus(int userId,boolean userStatus);
    int updateUser(int userId,String userEmail,String userPhone);
    int changePassword(String username,String password);
    User findUserById(int userId);
    PageResult findUserList(String query,Integer pageNum, Integer pageSize);
}

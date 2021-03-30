package com.xtei.tailorsys.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xtei.tailorsys.model.User;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.apache.ibatis.annotations.Param;


/**
 * FileName: UserService
 * Author: Li Zihao
 * Date: 2021/3/2 12:20
 * Description:
 */

public interface UserService {

    public User findByUsernameAndPassword(String username,String password);
    public User findByUsername(String username);
    public int insertUser(User user);

    public int updateUserStatus(int userId,boolean userStatus);
    public int updateUser(int userId,String userEmail,String userPhone);
    public User findUserById(int userId);
    public PageResult findUserList(String query,Integer pageNum, Integer pageSize);
}

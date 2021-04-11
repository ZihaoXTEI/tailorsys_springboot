package com.xtei.tailorsys.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xtei.tailorsys.mapper.UserMapper;
import com.xtei.tailorsys.entity.User;
import com.xtei.tailorsys.service.UserService;
import com.xtei.tailorsys.util.PageHelperUtils;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * FileName: UserServiceImpl
 * Author: Li Zihao
 * Date: 2021/3/2 15:54
 * Description:
 */

@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService, UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = userMapper.findByUsernameAndPassword(username, password);
        return user;
    }

    @Override
    public User findByUsername(String username) {
        User user = userMapper.findByUsername(username);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(s);
        return user;
    }

    @Override
    public int insertUser(User user) {
        int res = userMapper.insertUser(user);
        return res;
    }

    @Override
    public int updateUser(int userId, String userEmail, String userPhone) {
        int res = userMapper.updateUser(userId, userEmail, userPhone);
        return res;
    }

    @Override
    public int changePassword(String username, String password) {
        int res = userMapper.changePassword(username, password);
        return res;
    }

    @Override
    public int updateUserStatus(int userId, boolean userStatus) {
        int res = userMapper.updateStatus(userId, userStatus);
        return res;
    }

    @Override
    public User findUserById(int userId) {
        User user = userMapper.findUserById(userId);
        return user;
    }

    @Override
    public PageResult findUserList(String query, Integer pageNum, Integer pageSize) {
        return PageHelperUtils.getPageResult(getPageInfo(query, pageNum, pageSize));
    }

    private PageInfo<User> getPageInfo(String query, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.findUserList(query);
        //将用户密码信息抹除
        for (User user : userList) {
            user.setPassword("");
        }
        return new PageInfo<User>(userList);
    }

}

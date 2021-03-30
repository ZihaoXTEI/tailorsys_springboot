package com.xtei.tailorsys.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.xtei.tailorsys.model.User;
import com.xtei.tailorsys.model.response.ResponseBean;
import com.xtei.tailorsys.service.UserService;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

/**
 * FileName: UserController
 * Author: Li Zihao
 * Date: 2021/3/4 9:53
 * Description:
 */

@RestController
@RequestMapping("system")
public class UserController {

    @Autowired
    private UserService userService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*/
     *增加用户
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseBean addUser(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String email = jsonObject.getString("useremail");
        String phone = jsonObject.getString("userphone");
        Date date = new Date();          // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
        User user = new User(username, password, email, phone, true, "ADMIN", timeStamp);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        if (userService.insertUser(user) == 1) {
            return ResponseBean.success("新增用户信息成功");
        }
        return ResponseBean.error("新增用户信息失败");
    }

    /*
     *修改用户状态
     */
    @RequestMapping(value = "/user/{id}/{status}", method = RequestMethod.PUT)
    public ResponseBean changeUserStatus(@PathVariable("id") int id, @PathVariable("status") boolean status) {
        if (userService.updateUserStatus(id, status) == 1) {
            return ResponseBean.success("更新用户状态成功");
        }
        return ResponseBean.error("更新用户状态失败", null);
    }

    /*
     *修改用户信息
     */
    @RequestMapping(value = "/user/{userid}", method = RequestMethod.PUT)
    public ResponseBean changeUserInfo(@PathVariable("userid") Integer userId, @RequestBody JSONObject jsonObject) {

        int id = jsonObject.getInteger("userid");
        String userPhone = jsonObject.getString("userPhone");
        String userEmail = jsonObject.getString("userEmail");
        if (userService.updateUser(id, userEmail,userPhone) == 1) {
            return ResponseBean.success("修改用户信息成功");
        }
        return ResponseBean.error("修改用户信息失败");

    }

    /*
     *根据用户ID获取用户的信息
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseBean getUserById(@PathVariable("id") int id) {
        System.out.println("OBA");
        if (id <= 0) {
            return ResponseBean.error("获取用户信息失败");
        }
        User user = userService.findUserById(id);
        if (user != null) {
            return ResponseBean.success("获取用户信息成功", user);
        } else {
            return ResponseBean.error("获取用户信息失败");
        }

    }

    /*
     *用户列表
     */
    @GetMapping("/user")
    public ResponseBean<PageResult> getUserList(@RequestParam(value = "query", defaultValue = "") String query,
                                                @RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum,
                                                @RequestParam(value = "pagesize", defaultValue = "5") Integer pagesize) {
        PageResult pageResult = userService.findUserList(query, pagenum, pagesize);
        return ResponseBean.success("获取用户列表成功", pageResult);
    }


}

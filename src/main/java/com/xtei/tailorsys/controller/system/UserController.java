package com.xtei.tailorsys.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.xtei.tailorsys.entity.User;
import com.xtei.tailorsys.entity.response.ResponseBean;
import com.xtei.tailorsys.service.UserService;
import com.xtei.tailorsys.util.pagehelper.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

/**
 * FileName: UserController
 * Author: Li Zihao
 * Date: 2021/3/4 9:53
 * Description: 用户管理页面与修改密码页面视图控制器
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
    @RequestMapping(value = "/user/", method = RequestMethod.POST)
    public ResponseBean addUser(@RequestBody JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String email = jsonObject.getString("useremail");
        String phone = jsonObject.getString("userphone");
        Date date = new Date();          // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
        User user = new User(username, password, email, phone, true, "ADMIN", timeStamp);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        try {
            userService.insertUser(user);
            return ResponseBean.success("新增用户信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("新增用户信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /*
     *修改用户状态
     */
    @RequestMapping(value = "/user/{id}/{status}", method = RequestMethod.PUT)
    public ResponseBean changeUserStatus(@PathVariable("id") int id, @PathVariable("status") boolean status) {
        try {
            userService.updateUserStatus(id, status);
            return ResponseBean.success("更新用户状态成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("更新用户状态失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    /*
     *修改用户信息
     */
    @RequestMapping(value = "/user/{userid}", method = RequestMethod.PUT)
    public ResponseBean changeUserInfo(@PathVariable("userid") Integer userId, @RequestBody JSONObject jsonObject) {

        int id = jsonObject.getInteger("userid");
        String userPhone = jsonObject.getString("userPhone");
        String userEmail = jsonObject.getString("userEmail");
        try {
            userService.updateUser(id, userEmail, userPhone);
            return ResponseBean.success("修改用户信息成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("修改用户信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /*
     *根据用户ID获取用户的信息
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseBean getUserById(@PathVariable("id") int id) {

        try {
            User user = userService.findUserById(id);
            if (user != null) {
                return ResponseBean.success("获取用户信息成功", HttpServletResponse.SC_PARTIAL_CONTENT, user);
            } else {
                return ResponseBean.error("获取用户信息为空", HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("获取用户信息失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /*
     *用户列表
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseBean<PageResult> getUserList(@RequestParam(value = "query", defaultValue = "") String query,
                                                @RequestParam(value = "pagenum", defaultValue = "1") Integer pagenum,
                                                @RequestParam(value = "pagesize", defaultValue = "5") Integer pagesize) {
        try {
            PageResult pageResult = userService.findUserList(query, pagenum, pagesize);
            return ResponseBean.success("获取用户列表成功", HttpServletResponse.SC_OK, pageResult);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("获取用户列表失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 修改用户密码
     */
    @PutMapping(value = "/changepassword/{username}")
    public ResponseBean changePassword(@PathVariable("username") String username,
                                       @RequestParam(value = "oldpassword",defaultValue = "") String oldpassword,
                                       @RequestParam(value = "newpassword",defaultValue = "") String newpassword,
                                       @RequestParam(value = "confirmationpassword",defaultValue = "")String confirpassword) {

        String newPassword = bCryptPasswordEncoder.encode(newpassword);

        try {
            User user = userService.findByUsername(username);
            boolean matches = bCryptPasswordEncoder.matches(oldpassword, user.getPassword());
            if (!matches) {
                return ResponseBean.error("输入的旧密码不正确，请重新输入", HttpServletResponse.SC_BAD_REQUEST);
            }

            userService.changePassword(username, newPassword);
            return ResponseBean.success("修改密码成功", HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.error("修改密码失败", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }


}

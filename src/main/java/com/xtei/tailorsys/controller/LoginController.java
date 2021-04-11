package com.xtei.tailorsys.controller;

import com.xtei.tailorsys.entity.User;
import com.xtei.tailorsys.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Resource
    private UserService userService;


    //private BCryptPasswordEncoder bCryptPasswordEncoder =new BCryptPasswordEncoder();

    @GetMapping("/geta")
    public String login(){
        /*Date date = new Date();          // 获取一个Date对象
        Timestamp timeStamp = new Timestamp(date.getTime());     //   讲日期时间转换为数据库中的timestamp类型
        User user1=new User();
        user1.setUsername("admin");
        user1.setPassword(bCryptPasswordEncoder.encode("12345678"));
        user1.setEmail("zihaoxtei@outlook.com");
        user1.setStatus(true);
        user1.setRegistime(timeStamp);
        userService.insertUser(user1);

        date=new Date();
        timeStamp=new Timestamp(date.getTime());
        user1.setUsername("cikao");
        user1.setPassword(bCryptPasswordEncoder.encode("00008888"));
        user1.setEmail("2774176450@qq.com");
        user1.setStatus(true);
        user1.setRegistime(timeStamp);
        userService.insertUser(user1);

        date=new Date();
        timeStamp=new Timestamp(date.getTime());
        user1.setUsername("xteigroup");
        user1.setPassword(bCryptPasswordEncoder.encode("852741lzh"));
        user1.setEmail("2774176450@qq.com");
        user1.setStatus(false);
        user1.setRegistime(timeStamp);
        userService.insertUser(user1);*/


/*        int d= userService.insertUser("admin",bCryptPasswordEncoder.encode("12345678"),true);
        userService.insertUser("",bCryptPasswordEncoder.encode("12345678"),true);
        int c =userService.insertUser("cikao",bCryptPasswordEncoder.encode("00009999"),true);*/
        User user=userService.findByUsernameAndPassword("admin","123456");
        if(user == null){
            System.out.println("NULL");

            return null;
        }
        System.out.println(user.toString());

        return "HAHAHA";
    }
}

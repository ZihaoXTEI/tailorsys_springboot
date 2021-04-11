package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

/*    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    User selectByPrimaryKey(Integer userid);

    List<User> selectAll();

    int updateByPrimaryKey(User record);*/

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    int insertUser(User user);

    int updateStatus(@Param("userid") int userid, @Param("userstatus") boolean userstatus);

    int updateUser(@Param("userid") int userid, @Param("useremail") String useremail, @Param("userphone") String userphone);

    int changePassword(@Param("username") String username,@Param("password") String password);

    User findUserById(int userId);

    List<User> findUserList(String query);

    List<Map<Integer, String>> selectUserMap();

}
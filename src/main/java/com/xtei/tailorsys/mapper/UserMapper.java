package com.xtei.tailorsys.mapper;

import com.xtei.tailorsys.model.User;
import com.xtei.tailorsys.util.pagehelper.PageResult;
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

    User findUserById(int userId);

    List<User> findUserList(String query);

    List<Map<Integer, String>> selectUserMap();

}
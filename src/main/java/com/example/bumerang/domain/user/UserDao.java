package com.example.bumerang.domain.user;

import com.example.bumerang.web.dto.SessionUserDto;
import com.example.bumerang.web.dto.ex.request.JoinDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface  UserDao {

    public User findById(Integer userId);

    public List<User> findAll();

    public void insert(User user);

    public void update(User user);

    public void delete(Integer userId);

    public void join(JoinDto joinDto);

    public User findByLoginId(String userLoginId); // 예제

    public void updateUser(JoinDto joinDto); // 예제

    public SessionUserDto findByUser(@Param("userLoginId")String userLoginId, @Param("userPassword")String userPassword);
}

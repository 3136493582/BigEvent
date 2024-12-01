package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.example.service.UserService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

/**
 * user用户服务接口实现
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper usermapper;

    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        return usermapper.selectOne(queryWrapper);
    }

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public int register(String username, String password) {
        User user = new User();
        LocalDateTime localDateTime=LocalDateTime.now();
        user.setCreateTime(localDateTime);
        user.setUpdateTime(localDateTime);
        user.setUsername(username);
        user.setPassword(password);
        return usermapper.insert(user);
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public Integer login(String username, String password) {
        QueryWrapper<User> userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.and(condition->condition.eq("username",username).eq("password",password));
        return usermapper.selectOne(userQueryWrapper).getId();
    }

    /**
     * 更新用户
     * @param user
     * @return
     */
    @Override
    public int updateUserInfo(User user) {
        user.setUpdateTime(LocalDateTime.now());
        return usermapper.updateById(user);
    }

    /**
     * 更新用户头像
     * @param user
     * @return
     */
    @Override
    public int updateAvatar(User user) {
        user.setUpdateTime(LocalDateTime.now());
        return usermapper.updateById(user);
    }


    /**
     * 更新密码
     * @param user
     * @return
     */
    @Override
    public int updatePassword(User user) {
        return usermapper.updateById(user);
    }
}

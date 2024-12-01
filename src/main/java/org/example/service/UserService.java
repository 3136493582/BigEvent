package org.example.service;

import org.example.pojo.User;
import org.springframework.stereotype.Service;

/**
 * User用户服务接口
 */
public interface UserService {
    /**
     * 根据用户名查找
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 注册用户
     * @param username
     * @param password
     * @return
     */
    int register(String username, String password);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    Integer login(String username,String password);

    /**
     * 更新用户
     * @param user
     * @return
     */
    int updateUserInfo(User user);

    /**
     * 更新头像
     * @param user
     * @return
     */
    int updateAvatar(User user);

    /**
     * 更新密码
     * @param user
     * @return
     */
    int updatePassword(User user);
}

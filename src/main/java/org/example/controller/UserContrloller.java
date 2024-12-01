package org.example.controller;

import jakarta.annotation.Resource;
import jakarta.validation.constraints.Pattern;
import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.service.UserService;
import org.example.utils.JwtUtils;
import org.example.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserContrloller {

    @Resource
    private UserService userService;

    @Resource
    private JwtUtils jwtUtils;

    @Autowired
    private ThreadLocalUtil threadLocalUtil;

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password) {
        if(userService.findByUsername(username)!=null){
            return Result.error("用户已被注册");
        }
        userService.register(username,password);
        return Result.success();
    }

    /**
     * 用户登录
     * @param username
     * @param password  Authorization
     * @return
     */
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password){
        Integer userId = userService.login(username, password);
        if(userId!=null){
            Map<String,Object> claim=new HashMap<>();
            claim.put("id",userId);
            claim.put("username",username);
            String jwt=jwtUtils.genorateJwt(claim);
            return Result.success(jwt);
        }
        return Result.error("密码或者账号错误");
    }

    /**
     * 获取用户详细信息
     * header Authorization
     * @return
     */
    @GetMapping("/userInfo")
    public Result getUserInfo(/*@RequestHeader(name = "Authorization") String token*/){
//        User user=jwtUtils.parseJwt(token);
        User user=threadLocalUtil.get();
        User userInfo=userService.findByUsername(user.getUsername());
        return Result.success(userInfo);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping("/update")
    public Result updateUserInfo(@RequestBody @Validated User user){
        userService.updateUserInfo(user);
        return Result.success();
    }

    /**
     * 更新用户头像  @URL注解用来校验是否为URL格式
     * @param avatar
     * @return
     */
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam("avatarUrl") @URL String avatar){
        User user=threadLocalUtil.get();
        user.setUserPic(avatar);
        userService.updateAvatar(user);
        return  Result.success();
    }

    /**
     * 更新密码
     * @param pwds
     * @return
     */
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> pwds){
        String oldPwd=pwds.get("old_pwd");
        String newPwd=pwds.get("new_pwd");
        String rePwd=pwds.get("re_pwd");
        User user=threadLocalUtil.get();
        if (!(StringUtils.hasLength(oldPwd) && StringUtils.hasLength(newPwd) && StringUtils.hasLength(rePwd))){
            return Result.error("密码不能为空");
        }
        if(!newPwd.equals(rePwd)){
            return Result.error("新密码第一次与第二次输入不一致");
        }
        user.setPassword(newPwd);
        userService.updatePassword(user);
        return Result.success();
    }

}

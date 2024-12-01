package org.example.pojo;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;//主键ID
    @NotEmpty
    @Pattern(regexp = "^\\S{5,16}$")
    private String username;//用户名
    @JsonIgnore//让springboot向前端返回数据时忽略password
    @Pattern(regexp = "^\\S{5,16}$")
    private String password;//密码
    private String nickname;//昵称
    @Email //采用邮箱格式
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}

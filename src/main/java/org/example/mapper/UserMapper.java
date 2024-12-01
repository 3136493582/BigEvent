package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.User;

/**
 * user用户数据持久化
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

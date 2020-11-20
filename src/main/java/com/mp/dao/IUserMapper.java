package com.mp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mp.entity.User;
import org.springframework.stereotype.Component;

@Component("IUserMapper")
public interface IUserMapper extends BaseMapper<User> {

}

package com.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mp.dao.IUserMapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class WrapperUpperUse {
    @Autowired
    private IUserMapper userMapper;

    /**
     * 1、查询结果中只包含数据表的某几列
     */
    @Test
    public void queryWrapperUpper1() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.select("id", "user_real_name", "user_age")
                .like("user_real_name", "雨")
                .lt("user_age", 40);
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 2、当表的字段较多，但又只排除部分字段时
     * 但是我发现好像不可以使用这种方法排除主键列
     */
    @Test
    public void queryWrapperUpper2() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.select(
                User.class,
                info -> !info.getColumn().equals("user_real_name")
                        &&
                !info.getColumn().equals("id")
                )
                .like("user_real_name", "雨")
                .lt("user_age", 40);
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    @Test
    public void queryWrapperUpper3() {
        User user = new User();
        user.setId(2);
        user.setUserAge(26);
        QueryWrapper<User> query = new QueryWrapper<User>(user);
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }
}

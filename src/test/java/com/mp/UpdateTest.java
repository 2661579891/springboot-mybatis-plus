package com.mp;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.mp.dao.IUserMapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class UpdateTest {
    @Autowired
    private IUserMapper userMapper;

    @Test
    public void updateById() {
        User user = new User();
        user.setId(8);
        user.setUserName("wangjiushi");
        int res = userMapper.updateById(user);
        System.out.println("受影响行数："+res);
    }

    @Test
    public void updateWrapper() {
        //相当于： update user set user_age = 26 where id = 8
        User user = new User();
        user.setUserAge(26);
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.eq("id", 8);
        int res = userMapper.update(user, uw);
        System.out.println("受影响行数："+res);
    }

    @Test
    public void updateWrapper2() {
        //相当于： update user set user_age = 26 where id = 8
        UpdateWrapper<User> uw = new UpdateWrapper<>();
        uw.set("user_age", 30).eq("id", 8);
        int res = userMapper.update(null, uw);
        System.out.println("受影响行数："+res);
    }

    @Test
    public void updateLambda(){
        LambdaUpdateWrapper<User> luw = Wrappers.<User>lambdaUpdate();
        luw.set(User::getUserAge, 50).eq(User::getId, 8);
        int res = userMapper.update(null, luw);
        System.out.println("受影响行数："+res);
    }

    @Test
    public void lambdaUpdateChainWrappers(){
        boolean res = new LambdaUpdateChainWrapper<>(userMapper)
                .eq(User::getId, 8).set(User::getUserAge, 100).update();
        System.out.println("受影响行数："+res);
    }
}

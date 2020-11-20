package com.mp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.mp.dao.IUserMapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jws.soap.SOAPBinding;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class LambdaQuerySelectTest {
    @Autowired
    private IUserMapper userMapper;

    @Test
    public void lambdaSelect1() {
        //创建lambda表达式方式
//        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
//        LambdaQueryWrapper<User> lambda1 = new LambdaQueryWrapper<>();
//        LambdaQueryWrapper<User> lambda2 = Wrappers.<User>lambdaQuery();
        /**
         * 1、查询名字中带雨的
         */
        LambdaQueryWrapper<User> lambda = Wrappers.<User>lambdaQuery();
        lambda.like(User::getUserRealName, "雨").lt(User::getUserAge, 40);
        List<User> users = userMapper.selectList(lambda);
        users.forEach(System.out::println);
    }

    @Test
    public void LambdaQueryChainWrapper() {
        List<User> users = new LambdaQueryChainWrapper<User>(userMapper).like(User::getUserRealName, "雨")
                .lt(User::getUserAge, 40).list();
        users.forEach(System.out::println);
    }
}

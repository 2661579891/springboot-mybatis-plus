package com.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mp.dao.IUserMapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class DeleteTest {
    @Autowired
    private IUserMapper userMapper;

    @Test
    public void deleteById(){
        int res = userMapper.deleteById(18);
        System.out.println("受影响行数："+res);
    }

    @Test
    public void deleteByMap(){
        //多多条件匹配删除
        Map<String, Object> map = new HashMap<>();
        map.put("id", 21);
        map.put("user_real_name","trewt");
        int res = userMapper.deleteByMap(map);
        System.out.println("受影响行数："+res);
    }

    @Test
    public void deleteBatchIds(){
        //通过id删除多个值
        int res = userMapper.deleteBatchIds(Arrays.asList(19, 21));
        System.out.println("受影响行数："+res);
    }

    @Test
    public void lambdaWrapperDelete(){
        //使用lambda条件构造器进行删除
        LambdaQueryWrapper<User> lqw = Wrappers.<User>lambdaQuery();
        lqw.eq(User::getId,20);
        int res = userMapper.delete(lqw);
        System.out.println("受影响行数："+res);
    }
}

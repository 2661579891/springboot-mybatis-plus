package com.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mp.dao.IUserMapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SelectPage {
    @Autowired
    private IUserMapper userMapper;

    @Test
    public void selectPage() {
        QueryWrapper<User> query = new QueryWrapper<User>();
        query.ge("user_age", 20);
        Page<User> page = new Page<>(1, 2);
        IPage<User> iPage = userMapper.selectPage(page, query);
        System.out.println("总页数："+iPage.getPages());
        System.out.println("总记录数："+iPage.getTotal());
        List<User> users = iPage.getRecords();
        users.forEach(System.out::println);
    }

    @Test
    public void selectQueryMap(){
        QueryWrapper<User> query = new QueryWrapper<>();
        query.ge("user_age", 20);
        Page<User> page = new Page<>(1, 3);
        IPage<Map<String, Object>> iPage = userMapper.selectMapsPage(page, query);
        System.out.println("总页数："+iPage.getPages());
        System.out.println("总记录数："+iPage.getTotal());
        List<Map<String, Object>> users = iPage.getRecords();
        users.forEach(System.out::println);
    }
}

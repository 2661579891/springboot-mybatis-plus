package com.mp;

import com.mp.dao.IUserMapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseSelectTest {
    ////通过表的主键进行查询 返回一条或0条与主键对应的值   selectById
    ////相当于  select * from 表 where id =
    @Autowired
    private IUserMapper userMapper;

    /**
     * //通过表的主键进行查询 返回一条或0条与主键对应的值   selectById
     * //相当于  select * from 表 where id =
     */
    @Test
    public void selectById(){
        User user = userMapper.selectById(2);
        System.out.println(user);
    }

    /**
     * //通过传入多个主键值进行查询， 注意：传入的数组不可以位null或者为空  selectBatchIds
     * //相当于  select * from 表 where id in (...)
     */
    @Test
    public void selectBatchIds(){
        List<Integer> ids = Arrays.asList(1, 4, 5, 6);
        List<User> users = userMapper.selectBatchIds(ids);
        users.forEach(System.out::println);
    }

    /**
     *多条件查询 selectByMap
     *相当于  select * from 表 where name = .. and age = ..
     * ***********************************************************
     * 注意：                                                     *
     * 此时的map的K,V键值对中，K对应的是数据表的列名而不是实体类的属性名*
     * ************************************************************
     */
    @Test
    public void selectByMap(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_name", "zhangsan");
        map.put("user_age", 26);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }
}


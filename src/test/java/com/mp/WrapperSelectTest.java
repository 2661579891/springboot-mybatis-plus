package com.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mp.dao.IUserMapper;
import com.mp.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class WrapperSelectTest {
    @Autowired
    private IUserMapper userMapper;

    /**
     *  查询数据表中名字带雨且年龄小于40的人
     *  name like '%雨%' and age < 40
     ***************************************************
     * 注意：                                           *
     * 这里的column参数也是数据表中列名，而不是实体类属性名。*
     ****************************************************
     */
    @Test
    public void queryWrapper1(){
        QueryWrapper<User> query = Wrappers.<User>query();
        query.like("user_real_name", "雨").lt("user_age", 40);
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     *   查询名字中带雨并且年龄大于等于20且小于等于40并且Email不为空
     *   name like '%雨%' and age >= 20 and age <= 40 and email is not null
     ***************************************************
     * 注意：                                           *
     * 这里的column参数也是数据表中列名，而不是实体类属性名。*
     ****************************************************
     */
    @Test
    public void queryWrapper2(){
        QueryWrapper<User> query = new QueryWrapper<>();
        query.like("user_real_name", "雨").between("user_age",20, 40)
                .isNotNull("user_email");
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }


    /**
     *  查询名字为王姓，或者年龄大于等于25，按照年龄降序排序，年龄相同按照id升序排列
     *   name like '王%' or age >= 25 order by age desc, id asc
     ***************************************************
     * 注意：                                           *
     * 这里的column参数也是数据表中列名，而不是实体类属性名。*
     ****************************************************
     */
    @Test
    public void queryWrapper3(){
        QueryWrapper<User> query = new QueryWrapper<>();
        query.likeRight("user_real_name", "王")
                .ge("user_age", 25)
                .orderByDesc("user_age").orderByAsc("id");
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     *  名字为王姓并且（年龄小于40或者邮箱不为空）
     *  name like '王%' and (age < 40 or email is not null)
     */
    @Test
    public void queryWrapper4(){
        QueryWrapper<User> query = new QueryWrapper<>();
        query.likeRight("user_real_name", "王")
                .and(qw -> qw.lt("user_age", 40).isNotNull("user_email"));
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     *   名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）
     *   name like '王%' or (age > 20 and age < 40 and email is not null)
     */
    @Test
    public void queryWrapper5() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.likeRight("user_real_name", "王")
                .or(qw -> qw.lt("user_age", 40)
                        .gt("user_age", 20)
                        .isNotNull("user_email"));
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     *   (年龄小于40或者邮箱不为空)并且名字为王姓
     *    (age < 40 or email is not null) and name like '王%'
     */
    @Test
    public void queryWrapper6() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.nested(qw ->qw.lt("user_age", 40).or().isNotNull("user_email"))
                .likeRight("user_real_name", "王");
//        query.likeRight("user_real_name", "王")
//                .and(qw ->qw.lt("user_age", 40).or().isNotNull("user_email"));
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     *  年龄为30，31，34，35
     *   age in (30，31，34，35)
     */
    @Test
    public void queryWrapper7() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.in("id", Arrays.asList(1,2,4,5));
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     *  只返回满足条件的一条语句
     *  limit 1
     */
    @Test
    public void queryWrapper8() {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.in("id", Arrays.asList(1,2,4,5)).last("limit 1,2");
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }

    /**
     * 查询年龄大于20，小于40并且名字中带雨的人
     */
    @Test
    public void queryWrapper9() {
        QueryWrapper<User> query = new QueryWrapper<>();
//        query.gt("user_age", 20).lt("user_age", 40)
//                .like("user_real_name", "雨");
        query.gt("user_age", 20).lt("user_age", 40)
                .inSql("id", "select id from user where user_real_name like '%雨%'");
        List<User> users = userMapper.selectList(query);
        users.forEach(System.out::println);
    }
}

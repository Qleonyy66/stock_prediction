package com.nowcoder.community;


import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;


@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectUser(){
        System.out.println(userMapper.selectById(101));
        User usertoselect= userMapper.selectById(101);
        System.out.println(usertoselect);

        usertoselect = userMapper.selectByName("liubei");
        System.out.println(usertoselect);

        usertoselect = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(usertoselect);
    }


    @Test
    public void updataUser(){
        System.out.println(userMapper.updateStatus(170,1));
        int rows = userMapper.updateStatus(170,1);
        System.out.println(rows);

        rows= userMapper.updateHeader(170,"http://www.nowcoder.com/103.png");
        System.out.println(rows);

        rows= userMapper.updatePassword(170,"weishenmehuiocharunehello");
        System.out.println(rows);
    }
    @Test
    public void testInsertUser(){
        User usertoinsert=new User();
        usertoinsert.setUsername("test");
        usertoinsert.setPassword("123456");
        usertoinsert.setSalt("abc");
        usertoinsert.setEmail("test@qq.com");
        usertoinsert.setHeaderUrl("http://www.nowcoder.com/101.png");
        usertoinsert.setCreateTime(new Date());

        int rows11 = userMapper.insertUser(usertoinsert);
        System.out.println(rows11);
        System.out.println(usertoinsert.getId());

    }


}

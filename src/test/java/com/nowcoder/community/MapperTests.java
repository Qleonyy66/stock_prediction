package com.nowcoder.community;


import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import java.util.Date;
import java.util.List;


@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void testSelectUser() {
        System.out.println(userMapper.selectById(101));
        User usertoselect = userMapper.selectById(101);
        System.out.println(usertoselect);

        usertoselect = userMapper.selectByName("liubei");
        System.out.println(usertoselect);

        usertoselect = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(usertoselect);
    }

    @Test
    public void testInsertUser() {
        User usertoinsert = new User();
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

    @Test
    public void updataUser() {
        System.out.println(userMapper.updateStatus(150, 1));
        int rows = userMapper.updateStatus(150, 1);
        System.out.println(rows);

        rows = userMapper.updateHeader(150, "http://www.nowcoder.com/103.png");
        System.out.println(rows);

        rows = userMapper.updatePassword(150, "willo");
        System.out.println(rows);
    }


    @Test
    public void testSelectPosts(){
        List<DiscussPost> list=discussPostMapper.selectDiscussPosts(149,0,10);
        for (DiscussPost post:list){
            System.out.println(post);
        }

        int rows= discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }

    @Test
    public void testInsertLoginTicket(){
        LoginTicket loginTicket=new LoginTicket();
        loginTicket.setUserId(101);
        loginTicket.setTicket("abc");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 10*60*1000) );

        loginTicketMapper.insertLoginTicket(loginTicket);
    }

    @Test
    public void testSelectLoginTicket(){
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abc");
        System.out.println(loginTicket);

        loginTicketMapper.updateStatus("abc",1);
        loginTicket = loginTicketMapper.selectByTicket("abc");

        System.out.println(loginTicket);
    }

}

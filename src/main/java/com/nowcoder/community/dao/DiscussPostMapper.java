package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    List<DiscussPost> selectDiscussPosts(int userId , int offset, int limit);

    //@param 如果方法只有一个参数 并且在《if》里使用 必须使用别名 （动态sql时用）
    int selectDiscussPostRows(@Param("userId") int userId);



}

package com.cn;

import com.cn.mapper.BlogMapper;
import com.cn.mebatis.Configuration;
import com.cn.mebatis.SqlSession;
import com.cn.pojo.Blog;

public class MebatisTest {

    public static void main(String[] args) {

        SqlSession sqlSession = new SqlSession(new Configuration());
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectById(1);
        System.out.println(blog);
    }
}

package com.cn;

import com.cn.interceptor.LogInteceptor;
import com.cn.mapper.BlogMapper;
import com.cn.mebatis.Configuration;
import com.cn.mebatis.SqlSession;
import com.cn.pojo.Blog;

public class MebatisTest {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.addInteceptor(new LogInteceptor());
        SqlSession sqlSession = new SqlSession(configuration);
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectById(1);
        System.out.println(blog);
    }
}

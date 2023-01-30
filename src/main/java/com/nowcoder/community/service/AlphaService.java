package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")
public class AlphaService {

    @Autowired
    private AlphaDao alphaDao;


    public AlphaService(){
        System.out.println("实例化 constructor");
    }

    @PostConstruct
    public void init(){
        System.out.println("initial service");
    }


    @PreDestroy
    public void destroy(){
        System.out.println("destroy");
    }

    public String find(){
        return alphaDao.select();
    }
}

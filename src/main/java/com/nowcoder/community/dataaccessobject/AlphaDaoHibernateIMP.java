package com.nowcoder.community.dataaccessobject;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository("alphaHibernate")

public class AlphaDaoHibernateIMP implements AlphaDao{
    @Override
    public String select() {
        return "Hibernate";
    }
}

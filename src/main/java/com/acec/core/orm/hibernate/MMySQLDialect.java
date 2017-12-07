package com.acec.core.orm.hibernate;

import java.sql.Types;
import org.hibernate.Hibernate;
import org.hibernate.dialect.MySQLDialect;

public class MMySQLDialect extends MySQLDialect {
    public MMySQLDialect () {
        super();
        registerHibernateType(Types.DECIMAL, Hibernate.BIG_INTEGER.getName());
    }
}

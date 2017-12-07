package com.acec.wgt.models.chg;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.hibernate.HibernateDao;

@Repository
public class AuditDAO extends HibernateDao<AuditEO, Integer>{

	 
	public void updateaudit(int id){
	
		createQuery("update AuditEO set auditStatus='审核完成' where id="+id).executeUpdate();
	}
}

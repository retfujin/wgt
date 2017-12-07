package com.acec.wgt.models.chg;

import java.util.List;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;

@Repository
public class ManagerDAO extends HibernateEntityDao<Object> {

		
	
	public List getYearReportList(String hql){
		return getSession().createSQLQuery(hql).list();
	}
	
	public List getYearEdificeReportList(String hql){
		return getSession().createSQLQuery(hql).list();
	}
	
	public String getResultString(String hql){
	    
	    String query=getSession().createSQLQuery(hql).toString();
	    
	    return null;
	}

	public PaginatorTag getPage(int no , int i,String hql){
		return pagedQuery(hql, no, i);
	}
	
	public PaginatorTag getPaginatorTag(int no , int i,String hql){
		return pagedPayQuery(hql, no, i);
	}
}

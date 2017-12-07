package com.acec.commons.persist;   
  

import java.io.Serializable;   
import java.sql.SQLException;   
import java.util.ArrayList;   
import java.util.Iterator;   
import java.util.List;   
  
import org.apache.commons.beanutils.PropertyUtils;   
import org.hibernate.Hibernate;   
import org.hibernate.HibernateException;   
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.ScrollableResults;   
import org.hibernate.Session;   
import org.springframework.orm.hibernate3.HibernateCallback;   
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;   

import com.acec.commons.util.PaginatorTag;

  
public class HibernatePersistenceService extends HibernateDaoSupport implements  
        PersistenceService {   
       
    @SuppressWarnings("unchecked")   
    public <T> List<T> findAll(Class<T> entityClass) {
    	List l = getHibernateTemplate().loadAll(entityClass);
        getHibernateTemplate().clear();
    	return l;   
    }   
    
    
    
    
    @SuppressWarnings("unchecked")   
    public <T> List<T> findAll(final Class<T> entityClass, final int pageIndex, final int pageSize) {   
        return getHibernateTemplate().executeFind(new HibernateCallback() {   
            public Object doInHibernate(Session session) throws HibernateException, SQLException {   
                ScrollableResults rs = session.createCriteria(entityClass).scroll();   
                List list = new ArrayList();   
                if (pageIndex > 0) {
                    rs.scroll(pageIndex * pageSize);   
                }   
                for (int i = 0; i < pageSize && rs.next(); ++i) {   
                    list.add(rs.get(0));   
                }
                session.close();
                return list;
            }   
        });   
    }   
  
    
    public List find(String hql, Object[] values) {   
        if (values == null) {
        	List l = getHibernateTemplate().find(hql);
        	getHibernateTemplate().clear();
            return l;   
        } else {
        	List l = getHibernateTemplate().find(hql, values);
        	getHibernateTemplate().clear();
            return l;
        }   
    }
    
    public List find(final String hql, Object[] values,final String[] properties) {
    	
    	return getHibernateTemplate().executeFind(new HibernateCallback() {   
            public List doInHibernate(Session session) throws HibernateException, SQLException {   
                List ls = session.createQuery(hql).list();   
                Hibernate.initialize(ls);
                
                for(int i=0;i<ls.size();i++){
                	if (properties != null) {
                		Object o= ls.get(i);
                        for (String property : properties) {   
                            try {   
                                Hibernate.initialize(PropertyUtils.getProperty(o,   
                                        property));   
                            } catch (Exception e) {   
                                throw new RuntimeException(e);   
                            }   
                        }   
                    }   
                }
                session.close();
                return ls;   
            }   
               
        });    
    }
  
    @SuppressWarnings("unchecked")   
    public List find(String hql, Object[] values, int pageIndex, int pageSize) {   
        Iterator iter = null;   
        if (values == null) {   
            iter = getHibernateTemplate().iterate(hql);   
        } else {   
            iter = getHibernateTemplate().iterate(hql, values);   
        }   
          
        for (int i = 0; i < pageIndex * pageSize && iter.hasNext(); ++i) {   
            iter.next();   
        }   
           
        List list = new ArrayList();   
        for (int i = 0; i < pageSize && iter.hasNext(); ++i) {   
            list.add(iter.next());   
        }   
           
        return list;   
    }   
       
    public Object execute(final String hql) {   
        return getHibernateTemplate().execute(new HibernateCallback() {   
            public Object doInHibernate(Session session) throws HibernateException, SQLException {   
                int ei = session.createQuery(hql).executeUpdate();
                session.close();
                return ei;   
            }   
        });   
    }
    
    
    @SuppressWarnings("unchecked")   
    
    
    
    public <T> T get(final Class<T> entityClass, final Serializable id) {   
        return get(entityClass, id, null);   
    }
    
    
   
    @SuppressWarnings("unchecked")   
    public <T> T get(final Class<T> entityClass, final Serializable id, final String[] properties) {   
        return (T) getHibernateTemplate().execute(new HibernateCallback() {   
            public Object doInHibernate(Session session) throws HibernateException, SQLException {   
                Object o = session.get(entityClass, id);   
                Hibernate.initialize(o);   
                if (properties != null) {
                    for (String property : properties) {   
                        try {   
                            Hibernate.initialize(PropertyUtils.getProperty(o,   
                                    property));   
                        } catch (Exception e) {   
                            throw new RuntimeException(e);   
                        }   
                    }   
                }
                session.close();
                return (T) o;   
            }   
               
        });   
    }   
  
    public <T> T update(T entity) {   
        getHibernateTemplate().saveOrUpdate(entity);   
        return entity;   
    }   
    
    public <T> T save(T entity) {   
        getHibernateTemplate().save(entity);
        getHibernateTemplate().clear();
        return entity;   
    }   

    
    public void delete(Object entity) {   
        getHibernateTemplate().delete(entity);   
    }
    
    //自己的分页
    public List getOfficeBySearchCriteria(final String hsql, final PaginatorTag pt) {    
		Session session=getSession();         
		List offices=new ArrayList();
		Query query=null;    

//		//取总记录数
		String countSql = getCountSql(hsql);
		Long count = (Long)session.createQuery(countSql).uniqueResult();
		int total_count = count.intValue();
		
		pt.setTotalPut(total_count);
		int pageNo = pt.getCurrentPage();
		int pageSize=pt.getMaxPerPage();
		
		//判断是否超出范围
		if(total_count<pageNo)pageNo=total_count;    
		if(pageNo<1)pageNo=1;
		pt.setCurrentPage(pageNo);
		
		
		int start=(pageNo-1)*pageSize;    
		int rowNum=pageSize;
		
		
		query=session.createQuery(hsql); 
		query.setFirstResult(start);    
		query.setMaxResults(rowNum);    
		offices = query.list(); 
		session.clear();
		session.close();
		return offices;    
	}
    
    
    //自己的分页
    public <T>List getListByPage(final String hsql, final PaginatorTag pt,Class<T> entityClass) {    
		Session session=getSession();         
		List offices=new ArrayList();
		SQLQuery query=null;    

//		//取总记录数
		String countSql = getCountSql(hsql);
		SQLQuery sq= session.createSQLQuery(countSql);
		List ls = sq.list();
		Object o = ls.get(0);
		int total_count = ((java.math.BigInteger)o).intValue();
		
		pt.setTotalPut(total_count);
		int pageNo = pt.getCurrentPage();
		int pageSize=pt.getMaxPerPage();
		
		//判断是否超出范围
		if(total_count<pageNo)pageNo=total_count;    
		if(pageNo<1)pageNo=1;
		pt.setCurrentPage(pageNo);
		
		
		int start=(pageNo-1)*pageSize;    
		int rowNum=pageSize;
		
		
		query=session.createSQLQuery(hsql); 
		query.setFirstResult(start);    
		query.setMaxResults(rowNum);
		if(entityClass!=null){
			offices = query.addEntity(entityClass).list();  
		}
		else
			offices = query.list();
		session.clear();
		session.close();
		return offices;    
	}
	
	protected String getCountSql(String originalHql) { 
		String countSql="";
		int k = originalHql.toLowerCase().indexOf("order");
		
		//去除order by
		if(k>0)
		{
			originalHql = originalHql.substring(0,k);
		}
		//去除select XXX 
		k = originalHql.toLowerCase().indexOf("from");
		if(k>0)
		{
			originalHql = originalHql.substring(k);
		}
		
		countSql="select count(*) "+originalHql;
		
	//	QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(originalHql, originalHql, 
	//	Collections.EMPTY_MAP, (org.hibernate.engine.SessionFactoryImplementor)getSessionFactory());

	//	queryTranslator.compile(Collections.EMPTY_MAP, false);

	//	countSql = "select count(*) from (" + queryTranslator.getSQLString() + ") tmp_count_t";
		return  countSql;
	}
	/**
	 * 
	 * @param <T>
	 * @param sql  native Sql 原始sql
	 * @param entityClass 实体类，结果集每条记录对应一个实体类,为null,则
	 * @return List集合 保存为entityClass实体类
	 */
	public <T> List findNativeSQL(String sql,final Class<T> entityClass){
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		List list;
		if(entityClass!=null){
			list = session.createSQLQuery(sql).addEntity(entityClass).list();
		}
		else
			list = session.createSQLQuery(sql).list();
		session.clear();
		session.close();
		return list;
	    
	}

    
}  
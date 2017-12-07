package com.acec.commons.persist;   
  
import java.io.Serializable;   
import java.util.List;   
  
public interface PersistenceService {   
       
    public <T> List<T> findAll(Class<T> entityClass);   
       
    public <T> List<T> findAll(Class<T> entityClass, int pageIndex, int pageSize);   
       
    public Object execute(String hql);   
       
    public List find(String hql, Object[] values);   
       
    public List find(String hql, Object[] values, int pageIndex, int pageSize);   
       
    public <T> T get(Class<T> entityClass, Serializable id);   
       
    public <T> T get(Class<T> entityClass, Serializable id, String[] properties);   
  
    public <T> T update(T entity);   
       
    public void delete(Object entity);   
}  

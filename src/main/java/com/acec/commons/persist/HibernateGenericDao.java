package com.acec.commons.persist;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import com.acec.commons.util.PaginatorTag;
import com.acec.commons.util.BeanUtils;

/**
 * Hibernate Dao的泛型基类.
 * <p/>
 * 继承于Spring的<code>HibernateDaoSupport</code>,提供分页函数和若干便捷查询方法，并对返回值作了泛型类型转换.
 *
 * @author 
 * @see HibernateDaoSupport
 * @see HibernateEntityDao
 */
@SuppressWarnings("unchecked")
public class HibernateGenericDao extends HibernateDaoSupport {
	/**
	 * 根据ID获取对象.
	 */
	protected <T> T get(Class<T> entityClass, Serializable id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	/**
	 * 获取全部对象.
	 */
	protected <T> List<T> getAll(Class<T> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}

	/**
	 * 获取全部对象,带排序字段与升降序参数.
	 */
	protected <T> List<T> getAll(Class<T> entityClass, String orderBy, boolean isAsc) {
		Assert.hasText(orderBy);
		if (isAsc)
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(Order.asc(orderBy)));
		else
			return getHibernateTemplate().findByCriteria(
					DetachedCriteria.forClass(entityClass).addOrder(Order.desc(orderBy)));
	}

	/**
	 * 保存对象.
	 */
	public void save(Object  entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}
	
	
	/**
	 * 保存对象.  将readOnly的transaction就由Flush.NEVER转为Flush.AUTO
	 */
	public void save1(Object o) {
		getHibernateTemplate().saveOrUpdate(o);
		
	}
	
	
	
	

	/**
	 * 删除对象.
	 */
	public void remove(Object o) {
		getHibernateTemplate().delete(o);
	}

	/**
	 * 根据ID删除对象.
	 */
	protected <T> void removeById(Class<T> entityClass, Serializable id) {
		remove(get(entityClass, id));
	}

	protected void flush() {
		getHibernateTemplate().flush();
	}

	protected void clear() {
		getHibernateTemplate().clear();
	}

	/**
	 * 创建Query对象. 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
	 * 留意可以连续设置,如下：
	 * <pre>
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 调用方式如下：
	 * <pre>
	 *        dao.createQuery(hql)
	 *        dao.createQuery(hql,arg0);
	 *        dao.createQuery(hql,arg0,arg1);
	 *        dao.createQuery(hql,new Object[arg0,arg1,arg2])
	 * </pre>
	 *
	 * @param values 可变参数.
	 */
	protected Query createQuery(String hql, Object... values) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		if(values!=null)
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		return query;
	}

	/**
	 * 创建Criteria对象.
	 *
	 * @param criterions 可变的Restrictions条件列表,见{@link #createQuery(String,Object...)}
	 */
	protected <T> Criteria createCriteria(Class<T> entityClass, Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		if(criterions!=null)
			for (Criterion c : criterions) {
				criteria.add(c);
			}
		return criteria;
	}

	/**
	 * 创建Criteria对象，带排序字段与升降序字段.
	 *
	 * @see #createCriteria(Class,Criterion[])
	 */
	protected <T> Criteria createCriteria(Class<T> entityClass, String orderBy, boolean isAsc, Criterion... criterions) {
		Assert.hasText(orderBy);

		Criteria criteria = createCriteria(entityClass, criterions);

		if (isAsc)
			criteria.addOrder(Order.asc(orderBy));
		else
			criteria.addOrder(Order.desc(orderBy));

		return criteria;
	}

	/**
	 * 根据hql查询,直接使用HibernateTemplate的find函数.
	 * 
	 * @param values 可变参数,见{@link #createQuery(String,Object...)}
	 */
	@SuppressWarnings("unchecked")
	public List find(String hql, Object... values) {
		Assert.hasText(hql);
		if(values!=null)
			return getHibernateTemplate().find(hql, values);
		else
			return getHibernateTemplate().find(hql);
	}

	/**
	 * 根据属性名和属性值查询对象.
	 *
	 * @return 符合条件的对象列表
	 */
	protected <T> List<T> findBy(Class<T> entityClass, String propertyName, Object value) {
		Assert.hasText(propertyName);
		return createCriteria(entityClass, Restrictions.eq(propertyName, value)).list();
	}

	/**
	 * 根据属性名和属性值查询对象,带排序参数.
	 */
	protected <T> List<T> findBy(Class<T> entityClass, String propertyName, Object value, String orderBy, boolean isAsc) {
		Assert.hasText(propertyName);
		Assert.hasText(orderBy);
		return createCriteria(entityClass, orderBy, isAsc, Restrictions.eq(propertyName, value)).list();
	}

	/**
	 * 根据属性名和属性值查询唯一对象.
	 *
	 * @return 符合条件的唯一对象 or null if not found.
	 */
	protected <T> T findUniqueBy(Class<T> entityClass, String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(entityClass, Restrictions.eq(propertyName, value)).uniqueResult();
	}

	/**
	 * 分页查询函数，使用hql.
	 *
	 * @param pageNo 页号,从1开始.
	 */
	protected PaginatorTag pagedQuery(String hql, int pageNo, int pageSize, Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count(*) " + removeSelect(removeOrders(hql));
		List countlist = getHibernateTemplate().find(countQueryString, values);
		long totalCount = (Long) countlist.get(0);

		if (totalCount < 1)
			return new PaginatorTag();
		
		// 实际查询返回分页对象
		int startIndex = PaginatorTag.getStartOfPage(pageNo, pageSize);
		Query query = createQuery(hql, values);
		List list = new ArrayList();
		try{
		list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("数据库查询异常,请稍侯重试!");
		}
		return new PaginatorTag(pageNo, totalCount, pageSize, list);
	}
	
	
	/**
	 * 专用查询条件
	 * @param hql
	 * @param pageNo
	 * @param pageSize
	 * @param values
	 * @return
	 */
	protected PaginatorTag pagedPayQuery(String hql, int pageNo, int pageSize, Object... values) {
		Assert.hasText(hql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count(*) " + removeSelect(removeOrders(hql));
		List countlist = getHibernateTemplate().find(countQueryString, values);
		long totalCount = Long.parseLong(String.valueOf(countlist.size()));

		if (totalCount < 1)
			return new PaginatorTag();
		
		
		// 实际查询返回分页对象
		int startIndex = PaginatorTag.getStartOfPage(pageNo, pageSize);
		Query query = createQuery(hql, values);
		List list = new ArrayList();
		try{
		list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("数据库查询异常,请稍侯重试!");
		}
		return new PaginatorTag(pageNo, totalCount, pageSize, list);
	}
	
	/**
	 * 分页查询函数，使用原始sql.
	 *
	 * @param pageNo 页号,从1开始.
	 */
	protected PaginatorTag pagedQueryNative(String sql, int pageNo, int pageSize) {
		Assert.hasText(sql);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		// Count查询
		String countQueryString = " select count(*) " + removeSelect(removeOrders(sql));
		SQLQuery sq= getSession().createSQLQuery(countQueryString);
		List ls = sq.list();
		Object _object = ls.get(0);
		int totalCount=0;
		if(_object instanceof Integer)
			totalCount = (Integer)_object;
		else if(_object instanceof BigInteger)
			totalCount = ((BigInteger)_object).intValue();
			
		if (totalCount < 1)
			return new PaginatorTag();
		
		
		// 实际查询返回分页对象
		int startIndex = PaginatorTag.getStartOfPage(pageNo, pageSize);
		SQLQuery query=getSession().createSQLQuery(sql); 
		List list = new ArrayList();
		try{
		list = query.setFirstResult(startIndex).setMaxResults(pageSize).list();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("数据库查询异常,请稍侯重试!");
		}
		return new PaginatorTag(pageNo, totalCount, pageSize, list);
	}
	
	
	
	/**
	 * 分页查询函数，使用已设好查询条件与排序的<code>Criteria</code>.
	 *
	 * @param pageNo 页号,从1开始.
	 * @return 含总记录数和当前页数据的Page对象.
	 */
	protected PaginatorTag pagedQuery(Criteria criteria, int pageNo, int pageSize) {
		Assert.notNull(criteria);
		Assert.isTrue(pageNo >= 1, "pageNo should start from 1");
		CriteriaImpl impl = (CriteriaImpl) criteria;

		// 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
		Projection projection = impl.getProjection();
		List<CriteriaImpl.OrderEntry> orderEntries;
		try {
			orderEntries = (List) BeanUtils.forceGetProperty(impl, "orderEntries");
			BeanUtils.forceSetProperty(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		// 执行查询
		long totalCount = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();

		// 将之前的Projection和OrderBy条件重新设回去
		criteria.setProjection(projection);
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}

		try {
			BeanUtils.forceSetProperty(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			throw new InternalError(" Runtime Exception impossibility throw ");
		}

		// 返回分页对象
		if (totalCount < 1)
			return new PaginatorTag();

		int startIndex = PaginatorTag.getStartOfPage(pageNo, pageSize);
		List list = new ArrayList();
		try{
		list = criteria.setFirstResult(startIndex).setMaxResults(pageSize).list();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException("数据库查询异常,请稍侯重试!");
		}
		return new PaginatorTag(startIndex, totalCount, pageSize, list);
	}

	/**
	 * 分页查询函数，根据entityClass和查询条件参数创建默认的<code>Criteria</code>.
	 *
	 * @param pageNo 页号,从1开始.
	 * @return 含总记录数和当前页数据的Page对象.
	 */
	protected PaginatorTag pagedQuery(Class entityClass, int pageNo, int pageSize, Criterion... criterions) {
		Criteria criteria = createCriteria(entityClass, criterions);
		return pagedQuery(criteria, pageNo, pageSize);
	}

	/**
	 * 分页查询函数，根据entityClass和查询条件参数,排序参数创建默认的<code>Criteria</code>.
	 *
	 * @param pageNo 页号,从1开始.
	 * @return 含总记录数和当前页数据的Page对象.
	 */
	protected PaginatorTag pagedQuery(Class entityClass, int pageNo, int pageSize, String orderBy, boolean isAsc,
						   Criterion... criterions) {
		Criteria criteria = createCriteria(entityClass, orderBy, isAsc, criterions);
		return pagedQuery(criteria, pageNo, pageSize);
	}

	/**
	 * 判断对象某些属性的值在数据库中是否唯一.
	 *
	 * @param uniquePropertyNames 在POJO里不能重复的属性列表,以逗号分割 如"name,loginid,password"
	 */
	protected <T> boolean isUnique(Class<T> entityClass, Object entity, String uniquePropertyNames) {
		Assert.hasText(uniquePropertyNames);
		Criteria criteria = createCriteria(entityClass).setProjection(Projections.rowCount());
		String[] nameList = uniquePropertyNames.split(",");
		try {
			// 循环加入唯一列
			for (String name : nameList) {
				criteria.add(Restrictions.eq(name, PropertyUtils.getProperty(entity, name)));
			}

			// 以下代码为了如果是update的情况,排除entity自身.

			String idName = getIdName(entityClass);

			// 取得entity的主键值
			Serializable id = getId(entityClass, entity);

			// 如果id!=null,说明对象已存在,该操作为update,加入排除自身的判断
			if (id != null)
				criteria.add(Restrictions.not(Restrictions.eq(idName, id)));
		} catch (Exception e) {
			ReflectionUtils.handleReflectionException(e);
		}
		return (Integer) criteria.uniqueResult() == 0;
	}

	/**
	 * 取得对象的主键值,辅助函数.
	 */
	public Serializable getId(Class entityClass, Object entity) throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		Assert.notNull(entity);
		Assert.notNull(entityClass);
		return (Serializable) PropertyUtils.getProperty(entity, getIdName(entityClass));
	}

	/**
	 * 取得对象的主键名,辅助函数.
	 */
	public String getIdName(Class clazz) {
		Assert.notNull(clazz);
		ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
		Assert.notNull(meta, "Class " + clazz + " not define in hibernate session factory.");
		String idName = meta.getIdentifierPropertyName();
		Assert.hasText(idName, clazz.getSimpleName() + " has no identifier property define.");
		return idName;
	}

	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
	 *
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 *
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	private static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	/**
	 * 去除hql的groupby子句 用于pagedQuery
	 * @param hql
	 * @return
	 */
	private static String removeGroupBy(String hql){
		Assert.hasText(hql);
		Pattern p = Pattern.compile("group\\s*by[\\w|\\W|\\s|\\S]*" , Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
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
	public List<String[]> findNativeSQL1(final String sql){
		
		return getHibernateTemplate().executeFind(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Connection conn = session.connection();
				Statement statement = conn.createStatement();
	        	List list = new ArrayList();
	        	ResultSet rs=statement.executeQuery(sql);
	        	int length=rs.getMetaData().getColumnCount();
	        	while(rs.next()){
					String[] strs=new String[length];
					for(int j=0;j<length;j++){
						strs[j]=rs.getString(j+1);
						if(strs[j]!=null)
							strs[j]=strs[j].trim();
					}
					list.add(strs);
				}
	        	session.disconnect();
	        	session.clear();
	    		session.close();
				return list;
			}
		});
	}
	
	protected int updateNativeSQL(String sql){
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		
		int iRet = session.createSQLQuery(sql).executeUpdate();
		
		session.clear();
		session.close();
		return iRet;
	    
	}
}
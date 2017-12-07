package com.acec.wgt.service.sys;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.wgt.models.sys.SysBackUpEO;

//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class BackUpManager {
	
	private HibernateDao<SysBackUpEO, Integer> dao;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		
		dao = new HibernateDao<SysBackUpEO, Integer>(sessionFactory, SysBackUpEO.class);
	}
	
	public Page getBackUpListByPage(Page page) {
		
		return dao.find(page,"from SysBackUpEO order by id");
		
		
	}

	public void remove(SysBackUpEO entity) {
		dao.delete(entity);
		
	}

	public void save(SysBackUpEO entity) {
		dao.save(entity);
		
	}
	


}

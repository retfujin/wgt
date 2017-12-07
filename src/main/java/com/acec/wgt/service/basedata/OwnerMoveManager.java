package com.acec.wgt.service.basedata;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.OwnerMoveDAO;
import com.acec.wgt.models.baseData.OwnerMoveEO;

/**
 * 业务层
 * 
 * @author Administrator
 * 
 */
// Spring Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class OwnerMoveManager extends HibernateEntityDao<OwnerMoveEO> {

	@Autowired
	private OwnerMoveDAO dao;

	public void save(OwnerMoveEO entity) {
		dao.save(entity);
	}

	public OwnerMoveEO get(Integer id) {
		return dao.get(id);
	}

	private Map session;
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	public void setSession(Map session) {

		this.session = session;
	}

	public PaginatorTag getOwnermoveListByPage(int no, int i, String where) {
		String areaIds = (String) Struts2Utils.getSession().getAttribute(
				"areaIds");
		where +=" and areaId in ("+areaIds+") ";
		return pagedQuery("from OwnerMoveEO where 1=1 " + where, no, i);
	}

	/**
	 * 得到住户搬出/入记录
	 * 
	 * @param page
	 * @param where
	 * @return
	 */
	public Page getList(Page page, String where) {
		String areaIds = (String) Struts2Utils.getSession().getAttribute(
				"areaIds");
		where += " and areaId in (" + areaIds + ") ";
		return dao.getOwnerMover(page, where);
	}

	public void del(int id) {
		dao.delete(id);
	}
}
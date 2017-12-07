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
import com.acec.wgt.models.baseData.OwnerDecorateDAO;
import com.acec.wgt.models.baseData.OwnerDecorateEO;

/**
 * 业主装修管理
 * 
 * @author Administrator
 * 
 */
// Spring Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class OwnerDecorateManager extends HibernateEntityDao<OwnerDecorateEO> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// private HibernateDao<OwnerEO, Integer> ownerDao;

	@Autowired
	private OwnerDecorateDAO ownerDecorateDao;// 装修记录

	/**
	 * 得到所有的装修记录表
	 * 
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public Page getList(Page page, String where) {
		String areaIds = (String) Struts2Utils.getSession().getAttribute(
				"areaIds");
		where += " and areaId in (" + areaIds + ") ";
		return ownerDecorateDao.getOwnerDecorate(page, where);
	}

	/**
	 * 得到一条装修记录
	 * 
	 * @param id
	 * @return
	 */
	public OwnerDecorateEO get(int id) {

		return ownerDecorateDao.get(id);
	}

	/**
	 * 保存装修
	 * 
	 * @param entity2
	 */
	public void decorateSave(OwnerDecorateEO entity2) {

		ownerDecorateDao.save(entity2);
	}

	/**
	 * 删除装修记录
	 * 
	 * @param parseInt
	 */
	public void del(int id) {
		ownerDecorateDao.delete(id);

	}

	/**
	 * 房屋装修情况报表
	 * 
	 * @param page
	 * @param areaId
	 * @param edificeId
	 * @param cellId
	 * @param houseUID
	 * @param isdecorateEnd
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page report003(Page page, Integer areaId, String edificeId,
			Integer cellId, String houseId, String isdecorateEnd) {

		String where = "";
		if (areaId != null && areaId > 0)
			where += " and house.areaId=" + areaId;
		if (!edificeId.equals(""))
			where += " and house.edificeId= '" + edificeId + "' ";
		if (cellId != null && cellId > 0)
			where += " and house.cell=" + cellId;

		if (houseId != null && houseId.length() > 1)
			where = " and house.id='" + houseId + "'";

		if (isdecorateEnd != null) {
			if (isdecorateEnd.equals("true"))
				where += " and decorateinDate < now()";
			else
				where += " and decorateoutDate>=now()";
		}
		// String hql="from OwnerDecorateEO where 1=1 "+where;
		// return ownerDao.find(page, hql);
		return ownerDecorateDao.report003(page, where);
	}

	private String[] iniArray(int len) {
		// 初始化数祖
		String[] result = new String[len];
		for (int t = 0; t < result.length; t++) {
			result[t] = "0";
		}
		return result;
	}

	private Map session;
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	public void setSession(Map session) {

		this.session = session;
	}

	public PaginatorTag getOwnerdecorateListByPage(int no, int i, String where) {
		String areaIds = (String) Struts2Utils.getSession().getAttribute(
				"areaIds");
		return pagedQuery(" from OwnerDecorateEO where areaId in (" + areaIds
				+ ") " + where, no, i);
	}

}

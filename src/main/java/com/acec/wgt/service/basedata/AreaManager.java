package com.acec.wgt.service.basedata;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.Page;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.AreaDAO;
import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.models.baseData.EdificeDAO;


/**
 * 整个User模块的业务逻辑Facade类.
 * 组合User,Role,Authority三者的DAO,DAO均直接使用泛型的SimpleHibernateTemplate.
 * 使用Spring annotation定义依赖注入和事务管理.
 */
//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class AreaManager{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AreaDAO areaDao;
	@Autowired
	private EdificeDAO edificeDao;

	
	/**
	 * 得到授权的小区
	 */
	@Transactional(readOnly = true)
	public List getAreaALL() {
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		return areaDao.findAllArea(areaIds);
	}
	
	public void delArea(AreaEO entity) {
		List ls =  edificeDao.getListByAreaId(entity.getId());
		if(ls.isEmpty()){
			areaDao.delete(entity);
		}else{
			throw new RuntimeException("存在归属该小区的楼盘，不能删除。请先删除小区内的所有楼栋后再试！。。。。");
		}
	}

	@Transactional(readOnly = true)
	public AreaEO getArea(Integer id) {	
		return areaDao.get(id);
	}
	
	@Transactional(readOnly = true)
	public Page<AreaEO> getListByPage(Page page) {	
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		return areaDao.getAllAreaListByPage(page, areaIds);
	}
	
	public AreaEO getAreaById(int areaId) {
		List l = areaDao.getListByAreaId(areaId);
		if(l.size()>0)
			return (AreaEO) l.get(0);
		else
			return null;
	}
 
	/**
	 * 保存小区，同时插入到小区滞纳金比率表中一条数据
	 */
	public void saveArea(AreaEO entity,String areaId) {		
		if(null == entity.getId() ){
			entity.setId(Integer.parseInt(areaId));
			areaDao.save(entity);
		}else{
			areaDao.save(entity);
		}
	}
	
	/**
	 * 检索物业公司所有小区,只取id和名字
	 * @return
	 */
	public List getALL(String companyId) {
		return areaDao.findAllAreaByCompany(companyId);
	}



	public void gettestSql(int i) {
		Page page = new Page(2);
		page.setPageNo(i);
		page = areaDao.findSQLPage(page, "select id from tb_basedata_area");
		System.out.println(page.getResult());
	}
}
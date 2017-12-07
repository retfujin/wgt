package com.acec.wgt.models.baseData;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;

@Repository
public class EdificeDAO extends HibernateDao<EdificeEO, String> {

	/**
	 * 根据管理处ID，取该小区下的所有楼栋
	 * 
	 * @param areaId
	 * @return
	 */
	public List<EdificeEO> getListByAreaId(Integer areaId) {
		return find("from EdificeEO where area.id=" + areaId);
	}

	/**
	 * 根据楼栋编号取出所有房间列表
	 * 
	 * @param edificeId
	 * @return
	 */
	public List getHouseByEdificeId(String edificeId) {
		return find("from HouseEO where edificeId='" + edificeId + "'");
	}

	/**
	 * 1、根据楼栋编号取出所有房间列表 2、得到除售出外的所有房间
	 * 
	 * @param edificeId
	 * @return
	 */
	public List getHouseByEdificeIdKit(String edificeId) {
		return find("from HouseEO where id not in(select house.id from OwnerEO where isEnter=true ) and edificeId='"
				+ edificeId + "'");
	}

	/**
	 * 取出管理员权限范围内的所有楼栋
	 * 
	 * @param page
	 * @param areaIds
	 * @param where
	 * @return
	 */
	public Page<EdificeEO> getEdificeListByPage(Page page, String areaIds,
			String where) {
		return findPage(page, "from EdificeEO where area.id in(" + areaIds
				+ ")" + where + " order by id,edificeName");
	}

	/**
	 * 根据楼栋编号取出所有楼栋列表
	 * 
	 * @param edificeId
	 * @return
	 */
	public List getListEdificeByEdificeId(String edificeId) {
		return find("from EdificeEO where edificeId='" + edificeId + "'");
	}

	/**
	 * 由管理处编号取出所有楼栋列表
	 * 
	 * @param areaId
	 * @return
	 */
	public List getListEdificeByAreaId(Integer areaId) {
		return find("from EdificeEO where area.id=" + areaId);
	}

	/**
	 * 更新楼栋中的建筑面积
	 * 
	 * @param sumBuildNum
	 * @param edificeId
	 */
	public void updateBuilderNum(Float sumBuildNum, String edificeId) {
		createQuery("update EdificeEO set buildNum=" + sumBuildNum
				+ " where id='" + edificeId + "'");

	}
	
	public List getList(){
		return find("from EdificeEO");
	}

}

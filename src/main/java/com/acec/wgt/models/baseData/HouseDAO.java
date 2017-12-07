package com.acec.wgt.models.baseData;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.web.struts2.Struts2Utils;

@Repository
public class HouseDAO extends HibernateDao<HouseEO, String> {

    /**
     * 由房间ID取出所有房间列表信息
     * 
     * @param houseId
     * @return
     */
    public List getHouseListByHouseId(String houseId) {
	return find("from HouseEO where id='" + houseId + "'");
    }

    /**
     * 根据管理处ID得到楼栋列表
     * 
     * @param areaId
     * @return
     */
    public List<EdificeEO> findListByAreaId(Integer areaId) {
	return find("from EdificeEO where area.id=" + areaId);
    }

    /**
     * 根据管理处ID得到房间列表
     * 
     * @param areaId
     * @return
     */
    public List<HouseEO> findHouseListByAreaId(Integer areaId) {
	return find("from HouseEO where areaId=" + areaId);
    }

    public List<HouseEO> findHouseTypeListByAreaId(Integer areaId, String where) {
    	return find("from HouseEO where areaId=" + areaId + where);
    }

    /**
     * 取出管理员权限范围内，所有房间信息列表
     * 
     * @param page
     * @param areaIds
     * @param where
     * @return
     */
    public Page findListByPage(Page page, String areaIds, String where) {
	return findPage(page, "from HouseEO where areaId in(" + areaIds + ") " + where + " order by id");
    }

    /**
     * 取出小区楼栋的总面积
     * 
     * @param edificeId
     * @return
     */
    public String getSumBuildNum(String edificeId) {
	Object res = findUnique("select sum(buildnum) as buildnum from HouseEO where edificeId='" + edificeId
		+ "' group by edificeId");
	if (res == null) {
	    return "0";
	} else {
	    return res.toString();
	}
    }
    
    public String getSumBuilding(String param) {
    	Object res = findUnique("select sum(buildnum) as buildnum from HouseEO "+param);
    	if (res == null) {
    	    return "0";
    	} else {
    	    return res.toString();
    	}
        }

    /**
     * 得到该管理处的房间数量总和
     * 
     * @param areaId
     * @return
     */
    public List getCountHouseByAreaId(Integer areaId,String where) {
	return find("select count(*) from HouseEO where areaId=" + areaId+" "+where);
    }

    /**
     * 取某个小区的某栋的某单元总房间数
     * 
     * @param cell
     * @param edificeId
     * @return
     */
    public List getCountByCellAedificeId(String cell, String edificeId) {
	return find("select count(*) from HouseEO where cell='" + cell + "' and edificeId='" + edificeId + "'");
    }

    /**
     * 取出所有商铺列表
     * 
     * @param houseId
     * @return
     */
    public List getAllRoomByHouseId(String areaIds, String houseId) {
	String hql = "from HouseEO where houseType='商铺' and areaId in (" + areaIds + ")";
	if (houseId != null && !houseId.equals(""))
	    hql += " and id like '" + houseId + "%'";
	return find(hql);
    }

    /**
     * 取出所有摊位列表
     * 
     * @param houseId
     * @return
     */
    public List getAllStallByHouseId(String areaIds, String houseId) {
	String hql = "from HouseEO where houseType is null and areaId in (" + areaIds + ")";
	if (houseId != null && !houseId.equals(""))
	    hql += " and id like '" + houseId + "%'";
	return find(hql);
    }

    /**
     * 根据管理处ID和房间ID取出房间信息列表
     * 
     * @param areaId
     * @param houseId
     * @return
     */
    public List getHouseListByAreaIdAhouseId(Integer areaId, String houseId) {
	return find("from HouseEO where areaId=" + areaId + " and id='" + houseId + "'");
    }

    /**
     * 取权限下所有管理处的房间资料
     * 
     * @return
     */
    public List getHouseList() {
	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
	return find("from HouseEO where areaId in (" + areaIds + ") order by id");
    }

    /**
     * 根据小区编号得到该小区的所有房间信息
     * 
     * @param areaId
     * @return
     */
    public List getHouseListByAreaId(int areaId, String houseId) {
	String hql = "";

	if (!houseId.equals(""))
	    hql = "from HouseEO where areaId=" + areaId + " and id ='" + houseId + "' order by id";
	else
	    hql = "from HouseEO where areaId=" + areaId + " order by id";
	return find(hql);
    }

    /**
     * 
     * @param houseId
     * @return
     */
    public List getAllByHouseId(String areaIds, String houseId) {
	String hql = "from HouseEO where areaId in (" + areaIds + ")";
	if (houseId != null && !houseId.equals(""))
	    hql += " and id like '" + houseId + "%'";
	return find(hql);
    }

    /**
     * 根据id取小区名称
     * @param areaId
     * @return
     */
    public String getAreaName(String areaId) {
	Object res = findUnique("select areaName from AreaEO where id='" + areaId + "'");
	if (res == null) {
	    return "";
	} else {
	    return res.toString();
	}
    }

    public HouseEO getHouse(String houseId) {
	return findUnique("from HouseEO where id=?", houseId);
    }

    public List getHouseIdAownerNameBywhere(String where) {
	return find("select id,ownerName from HouseEO where 1=1 " + where);
    }
    
    
    public List getHouseList(String edificeId){
    	return find("from HouseEO where edificeId='"+edificeId+"' ");
    }
    
    public HouseEO getHouseByMobile(String sjh){
    	List<HouseEO> l = find("from HouseEO where mobTel='"+sjh+"'");
    	if(!l.isEmpty())
    		return l.get(0);
    	return null;
    }
}

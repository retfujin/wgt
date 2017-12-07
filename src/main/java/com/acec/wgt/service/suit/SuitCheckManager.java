package com.acec.wgt.service.suit;


import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang.StringUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;

import com.acec.core.utils.ArithUtils;
import com.acec.core.utils.Utils;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.models.suit.SuitEO;

//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class SuitCheckManager extends HibernateEntityDao<SuitEO> {


	public PaginatorTag getListByPage(Integer areaId, String beginDate,String endDate, int no, int i) {// 取申请列表
		String hql = "from SuitEO r ";
		String areaIds = (String)Struts2Utils.getSession().getAttribute("areaIds");
		String where = " where r.areaId in ("+areaIds+") ";
		if (areaId > 0)
			where = " and r.areaId=" + areaId;
		if (null != beginDate && !"".equals(beginDate) && !beginDate.equals("null")) 
			where = where + " and r.SuitDate>='" + beginDate + "'";
		if (null != endDate && !"".equals(endDate) && !endDate.equals("null"))
			where = where + " and r.SuitDate<='" + endDate + "'";
		hql = hql + where;
		return pagedQuery(hql, no, i);
	}

	public PaginatorTag getListNobyPage(String flowState, String type,
			String areaId, String beginDate, String endDate, String houseId, String dayNum, int no, int i) {
		
		String hql = "from SuitEO r ";
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		String where = " where r.areaId in ("+areaIds+") ";
		if (areaId != null && !areaId.equals(""))
			where += " and r.areaId='" + areaId + "'";
		
		if (houseId != null && !houseId.equals(""))
		    where += " and r.houseId='"+houseId+"'";
		if (type != null && !type.equals(""))
			where += " and r.suitType='" + type + "'";
		
		if (dayNum != null && !dayNum.equals("")) {
			if (dayNum.equals("1")) {// 1天
				where += " and datediff(suitDate4,suitDate1)>=1";
			} else if (dayNum.equals("3")) {// 3天
				where += " and datediff(suitDate4,suitDate1)>=3";
			} else if (dayNum.equals("7")) {// 7天
				where += " and datediff(suitDate4,suitDate1)>=7";
			}
		}
		
		if (beginDate != null && !beginDate.equals("")){
		    where += " and r.suitDate>'"+beginDate+"'";
		}
		if (endDate != null && !endDate.equals("")){
		    where += " and r.suitDate<'"+endDate+"'";
		}
		//不要加=号
		if (!StringUtils.isEmpty(flowState))
			where += "and flowState"+flowState;
		
		hql = hql + where + " order by r.id desc";

		return pagedQuery(hql, no, i);
	}

	

	/**
	 * 投诉统计
	 * @param areaId
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List tsstat(String beginTime,String endTime){
		
		
		List<AreaEO> areaList = getAreaALL(); 
		List viewList = new ArrayList();
		for(AreaEO area : areaList){
			String [] tscount = new String[]{"","0","0","0","0%","0","0","0%"};
			Integer areaId = area.getId();
			tscount[0] = area.getAreaName();
			tscount[1] = ownerCount(area.getId());
			
			StringBuffer sb=new StringBuffer();
			if(!"".equals(beginTime)) 
				sb.append(" and date_format(suitDate,'%Y-%m-%d')>=date_format('").append(beginTime).append("','%Y-%m-%d') ");
			if(!"".equals(endTime)) 
				sb.append(" and date_format(suitDate,'%Y-%m-%d')<=date_format('").append(endTime).append("','%Y-%m-%d') ");
			List list=find("select suitType,flowState,count(*) as a from SuitEO where areaId = "+areaId +sb.toString()+" group by suitType,flowState");
			for(int i=0;i<list.size();i++){
				String suitType = ((Object[])list.get(i))[0].toString();
				Integer flowState = Integer.parseInt(((Object[])list.get(i))[1].toString());
				int count = Integer.parseInt(((Object[])list.get(i))[2].toString());
				if("有效".equals(suitType)){
					if(flowState>0)
						tscount[3]=String.valueOf(count);
					tscount[2]=String.valueOf(Integer.parseInt(tscount[2])+count);
					
					
				}else if("无效".equals(suitType)){
					if(flowState>0)
						tscount[6]=String.valueOf(count);
					tscount[5]=String.valueOf(Integer.parseInt(tscount[5])+count);
				}
			}
			
			//计算百分比
			
			if(Integer.parseInt(tscount[2])>0)
				tscount[4] = String.valueOf(ArithUtils.div(Float.valueOf(tscount[3]),Float.valueOf(tscount[2]))*100+"%");
			
			if(Integer.parseInt(tscount[5])>0)
				tscount[7] = String.valueOf(ArithUtils.div(Float.valueOf(tscount[6]),Float.valueOf(tscount[5]))*100+"%");
			

			viewList.add(tscount);
		}
		return viewList;
	}
	
	/**
	 * 权限下的小区集合
	 * @return
	 */
	public List getAreaALL() {
		String areaIds = (String)Struts2Utils.getSession().getAttribute("areaIds");
		StringBuffer sb=new StringBuffer();
		sb.append(" id in (").append(areaIds).append(")");
		return find("from AreaEO where "+sb.toString());
	}

	/**
	 * 获取已入有客户信息的总房数
	 * @param areaId
	 * @return
	 */
	public String ownerCount(int areaId){
		List list = find("select count(*) as b from HouseEO where areaId ="+areaId);
		if(list.isEmpty())
			return "0";
		else
			return list.get(0).toString();
	}
	
	
	public List findEdifice(String areaId) {
		return find("from EdificeEO e where area.id='" + areaId + "'");
	}

	public List ajaxHouse(String edificeId) {
		return find("select id,houseName from HouseEO where edificeId='"
				+ edificeId + "'");
	}




	public void del(int id) {
		getSession().createQuery("delete SuitEO where id=" + id)
				.executeUpdate();
	}

	public void save(SuitEO suit) {
		this.getSession().saveOrUpdate(suit);
	}


	public SuitEO getSuit(int id) {
		List<SuitEO> list = find("from SuitEO where id=" + id);
		if (!list.isEmpty())
			return list.get(0);
		else
			return null;
	}

	public String findSuitCount(String houseId) {
		String where=" houseId='"+houseId+"' and flowState=0"; 
		return find("select count(*) as num from SuitEO where "+where).toString();
		
	}
}

package com.acec.wgt.models.chargemanager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.wgt.models.baseData.HouseEO;

@Repository
public class HousechargeDAO extends HibernateDao<HousechargeEO, Integer> {
	/**
	 * 根据收费项目找到所有对应的房间
	 * @param chargeBaseId
	 * @return
	 */
	public List findByChageBasedata(String houseId,Integer chargeBaseId) {
	
		return find("select h from HousechargeEO h inner join fetch h.house where h.house.id like ? and h.chargeBasedata.id=?",houseId+"%",chargeBaseId);
		
		
	}
	
	/**
	 * 根据检索范围找到所有对应收费项的房间
	 * @param chargeBaseId
	 * @param paraId 类似于GT-1023-1-1
	 * @return
	 */
	public List<HouseEO> findHouseList(int chargeBaseId) {

		List retList =find("select h.house from HousechargeEO h where  h.chargeBasedata.id=?",chargeBaseId);

		return retList;
	}
	
	
	/**
	 * 根据检索范围找到所有对应收费项的房间
	 * @param chargeBaseId
	 * @param paraId 类似于GT-1023-1-1
	 * @return
	 */
	public List<HouseEO> findList(String paraId,int chargeBaseId) {
		String[] paras = paraId.split("-");
		List retList =new ArrayList();
		if(paras.length<=2){
			//范围应该是小区或楼栋
			String pa = paras[1]+"%";
			retList =find("select h.house from HousechargeEO h where h.house.id like ? and h.chargeBasedata.id=?",pa,chargeBaseId);
		}else if(paras.length<=3){
			//范围应该是小区或楼栋
			String pa = paras[1]+"-"+paras[2]+"%";
			retList =find("select h.house from HousechargeEO h where h.house.id like ? and h.chargeBasedata.id=?",pa,chargeBaseId);
		}else if(paras.length==4){
			String pa = paras[1]+"-"+paras[2]+"%";//楼栋
			String cell = paras[3];
			retList = find("select h.house from HousechargeEO h where h.house.id like ? and h.chargeBasedata.id=? and h.house.cell=?",pa,chargeBaseId,cell);	
		}
		return retList;
	}
	
	
	/**
	 * 根据检索范围找到所有对应收费项的房间
	 * @param chargeBaseId
	 * 公摊电电梯的取房间值范围
	 * @param paraId 类似于GTD-1023-1-1
	 * @return
	 */
	public List<HouseEO> findDTList(String paraId,int chargeBaseId) {
		String[] paras = paraId.split("-");
		List retList =new ArrayList();
		if(paras.length<=3){
			//范围应该是小区或楼栋
			String pa = paras[1]+"-"+paras[2]+"%";
			retList =find("select h.house from HousechargeEO h where h.house.isSale<>'空置' and h.house.layer>1 and h.house.id like ? and h.chargeBasedata.id=?",pa,chargeBaseId);
		}else if(paras.length==4){
			String pa = paras[1]+"-"+paras[2]+"%";//楼栋
			String cell = paras[3];
			retList = find("select h.house from HousechargeEO h where h.house.isSale<>'空置' and h.house.layer>1 and h.house.id like ? and h.chargeBasedata.id=? and h.house.cell=?",pa,chargeBaseId,cell);	
		}
		return retList;
	}
	
	
	/**
	 * 根据检索1004和1005所有的用户 找到所有对应收费项的房间
	 * 此方法作监控公摊时使用
	 * 此方法作电损公摊时使用
	 * @param chargeBaseId
	 * @param 
	 * @return
	 */
	public List<HouseEO> findallList(int subChargeId) {
		List retList =find("select h.house from HousechargeEO h where (h.house.areaId=1004 or h.house.areaId=1005) and (h.chargeBasedata.id='1004"+subChargeId+"' or h.chargeBasedata.id='1005"+subChargeId+"') ");
		return retList;
	}
}
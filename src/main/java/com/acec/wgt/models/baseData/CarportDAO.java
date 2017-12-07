package com.acec.wgt.models.baseData;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.web.struts2.Struts2Utils;

@Repository
public class CarportDAO extends HibernateEntityDao<CarportEO> {

	
	public PaginatorTag getAllCarPortState(int no , int i,String areaIds,String where){
		return pagedQueryNative("select a.id, b.house_id,b.owner_name,a.car_code,a.local,a.state,b.car_nums,b.begin_date,b.end_date,a.type,b.charge_id from tb_basedata_carport a left join tb_basedata_carport_lease b on a.carport_lease_id = b.id where a.is_del=1 and area in ("+areaIds+") "+where+" order by a.car_code",no , i);
	}
	
	public List getNowCarport(String areaId){
		return getSession().createSQLQuery("select a.area,a.car_code, b.house_id,a.state,b.end_date from tb_basedata_carport a left join tb_basedata_carport_lease b on a.carport_lease_id = b.id where a.is_del=1 and b.area_id = "+areaId+" order by a.car_code").list();
	}
	
	public void updateState(String state,int carportId,int id) {
		createQuery("update CarportEO set state='"+state+"',carportLeaseId="+id+" where id="+carportId).executeUpdate();
	}
	
	
	public PaginatorTag getCarportByPage(int no , int i , String areaIds,String where) {
		String hql="from CarportEO where isDel=true and area.id in ("+areaIds+")"+where;
		return pagedQuery(hql, no, i);
	}
	
//	public PaginatorTag getCarportByPage2(int no , int i,String type,String areaIds,String where) {
//		String hql="from CarportEO where type='"+type+"' and type2 = '电动车位' and isDel=true and area.id in ("+areaIds+")"+where;
//		return pagedQuery(hql, no,i);
//	}
//	
//	public PaginatorTag getCarportByPage3(int no , int i ,String type,String areaIds,String where) {
//		String hql="from CarportEO where type='"+type+"' and type2 = '其它车位' and isDel=true and area.id in ("+areaIds+")"+where;
//		return pagedQuery(hql, no, i);
//	}
	
	
	public CarportEO getcarCode(String carCode,String areaId){
		return (CarportEO) find("from CarportEO where carCode='"+carCode+"' and area.id="+areaId);
	}
	
	
	public List<CarportLeaseEO> getCarportAll(String houseId,int chargeId) {	
		return find("from CarportLeaseEO where houseId='"+houseId+"' and chargeId="+chargeId+" and isNow='当前'"); 	
	}
	
	
	public void updCarportHistory(Integer carportId){
		getSession().createSQLQuery("update tb_basedata_carport_lease set is_now='历史' where carport="+carportId).executeUpdate();
	}
	
	
	public void updCarport(Integer carportLeaseId,Integer id,String carType){
		getSession().createSQLQuery("update tb_basedata_carport set state='"+carType+"',carport_lease_id="+carportLeaseId+" where id="+id).executeUpdate();
	}
	
	
	public void updTbCarport(Integer carportLeaseId,Integer id){
		getSession().createSQLQuery("update tb_basedata_carport set state='出售',carport_lease_id="+carportLeaseId+" where id="+id).executeUpdate();
	}
	
	
	public List<Object> getMaxId(){
		return find("select max(id) from CarportLeaseEO");
	}
	
	
	/**
	 * 逻辑删除车位
	 * @param parameter
	 */
	public void delCarport(Integer carportId) {
		createQuery("update CarportEO set isDel=false where id="+carportId).executeUpdate();
		
	}
	
	public void delHistoryCarprot(int id){
		createQuery("delete CarportLeaseEO where id="+id).executeUpdate();
	}
	
	public PaginatorTag getHouseDetail(int no , int i,String houseId) {
		return pagedQuery("from CarportLeaseDetailsEO where (chargeId like '%1102%' or chargeId like '%3102%') and arrearageMoney > 0 and houseId='"+houseId+"' order by recordMonth" , no , i);
	}
	
//	
//	public CarportLeaseEO getCarportLeaseBycarportId(Integer carportId){
//		 
//		return (CarportLeaseEO)find("from CarportLeaseEO where isNow='当前' and carport.id="+carportId );
//	}
//	
	
	public void updChargeHDPayMoney(Float factMoney,String detailId){
		getSession().createQuery("update CarportLeaseDetailsEO set gatheringDate=now(), payMoney=payMoney+"+factMoney+",arrearageMoney=oughtMoney - payMoney - "+factMoney+" where chargeHouseDetailId="+detailId).executeUpdate();
	}
	
	public void updChargeHDALLMoney(Float factMoney,String detailId){
		getSession().createQuery("update CarportLeaseDetailsEO set gatheringDate=now(),factMoney=factMoney+"+factMoney+",payMoney=payMoney+"+factMoney+",arrearageMoney=oughtMoney - payMoney - "+factMoney+" where chargeHouseDetailId="+detailId).executeUpdate();
	}
	
	
	/**
	 * 车位停止租赁
	 * @param carportId
	 */
	public void updateCarportLease(int carportId) {
		createQuery("update CarportLeaseEO set isNow='历史',outDate=now() where carport.id="+carportId+" and isNow='当前'").executeUpdate();
	}
	
	
	public PaginatorTag getCarportLeaseListByWhere(int no , int i,String areaIds,String where){
		return pagedQuery("from CarportLeaseEO where isNow='当前' "+where+" and areaId in("+areaIds+")" , no , i);
	}
	
	
	/**
	 * 取车位历史缴费记录
	 * @param page
	 * @param parseInt
	 * @param houseId
	 * @return
	 */
	public PaginatorTag getHistoryCharge(int no , int i, String areaIds, String where) {		
		return pagedQuery(" from CarportLeaseDetailsEO where areaId in("+areaIds+")"+ where , no , i);
	}
	
	public PaginatorTag getCount(int no , int i ,String areaids , String where){
		
		return pagedQueryNative( "select distinct house_id , owner_name ,other_name ,sum(ought_money) as a ,sum(pay_money) as b ,sum(arrearage_money) as c from tb_basedata_carport_lease_detail where area_id in ( " +areaids+ ")" + where + " group by house_id ,owner_name,other_name ",no , i);
	}
	
	public List getCarportLease(int id){
		return find("from CarportLeaseEO where id='"+id+"'");
	}
}

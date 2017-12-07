package com.acec.wgt.service.chargemanger;



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.baseData.HouseMeterDAO;
import com.acec.wgt.models.baseData.HouseMeterEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chargemanager.ChargehouseVO;
import com.acec.wgt.models.chargemanager.HousechargeEO;
import com.acec.wgt.models.chargemanager.HousechargeImplDAO;


//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class HouseChargeManager {

	private final Logger logger = LoggerFactory.getLogger(HouseChargeManager.class);

	private HibernateDao<HousechargeEO, Integer> managerDao;	
	private HibernateDao<HouseEO ,String> houseDao;
	
	@Autowired
    private HousechargeImplDAO housechargeImplDAO;
	@Autowired
    private HouseMeterDAO houseMeterDAO;
	@Autowired
	private BasedataManager basedataManager;

	//@Autowired
	//private HouseMeterManager houseMeterInfoManager;


	
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		managerDao = new HibernateDao<HousechargeEO, Integer>(sessionFactory, HousechargeEO.class);
		houseDao = new HibernateDao<HouseEO, String>(sessionFactory,HouseEO.class);
	   
	}
	
	

	/**
	 * 取分页显示所有业主的收费项目 -- 多层
	 * @param page
	 * @param edificeId 
	 * @param areaId 
	 * @param houseUID 
	 * @param managerMenId
	 * @return ChargehouseVO 
	 */
	@Transactional(readOnly = true)
	public PaginatorTag getChargeHousePage( int areaId, String edificeId,String _houseId, int no,int i)
	{
		String where ="";
		if(areaId > 0)
			where = " and area_id="+areaId;
		if(!edificeId.equals(""))
			where +=" and edifice_id ='" + edificeId+"'";
		if(!"".equals(_houseId))
			where +=" and id like '" + _houseId+"%'";
		
		
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
	    
		PaginatorTag pt = housechargeImplDAO.getHousecharge(areaIds, where, no, i);

		for(int k=0;k<(pt.getData()!=null ? pt.getData().size() : 0 );k++)
		{
			String houseId = ((Object[])pt.getData().get(k))[0].toString();//houseId
			
			List<HousechargeEO> l = getChargeByHouseId(houseId);
			String chargeName="";
			for(HousechargeEO hc : l)
			{
				chargeName += "["+hc.getChargeBasedata().getChargeName()+"] ";
			}
			
			((Object[])pt.getData().get(k))[5] = chargeName ;
		}

		return pt;
	}
	
	/**
	 * 根据房间id取房间的收费项目
	 * @param houseId
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<HousechargeEO> getChargeByHouseId(String houseId)
	{
		return managerDao.find("from HousechargeEO where house.id=?", houseId);
	}
	
	
	
	
	/**
	 * 通过房间id取房间的收费项目
	 * 不管该房间有没有 所有的收费项目
	 * @param id
	 * @return ChargeHouseVO
	 */
	@Transactional(readOnly = true)
	public List getChargeHouseById(String houseId) {
		
		List<ChargehouseVO> retList = new ArrayList();
		String[] strs = houseId.split("-");
		List<ChargeBasedataEO> l = basedataManager.getAllByAreaId(strs[0]);
		List<HousechargeEO> l2 = managerDao.find("from HousechargeEO where house.id=? order by chargeBasedata.chargeName", houseId);
		for(ChargeBasedataEO b : l)
		{
			ChargehouseVO chVO = new ChargehouseVO();
			chVO.setChargeName(b.getChargeName());
			chVO.setPriceValue(b.getPriceValue());
			chVO.setChargeExpression(b.getChargeExpression());
			chVO.setExpExplain(b.getExpExplain());
			
			chVO.setChargeBasedataId(b.getId());			
			chVO.setHouseId(houseId);
			
			//给默认的初始值，否则保存出错
			chVO.setBackRecord(9999);
			chVO.setNowRecord(0);
			
			if(b.getChargeType().equals("走表类"))
				chVO.setIsMeter(1);
			else
				chVO.setIsMeter(0);
			
			for(HousechargeEO h : l2)
			{
				if(h.getChargeBasedata().getId() == b.getId())
				{
					chVO.setIsSelect(1);
			//		chVO.setBackRecord(h.getBackRecord());
			//		chVO.setNowRecord(h.getNowRecord());
				}
			}
			
			retList.add(chVO);
		}
		
		return retList;
	}


	/**
	 * 保存单个房间的单个收费项目
	 * @param housechargeEO
	 */
	public void saveHouseCharge(HousechargeEO housecharge) {
		if(null == housecharge.getHouse())
			throw new RuntimeException("保存收费设置错误，该房间不存在！");
		if(null == housecharge.getChargeBasedata() || null == housecharge.getChargeBasedata().getId() )
			throw new RuntimeException("保存收费设置错误，收费项目不存在！");

		
		managerDao.save(housecharge);
	}

	/**
	 * 专门为商铺加上租赁费用的接口
	 * 
	 * 商铺租赁费的收费编号为指定的2106
	 * 
	 * @param houseId
	 */
	public void saveShopCharge(String houseId)
	{
		HousechargeEO h = new HousechargeEO();
		
		HouseEO house = new HouseEO();
		house.setId(houseId);
		h.setHouse(house);
		//指定为商铺租赁费
		ChargeBasedataEO cb = new ChargeBasedataEO();
		cb.setId(2106);
		h.setChargeBasedata(cb);
		
		
		saveHouseCharge(h);
	}
	
	/**
	 * 专门为车位加上租赁费用的接口
	 * @param houseId
	 * @param beginTime
	 * @param endTime
	 */
	public void saveCarCharge(String houseId,int chargeId)
	{
		HousechargeEO h = new HousechargeEO();
		
		HouseEO house = new HouseEO();
		house.setId(houseId);
		h.setHouse(house);
		//指定为商铺租赁费
		ChargeBasedataEO cb = new ChargeBasedataEO();
		cb.setId(chargeId);
		h.setChargeBasedata(cb);
		
		
		saveHouseCharge(h);
	}
	
	
	/**
	 * 批量增加房间的收费项目
	 * @param l
	 * @param houseIds
	 */
	public void addChargeAll(List<HousechargeEO> l ,String houseIds)
	{
		deleteHouseCharge(houseIds);
		multSave(l);
	}
	
	
	
	/**
	 * 批量房间的保存收费项目
	 * 
	 * 只做保存操作
	 * @param List<HousechargeEO> 
	 */
	public void multSaveOrUpdate(List<HousechargeEO> l) {
		logger.debug("批量保存房间收费项目");
		for(HousechargeEO h : l)
		{
			saveHouseCharge(h);
		}
	}
	
	
	
	/**
	 * 批量房间的保存收费项目
	 * 
	 * 只做保存操作
	 * @param List<HousechargeEO> 
	 */
	public void multSave(List<HousechargeEO> l) {
		logger.debug("批量保存房间收费项目");
		List _ls = null;
		for(HousechargeEO h : l)
		{
			//判断二次供水、热水、冷水、电费 都需要房间表信息
			String _chargeId = String.valueOf(h.getChargeBasedata().getId());
			String _houseId = h.getHouse().getId();

			String meterType = getMeterTypeBychargeId(_chargeId);
			if(!StringUtils.isEmpty(meterType)){
				
				if(_ls == null){
					_ls = houseMeterDAO.getHouseMeterLikeHouseId(_houseId.substring(0,4));
				}
				boolean finded = false;
				for(int i=0;i<_ls.size();i++){
					Object[] objs = (Object[])_ls.get(i);
					if(_houseId.equals(objs[0])&&meterType.equals(objs[1])){
						finded = true;
						break;
					}
				}
				if(!finded)
					throw new RuntimeException("取"+_houseId+"房间"+meterType+"初始度数出错，请确认该表是否存在");
				
			}				
			managerDao.save(h);
		}
		
	}
	/**
	 * 批量删除房间的所有收费项目
	 * @param houseId
	 */
	public void deleteHouseCharge(String houseIds) {
		logger.debug("批量删除房间的所有收费项目");
		managerDao.createQuery("delete from HousechargeEO where house.id in("+houseIds+")").executeUpdate();
	}


	/**
	 * 分组取所有小区的各种表的数量
	 * @return
	 */
	public List reportOwner001() {

		return managerDao.find("select house.areaId,meterType,count(*) from HouseMeterEO where isNow=true group by house.areaId,meterType ");
		
	}


	/**
	 * 分组取楼栋 单元的 住户数量
	 * @return
	 */
	public List reportAll007() {
		return managerDao.find("select house.edificeId,house.cell,count(*) from HousechargeEO where chargeBasedata.otherName='公摊电费' group by house.edificeId,house.cell");
	}
	/**
	 * 分组取楼栋 住户数量
	 * @return
	 */
	public List reportAll0071() {
		return managerDao.find("select house.edificeId,count(*) from HousechargeEO where chargeBasedata.otherName='公摊电费' group by house.edificeId");
	}

	public static void main(String[] args)
	{
		String a="2008-09-12";
		int b = 0;
		System.out.println("------"+a.substring(8,10));
		
	}
	
	/*
	 * 批量保存房间收费项目
	 */
	public void saveChargeAll(String areaId,String edificeId,String cell,String symbol,String layer, String [] chargeId){
		List list=new ArrayList();
		String houseIds="";
		
		String hql = "from HouseEO where  areaId='"+areaId+"'";
		if(edificeId!=null && !edificeId.equals(""))
		{
			hql = "from HouseEO where  edificeId='"+edificeId+"'";
		}
		//单元筛选
		if(cell!=null&&!cell.equals(""))
			hql += " and cell='"+cell+"'";
		//层数筛选
		if(symbol!=null && !"".equals(symbol)){
			if(layer!=null&&!layer.equals("") && symbol.equals("大于等于"))
				hql +=" and layer>='"+layer+"'";
			else if(layer!=null&&!layer.equals("") && symbol.equals("小于"))
				hql +=" and layer<'"+layer+"'";			
		}			
		List<HouseEO> l=houseDao.find(hql);
		if(l.isEmpty())
			throw new RuntimeException("没有找到符合条件的房间");
		for(int i=0;i<l.size();i++){
			houseIds +="'"+l.get(i).getId()+"',";
			if(chargeId!=null){
				for(int j=0;j<chargeId.length;j++)
				{
					HousechargeEO hc=new HousechargeEO();
					ChargeBasedataEO ch  = new ChargeBasedataEO();
					ch.setId(Integer.parseInt(chargeId[j]));
					hc.setChargeBasedata(ch);
					HouseEO h=new HouseEO();
					h.setId(l.get(i).getId());
					hc.setHouse(h);
					list.add(hc);			
				}	
			}
		}
		houseIds=houseIds.substring(0, houseIds.length()-1);
		addChargeAll(list,houseIds);
	}
	
	/**
	 * 删除单个房间收费项
	 * @param id
	 */
	public void delHousecharge(String houseId,String meterType){
		managerDao.createQuery("delete HousechargeEO where house.id='"+houseId+"' and chargeBasedata.meterType='"+meterType+"' ").executeUpdate();
	}
	

	
	public static String getMeterTypeBychargeId(String subchargeId) {
		String meterType="";
		subchargeId = subchargeId.substring(4,8);
		if(subchargeId.equals("2102"))
			meterType="水表";
		else if(subchargeId.equals("2103"))
			meterType="热水表";
		else if(subchargeId.equals("2104"))
			meterType="暖气表";
		else if(subchargeId.equals("2105"))
			 meterType="电表";
		return meterType;
	}
	public static String getchargeIdByMeterType(String meterType) {
		String subChargeId="";
		if(meterType.equals("水表"))
			subChargeId="2102";
		else if(meterType.equals("热水表"))
			subChargeId="2103";
		else if(meterType.equals("暖气表"))
			subChargeId="2104";
		else if(meterType.equals("电表"))
			subChargeId="2105";
		return subChargeId;
	}
}
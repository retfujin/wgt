package com.acec.wgt.service.chargemanger;


import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.commons.util.ExcelInOut;
import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.utils.Utils;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.baseData.HouseMeterDAO;
import com.acec.wgt.models.baseData.HouseMeterEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataDAO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chargemanager.HousechargeEO;
import com.acec.wgt.models.chargemanager.OwnerMeterRecordEO;
import com.acec.wgt.models.chargemanager.OwnerMeterRecordImplDAO;
import com.acec.wgt.models.chg.ChargeHouseDetailDAO;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;
import com.acec.wgt.service.basedata.CellManager;

//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class HouseMeterrecordManager {
	

	@Autowired
	private HouseMeterDAO houseMeterDao;
	
	@Autowired
	private CellManager cellManager;
	@Autowired
	private ChargeBasedataDAO chargeBasedataDao;
	
	private HibernateDao<OwnerMeterRecordEO, Integer> owDao;
	@Autowired
	private OwnerMeterRecordImplDAO OwnerMeterRecordImplDAO;
	@Autowired
	private ChargeHouseDetailDAO chargeHouseDetailDAO;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		owDao = new HibernateDao<OwnerMeterRecordEO, Integer>(sessionFactory, OwnerMeterRecordEO.class);
	}

	public HouseMeterEO getHouseMeterByHouseId(String houseId, String meterType) {
		
		List<HouseMeterEO> l = houseMeterDao.getHouseMeterByHouseId(houseId, meterType);
		if(l.isEmpty())
			return null;
		else
		return l.get(0);
	}

	/**
	 * 权限下取所有房间水电表
	 * @param page
	 * @param areaId
	 * @return
	 */
	public Page getAllHouseMeter(Page page,String where) {		
		return houseMeterDao.getAllHouseMeter(page, where);
	}

	/**
	 * 删除房间表基础资料
	 * @param id
	 */
	public void delHouseMeter(String id){
		houseMeterDao.delete(Integer.parseInt(id));
	}
	
	/**
	 * 生成业户表抄表数据
	 * 
	 * @param areaId
	 * @param recordMonth
	 */
	public void resultMeterData(int areaId,String meterType,String recordMonth) throws Exception
	{	
		//看能否生成抄表数据
		List _ls = owDao.createQuery("select recordMonth from OwnerMeterRecordEO where areaId=? and meterType=? and recordMonth>='"+recordMonth+"-01'",areaId,meterType).list();
		if(!_ls.isEmpty()){
			Object obj = (Object)_ls.get(0);
			throw new RuntimeException(meterType+"已生成过"+obj+"月抄表记录");
		}
		Map<String,Object[]> m = cellManager.getAllOwnerName(areaId,"","","");
		
		String subchargeId = areaId+HouseChargeManager.getchargeIdByMeterType(meterType);
		List<HousechargeEO> l = houseMeterDao.find("from HousechargeEO where chargeBasedata.id like '"+subchargeId+"%' and house.areaId=?",areaId);		
		if(l.isEmpty())
			throw new RuntimeException("没有找到业户表类费用");
		
		Date _recordMonth = Utils.strToDate(recordMonth + "-01");
		for (HousechargeEO a : l) {
			int nowRecord = 0;
			int times = 1;
			int backRecord = 0;
			String id = "";

			List<Object[]> _lshouseMeter = houseMeterDao
					.find("select id,nowRecord,times,backRecord from HouseMeterEO where house.id=? and meterType=? and isNow=true",
							a.getHouse().getId(), meterType);
			if (_lshouseMeter.isEmpty()) {
				// throw new
				// RuntimeException("没有找到房间编号为"+a.getHouse().getId()+"的表数据");
				continue;
			} else {
				Object[] objs = _lshouseMeter.get(0);
				id = objs[0].toString();
				nowRecord = (Integer) objs[1];
				times = (Integer) objs[2];
				backRecord = (Integer) objs[3];
			}

			OwnerMeterRecordEO r = new OwnerMeterRecordEO();
			r.setCheckStatus("正在录入");

			r.setRecordMonth(_recordMonth);// 生成月份
			r.setUnit(a.getChargeBasedata().getPriceUnit());
			r.setMeterName(a.getChargeBasedata().getMeterType());
			r.setHouseId(a.getHouse().getId());
			r.setLastRecord(nowRecord);
			r.setNowRecord(nowRecord);
			r.setBackRecord(backRecord);
			// 见类注释
			r.setChangeNum(0);

			r.setMeterCode(id);// 业户表的id
			// r.setRecordMonth(Utils.strToDate(recordMonth.substring(0,7)+"-01"));
			r.setUseNums(0);
			r.setMeterType(meterType);
			r.setIsNow(1);
			r.setTimes(times);
			r.setPriceValue(a.getChargeBasedata().getPriceValue());
			r.setBuildnum(a.getHouse().getBuildnum());
			r.setCell(a.getHouse().getCell());
			r.setEdificeId(a.getHouse().getEdificeId());
			r.setHouseAddress(a.getHouse().getHouseAddress());
			r.setChargeId(a.getChargeBasedata().getId());// 收费编号
			r.setHouseName(a.getHouse().getHouseName());
			// String temp[] = (String[]) m2.get(a.getHouse().getId());
			// if(temp != null)
			// r.setManagerMen(temp[1]);
			
			//设置业主姓名
			Object[] aa = m.get(a.getHouse().getId());
			if (null != aa&&aa[1]!=null)
				r.setOwnerName(aa[1].toString());
			else
				r.setOwnerName("");
			
			
			r.setAreaId(areaId);
			saveOrUpdateOwnerMeter(r);
		}
	}

	
	
	/**
	 * 保存业户表抄表数据
	 * @param recordId
	 * @param lastRecord
	 * @param nowRecord
	 * @param backRecord
	 * @param recordTime
	 * @param employee
	 * @param changeNums 换表数
	 * @param meterType
	 * @throws Exception
	 */
	public void saveMeterData(String[] recordId,  String[] lastRecord, String[] nowRecord, String[] backRecord,
			String recordTime, String employee,String[] changeNums,String[] meterType) throws Exception
	{
				
		String userName = (String)Struts2Utils.getSession().getAttribute("userName");
		for(int i=0;i<nowRecord.length;i++)
		{
			int lastRecord_ = Integer.parseInt(lastRecord[i]);//上期数
			int nowRecord_ = Integer.parseInt(nowRecord[i]);//本期数
			int backRecord_ = Integer.parseInt(backRecord[i]);//回转数
			int changeNum_ = Integer.parseInt(changeNums[i]);//换表后旧表量
			int recordId_ = Integer.parseInt(recordId[i]);
			int useNums_ = (nowRecord_-lastRecord_)+changeNum_;
			if(lastRecord_>nowRecord_){//如果上期数大于本期数，说明表到达最大量程
				useNums_ = (backRecord_-lastRecord_)+nowRecord_+changeNum_;
			}
			
			owDao.createQuery("update OwnerMeterRecordEO set nowRecord=?,changeNum=?,useNums=?"
				+",recordTime=?,recordName=?,submitName=?,submitTime=? where id=?",
				nowRecord_,changeNum_,useNums_,Utils.strToDate(recordTime),employee,
				userName,new Date(),recordId_).executeUpdate();
		}	
	}
	
	/**
	 * 业主换表保存
	 * @param oldId  原来的序号
	 * @param initRecord 期初表数
	 * @param nowRecord 当前表数
	 */
	public void changeOwnerMeter(int oldMeterRecord,HouseMeterEO houseMeter)
	{
		HouseMeterEO hm = (HouseMeterEO) houseMeterDao.findUnique("from HouseMeterEO where house.id='"+houseMeter.getHouse().getId()+"' and meterType='"+houseMeter.getMeterType()+"' and isNow=true");
		String ownerName=cellManager.getOwnerName(houseMeter.getHouse().getId());
		//业主姓名
		if(ownerName==null)
			ownerName = "";
		
		if(null == hm)
			throw new RuntimeException("该房间没有"+houseMeter.getMeterType()+"数据");
		
		
		
		//当前的抄表记录没有生成不能换表
		OwnerMeterRecordEO ro = (OwnerMeterRecordEO) houseMeterDao.findUnique("from OwnerMeterRecordEO where isNow=1 and meterType='"+houseMeter.getMeterType()+"' and houseId='"+houseMeter.getHouse().getId()+"'");
		if(null == ro)
			throw new RuntimeException("请先生成抄表记录再换表，否则以前表数据无法在本期录入");
		
		//重新变更抄表记录
		
		if(houseMeter.getNowRecord()- houseMeter.getInitNum()<0)
			throw new RuntimeException("新表度数不能为负数");
		
		if(oldMeterRecord- ro.getLastRecord()<0)
			throw new RuntimeException("老表度数不能为负数");
		
		//换表数
		ro.setChangeNum(oldMeterRecord- ro.getLastRecord());
		
		ro.setLastRecord(houseMeter.getInitNum());
		ro.setNowRecord(houseMeter.getNowRecord());
		
		//重新生成金额
		setMeterTotalMoney(ro);
				
		
		//更新老表最后一次抄表表数
		hm.setNowRecord(oldMeterRecord);
		hm.setChangeName(houseMeter.getChangeName());
		hm.setChangeReason(houseMeter.getChangeReason());
		hm.setChangeTime(houseMeter.getChangeTime());
		hm.setIsNow(false);
		hm.setOwnerName(ownerName);
		houseMeterDao.save(hm);
		
		//保存新表期初表数
		houseMeter.setChangeName(null);
		houseMeter.setChangeReason(null);
		houseMeter.setChangeTime(null);
		houseMeter.setHouse(hm.getHouse());
		houseMeter.setIsNow(true);
		houseMeter.setMeterType(hm.getMeterType());
		houseMeter.setOwnerName(ownerName);
		
		//当前度数在审核时自动更改
		houseMeterDao.save(houseMeter);
	}

	
	
	/**
	 *  业主表 历史行度查询
	 * @param areaId 小区编号
	 * @param edificeId 楼栋编号
	 * @param houseId 房间编号
	 * @param meterType 表类型
	 * @param beginMonth 起始月
	 * @param endMonth 截至月
	 * @param no 页数
	 * @param i 每页条数
	 * @return
	 */
	public PaginatorTag getHistoryList( int areaId, String edificeId,String houseId,
			String meterType, String beginMonth,String endMonth, int no , int i) {
		
		String where="";
		
		if(!StringUtils.isEmpty(houseId))
			where += " and houseId like '"+houseId+"%'";
		else if(!StringUtils.isEmpty(edificeId))
			where += " and edificeId='"+edificeId+"'";
		else if(!"0".equals(areaId))
			where += " and areaId="+areaId;
		
		if(!StringUtils.isEmpty(meterType))
			where += " and meterType='"+meterType+"'";
		if(!StringUtils.isEmpty(beginMonth))
			where += " and recordMonth>='"+beginMonth+"-01'";
		if(!StringUtils.isEmpty(endMonth))
			where += " and recordMonth<='"+endMonth+"-01'";
		
		where +=" and checkStatus ='审核' order by recordMonth desc,houseId";
		//page = ownermeterRecordManager.getListByPage(page,where+" and checkStatus<>'数据锁定'");
		PaginatorTag pt = getListByPageImpl(where, no, i);
		return pt;
	}
	/**
	 * 取业主表抄表数据(最新)不分页
	 * @param page
	 * @param areaId
	 * @param edificeId
	 * @param meterType
	 * @return
	 */
	public List getOwnerMeterInputImpl( int areaId, String edificeId,
			String meterType,String month) {
		
		String where="";
		
		if(!"0".equals(areaId))
			where += " and areaId="+areaId;
		if(!edificeId.equals(""))
			where += " and edificeId='"+edificeId+"'";
		if(!meterType.equals(""))
			where += " and meterType='"+meterType+"'";
		if(!month.equals(""))
			where += " and date_format(recordMonth,'%Y-%m')='"+month+"' ";
		where += " and checkStatus='录入' ";
		//page = ownermeterRecordManager.getListByPage(page,where+" and checkStatus<>'数据锁定'");
		List ls = getNowRecordAll(where);
		return ls;
	}
	/**
	 * 取所有当前月份的抄表记录
	 * @return
	 */
	public List<OwnerMeterRecordEO> getNowRecordAll(String where) {
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		String hql="from OwnerMeterRecordEO where isNow=1 and areaId in ("+areaIds+") "+where+" order by houseId";
		
		
		return houseMeterDao.find(hql);
	}
	
	/**
	 * 取业主房间表 表信息
	 * @param houseId
	 * @param meterType
	 * @return
	 */
	public HouseMeterEO getHouseMeter(String houseId, String meterType) {
		return  (HouseMeterEO) houseMeterDao.findUnique("from HouseMeterEO where isNow=1 and meterType='"+meterType+"' and house.id='"+houseId+"'");
	}
	
	public HouseMeterEO getHouseMeter(int id){
		return houseMeterDao.get(id);
	}

	/**
	 * 通过用量计算总金额
	 * @param re
	 */
	private void setMeterTotalMoney(OwnerMeterRecordEO re) {
		int nowNums = (re.getNowRecord() - re.getLastRecord());
		re.setUseNums(nowNums+re.getChangeNum());
		re.setTotalMoney(re.getPriceValue() * re.getUseNums());
	}
	/**
	 * 删除当前业户表抄表记录
	 * @param areaId
	 * @param meterType
	 */
	public void deleteMeterData(int areaId,String meterType) {
		houseMeterDao.createQuery("delete OwnerMeterRecordEO where isNow=1 and meterType=? and areaId=?",meterType,areaId).executeUpdate();
	}
	
	/**
	 * 新增或保存一条业户表抄表记录
	 * @param allmeter
	 */
	public void saveOrUpdateOwnerMeter(OwnerMeterRecordEO ownerMeterRecord) {
		// TODO Auto-generated method stub
		owDao.save(ownerMeterRecord);
	}
	
	/**
	 * 权限下 所有业户表记录 分页(最新)
	 * @param page
	 * @param where   查询条件
	 * @return
	 */
	@Transactional(readOnly = true)
	 public PaginatorTag getListByPageImpl(String where , int no , int i) {

		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		String hql="from OwnerMeterRecordEO where areaId in ("+areaIds+") "+where;
		
		return OwnerMeterRecordImplDAO.getOwnerMeterRecordImpl(areaIds, where, no, i);
	}
	
	
	
	
	/**
	 * 制作excel文件
	 * @param areaId
	 */
	public void writeExc(String areaId,String meterType,String month) {
		List<HouseMeterEO> l1 = houseMeterDao.getHouseMeterByHouseId(areaId,meterType);
		StringBuffer sb = new StringBuffer();
		sb.append(" and areaId=").append(areaId).append(" and chargeType='走表类' and meterType='").append(meterType).append("' ");
		List<ChargeBasedataEO> list = chargeBasedataDao.findAllList(sb.toString());
		String chargeId="";
		if(!list.isEmpty())
			chargeId = String.valueOf(list.get(0).getId());
		List<String[]> retList =new ArrayList<String[]>();
		String[] temp={"小区编号","楼栋编号","房间地址","业主姓名","面积","单元","所在楼层","房间名称","房间编号","表类型","倍率","抄表月","上期度数","本期度数","收费编号"};
		retList.add(temp);
		if(!l1.isEmpty())
		{
			for(int i=0;i<l1.size();i++)
			{
				String[] s=new String[15];
				s[0]=String.valueOf(l1.get(i).getHouse().getAreaId());
				s[1]=l1.get(i).getHouse().getEdificeId();
				s[2]=l1.get(i).getHouse().getHouseAddress();
				s[3]=l1.get(i).getOwnerName();
				s[4]=String.valueOf(l1.get(i).getHouse().getBuildnum());
				s[5]=l1.get(i).getHouse().getCell();
				s[6]=String.valueOf(l1.get(i).getHouse().getLayer());
				s[7]=l1.get(i).getHouse().getHouseName();
				s[8]=l1.get(i).getHouse().getId();
				s[9]=l1.get(i).getMeterType();
				s[10]=String.valueOf(l1.get(i).getTimes());
				s[11]=month;
				s[12]=String.valueOf(l1.get(i).getNowRecord());
				s[14]=chargeId;
				retList.add(s);
			}
		}
		ExcelInOut eIO=new ExcelInOut();
		Boolean ret=eIO.writeExc(retList);
		if(!ret)
			throw new RuntimeException("下载失败");
	}
	
	
	public void saveForExc(File theFile) {
		ExcelInOut eIO = new ExcelInOut();
		List<String[]> list = eIO.readExc(theFile);
//　　　	String[] temp={"小区编号","楼栋编号","房间地址","业主姓名","面积","单元","所在楼层","房间名称","房间编号","表类型","倍率","抄表月","上期度数","本期度数","收费编号"};
		for (int i = 0; i < list.size(); i++) {
			OwnerMeterRecordEO r = new OwnerMeterRecordEO();
			ChargeBasedataEO chg = null;
			int layer = 1;
			
			if (null != list.get(i)[0] && !"".equals(list.get(i)[0]))
				r.setAreaId(Integer.parseInt(list.get(i)[0]));
			else
				throw new RuntimeException("第"+(i+1)+"行记录的小区编号为空。");			
			if (null != list.get(i)[1] && !"".equals(list.get(i)[1]))	
				r.setEdificeId(list.get(i)[1]);
			else
				throw new RuntimeException("第"+(i+1)+"行记录的楼栋编号为空。");			
			if (null != list.get(i)[2] && !"".equals(list.get(i)[2]))	
				r.setHouseAddress(list.get(i)[2]);
			if (null != list.get(i)[3] && !"".equals(list.get(i)[3]))	
				r.setOwnerName(list.get(i)[3]);
			else
				r.setOwnerName("无");
			if (null != list.get(i)[4] && !"".equals(list.get(i)[4]))	
				r.setBuildnum(Float.valueOf(list.get(i)[4]));
			if (null != list.get(i)[5] && !"".equals(list.get(i)[5]))	
				r.setCell(list.get(i)[5]);
			if (null != list.get(i)[6] && !"".equals(list.get(i)[6]))	
				layer = Integer.parseInt(list.get(i)[6]);
			else
				throw new RuntimeException("第"+(i+1)+"行记录的所在楼层为空。");			
			if (null != list.get(i)[7] && !"".equals(list.get(i)[7]))	
				r.setHouseName(list.get(i)[7]);
			if (null != list.get(i)[8] && !"".equals(list.get(i)[8]))	
				r.setHouseId(list.get(i)[8]);
			else
				throw new RuntimeException("第"+(i+1)+"行记录的房间编号为空。");			
			if (null != list.get(i)[9] && !"".equals(list.get(i)[9]))	
				r.setMeterType(list.get(i)[9]);
			else
				throw new RuntimeException("第"+(i+1)+"行记录的表类型为空。");
			
			if (null != list.get(i)[10] && !"".equals(list.get(i)[10]))	
				r.setTimes(Integer.parseInt(list.get(i)[10]));
			else
				throw new RuntimeException("第"+(i+1)+"行记录的倍率为空。");			
			if (null != list.get(i)[11] && !"".equals(list.get(i)[11]))	
				r.setRecordMonth(java.sql.Date.valueOf(list.get(i)[11].toString().trim()+"-01"));
			else
				throw new RuntimeException("第"+(i+1)+"行记录的抄表月为空。");
			
			if (null != list.get(i)[12] && !"".equals(list.get(i)[12]))	
				r.setLastRecord(Integer.parseInt(list.get(i)[12]));
			else
				throw new RuntimeException("第"+(i+1)+"行记录的上期度数为空。");			
			if (null != list.get(i)[13] && !"".equals(list.get(i)[13]))	
				r.setNowRecord(Integer.parseInt(list.get(i)[13]));
			else
				throw new RuntimeException("第"+(i+1)+"行记录的本期度数为空。");
			if (null != list.get(i)[14] && !"".equals(list.get(i)[14])){	
				r.setChargeId(Integer.parseInt(list.get(i)[14]));
				chg = chargeBasedataDao.getChargeBasedataByChargeId(r.getChargeId());
			}
			r.setUseNums(r.getNowRecord()-r.getLastRecord());
			
			if(("1017".equals(String.valueOf(r.getAreaId())) || "1018".equals(String.valueOf(r.getAreaId())) || "1019".equals(String.valueOf(r.getAreaId()))) && r.getMeterType().equals("水表")){
				if(layer<=5){
					if(r.getUseNums()<=24)
						r.setChargeId(Integer.parseInt(r.getAreaId()+""+210204));
					else if(r.getUseNums()<=40)
						r.setChargeId(Integer.parseInt(r.getAreaId()+""+210205));
					else if(r.getUseNums()>40)
						r.setChargeId(Integer.parseInt(r.getAreaId()+""+210206));
				}else{
					if(r.getUseNums()<=24)
						r.setChargeId(Integer.parseInt(r.getAreaId()+""+210207));
					else if(r.getUseNums()<=40)
						r.setChargeId(Integer.parseInt(r.getAreaId()+""+210208));
					else if(r.getUseNums()>40)
						r.setChargeId(Integer.parseInt(r.getAreaId()+""+210209));
				}
				chg = chargeBasedataDao.getChargeBasedataByChargeId(r.getChargeId());
			}
			
			
			
			r.setUnit(chg.getPriceUnit());
			r.setPriceValue(chg.getPriceValue());
			r.setTotalMoney(chg.getPriceValue()*r.getUseNums()*r.getTimes());
			r.setCheckStatus("录入");
			r.setRecordName((String)Struts2Utils.getSession().getAttribute("userName"));
			r.setRecordTime(new Date());
			r.setIsNow(1);
			
			owDao.save(r);
			
			if(i%20==0){
				owDao.getSession().flush();
				owDao.getSession().clear();
			}
		}
	}
	
	
	public void savenum(String id,String num) {
		OwnerMeterRecordEO om = owDao.get(Integer.parseInt(id));
		om.setNowRecord(Integer.parseInt(num));
		om.setUseNums(om.getNowRecord()-om.getLastRecord());
		om.setTotalMoney((om.getNowRecord()-om.getLastRecord())*om.getTimes()*om.getPriceValue());
		owDao.save(om);
	}
	
	
	public void initsave(String areaId,String meterType,String month) {
		
		List<OwnerMeterRecordEO> list = OwnerMeterRecordImplDAO.find("from OwnerMeterRecordEO where checkStatus='录入' and areaId = "+areaId+" and meterType='"+meterType+"' and date_format(recordMonth,'%Y-%m')='"+month+"' ");
		if(!list.isEmpty()){
			Map map = getMaps(areaId);
			for(OwnerMeterRecordEO om :list){
				houseMeterDao.updateHouseMeter(om.getHouseId(),om.getMeterType(),om.getNowRecord());
				om.setCheckStatus("审核");
				OwnerMeterRecordImplDAO.save(om);
				ChargeHouseDetailEO chd = new ChargeHouseDetailEO();
				chd.setAreaId(om.getAreaId());
				chd.setChargeName((String)map.get(om.getChargeId()));
				chd.setChargeType("走表类");
				chd.setLastRecord(om.getLastRecord());
				chd.setNowRecord(om.getNowRecord());
				chd.setOughtMoney(om.getTotalMoney());
				chd.setFactMoney(0f);
				chd.setArrearageMoney(om.getTotalMoney());
				chd.setPrivilegeMoney(0);
				chd.setRecordMonth(java.sql.Date.valueOf(month+"-01"));
				chd.setUserNum(om.getUseNums());
				HouseEO house = cellManager.get(om.getHouseId());
				chd.setHouse(house);
				chd.setHouseType(house.getHouseType());
				chd.setOwnerName(om.getOwnerName());
				chd.setChargeId(om.getChargeId());
				if(chd.getOughtMoney()>0)
					chargeHouseDetailDAO.save(chd);				
			}
		}
	}
	
	public Map getMaps(String areaId){
		Map map = new HashMap();
		StringBuffer sb = new StringBuffer();
		sb.append(" and areaId=").append(areaId).append(" and chargeType='走表类'");
		List<ChargeBasedataEO> list = chargeBasedataDao.findAllList(sb.toString());
		for(ChargeBasedataEO chg : list){
			map.put(chg.getId(), chg.getChargeName());
		}
		return map;
	}
}
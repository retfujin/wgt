package com.acec.wgt.service.chg;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.commons.util.BeanUtilEx;
import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.orm.hibernate.SimpleHibernateDao;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chg.ChargeAdvanceDetailEO;
import com.acec.wgt.models.chg.ChargeAdvanceEO;
import com.acec.wgt.models.chg.ChargeUserPayRecordEO;
import com.acec.wgt.service.basedata.CellManager;

//Spring Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class AdvanceManager {

    private final Logger logger = LoggerFactory.getLogger(AdvanceManager.class);

    private HibernateDao<ChargeAdvanceEO, Integer> advanceManager;
    private HibernateDao<ChargeAdvanceDetailEO, Integer> advanceDetailManager;
    private SimpleHibernateDao<ChargeUserPayRecordEO, Integer> payRecordDao;
    @Autowired
    private CellManager cellManager;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
	advanceManager = new HibernateDao<ChargeAdvanceEO, Integer>(sessionFactory, ChargeAdvanceEO.class);
	advanceDetailManager = new HibernateDao<ChargeAdvanceDetailEO, Integer>(sessionFactory,
		ChargeAdvanceDetailEO.class);
	payRecordDao = new HibernateDao<ChargeUserPayRecordEO, Integer>(sessionFactory, ChargeUserPayRecordEO.class);
    }

    /**
     * hanx 090728改为每个房间只有一个预存帐号<br>
     * 
     * 如果该房间已经有预存，那么新增一条记录，更新以前的账号剩余改为0
     * 
     * @param houseId
     *            房间编号
     * @param ownerName
     *            业主
     * @param recordYear
     *            预交年份
     * @param bigType
     *            预缴分类 预交、免交（地产公司）、优惠（物业公司）
     * @param type
     *            预交类型 N(个月)
     * @param explain
     * @param userName
     */
    public void saveAdvanceForMoney(String houseId, String ownerName, String recordYear, String bigType, String type,
	    String explain, String userName, float antimoney) {
	List<ChargeAdvanceEO> l = advanceManager.find("from ChargeAdvanceEO where houseId=? and money>0", houseId);

	// 历史预存为0
	if (l.isEmpty()) {
	    ChargeAdvanceEO ca = new ChargeAdvanceEO();
	    ca.setHouseId(houseId);
	    ca.setAntimoney(antimoney);
	    ca.setUseMoney(0);
	    ca.setMoney(antimoney);
	    ca.setBigType(bigType);
	    ca.setType(type + "个月");
	    ca.setRemark(explain);
	    ca.setOwnerName(ownerName);
	    ca.setUserName(userName);

	    advanceManager.save(ca);
	}
	// 历史预存>0
	else {
	    ChargeAdvanceEO ca_1 = l.get(0);
	    ChargeAdvanceEO ca = new ChargeAdvanceEO();
	    ca.setHouseId(houseId);
	    ca.setAntimoney(antimoney);
	    ca.setUseMoney(0);
	    ca.setMoney(antimoney + ca_1.getMoney());
	    ca.setBigType(bigType);
	    ca.setType(type + "个月");
	    ca.setRemark(explain);
	    ca.setOwnerName(ownerName);
	    ca.setUserName(userName);
	    advanceManager.save(ca);

	    ca_1.setUseMoney(ca_1.getAntimoney());
	    ca_1.setMoney(0);
	    ca_1.setRemark(ca_1.getRemark() + "  " + ca.getRecordTime() + "又预存" + ca.getAntimoney() + ",将此次结余自动转为下次的预存");
	}
    }

    /**
     * 权限下取预交列表
     * 
     * @param page
     * @return
     */
    public Page getAdvanceListByPage(Page page, String areaId, String houseId, String ownerName) {

	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
	String where = " and areaId in (" + areaIds + ") ";
	if (areaId != null && !areaId.equals(""))
	    where += " and areaId=" + areaId;
	if (!"".equals(houseId))
	    where += " and houseId='" + houseId + "' ";
	if (!"".equals(ownerName))
	    where += " and ownerName='" + ownerName + "' ";
	page = advanceManager.find(page, "from ChargeAdvanceEO where 1=1 " + where + " order by id desc");
	return page;
    }

    /**
     * 根据小区id 取收费项目
     * 
     * @param areaId
     * @return
     */
    public List getChargeId(int areaId) {
	return advanceManager.find("from ChargeBasedataEO where isUser='使用' and areaId=?", areaId);
    }

    /**
     * 检测houseId是否合法
     * 
     * @param areaId
     * @param houseId
     * @return true合法 false不合法
     */
    public boolean checkHouse(int areaId, String houseId) {
	return cellManager.checkHouse(areaId, houseId);
    }

    /**
     * 保存预存收费项目<br>
     * 保存到预存详情表<br>
     * 
     * @param chargeAdvance
     * @throws Exception
     */
    public void saveAdvanceForChargeId(ChargeAdvanceEO chargeAdvance) throws Exception {

	String userName = (String) Struts2Utils.getSession().getAttribute("userName");
	if (null == userName)
	    throw new RuntimeException("操作人员信息失败，请重新登录！");
	chargeAdvance.setUserName(userName);

	// 查看是否已经有预交项目
	// List l_ =
	// advanceManager.find("from ChargeAdvanceEO where date_format(beginTime,'%Y-%m-%d')>=date_format('"+Utils.dateToString(chargeAdvance.getBeginTime())+"','%Y-%m-%d') "
	// +
	// " and houseId='"+chargeAdvance.getHouseId()+"' and chargeId="+chargeAdvance.getChargeId());
	// if(!l_.isEmpty())
	// throw new RuntimeException("在此时间段内已经存在缴费记录");

	chargeAdvance.setUseMoney(0);
	chargeAdvance.setMoney(chargeAdvance.getAntimoney());

	List<ChargeBasedataEO> l = advanceManager.find("from ChargeBasedataEO where id=?", chargeAdvance.getChargeId());
	chargeAdvance.setChargeName(l.get(0).getChargeName());

	HouseEO house = cellManager.get(chargeAdvance.getHouseId());
	if (house == null)
	    throw new RuntimeException("没有找到该房间资料");

	// 设置业主姓名
	if (house.getOwnerName() != null)
	    chargeAdvance.setOwnerName(house.getOwnerName());
	else
	    chargeAdvance.setOwnerName("");

	advanceManager.save(chargeAdvance);
	saveChgAdvDetail(chargeAdvance);
    }

    public void saveAdvance(ChargeAdvanceEO chargeAdvance) throws Exception {

	String userName = (String) Struts2Utils.getSession().getAttribute("userName");
	if (null == userName)
	    throw new RuntimeException("操作人员信息失败，请重新登录！");

	List<ChargeAdvanceEO> old = advanceManager.find("from ChargeAdvanceEO where house_id="
		+ chargeAdvance.getHouseId());
	if (old.size() > 0) {
	    ChargeAdvanceEO oldEO = old.get(0);
	    chargeAdvance.setUseMoney(oldEO.getUseMoney());
	    chargeAdvance.setMoney(chargeAdvance.getAntimoney() + oldEO.getMoney());
	} else {
	    chargeAdvance.setUseMoney(0);
	    chargeAdvance.setMoney(chargeAdvance.getAntimoney());
	}

	chargeAdvance.setUserName(userName);

	HouseEO house = cellManager.get(chargeAdvance.getHouseId());
	if (house == null)
	    throw new RuntimeException("没有找到该房间资料");

	// 设置业主姓名
	if (house.getOwnerName() != null)
	    chargeAdvance.setOwnerName(house.getOwnerName());
	else
	    chargeAdvance.setOwnerName("");

	advanceManager.save(chargeAdvance);
    }

    /**
     * 保存预存记录表
     * 
     * @param chargeAdvance
     * @throws Exception
     */
    public void saveChgAdvDetail(ChargeAdvanceEO chargeAdvance) throws Exception {
	ChargeAdvanceDetailEO ch = new ChargeAdvanceDetailEO();
	ch.setAreaId(chargeAdvance.getAreaId());
	ch.setBigType(chargeAdvance.getBigType());
	ch.setChargeId(chargeAdvance.getChargeId());
	ch.setChargeName(chargeAdvance.getChargeName());
	ch.setHouseId(chargeAdvance.getHouseId());
	ch.setOwnerName(chargeAdvance.getOwnerName());
	ch.setAntimoney(chargeAdvance.getAntimoney());
	ch.setOughtMoney(chargeAdvance.getAntimoney());
	ch.setSubmitTime(new Date());
	ch.setBh(chargeAdvance.getBh());
	ch.setUserName(chargeAdvance.getUserName());
	ch.setRecordMonth(chargeAdvance.getBeginTime());
	ch.setRemark(chargeAdvance.getRemark());
	// saveAdvanceDetail(ch);
	saveAdvanceUserPayRecord(ch);
    }

    /**
     * 
     * 预计收费项时直接加入缴费纪录
     * 
     * @param chargeAdvance
     */
    public void saveAdvanceUserPayRecord(ChargeAdvanceDetailEO chargeAdvanceDeatil) {

	ChargeUserPayRecordEO pay = new ChargeUserPayRecordEO();
	pay.setAreaId(chargeAdvanceDeatil.getAreaId());
	pay.setHouse(cellManager.getHouse(chargeAdvanceDeatil.getHouseId()));
	pay.setPayName(chargeAdvanceDeatil.getOwnerName());
	pay.setChargeName(chargeAdvanceDeatil.getChargeName());
	pay.setChargeId(chargeAdvanceDeatil.getChargeId());
	pay.setFactMoney(chargeAdvanceDeatil.getAntimoney());
	pay.setPayType(chargeAdvanceDeatil.getBigType());
	pay.setBh(chargeAdvanceDeatil.getBh());
	if (null != chargeAdvanceDeatil.getBigType() && chargeAdvanceDeatil.getBigType().equals("预交"))
	    pay.setReason("现金");
	pay.setRecordMonth(chargeAdvanceDeatil.getRecordMonth());
	pay.setUserName(chargeAdvanceDeatil.getUserName());
	pay.setSubmitTime(chargeAdvanceDeatil.getSubmitTime());
	pay.setGatheringTime(chargeAdvanceDeatil.getSubmitTime());
	payRecordDao.save(pay);
    }

    /**
     * 预存退费
     * 
     * @param id
     * @param bh
     *            单据号(退费情况是空,由原先预交的单据号填充)
     * @return 单据号
     * @throws Exception
     */
    public String refund(String id, String bh) throws Exception {
	ChargeAdvanceEO entity = advanceManager.get(Integer.parseInt(id));

	entity.setType("Y");
	ChargeAdvanceEO dest = new ChargeAdvanceEO();
	BeanUtilEx.copyProperties(dest, entity);
	dest.setId(null);
	dest.setAntimoney(-entity.getMoney());
	dest.setUseMoney(0F);
	dest.setMoney(-entity.getMoney());
	// dest.setBh(bh);
	dest.setRecordTime(new Date());
	advanceManager.save(entity);
	advanceManager.save(dest);
	saveChgAdvDetail(dest);

	return dest.getBh();
    }

    /**
     * 取客户预存款
     * 
     * @param houseId
     * @return
     */
    public String getAdvance(String houseId) {
	return advanceManager.findUnique(
		"select COALESCE(sum(money),0) as money from ChargeAdvanceEO where houseId='" + houseId + "'").toString();
    }

    // public void saveAdvanceDetail(ChargeAdvanceDetailEO entity) throws
    // Exception
    // {
    // List<ChargeAdvanceDetailEO> l =
    // advanceDetailManager.find("from ChargeAdvanceDetailEO where houseId=? and date_format(recordMonth,'%Y-%m')=date_format('"+Utils.dateToString(entity.getRecordMonth()).substring(0,7)+"','%Y-%m') ",entity.getHouseId());
    // if(l.isEmpty())
    // advanceDetailManager.save(entity);
    // else
    // throw new
    // RuntimeException((l.get(0).getRecordMonth().getYear()+1900)+"-"+(l.get(0).getRecordMonth().getMonth()+1)+" 已经存在预交记录！");
    // }
}

package com.acec.wgt.service.chg;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.acec.core.utils.ArithUtils;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.models.baseData.HouseDAO;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chg.ChargeAdvanceEO;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;
import com.acec.wgt.models.chg.ChargePayDetailEO;
import com.acec.wgt.models.chg.ChargeUserPayRecordEO;
import com.acec.wgt.models.chg.PayAdviceRecordEO;
import com.acec.wgt.models.chg.ReleaseEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.CarportManager;

/**
 * 包括费用的收取保存，查询
 * 
 * @author Administrator
 * 
 */
// Spring Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class PayManager implements com.acec.wgt.service.chg.PayMangerI {

    private final Logger logger = LoggerFactory.getLogger(PayManager.class);

    private SimpleHibernateDao<ChargeUserPayRecordEO, Integer> payRecordDao;
    private HibernateDao<ChargeHouseDetailEO, Integer> payDao;
    private SimpleHibernateDao<ChargeAdvanceEO, Integer> advanceDao;
    private HibernateDao<ChargeAdvanceEO, Integer> managerDao;
    private SimpleHibernateDao<PayAdviceRecordEO, Integer> payadvicerecordDao;
    private HibernateDao<ChargePayDetailEO, Integer> chargePayDetailDao;
    @Autowired
    private com.acec.wgt.service.chg.PayRecordManager payRecordManager;
    @Autowired
    private AreaManager areaManager;
    @Autowired
	private CarportManager carportManager;
    @Autowired
    private HouseDAO houseDao;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
	payDao = new HibernateDao<ChargeHouseDetailEO, Integer>(sessionFactory, ChargeHouseDetailEO.class);
	payRecordDao = new SimpleHibernateDao<ChargeUserPayRecordEO, Integer>(sessionFactory,
		ChargeUserPayRecordEO.class);
	advanceDao = new SimpleHibernateDao<ChargeAdvanceEO, Integer>(sessionFactory, ChargeAdvanceEO.class);
	managerDao = new HibernateDao<ChargeAdvanceEO, Integer>(sessionFactory, ChargeAdvanceEO.class);
	payadvicerecordDao = new SimpleHibernateDao<PayAdviceRecordEO, Integer>(sessionFactory, PayAdviceRecordEO.class);
	chargePayDetailDao = new HibernateDao<ChargePayDetailEO, Integer>(sessionFactory, ChargePayDetailEO.class);

    }

    /**
     * 返回一个按公司、楼栋、房间为条件的所有房间的应收，已收，欠收
     * 
     * @param houseId
     * @param beginDate
     * @param endDate
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public Page getHouseChargeDetailALL(Page page, String areaId, String edificeId, String houseId, String beginDate,
	    String endDate, String noArrearage, String wherechargeNames, String managerMen) {
	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
	String where = " where house.areaId in(" + areaIds + ") ";

	if (houseId != null && !houseId.equals("")) {
	    where += " and house.id like '" + houseId + "%'";
	}
	if (edificeId != null && !edificeId.equals("")) {
	    where += " and house.edificeId='" + edificeId + "'";
	}
	if (areaId != null && !areaId.equals("")) {
	    where += " and house.areaId=" + areaId;
	}

	if (managerMen != null && !managerMen.equals(""))
	    where += " and managerMen='" + managerMen + "'";
	if (beginDate != null && !beginDate.equals("")) {
	    where += " and recordMonth>='" + beginDate + "-01'";
	}
	if (endDate != null && !endDate.equals("")) {
	    where += " and recordMonth<='" + endDate + "-01'";
	}
	if ("1".equals(noArrearage)) {// 不欠
	    where += " and arrearageMoney<=0 ";
	} else if ("2".equals(noArrearage))// 还欠
	    where += " and arrearageMoney>0";

	where += wherechargeNames;

	List ls = payDao
		.find("select house.id,ownerName,house.buildnum,chargeName,min(recordMonth) as beginDate,max(recordMonth) as endDate,max(gatheringDate) as gatheringDate,sum(oughtMoney),sum(payMoney),sum(arrearageMoney) from ChargeHouseDetailEO "
			+ where + " group by house.id,ownerName,house.buildnum,chargeName order by house.id");
	page.setTotalCount(ls.size());

	if (!ls.isEmpty()) {
	    int toIndex = page.getFirst() + page.getPageSize();
	    if (toIndex <= ls.size())
		page.setResult(ls.subList(page.getFirst() - 1, toIndex));
	    else
		page.setResult(ls.subList(page.getFirst() - 1, ls.size()));
	}
	return page;
    }

    /**
     * 返回该房间的需要缴费记录(按每月）,进入收费页面
     * 
     * @param houseId
     * @param beginDate
     * @param endDate
     * @param chargeId
     * @param payType
     *            收款方式
     * @return
     */

    public List<String[]> getArreargeHouseByHouseId(String houseId, String beginDate, String endDate, String chargeId,
	    String payType) {
	String appwhere = "";
	if (beginDate != null && !beginDate.equals(""))
	    appwhere += " and recordMonth>='" + beginDate + "-01'";
	if (endDate != null && !endDate.equals(""))
	    appwhere += " and recordMonth<='" + endDate + "-01'";
	if (chargeId != null && !chargeId.equals(""))
	    appwhere += " and chargeId='" + chargeId + "'";
	
	appwhere += " order by recordMonth";
	

	List<ChargeHouseDetailEO> _ls = payDao.find("from ChargeHouseDetailEO where house.id =? and arrearageMoney>0"
		+ appwhere, houseId);

	List<String[]> list = new ArrayList<String[]>();

	if (!_ls.isEmpty()) {

	    for (ChargeHouseDetailEO entity : _ls) {

		String[] str = new String[13];

		// 如果是优惠的话,不需要取与存款了
		if ("privilege".equals(payType)) {
		    str[0] = "0";
		} else {
		    String tmp_chargeId = String.valueOf(entity.getChargeId());
		    String _subChargeId = tmp_chargeId.substring(4, 8);
		    // 不明白干吗单独拎出来 fu 2012-3-11
		    if (_subChargeId.equals("2101")) {// 当预存为能耗费用时，可取任何一个关于能耗费用的预存
			String _chargeId = String.valueOf(entity.getAreaId()) + "2101";
			Double e = (Double) payDao
				.findUnique("select sum(money) as money from ChargeAdvanceEO where houseId='" + houseId
					+ "' and money > 0 and bigType='预交' and chargeId like '" + _chargeId
					+ "%' group by houseId");
			if (e == null) {
			    str[0] = "0";
			} else {
			    str[0] = String.valueOf(e);
			}
		    } else {
			Double e = (Double) payDao
				.findUnique("select sum(money) as money from ChargeAdvanceEO where houseId='" + houseId
					+ "' and money > 0 and bigType='预交' and chargeId= " + tmp_chargeId
					+ " group by houseId");

			if (e == null) {
			    str[0] = "0";
			} else {
			    str[0] = String.valueOf(e);
			}
		    }
		}

		str[1] = entity.getRecordMonth().toString();
		str[2] = entity.getChargeName();
		str[3] = String.valueOf(entity.getOughtMoney());
		str[4] = String.valueOf(entity.getFactMoney());
		str[5] = String.valueOf(entity.getArrearageMoney());
		str[6] = entity.getHouse().getId();
		str[7] = String.valueOf(entity.getId());
		str[8] = entity.getHouse().getId();
		str[9] = entity.getHouse().getBuildnum().toString();
		str[10] = entity.getOwnerName();
		str[11] = String.valueOf(entity.getChargeId());
		str[12] = String.valueOf(entity.getPrivilegeReason()==null?"":entity.getPrivilegeReason());
		list.add(str);
	    }
	}
	return list;
    }

    /**
     * 保存收费记录(按每月),按收款方式的
     * 
     * @param houseId
     *            房间id
     * @param payType
     *            支付类型，是收款，还是优惠
     * @param xh
     *            string[] 缴费项id数组
     * @param advances
     *            每月预扣费用
     * @param privileges
     *            每月优惠费用
     * @param advanceSrc
     *            客户预存总额
     * @param oughtMoneyStr
     *            string[] 每个缴费项的应缴金额
     * @param arrearType
     *            尾数优惠类型 0无 1四舍五入2取整
     * @param userName
     *            收款人
     * @param reason
     *            string 说明
     * @param bh
     *            收据编号 由用户自己填写
     */
    public void savePay(String houseId, String payType, String[] xh, String[] factMoneys, String[] advances,
	    String[] privileges, String[] privilegeReasons, String arrearMoney, String advanceSrc, String userName,
	    String reason, String bh, String gatheringDate) {
	
		HashMap<String, ChargeUserPayRecordEO> charges = new HashMap<String, ChargeUserPayRecordEO>();
		HashMap<String, ArrayList<ChargeHouseDetailEO>> houses = new HashMap<String, ArrayList<ChargeHouseDetailEO>>();

		for (int i = 0; i < xh.length; i++) {
		    ChargeHouseDetailEO a = payDao.get(Integer.parseInt(xh[i]));
		    float oughtMoney = a.getOughtMoney();
	
		    float monthFactMoney = Float.parseFloat(factMoneys[i]);
	
		    if (a.getArrearageMoney() <= 0.001)
			throw new RuntimeException("该用户" + a.getRecordMonth() + a.getChargeName() + "不欠费");
	
		    // 是否交清每月
		    if (oughtMoney == monthFactMoney) {
				a.setOughtMoney(oughtMoney);
				a.setFactMoney(a.getOughtMoney());
				a.setArrearageMoney(0);
		    } else {//新的记录
				float oldOughtMoney = monthFactMoney;
				float newOughtMoney = oughtMoney - monthFactMoney;
				a.setOughtMoney(oldOughtMoney);
				a.setFactMoney(monthFactMoney);
				a.setArrearageMoney(0);
		
				ChargeHouseDetailEO newA = new ChargeHouseDetailEO();
		
				try {
				    BeanUtilEx.copyProperties(newA, a);
				} catch (InvocationTargetException e) {
		
				    e.printStackTrace();
				} catch (IllegalAccessException e) {
		
				    e.printStackTrace();
				}
		
				newA.setId(null);
				newA.setOughtMoney(newOughtMoney);
				newA.setFactMoney(0);
				newA.setArrearageMoney(newOughtMoney);
		
				payDao.save(newA);
		    }
	
		    a.setGatheringDate(Date.valueOf(gatheringDate));
	
		    // 保存收费明细pay_detail
		    float factMoney = a.getFactMoney();
		    float userPayMoeny = 0;
	
		    if (advances != null) {
			if (!advances[i].equals("")) {
			    ChargePayDetailEO b = new ChargePayDetailEO();
			    b.setArea_id(a.getAreaId());
			    b.setCharge_id(a.getChargeId());
			    b.setChargeHouseDetailId(a.getId());
			    b.setGathering_time(a.getGatheringDate());
			    b.setHouseId(houseId);
			    b.setPay_money(Float.parseFloat(advances[i]));
			    b.setPay_type("预扣");
			    b.setPrivilege_reason("预存扣款");
			    b.setRecord_month(a.getRecordMonth());
	
			    chargePayDetailDao.save(b);
	
			    factMoney = factMoney - Float.parseFloat(advances[i]);
			}
		    }
		    if (privileges != null) {
			if (!privileges[i].equals("")) {
			    ChargePayDetailEO b = new ChargePayDetailEO();
			    b.setArea_id(a.getAreaId());
			    b.setCharge_id(a.getChargeId());
			    b.setChargeHouseDetailId(a.getId());
			    b.setGathering_time(a.getGatheringDate());
			    b.setHouseId(houseId);
			    b.setPay_money(Float.parseFloat(privileges[i]));
			    b.setPay_type("优惠");
			    b.setPrivilege_reason(privilegeReasons[i]);
			    b.setRecord_month(a.getRecordMonth());
	
			    chargePayDetailDao.save(b);
	
			    // 写入house detail字段
			    a.setFactMoney(a.getFactMoney() - Float.parseFloat(privileges[i]));
			    a.setPrivilegeMoney(Float.parseFloat(privileges[i]));
			    a.setPrivilegeReason(privilegeReasons[i]);
	
			    factMoney = factMoney - Float.parseFloat(privileges[i]);
			}
		    }
	
		    userPayMoeny = factMoney;
	
		    // 最后一条了
		    if (i == xh.length - 1) {
				// 尾数处理(多收尾数)
				if (Float.parseFloat(arrearMoney) > 0) {
				    // house detail中将多收的尾数增加到最后一月实收上
				    a.setFactMoney(a.getFactMoney() + Math.abs(Float.parseFloat(arrearMoney)));
				    // 用户实收增加
				    userPayMoeny = userPayMoeny + Math.abs(Float.parseFloat(arrearMoney));
				    // pay detail表增加尾数收款纪录
				    ChargePayDetailEO b = new ChargePayDetailEO();
				    b.setArea_id(a.getAreaId());
				    b.setCharge_id(a.getChargeId());
				    b.setChargeHouseDetailId(a.getId());
				    b.setGathering_time(a.getGatheringDate());
				    b.setHouseId(houseId);
				    b.setPay_money(Math.abs(Float.parseFloat(arrearMoney)));
				    b.setPay_type("收款");
				    b.setPrivilege_reason("多收尾数");
				    b.setRecord_month(a.getRecordMonth());
		
				    chargePayDetailDao.save(b);
				    
				    a.setPrivilegeReason(a.getPrivilegeReason() + "; 多收尾数");
				    
				} else if (Float.parseFloat(arrearMoney) < 0) {
		
				    // house detail中将优惠的金额在最后一月实收中扣除
				    a.setFactMoney(a.getFactMoney() - Math.abs(Float.parseFloat(arrearMoney)));
				    // 最后1月实际收款额=应缴-尾数优惠
				    factMoney = factMoney - Math.abs(Float.parseFloat(arrearMoney));
				    // 用户实收减少
				    userPayMoeny = userPayMoeny - Math.abs(Float.parseFloat(arrearMoney));
		
				    ChargePayDetailEO b = new ChargePayDetailEO();
				    b.setArea_id(a.getAreaId());
				    b.setCharge_id(a.getChargeId());
				    b.setChargeHouseDetailId(a.getId());
				    b.setGathering_time(a.getGatheringDate());
				    b.setHouseId(houseId);
				    b.setPay_money(Math.abs(Float.parseFloat(arrearMoney)));
				    b.setPay_type("优惠");
				    b.setPrivilege_reason("尾数优惠");
				    b.setRecord_month(a.getRecordMonth());
		
				    a.setPrivilegeMoney(a.getPrivilegeMoney() + Math.abs(Float.parseFloat(arrearMoney)));
				    a.setPrivilegeReason(a.getPrivilegeReason() + "; 尾数优惠");
		
				    chargePayDetailDao.save(b);
				}
		    }
		    payDao.save(a);// 保存收费表 house_detail
		    
		    //当前月费用缴清后（车位费）会自动更新车位表信息
		    if(getNowAChargeHouseDetailEO(a)){
		    	if(String.valueOf(a.getChargeId()).substring(4, 8).equals("1102"))
					carportManager.updateDate(a.getCarportLeaseId(), a.getBeginDate(), a.getEndDate());
		    }
	
		    if (!charges.containsKey(String.valueOf(a.getChargeId()))) {
			ChargeUserPayRecordEO cupe = new ChargeUserPayRecordEO();
	
			cupe.setBh(bh);
			cupe.setUserName(userName);// 录入人
			cupe.setChargeName(a.getChargeName());// 缴费项目名称
			cupe.setPayType(payType);// 支付类型 收款、优惠、预交、预扣,滞纳金
			cupe.setReason(reason);// 具体说明( 收款分为：现金、转账等 优惠为优惠原因：如尾数优惠，)
			cupe.setFactMoney(a.getFactMoney());// 实际缴费金额
	
			HouseEO houseEO = new HouseEO(houseId);
			cupe.setHouse(houseEO);// 关联房间
			cupe.setPayName(a.getOwnerName());// 保存交款人
			cupe.setGatheringTime(a.getGatheringDate());
			cupe.setSubmitTime(new Date(System.currentTimeMillis()));// 当前时间
			cupe.setChargeHouseDetailId(a.getId());
			cupe.setAreaId(a.getAreaId());// 小区id
			cupe.setChargeId(a.getChargeId());// 收费项id
			cupe.setRecordMonth(a.getRecordMonth());
	
			charges.put(String.valueOf(a.getChargeId()), cupe);
	
			ArrayList<ChargeHouseDetailEO> chdes = new ArrayList<ChargeHouseDetailEO>();
			chdes.add(a);
			houses.put(String.valueOf(a.getChargeId()), chdes);
		    } else {
			ChargeUserPayRecordEO cupe = charges.get(String.valueOf(a.getChargeId()));
			cupe.setFactMoney(cupe.getFactMoney() + a.getFactMoney());
	
			ArrayList<ChargeHouseDetailEO> chdes = houses.get(String.valueOf(a.getChargeId()));
			chdes.add(a);
		    }
	
		    ChargePayDetailEO b = new ChargePayDetailEO();
		    b.setArea_id(a.getAreaId());
		    b.setCharge_id(a.getChargeId());
		    b.setChargeHouseDetailId(a.getId());
		    b.setGathering_time(a.getGatheringDate());
		    b.setHouseId(houseId);
		    b.setPay_money(factMoney);
		    b.setPay_type("收款");
		    b.setPrivilege_reason("");
		    b.setRecord_month(a.getRecordMonth());
	
		    chargePayDetailDao.save(b);
	
		}

		Iterator it = charges.keySet().iterator();
		while (it.hasNext()) {
		    String chargeId = (String) it.next();
		    ChargeUserPayRecordEO cupe = charges.get(chargeId);
		    // 保存用户缴费流水 user_pay_record
		    payRecordManager.savePayRecordMoney(cupe);
	
		    int payId = cupe.getId();
		    ArrayList<ChargeHouseDetailEO> chdes = houses.get(chargeId);
		    for (int x = 0; x < chdes.size(); x++) {
			ChargeHouseDetailEO chde = chdes.get(x);
			chde.setPayId(String.valueOf(payId));
			payDao.save(chde);
		    }
		}

    }
    
    /**
     * 当前房间，当前月，当前收费项目，所欠费用
     * 欠费  false
     * 不欠费 true
     * @param c
     * @return
     */
    public boolean getNowAChargeHouseDetailEO(ChargeHouseDetailEO c){
    	List list = payDao.find("from ChargeHouseDetailEO where chargeId='"+c.getChargeId()+"' and house.id='"+c.getHouse().getId()+"' and arrearageMoney>0 and date_format(recordMonth,'%Y-%m-%d')=date_format('"+c.getRecordMonth()+"','%Y-%m-%d') ");
    	if(list.isEmpty())
    		return true;
    	else
    		return false;
    }

    /**
     * (优惠单独处理，没有完成)
     * 
     * @param houseId
     * @param string
     * @param id
     * @param moneys
     * @param userName
     * @param reason1
     * @param bh
     * @param gatheringDate
     */
    public void savePrivilegeSave(String houseId, String payType, String[] xh, String[] moneys, String userName,
	    String reason, String bh, String gatheringDate) {

	for (int i = 0; i < xh.length; i++) {

	    ChargeHouseDetailEO a = payDao.get(Integer.parseInt(xh[i]));
	    String oughtMoney = String.valueOf(a.getOughtMoney());
	    String privilegeMoney = moneys[i];

	    BigDecimal subMoney = ArithUtils.sub(oughtMoney, privilegeMoney);

	    if (subMoney.signum() == 1) {// 正数
		a.setOughtMoney(subMoney.floatValue());
		a.setFactMoney(0);
		a.setArrearageMoney(subMoney.floatValue());

	    } else if (subMoney.signum() == 0) {//
		a.setFactMoney(a.getOughtMoney());
		a.setArrearageMoney(0);

	    } else if (subMoney.signum() == -1) {// 负数
		throw new RuntimeException("优惠金额不能大于应收金额");
	    }

	    // a.setGatheringDate(Date.valueOf(gatheringDate));

	    payDao.save(a);// 保存收费详情表

	    // 保存用户缴费流水
	    payRecordManager.savePayRecord(houseId, bh, payType, userName, a, a.getFactMoney(), reason);
	}

    }

    /**
     * 内部调用 保存缴费多出的部分放到预存帐户中
     * 
     * @param houseId
     * @param userName
     * @param a
     * @param t
     *            余额
     */
    private void saveAdvanceForPay(String houseId, String userName, final ChargeHouseDetailEO a, float balance,
	    String bh) {
	logger.debug("多出" + balance + "部分准备放入预存,计费项=" + a.getChargeId());
	// 放入预存表中
	ChargeAdvanceEO e = (ChargeAdvanceEO) advanceDao.findUnique("from ChargeAdvanceEO where chargeId = "
		+ a.getChargeId() + " and houseId='" + houseId + "'");
	if (null != e) {// 预存表已有该收费项
	    e.setAntimoney(e.getAntimoney() + balance);// 总预交金额
	    e.setMoney(e.getMoney() + balance);// 剩余金额

	} else {// 预存表没有该收费项，新增
	    e = new ChargeAdvanceEO();
	    e.setBh(bh);
	    e.setBigType("预交");
	    e.setHouseId(houseId);
	    e.setOwnerName(a.getOwnerName());
	    e.setType("1");

	    Calendar cal = Calendar.getInstance();
	    cal.setTime(a.getGatheringDate());
	    e.setAntimoney(balance);// 总预交金额
	    e.setMoney(balance);// 剩余金额
	    e.setRecordTime(a.getGatheringDate());
	    e.setUserName(userName);
	    e.setChargeId(a.getChargeId());
	    e.setAreaId(a.getAreaId());
	    e.setChargeName(a.getChargeName());

	}
	advanceDao.save(e);

	if (balance > 0) {
	    payRecordManager.savePayRecord(houseId, bh, "预交", userName, a, balance, "");
	}

    }

    public List printViewList(String bh) {
	List viewList = new ArrayList();
	try {
	    
	    String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
	    // 非度数的费用，如物业费，车位服务费等等
	    String hql = "select chargeName, min(recordMonth) as minRecord,max(recordMonth) as maxRecord,sum(factMoney) as factMoney from ChargeHouseDetailEO where "
		    + "areaId in ("
		    + areaIds
		    + ") and payId in (select id from ChargeUserPayRecordEO where bh='"+bh+"') and substring(chargeId,5,4)<>'2101' and substring(chargeId,5,4)<>'2105' and substring(chargeId,5,4)<>'2102'  group by chargeName order by chargeName";
	    logger.debug(hql);
	    // 度数的费用，如水费，电费，燃气费等等
	    String _hql = "select chargeName, chargePrice, lastRecord, nowRecord, min(recordMonth) as beginTime,max(recordMonth) as endTime, sum(factMoney) from ChargeHouseDetailEO where "
		    + "areaId in ("
		    + areaIds
		    + ") and payId in (select id from ChargeUserPayRecordEO where bh='"+bh+"') and (substring(chargeId,5,4)='2102' or substring(chargeId,5,4)='2103' or substring(chargeId,5,4)='2104' or substring(chargeId,5,4)='2105') group by chargeName order by chargeName";
	    logger.debug(_hql);

	    List list = payRecordDao.find(hql);
	    List _list = payRecordDao.find(_hql);

	    if (!list.isEmpty()) {
		for (int i = 0; i < list.size(); i++) {
		    String ret[] = new String[9];
		    ret[0] = ((Object[]) list.get(i))[0].toString();
		    ret[1] = "";
		    ret[2] = "";
		    ret[3] = ((Object[]) list.get(i))[1].toString();
		    ret[4] = ((Object[]) list.get(i))[2].toString();
		    ret[5] = ((Object[]) list.get(i))[3].toString();
		    ret[6] = "";
		    ret[7] = "";
		    ret[8] = "";
		    
		    viewList.add(ret);
		}
	    }
	    if (!_list.isEmpty()) {
		for (int i = 0; i < _list.size(); i++) {
		    String ret[] = new String[9];
		    ret[0] = ((Object[]) _list.get(i))[0].toString();
		    ret[1] = String.valueOf(Integer.parseInt(((Object[])_list.get(i))[3].toString()) - Integer.parseInt(((Object[]) _list.get(i))[2].toString()));
		    ret[2] = String.valueOf(((Object[])_list.get(i))[1].toString());
		    ret[3] = ((Object[]) _list.get(i))[4].toString();// 开始日期
		    ret[4] = ((Object[]) _list.get(i))[5].toString();// 截止日期
		    ret[5] = ((Object[]) _list.get(i))[6].toString();
		    ret[6] = String.valueOf(((Object[])_list.get(i))[2].toString());// 上期度数
		    ret[7] = String.valueOf(((Object[])_list.get(i))[3].toString());// 本期度数
		    
		    viewList.add(ret);
		}
	    }

	} catch (Exception e) {

	}

	return viewList;
    }

    public List printView(String bh) {
	List viewList = new ArrayList();
	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
	List list = payRecordDao
		.find("select bh,payName,house.id,sum(factMoney) as factMoney,userName,DATE_FORMAT(gatheringTime,'%Y-%m-%d') as gatheringTime "
			+ " from ChargeUserPayRecordEO where bh='"
			+ bh
			+ "' and areaId in ("
			+ areaIds
			+ ") and payType<>'优惠' and payType<>'免交' and payType<>'预扣' group by bh,payName,house,userName,DATE_FORMAT(gatheringTime,'%Y-%m-%d')");
	if (!list.isEmpty()) {
	    for (int i = 0; i < list.size(); i++) {
		String ret[] = new String[7];
		ret[0] = ((Object[]) list.get(i))[0].toString();
		if (null != ((Object[]) list.get(i))[1])
		    ret[1] = ((Object[]) list.get(i))[1].toString();
		else
		    ret[1] = "";
		if (null != ((Object[]) list.get(i))[2])
		    ret[2] = ((Object[]) list.get(i))[2].toString();
		else
		    ret[2] = "";

		if (null != ((Object[]) list.get(i))[2]) {
		    String[] strparam = ret[2].split("-");
		    ret[2] = strparam[1] + "-" + strparam[2];
		}
		ret[3] = ((Object[]) list.get(i))[3].toString();

		ret[4] = ArithUtils.convertToCapitalMoney(Double.valueOf(ret[3]));
		ret[5] = ((Object[]) list.get(i))[4].toString();
		ret[6] = ((Object[]) list.get(i))[5].toString();
		viewList.add(ret);
	    }
	}
	return viewList;
    }

    /**
     * 根据收据单号查询detail
     * 
     * @param bh
     * @return
     */
    public List getDetailByPayId(String payId) {
	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");

	String hql = "select chargeName, gatheringDate, oughtMoney, factMoney, arrearageMoney, privilegeMoney, privilegeReason, recordMonth, house.id, ownerName from ChargeHouseDetailEO where payId ='"
		+ payId + "' and  areaId in (" + areaIds + ")";
	logger.debug(hql);

	return payRecordDao.find(hql);

    }

    /**
     * 收据单查询
     * 
     * @param bh
     * @return
     */
    public List getReceiptForBh(String bh) {
	logger.debug("收据单号是：" + bh);

	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");

	String hql = "select id, house.id, payName, chargeName, factMoney, userName, gatheringTime from ChargeUserPayRecordEO where bh ='"
		+ bh + "' and  areaId in (" + areaIds + ")";
	logger.debug(hql);

	return payRecordDao.find(hql);
    }


    // 此方法是判断相同的用户名是否同时存在两个或多个不同的房间号
    public List getReceiptForONHouse(String ownerName) {
	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
	return payDao.find(
		"select house.id,payName,house.houseName from ChargeUserPayRecordEO where payName = ? and areaId in ("
			+ areaIds + ") group by house.id,payName,house.houseName", ownerName);
    }

    public List getReceiptForHouseId(String houseId) {
	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");

	String hql = "select bh, house.id, payName, sum(factMoney) as factMoney, userName, gatheringTime from ChargeUserPayRecordEO where house.id ='"
		+ houseId + "' and  areaId in (" + areaIds + ") group by bh order by bh";
	logger.debug(hql);

	return payRecordDao.find(hql);
    }

    /**
     * 得到新的收据单编号 string prefix 编号前缀
     */
    public String getNewBh(String prefix) {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

	String rq = sdf.format(new java.util.Date());
	String bhlike = prefix + rq;

	List _ls = payDao.find("select max(bh) from ChargeUserPayRecordEO where bh like ?", bhlike + "%");

	if (_ls.get(0) == null || "".equals(_ls.get(0).toString())) {
	    return bhlike + "0001";
	} else {
	    String _str = _ls.get(0).toString();
	    int nextIncrease = Integer.parseInt(_str.substring(12)) + 1;
	    if (nextIncrease < 10)
		return bhlike + "000" + nextIncrease;
	    else if (nextIncrease < 100)
		return bhlike + "00" + nextIncrease;
	    else if (nextIncrease < 1000)
		return bhlike + "0" + nextIncrease;
	    else
		return bhlike + nextIncrease;
	}
    }

    /**
     * 保存交费记录，没有bh的情况
     * 
     * @param chargeUserPayRecord
     */
    public void savePayRecordMoney(ChargeUserPayRecordEO chargeUserPayRecord) {

	String userName = (String) Struts2Utils.getSession().getAttribute("userName");

	if (null == chargeUserPayRecord.getUserName() || "".equals(chargeUserPayRecord.getUserName()))
	    chargeUserPayRecord.setUserName(userName);

	// String bh = getNewBh("C");//获得收据编号
	// chargeUserPayRecord.setBh(bh);

	payRecordDao.save(chargeUserPayRecord);
    }

    /**
     * 取 零星 有偿服务 等不和业主关联的费用缴费记录 根据条件搜索相应的交费记录
     * 
     * @param areaId
     *            默认为0
     * @param chargeName
     *            零星收费、有偿服务收费
     * @param beginTime
     * @param endTime
     * @return
     */
    public Page getPayRecord(Page page, int areaId, String chargeBigType, String beginTime, String endTime) {

	StringBuffer where = new StringBuffer("");
	if (areaId > 0)
	    where.append(" and areaId=" + areaId);
	if (!"".equals(chargeBigType))
	    where.append("and chargeBigType='" + chargeBigType + "'");
	if (!"".equals(beginTime))
	    where.append("and gatheringTime >='" + beginTime + "'");
	if (!"".equals(endTime))
	    where.append("and gatheringTime <='" + endTime + " 23:59:59'");

	return getUserChargeRecord(page, where.toString());

    }

    /**
     * 取所有交费记录
     */
    public Page getUserChargeRecord(Page page, String where) {

	return payDao.find(page, "from ChargeUserPayRecordEO where 1=1 " + where + " order by id desc");
    }

    /**
     * 取所有预交记录
     */
    public Page getAdvanceListByPage(Page page) {

	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
	String hql = "from ChargeAdvanceEO where areaId in (" + areaIds + ") and bigType='预交' order by recordTime";
	return managerDao.find(page, hql);
    }

    public void printPaymentAdviceInitSave(String areaId) {
	// 删除以往记录
	String hql = "delete PayAdviceRecordEO where areaId=" + areaId;
	payDao.createQuery(hql).executeUpdate();

	// 重新生成
	hql = "select distinct areaId,areaName,edificeId,edificeName,cell from HouseEO where areaId=" + areaId;

	List<Object[]> ls = payDao.createQuery(hql).list();
	for (Object[] objs : ls) {

	    payadvicerecordDao.save(new PayAdviceRecordEO((Integer) objs[0], (String) objs[1], (String) objs[2],
		    (String) objs[3], Integer.parseInt(objs[4].toString()), 0));
	}

    }

    /**
     * 得到上次催缴单的催缴截止日期
     * 
     * @param adviceId
     * @return
     */
    public String getPrintPaymentAdviceDate(String adviceId) {
	String hql = "select oughtpayDate from PayAdviceRecordEO where id=" + adviceId;
	Object obj = payDao.findUnique(hql);
	if (obj != null)
	    return obj.toString();
	else
	    return "";
    }

    /**
     * 打印催缴单
     * 
     * @param areaId
     * @param edificeId
     * @param cellId
     * @param adviceId
     *            催缴单记录表的id
     * @return
     */
    public Map getPrintPaymentAdvice(String areaId, String edificeId, String cellId, String houseId, String adviceId,
	    String oughtpayDate) {
	// 催缴单次数增加1
	String hql = "update PayAdviceRecordEO set advicecount=advicecount+1,oughtpayDate='" + oughtpayDate
		+ "' where id=" + adviceId;
	payDao.createQuery(hql).executeUpdate();

	return getPrintPaymentAdvice(areaId, edificeId, cellId, houseId);
    }

    /**
     * 打印费用交费单
     * 
     * @param areaId
     * @param edificeId
     * @param cellId
     * @return List集合元素objects[9]
     */
    public Map getPrintPaymentAdvice(String areaId, String edificeId, String cellId, String houseId) {

	// String where=" and chg.area_id="+areaId+" and edifice.id="+edificeId;

	// String
	// sql="select house.house_address, year(record_month) as year,DATEPART(quarter, record_month) as recordQuarter,"
	// +
	// "charge_name, sum(arrearage_money) from tb_chg_house_detail chg,tb_basedata_house house,tb_basedata_edifice edifice "
	// +
	// " where chg.house=house.id and house.edifice=edifice.id "+where
	// +" group by house.house_address,year(record_month),DATEPART(quarter, record_month),charge_name ";
	//
	// return payDao.getSession().createSQLQuery(sql).list();
	String where = "";
	if (houseId != null && !houseId.equals(""))
	    where = " and house.id='" + houseId + "'";
	else if (edificeId != null && !edificeId.equals(""))
	    where = " and house.edificeId='" + edificeId + "' ";
	else if (areaId != null && !areaId.equals(""))
	    where = " and areaId=" + areaId;

	if (cellId != null && !cellId.equals(""))
	    where += " and house.cell=" + cellId;

	String hql = " select house.houseAddress,ownerName,chargeName,sum(userNum),max(chargePrice),sum(arrearageMoney),"
		+ " min(recordMonth),max(recordMonth),min(house.inDate) from ChargeHouseDetailEO where arrearageMoney>0 "
		+ where + " group by house.houseAddress,ownerName,chargeName";

	// //如果是打印催缴单
	// if(count!=null&&!count.equals("")){
	//
	// String where1 =
	// " and house.id in(select houseId from PayAdviceRecordEO where count"+count;
	//
	// }

	List _ls = payDao.find(hql);

	Map<String, List> map = new HashMap<String, List>();
	for (int i = 0; i < _ls.size(); i++) {
	    Object[] objs = (Object[]) _ls.get(i);
	    String houseAddress = (String) objs[0];
	    if (map.get(houseAddress) == null) {
		map.put(houseAddress, new ArrayList());
	    }
	    List ls = map.get(houseAddress);
	    ls.add(objs);
	}

	return map;
    }

    /**
     * 催缴次数记录表
     * 
     * @param areaId
     * @return
     */
    public List getPrintPaymentAdviceInitList(String areaId) {
	String hql = "from PayAdviceRecordEO where areaId=" + areaId;

	return payDao.find(hql);
    }

    public HouseEO gethouseId(String houseId) {
	String hql = "from HouseEO where id='" + houseId + "'";
	return (HouseEO) payDao.findUnique(hql);
    }

    /**
     * 物业公司预交费用保存 包含优惠部分
     * 
     * @param chargeAdvance
     * @param isadvance
     *            是否优惠
     * @param release
     * @param chargeBasedataId
     * @param cb
     * @throws Exception
     */
    public void savePrePay(ChargeAdvanceEO chargeAdvance, ReleaseEO release, String isadvance, String chargeBasedataId)
	    throws Exception {

	// 如果在该时间段内有缴费记录
	List<ChargeAdvanceEO> l3 = advanceDao.find("from ChargeAdvanceEO where houseId=?", chargeAdvance.getHouseId());
	if (!l3.isEmpty())
	    throw new RuntimeException("缴费时间段有重复或者本次缴费时间段小于历史缴费时间段");

	List<ChargeAdvanceEO> l2 = advanceDao.find("from ChargeAdvanceEO where  houseId=?", chargeAdvance.getHouseId());
	ChargeAdvanceEO ca = l2.get(0);
	if (null != ca)// 存在预缴记录
	{

	    ca.setRemark("上次剩余" + ca.getMoney() + "元作为累计到本次余额");
	    // 新剩余金额 = 历史剩余+当前预缴
	    ca.setMoney(ca.getMoney() + chargeAdvance.getAntimoney());
	    ca.setAntimoney(chargeAdvance.getAntimoney());
	    ca.setUseMoney(0);
	    ca.setBigType(chargeAdvance.getBigType());
	    ca.setType(chargeAdvance.getType());
	    ca.setUserName(chargeAdvance.getUserName());
	    ca.setRecordTime(new Date(System.currentTimeMillis()));
	    advanceDao.save(ca);

	    return;
	} else {
	    chargeAdvance.setUseMoney(0);
	    chargeAdvance.setMoney(chargeAdvance.getAntimoney());
	}

	HouseEO house = houseDao.get(chargeAdvance.getHouseId());
	if (house.getOwnerName() != null)
	    chargeAdvance.setOwnerName(house.getOwnerName());
	else
	    chargeAdvance.setOwnerName("");

	advanceDao.save(chargeAdvance);

	// 加入缴费纪录
	ChargeUserPayRecordEO pay = new ChargeUserPayRecordEO();
	pay.setAreaId(house.getAreaId());
	pay.setHouse(new HouseEO(chargeAdvance.getHouseId()));

	List<ChargeBasedataEO> l = advanceDao.find("from ChargeBasedataEO where id=?",
		Integer.parseInt(chargeBasedataId));
	if (l.isEmpty())
	    throw new RuntimeException("收费项目不存在");
	ChargeBasedataEO cb = l.get(0);

	pay.setPayName(chargeAdvance.getOwnerName());
	pay.setChargeName(cb.getChargeName());
	// pay.setChargeOtherName(cb.getOtherName());
	pay.setChargeId(cb.getId());
	pay.setFactMoney(chargeAdvance.getAntimoney());
	pay.setPayType("预交");
	pay.setGatheringTime(chargeAdvance.getRecordTime());
	savePayRecordMoney(pay);

    }

    public ChargeHouseDetailEO getHouseChargeDetailById(int id) {
	return payDao.get(id);
    }

    /**
     * 退还保证金
     * 
     * @param id
     */
    public void tuihuanFY(int id, Object obj) {
	String sql = "";
	if (Integer.parseInt(obj.toString()) != 0)
	    sql = " ,returnMoney=" + obj;

	payRecordDao.createQuery("update ChargeUserPayRecordEO set isBack=true " + sql + " where id=" + id)
		.executeUpdate();
    }

    /**
     * 通过ID得到收费记录
     * 
     * @param id
     * @return
     */
    public ChargeUserPayRecordEO getUserRecord(String id) {
	return (ChargeUserPayRecordEO) payRecordDao.findUnique(" from ChargeUserPayRecordEO where id=" + id);
    }

    /**
     * 在收费页面中，从预存中扣除金额
     */
    public Float calAdvance(String houseId, int xh, String userName, String chargeId) {
	Float _money = 0F;
	String str[] = houseId.split("-");
	String _subChargeId = str[0] + chargeId.substring(4, 8);

	// 修改为 只搜当前 余额>0 的记录
	List<ChargeAdvanceEO> adv = null;
	if (chargeId.substring(4, 8).equals("2101"))// 为能耗费用时，取相似的预交项
	    adv = payDao.find("from ChargeAdvanceEO where houseId='" + houseId + "' and money > 0 and bigType='预交'"
		    + "and chargeId like '" + _subChargeId + "%' ");
	else
	    adv = payDao.find("from ChargeAdvanceEO where houseId='" + houseId + "' and money > 0 and bigType='预交'"
		    + "and chargeId=" + chargeId);
	ChargeAdvanceEO e = null;
	if (!adv.isEmpty())
	    e = adv.get(0);
	if (null != e) {
	    ChargeHouseDetailEO a = (ChargeHouseDetailEO) payDao.findUnique("from ChargeHouseDetailEO where id=" + xh
		    + "and chargeId=" + chargeId);

	    logger.debug("应缴钱：" + a.getOughtMoney() + ";帐号的钱：" + e.getMoney());
	    Float accountMoney = e.getMoney();
	    if (accountMoney <= 0) {// 帐号的钱为0
		return 0F;
	    }
	    if (accountMoney >= a.getArrearageMoney()) {// 帐号的钱比应欠款金额的多
		// 已用
		e.setMoney(accountMoney - a.getArrearageMoney());
		// 更新已用金额
		e.setUseMoney(e.getUseMoney() + a.getArrearageMoney());
		_money = a.getArrearageMoney();
		// 不欠费了
		a.setFactMoney(_money + a.getFactMoney());
		a.setArrearageMoney(0F);
	    } else {
		throw new RuntimeException("余额不足");
	    }

	    a.setGatheringDate(new Date(e.getRecordTime().getTime()));
	    payDao.getSession().save(e);
	    payDao.save(a);

	    logger.debug("帐号总金额：" + e.getAntimoney() + "帐号已用：" + e.getUseMoney() + "帐号剩余：" + e.getMoney());
	    payRecordManager.savePayRecord(houseId, e.getBh(), "预扣", userName, a, _money, "");
	}
	return _money;
    }

    // 得到前一日收费项目汇总
    public List beforeMoney(String beforeDay) {
	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
	Map map = getArea();

	String hql = "select areaId,chargeName,sum(factMoney) from ChargeUserPayRecordEO where areaId in (" + areaIds
		+ ") and date_format(gatheringTime,'%Y-%m-%d') ='" + beforeDay + "' "
		+ " group by areaId,chargeName order by areaId";
	List list = payRecordDao.find(hql);

	if (!list.isEmpty()) {
	    List _list = new ArrayList();
	    for (int i = 0; i < list.size(); i++) {
		String str[] = new String[3];

		str[0] = String.valueOf(map.get(Integer.parseInt(((Object[]) list.get(i))[0].toString())));
		str[1] = ((Object[]) list.get(i))[1].toString();
		str[2] = ((Object[]) list.get(i))[2].toString();
		_list.add(str);
	    }
	    return _list;
	} else
	    return null;
    }

    public Map getArea() {
		Map map = new HashMap();
		List<AreaEO> list = areaManager.getAreaALL();
		for (AreaEO area : list) {
		    map.put(area.getId(), area.getAreaName());
		}
		return map;
    }

    public String getStateHouse(String houseId){
    	StringBuffer sb = new StringBuffer();
    	sb.append("SELECT MAX(date_format(recordMonth,'%Y-%m')) as recordMonth FROM ChargeHouseDetailEO where house.id='").append(houseId)
    	  .append("' and oughtMoney=factMoney and chargeId like '").append(houseId.split("-")[0].toString()).append("1101%'");
    	return payDao.find(sb.toString()).toString();
    }
    
    public static void main(String args[]) {

	BigDecimal obj = new BigDecimal(-20.15);
	int i = obj.signum();
	System.out.println("\nobject value : " + obj + "\nmethod generated value : " + i);

	obj = new BigDecimal(0);
	System.out.println("\nobject value : " + obj + "\nmethod generated value : " + obj.signum());

	obj = new BigDecimal(145);
	System.out.println("\nobject value : " + obj + "\nmethod generated value : " + obj.signum());

    }
}
package com.acec.wgt.service.basedata;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.orm.hibernate.SimpleHibernateDao;
import com.acec.core.utils.ExcelInOut;
import com.acec.core.utils.StringUtil;
import com.acec.core.utils.Utils;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.models.baseData.CarportDAO;
import com.acec.wgt.models.baseData.CarportEO;
import com.acec.wgt.models.baseData.CarportLeaseDetailsEO;
import com.acec.wgt.models.baseData.CarportLeaseEO;
import com.acec.wgt.models.baseData.EdificeEO;
import com.acec.wgt.models.baseData.HouseDAO;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataDAO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chg.ChargeAdvanceEO;
import com.acec.wgt.models.chg.ChargeHouseDetailDAO;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;
import com.acec.wgt.models.chg.ChargePayDetailEO;
import com.acec.wgt.models.chg.ChargeUserPayRecordEO;
import com.acec.wgt.models.chg.PayAdviceRecordEO;
import com.acec.wgt.service.chg.PayManager;
import com.acec.wgt.service.chg.PayRecordManager;
import com.opensymphony.xwork2.ActionContext;

//Spring Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class CarportManager {

    private final Logger logger = LoggerFactory.getLogger(CarportManager.class);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private HouseDAO houseDao;
    @Autowired
    private CarportDAO carportDao;
    @Autowired
    private ChargeBasedataDAO chargeBasedataDao;
    @Autowired
    private ChargeHouseDetailDAO chargeHouseDetailDao;
    private HibernateDao<ChargeHouseDetailEO, Integer> payDao;
    private HibernateDao<ChargePayDetailEO, Integer> chargePayDetailDao;

    private HibernateDao<CarportEO, Integer> carportManager;
    private HibernateDao<CarportLeaseEO, Integer> carportLeaseManager;

    @Autowired
    private PayRecordManager payRecordManager;
    @Autowired
    private PayManager payManager;
    @Autowired
    private AreaManager areaManager;
    @Autowired
    private CellManager cellManager;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
	carportManager = new HibernateDao<CarportEO, Integer>(sessionFactory, CarportEO.class);
	carportLeaseManager = new HibernateDao<CarportLeaseEO, Integer>(sessionFactory, CarportLeaseEO.class);
	payDao = new HibernateDao<ChargeHouseDetailEO, Integer>(sessionFactory, ChargeHouseDetailEO.class);
	chargePayDetailDao = new HibernateDao<ChargePayDetailEO, Integer>(sessionFactory, ChargePayDetailEO.class);
    }

    /**
     * 
     * @param page
     * @param state
     *            状态
     * @param areaId
     *            小区id 如果没有areaid 默认为0，必要要有值
     * @param bigType
     *            机动车 非机动车
     * @param carCode
     *            车位编号
     * @param houseId
     *            房间编号
     * @return
     */
    public PaginatorTag getAllCarPortState(int no, int i, String state, int areaId, String bigType, String houseId,
	    String carCode,String enddays) {
	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
	String where = "";
	if (areaId > 0)
	    where += " and a.area=" + areaId;
	if (!state.equals(""))
	    where += " and a.state='" + state + "'";
	if (!bigType.equals(""))
	    where += " and a.type='" + bigType + "'";
	if (!"".equals(houseId))
	    where += " and b.house_id='" + houseId + "' ";
	if (!carCode.equals(""))
	    where += " and a.car_code='" + carCode + "' ";
	if(StringUtils.isNotEmpty(enddays)&&StringUtil.isInteger(enddays))
		where +=" and datediff(b.end_date,now())<="+enddays;
	
	return carportDao.getAllCarPortState(no, i, areaIds, where);
    }

    /**
     * 当前车位状态
     * 
     * @param areaId
     */
    public void writeNowCarportExc(String areaId) {
	List<String[]> retList = new ArrayList<String[]>();
	String[] temp = { "小区编号", "车位编号", "房间编号", "车位属性", "截止日期" };
	retList.add(temp);
	List list = carportDao.getNowCarport(areaId);
	if (!list.isEmpty()) {
	    for (int i = 0; i < list.size(); i++) {
		String[] s = new String[5];
		s[0] = ((Object[]) list.get(i))[0].toString();
		s[1] = ((Object[]) list.get(i))[1].toString();
		s[2] = ((Object[]) list.get(i))[2].toString();
		s[3] = ((Object[]) list.get(i))[3].toString();
		s[4] = ((Object[]) list.get(i))[4].toString();
		if (!"".equals(s[4]))
		    s[4] = s[4].substring(0, 10);
		retList.add(s);
	    }
	}
	ExcelInOut eIO = new ExcelInOut();
	Boolean ret = eIO.writeExc(retList);
	if (!ret)
	    throw new RuntimeException("下载失败");
    }

    /**
     * 根据车位id取车位信息
     * 
     * @param carportId
     * @return
     */
    public CarportEO getCarportById(int id) {

	return carportDao.get(id);
    }

    /**
     * 制作excel文件
     * 
     * @param areaId
     */
    public void writeExc(String areaId) {
	List<String[]> retList = new ArrayList<String[]>();
	String[] temp = { "小区编号", "车位编号", "车位位置(地面，地下,车库)", "面积", "状态(空置，出租，出售)", "类型(机动车:1,非机动车:2)" };
	retList.add(temp);
	if (areaId != null) {
	    String[] s = new String[6];
	    s[0] = areaId;
	    s[4] = "空置";
	    retList.add(s);
	}
	ExcelInOut eIO = new ExcelInOut();
	Boolean ret = eIO.writeExc(retList);
	if (!ret)
	    throw new RuntimeException("下载失败");
    }

    /**
     * 上传的文件保存
     * 
     * @param theFile
     */
    public void saveForExc(File theFile) {
	ExcelInOut eIO = new ExcelInOut();
	List<String[]> list = eIO.readExc(theFile);
	// String[]
	// temp={"小区编号","车位编号(小区编号-编号-编号)","车位位置(地面，地下)","面积","状态(默认：空置)","类型(机动车:1,非机动车:2)"};

	for (int i = 0; i < list.size(); i++) {

	    CarportEO carport = new CarportEO();

	    // 设置小区编号
	    if (null != list.get(i)[0] && !"".equals(list.get(i)[0])) {
		AreaEO area = new AreaEO();
		area.setId(Integer.parseInt(list.get(i)[0]));
		carport.setArea(area);
	    } else {
		throw new RuntimeException("第" + (i + 1) + "行记录的小区编号为空。");
	    }

	    // 设置车位编号
	    if (null != list.get(i)[1] && !"".equals(list.get(i)[1])) {
		carport.setCarCode(list.get(i)[1]);
	    } else {
		throw new RuntimeException("第" + (i + 1) + "行记录的车位编号为空。");
	    }

	    // 设置车位位置
	    if (null != list.get(i)[2] && !"".equals(list.get(i)[2])) {
		carport.setLocal(list.get(i)[2]);
	    } else {
		throw new RuntimeException("第" + (i + 1) + "行记录的车位位置为空。");
	    }

	    if (null != list.get(i)[3] && !"".equals(list.get(i)[3])) {
		try {
		    carport.setMianji(Float.parseFloat(list.get(i)[3].toString()));
		} catch (NumberFormatException e) {
		    throw new RuntimeException("第" + (i + 1) + "行记录的车位面积只能是数字");
		}
	    }
	    if (null != list.get(i)[4] && !"".equals(list.get(i)[4])) {
		try {
		    carport.setState(list.get(i)[4].toString());
		} catch (NumberFormatException e) {
		    throw new RuntimeException("第" + (i + 1) + "行记录的车位面积只能是数字");
		}
	    }

	    if (null != list.get(i)[5] && !"".equals(list.get(i)[5])) {
		carport.setType(list.get(i)[5]);
	    } else {
		throw new RuntimeException("第" + (i + 1) + "行记录的车位类型为空。");
	    }
	    save(carport);
	}
    }

    /**
     * 更新车位状态表
     * 
     * @param state
     */
    public void updateState(String state, int carportId) {
	// TODO Auto-generated method stub
	int id = getMaxId();
	// carportManager.createQuery("update CarportEO set state='"+state+"',carportLeaseId="+id+" where id="+carportId).executeUpdate();
	carportDao.updateState(state, carportId, id);
    }

    public void save(CarportEO entity) {
	// carportManager.save(entity);
	carportDao.save(entity);
    }

    /**
     * 
     * @param type
     *            机动车、非机动车
     * @param page
     * @param where
     * @return
     */
    public PaginatorTag getCarportByPage(int no, int i, String where) {
	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
	return carportDao.getCarportByPage(no, i, areaIds, where);
    }

    // public PaginatorTag getCarportByPage2(String type,int no , int i, String
    // where) {
    // String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
    // return carportDao.getCarportByPage2( no, i ,type, areaIds, where);
    // }
    //
    // public PaginatorTag getCarportByPage3(String type,int no , int i, String
    // where) {
    // String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
    // return carportDao.getCarportByPage3(no , i , type, areaIds, where);
    // }

    /**
	 * 
	 */
    public CarportEO getcarCode(String carCode, String areaId) {
	return carportDao.getcarCode(carCode, areaId);
    }

    /**
     * 当前房间的某项租赁费用 的车位租赁详情
     * 
     * @param houseId
     * @param chargeId
     * @return
     */
    public CarportLeaseEO getCarportAll(String houseId, int chargeId) {

	List<CarportLeaseEO> l = carportDao.getCarportAll(houseId, chargeId);
	if (!l.isEmpty())
	    return l.get(0);
	else
	    return null;
    }
    
    /**
     * 车位分配保存
     * @param c
     * @param houseId
     * @param areaId
     * @param chongdian
     * @param factMoney
     * @param bh
     * @param userName
     * @throws Exception
     */
    public void saveCarportLease1(CarportLeaseEO c, String houseId, int areaId, String chongdian,
	    String factMoney, String bh, String userName) throws Exception {
	String submitName = (String) Struts2Utils.getSession().getAttribute("userName");
	if (null == submitName)
	    throw new RuntimeException("取操作员信息失败，请重新登录");
	HouseEO h = houseDao.getHouse(houseId);
	if (null == h)// 没有取到业主名称
	    throw new RuntimeException("取业主名称错误，请确认该房间有业主");

	Session session = carportLeaseManager.getSession();
	// 更新历史车位状态
	carportDao.updCarportHistory(c.getCarport().getId());
	c.setHouseId(houseId);
	c.setAreaId(areaId);
	c.setIsNow("当前");
	c.setFactMoney(0);
	c.setBeginDate(c.getInDate());
	c.setEndDate(c.getOutDate());
	
	//fjd at 2015.4.7 将空置车位状态改成出租
	if("空置".equals(c.getState())){
		c.setState("出租");
	}
	// 填业主名称
	if (null == c.getOwnerName() || "".equals(c.getOwnerName()))
	    c.setOwnerName(h.getOwnerName());
	session.save(c);

	//更新车位基础表
	carportDao.updCarport(c.getId(), c.getCarport().getId(), c.getState());

	
//2012-8-13新增  如果应收款为0，则不需要收取费用
	if(!StringUtils.isEmpty(factMoney)&&Float.valueOf(factMoney)>0)
	{
		ChargeBasedataEO ch = chargeBasedataDao.getChargeBasedataByChargeId(c.getChargeId());
		if (null == ch)
		    throw new RuntimeException("您还没有设置车位收费项目！");
		saveToCarportLeaseDetail1(c, session, houseId, ch, h, factMoney, bh, userName);

		// 电瓶车充电费
		//if (null != chongdian && "yes".equals(chongdian)) {
		    // ch = (ChargeBasedataEO)
		    // carportManager.findUnique("from ChargeBasedataEO where id ='"+areaId+"110207'");
		//    ch = chargeBasedataDao.getChargeBasedataByChargeId(Integer.parseInt(areaId + "110207"));
		//    if (ch == null)
		//	throw new RuntimeException("您还没有设置电动车充电的收费项目！");
		//    saveToCarportLeaseDetail1(c, session, houseId, ch, h, factMoney, bh, userName);
		//}
	}
	
	

    }

    public void saveCarportLeaseTemp(CarportLeaseEO c, String houseId, int areaId, String factMoney, String bh,
	    String userName) throws Exception {
	String submitName = (String) Struts2Utils.getSession().getAttribute("userName");
	if (null == submitName)
	    throw new RuntimeException("取操作员信息失败，请重新登录");
	HouseEO h = houseDao.getHouse(houseId);
	if (null == h)// 没有取到业主名称
	    throw new RuntimeException("取业主名称错误，请确认该房间有业主");

	Session session = carportLeaseManager.getSession();
	// 更新历史车位状态
	// carportDao.updCarportHistory(c.getCarport().getId());
	
	
	//临时停车的记录直接变成历史记录
	c.setHouseId(houseId);
	c.setAreaId(areaId);
	c.setIsNow("历史");
	c.setFactMoney(0);
	c.setBeginDate(c.getInDate());
	c.setEndDate(c.getOutDate());

	// 填业主名称
	if (null == c.getOwnerName() || "".equals(c.getOwnerName()))
	    c.setOwnerName(h.getOwnerName());

	session.save(c);

	// carportDao.updCarport(c.getId(), c.getCarport().getId(),
	// c.getState());

	ChargeBasedataEO ch = chargeBasedataDao.getChargeBasedataByChargeId(c.getChargeId());
	if (null == ch)
	    throw new RuntimeException("您还没有设置车位收费项目！");
	saveToCarportLeaseDetail1(c, session, houseId, ch, h, factMoney, bh, userName);

    }

    /**
     * 根据车位编号的ID，在车位记录列表中得到记录
     * 
     * @param carportId
     * @return
     */
    public CarportLeaseEO getCarportLeaseByCarportId(int carportId) {
	List l = carportLeaseManager.find("from CarportLeaseEO where isNow='当前' and carport.id='" + carportId + "' ");
	if (!l.isEmpty())
	    return (CarportLeaseEO) l.get(0);
	else
	    return null;
    }

    /**
     * 续交
     * 
     * @param c
     * @throws Exception
     */
    @SuppressWarnings({ "deprecation", "unchecked" })
    public void saveCarportLeaseAther(CarportLeaseEO c, Date inDate, Date outDate, Date beginDate, Date endDate)
	    throws Exception {
	String submitName = (String) Struts2Utils.getSession().getAttribute("userName");
	if (null == submitName)
	    throw new RuntimeException("取操作员信息失败，请重新登录");
	CarportLeaseEO carportLease = new CarportLeaseEO();
	HouseEO h = houseDao.getHouse(c.getHouseId());
	Session session = carportLeaseManager.getSession();
	// 更新历史车位状态
	session.createSQLQuery(
		"update tb_basedata_carport_lease set is_now='历史' where carport=" + c.getCarport().getId())
		.executeUpdate();
	
	//fjd at 2015.4.7 将空置车位状态改成出租
	if("空置".equals(c.getState())){
		carportLease.setState("出租");
	}
	
	carportLease.setBigType(c.getBigType());
	carportLease.setType(c.getType());
	carportLease.setCarNums(c.getCarNums());
	carportLease.setInDate(inDate);
	carportLease.setOutDate(outDate);
	carportLease.setLocal(c.getLocal());
	carportLease.setCarport(c.getCarport());
	carportLease.setState(c.getState());
	carportLease.setMianji(c.getMianji());
	carportLease.setCarCode(c.getCarCode());
	carportLease.setChargeId(c.getChargeId());
	carportLease.setOwnerName(c.getOwnerName());
	carportLease.setHouseId(c.getHouseId());
	carportLease.setFactMoney(c.getFactMoney());
	carportLease.setElectric(c.getElectric());
	carportLease.setAreaId(c.getAreaId());
	carportLease.setIsNow("当前");
	carportLease.setBeginDate(beginDate);
	carportLease.setEndDate(endDate);
	carportLease.setCarColor(c.getCarColor());
	carportLease.setPhone(c.getPhone());
	carportLease.setCardNum(c.getCardNum());
	session.save(carportLease);

	// id,作为传递交费时用的序号
	Map map = ActionContext.getContext().getSession();
	map.put("carportLeaseId", carportLease.getId());
	// 更新车位基础表 当前状态
	//fjd at 2015-4-7
	carportDao.updCarport(carportLease.getId(),carportLease.getCarport().getId(), carportLease.getState());
	

	ChargeBasedataEO ch = (ChargeBasedataEO) carportManager.findUnique("from ChargeBasedataEO where id ="
		+ carportLease.getChargeId());
	saveToCarportLeaseDetailAther1(carportLease, session, ch, h);

	// 电瓶车充电费
	if (null != carportLease.getElectric() && "是".equals(carportLease.getElectric())) {
	    ch = (ChargeBasedataEO) carportManager.findUnique("from ChargeBasedataEO where id ='"
		    + carportLease.getAreaId() + "110207' ");
	    if (ch == null)
		throw new RuntimeException("您还没有设置电动车充电的收费项目！");
	    saveToCarportLeaseDetailAther1(carportLease, session, ch, h);
	}
    }

    public void saveCarportLeaseAther1(CarportLeaseEO c, String inDate, String outDate, String factMoney, String bh) {
	String submitName = (String) Struts2Utils.getSession().getAttribute("userName");
	if (null == submitName)
	    throw new RuntimeException("取操作员信息失败，请重新登录");
	Date _inDate = new Date();
	Date _outDate = new Date();
	try {
	    _inDate = Utils.strToDate(inDate);
	    _outDate = Utils.strToDate(outDate);
	} catch (Exception e) {
	    e.printStackTrace();
	    throw new RuntimeException("开始截止时间格式错误！");
	}
	HouseEO h = houseDao.getHouse(c.getHouseId());

	Session session = carportLeaseManager.getSession();
	CarportLeaseEO carportLease = new CarportLeaseEO();
	// 更新历史车位状态
	session.createSQLQuery(
		"update tb_basedata_carport_lease set is_now='历史' where carport=" + c.getCarport().getId())
		.executeUpdate();
	carportLease.setBigType(c.getBigType());
	carportLease.setType(c.getType());
	carportLease.setCarNums(c.getCarNums());
	carportLease.setInDate(_inDate);
	carportLease.setOutDate(_outDate);
	carportLease.setLocal(c.getLocal());
	carportLease.setCarport(c.getCarport());
	
	//fjd at 2015.4.7 将空置车位状态改成出租
	if("空置".equals(c.getState())){
			carportLease.setState("出租");
	}else{
		carportLease.setState(c.getState());
	}
	
	
	
	carportLease.setMianji(c.getMianji());
	carportLease.setCarCode(c.getCarCode());
	carportLease.setChargeId(c.getChargeId());
	carportLease.setOwnerName(c.getOwnerName());
	carportLease.setHouseId(c.getHouseId());
	carportLease.setFactMoney(c.getFactMoney());
	carportLease.setElectric(c.getElectric());
	carportLease.setAreaId(c.getAreaId());
	carportLease.setIsNow("当前");
	carportLease.setBeginDate(_inDate);
	carportLease.setEndDate(_outDate);
	carportLease.setCarColor(c.getCarColor());
	carportLease.setPhone(c.getPhone());
	carportLease.setCardNum(c.getCardNum());
	session.save(carportLease);
	
	// 更新车位基础表 当前状态
	
	//fjd at 2015.4.7 将空置车位状态改成出租e
	String updateState="";
	if(!"出售".equals(c.getState())){
			updateState+=",state='出租' ";
	}
	
	session.createSQLQuery(
		"update tb_basedata_carport set carport_lease_id=" + carportLease.getId() +updateState+ " where id="
			+ carportLease.getCarport().getId()).executeUpdate();

	ChargeBasedataEO ch = (ChargeBasedataEO) carportManager.findUnique("from ChargeBasedataEO where id ="
		+ c.getChargeId());

	// 电瓶车充电费
	if (null != c.getElectric() && "是".equals(c.getElectric())) {
	    ch = (ChargeBasedataEO) carportManager.findUnique("from ChargeBasedataEO where id ='" + c.getAreaId()
		    + "110207' ");
	    if (ch == null)
		throw new RuntimeException("您还没有设置电动车充电的收费项目！");
	}

	//2012-8-23新增  如果应收款为0，则不需要收取费用
	if(!StringUtils.isEmpty(factMoney)&&Float.valueOf(factMoney)>0)
	{
		ChargeHouseDetailEO chargeHouseDetail = new ChargeHouseDetailEO();
	
		chargeHouseDetail.setRecordMonth(new java.sql.Date(c.getRecordMonth().getTime()));
	
		chargeHouseDetail.setAreaId(ch.getAreaId());
		chargeHouseDetail.setHouseType(h.getHouseType());
		chargeHouseDetail.setHouse(h);
		chargeHouseDetail.setGatheringDate(new java.sql.Date(c.getRecordMonth().getTime()));
		chargeHouseDetail.setOwnerName(c.getOwnerName());
		chargeHouseDetail.setLastRecord(0);
		chargeHouseDetail.setNowRecord(0);
		chargeHouseDetail.setUserNum(0);
		chargeHouseDetail.setChargeUnit(ch.getPriceUnit());
		chargeHouseDetail.setChargeType(ch.getChargeType());
		chargeHouseDetail.setChargeName(ch.getChargeName());
		chargeHouseDetail.setChargePrice(ch.getPriceValue());
		chargeHouseDetail.setOughtMoney(Float.parseFloat(factMoney));
		chargeHouseDetail.setArrearageMoney(0);
		chargeHouseDetail.setFactMoney(Float.parseFloat(factMoney));
		chargeHouseDetail.setChargeId(ch.getId());
		chargeHouseDetail.setPrivilegeReason(inDate + "至" + outDate);
		chargeHouseDetail.setBeginDate(new java.sql.Date(c.getInDate().getTime()));
		chargeHouseDetail.setEndDate(new java.sql.Date(c.getEndDate().getTime()));
		chargeHouseDetail.setCarportLeaseId(c.getId());
		session.save(chargeHouseDetail);// 保存每月费用表
	
		// 保存缴费明细表
		ChargePayDetailEO b = new ChargePayDetailEO();
		b.setArea_id(chargeHouseDetail.getAreaId());
		b.setCharge_id(chargeHouseDetail.getChargeId());
		b.setChargeHouseDetailId(chargeHouseDetail.getId());
		b.setGathering_time(chargeHouseDetail.getGatheringDate());
		b.setHouseId(h.getId());
		b.setPay_money(chargeHouseDetail.getFactMoney());
		b.setPay_type("收款");
		b.setPrivilege_reason("");
		b.setRecord_month(chargeHouseDetail.getRecordMonth());
	
		chargePayDetailDao.save(b);
	
		// 保存缴费单表
		ChargeUserPayRecordEO cupe = new ChargeUserPayRecordEO();
	
		cupe.setBh(bh);
		cupe.setUserName(submitName);// 录入人
		cupe.setChargeName(chargeHouseDetail.getChargeName());// 缴费项目名称
		cupe.setPayType("收款");// 支付类型 收款、优惠、预交、预扣,滞纳金
		cupe.setReason("");// 具体说明( 收款分为：现金、转账等 优惠为优惠原因：如尾数优惠，)
		cupe.setFactMoney(chargeHouseDetail.getFactMoney());// 实际缴费金额
	
		cupe.setHouse(h);// 关联房间
		cupe.setPayName(chargeHouseDetail.getOwnerName());// 保存交款人
		cupe.setGatheringTime(chargeHouseDetail.getGatheringDate());
		cupe.setSubmitTime(new Date(System.currentTimeMillis()));// 当前时间
		cupe.setChargeHouseDetailId(chargeHouseDetail.getId());
		cupe.setAreaId(chargeHouseDetail.getAreaId());// 小区id
		cupe.setChargeId(chargeHouseDetail.getChargeId());// 收费项id
		cupe.setRecordMonth(chargeHouseDetail.getRecordMonth());
	
		payRecordManager.savePayRecordMoney(cupe);
	
		// 更新house_detail缴费单id
		int payId = cupe.getId();
		chargeHouseDetail.setPayId(String.valueOf(payId));
		payDao.save(chargeHouseDetail);
	}

 }

    /**
     * 
     * @param carportLeaseId
     * @param beginDate
     * @param endDate
     */
    public void updateDate(int carportLeaseId, Date beginDate, Date endDate) {
	carportLeaseManager
		.getSession()
		.createSQLQuery(
			"update tb_basedata_carport_lease set begin_date='" + beginDate + "',end_date='" + endDate
				+ "' where id=" + carportLeaseId).executeUpdate();
    }

    /**
     * 根据续交月份得到交费情况
     * 
     * @param c
     * @param session
     * @param ch
     * @param o
     * @throws Exception
     */
    private void saveToCarportLeaseDetailAther(CarportLeaseEO c, Session session, ChargeBasedataEO ch, HouseEO h)
	    throws Exception {

	List list = Utils.getMonthDate(c.getInDate(), c.getOutDate());
	try {
	    for (int j = 0; j < list.size(); j = j + 2) {
		Calendar ca1 = Calendar.getInstance();
		ca1.setTime(Utils.strToDate(list.get(j).toString()));// 设置每次的开始时间
		ca1.add(ca1.MONTH, 1);
		String recordMonth = ca1.get(Calendar.YEAR) + "-" + ca1.get(Calendar.MONTH) + "-01";
		ChargeHouseDetailEO chargeHouseDetail = new ChargeHouseDetailEO();

		chargeHouseDetail.setRecordMonth(new java.sql.Date(Utils.strToDate(recordMonth).getTime()));
		chargeHouseDetail.setAreaId(ch.getAreaId());

		chargeHouseDetail.setHouseType(h.getHouseType());
		chargeHouseDetail.setHouse(h);

		chargeHouseDetail.setOwnerName(c.getOwnerName());
		chargeHouseDetail.setLastRecord(0);
		chargeHouseDetail.setNowRecord(0);
		chargeHouseDetail.setUserNum(0);
		chargeHouseDetail.setChargeUnit("元/月");
		chargeHouseDetail.setChargeType("固定类");
		chargeHouseDetail.setChargeName(ch.getChargeName());
		chargeHouseDetail.setChargePrice(ch.getPriceValue());
		chargeHouseDetail.setOughtMoney(ch.getPriceValue());
		chargeHouseDetail.setArrearageMoney(ch.getPriceValue());
		chargeHouseDetail.setFactMoney(0);
		chargeHouseDetail.setChargeId(ch.getId());

		chargeHouseDetail.setBeginDate(new java.sql.Date(Utils.strToDate(list.get(j).toString()).getTime()));
		chargeHouseDetail.setEndDate(new java.sql.Date(Utils.strToDate(list.get(j + 1).toString()).getTime()));
		chargeHouseDetail.setCarportLeaseId(c.getId());

		session.save(chargeHouseDetail);// 保存每月费用表

		// 保存到租赁详细记录
		// CarportLeaseDetailsEO cd = new CarportLeaseDetailsEO();
		// cd.setAreaId(c.getAreaId());
		// cd.setBigType(c.getBigType());
		// cd.setCarportId(c.getCarport().getId());
		// cd.setCarportLeaseId(c.getId());
		// cd.setChargeId(ch.getId());
		// cd.setChargeName(ch.getChargeName());
		// cd.setHouseId(c.getHouseId());
		// cd.setLocal(c.getLocal());
		// cd.setMianji(c.getMianji());
		// cd.setOwnerName(c.getOwnerName());
		// cd.setRecordMonth(new
		// java.sql.Date(Utils.strToDate(recordMonth).getTime()));
		// cd.setState(c.getState());
		// cd.setType(c.getType());
		// cd.setOughtMoney(chargeHouseDetail.getOughtMoney());
		// cd.setArrearageMoney(chargeHouseDetail.getArrearageMoney());
		// cd.setChargeHouseDetailId(chargeHouseDetail.getId());
		// cd.setCarCode(c.getCarCode());
		// session.save(cd);
	    }
	} catch (Exception e) {
	    throw new RuntimeException("所得收费项目不正确，请确认后再输入");
	}
    }

    private void saveToCarportLeaseDetailAther1(CarportLeaseEO c, Session session, ChargeBasedataEO ch, HouseEO h)
	    throws Exception {

	try {
	    int y1 = (c.getOutDate().getYear() - c.getInDate().getYear());
	    int m2 = y1 * 12 + (c.getOutDate().getMonth() - c.getInDate().getMonth());
	    float money = m2 * ch.getPriceValue();

	    ChargeHouseDetailEO chargeHouseDetail = new ChargeHouseDetailEO();

	    chargeHouseDetail.setRecordMonth(new java.sql.Date(new Date().getTime()));
	    chargeHouseDetail.setAreaId(ch.getAreaId());

	    chargeHouseDetail.setHouseType(h.getHouseType());
	    chargeHouseDetail.setHouse(h);

	    chargeHouseDetail.setOwnerName(c.getOwnerName());
	    chargeHouseDetail.setLastRecord(0);
	    chargeHouseDetail.setNowRecord(0);
	    chargeHouseDetail.setUserNum(0);
	    chargeHouseDetail.setChargeUnit("元/月");
	    chargeHouseDetail.setChargeType("固定类");
	    chargeHouseDetail.setChargeName(ch.getChargeName());
	    chargeHouseDetail.setChargePrice(ch.getPriceValue());
	    chargeHouseDetail.setOughtMoney(money);
	    chargeHouseDetail.setArrearageMoney(money);
	    chargeHouseDetail.setFactMoney(0);
	    chargeHouseDetail.setChargeId(ch.getId());

	    chargeHouseDetail.setBeginDate(new java.sql.Date(c.getInDate().getTime()));
	    chargeHouseDetail.setEndDate(new java.sql.Date(c.getOutDate().getTime()));
	    chargeHouseDetail.setCarportLeaseId(c.getId());

	    session.save(chargeHouseDetail);// 保存每月费用表

	} catch (Exception e) {
	    throw new RuntimeException("所得收费项目不正确，请确认后再输入");
	}
    }

    /**
     * 保存到房间交费记录和车位租赁详情
     * 
     * @param c
     * @param session
     * @param houseId
     * @param ch
     * @param o
     * @throws Exception
     */
    private void saveToCarportLeaseDetail(CarportLeaseEO c, Session session, String houseId, ChargeBasedataEO ch,
	    HouseEO h) throws Exception {
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
	if (c.getInDate().after(c.getOutDate())) {
	    throw new RuntimeException("开始时间不能小于结束时间");
	}

	List list = Utils.getMonthDate(c.getInDate(), c.getOutDate());

	for (int i = 0; i < list.size(); i += 2) {
	    ChargeHouseDetailEO chargeHouseDetail = new ChargeHouseDetailEO();

	    chargeHouseDetail.setRecordMonth(new java.sql.Date(Utils.strToDate(
		    list.get(i).toString().substring(0, 7) + "-01").getTime()));
	    chargeHouseDetail.setAreaId(ch.getAreaId());

	    chargeHouseDetail.setHouseType(h.getHouseType());
	    chargeHouseDetail.setHouse(h);

	    chargeHouseDetail.setOwnerName(c.getOwnerName());
	    chargeHouseDetail.setLastRecord(0);
	    chargeHouseDetail.setNowRecord(0);
	    chargeHouseDetail.setUserNum(0);
	    chargeHouseDetail.setChargeUnit(ch.getPriceUnit());
	    chargeHouseDetail.setChargeType(ch.getChargeType());
	    chargeHouseDetail.setChargeName(ch.getChargeName());
	    chargeHouseDetail.setChargePrice(ch.getPriceValue());
	    chargeHouseDetail.setOughtMoney(ch.getPriceValue());
	    chargeHouseDetail.setArrearageMoney(ch.getPriceValue());
	    chargeHouseDetail.setFactMoney(0);
	    chargeHouseDetail.setChargeId(ch.getId());
	    chargeHouseDetail.setBeginDate(new java.sql.Date(c.getBeginDate().getTime()));
	    chargeHouseDetail.setEndDate(new java.sql.Date(c.getEndDate().getTime()));
	    chargeHouseDetail.setCarportLeaseId(c.getId());
	    session.save(chargeHouseDetail);// 保存每月费用表

	    // 保存到租赁详细记录
	    // CarportLeaseDetailsEO cd = new CarportLeaseDetailsEO();
	    // cd.setAreaId(c.getAreaId());
	    // cd.setBigType(c.getBigType());
	    // cd.setCarportId(c.getCarport().getId());
	    // cd.setCarportLeaseId(c.getId());
	    // cd.setChargeId(ch.getId());
	    // cd.setChargeName(ch.getChargeName());
	    // cd.setHouseId(houseId);
	    // cd.setLocal(c.getLocal());
	    // cd.setMianji(c.getMianji());
	    // cd.setOwnerName(c.getOwnerName());
	    // cd.setRecordMonth(new
	    // java.sql.Date(Utils.strToDate(list.get(i).toString().substring(0,7)+"-01").getTime()));
	    // cd.setState(c.getState());
	    // cd.setType(c.getType());
	    // cd.setOughtMoney(chargeHouseDetail.getOughtMoney());
	    // cd.setArrearageMoney(chargeHouseDetail.getArrearageMoney());
	    // cd.setChargeHouseDetailId(chargeHouseDetail.getId());
	    // cd.setCarCode(c.getCarCode());
	    // session.save(cd);
	}

	// //保存到月详情表
	// int zongyue = 0;
	// //z只有一个月
	// if(sf.format(c.getOutDate()).substring(0,7).equals(sf.format(c.getInDate())))
	// zongyue = 1;
	// else
	// zongyue = 12 *
	// (c.getOutDate().getYear()-c.getInDate().getYear())+c.getOutDate().getMonth()-c.getInDate().getMonth()+1;
	// logger.debug("总月数据："+zongyue);
	//
	// java.util.Calendar ca1 = Calendar.getInstance();
	// ca1.setTime(c.getInDate());
	// ca1.add(ca1.MONTH, 1);
	// //循环次数为 租赁总月数 - 优惠月数
	// for(int i=0;i<zongyue;i++)
	// {
	//
	// String recordMonth =
	// ca1.get(Calendar.YEAR)+"-"+ca1.get(Calendar.MONTH)+"-01";
	// ChargeHouseDetailEO chargeHouseDetail=new ChargeHouseDetailEO();
	//
	// chargeHouseDetail.setRecordMonth(new
	// java.sql.Date(Utils.strToDate(recordMonth).getTime()));
	// chargeHouseDetail.setManagerMen("");
	// chargeHouseDetail.setAreaId(ch.getAreaId());
	// chargeHouseDetail.setHouseType(o.getHouse().getHousetype());
	// chargeHouseDetail.setChargeBigType(ch.getChargeBigType());
	// chargeHouseDetail.setHouse(o.getHouse());
	// chargeHouseDetail.setHabitationType("");
	// chargeHouseDetail.setOwnerName(c.getOwnerName());
	// chargeHouseDetail.setLastRecord(0);
	// chargeHouseDetail.setNowRecord(0);
	// chargeHouseDetail.setUserNum(0);
	// chargeHouseDetail.setChargeUnit("元/月");
	// chargeHouseDetail.setChargeType("固定类");
	// chargeHouseDetail.setChargeName(ch.getChargeName());
	// chargeHouseDetail.setOtherName(ch.getOtherName());
	// chargeHouseDetail.setChargePrice(ch.getPriceValue());
	// chargeHouseDetail.setOughtMoney(ch.getPriceValue());
	// chargeHouseDetail.setArrearageMoney(ch.getPriceValue());
	// chargeHouseDetail.setFactMoney(0);
	// chargeHouseDetail.setPayMoney(0);
	// chargeHouseDetail.setChargeId(ch.getId());
	// session.save(chargeHouseDetail);//保存每月费用表
	//
	// //保存到租赁详细记录
	// CarportLeaseDetailsEO cd = new CarportLeaseDetailsEO();
	// cd.setAreaId(c.getAreaId());
	// cd.setBigType(c.getBigType());
	// cd.setCarportId(c.getCarport().getId());
	// cd.setCarportLeaseId(c.getId());
	// cd.setChargeId(ch.getId());
	// cd.setChargeName(ch.getChargeName());
	// cd.setChargeBigType(ch.getChargeBigType());
	// cd.setHouseId(houseId);
	// cd.setLocal(c.getLocal());
	// cd.setMianji(c.getMianji());
	// cd.setOwnerName(c.getOwnerName());
	// cd.setRecordMonth(new
	// java.sql.Date(Utils.strToDate(recordMonth).getTime()));
	// cd.setState(c.getState());
	// cd.setType(c.getType());
	// cd.setOughtMoney(chargeHouseDetail.getOughtMoney());
	// cd.setArrearageMoney(chargeHouseDetail.getArrearageMoney());
	// cd.setChargeHouseDetailId(chargeHouseDetail.getId());
	// cd.setCarCode(c.getCarCode());
	// cd.setOtherName(ch.getOtherName());
	// session.save(cd);
	//
	// //取开始月份的下一个月
	// ca1.add(ca1.MONTH, 1);
	// }
    }

    private void saveToCarportLeaseDetail1(CarportLeaseEO c, Session session, String houseId, ChargeBasedataEO ch,
	    HouseEO h, String factMoney, String bh, String userName) throws Exception {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	String _inDate = sdf.format(c.getInDate());
	String _outDate = sdf.format(c.getOutDate());
	
	ChargeHouseDetailEO chargeHouseDetail = new ChargeHouseDetailEO();

	chargeHouseDetail.setRecordMonth(new java.sql.Date(c.getRecordMonth().getTime()));

	chargeHouseDetail.setAreaId(ch.getAreaId());
	chargeHouseDetail.setHouseType(h.getHouseType());
	chargeHouseDetail.setHouse(h);
	chargeHouseDetail.setGatheringDate(new java.sql.Date(c.getRecordMonth().getTime()));
	chargeHouseDetail.setOwnerName(c.getOwnerName());
	chargeHouseDetail.setLastRecord(0);
	chargeHouseDetail.setNowRecord(0);
	chargeHouseDetail.setUserNum(0);
	chargeHouseDetail.setChargeUnit(ch.getPriceUnit());
	chargeHouseDetail.setChargeType(ch.getChargeType());
	chargeHouseDetail.setChargeName(ch.getChargeName());
	chargeHouseDetail.setChargePrice(ch.getPriceValue());
	chargeHouseDetail.setOughtMoney(Float.parseFloat(factMoney));
	chargeHouseDetail.setArrearageMoney(0);
	chargeHouseDetail.setFactMoney(Float.parseFloat(factMoney));
	chargeHouseDetail.setChargeId(ch.getId());
	chargeHouseDetail.setPrivilegeReason(_inDate+"至"+_outDate);
	chargeHouseDetail.setBeginDate(new java.sql.Date(c.getBeginDate().getTime()));
	chargeHouseDetail.setEndDate(new java.sql.Date(c.getEndDate().getTime()));
	chargeHouseDetail.setCarportLeaseId(c.getId());
	session.save(chargeHouseDetail);// 保存每月费用表

	// 保存缴费明细表
	ChargePayDetailEO b = new ChargePayDetailEO();
	b.setArea_id(chargeHouseDetail.getAreaId());
	b.setCharge_id(chargeHouseDetail.getChargeId());
	b.setChargeHouseDetailId(chargeHouseDetail.getId());
	b.setGathering_time(chargeHouseDetail.getGatheringDate());
	b.setHouseId(houseId);
	b.setPay_money(chargeHouseDetail.getFactMoney());
	b.setPay_type("收款");
	b.setPrivilege_reason("");
	b.setRecord_month(chargeHouseDetail.getRecordMonth());

	chargePayDetailDao.save(b);

	// 保存缴费单表
	ChargeUserPayRecordEO cupe = new ChargeUserPayRecordEO();

	cupe.setBh(bh);
	cupe.setUserName(userName);// 录入人
	cupe.setChargeName(chargeHouseDetail.getChargeName());// 缴费项目名称
	cupe.setPayType("收款");// 支付类型 收款、优惠、预交、预扣,滞纳金
	cupe.setReason("");// 具体说明( 收款分为：现金、转账等 优惠为优惠原因：如尾数优惠，)
	cupe.setFactMoney(chargeHouseDetail.getFactMoney());// 实际缴费金额

	HouseEO houseEO = new HouseEO(houseId);
	cupe.setHouse(houseEO);// 关联房间
	cupe.setPayName(chargeHouseDetail.getOwnerName());// 保存交款人
	cupe.setGatheringTime(chargeHouseDetail.getGatheringDate());
	cupe.setSubmitTime(new Date(System.currentTimeMillis()));// 当前时间
	cupe.setChargeHouseDetailId(chargeHouseDetail.getId());
	cupe.setAreaId(chargeHouseDetail.getAreaId());// 小区id
	cupe.setChargeId(chargeHouseDetail.getChargeId());// 收费项id
	cupe.setRecordMonth(chargeHouseDetail.getRecordMonth());

	payRecordManager.savePayRecordMoney(cupe);

	// 更新house_detail缴费单id
	int payId = cupe.getId();
	chargeHouseDetail.setPayId(String.valueOf(payId));
	payDao.save(chargeHouseDetail);

	// 当前月费用缴清后（车位费）更新车位表信息
	updateDate(chargeHouseDetail.getCarportLeaseId(), chargeHouseDetail.getBeginDate(),
		chargeHouseDetail.getEndDate());
    }

    /**
     * 保存出售车位
     * 
     * @param houseId
     * @param areaId
     * @param carportLease
     */
    public void saveCarportLeaseCS(CarportLeaseEO c, String houseId, String areaId) throws Exception {
	Session session = carportLeaseManager.getSession();
	if (null == c.getOwnerName() || "".equals(c.getOwnerName()))
	    c.setOwnerName("");

	c.setHouseId(houseId);
	c.setAreaId(Integer.parseInt(areaId));
	c.setIsNow("当前");
	// 更新历史车位状态
	carportDao.updCarportHistory(c.getCarport().getId());

	// 保存车位租售记录 车位单独分开
	if (c.getLocal().toString().equals("车库"))
	    c.setChargeId(Integer.parseInt(areaId + "110209"));
	else
	    c.setChargeId(Integer.parseInt(areaId + "110201"));
	c.setFactMoney(0);
	c.setOutDate(Utils.strToDate("2099-12-31"));
	session.save(c);

	// 更新车位基础表 当前状态
	carportDao.updTbCarport(c.getId(), c.getCarport().getId());
    }

    // 更新车位id isUer = false
    private void updateLeaseState(int carportId) {
	// carportLeaseManager.createQuery("update CarportLeaseEO set isNow='历史' where carport.id="+carportId).executeUpdate();
	carportDao.updCarportHistory(carportId);
    }

    /**
     * 取当前最大id
     * 
     * @return
     */
    public int getMaxId() {

	// List<Object> l =
	// carportLeaseManager.find("select max(id) from CarportLeaseEO");
	List<Object> l = carportDao.getMaxId();

	if (l.isEmpty())
	    return 0;
	else if (null == l.get(0) || "".equals(l.get(0)))
	    return 0;
	else
	    return (Integer) l.get(0);
    }

    /**
     * 逻辑删除车位
     * 
     * @param parameter
     */
    public void del(Integer carportId) {
	// carportManager.createQuery("update CarportEO set isDel=false where id="+carportId).executeUpdate();
	carportDao.delCarport(carportId);
    }

    /**
     * 取车位月缴费详情
     * 
     * @param parseInt
     * @return
     */
    public Page getHouseDetail(Page page, int carportId) {
	// CarportLeaseEO c = (CarportLeaseEO)
	// carportManager.findUnique("from CarportLeaseEO where isNow='当前' and carport.id="+carportId);
	// CarportLeaseEO c = carportDao.getCarportLeaseBycarportId(carportId);
	CarportLeaseEO car = carportLeaseManager.findUnique("from CarportLeaseEO where isNow='当前' and carport.id="
		+ carportId);
	if (null == car)
	    throw new RuntimeException("当前不需要交费");

	return carportManager
		.findPage(
			page,
			"from CarportLeaseDetailsEO where (chargeId like '%1102%' or chargeId like '%3102%') and arrearageMoney > 0 and houseId='"
				+ car.getHouseId() + "' order by recordMonth");
	// return carportDao.getHouseDetail(no , i ,car.getHouseId());
    }

    public void updateCarportPayMoney(String factMoney, String detailId) {

	Session session = carportManager.getSession();

	String submitName = (String) Struts2Utils.getSession().getAttribute("userName");
	if (null == submitName)
	    throw new RuntimeException("取操作员信息失败，请重新登录");

	// ChargeHouseDetailEO ch = (ChargeHouseDetailEO)
	// carportManager.findUnique("from ChargeHouseDetailEO where id="+detailId);
	ChargeHouseDetailEO ch = chargeHouseDetailDao.getChargeHouseDetailById(detailId);

	// 更新房间交费详情表
	// session.createQuery("update ChargeHouseDetailEO set gatheringDate=getDate(), payMoney=payMoney+"+Float.valueOf(factMoney)+",arrearageMoney=oughtMoney - payMoney - "+Float.valueOf(factMoney)+" where id="+detailId).executeUpdate();
	chargeHouseDetailDao.updChargeHDPayMoney(Float.valueOf(factMoney), detailId);
	// 更新车位交费详情表
	// session.createQuery("update CarportLeaseDetailsEO set gatheringDate=getDate(), payMoney=payMoney+"+Float.valueOf(factMoney)+",arrearageMoney=oughtMoney - payMoney - "+Float.valueOf(factMoney)+" where chargeHouseDetailId="+detailId).executeUpdate();
	carportDao.updChargeHDPayMoney(Float.valueOf(factMoney), detailId);

	// 保存到缴费单 优惠费用
	ChargeUserPayRecordEO b = new ChargeUserPayRecordEO();
	// b.setUserName(userName);//录入人
	b.setRecordMonth(ch.getRecordMonth());// 收费季度
	b.setChargeName(ch.getChargeName());// 缴费项目名称
	b.setChargeId(ch.getChargeId());

	b.setPayType("优惠");// 支付类型 收款、减免、预交、预扣,滞纳金
	b.setFactMoney(Float.valueOf(factMoney));// 实际缴费金额

	b.setHouse(ch.getHouse());// 关联房间
	b.setAreaId(ch.getAreaId());
	b.setPayName(ch.getOwnerName());// 保存交款人

	b.setUserName(submitName);
	b.setSubmitTime(new Date(System.currentTimeMillis()));// 当前时间
	b.setChargeHouseDetailId(ch.getId());
	// b.setRecordQuarter(quarterMap.get("");
	// 在预交处添加到缴费单
	payRecordManager.savePayRecordMoney(b);
    }

    public void savePayMoney(String[] factMoney, String[] detailId) {
	String submitName = (String) Struts2Utils.getSession().getAttribute("userName");
	if (null == submitName)
	    throw new RuntimeException("取操作员信息失败，请重新登录");
	Session session = carportManager.getSession();

	for (int i = 0; i < detailId.length; i++) {
	    if (null == factMoney[i] || "".equals(factMoney[i]))
		throw new RuntimeException("没有录入费用！如果没有该项目没有费用就录入 0 ");

	    // 保存到缴费详情
	    // session.createQuery("update ChargeHouseDetailEO set gatheringDate=getDate(),factMoney=factMoney+"+Float.valueOf(factMoney[i])+",payMoney=payMoney+"+Float.valueOf(factMoney[i])+",arrearageMoney=oughtMoney - payMoney - "+Float.valueOf(factMoney[i])+" where id="+detailId[i]).executeUpdate();
	    chargeHouseDetailDao.updChargeHDALLMoney(Float.valueOf(factMoney[i]), detailId[i]);
	    ChargeHouseDetailEO ch = payManager.getHouseChargeDetailById(Integer.parseInt(detailId[i]));
	    // 更新车位月详情
	    // session.createQuery("update CarportLeaseDetailsEO set gatheringDate=getDate(),factMoney=factMoney+"+Float.valueOf(factMoney[i])+",payMoney=payMoney+"+Float.valueOf(factMoney[i])+",arrearageMoney=oughtMoney - payMoney - "+Float.valueOf(factMoney[i])+" where chargeHouseDetailId="+detailId[i]).executeUpdate();
	    carportDao.updChargeHDALLMoney(Float.valueOf(factMoney[i]), detailId[i]);

	    // 保存到缴费单
	    ChargeUserPayRecordEO b = new ChargeUserPayRecordEO();
	    // b.setUserName(userName);//录入人
	    b.setRecordMonth(ch.getRecordMonth());// 收费季度
	    b.setChargeName(ch.getChargeName());// 缴费项目名称
	    b.setChargeId(ch.getChargeId());
	    b.setPayType("收款");// 支付类型 收款、减免、预交、预扣,滞纳金

	    b.setFactMoney(Float.valueOf(factMoney[i]));// 实际缴费金额

	    b.setHouse(ch.getHouse());// 关联房间
	    b.setAreaId(ch.getAreaId());
	    b.setPayName(ch.getOwnerName());// 保存交款人
	    b.setUserName(submitName);
	    b.setSubmitTime(new Date(System.currentTimeMillis()));// 当前时间
	    b.setChargeHouseDetailId(ch.getId());
	    // b.setRecordQuarter(quarterMap.get("");
	    // 在预交处添加到缴费单
	    if (!factMoney[i].equals("0"))
		payRecordManager.savePayRecordMoney(b);
	}
    }

    /**
     * 车位停止租赁
     * 
     * @param carportId
     */
    public void updateCarportLease(int carportId) {
		CarportEO c = carportManager.get(carportId);
		if("出租".equals(c.getState())){
			c.setState("空置");
		}
		c.setCarportLeaseId(0);
	// carportLeaseManager.createQuery("update CarportLeaseEO set isNow='历史',outDate=getDate() where carport.id="+carportId+" and isNow='当前'").executeUpdate();
	//更新车位租赁表
	carportDao.updateCarportLease(carportId);
    }

    /**
     * 取租赁的车位 用来交费
     * 
     * @param string
     * @param parseInt
     * @param string2
     * @param carCode
     * @param houseId
     * @return
     */
    public PaginatorTag getCarPortLease(int no, int i, String state, int areaId, String bigType, String carCode,
	    String houseId, String local) {
	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
	String where = "";
	if (areaId > 0)
	    where += " and areaId=" + areaId;
	if (!state.equals(""))
	    where += " and state='" + state + "'";
	if (!bigType.equals(""))
	    where += " and bigType='" + bigType + "'";
	if (!carCode.equals(""))
	    where += " and carCode='" + carCode + "'";
	if (!houseId.equals(""))
	    where += " and houseId='" + houseId + "'";
	if (!"".equals(local)) {
	    where += " and local = '" + local + " ' ";
	}

	return carportDao.getCarportLeaseListByWhere(no, i, areaIds, where);
	// return
	// carportLeaseManager.findPage(page,"from CarportLeaseEO where isNow='当前' "+where+" and areaId in("+areaIds+")");
    }

    /**
     * 根据车位id取车位租赁记录
     * 
     * @param id
     * @return
     */
    public CarportLeaseEO getCarportLease(int id) {
	List list = carportDao.getCarportLease(id);
	if (!list.isEmpty())
	    return (CarportLeaseEO) list.get(0);
	else
	    return null;
    }

    /**
     * 保存地下车位 管理费 租赁费
     * 
     * @param carportLease
     * @param oughtMoney1
     * @param factMoney1
     * @param oughtMoney2
     * @param factMoney2
     * @param inDate
     * @param outDate
     * @throws Exception
     */
    public void savePayMoney1(CarportLeaseEO carportLease, String oughtMoney1, String factMoney1, String oughtMoney2,
	    String factMoney2, String inDate, String outDate) throws Exception {

	Session session = carportManager.getSession();

	carportLease = getCarportLease(carportLease.getId());
	carportLease.setInDate(Utils.strToDate(inDate));
	carportLease.setOutDate(Utils.strToDate(outDate));

	String submitName = (String) Struts2Utils.getSession().getAttribute("userName");
	if (null == submitName)
	    throw new RuntimeException("取操作员信息失败，请重新登录");
	// OwnerEO o = (OwnerEO)
	// carportLeaseManager.findUnique("from OwnerEO where house.id='"+carportLease.getHouseId()+"'");
	HouseEO h = houseDao.getHouse(carportLease.getHouseId());

	if (null == h)// 没有取到业主名称
	    throw new RuntimeException("取业主名称错误，请确认该房间有业主");

	// 地下车位管理费 车位租赁费
	String[] chIds = { carportLease.getAreaId() + "110202", carportLease.getAreaId() + "310201" };
	String[] factMoney = { factMoney1, factMoney2 };
	String[] oughtMoney = { oughtMoney1, oughtMoney2 };
	for (int j = 0; j < chIds.length; j++) {

	    // 保存到月详情表
	    int zongyue = 0;
	    zongyue = 12 * (carportLease.getOutDate().getYear() - carportLease.getInDate().getYear())
		    + carportLease.getOutDate().getMonth() - carportLease.getInDate().getMonth();
	    Calendar ca1 = Calendar.getInstance();
	    ca1.setTime(carportLease.getInDate());
	    ca1.add(ca1.MONTH, 1);

	    // ChargeBasedataEO ch = (ChargeBasedataEO)
	    // carportManager.findUnique("from ChargeBasedataEO where id ="+chIds[j]);
	    ChargeBasedataEO ch = chargeBasedataDao.getChargeBasedataByChargeId(Integer.parseInt(chIds[j]));

	    // 保存租赁费 循环次数为 租赁总月数 - 优惠月数
	    for (int i = 0; i < zongyue; i++) {
		// 取开始月份的下一个月
		ca1.add(ca1.MONTH, 1);
		String recordMonth = ca1.get(Calendar.YEAR) + "-" + ca1.get(Calendar.MONTH) + "-01";

		// 保存到月交费详情
		ChargeHouseDetailEO chargeHouseDetail = fillInto(recordMonth, ch, h, ch.getPriceValue());
		session.save(chargeHouseDetail);// 保存每月费用表

		// 保存到租赁详细记录
		CarportLeaseDetailsEO chd = fillInto2(carportLease, ch, chargeHouseDetail);
		session.save(chd);
	    }
	    // 保存到缴费单表 收款
	    ChargeUserPayRecordEO cup = fillInto3(carportLease, ch, submitName, carportLease.getInDate(),
		    Integer.parseInt(chIds[j]), Float.valueOf(factMoney[j]), "收款");
	    cup.setBh(payRecordManager.getNewBh("C"));
	    session.save(cup);

	    // 保存到缴费单 优惠
	    if (Float.valueOf(oughtMoney[j]) > Float.valueOf(factMoney[j]))// 应收大于实收
									   // 有优惠
	    {
		ChargeUserPayRecordEO _cup = fillInto3(carportLease, ch, submitName, carportLease.getInDate(),
			Integer.parseInt(chIds[j]), Float.valueOf(oughtMoney[j]) - Float.valueOf(factMoney[j]), "优惠");
		_cup.setBh(payRecordManager.getNewBh("C"));
		session.save(_cup);
	    }
	}
    }

    private ChargeHouseDetailEO fillInto(String recordMonth, ChargeBasedataEO ch, HouseEO h, float oughtMoney)
	    throws Exception {
	ChargeHouseDetailEO chargeHouseDetail = new ChargeHouseDetailEO();

	chargeHouseDetail.setRecordMonth(new java.sql.Date(Utils.strToDate(recordMonth).getTime()));
	chargeHouseDetail.setAreaId(ch.getAreaId());
	chargeHouseDetail.setHouseType(h.getHouseType());
	chargeHouseDetail.setHouse(h);
	chargeHouseDetail.setOwnerName(h.getOwnerName());
	chargeHouseDetail.setLastRecord(0);
	chargeHouseDetail.setNowRecord(0);
	chargeHouseDetail.setUserNum(0);
	chargeHouseDetail.setChargeUnit("元/月");
	chargeHouseDetail.setChargeType("固定类");
	chargeHouseDetail.setChargeName(ch.getChargeName());
	chargeHouseDetail.setChargePrice(ch.getPriceValue());
	chargeHouseDetail.setOughtMoney(oughtMoney);
	chargeHouseDetail.setArrearageMoney(0);
	chargeHouseDetail.setFactMoney(oughtMoney);
	chargeHouseDetail.setChargeId(ch.getId());
	chargeHouseDetail.setGatheringDate(new java.sql.Date(System.currentTimeMillis()));

	return chargeHouseDetail;
    }

    private CarportLeaseDetailsEO fillInto2(CarportLeaseEO carportLease, ChargeBasedataEO ch,
	    ChargeHouseDetailEO chargeHouseDetail) {
	CarportLeaseDetailsEO cd = new CarportLeaseDetailsEO();
	cd.setAreaId(carportLease.getAreaId());
	cd.setBigType(carportLease.getBigType());
	cd.setCarportId(carportLease.getCarport().getId());
	cd.setCarportLeaseId(carportLease.getId());
	cd.setChargeId(chargeHouseDetail.getChargeId());
	cd.setChargeName(ch.getChargeName());
	cd.setHouseId(carportLease.getHouseId());
	cd.setLocal(carportLease.getLocal());
	cd.setMianji(carportLease.getMianji());
	cd.setOwnerName(carportLease.getOwnerName());
	cd.setRecordMonth(chargeHouseDetail.getRecordMonth());
	cd.setState(carportLease.getState());
	cd.setType(carportLease.getType());
	cd.setOughtMoney(chargeHouseDetail.getOughtMoney());
	cd.setFactMoney(chargeHouseDetail.getOughtMoney());
	cd.setPayMoney(chargeHouseDetail.getOughtMoney());
	cd.setArrearageMoney(0);
	cd.setChargeHouseDetailId(chargeHouseDetail.getId());
	cd.setCarCode(carportLease.getCarCode());
	return cd;
    }

    private ChargeUserPayRecordEO fillInto3(CarportLeaseEO carportLease, ChargeBasedataEO ch, String submitName,
	    Date date, int chargeId, float factMoney, String type) {
	ChargeUserPayRecordEO b = new ChargeUserPayRecordEO();
	b.setBh("");
	b.setUserName(submitName);// 录入人
	b.setRecordMonth(date);// 收费季度
	b.setChargeName(ch.getChargeName());// 缴费项目名称
	b.setChargeId(chargeId);
	b.setPayType(type);// 支付类型 收款、减免、预交、预扣,滞纳金
	b.setFactMoney(factMoney);// 实际缴费金额
	b.setHouse(new HouseEO(carportLease.getHouseId()));// 关联房间
	b.setAreaId(ch.getAreaId());
	b.setPayName(carportLease.getOwnerName());// 保存交款人
	b.setSubmitTime(new Date(System.currentTimeMillis()));// 当前时间
	b.setChargeHouseDetailId(ch.getId());

	return b;
    }

    /**
     * 保存地下车位----只收取管理费
     * 
     * @param carportLease
     * @param oughtMoney1
     * @param factMoney1
     * @param inDate
     * @param outDate
     * @throws Exception
     * @throws NumberFormatException
     */
    public void savePayMoney3(CarportLeaseEO carportLease, String oughtMoney1, String factMoney1, String inDate,
	    String outDate) throws NumberFormatException, Exception {

	Session session = carportManager.getSession();

	carportLease = getCarportLease(carportLease.getId());
	carportLease.setInDate(Utils.strToDate(inDate));
	carportLease.setOutDate(Utils.strToDate(outDate));

	String submitName = (String) Struts2Utils.getSession().getAttribute("userName");
	if (null == submitName)
	    throw new RuntimeException("取操作员信息失败，请重新登录");
	// OwnerEO o = (OwnerEO)
	// carportLeaseManager.findUnique("from OwnerEO where house.id='"+carportLease.getHouseId()+"'");
	HouseEO h = houseDao.getHouse(carportLease.getHouseId());
	if (null == h)// 没有取到业主名称
	    throw new RuntimeException("取业主名称错误，请确认该房间有业主");

	// 保存到月详情表
	int zongyue = 0;
	zongyue = 12 * (carportLease.getOutDate().getYear() - carportLease.getInDate().getYear())
		+ carportLease.getOutDate().getMonth() - carportLease.getInDate().getMonth();
	Calendar ca1 = Calendar.getInstance();
	ca1.setTime(carportLease.getInDate());
	ca1.add(ca1.MONTH, 1);

	String chId = carportLease.getAreaId() + "110202";

	// ChargeBasedataEO ch = (ChargeBasedataEO)
	// carportManager.findUnique("from ChargeBasedataEO where id ="+chId);
	ChargeBasedataEO ch = chargeBasedataDao.getChargeBasedataByChargeId(Integer.parseInt(chId));
	// 保存租赁费 循环次数为 租赁总月数 - 优惠月数
	for (int i = 0; i < zongyue; i++) {
	    // 取开始月份的下一个月
	    ca1.add(ca1.MONTH, 1);
	    String recordMonth = ca1.get(Calendar.YEAR) + "-" + ca1.get(Calendar.MONTH) + "-01";

	    // 保存到月交费详情
	    ChargeHouseDetailEO chargeHouseDetail = fillInto(recordMonth, ch, h, ch.getPriceValue());
	    session.save(chargeHouseDetail);// 保存每月费用表

	    // 保存到租赁详细记录
	    CarportLeaseDetailsEO chd = fillInto2(carportLease, ch, chargeHouseDetail);
	    session.save(chd);
	}
	// 保存到缴费单表 收款
	ChargeUserPayRecordEO cup = fillInto3(carportLease, ch, submitName,
		Utils.strToDate(ca1.get(Calendar.YEAR) + "-" + ca1.get(Calendar.MONTH) + "-01"),
		Integer.parseInt(chId), Float.valueOf(factMoney1), "现金");
	session.save(cup);

	// 保存到缴费单 优惠
	if (Float.valueOf(oughtMoney1) > Float.valueOf(factMoney1))// 应收大于实收 有优惠
	{
	    ChargeUserPayRecordEO _cup = fillInto3(carportLease, ch, submitName,
		    Utils.strToDate(ca1.get(Calendar.YEAR) + "-" + ca1.get(Calendar.MONTH) + "-01"),
		    Integer.parseInt(chId), Float.valueOf(oughtMoney1) - Float.valueOf(factMoney1), "优惠");
	    session.save(_cup);
	}

    }

    /**
     * 保存地面车位管理费
     * 
     * @param carportLease
     * @param oughtMoney1
     * @param factMoney1
     * @param inDate
     * @param outDate
     * @throws Exception
     * @throws NumberFormatException
     */
    public void savePayMoney2(CarportLeaseEO carportLease, String oughtMoney1, String factMoney1, String inDate,
	    String outDate) throws NumberFormatException, Exception {

	Session session = carportManager.getSession();

	carportLease = getCarportLease(carportLease.getId());
	carportLease.setInDate(Utils.strToDate(inDate));
	carportLease.setOutDate(Utils.strToDate(outDate));

	String submitName = (String) Struts2Utils.getSession().getAttribute("userName");
	if (null == submitName)
	    throw new RuntimeException("取操作员信息失败，请重新登录");
	// OwnerEO o = (OwnerEO)
	// carportLeaseManager.findUnique("from OwnerEO where house.id='"+carportLease.getHouseId()+"'");
	HouseEO h = houseDao.getHouse(carportLease.getHouseId());
	if (null == h)// 没有取到业主名称
	    throw new RuntimeException("取业主名称错误，请确认该房间有业主");

	// 保存到月详情表
	int zongyue = 0;
	zongyue = 12 * (carportLease.getOutDate().getYear() - carportLease.getInDate().getYear())
		+ carportLease.getOutDate().getMonth() - carportLease.getInDate().getMonth();
	Calendar ca1 = Calendar.getInstance();
	ca1.setTime(carportLease.getInDate());
	ca1.add(ca1.MONTH, 1);

	String chId = carportLease.getAreaId() + "110203";

	// ChargeBasedataEO ch = (ChargeBasedataEO)
	// carportManager.findUnique("from ChargeBasedataEO where id ="+chId);
	ChargeBasedataEO ch = chargeBasedataDao.getChargeBasedataByChargeId(Integer.parseInt(chId));
	// 保存租赁费 循环次数为 租赁总月数 - 优惠月数
	for (int i = 0; i < zongyue; i++) {
	    // 取开始月份的下一个月
	    ca1.add(ca1.MONTH, 1);
	    String recordMonth = ca1.get(Calendar.YEAR) + "-" + ca1.get(Calendar.MONTH) + "-01";

	    // 保存到月交费详情
	    ChargeHouseDetailEO chargeHouseDetail = fillInto(recordMonth, ch, h, ch.getPriceValue());
	    session.save(chargeHouseDetail);// 保存每月费用表

	    // 保存到租赁详细记录
	    CarportLeaseDetailsEO chd = fillInto2(carportLease, ch, chargeHouseDetail);
	    session.save(chd);
	}
	// 保存到缴费单表 收款
	ChargeUserPayRecordEO cup = fillInto3(carportLease, ch, submitName,
		Utils.strToDate(ca1.get(Calendar.YEAR) + "-" + ca1.get(Calendar.MONTH) + "-01"),
		Integer.parseInt(chId), Float.valueOf(factMoney1), "收款");
	session.save(cup);

	// 保存到缴费单 优惠
	if (Float.valueOf(oughtMoney1) > Float.valueOf(factMoney1))// 应收大于实收 有优惠
	{
	    ChargeUserPayRecordEO _cup = fillInto3(carportLease, ch, submitName,
		    Utils.strToDate(ca1.get(Calendar.YEAR) + "-" + ca1.get(Calendar.MONTH) + "-01"),
		    Integer.parseInt(chId), Float.valueOf(oughtMoney1) - Float.valueOf(factMoney1), "优惠");
	    session.save(_cup);
	}
    }

    /**
     * 取车位历史缴费记录
     * 
     * @param page
     * @param parseInt
     * @param houseId
     * @return
     */
    public PaginatorTag getHistoryCharge(int no, int i, int areaId, String houseId) {
	String areaIds = Struts2Utils.getSession().getAttribute("areaIds").toString();
	String where = "";
	if (areaId > 0)
	    where += " and area_id=" + areaId;
	if (houseId != null && !houseId.equals(""))
	    where += " and house_id ='" + houseId + "'";

	// return
	// carportManager.findPage(page," from CarportLeaseDetailsEO where areaId in("+Struts2Utils.getSession().getAttribute("areaIds")+")"+
	// where);
	PaginatorTag list = carportDao.getCount(no, i, areaIds, where);

	// List listview =new ArrayList();
	//
	// for(int i=0;i<list.size(); i++){
	//
	// String[] str = new String[5];
	//
	// str[0] = list.get(i)[0];
	// str[1] = list.get(i)[1];
	// str[2] = list.get(i)[2];
	// str[3] = list.get(i)[3];
	// str[4] = list.get(i)[4];
	//
	// listview.add(str);
	// }
	//
	return list;
    }

    /**
     * 取所有小区
     * 
     * @return Map(areaId,AreaEO)
     */
    public Map getAreaName() {
	Map m = new HashMap();
	List<AreaEO> l = carportManager.find("from AreaEO");
	for (AreaEO a : l) {
	    m.put(a.getId(), a);
	}

	return m;
    }

    /**
     * 取所有楼栋
     * 
     * @return Map(edificeId,EdificeEO)
     */
    public Map getEdificeName() {
	Map m = new HashMap();
	List<EdificeEO> l = carportManager.find("from EdificeEO");
	for (EdificeEO e : l) {
	    m.put(e.getId(), e);
	}

	return m;
    }

    /*
     * 得到车位置
     */
    public List getCarState() {
	List list;
	list = carportManager.getSession()
		.createSQLQuery("select distinct local from tb_basedata_carport where is_del=1").list();
	return list;
    }

    /**
     * 车位统计报表
     */
    public List reportCar(Integer areaId, String local) {
	String where = "";
	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
	where += " and area.id in (" + areaIds + ") ";
	if (areaId > 0)
	    where += " and area.id=" + areaId;
	if (!local.equals(""))
	    where += " and local='" + local + "' ";
	List retList = new ArrayList();

	List l = carportManager.find("select area.id,local,count(id),sum(mianji) from CarportEO  where isDel=true "
		+ where + " group by area.id,local");
	// 出租数量
	List l2 = carportManager
		.find("select area.id,local,count(state) from CarportEO where isDel=true and state='出租' " + where
			+ " group by area.id,local");
	// 出售数量
	List l3 = carportManager
		.find("select area.id,local,count(state) from CarportEO where isDel=true and state='出售' " + where
			+ " group by area.id,local");
	// 空置数量
	List l4 = carportManager
		.find("select area.id,local,count(state) from CarportEO where isDel=true and state='空置' " + where
			+ " group by area.id,local");

	Map m = getAreaName();

	for (int i = 0; i < l.size(); i++) {
	    String[] ret = new String[8];
	    String aId = ((Object[]) l.get(i))[0].toString();
	    String loc = ((Object[]) l.get(i))[1].toString();
	    AreaEO a = (AreaEO) m.get(Integer.parseInt(aId));
	    ret[0] = a.getAreaName();
	    ret[1] = loc;
	    ret[2] = ((Object[]) l.get(i))[2].toString();
	    ret[3] = ((Object[]) l.get(i))[3].toString();
	    ret[7] = "";
	    // 出租
	    for (int j = 0; j < l2.size(); j++) {
		String aId_1 = ((Object[]) l2.get(j))[0].toString();
		String loc_1 = ((Object[]) l2.get(j))[1].toString();
		if (aId.equals(aId_1) && loc.equals(loc_1)) {
		    ret[5] = ((Object[]) l2.get(j))[2].toString();
		    break;
		}
	    }
	    // 出售
	    for (int j = 0; j < l3.size(); j++) {
		String aId_1 = ((Object[]) l3.get(j))[0].toString();
		String loc_1 = ((Object[]) l3.get(j))[1].toString();
		if (aId.equals(aId_1) && loc.equals(loc_1)) {
		    ret[4] = ((Object[]) l3.get(j))[2].toString();
		    break;
		}
	    }
	    // 空置
	    for (int j = 0; j < l4.size(); j++) {
		String aId_1 = ((Object[]) l4.get(j))[0].toString();
		String loc_1 = ((Object[]) l4.get(j))[1].toString();
		if (aId.equals(aId_1) && loc.equals(loc_1)) {
		    ret[6] = ((Object[]) l4.get(j))[2].toString();
		    break;
		}
	    }

	    retList.add(ret);
	}
	return retList;
    }

    /**
     * 制作excel文件
     * 
     * @param areaId
     */
    public void writeCaredExc(String areaId) {
	List<String[]> retList = new ArrayList<String[]>();
	String[] temp = { "小区编号", "车位编号", "房间编号", "车主姓名", "联系方式", "车牌号码", "卡号", "车名/颜色", "车位属性(出租、出售)", "收费项目编号",
		"开始日期", "截止日期" };
	retList.add(temp);
	ExcelInOut eIO = new ExcelInOut();
	Boolean ret = eIO.writeExc(retList);
	if (!ret)
	    throw new RuntimeException("下载失败");
    }

    /**
     * 上传的文件保存
     * 
     * @param theFile
     */
    public void saveCaredForExc(File theFile) {
	ExcelInOut eIO = new ExcelInOut();
	List<String[]> list = eIO.readExc(theFile);
	// String[]
	// temp={"小区编号","车位编号","房间编号","车主姓名","联系方式","车牌号码","卡号","车型(小型车)","车名/颜色","车位属性(出租、出售)","收费项目编号","开始日期","截止日期"};
	for (int i = 0; i < list.size(); i++) {

	    CarportLeaseEO entity = new CarportLeaseEO();

	    CarportEO carport = null;
	    // 车位编号
	    if (null != list.get(i)[1] && !"".equals(list.get(i)[1])) {
		List<CarportEO> clist = carportManager.find("from CarportEO where carCode='"
			+ list.get(i)[1].toString() + "' ");
		if (!clist.isEmpty())
		    carport = (CarportEO) clist.get(0);
		else
		    throw new RuntimeException("车位基础资料中车位编号" + list.get(i)[1].toString() + "不存在。");
		
		
		entity.setCarport(carport);
		entity.setBigType(carport.getType());
		entity.setLocal(carport.getLocal());
		entity.setMianji(carport.getMianji());
		entity.setCarCode(list.get(i)[1].toString());
		entity.setFactMoney(0f);
		entity.setIsNow("当前");
		entity.setElectric("否");
	    } else {
		throw new RuntimeException("第" + (i + 1) + "行记录的车位编号为空。");
	    }

	    if (null != list.get(i)[0] && !"".equals(list.get(i)[0]))
		entity.setAreaId(Integer.parseInt(list.get(i)[0]));
	    else
		throw new RuntimeException("第" + (i + 1) + "行记录的小区编号为空。");
	    if (null != list.get(i)[2] && !"".equals(list.get(i)[2]))
		entity.setHouseId(list.get(i)[2].toString());
	    else
		throw new RuntimeException("第" + (i + 1) + "行记录的房间编号为空。");

	    if (null != list.get(i)[3] && !"".equals(list.get(i)[3]))
		entity.setOwnerName(list.get(i)[3].toString());
	    else
		throw new RuntimeException("第" + (i + 1) + "行记录的车主姓名为空。");

	    if (null != list.get(i)[4] && !"".equals(list.get(i)[4]))
		entity.setPhone(list.get(i)[4].toString());
	    if (null != list.get(i)[5] && !"".equals(list.get(i)[5]))
		entity.setCarNums(list.get(i)[5].toString());
	    if (null != list.get(i)[6] && !"".equals(list.get(i)[6]))
		entity.setCardNum(list.get(i)[6].toString());
	    entity.setType("小型车");
	    if (null != list.get(i)[7] && !"".equals(list.get(i)[7]))
		entity.setCarColor(list.get(i)[7].toString());
	    
	    
	    if (null != list.get(i)[8] && !"".equals(list.get(i)[8])
	    	&&(list.get(i)[8].equals("出租")||list.get(i)[8].equals("出售"))
	    	)
	    	entity.setState(list.get(i)[8].toString());
	    else
	    	throw new RuntimeException("第" + (i + 1) + "行记录的车位属性不正确。必须是出租或出售状态");
	    
	    
	    if (null != list.get(i)[9] && !"".equals(list.get(i)[9]))
	    	entity.setChargeId(Integer.parseInt(list.get(i)[9].toString()));
	    else
	    	throw new RuntimeException("第" + (i + 1) + "行记录的收费编号不正确。");
	    
	    
	    
	    // if (null != list.get(i)[10] && !"".equals(list.get(i)[10])){
	    // entity.setInDate(new java.util.Date(list.get(i)[10].toString()));
	    // entity.setBeginDate(entity.getInDate());
	    // }else
	    // throw new RuntimeException("第" + (i + 1) + "行记录的车位属性不正确。");
	    // if (null != list.get(i)[11] && !"".equals(list.get(i)[11])){
	    // entity.setOutDate(new
	    // java.util.Date(list.get(i)[11].toString()));
	    // entity.setEndDate(entity.getOutDate());
	    // }else
	    // throw new RuntimeException("第" + (i + 1) + "行记录的车位属性不正确。");
	    
	  //同步车位基础资料的租售状态
	  	carport.setState(entity.getState());
	  	carportDao.save(carport);
	    
	    carportLeaseManager.save(entity);

	}

    }

    /**
     * 制作excel文件 小区编号 车位号 房间编号 车主姓名 联系电话 车牌号 卡号 车名 / 颜色 租/售 开始日期 截止日期 备注
     * 
     * @param areaId
     */
    public void writeCarportExc() {
	List<String[]> retList = new ArrayList<String[]>();
	String[] temp = { "小区编号", "车位编号", "房间编号", "车主姓名", "联系电话", "车牌号", "卡号", "车名/颜色", "出租/出售", "开始日期", "截止日期",
		"收费编号", "备注" };
	retList.add(temp);
	ExcelInOut eIO = new ExcelInOut();
	Boolean ret = eIO.writeExc(retList);
	if (!ret)
	    throw new RuntimeException("下载失败");
    }

    public CarportEO getCarport(String carCode) {
    	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
		List<CarportEO> list = carportManager.find("from CarportEO where isDel=true and carCode='" + carCode + "' and area.id in ("+areaIds+") ");
		if (!list.isEmpty())
		    return list.get(0);
		return null;
		
    }

    /**
     * 上传的文件保存
     * 
     * @param theFile
     */
    public void saveCarportExc(File theFile) {
	ExcelInOut eIO = new ExcelInOut();
	List<String[]> list = eIO.readExc(theFile);
	// String[]
	// temp={"小区编号","车位编号","房间编号","车主姓名","联系电话","车牌号","卡号","车名/颜色","出租/出售","开始日期","截止日期","备注"};

	for (int i = 0; i < list.size(); i++) {
	    logger.debug(i + "------");
	    CarportLeaseEO c = new CarportLeaseEO();
	    CarportEO carport = new CarportEO();
	    if (null != list.get(i)[0] && !"".equals(list.get(i)[0])) {
		AreaEO area = areaManager.getArea(Integer.parseInt(list.get(i)[0]));
		if (area == null)
		    throw new RuntimeException(list.get(i)[0] + "此小区编号不存在");
		c.setAreaId(Integer.parseInt(list.get(i)[0]));
	    } else
		throw new RuntimeException("第" + (i + 1) + "行记录的小区编号为空。");

	    // 设置车位编号
	    if (null != list.get(i)[1] && !"".equals(list.get(i)[1])) {
		carport = getCarport(list.get(i)[1]);
		if (carport == null)
		    throw new RuntimeException(list.get(i)[1] + "此车位编号不存在");
		c.setCarCode(list.get(i)[1]);
		c.setCarport(carport);
		if (carport.getType().equals("1"))
		    c.setBigType("机动车");
		else
		    c.setBigType("非机动车");
		c.setIsNow("当前");
		c.setLocal(carport.getLocal());
		c.setMianji(carport.getMianji());
		if ("机动车".equals(carport.getType()))
		    c.setType("小型车");
		else
		    c.setType("电动车");
	    } else
		throw new RuntimeException("第" + (i + 1) + "行记录的车位编号为空。");

	    // 房间编号
	    if (null != list.get(i)[2] && !"".equals(list.get(i)[2])) {
		HouseEO house = cellManager.getHouse(list.get(i)[2]);
		if (null == house)
		    throw new RuntimeException(list.get(i)[2] + "此房间编号不存在");
		c.setHouseId(list.get(i)[2]);
	    } else
		throw new RuntimeException("第" + (i + 1) + "行记录的房间编号为空。");

	    // 车主姓名
	    if (null != list.get(i)[3] && !"".equals(list.get(i)[3]))
		c.setOwnerName(list.get(i)[3]);
	    else {
		c.setOwnerName("");
		// throw new RuntimeException("第"+(i+1)+"行记录的车主姓名为空。");
	    }
	    // 联系电话","车牌号","卡号","车名/颜色
	    if (null != list.get(i)[4] && !"".equals(list.get(i)[4]))
		c.setPhone(list.get(i)[4]);
	    if (null != list.get(i)[5] && !"".equals(list.get(i)[5]))
		c.setCarNums(list.get(i)[5]);
	    if (null != list.get(i)[6] && !"".equals(list.get(i)[6]))
		c.setCardNum(list.get(i)[6]);
	    if (null != list.get(i)[7] && !"".equals(list.get(i)[7]))
		c.setCarColor(list.get(i)[7]);

	    // 状态
	    if (null != list.get(i)[8] && !"".equals(list.get(i)[8]))
		c.setState(list.get(i)[8]);
	    else
		throw new RuntimeException("第" + (i + 1) + "行记录的车位状态为空。");

	    if (null != list.get(i)[9] && !"".equals(list.get(i)[9])) {

		try {
		    String[] strs = list.get(i)[9].split("-");
		    if (strs[0].length() == 2)
			list.get(i)[9] = "20" + list.get(i)[9];

		    Date inDate = sdf.parse(list.get(i)[9]);
		    c.setInDate(inDate);
		    c.setBeginDate(inDate);
		} catch (ParseException e) {
		    e.printStackTrace();
		    throw new RuntimeException("第" + (i + 1) + "行记录的开始日期格式错误，正确格式如2012-9-12");

		}
	    } else
		throw new RuntimeException("第" + (i + 1) + "行记录的开始日期为空。");

	    if (null != list.get(i)[10] && !"".equals(list.get(i)[10])) {
		try {
		    String[] strs = list.get(i)[10].split("-");
		    if (strs[0].length() == 2)
			list.get(i)[10] = "20" + list.get(i)[10];

		    Date outDate = sdf.parse(list.get(i)[10]);
		    c.setOutDate(outDate);
		    c.setEndDate(outDate);
		} catch (ParseException e) {
		    e.printStackTrace();
		    throw new RuntimeException("第" + (i + 1) + "行记录的截止日期格式错误，正确格式如2012-9-12");

		}

	    } else {
		throw new RuntimeException("第" + (i + 1) + "行记录的截止日期为空。");
	    }

	    if (null != list.get(i)[11] && !"".equals(list.get(i)[11]))
		c.setChargeId(Integer.parseInt(list.get(i)[11]));

	    if (null != list.get(i)[12] && !"".equals(list.get(i)[12]))
		c.setRemark(list.get(i)[12]);

	    carportLeaseManager.save(c);
	}
    }

    public void updateCarport() {
		String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
		List<CarportLeaseEO> ls = carportManager.find("from CarportLeaseEO where isNow='当前' and areaId in(" + areaIds
			+ ") ");
		for (CarportLeaseEO c : ls) {
		    carportManager.createSQLQuery(
			    "update tb_basedata_carport set carport_lease_id=" + c.getId() + " where id="
				    + c.getCarport().getId()+" and area in ("+areaIds+") ").executeUpdate();
		}
    }

    public List<CarportLeaseEO> historyList(String areaId,String houseId,String carCode){
    	String param = "";
    	if(!"".equals(areaId))
    		param = " and areaId="+areaId;
    	if(!"".equals(houseId))
    		param += " and houseId='"+houseId+"' ";
    	if(!"".equals(carCode))
    		param += " and carCode='"+carCode+"' ";
    	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
    	List<CarportLeaseEO> ls = carportManager.find("from CarportLeaseEO where isNow='历史' and areaId in(" + areaIds+ ") "+param);
    	return ls;
    }
    
    public void delHistoryCarport(int id){
    	carportDao.delHistoryCarprot(id);
    }
    
    public static void main(String[] args) throws ParseException {
		String rq = "2010-9-8";
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
		Date a = sdf.parse(rq);
		System.out.println(sdf.format(a));

    }
}

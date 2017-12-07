package com.acec.wgt.service.chargemanger.generate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.utils.Utils;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.chargemanager.AllMeterRecordDAO;
import com.acec.wgt.models.chargemanager.AllMeterRecordEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chargemanager.ChargePreviewDAO;
import com.acec.wgt.models.chargemanager.HousechargeDAO;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;
import com.acec.wgt.service.basedata.CellManager;
//Spring Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class GeneratePUBChargeManager extends GenerateCommManager {

    @Autowired
    HousechargeDAO housechargeDao;// 操作房间收费项目的dao
    @Resource
    ChargePreviewDAO chargePreviewDAO;
    @Autowired
    CellManager cellManager;

    @Autowired
    private AllMeterRecordDAO allMeterRecordDao;

    /**
	 * 
	 */
    @SuppressWarnings("unchecked")
    public void result1(int areaId, String houseId, String beginRecordMonth, String endRecordMonth,
	    List<ChargeBasedataEO> _listBase, String userName) throws Exception {
	// 删除预览表信息
	chargePreviewDAO.del(userName);

	// 缓存把所有房间id 业主id放到map中临时存放 为了下面关联owner

	Map m = cellManager.getAllOwnerName(areaId, "", "", "");

	// 取当前小区的收费项目
	if (houseId == null || houseId.equals(""))
	    houseId = String.valueOf(areaId);

	Session session = housechargeDao.getSession();
	// Transaction tx = session.beginTransaction();
	session.setCacheMode(CacheMode.IGNORE);

	for (ChargeBasedataEO chb : _listBase)// 循环收费项目
	{

	    Map<String, Object[]> houseMap = new HashMap<String, Object[]>();
	    // 编号为chargebaseId收费项目的分摊表的集合
	    List<AllMeterRecordEO> _allMeter = allMeterRecordDao.findList(chb.getId(), beginRecordMonth);
	    for (AllMeterRecordEO meterRecord : _allMeter)// 循环公摊表
	    {
		// 费用
		float usenums = ((meterRecord.getNowRecord() - meterRecord.getLastRecord()) * meterRecord.getTimes())
			+ meterRecord.getChangeNums();
		float money = usenums * meterRecord.getPriceValue();
		if (money < 0.1) {
		    continue;
		}
		String assignArea = meterRecord.getAssignArea();
		Map<String, Object[]> map = getHouseList(session, assignArea);
		if ("1".equals(meterRecord.getAssignType())) {// 按面积分摊
		    // 先计算总面积
		    Double totalBuildNum = 0D;
		    Iterator iter = map.keySet().iterator();
		    while (iter.hasNext()) {
			String _houseId = (String) iter.next();
			Object[] _objs = map.get(_houseId);
			Float _buildNum = (Float) _objs[1];
			totalBuildNum += _buildNum;
		    }
		    if (totalBuildNum < 1)
			// new
			// RuntimeException("分摊"+meterRecord.getMeterCode()+meterRecord.getAreaName()+"时，分摊面积为零");
			continue;

		    // 每户分摊金额
		    iter = map.keySet().iterator();
		    while (iter.hasNext()) {
			String _houseId = (String) iter.next();
			Object[] _objs = map.get(_houseId);
			Float _buildNum = (Float) _objs[1];
			Double _houseMoney = (_buildNum / totalBuildNum) * money;
			Object[] _objs1 = houseMap.get(_houseId);
			if (_objs1 != null) {
			    Double _h = (Double) _objs1[3];
			    _h += _houseMoney;
			} else {
			    _objs[3] = _houseMoney;
			    houseMap.put(_houseId, _objs);
			}
		    }
		} else {// 按户数分摊
		    int totalCount = map.keySet().size();
		    // 每户分摊金额
		    if (totalCount < 1)
			// new
			// RuntimeException("分摊"+meterRecord.getMeterCode()+meterRecord.getAreaName()+"时，分摊户数为零");
			continue;

		    Double _houseMoney = (double) money / totalCount;

		    Iterator iter = map.keySet().iterator();
		    while (iter.hasNext()) {
			String _houseId = (String) iter.next();
			Object[] _objs = map.get(_houseId);
			// Float _buildNum = (Float)_objs[1];

			Object[] _objs1 = houseMap.get(_houseId);
			if (_objs1 != null) {
			    Double _h = (Double) _objs1[3];
			    _h += _houseMoney;
			} else {
			    _objs[3] = _houseMoney;
			    houseMap.put(_houseId, _objs);
			}

		    }
		}

		// 开始保存
		Iterator iter = houseMap.keySet().iterator();
		while (iter.hasNext()) {
		    String _houseId = (String) iter.next();
		    Object[] _objs = houseMap.get(_houseId);
		    Float _buildNum = (Float) _objs[1]; // 建筑面积
		    Double _oughtMoney = (Double) _objs[3]; // 应收款
		    String houseType = (String) _objs[2];// 房间类型 住宅/商铺
		    if (_oughtMoney == null || _oughtMoney < 0.1)
			continue;

		    ChargeHouseDetailEO chargeHouseDetail = new ChargeHouseDetailEO();
		    java.sql.Date _date = new java.sql.Date(Utils.strToDate(beginRecordMonth).getTime());
		    chargeHouseDetail.setRecordMonth(_date);
		    // 设置小区、房间
		    chargeHouseDetail.setAreaId(Integer.parseInt(_houseId.substring(0, 4)));
		    chargeHouseDetail.setHouseType(houseType);
		    HouseEO house = new HouseEO();
		    house.setId(_houseId);
		    chargeHouseDetail.setHouse(house);

		    // 设置业主,物管员
		    // 设置业主,物管员
		    Object[] temp = (Object[]) m.get(_houseId);
		    
		    if (temp != null&&temp[1]!=null) {
		    	chargeHouseDetail.setOwnerName(temp[1].toString());
		    }else {
		    	chargeHouseDetail.setOwnerName("");
		    }
		    

		    chargeHouseDetail.setChargeId(chb.getId());
		    chargeHouseDetail.setChargeUnit(chb.getPriceUnit());

		    chargeHouseDetail.setChargeType(chb.getChargeType());
		    chargeHouseDetail.setChargeName(chb.getChargeName());
		    chargeHouseDetail.setChargePrice(chb.getPriceValue());
		    chargeHouseDetail.setOughtMoney(_oughtMoney.floatValue());
		    chargeHouseDetail.setArrearageMoney(chargeHouseDetail.getOughtMoney());
		    chargeHouseDetail.setFactMoney(0);
		    if (chargeHouseDetail.getOughtMoney() > 0) {
			chargePreviewDAO.save(chargeHouseDetail, userName);
		    }
		}// 循环保存

	    }// 循环公摊表结束

	}// 循环收费项目结束
	 // tx.commit();
    }

    /**
     * 预览收费信息列表页面
     * 
     * @param userName
     * @return
     */
    public List findPreviewList(String userName) {
	return chargePreviewDAO.findPreviewList(userName);
    }

    /**
     * 
     * @param userName
     *            操作人登录名
     * @param chargeIds
     *            保存的收费项目id，（只针对业户表，此处无用）， 如果是的话 更新水电记录表的状态
     */
    public void generateResult(String userName, String chargeIds) {

	// 更新总表指数
	String[] chargeId = chargeIds.substring(1).split(",");
	int areaId = Integer.parseInt(chargeId[0].substring(0, 4));
	allMeterRecordDao.updateCurrentRecordOver(areaId);

	chargePreviewDAO.generateResult(userName);
    }

    /**
     * 
     * @param session
     * @param assignArea
     * @return map[房间编号,收费面积]
     */
    public Map<String, Object[]> getHouseList(Session session, String assignArea) {

	String a = "";// 小区id ,号分隔
	String b = "";// 楼栋id ,号分隔
	String c = "";// 房间id ,号分隔

	// 放入查询后的手机号
	ArrayList<Object[]> sjhList = new ArrayList<Object[]>();
	if (assignArea != null && assignArea.length() > 0) {
	    String aa[] = assignArea.split(",");
	    for (int i = 0; i < aa.length; i++) {
		if (aa[i].contains("a")) {
		    a += aa[i].split("a")[0] + ",";
		}
		if (aa[i].contains("b")) {
		    b += "'" + aa[i].split("b")[0] + "',";
		}
		if (aa[i].contains("c")) {
		    c += "'" + aa[i].split("c")[0] + "',";
		}
	    }
	}
	// 取小区
	if (a.length() > 0) {
	    String _sql = "select id,buildnum,houseType,0.0 from HouseEO where areaId in ("
		    + a.toString().substring(0, a.length() - 1) + ")";
	    List _ls1 = session.createQuery(_sql).list();
	    sjhList.addAll(_ls1);
	}
	if (b.length() > 0) {
	    String _sql = "select id,buildnum,houseType,0.0 from HouseEO where edificeId in ("
		    + b.toString().substring(0, b.length() - 1) + ")";
	    List _ls1 = session.createQuery(_sql).list();
	    sjhList.addAll(_ls1);
	}
	if (c.length() > 0) {
	    String _sql = "select id,buildnum,houseType,0.0 from HouseEO  where id in ("
		    + c.toString().substring(0, c.length() - 1) + ")";
	    List _ls1 = session.createQuery(_sql).list();
	    sjhList.addAll(_ls1);
	}
	Map map = new HashMap();
	for (int i = 0; i < sjhList.size(); i++) {
	    Object[] objs = sjhList.get(i);
	    map.put(objs[0].toString(), objs);
	}
	return map;

    }

}

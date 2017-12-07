package com.acec.wgt.service.chargemanger.generate;

import java.sql.Date;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.CacheMode;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.utils.Utils;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chargemanager.ChargePreviewDAO;
import com.acec.wgt.models.chargemanager.HousechargeDAO;
import com.acec.wgt.models.chargemanager.HousechargeEO;
import com.acec.wgt.models.chargemanager.OwnerMeterRecordDAO;
import com.acec.wgt.models.chg.ChargeHouseDetailEO;
import com.acec.wgt.service.basedata.CellManager;
import com.acec.wgt.service.chargemanger.HouseChargeManager;
import com.acec.wgt.service.chargemanger.generate.formula.ExpressVariable;

//Spring Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class GenerateFixedChargeManager extends GenerateCommManager {

    @Autowired
    HousechargeDAO housechargeDao;// 操作房间收费项目的dao
    @Resource
    ChargePreviewDAO chargePreviewDAO;
    @Autowired
    CellManager cellManager;
    @Autowired
    OwnerMeterRecordDAO ownerMeterRecordDAO;

    // 公式里面的变量值Map
    ExpressVariable expressVariable;

    /**
     * 生成管理类费用（固定类费用）
     * 
     * @param areaId
     *            生成的小区id
     * @param recordMonth
     *            生成月份
     * @param subChargeId
     *            费用编号(4位)1101 如1023+1101+01 小区编号+具体费用(如物管费)+别名（如高层）
     * @throws ParseException
     */
    @SuppressWarnings("unchecked")
    public void result1(int areaId, String houseId, String beginRecordMonth, String endRecordMonth,
	    List<ChargeBasedataEO> _listBase, String userName) throws Exception {
	expressVariable = new ExpressVariable();
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

	    List<HousechargeEO> l1 = housechargeDao.findByChageBasedata(houseId, chb.getId());
	    int i = 0;

	    Calendar begin_cal = Calendar.getInstance();
	    begin_cal.setTime(Date.valueOf(beginRecordMonth));

	    Calendar end_cal = Calendar.getInstance();
	    end_cal.setTime(Date.valueOf(endRecordMonth));

	    for (HousechargeEO ch1 : l1) {
		i++;
		String exp = ch1.getChargeBasedata().getChargeExpression();

		Calendar tmp_cal = Calendar.getInstance();
		tmp_cal.setTime(begin_cal.getTime());

		while (tmp_cal.compareTo(end_cal) <= 0) {
		    ChargeHouseDetailEO chargeHouseDetail = new ChargeHouseDetailEO();
		    Date recordMonth = new Date(tmp_cal.getTimeInMillis());
		    chargeHouseDetail.setRecordMonth(recordMonth);

		    // 设置小区、房间
		    chargeHouseDetail.setAreaId(ch1.getHouse().getAreaId());
		    chargeHouseDetail.setHouseType(ch1.getHouse().getHouseType());
		    HouseEO house = ch1.getHouse();
		    chargeHouseDetail.setHouse(house);

		    // 设置业主
		    Object[] temp = (Object[]) m.get(ch1.getHouse().getId());
		   
		    if (temp != null&&temp[1]!=null) {
		    	chargeHouseDetail.setOwnerName(temp[1].toString());
		    }else {
		    	chargeHouseDetail.setOwnerName("");
		    }
		   

		    chargeHouseDetail.setChargeId(ch1.getChargeBasedata().getId());
		    chargeHouseDetail.setChargeUnit(ch1.getChargeBasedata().getPriceUnit());

		    chargeHouseDetail.setChargeType(ch1.getChargeBasedata().getChargeType());
		    chargeHouseDetail.setChargeName(ch1.getChargeBasedata().getChargeName());
		    chargeHouseDetail.setChargePrice(ch1.getChargeBasedata().getPriceValue());
		    Double oughtMoney = CalCharge(session, ch1, exp, Utils.dateToString(recordMonth), chargeHouseDetail);
		    chargeHouseDetail.setOughtMoney(oughtMoney.floatValue());
		    chargeHouseDetail.setArrearageMoney(chargeHouseDetail.getOughtMoney());
		    chargeHouseDetail.setFactMoney(0);

		    if (chargeHouseDetail.getOughtMoney() > 0) {
		    	chargePreviewDAO.save(chargeHouseDetail, userName);
			// if(i%30==0){
			// session.flush();
			// session.clear();
			// }
		    }
		    tmp_cal.add(Calendar.MONTH, 1);
		}

	    }
	}// 循环收费项目结束
	 // tx.commit();
    }

    private Double CalCharge(Session session, HousechargeEO ch1, String exp, String recordMonth,
	    ChargeHouseDetailEO chargeHouseDetail) {
	if (exp == null || exp.equals(""))
	    throw new RuntimeException("收费项目表达式为空，检查后请重新生成");

	float retvalue = 0;// 把表达式换成值
	while (true) {
	    int left = exp.indexOf("#");
	    int right = exp.indexOf("#", left + 1);
	    if (left >= 0 && right > 0) {
		String var = exp.substring(left + 1, right);

		retvalue = expressVariable.findVariable(var, ch1.getChargeBasedata(), ch1.getHouse(), session,
			recordMonth, chargeHouseDetail);
		exp = exp.replaceAll("#" + var + "#", String.valueOf(retvalue));

	    } else {
		break;
	    }
	}
	Double oughtMoney = 0d;// 保存表达式的值
	try {
	    oughtMoney = Calculator.jisuan(exp);
	} catch (Exception ex) {
	    new RuntimeException("计算表达式有非法字符，无法计算");
	}
	return oughtMoney;
    }

    public List findPreviewList(String userName) {
	return chargePreviewDAO.findPreviewList(userName);
    }

    /**
     * 
     * @param userName
     *            操作人登录名
     * @param chargeIds
     *            这次保存的收费项目id，主要用来判断是否是水/电费， 如果是的话 更新水电记录表的状态
     */
    public void generateResult(String userName, String chargeIds) {
	// 看看是否是走表类的
	String[] chargeId = chargeIds.substring(1).split(",");
	for (String v_chargeId : chargeId) {
	    int areaId = Integer.parseInt(v_chargeId.substring(0, 4));
	    String meterType = HouseChargeManager.getMeterTypeBychargeId(v_chargeId);
	    if (!StringUtils.isEmpty(meterType)) {
	    	ownerMeterRecordDAO.updateCurrentRecordOver(areaId, meterType);
	    }

	}
	chargePreviewDAO.generateResult(userName);
    }
}

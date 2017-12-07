package com.acec.wgt.service.chg;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.Page;

public interface PayMangerI {

    @Autowired
    public abstract void setSessionFactory(SessionFactory sessionFactory);

    /**
     * 账务中心、返回一个按公司、楼栋、房间为条件的所有房间的应收，已收，欠收
     * 
     * @param where
     *            页面组合的一些条件 ，如小区、楼栋、单元等
     * @param beginDate
     *            季度 ，如2009-01 表示2009一季度
     * @param endDate
     *            季度 ，如2009-01 表示2009一季度
     * @param noArrearage
     *            1表示不欠费的，2表示欠费的
     * @return
     */
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public abstract Page getHouseChargeDetailALL(Page page, String areaId, String edificeId, String houseId,
                                                 String beginDate, String endDate, String noArrearage, String wherechargeNames, String managerMen);

    /**
     * 该房间的需要缴费记录（欠费项目记录）
     * 
     * @param houseId
     *            房间id
     * @param chargeId
     * @param endDate
     * @param beginDate
     * @param payType
     */

    @SuppressWarnings("unchecked")
    public abstract List getArreargeHouseByHouseId(String houseId, String beginDate, String endDate, String chargeName,
                                                   String payType);

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
    public abstract void savePay(String houseId, String payType, String[] xh, String[] factMoneys, String[] advances, String[] privileges, String[] privilegeReasons,
                                 String arrearMoney, String advanceSrc, String userName, String reason, String bh, String gatheringDate);

    /**
     * 
     * @param houseId
     *            房间编号
     * @param xh
     *            季度表记录编号
     * @param userName
     *            操作人
     * @return
     */
    public abstract Float calAdvance(String houseId, int xh, String userName, String chargeId);

    /**
     * 得到新的收据单编号 string prefix 编号前缀
     */
    public abstract String getNewBh(String prefix);

}
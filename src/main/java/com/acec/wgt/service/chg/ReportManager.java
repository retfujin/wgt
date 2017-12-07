package com.acec.wgt.service.chg;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.utils.ArithUtils;
import com.acec.core.utils.Utils;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.models.baseData.EdificeEO;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.chargemanager.ChargeBasedataEO;
import com.acec.wgt.models.chargemanager.HousechargeEO;
import com.acec.wgt.models.chg.AuditDAO;
import com.acec.wgt.models.chg.AuditEO;
import com.acec.wgt.models.chg.ManagerDAO;
import com.acec.wgt.models.sys.SysUserEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.CellManager;
import com.acec.wgt.service.basedata.EdificeManager;

@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class ReportManager {

	private final Logger logger = LoggerFactory.getLogger(ReportManager.class);

	@Autowired
	private AreaManager areaManager;
	@Autowired
	private CellManager cellManager;
	@Autowired
	private EdificeManager edificeManager;
	@Autowired
	private ManagerDAO managerDao;
	@Autowired
	private AuditDAO auditDao;

	private HibernateDao<AuditEO, Integer> auditManager;
	private String _subChargeId=SubCharge.subPropertyChargeId;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		auditManager = new HibernateDao<AuditEO, Integer>(sessionFactory,
				AuditEO.class);
	}

	/**
	 * 物业费年度统计(小区)
	 * 
	 * @param year
	 * @return
	 */
	public List getYearCharge(int year) {
		
		//长江物业被盗只显示3年物业收费
		String areaIds = (String) Struts2Utils.getSession().getAttribute(
				"areaIds");
		List paramList = new ArrayList();
		// 小区名称 应收款 实收，优惠，欠收款
		StringBuffer hql = new StringBuffer();
		hql.append(
				"select a.area_name,sum(d.ought_money) as ought_money,sum(d.fact_money) as fact_money,sum(d.privilege_money) as privilege_money, sum(d.arrearage_money) as arrearage_money ")
				.append(" from tb_chg_house_detail as d inner join tb_basedata_area as a on a.id=d.area_id ")
				.append(" where year(d.record_month)=").append(year)
				.append(" and d.charge_id like '%").append(_subChargeId)
				.append("%' and a.id in (").append(areaIds).append(") ")
				.append("  group by a.area_name order by a.area_name");


		// 小区名称 补交前1
		StringBuffer hql3 = new StringBuffer();
		hql3.append(
				"select a.area_name,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
				.append(" inner join tb_basedata_area as a on a.id=p.area_id where p.pay_type='收款' and year(p.record_month)=").append(year-1)
				.append(" and a.id in (").append(areaIds).append(") ")
				.append(" and year(p.gathering_time)=").append(year)
				.append(" and p.charge_id like '%").append(_subChargeId)
				.append("%' group by a.area_name order by a.area_name");
		
		// 小区名称 补交前2
		StringBuffer hql4 = new StringBuffer();
		hql4.append(
				"select a.area_name,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
				.append(" inner join tb_basedata_area as a on a.id=p.area_id where p.pay_type='收款' and year(p.record_month)=").append(year-2)
				.append(" and a.id in (").append(areaIds).append(") ")
				.append(" and year(p.gathering_time)=").append(year)
				.append(" and p.charge_id like '%").append(_subChargeId)
				.append("%' group by a.area_name order by a.area_name");
		// 小区名称 补交前3
		StringBuffer hql5 = new StringBuffer();
		hql5.append(
				"select a.area_name,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
				.append(" inner join tb_basedata_area as a on a.id=p.area_id where p.pay_type='收款' and year(p.record_month)=").append(year-3)
				.append(" and a.id in (").append(areaIds).append(") ")
				.append(" and year(p.gathering_time)=").append(year)
				.append(" and p.charge_id like '%").append(_subChargeId)
				.append("%' group by a.area_name order by a.area_name");
		
		// 小区名称 补交前4
		StringBuffer hql6 = new StringBuffer();
		hql6.append(
				"select a.area_name,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
				.append(" inner join tb_basedata_area as a on a.id=p.area_id where p.pay_type='收款' and year(p.record_month)<").append(year-3)
				.append(" and a.id in (").append(areaIds).append(") ")
				.append(" and year(p.gathering_time)=").append(year)
				.append(" and p.charge_id like '%").append(_subChargeId)
				.append("%' group by a.area_name order by a.area_name");
	
		List list = managerDao.getYearReportList(hql.toString());
		
		List list3 = managerDao.getYearReportList(hql3.toString());
		List list4 = managerDao.getYearReportList(hql4.toString());
		List list5 = managerDao.getYearReportList(hql5.toString());
		List list6 = managerDao.getYearReportList(hql6.toString());
	
		
		List<AreaEO> _list = areaManager.getAreaALL();
		for (AreaEO area : _list) {
			String[] str = new String[11];
			str[0] = area.getAreaName();// 小区名称
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					if (((Object[]) list.get(i))[0]!=null&&((Object[]) list.get(i))[0].toString().equals(str[0])) {
						str[1] = ((Object[]) list.get(i))[1].toString();// 应收
						str[2] = ((Object[]) list.get(i))[2].toString();// 实收
						str[3] = ((Object[]) list.get(i))[3].toString();// 优惠
						str[4] = ((Object[]) list.get(i))[4].toString();// 欠收
						continue;
					}
				}
			}


			if (!list3.isEmpty()) {
				for (int i = 0; i < list3.size(); i++) {
					if (((Object[]) list3.get(i))[0].toString().equals(str[0])) {
						str[5] = ((Object[]) list3.get(i))[1].toString();// 补交1
						continue;
					}
				}
			}

			if (!list4.isEmpty()) {
				for (int i = 0; i < list4.size(); i++) {
					if (((Object[]) list4.get(i))[0].toString().equals(str[0])) {
						str[6] = ((Object[]) list4.get(i))[1].toString();// 补交2
						continue;
					}
				}
			}
			
			if (!list5.isEmpty()) {
				for (int i = 0; i < list5.size(); i++) {
					if (((Object[]) list5.get(i))[0].toString().equals(str[0])) {
						str[7] = ((Object[]) list5.get(i))[1].toString();// 补交3
						continue;
					}
				}
			}
			
			if (!list6.isEmpty()) {
				for (int i = 0; i < list6.size(); i++) {
					if (((Object[]) list6.get(i))[0].toString().equals(str[0])) {
						str[8] = ((Object[]) list6.get(i))[1].toString();// 补交4
						continue;
					}
				}
			}
		
			
			str[10]=area.getId().toString();
			
			//欠费重新计算下
			if(StringUtils.isEmpty(str[1]))
				str[1]="0";
			BigDecimal b1= new BigDecimal(str[1]);
			
			if(StringUtils.isEmpty(str[2]))
				str[2]="0";
			BigDecimal b2= new BigDecimal(str[2]);
			
			if(StringUtils.isEmpty(str[3]))
				str[3]="0";
			BigDecimal b3= new BigDecimal(str[3]);
			
			
			str[4] = b1.subtract(b2).subtract(b3).toString();
			
			paramList.add(str);
		}
		
		return paramList;
	}

	
	/**
	 * 物业费年度统计(楼栋)
	 * @param areaId
	 * @param year
	 * @return
	 */
	public List getYearEdificeReport(int areaId,int year){
		
		
		// 小区名称 应收款 实收款
		StringBuffer hql = new StringBuffer();
		hql.append(
				"select h.edifice_id,sum(d.ought_money) as ought_money,sum(d.fact_money) as fact_money,sum(d.privilege_money) as privilege_money,sum(d.arrearage_money) as arrearage_money ")
				.append(" from tb_chg_house_detail as d inner join tb_basedata_house as h on d.house=h.id ")
				.append(" where year(d.record_month)=").append(year)
				.append(" and d.charge_id like '%").append(_subChargeId)
				.append("%' and h.area_id='").append(areaId)
				.append("' group by h.edifice_id order by h.edifice_id");
		
		
		// 小区名称 补交前1
		StringBuffer hql3 = new StringBuffer();
		hql3.append(
				"select h.edifice_id,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
				.append(" inner join tb_basedata_house as h on h.id=p.house_id where p.pay_type='收款' and year(p.record_month)=")
				.append(year - 1).append(" and year(p.gathering_time)=")
				.append(year).append(" and p.charge_id like '%")
				.append(_subChargeId).append("%' and h.area_id='").append(areaId)
				.append("' group by h.edifice_id order by h.edifice_id");
		
		// 小区名称 补交前2
		StringBuffer hql4 = new StringBuffer();
		hql4.append(
				"select h.edifice_id,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
				.append(" inner join tb_basedata_house as h on h.id=p.house_id where p.pay_type='收款' and year(p.record_month)=")
				.append(year - 2).append(" and year(p.gathering_time)=")
				.append(year).append(" and p.charge_id like '%")
				.append(_subChargeId).append("%' and h.area_id='").append(areaId)
				.append("' group by h.edifice_id order by h.edifice_id");
		
		// 小区名称 补交前3
		StringBuffer hql5 = new StringBuffer();
		hql5.append(
				"select h.edifice_id,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
				.append(" inner join tb_basedata_house as h on h.id=p.house_id where p.pay_type='收款' and year(p.record_month)=")
				.append(year - 3).append(" and year(p.gathering_time)=")
				.append(year).append(" and p.charge_id like '%")
				.append(_subChargeId).append("%' and h.area_id='")
				.append(areaId)
				.append("' group by h.edifice_id order by h.edifice_id");

		// 小区名称 补交前4
		StringBuffer hql6 = new StringBuffer();
		hql6.append(
				"select h.edifice_id,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
				.append(" inner join tb_basedata_house as h on h.id=p.house_id where p.pay_type='收款' and year(p.record_month)<")
				.append(year - 3).append(" and year(p.gathering_time)=")
				.append(year).append(" and p.charge_id like '%")
				.append(_subChargeId).append("%' and h.area_id='")
				.append(areaId)
				.append("' group by h.edifice_id order by h.edifice_id");
		
		
		List list = managerDao.getYearEdificeReportList(hql.toString());
		List list3 = managerDao.getYearEdificeReportList(hql3.toString());
		List list4 = managerDao.getYearEdificeReportList(hql4.toString());
		List list5 = managerDao.getYearEdificeReportList(hql5.toString());
		List list6 = managerDao.getYearEdificeReportList(hql6.toString());
		
		
		
		List paramList = new ArrayList();		
		List<EdificeEO> _list = edificeManager.getAllEdifice(areaId);
		for (EdificeEO edifice : _list) {
			
			String[] str = new String[11];
			str[0] = edifice.getEdificeName();
			str[10]=edifice.getId();
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					if (((Object[]) list.get(i))[0]!=null&&((Object[]) list.get(i))[0].toString().equals(str[10])) {
						str[1] = ((Object[]) list.get(i))[1].toString();// 应收
						str[2] = ((Object[]) list.get(i))[2].toString();// 实收
						str[3] = ((Object[]) list.get(i))[3].toString();// 优惠
						str[4] = ((Object[]) list.get(i))[4].toString();// 欠收
						continue;
					}
				}
			}

			if (!list3.isEmpty()) {
				for (int i = 0; i < list3.size(); i++) {
					if (((Object[]) list3.get(i))[0].toString().equals(str[10])) {
						str[5] = ((Object[]) list3.get(i))[1].toString();// 补交1
						continue;
					}
				}
			}

			if (!list4.isEmpty()) {
				for (int i = 0; i < list4.size(); i++) {
					if (((Object[]) list4.get(i))[0].toString().equals(str[10])) {
						str[6] = ((Object[]) list4.get(i))[1].toString();// 补交2
						continue;
					}
				}
			}
			
			if (!list5.isEmpty()) {
				for (int i = 0; i < list5.size(); i++) {
					if (((Object[]) list5.get(i))[0].toString().equals(str[10])) {
						str[7] = ((Object[]) list5.get(i))[1].toString();// 补交3
						continue;
					}
				}
			}
			
			if (!list6.isEmpty()) {
				for (int i = 0; i < list6.size(); i++) {
					if (((Object[]) list6.get(i))[0].toString().equals(str[10])) {
						str[8] = ((Object[]) list6.get(i))[1].toString();// 补交4
						continue;
					}
				}
			}
			
			//欠费重新计算下
			if(StringUtils.isEmpty(str[1]))
				str[1]="0";
			BigDecimal b1= new BigDecimal(str[1]);
			if(StringUtils.isEmpty(str[2]))
				str[2]="0";
			BigDecimal b2= new BigDecimal(str[2]);
			
			if(StringUtils.isEmpty(str[3]))
				str[3]="0";
			BigDecimal b3= new BigDecimal(str[3]);
		
			str[4] = b1.subtract(b2).subtract(b3).toString();
			
			paramList.add(str);
		}
		
		return paramList;
	}
	
	public List getYearHouseReport(String edificeId,int year){
		
		// 小区名称 应收款 欠收款
		StringBuffer hql = new StringBuffer();
		hql.append(
				"select d.house,sum(d.ought_money) as ought_money,sum(d.fact_money) as fact_money,sum(d.privilege_money) as privilege_money,sum(d.arrearage_money) as arrearage_money ")
				.append(" from tb_chg_house_detail as d ")
				.append(" where year(d.record_month)=").append(year)
				.append(" and d.house like '").append(edificeId)
				.append("%' and d.charge_id like '%").append(_subChargeId)
				.append("%'  group by d.house");

		List list = managerDao.getYearEdificeReportList(hql.toString());
					
					
		List paramList = new ArrayList();		
		List<HouseEO> _list = cellManager.getHouseByEdificeId(edificeId);
		for (HouseEO house : _list) {
			
			String[] str = new String[11];
			str[0] = house.getId();
			str[5]= house.getEdificeName()+house.getHouseName();
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					
					if (((Object[]) list.get(i))[0]!=null&&((Object[]) list.get(i))[0].toString().equals(str[0])) {
						str[1] = ((Object[]) list.get(i))[1].toString();// 应收
						str[2] = ((Object[]) list.get(i))[2].toString();// 实收
						str[3] = ((Object[]) list.get(i))[3].toString();// 优惠
						str[4] = ((Object[]) list.get(i))[4].toString();// 欠收
						continue;
					}
				}
			}
			
			//欠费重新计算下
			if(StringUtils.isEmpty(str[1]))
				str[1]="0";
			BigDecimal b1= new BigDecimal(str[1]);
			
			if(StringUtils.isEmpty(str[2]))
				str[2]="0";
			BigDecimal b2= new BigDecimal(str[2]);
			
			if(StringUtils.isEmpty(str[3]))
				str[3]="0";
			BigDecimal b3= new BigDecimal(str[3]);
			
			str[4] = b1.subtract(b2).subtract(b3).toString();
			
			paramList.add(str);
		}
		return paramList;
	}
	
	/**
	 * 物业费月度统计(小区)
	 * 
	 * @param recordMonth
	 * @return
	 */
	public List getMonthReport(String beginDate,String endDate) {
		String strparam[] =beginDate.split("-");
		int _year = Integer.parseInt(strparam[0]);
		int year = _year;
		//长江物业被盗只显示3年物业收费
				String areaIds = (String) Struts2Utils.getSession().getAttribute(
						"areaIds");
				List paramList = new ArrayList();
				// 小区名称 应收款 欠收款
				StringBuffer hql = new StringBuffer();
				hql.append(
						"select a.area_name,sum(d.ought_money) as ought_money,sum(d.fact_money) as fact_money,sum(d.privilege_money) as privilege_money,sum(d.arrearage_money) as arrearage_money ")
						.append(" from tb_chg_house_detail as d inner join tb_basedata_area as a on a.id=d.area_id ")
						.append(" where date_format(d.record_month,'%Y-%m')>='").append(beginDate).append("' and date_format(d.record_month,'%Y-%m')<='").append(endDate)
						.append("' and d.charge_id like '%").append(_subChargeId)
						.append("%' and a.id in (").append(areaIds).append(") ")
						.append("  group by a.area_name order by a.area_name");
				
				// 小区名称 补交前1
				StringBuffer hql3 = new StringBuffer();
				hql3.append(
						"select a.area_name,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
						.append(" inner join tb_basedata_area as a on a.id=p.area_id where p.pay_type='收款' and year(p.record_month)=").append(--_year)
						.append(" and a.id in (").append(areaIds).append(") ")
						.append(" and year(p.gathering_time)=").append(year)
						.append(" and p.charge_id like '%").append(_subChargeId)
						.append("%' group by a.area_name order by a.area_name");
				
				// 小区名称 补交前2
				StringBuffer hql4 = new StringBuffer();
				hql4.append(
						"select a.area_name,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
						.append(" inner join tb_basedata_area as a on a.id=p.area_id where p.pay_type='收款' and year(p.record_month)=").append(--_year)
						.append(" and a.id in (").append(areaIds).append(") ")
						.append(" and year(p.gathering_time)=").append(year)
						.append(" and p.charge_id like '%").append(_subChargeId)
						.append("%' group by a.area_name order by a.area_name");
				// 小区名称 补交前3
				StringBuffer hql5 = new StringBuffer();
				hql5.append(
						"select a.area_name,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
						.append(" inner join tb_basedata_area as a on a.id=p.area_id where p.pay_type='收款' and year(p.record_month)=").append(--_year)
						.append(" and a.id in (").append(areaIds).append(") ")
						.append(" and year(p.gathering_time)=").append(year)
						.append(" and p.charge_id like '%").append(_subChargeId)
						.append("%' group by a.area_name order by a.area_name");
				
				// 小区名称 补交前4
				StringBuffer hql6 = new StringBuffer();
				hql6.append(
						"select a.area_name,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
						.append(" inner join tb_basedata_area as a on a.id=p.area_id where p.pay_type='收款' and year(p.record_month)<").append(_year)
						.append(" and a.id in (").append(areaIds).append(") ")
						.append(" and year(p.gathering_time)=").append(year)
						.append(" and p.charge_id like '%").append(_subChargeId)
						.append("%' group by a.area_name order by a.area_name");
		
				
				List list = managerDao.getYearReportList(hql.toString());
			
				List list3 = managerDao.getYearReportList(hql3.toString());
				List list4 = managerDao.getYearReportList(hql4.toString());
				List list5 = managerDao.getYearReportList(hql5.toString());
				List list6 = managerDao.getYearReportList(hql6.toString());

				
				
				List<AreaEO> _list = areaManager.getAreaALL();
				for (AreaEO area : _list) {
					String[] str = new String[11];
					str[0] = area.getAreaName();// 小区名称
					if (!list.isEmpty()) {
						for (int i = 0; i < list.size(); i++) {
							if (((Object[]) list.get(i))[0]!=null&&((Object[]) list.get(i))[0].toString().equals(str[0])) {
								str[1] = ((Object[]) list.get(i))[1].toString();// 应收
								str[2] = ((Object[]) list.get(i))[2].toString();// 实收
								str[3] = ((Object[]) list.get(i))[3].toString();// 优惠
								str[4] = ((Object[]) list.get(i))[4].toString();// 欠收
								continue;
							}
						}
					}

				
					if (!list3.isEmpty()) {
						for (int i = 0; i < list3.size(); i++) {
							if (((Object[]) list3.get(i))[0].toString().equals(str[0])) {
								str[5] = ((Object[]) list3.get(i))[1].toString();// 补交1
								continue;
							}
						}
					}

					if (!list4.isEmpty()) {
						for (int i = 0; i < list4.size(); i++) {
							if (((Object[]) list4.get(i))[0].toString().equals(str[0])) {
								str[6] = ((Object[]) list4.get(i))[1].toString();// 补交2
								continue;
							}
						}
					}
					
					if (!list5.isEmpty()) {
						for (int i = 0; i < list5.size(); i++) {
							if (((Object[]) list5.get(i))[0].toString().equals(str[0])) {
								str[7] = ((Object[]) list5.get(i))[1].toString();// 补交3
								continue;
							}
						}
					}
					
					if (!list6.isEmpty()) {
						for (int i = 0; i < list6.size(); i++) {
							if (((Object[]) list6.get(i))[0].toString().equals(str[0])) {
								str[8] = ((Object[]) list6.get(i))[1].toString();// 补交4
								continue;
							}
						}
					}
				
					
					str[10]=area.getId().toString();
					
					//欠费重新计算下
					if(StringUtils.isEmpty(str[1]))
						str[1]="0";
					BigDecimal b1= new BigDecimal(str[1]);
					
					if(StringUtils.isEmpty(str[2]))
						str[2]="0";
					BigDecimal b2= new BigDecimal(str[2]);
					
					if(StringUtils.isEmpty(str[3]))
						str[3]="0";
					BigDecimal b3= new BigDecimal(str[3]);
					
					str[4] = b1.subtract(b2).subtract(b3).toString();
					
					
					paramList.add(str);
				}
				
				return paramList;
	}

	/**
	 * 
	 * @param areaId
	 * @param year
	 * @return
	 */
	public List getMonthEdificeReport(int areaId,String beginDate,String endDate){
		String paramStr[] = beginDate.split("-");
		int year = Integer.parseInt(paramStr[0]);
		int _year = year;
		
		
		// 小区名称 应收款 欠收款
		StringBuffer hql = new StringBuffer();
		hql.append(
				"select h.edifice_id,sum(d.ought_money) as ought_money,sum(d.fact_money) as fact_money,sum(d.privilege_money) as privilege_money,sum(d.arrearage_money) as arrearage_money ")
				.append(" from tb_chg_house_detail as d inner join tb_basedata_house as h on d.house=h.id ")
				.append(" where date_format(d.record_month,'%Y-%m')>='")
				.append(beginDate)
				.append("' and date_format(d.record_month,'%Y-%m')<='")
				.append(endDate).append("' and d.charge_id like '%")
				.append(_subChargeId).append("%' and h.area_id='").append(areaId)
				.append("' group by h.edifice_id order by h.edifice_id");

		// 小区名称 补交前1
		StringBuffer hql3 = new StringBuffer();
		hql3.append(
				"select h.edifice_id,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
				.append(" inner join tb_basedata_house as h on h.id=p.house_id where p.pay_type='收款' and year(p.record_month)=")
				.append(--_year).append(" and year(p.gathering_time)=")
				.append(year).append(" and p.charge_id like '%")
				.append(_subChargeId).append("%' and h.area_id='").append(areaId)
				.append("' group by h.edifice_id order by h.edifice_id");

		// 小区名称 补交前2
		StringBuffer hql4 = new StringBuffer();
		hql4.append(
				"select h.edifice_id,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
				.append(" inner join tb_basedata_house as h on h.id=p.house_id where p.pay_type='收款' and year(p.record_month)=")
				.append(--_year).append(" and year(p.gathering_time)=")
				.append(year).append(" and p.charge_id like '%")
				.append(_subChargeId).append("%' and h.area_id='").append(areaId)
				.append("' group by h.edifice_id order by h.edifice_id");
		// 小区名称 补交前3
		StringBuffer hql5 = new StringBuffer();
		hql5.append(
				"select h.edifice_id,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
				.append(" inner join tb_basedata_house as h on h.id=p.house_id where p.pay_type='收款' and year(p.record_month)=")
				.append(--_year).append(" and year(p.gathering_time)=")
				.append(year).append(" and p.charge_id like '%")
				.append(_subChargeId).append("%' and h.area_id='").append(areaId)
				.append("' group by h.edifice_id order by h.edifice_id");

		// 小区名称 补交前4
		StringBuffer hql6 = new StringBuffer();
		hql6.append(
				"select h.edifice_id,sum(p.pay_money) as pay_money from tb_chg_pay_detail as p ")
				.append(" inner join tb_basedata_house as h on h.id=p.house_id where p.pay_type='收款' and year(p.record_month)<")
				.append(_year).append(" and year(p.gathering_time)=")
				.append(year).append(" and p.charge_id like '%")
				.append(_subChargeId).append("%' and h.area_id='").append(areaId)
				.append("' group by h.edifice_id order by h.edifice_id");
	
		List list = managerDao.getYearEdificeReportList(hql.toString());

		List list3 = managerDao.getYearEdificeReportList(hql3.toString());
		List list4 = managerDao.getYearEdificeReportList(hql4.toString());
		List list5 = managerDao.getYearEdificeReportList(hql5.toString());
		List list6 = managerDao.getYearEdificeReportList(hql6.toString());


		List paramList = new ArrayList();		
		List<EdificeEO> _list = edificeManager.getAllEdifice(areaId);
		for (EdificeEO edifice : _list) {

			String[] str = new String[11];
			str[0] = edifice.getEdificeName();
			str[10]=edifice.getId();
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					if (((Object[]) list.get(i))[0]!=null&&((Object[]) list.get(i))[0].toString().equals(str[10])) {
						str[1] = ((Object[]) list.get(i))[1].toString();// 应收
						str[2] = ((Object[]) list.get(i))[2].toString();// 实收
						str[3] = ((Object[]) list.get(i))[3].toString();// 优惠
						str[4] = ((Object[]) list.get(i))[4].toString();// 欠收
						continue;
					}
				}
			}

		

			if (!list3.isEmpty()) {
				for (int i = 0; i < list3.size(); i++) {
					if (((Object[]) list3.get(i))[0].toString().equals(str[10])) {
						str[5] = ((Object[]) list3.get(i))[1].toString();// 补交1
						continue;
					}
				}
			}

			if (!list4.isEmpty()) {
				for (int i = 0; i < list4.size(); i++) {
					if (((Object[]) list4.get(i))[0].toString().equals(str[10])) {
						str[6] = ((Object[]) list4.get(i))[1].toString();// 补交2
						continue;
					}
				}
			}
			
			if (!list5.isEmpty()) {
				for (int i = 0; i < list5.size(); i++) {
					if (((Object[]) list5.get(i))[0].toString().equals(str[10])) {
						str[7] = ((Object[]) list5.get(i))[1].toString();// 补交3
						continue;
					}
				}
			}
			
			if (!list6.isEmpty()) {
				for (int i = 0; i < list6.size(); i++) {
					if (((Object[]) list6.get(i))[0].toString().equals(str[10])) {
						str[8] = ((Object[]) list6.get(i))[1].toString();// 补交4
						continue;
					}
				}
			}
		
			
			//欠费重新计算下
			if(StringUtils.isEmpty(str[1]))
				str[1]="0";
			BigDecimal b1= new BigDecimal(str[1]);
			if(StringUtils.isEmpty(str[2]))
				str[2]="0";
			BigDecimal b2= new BigDecimal(str[2]);
			if(StringUtils.isEmpty(str[3]))
				str[3]="0";
			BigDecimal b3= new BigDecimal(str[3]);
			str[4] = b1.subtract(b2).subtract(b3).toString();
			
			paramList.add(str);
		}
		
		return paramList;
	}
	
	
	public List getMonthHouseReport(String edificeId,String beginDate,String endDate){
		String strParam[] = beginDate.split("-");
		int year = Integer.parseInt(strParam[0]);
			
		
		// 小区名称 应收款 欠收款
		StringBuffer hql = new StringBuffer();
		hql.append("select d.house,sum(d.ought_money) as ought_money,sum(d.fact_money) as fact_money,sum(d.privilege_money) as privilege_money,sum(d.arrearage_money) as arrearage_money ")
			.append(" from tb_chg_house_detail as d ")
			.append(" where date_format(d.record_month,'%Y-%m')>='").append(beginDate).append("' and date_format(d.record_month,'%Y-%m')<='").append(endDate)
			.append("' and d.charge_id like '%").append(_subChargeId)
			.append("%' and d.house like '").append(edificeId).append("%' ")
			.append(" group by d.house");
				
		List list = managerDao.getYearEdificeReportList(hql.toString());
		
		List paramList = new ArrayList();		
		List<HouseEO> _list = cellManager.getHouseByEdificeId(edificeId);
		for (HouseEO house : _list) {
			String[] str = new String[11];
			str[0] = house.getId();
			str[5]= house.getEdificeName()+house.getHouseName();
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					if (((Object[]) list.get(i))[0]!=null&&((Object[]) list.get(i))[0].toString().equals(str[0])) {
						str[1] = ((Object[]) list.get(i))[1].toString();// 应收
						str[2] = ((Object[]) list.get(i))[2].toString();// 实收
						str[3] = ((Object[]) list.get(i))[3].toString();// 优惠
						str[4] = ((Object[]) list.get(i))[4].toString();// 欠收
						continue;
					}
				}
			}

			
			//欠费重新计算下
			if(StringUtils.isEmpty(str[1]))
				str[1]="0";
			BigDecimal b1= new BigDecimal(str[1]);
			if(StringUtils.isEmpty(str[2]))
				str[2]="0";
			BigDecimal b2= new BigDecimal(str[2]);
			if(StringUtils.isEmpty(str[3]))
				str[3]="0";
			BigDecimal b3= new BigDecimal(str[3]);
			str[4] = b1.subtract(b2).subtract(b3).toString();
			
			paramList.add(str);
		}
		return paramList;
	}
	
	/**
	 * 物业费用明细
	 * 
	 * @param areaId
	 * @param houseId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List getPerReport(String areaId, String houseId, String beginDate,
			String endDate) {
		List paramList = new ArrayList();
		String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
		// 定义五个判断表达式，因为每个表达式都不一样
		String where = " and d.area_id in (" + areaIds + ") ";
		StringBuffer hql = new StringBuffer();
		if (!"".equals(areaId))
			where += " and d.area_id = " + areaId + " ";
		if (!"".equals(houseId))
			where += " and d.house like '" + houseId + "%' ";
		if (!"".equals(beginDate))
			where += " and date_format(d.record_month,'%Y-%m')>='" + beginDate + "' ";
		if (!"".equals(endDate))
			where += " and date_format(d.record_month,'%Y-%m')<='" + endDate  + "'";
		 
		
		hql.append("select d.owner_name,d.house,h.buildnum,date_format(d.record_month,'%Y-%m'),d.ought_money,d.fact_money,d.arrearage_money,d.privilege_money,d.id,d.privilege_reason ")
				.append(" from tb_chg_house_detail as d inner join tb_basedata_house as h on d.house=h.id ")
				.append(" where d.charge_id like '%").append(_subChargeId).append("%'"+where);
		
		paramList = managerDao.getYearReportList(hql.toString());
		
		
		return paramList;
	}
	
	public List getPayDetailById(int detailId){
		List paramList = new ArrayList();
		paramList = managerDao.getYearReportList("select pay_type,pay_money,privilege_reason from tb_chg_pay_detail where charge_house_detail_id="+detailId);
		return paramList;
	}
	
	/**
	 * 物业费欠费记录(按小区统计)
	 * 
	 * @param no
	 * @param i
	 * @return
	 */
	public List getArrearageArea(String endYearMonth) {
		
		List returnLs = new ArrayList();
		
		StringBuffer hql = new StringBuffer();
		String areaIds = (String) Struts2Utils.getSession().getAttribute(
				"areaIds");

		hql.append(
				"select area_id,sum(arrearage_Money) from tb_chg_house_detail ")
				.append(" where arrearage_Money>0 ")
				.append(" and date_format(record_Month,'%Y-%m') <='").append(endYearMonth).append("'")
				.append(" and charge_Id like '%").append(_subChargeId).append("%'")
				.append(" and area_Id in (").append(areaIds).append(") group by area_id");
		List list= managerDao.getYearReportList(hql.toString());
		
		
		hql = new StringBuffer();
		hql.append(
				"select area_Id,count(distinct house) from tb_chg_house_detail ")
				.append(" where arrearage_Money>0 ")
				.append(" and date_format(record_Month,'%Y-%m') <='").append(endYearMonth).append("'")
				.append(" and charge_Id like '%").append(_subChargeId).append("%'")
				.append(" and area_Id in (").append(areaIds).append(") group by area_Id");
		List list1= managerDao.getYearReportList(hql.toString());
		
		List<AreaEO> _list = areaManager.getAreaALL();
		for (AreaEO area : _list) {
			String[] str = new String[8];
			
			str[0] = area.getId().toString();// 小区编号
			str[1] = area.getAreaName();// 小区名称
			//户数 
			if (!list1.isEmpty()) {
				for (int i = 0; i < list1.size(); i++) {
					if (((Object[]) list.get(i))[0]!=null&&((Object[]) list.get(i))[0].toString().equals(area.getId().toString())) {
						str[2] = ((Object[]) list1.get(i))[1].toString();// 欠费
						continue;
					}
				}
			}
			//欠费金额
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					if (((Object[]) list.get(i))[0]!=null&&((Object[]) list.get(i))[0].toString().equals(area.getId().toString())) {
						str[3] = ((Object[]) list.get(i))[1].toString();// 欠费
						continue;
					}
				}
			}
			returnLs.add(str);
		}
		
		return returnLs;
	}
	
	
	/**
	 * 物业费欠费记录(按小区的楼栋统计)
	 * 
	 * @param no
	 * @param i
	 * @return
	 */
	public List getArrearageEdifice(Integer areaId,String endYearMonth) {
		
		List returnLs = new ArrayList();
		
		StringBuffer hql = new StringBuffer();

		hql.append(
				"select h.edifice_id,sum(arrearage_Money) from tb_chg_house_detail as p ")
				.append(" inner join tb_basedata_house as h on h.id=p.house ")
				.append(" where arrearage_Money>0 ")
				.append(" and date_format(record_Month,'%Y-%m') <='").append(endYearMonth).append("'")
				.append(" and p.area_Id = ").append(areaId)
				.append(" and charge_Id like '").append(areaId.toString()+_subChargeId).append("%'")
				.append(" group by h.edifice_id");
		List list= managerDao.getYearReportList(hql.toString());
		
		
		hql = new StringBuffer();
		hql.append(
				"select h.edifice_id,count(distinct house) from tb_chg_house_detail as p")
				.append(" inner join tb_basedata_house as h on h.id=p.house ")
				.append(" where arrearage_Money>0 ")
				.append(" and date_format(record_Month,'%Y-%m') <='").append(endYearMonth).append("'")
				.append(" and p.area_Id = ").append(areaId)
				.append(" and charge_Id like '").append(areaId.toString()+_subChargeId).append("%'")
				.append(" group by h.edifice_id");
		List list1= managerDao.getYearReportList(hql.toString());
		
		List<EdificeEO> _list = edificeManager.getAllEdifice(areaId);
		for (EdificeEO edifice : _list) {
			String[] str = new String[8];
			
			str[0] = edifice.getId();// 楼栋编号
			str[1] = edifice.getEdificeName();// 楼栋名称
			//户数 
			if (!list1.isEmpty()) {
				for (int i = 0; i < list1.size(); i++) {
					if (((Object[]) list.get(i))[0]!=null&&((Object[]) list.get(i))[0].toString().equals(edifice.getId().toString())) {
						str[2] = ((Object[]) list1.get(i))[1].toString();// 户数
						continue;
					}
				}
			}
			//欠费金额
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					if (((Object[]) list.get(i))[0]!=null&&((Object[]) list.get(i))[0].toString().equals(edifice.getId().toString())) {
						str[3] = ((Object[]) list.get(i))[1].toString();// 欠费
						continue;
					}
				}
			}
			returnLs.add(str);
		}
		
		return returnLs;
	}
	/**
	 * 物业费欠费记录(某楼栋下欠费房间)
	 * 
	 * @param no
	 * @param i
	 * @return
	 */
	public List getArrearageHouse(String edificeId,String endYearMonth) {
		
		
		
		StringBuffer hql = new StringBuffer();
		hql.append(
				"select h.id,h.house_Address,h.owner_Name,sum(arrearage_Money) from tb_chg_house_detail as p ")
				.append(" inner join tb_basedata_house as h on h.id=p.house ")
				.append(" where arrearage_Money>0 ")
				.append(" and date_format(record_Month,'%Y-%m') <='").append(endYearMonth).append("'")
				.append(" and h.edifice_Id = '").append(edificeId).append("'")
				.append(" and charge_Id like '%").append(_subChargeId).append("%'")
				.append(" group by  h.id,h.house_Address,h.owner_Name");
		List list= managerDao.getYearReportList(hql.toString());
		
		return list;
	}

	/**
	 * 物业费欠费明细
	 * 
	 * @param no
	 * @param i
	 * @param houseId
	 * @return
	 */
	public PaginatorTag getArrearageDetail(int no, int i, String houseId) {
		StringBuffer hql = new StringBuffer();
		hql.append(
				"select ownerName,house.id,house.buildnum,date_format(recordMonth,'%Y-%m'),arrearageMoney from ChargeHouseDetailEO")
				.append(" where arrearageMoney>0 and chargeId like '%").append(_subChargeId).append("%' and house.id='")
				.append(houseId)
				.append("' group by ownerName,house.id,house.buildnum,date_format(recordMonth,'%Y-%m'),arrearageMoney ");
		return managerDao.getPage(no, i, hql.toString());
	}

	/**
	 * 常规类费用统计
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public List getPayYear(String beginDate,String endDate) {
		String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
		List paramList = new ArrayList();
		List<AreaEO> _list = areaManager.getAreaALL();
		for (AreaEO area : _list) {
			String sql ="select charge_name,sum(h.fact_money) as fact_money,sum(h.privilege_money) as privilege_money from tb_chg_house_detail h "
					+" where area_id='"+area.getId()+"'"
				//	+"' and charge_id not like '"+area.getId()+"1101%' "
					+" and date_format(h.gathering_date,'%Y-%m')>='"+beginDate+"'"
					+" and date_format(h.gathering_date,'%Y-%m')<='"+endDate+"'"
					+" group by charge_name";
			List list1 = managerDao.getYearReportList(sql);
			
			
			if (!list1.isEmpty()) {
				String areaName = area.getAreaName();// 小区名称
				BigDecimal sumfactMoney= new BigDecimal(0);
				BigDecimal privilegeMoney= new BigDecimal(0);
				
				for (int i = 0; i < list1.size(); i++) {
					String[] str = new String[7];
					str[0] = area.getAreaName();// 小区名称
					str[1] = ((Object[]) list1.get(i))[0].toString();// 费用名称
					str[2] = ((Object[]) list1.get(i))[1].toString();// 实收
					str[3] = ((Object[]) list1.get(i))[2].toString();// 优惠
					paramList.add(str);
					
					sumfactMoney = sumfactMoney.add(new BigDecimal(str[2]));
					privilegeMoney = privilegeMoney.add(new BigDecimal(str[3]));
				}
				
				//进行合计
				String[] str = new String[7];
				str[0] = areaName;
				str[1] ="合计 ";
				str[2] = sumfactMoney.toString();
				str[3] = privilegeMoney.toString();
				paramList.add(str);
			}
			
		}
		return paramList;
	}
	
	
	/**
	 * 常规费用收费明细
	 * 
	 * @param no
	 * @param i
	 * @param houseId
	 * @param ownerName
	 * @return
	 */
	public PaginatorTag getPayDetail(int no, int i, String houseId,
			String ownerName,String chargeName,String startDate , String endDate) {
		StringBuffer hql = new StringBuffer();

		String where = "";
		String[] house = houseId.split("-");
		if(house!=null&&house.length>1)
			where += " and house.id like'" + houseId + "%' ";
		else
			where +=" and areaId = "+house[0];
		
		if (!"".equals(ownerName))
			where += " and ownerName='" + ownerName + "' ";
		if (!"".equals(chargeName))
			where += " and chargeName like '%" + chargeName + "%' ";
		if (!"".equals(startDate))
			where += "  and date_format(gathering_date,'%Y-%m-%d')>='"+startDate+"' ";
		if (!"".equals(endDate))
			where += "  and date_format(gathering_date,'%Y-%m-%d')<='"+endDate+"' ";
		hql.append(
				"select chargeName,ownerName,house.id,date_format(recordMonth,'%Y-%m'),factMoney,privilegeMoney,date_format(gatheringDate,'%Y-%m-%d'),privilegeReason")
				//.append(" from ChargeHouseDetailEO where chargeId not like concat(areaId,'1101%') ")
				.append(" from ChargeHouseDetailEO where 1=1 ")
				.append(where)
				.append(" order by house.id,recordMonth ");

		return managerDao.getPage(no, i, hql.toString());
	}

	/**
	 * 前台收费统计
	 * 
	 * @param no
	 * @param i
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public List getCollectReport(String areaId, String beginTime,
			String endTime, String _userName) {
		if(StringUtils.isEmpty(areaId))
			return new ArrayList();
		
		
		String where = " areaId = " + areaId + " ";
		if (!"".equals(beginTime))
			where += " and date_format(gatheringTime,'%Y-%m-%d')>='" + beginTime + "' ";
		if (!"".equals(endTime))
			where += " and date_format(gatheringTime,'%Y-%m-%d')<='" + endTime + "' ";

		// 前台人员
		StringBuffer hql = new StringBuffer();
		hql.append("select userName,sum(factMoney) FROM ChargeUserPayRecordEO where "
				+ where + " group by userName");
		List list = managerDao.find(hql.toString());
		return list;
	}
	
	//按收费员统计各收费项目
	public List getCollectReportForUser(String beginTime,
			String endTime, String userName) {
		
		String where = " userName = '" + userName + "' ";
		if (!"".equals(beginTime))
			where += " and date_format(gatheringTime,'%Y-%m-%d')>='" + beginTime + "' ";
		if (!"".equals(endTime))
			where += " and date_format(gatheringTime,'%Y-%m-%d')<='" + endTime + "' ";

		// 前台人员
		StringBuffer hql = new StringBuffer();
		hql.append("select chargeName,sum(factMoney) FROM ChargeUserPayRecordEO where "
				+ where + " group by chargeName");
		List list = managerDao.find(hql.toString());
		
		return list;
	}

	
	
	//物业费每月收费明细
	public List paymonthdetail(String areaId,String month) {
		String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
		StringBuffer sb = new StringBuffer();
		if(!"".equals(areaId))
			sb.append(" and areaId =").append(areaId);
		if (!"".equals(month))
			sb.append(" and date_format(gatheringTime,'%Y-%m')='").append(month).append("' ");
		
		StringBuffer hql = new StringBuffer();
		hql.append("FROM ChargeUserPayRecordEO where areaId in (").append(areaIds).append(") ");
		hql.append(sb.toString());	
		hql.append(" and chargeId like '").append(areaId).append("1101%' ").append(" order by gatheringTime,house.id");
		List list = managerDao.find(hql.toString());
		return list;
	}
	
	
	
	/**
	 * 物业费台账表 小区编号 年度日期
	 * 
	 * @param areaId
	 * @param year
	 * @return
	 */
	public List chargeList(String areaId, String edificeId, String year) {
		String areaIds = (String) Struts2Utils.getSession().getAttribute(
				"areaIds");
		List list = new ArrayList();
		StringBuffer sb = new StringBuffer();

		if (!"".equals(edificeId))
			sb.append(" and edificeId = '").append(edificeId).append("' ");
		StringBuffer _hql = new StringBuffer();
		_hql.append(
				"select id,areaId,areaName,buildnum from HouseEO where areaId = ")
				.append(areaId).append(sb).append(" order by id");
		List _list = managerDao.find(_hql.toString());

		Map<String, Object[]> map = cellManager.getAllOwnerName(
				Integer.parseInt(areaId), "", "", "");

		if (!_list.isEmpty()) {
			for (int i = 0; i < _list.size(); i++) {
				String str[] = new String[33];
				String houseId = ((Object[]) _list.get(i))[0].toString();
				// 业主姓名
				Object[] tmp = map.get(houseId);
				if (tmp != null && tmp[1] != null)
					str[0] = tmp[1].toString();
				else
					str[0] = "";
				// 楼房号
				str[1] = houseId.split("-")[1] + "-" + houseId.split("-")[2];
				// 房间面积
				str[2] = ((Object[]) _list.get(i))[3].toString();

				// 收费标准 每月应收
				String hql_1 = "from HousechargeEO where house.id = '"
						+ houseId + "' and chargeBasedata.id like'"
						+ String.valueOf(areaId) + "1101%' ";
				List<HousechargeEO> list_1 = managerDao.find(hql_1);
				String chargeId = "";
				if (!list_1.isEmpty() && list_1.size() == 1) {
					chargeId = String.valueOf(list_1.get(0).getChargeBasedata()
							.getId());
					String hql_2 = "from ChargeBasedataEO where id=" + chargeId;
					List<ChargeBasedataEO> list_2 = managerDao.find(hql_2);
					str[3] = String.valueOf(list_2.get(0).getPriceValue());
					str[4] = String.valueOf(ArithUtils.div(
							ArithUtils.mul(Float.valueOf(str[2]),
									Float.valueOf(str[3])), 1, 1));
				} else {
					str[3] = "";
					str[4] = "";
				}

				// 小于当前年度的欠款费用
				String hql_3 = "";
				if (!chargeId.isEmpty())
					hql_3 = "select house.id,sum(arrearageMoney) as arrearageMoney from ChargeHouseDetailEO where house.id='"
							+ houseId
							+ "' and chargeId='"
							+ chargeId
							+ "' and year(recordMonth) < "
							+ year
							+ " group by house.id";
				else
					hql_3 = "select house.id,sum(arrearageMoney) as arrearageMoney from ChargeHouseDetailEO where house.id='"
							+ houseId
							+ "' and chargeId like '"
							+ String.valueOf(areaId)
							+ "1101%' and year(recordMonth) < "
							+ year
							+ " group by house.id";
				List list_3 = managerDao.find(hql_3);
				if (null != list_3 && !list_3.isEmpty())
					str[5] = ((Object[]) list_3.get(0))[1].toString();
				else
					str[5] = "";
				// 当前年度12个月每月应收费用
				String hql_4 = "";
				if (!chargeId.isEmpty())
					hql_4 = "select recordMonth,sum(arrearageMoney) as arrearageMoney,sum(oughtMoney) as oughtMoney,sum(factMoney) as factMoney from ChargeHouseDetailEO"
							+ " where house.id='"
							+ houseId
							+ "' and chargeId='"
							+ chargeId
							+ "' and year(recordMonth) ="
							+ year
							+ " group by recordMonth order by recordMonth";
				else
					hql_4 = "select recordMonth,sum(arrearageMoney) as arrearageMoney,sum(oughtMoney) as oughtMoney,sum(factMoney) as factMoney from ChargeHouseDetailEO"
							+ " where house.id='"
							+ houseId
							+ "' and chargeId like '"
							+ String.valueOf(areaId)
							+ "1101%' and year(recordMonth) ="
							+ year
							+ " group by recordMonth order by recordMonth";
				List list_ = new ArrayList();
				list_ = managerDao.find(hql_4);
				if (!list_.isEmpty()) {
					for (int j = 0; j < list_.size(); j++) {
						String recordMonth = ((Object[]) list_.get(j))[0]
								.toString();
						// 欠收
						str[Integer.parseInt(recordMonth.split("-")[1]) + 5] = ((Object[]) list_
								.get(j))[1].toString();
						// 应收
						str[Integer.parseInt(recordMonth.split("-")[1]) + 19] = ((Object[]) list_
								.get(j))[2].toString();
					}
				} else {
					str[6] = "";
					str[7] = "";
					str[8] = "";
					str[9] = "";
					str[10] = "";
					str[11] = "";
					str[12] = "";
					str[13] = "";
					str[14] = "";
					str[15] = "";
					str[16] = "";
					str[17] = "";
				}
				// 合计 优惠
				String hql_5 = "select house.id,sum(factMoney) as factMoney from ChargeUserPayRecordEO where house.id='"
						+ houseId
						+ "' and chargeId='"
						+ chargeId
						+ "' and (payType='收款' or payType='预交') and year(recordMonth) ="
						+ year + " group by house.id";
				String hql_6 = "select house.id,sum(factMoney) as factMoney from ChargeUserPayRecordEO where house.id='"
						+ houseId
						+ "' and chargeId='"
						+ chargeId
						+ "' and payType='优惠' and year(recordMonth) ="
						+ year
						+ " group by house.id";
				List list_5 = managerDao.find(hql_5);
				List list_6 = managerDao.find(hql_6);

				if (!list_5.isEmpty())
					str[18] = ((Object[]) list_5.get(0))[1].toString();
				else
					str[18] = "";
				if (!list_6.isEmpty())
					str[19] = ((Object[]) list_6.get(0))[1].toString();
				else
					str[19] = "";
				str[32] = houseId;
				list.add(str);
			}
		}
		return list;
	}

	public List chargeHouseId(String houseId, String subHouseId) {
		List _list = new ArrayList();
		String chargeId = houseId.split("-")[0] + "1101";
		String hql = "select ownerName,'"
				+ subHouseId
				+ "',date_format(recordMonth,'%Y-%m') as recordMonth,oughtMoney,arrearageMoney from ChargeHouseDetailEO "
				+ " where house.id='" + houseId + "' and chargeId like '"
				+ chargeId + "%' order by recordMonth desc";
		List list = managerDao.find(hql);

		for (int i = 0; i < list.size(); i++) {
			String str[] = new String[9];
			// 业主名称
			str[0] = ((Object[]) list.get(i))[0].toString();
			// 楼房号
			str[1] = subHouseId;
			// 收费月份
			str[2] = ((Object[]) list.get(i))[2].toString();
			// 应收款
			str[3] = ((Object[]) list.get(i))[3].toString();
			// 欠款
			str[5] = ((Object[]) list.get(i))[4].toString();

			// 设定实收与优惠分别都为0
			str[4] = "0";
			str[8] = "0";
			// 实收 缴费日期 票据号
			String hql_1 = "select bh,factMoney,date_format(recordMonth,'%Y-%m') as recordMonth,date_format(gatheringTime,'%Y-%m-%d') as gatheringTime,payType from ChargeUserPayRecordEO "
					+ " where house.id='"
					+ houseId
					+ "' and chargeId like '"
					+ chargeId + "%' order by recordMonth desc";
			List list_1 = managerDao.find(hql_1);

			for (int j = 0; j < list_1.size(); j++) {
				String ret[] = new String[5];
				ret[0] = ((Object[]) list_1.get(j))[0].toString();// 票据号
				ret[1] = ((Object[]) list_1.get(j))[1].toString();// 实收 --优惠
				ret[2] = ((Object[]) list_1.get(j))[2].toString();//
				ret[3] = ((Object[]) list_1.get(j))[3].toString();
				ret[4] = ((Object[]) list_1.get(j))[4].toString();
				if (ret[2].equals(str[2])
						&& (ret[4].equals("收款") || ret[4].equals("预交"))) {
					if (Float.valueOf(str[4]) > 0)
						str[4] = String.valueOf(Float.valueOf(str[4])
								+ Float.valueOf(ret[1]));
					else
						str[4] = ret[1];
					str[6] = ret[0];
					str[7] = ret[3];
				} else if (ret[2].equals(str[2]) && ret[4].equals("优惠")) {
					if (Float.valueOf(str[8]) > 0)
						str[8] = String.valueOf(Float.valueOf(str[8])
								+ Float.valueOf(ret[1]));
					else
						str[8] = ret[1];
					str[6] = ret[0];
					str[7] = ret[3];
				}
			}
			_list.add(str);
		}
		return _list;
	}

	public void saveAudit(AuditEO audit) {
		auditManager.save(audit);
	}

	public List getSystemUser() {
		StringBuffer hql = new StringBuffer();
		hql.append("select id,userName FROM SysUserEO where userName<>'ffadmin'");
		List<SysUserEO> list = managerDao.find(hql.toString());
		return list;
	}

	public Page auditdetail(Page page, String beginDate, String endDate,
			String userName) {
		StringBuffer str = new StringBuffer();
		if (!"".equals(beginDate))
			str.append(" and gathering_time>='").append(beginDate).append("' ");
		if (!"".equals(endDate))
			str.append(" and gathering_time<='").append(endDate).append("' ");
		if (!"".equals(userName))
			str.append(" and userName='").append(userName).append("' ");
		page = auditDao.find(page, "from ChargeUserPayRecordEO where 1=1 "
				+ str.toString() + " order by gatheringTime");
		return page;
	}

	public Page auditList(Page page, String userName, String areaId) {
		String where = "";
		if (!"".equals(userName))
			where = " and userName = '" + userName + "' ";
		page = auditDao.find(page, "from AuditEO where areaId in (" + areaId
				+ ") " + where + " order by recordTime desc");
		return page;
	}

	public Page getSysAuditList(Page page, String userName, String areaId,
			String beginDate, String endDate) {
		String where = "";
		if (!"".equals(userName))
			where = " and userName = '" + userName + "' ";
		if (!"".equals(beginDate))
			where += " and beginDate >= '" + beginDate + "' ";
		if (!"".equals(endDate))
			where += " and endDate <= '" + endDate + "' ";

		page = auditDao.find(page, "from AuditEO where areaId in (" + areaId
				+ ") " + where + " order by recordTime desc");
		return page;
	}

	public void updateaudit(int id) {
		auditDao.updateaudit(id);
	}

	/**
	 * 用户报账列表
	 * 
	 * @param userName
	 * @return
	 */
	public List getAuditByUser(String userName, String areaId) {
		if (areaId.length() > 4)
			throw new RuntimeException("该权限无法访问，请重新设置用户管理小区权限。");
		return auditDao.find("FROM AuditEO WHERE areaId =" + areaId
				+ " and userName='" + userName + "' order by id desc");
	}

	public Map getAreaName() {
		Map map = new HashMap();
		List<AreaEO> list = areaManager.getAreaALL();
		for (AreaEO area : list) {
			map.put(area.getId(), area.getAreaName());
		}
		return map;
	}


}
package com.acec.wgt.service.repair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.core.orm.Page;
import com.acec.core.utils.ArithUtils;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.models.baseData.HouseDAO;
import com.acec.wgt.models.repair.CustomerrepairDAO;
import com.acec.wgt.models.repair.CustomerrepairEO;
import com.acec.wgt.models.repair.DevelopersrepairDAO;
import com.acec.wgt.models.repair.DevelopersrepairEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.CellManager;
import com.acec.wgt.service.suit.SuitCheckManager;

//Spring Service Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class CustomerrepairManager {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 
	@Autowired 
	private CustomerrepairDAO customerrepairDao;
	@Autowired
	private DevelopersrepairDAO developersrepairDao;
	@Autowired
	private AreaManager areaManager;
	private SuitCheckManager suitCheckManager;        
	@Autowired
	private HouseDAO houseDao;
	
	/**
	 * 客户请修登记表列表
	 * @param where
	 * @return
	 */
	public Page<CustomerrepairEO> getCustomerRepair(Page page,String where){
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		where +=" and areaId in ("+areaIds+") ";
		return customerrepairDao.getCustomerrepair(page, where);
	}
	
	/**
	 * 保存客户请修登记表记录
	 * @param customerrepair
	 */
	public void saveCustomerRepair(CustomerrepairEO customerrepair){
		customerrepairDao.getSession().saveOrUpdate(customerrepair);
	}
	
	/**
	 * 得到客户请修记录
	 * @param id
	 * @return
	 */
	public CustomerrepairEO getCustomerrepair(int id){
		return customerrepairDao.get(id);
	}	
	
	public String findRepairList(String houseId){
		//没有回访时间和回访结果的报修
		StringBuffer sb = new StringBuffer();
		sb.append(" and houseId='").append(houseId).append("' and flowState=0 group by areaId");
		return customerrepairDao.getCountRepair(sb.toString());
	}
	
	
	
	public List bxstat(String beginTime,String endTime){
		List<AreaEO> areaList = areaManager.getAreaALL(); 
		List viewList = new ArrayList();
		
		for(AreaEO area : areaList){//住户1 报修总数2 已处理3  未处理4  处理率5 已完成6  未完成7  完成率8
			String [] count = new String[]{"","0","0","0","0","0%","0","0","0%"};
			Integer areaId = area.getId();
			count[0] = area.getAreaName();
			count[1] = ownerCount(area.getId());

			StringBuffer sb=new StringBuffer();
			if(!"".equals(beginTime)) 
				sb.append(" and date_format(acceptedDate,'%Y-%m-%d')>=date_format('").append(beginTime).append("','%Y-%m-%d') ");
			if(!"".equals(endTime)) 
				sb.append(" and date_format(acceptedDate,'%Y-%m-%d')<=date_format('").append(endTime).append("','%Y-%m-%d') ");
			
			StringBuffer paramone = new StringBuffer();
			paramone.append(sb.toString()).append(" and areaId = ").append(area.getId()).append(" group by areaId");
			String par_one = customerrepairDao.getCountRepair(paramone.toString());//报修总数
			StringBuffer paramtwo = new StringBuffer();
			paramtwo.append(sb.toString()).append(" and achieveDate is not null").append(" and areaId = ").append(areaId).append(" GROUP BY areaId");
			String par_two = customerrepairDao.getCountRepair(paramtwo.toString());//报修已处理数
			StringBuffer paramthree = new StringBuffer();
			paramthree.append(sb.toString()).append(" and reciprocal is not null").append(" and areaId = ").append(areaId).append(" GROUP BY areaId");
			String par_three = customerrepairDao.getCountRepair(paramthree.toString());//报修已完成数
			count[2] = par_one;
			count[3] = par_two;			
			count[6] = par_three;
			
			//计算百分比
			if(Integer.parseInt(count[2])>0){
				if(Integer.parseInt(count[3])>0)
					count[4] = String.valueOf(Integer.parseInt(count[2]) - Integer.parseInt(count[3]));
				count[5] = String.valueOf(ArithUtils.div(Float.valueOf(count[3]),Float.valueOf(count[2]))*100+"%");
				if(Integer.parseInt(count[6])>0)
					count[7] = String.valueOf(Integer.parseInt(count[2]) - Integer.parseInt(count[6]));
				count[8] = String.valueOf(ArithUtils.div(Float.valueOf(count[6]),Float.valueOf(count[2]))*100+"%");
			}
			
			viewList.add(count);
		}
		return viewList;
		
	}
	
	/**
	 * 获取已入有客户信息的总房数
	 * @param areaId
	 * @return
	 */
	public String ownerCount(int areaId){
		List list = houseDao.find("select count(*) as b from HouseEO where areaId ="+areaId);
		if(list.isEmpty())
			return "0";
		else
			return list.get(0).toString();
	}
	
	/**
	 * 根据ID删除客户请修记录
	 * @param id
	 */
	public void delCustomerrepair(int id){
		customerrepairDao.delete(id);
	}
	
	/**
	 * 开发商维修单列表
	 * @param where
	 * @return
	 */
	public Page<DevelopersrepairEO> getDevelopersrepair(Page page,String where){
		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
		where +=" and areaId in ("+areaIds+") ";
		return developersrepairDao.getDeveRepair(page, where);
	}
	
	/**
	 * 保存开发商维修单
	 * @param developersrepair
	 */
	public void saveDeveloperreapir(DevelopersrepairEO developersrepair){
		developersrepairDao.getSession().saveOrUpdate(developersrepair);
	}
	 
	/**
	 * 得到开发商维修单
	 * @param id
	 * @return
	 */
	public DevelopersrepairEO getDevelopersrepair(int id){
		return developersrepairDao.get(id);
	}
	
	/**
	 * 根据ID删除开发商维修单
	 * @param id
	 */
	public void delDevelopersrepair(int id){
		developersrepairDao.delete(id);
	}
	
	
	
	public boolean getrecordMonth(String id){
		return customerrepairDao.getrecordMonth(id);
	}
	
	
	public Map getArea(){
		Map map=new HashMap();
		List<AreaEO> list=areaManager.getAreaALL();
		for(AreaEO area:list){
			map.put(area.getId(), area.getAreaName());
		}
		return map;
	}

	public SuitCheckManager getSuitCheckManager() {
		return suitCheckManager;
	}
	public void setSuitCheckManager(SuitCheckManager suitCheckManager) {
		this.suitCheckManager = suitCheckManager;
	}

}
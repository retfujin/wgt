/**
 * @author th
 * @version 1.0
 * @since 1.0
 */

package com.acec.wgt.models.ser;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;
import org.springframework.stereotype.Repository;

@Repository
public class SMSSendDAO extends HibernateEntityDao<SMSSend>{
 
	
//	public SMSSend getById(Integer id){		
//		return (SMSSend) find("from SMSSend where id="+id);
//	}
	
	public PaginatorTag getListByPage(int no , int i,String where) {
		return pagedQuery("from SMSSend where 1=1 "+where+" order by sendTime desc",no,i);		
	}

	/**
	 * 非分页查询(map参数)
	 * 
	 * @return
	 */
//	public List getList(Map map) {
//		return find("from SMSSend "); 
//	}
	
	/**
	 * 非分页查询(类参数)
	 * 
	 * @return
	 */
//	public List getList(SMSSend entity) {
//		return find("from SMSSend "); 
//	}
	
	/**
	 * 分页查询(类参数)
	 * @return
	 */
//	public PageForm getList(SMSSend entity,int pageNo,int pageSize) {
//		return this.getListForPage("SMSSend.getList",entity,pageNo,pageSize);	
//
//	}
	/**
	 * 分页查询(map参数)
	 * @return
	 */
//	public PageForm getList(Map map,int pageNo,int pageSize) {
//		return this.getListForPage("SMSSend.getList",map,pageNo,pageSize);	
//
//	}


	/**
	 * 发送条数统计
	 * @param map
	 * @return
	 */
//	public String getSendCount(Map map) {
//		Object o = this.getSqlMapClientTemplate().queryForObject("SMSSend.getSendCount",map);
//		if(o!=null)
//			return o.toString();
//		else
//			return "0";
//	}
	
	

}

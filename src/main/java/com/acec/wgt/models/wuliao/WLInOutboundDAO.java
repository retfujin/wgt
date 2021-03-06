package com.acec.wgt.models.wuliao;


import java.util.List;
import org.springframework.stereotype.Repository;
import com.acec.core.orm.hibernate.HibernateDao;

@Repository
public class WLInOutboundDAO extends HibernateDao<WLInOutboundEO, Integer> {
 
	
	
	public List getAll(){
		return find("from WLInOutboundEO");
	}
	
	public List getById(int id){
		return find("from WLInOutboundEO where id = "+id);
	}
 
	
	public List getList(String sql){
		return find(sql);
	}
	
	public String getParam(String sql){
		return findUnique(sql);
	}

}

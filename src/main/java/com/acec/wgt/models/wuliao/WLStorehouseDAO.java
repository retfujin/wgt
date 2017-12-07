package com.acec.wgt.models.wuliao;


import java.util.List;
import org.springframework.stereotype.Repository;
import com.acec.core.orm.hibernate.HibernateDao;

@Repository
public class WLStorehouseDAO extends HibernateDao<WLStorehouseEO, Integer> {
 
	
	
	public List getAll(){
		return find("from WLStorehouseEO");
	}
	
	public List getById(int id){
		return find("from WLStorehouseEO where id = "+id);
	}
 
}

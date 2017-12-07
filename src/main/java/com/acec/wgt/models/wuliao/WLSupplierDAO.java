package com.acec.wgt.models.wuliao;


import java.util.List;
import org.springframework.stereotype.Repository;
import com.acec.core.orm.hibernate.HibernateDao;

@Repository
public class WLSupplierDAO extends HibernateDao<WLSupplierEO, Integer> {
 
	
	
	public List getAll(){
		return find("from WLSupplierEO");
	}
	
	public List getById(int id){
		return find("from WLSupplierEO where id = "+id);
	}
 
}

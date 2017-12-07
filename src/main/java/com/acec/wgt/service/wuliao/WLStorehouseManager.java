package com.acec.wgt.service.wuliao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.models.wuliao.WLStorehouseDAO; 
import com.acec.wgt.models.wuliao.WLStorehouseEO;

@Service
@Transactional
public class WLStorehouseManager {
	
	@Autowired
	private WLStorehouseDAO wlstorehouseDAO;
	
	
	public WLStorehouseEO getById(int id) {
		List l = wlstorehouseDAO.getById(id);
		if(l.size()>0)
			return (WLStorehouseEO) l.get(0);
		else
			return null;
	}
	
	public List getAll(){
		return wlstorehouseDAO.getAll();
	}
	
	
	public void save(WLStorehouseEO entity) {		
		 wlstorehouseDAO.save(entity);
	}
	
	public void del(WLStorehouseEO entity) { 
		wlstorehouseDAO.delete(entity);
	}
}

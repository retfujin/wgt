package com.acec.wgt.service.wuliao;



import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.commons.persist.HibernateEntityDao;
import com.acec.wgt.models.wuliao.WLSupplierDAO;
import com.acec.wgt.models.wuliao.WLSupplierEO;

@Service
@Transactional
public class WLSupplierManager{
	
	@Autowired
	private WLSupplierDAO wlsupplierDAO;
	
	public WLSupplierEO getById(int id) {
		List l = wlsupplierDAO.getById(id);
		if(l.size()>0)
			return (WLSupplierEO) l.get(0);
		else
			return null;
	}
	public List getAll(){
		return wlsupplierDAO.getAll();
	}
	public void save(WLSupplierEO entity) {		
		wlsupplierDAO.save(entity);
	}	
	public void del(WLSupplierEO entity) { 
		wlsupplierDAO.delete(entity);
	}
}
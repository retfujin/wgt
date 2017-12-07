package com.acec.wgt.service.wuliao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.wgt.models.wuliao.WLCatalogDAO;
import com.acec.wgt.models.wuliao.WLCatalogEO;

@Service
@Transactional
public class WLCatalogManager {

	@Autowired
	private WLCatalogDAO wlcatalogDAO;
	
	public WLCatalogEO getById(int id) {
		List l = wlcatalogDAO.getById(id);
		if(l.size()>0)
			return (WLCatalogEO) l.get(0);
		else
			return null;
	}
	public List getAll(){
		return wlcatalogDAO.getAll();
	}
	public void save(WLCatalogEO entity) {		
		wlcatalogDAO.save(entity);
	}	
	public void del(WLCatalogEO entity) { 
		wlcatalogDAO.delete(entity);
	}
	
	public List getAllForGradCode(String gradCode) {
		List _ls;
		if("-1".equals(gradCode))
			_ls = getAll();
		else
			_ls = wlcatalogDAO.find("from WLCatalogEO where wlAssortmentEO.gradCode like ?",gradCode+"%");
		return _ls;
	}

	public WLCatalogEO getEntity(Integer id) {
		WLCatalogEO e = getById(id);
		e.getWlAssortmentEO().getId();
		return e;
	}

}
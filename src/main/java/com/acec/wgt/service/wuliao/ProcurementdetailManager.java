package com.acec.wgt.service.wuliao;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.core.orm.Page;
import com.acec.wgt.models.wuliao.ProcurementdetailDAO;
import com.acec.wgt.models.wuliao.ProcurementdetailEO;

@Service
@Transactional
public class ProcurementdetailManager {
    private final Logger logger = LoggerFactory.getLogger(ProcurementdetailManager.class);

    @Autowired
    private ProcurementdetailDAO procurementdetailDao;
 
    public Page getPage(Page page, String where) {
		Page procurementdetail = procurementdetailDao.findPage(page, ("from ProcurementdetailEO " + where));	
		return procurementdetail;
    }
    
    public List<ProcurementdetailEO> getTrainingdetail(String where) {
    	return procurementdetailDao.find("from ProcurementdetailEO " + where);
    }
 
    public ProcurementdetailEO getById(int id) {
    	return procurementdetailDao.get(id);
    }
    
    
    
    public void save(ProcurementdetailEO entity) {
    	procurementdetailDao.save(entity);
    }
}
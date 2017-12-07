package com.acec.wgt.service.contract;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.core.orm.Page;
import com.acec.wgt.models.contract.RenewDAO;
import com.acec.wgt.models.contract.RenewEO;

@Service
@Transactional
public class RenewManager {
    private final Logger logger = LoggerFactory.getLogger(RenewManager.class);

    @Autowired
    private RenewDAO renewDao;

    
    public Page getPage(Page page,String where) {
		return renewDao.findPage(page, "from RenewEO where isdel='N' "+where);
    }
 
    public List<RenewEO> getNotice(String where) {
    	return renewDao.find("from RenewEO where isdel='N' " + where);
    }

    /**
     * 根据id获取
     * 
     * @param id
     * @return
     */
    public RenewEO getById(int id) {
    	return renewDao.get(id);
    }

    /**
     * 新增;修改;
     * 
     * @param department
     * @return
     */
    public void save(RenewEO entity) {
    	renewDao.save(entity);
    }

    
}

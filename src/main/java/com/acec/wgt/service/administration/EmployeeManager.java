package com.acec.wgt.service.administration;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.core.orm.Page;
import com.acec.wgt.models.administration.EmployeeDAO;
import com.acec.wgt.models.administration.EmployeeEO;

@Service
@Transactional
public class EmployeeManager {
    private final Logger logger = LoggerFactory.getLogger(EmployeeManager.class);

    @Autowired
    private EmployeeDAO employeeDao;
    
    public Page getPage(Page page, String where) {
		Page employee = employeeDao.findPage(page, ("from EmployeeEO where isdel='N'" + where+" order by id desc"));	
		return employee;
    }
    
    public List<EmployeeEO> getApply(String where) {
    	return employeeDao.find("from EmployeeEO where isdel='N' " + where);
    }
 
    public EmployeeEO getById(int id) {
    	return employeeDao.get(id);
    }
        
    public void save(EmployeeEO entity) {
    	employeeDao.save(entity);
    }
    
}

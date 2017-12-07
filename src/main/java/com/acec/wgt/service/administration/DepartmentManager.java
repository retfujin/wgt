package com.acec.wgt.service.administration;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.Page;
import com.acec.wgt.models.administration.DepartmentDAO;
import com.acec.wgt.models.administration.DepartmentEO;

@Service
@Transactional
public class DepartmentManager {
    private final Logger logger = LoggerFactory.getLogger(EmployeeManager.class);

    @Autowired
    private DepartmentDAO departmentDao;

    /**
     * 页面显示部门列表
     * 
     * @param page
     * @return
     */
    public Page getPage(Page page) {
	return departmentDao.findPage(page, ("from DepartmentEO where isdel='N'"));
    }

    /**
     * 内部调用部门列表
     * 
     * @return
     */
    public List<DepartmentEO> getDepartments(String where) {
	return departmentDao.find("from DepartmentEO where isdel='N' " + where);
    }

    /**
     * 根据id获取对应部门
     * 
     * @param id
     * @return
     */
    public DepartmentEO getById(int id) {
	return departmentDao.get(id);
    }

    /**
     * 检查重名
     * 
     * @param id
     *            是否新建
     * @return true 正常 false 不可命名
     */
    public boolean checkName(DepartmentEO department) {
	StringBuffer sb = new StringBuffer();
	sb.append(" where isdel='N' and name='" + department.getName() + "'");
	if (department.getId() != null)
	    sb.append(" and id<>'" + department.getId() + "'");

	List<DepartmentEO> departments = departmentDao.find("from DepartmentEO" + sb.toString());
	if (departments.isEmpty())
	    return true;
	else
	    return false;
    }

    /**
     * 新增;修改;逻辑删除部门
     * 
     * @param department
     * @return
     */
    public void saveDepartment(DepartmentEO department) {
	departmentDao.save(department);
    } 
}

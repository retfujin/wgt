package com.acec.wgt.service.administration;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.Page;
import com.acec.wgt.models.administration.PositionDAO;
import com.acec.wgt.models.administration.PositionEO;

@Service
@Transactional
public class PositionManager {
    private final Logger logger = LoggerFactory.getLogger(EmployeeManager.class);

    @Autowired
    private PositionDAO positionDao;

    /**
     * 页面显示岗位列表
     * 
     * @param page
     * @return
     */
    public Page getPage(Page page, Map<String, String[]> map) {
	StringBuffer sb = new StringBuffer();
	sb.append(" where isdel='N'");

	String[] departmentId = map.get("departmentId");
	if (departmentId != null)
	    if (!departmentId[0].equals(""))
		sb.append(" and department='" + departmentId[0] + "'");

	return positionDao.findPage(page, ("from PositionEO" + sb.toString()));
    }

    /**
     * 内部调用岗位列表
     * 
     * @return
     */
    public List<PositionEO> getPositions(String where) {
    	return positionDao.find("from PositionEO where isdel='N' " + where);
    }

    /**
     * 根据id获取对应岗位
     * 
     * @param id
     * @return
     */
    public PositionEO getById(int id) {
	return positionDao.get(id);
    }

    /**
     * 新增;修改;逻辑删除岗位
     * 
     * @param department
     * @return
     */
    public void save(PositionEO position) {
	positionDao.save(position);
    }

    /**
     * 检查重名
     * 
     * @param id
     * @param name
     * @param departmentId
     * @return
     */
    public boolean checkName(PositionEO position) {
	StringBuffer sb = new StringBuffer();
	sb.append(" where isdel='N' and name='" + position.getName() + "'");
	if (position.getId() != null)
	    sb.append(" and id<>'" + position.getId() + "'");
	
	List<PositionEO> positions = positionDao.find("from PositionEO" + sb.toString());
	if (positions.isEmpty())
	    return true;
	else
	    return false;
    }
}

package com.acec.wgt.service.administration;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.Page;
import com.acec.wgt.models.administration.RankDAO;
import com.acec.wgt.models.administration.RankEO;

@Service
@Transactional
public class RankManager {
    private final Logger logger = LoggerFactory.getLogger(EmployeeManager.class);

    @Autowired
    private RankDAO rankDao;

    /**
     * 页面显示职位列表
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

	return rankDao.findPage(page, ("from RankEO where isdel='N' " + sb.toString()));
    }

    /**
     * 内部调用职位列表
     * 
     * @return
     */
    public List<RankEO> getRanks(String where) {
	return rankDao.find("from RankEO where isdel='N' " + where);

    }

    /**
     * 根据id获取对应职位
     * 
     * @param id
     * @return
     */
    public RankEO getById(int id) {
	return rankDao.get(id);
    }

    /**
     * 新增;修改;逻辑删除职位
     * 
     * @param department
     * @return
     */
    public void save(RankEO rank) {
	rankDao.save(rank);
    }

    /**
     * 检查重名
     * 
     * @param id
     * @param name
     * @param departmentId
     * @return
     */
    public boolean checkName(RankEO rank) {
	StringBuffer sb = new StringBuffer();
	sb.append(" where isdel='N' and name='" + rank.getName() + "'");
	if (rank.getId() != null)
	    sb.append(" and id<>'" + rank.getId() + "'");
	if (rank.getDepartment().getId() != null)
	    sb.append(" and department='" + rank.getDepartment().getId() + "'");

	List<RankEO> ranks = rankDao.find("from RankEO" + sb.toString());
	if (ranks.isEmpty())
	    return true;
	else
	    return false;
    }
}

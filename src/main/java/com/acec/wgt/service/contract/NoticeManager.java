package com.acec.wgt.service.contract;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.core.orm.Page;
import com.acec.wgt.models.contract.NoticeDAO;
import com.acec.wgt.models.contract.NoticeEO;

@Service
@Transactional
public class NoticeManager {
    private final Logger logger = LoggerFactory.getLogger(NoticeManager.class);

    @Autowired
    private NoticeDAO noticeDao;

    
    public Page getPage(Page page,String where) {
		StringBuffer sb = new StringBuffer();
		sb.append(" where isdel='N'");	 
		return noticeDao.findPage(page, "from NoticeEO" + sb.toString()+where);
    }
 
    public List<NoticeEO> getNotice(String where) {
    	return noticeDao.find("from NoticeEO where isdel='N' " + where);
    }

    /**
     * 根据id获取
     * 
     * @param id
     * @return
     */
    public NoticeEO getById(int id) {
    	return noticeDao.get(id);
    }

    /**
     * 新增;修改;
     * 
     * @param department
     * @return
     */
    public void save(NoticeEO entity) {
    	noticeDao.save(entity);
    }

    
}

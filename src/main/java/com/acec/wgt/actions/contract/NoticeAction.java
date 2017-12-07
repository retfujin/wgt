package com.acec.wgt.actions.contract;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.core.orm.Page;
import com.acec.core.utils.AuthorityService;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.contract.NoticeEO;
import com.acec.wgt.models.sys.SysUserEO;
import com.acec.wgt.service.contract.NoticeManager;
import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;

public class NoticeAction extends BaseAction {
    @Autowired
    private NoticeManager noticeManger;
    // 分页
    private Page<NoticeEO> page = new Page<NoticeEO>(20);// 每页20条记录
    private NoticeEO entity;
    SysUserEO user = AuthorityService.getUser();

    public String list() {	
    	String title = getRequestValue("title", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(title))
 			sb.append(" and title like '%").append(title).append("%' ");
		page = noticeManger.getPage(page,sb.toString());
		page.setUrl("notice!list.action");
		return "list";
    }

    public String add() {
    	return "add";
    }
    
    public String sel() {
    	entity = noticeManger.getById(entity.getId());
    	return "sel";
    }

    public void del() {
		try {
		    entity = noticeManger.getById(entity.getId());
		    entity.setIsdel("Y");
		    noticeManger.save(entity);
		    write("{success:true,msg:'删除成功'}");
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'删除失败.原因：" + ex.getMessage() + "'}");
		}
    }
    
    public void save() {    	
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
		    if(entity.getIsdel().equals("")){
		    	entity.setIsdel("N");
		    	entity.setRecordid(user.getId());
		    	entity.setRecordname(user.getUserName());	
		    	entity.setRecordtime(sf.format(new Date()));
		    }
		    noticeManger.save(entity);
		    write("{success:true,msg:'保存成功'}");
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'保存失败.原因：" + ex.getMessage() + "'}");
		}
    }

    public Page getPage() {
    	return page;
    }
    public void setPage(Page page) {
    	this.page = page;
    }
    public NoticeEO getEntity() {
    	return entity;
    }
    public void setEntity(NoticeEO entity) {
    	this.entity = entity;
    }
    
}

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
import com.acec.wgt.models.contract.RenewEO;
import com.acec.wgt.models.sys.SysUserEO;
import com.acec.wgt.service.contract.RenewManager;
import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;

public class RenewAction extends BaseAction {
    @Autowired
    private RenewManager renewManager;
    // 分页
    private Page<RenewEO> page = new Page<RenewEO>(20);// 每页20条记录
    private RenewEO entity;
    SysUserEO user = AuthorityService.getUser();

    public String list() {	
    	String departname = getRequestValue("departname", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(departname))
 			sb.append(" and departname like '%").append(departname).append("%' ");
		page = renewManager.getPage(page,sb.toString());
		page.setUrl("demand!list.action");
		return "list";
    }

    public String add() {
    	return "add";
    }
    
    public String sel() {
    	entity = renewManager.getById(entity.getId());
    	return "sel";
    }

    public void del() {
		try {
		    entity = renewManager.getById(entity.getId());
		    entity.setIsdel("Y");
		    renewManager.save(entity);
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
		    renewManager.save(entity);
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
    public RenewEO getEntity() {
    	return entity;
    }
    public void setEntity(RenewEO entity) {
    	this.entity = entity;
    }
    
}

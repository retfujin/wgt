package com.acec.wgt.actions.administration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.core.orm.Page;
import com.acec.core.utils.AuthorityService;
import com.acec.core.web.struts2.BaseAction;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.administration.TrainingEO;
import com.acec.wgt.models.administration.TrainingdetailEO;
import com.acec.wgt.service.administration.TrainingManager;
import com.acec.wgt.service.administration.TrainingdetailManager;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.models.sys.SysUserEO;

public class TrainingAction extends BaseAction {
    
	@Autowired
    private TrainingManager trainingManager;
    @Autowired
    private TrainingdetailManager trainingdetailManager;
    @Autowired
    private AreaManager areaManager;
    
    
    // 分页
    private Page<TrainingEO> page = new Page<TrainingEO>(20);// 每页20条记录

    private TrainingEO entity;
    private TrainingdetailEO dentity;
    
    SysUserEO user = AuthorityService.getUser();
    
    public String list() {
 		String name = getRequestValue("name", "");
 		StringBuffer sb = new StringBuffer();
 		if(!"".equals(name))
 			sb.append(" and name like '").append(name).append("%' ");
 		String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
		page = trainingManager.getPage(page, sb.toString());
		page.setUrl("training!list.action");	
		return "list";
    }
    
    public String add() {
    	retList = areaManager.getAreaALL();
		return "add";
    }
    
    public String sel() {
		entity = trainingManager.getById(entity.getId());
		StringBuffer sb = new StringBuffer();
		sb.append(" where trainingid = ").append(entity.getId());
		viewList = trainingdetailManager.getTrainingdetail(sb.toString());
		return "sel";
    }
    
    public String edit() {
    	retList = areaManager.getAreaALL();
		entity = trainingManager.getById(entity.getId());
		StringBuffer sb = new StringBuffer();
		sb.append(" where trainingid = ").append(entity.getId());
		viewList = trainingdetailManager.getTrainingdetail(sb.toString());
		return "edit";
    }

    public void del() {
		try {
		    entity = trainingManager.getById(entity.getId());
		    entity.setIsdel("Y");
		    trainingManager.save(entity);
		    write("{success:true,msg:'删除成功'}");
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'删除失败.原因：" + ex.getMessage() + "'}");
		}
    }

    public void save() {
    	String[] trainingdate = getRequest().getParameterValues("trainingdate");
    	String[] content = getRequest().getParameterValues("content");
    	String[] unit = getRequest().getParameterValues("unit");
    	String[] results = getRequest().getParameterValues("results");
    	String[] detailid = getRequest().getParameterValues("detailid");
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
		    if (entity.getIsdel().equals("")){
		    	entity.setIsdel("N");
		    	entity.setRecordid(user.getId());
		    	entity.setRecordname(user.getUserName());
		    	entity.setRecordtime(sf.format(new Date()));
		    }
		    trainingManager.save(entity);

		    if(trainingdate!=null && trainingdate.length>0){
		    	for(int i = 0; i < trainingdate.length;i++){
		    		TrainingdetailEO d = new TrainingdetailEO();
		    		if(null!=trainingdate && null!=trainingdate[i] && !"".equals(trainingdate[i])){
		    			Date  date  =  new SimpleDateFormat("yyyy-MM-dd").parse(trainingdate[i]);
		    			d.setTrainingdate(new java.sql.Date(date.getTime()));		    			
		    		}
		    		if(null != content && null!=content[i] && !"".equals(content[i]))
		    			d.setContent(content[i]);
		    		if(null!=unit && null!=unit[i] && !"".equals(unit[i]))
		    			d.setUnit(unit[i]);
		    		if(null!=results && null!=results[i] && !"".equals(results[i]))
		    			d.setResults(results[i]);
		    		if(null!=detailid && i<detailid.length){
		    			if(null!=detailid[i] && !"".equals(detailid[i]))
		    				d.setId(Integer.parseInt(detailid[i]));
		    		}
		    			
		    		d.setTrainingid(entity.getId());
		    		trainingdetailManager.save(d);
		    	}
		    }
		    
		    write("{success:true,msg:'保存成功'}");	
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'保存失败.原因：" + ex.getMessage() + "'}");
		}
    }

    
    
    
    
    
    
    
    
    
    public Page<TrainingEO> getPage() {
    	return page;
    }
    public void setPage(Page<TrainingEO> page) {
    	this.page = page;
    }
    public TrainingEO getEntity() {
    	return entity;
    }
    public void setEntity(TrainingEO entity) {
    	this.entity = entity;
    }
	public TrainingdetailEO getDentity() {
		return dentity;
	}
	public void setDentity(TrainingdetailEO dentity) {
		this.dentity = dentity;
	}
   
}
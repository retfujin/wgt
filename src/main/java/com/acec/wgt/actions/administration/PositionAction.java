package com.acec.wgt.actions.administration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.administration.PositionEO;
import com.acec.wgt.service.administration.DepartmentManager;
import com.acec.wgt.service.administration.PositionManager;
import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;

public class PositionAction extends BaseAction {
    @Autowired
    private PositionManager positionManger;
    @Autowired
    private DepartmentManager departmentManger;
    // 分页
    private Page<PositionEO> page = new Page<PositionEO>(20);// 每页20条记录
    private PositionEO entity;
    private List departments;

    public String list() {
		departments = departmentManger.getDepartments("");
	
		Map<String, String[]> map = getRequest().getParameterMap();
		page = positionManger.getPage(page, map);
	
		page.setUrl("position!list.action" + getParamString(""));
	
		return "list";
    }

    public String add() {
    	return "edit";
    }

    public String edit() {
		entity = positionManger.getById(entity.getId());
		return "edit";
    }

    public void del() {
		try {
		    entity = positionManger.getById(entity.getId());
		    entity.setIsdel("Y");
		    positionManger.save(entity);
		    write("{success:true,msg:'删除成功'}");
		} catch (Exception ex) {
		    ex.printStackTrace();
		    write("{success:false,msg:'删除失败.原因：" + ex.getMessage() + "'}");
		}
    }
    
    /**
     * 页面下拉框选择
     * @throws JSONException 
     * @throws IOException 
     */
    public void ajaxPositionByDepartmentId() throws IOException, JSONException{
    	String where = " and department="+getRequest().getParameter("departmentId");
    	List<PositionEO> ls = new ArrayList();
//    	PositionEO position = new PositionEO();
//    	position.setId(null);
//    	position.setName("==请选择==");
//    	ls.add(position);
    	List<PositionEO> _ls = positionManger.getPositions(where);
    	ls.addAll(_ls);
    	write(JSONUtil.serialize(ls));
    }
    
    
    public void save() {
		try {
		    if (entity.getIsdel().equals(""))
			entity.setIsdel("N");
		    boolean res = positionManger.checkName(entity);
		    if (res) {
			positionManger.save(entity);
			write("{success:true,msg:'保存成功'}");
		    } else
			write("{success:false,msg:'岗位名称已存在'}");
	
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

    public PositionEO getEntity() {
	return entity;
    }

    public void setEntity(PositionEO entity) {
	this.entity = entity;
    }

    public List getDepartments() {
	return departments;
    }

    public void setDepartments(List departments) {
	this.departments = departments;
    }

}

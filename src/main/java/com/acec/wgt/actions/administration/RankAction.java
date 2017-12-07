package com.acec.wgt.actions.administration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.administration.RankEO;
import com.acec.wgt.service.administration.DepartmentManager;
import com.acec.wgt.service.administration.RankManager;

public class RankAction extends BaseAction {
    @Autowired
    private RankManager rankManger;
    @Autowired
    private DepartmentManager departmentManger;

    private Page<RankEO> page = new Page<RankEO>(20);// 每页20条记录
    private RankEO entity;
    private List departments;

    public String list() {
	departments = departmentManger.getDepartments("");

	Map<String, String[]> map = getRequest().getParameterMap();
	page = rankManger.getPage(page, map);

	page.setUrl("position!list.action" + getParamString(""));

	return "list";
    }

    public String add() {
	departments = departmentManger.getDepartments("");
	return "edit";
    }

    public String edit() {
	departments = departmentManger.getDepartments("");
	entity = rankManger.getById(entity.getId());
	return "edit";
    }

    public void del() {
	try {
	    entity = rankManger.getById(entity.getId());
	    entity.setIsdel("Y");
	    rankManger.save(entity);
	    write("{success:true,msg:'删除成功'}");
	} catch (Exception ex) {
	    ex.printStackTrace();
	    write("{success:false,msg:'删除失败.原因：" + ex.getMessage() + "'}");
	}
    }

    public void save() {
	try {
	    if (entity.getIsdel().equals(""))
		entity.setIsdel("N");
	    boolean res = rankManger.checkName(entity);
	    if (res) {
		rankManger.save(entity);
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

    public RankEO getEntity() {
	return entity;
    }

    public void setEntity(RankEO entity) {
	this.entity = entity;
    }

    public List getDepartments() {
	return departments;
    }

    public void setDepartments(List departments) {
	this.departments = departments;
    }
}

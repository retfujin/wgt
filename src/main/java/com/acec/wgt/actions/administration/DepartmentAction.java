package com.acec.wgt.actions.administration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.administration.DepartmentEO;
import com.acec.wgt.service.administration.DepartmentManager;

public class DepartmentAction extends BaseAction {
    @Autowired
    private DepartmentManager departmentManger;
    // 分页
    private Page<DepartmentEO> page = new Page<DepartmentEO>(20);// 每页20条记录
    private DepartmentEO entity;

    public String list() {
	page = departmentManger.getPage(page);
	page.setUrl("employee!list.action" + getParamString(""));

	return "list";
    }

    public String add() {
	return "edit";
    }

    public String edit() {
	entity = departmentManger.getById(entity.getId());
	return "edit";
    }

    public void del() {
	try {
	    entity = departmentManger.getById(entity.getId());
	    entity.setIsdel("Y");
	    departmentManger.saveDepartment(entity);
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
	    boolean res = departmentManger.checkName(entity);
	    if (res) {
		departmentManger.saveDepartment(entity);
		write("{success:true,msg:'保存成功'}");
	    } else
		write("{success:false,msg:'部门名称已存在'}");

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

    public DepartmentEO getEntity() {
	return entity;
    }

    public void setEntity(DepartmentEO entity) {
	this.entity = entity;
    }
}

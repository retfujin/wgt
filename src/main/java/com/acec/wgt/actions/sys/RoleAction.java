package com.acec.wgt.actions.sys;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.sys.SysRoleEO;
import com.acec.wgt.service.sys.AdminRoleManager;

public class RoleAction extends BaseAction {
	
	//业务层类
	@Autowired
	private AdminRoleManager adminRoleManager;
	

	private Page page = new Page(10);
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	private SysRoleEO entity;
	
	//列表
	public String list() {
		
		
		page  = adminRoleManager.getAllRole(page);
		
		
		return "list";
	}
	
	//新增
	public String add(){
		viewList = adminRoleManager.getAllModel();
		
		return "add";
	}
	
	//保存
	public void save(){
		
		if(entity!=null){
			String companyId = (String)getSession().get("companyId");
			String companyName = (String)getSession().get("companyName");
			
			entity.setCompanyId(companyId);
			entity.setCompanyName(companyName);
			
			if(entity.getId()!=null){//是编辑
				adminRoleManager.save(entity);
				write("{success:true,msg:'保存成功'}");
				return;
			}
			else{//新增
				
				//同一公司的角色名不允许重复
				List ls = adminRoleManager.checkRepeat(entity.getName(),entity.getCompanyId());
				if(!ls.isEmpty()){
					forwardStr="对不起，数据库中已有重复的角色名了。";
					write("{success:false,msg:'保存失败。"+forwardStr+"'}");
					return ;
				}
				else{
					
					try{
						adminRoleManager.save(entity);
					}catch(Exception ex){
						write("{success:false,msg:'保存失败。数据异常"+ex.getMessage()+"'}");
						logger.error("保存失败", ex);
					}
					
					write("{success:true,msg:'保存成功'}");
					return;
				}
			}
		}
		
		write("{success:false,msg:'保存失败。'}");
		return ;
	}
	
	//编辑
	public String edit(){
		
		String id = ServletActionContext.getRequest().getParameter("id");

		viewList = adminRoleManager.getModelForRole(id);
		entity = adminRoleManager.getEntity();
		

		return "add";
	}
		
		
	//删除
	public void del(){
		String id = ServletActionContext.getRequest().getParameter("id");
		if (id != null) {
			List ls = adminRoleManager.findUserForRoleid(Integer.parseInt(id));
			if(ls.isEmpty())
				try{adminRoleManager.delRole(Integer.parseInt(id));}
				catch(Exception ex){
					forwardStr="删除失败：原因："+ex.toString();
					write("{success:false,msg:'"+forwardStr+"'}");
					return ;
				}
			else{
				forwardStr="删除失败，角色下面还有存在登陆用户,请先删除用户。";
				write("{success:false,msg:'"+forwardStr+"'}");
				return ;
			}
				
		}
		write("{success:true,msg:'删除成功'}");
		return ;
	}
	


	public SysRoleEO getEntity() {
		return entity;
	}

	public void setEntity(SysRoleEO entity) {
		this.entity = entity;
	}

	
}

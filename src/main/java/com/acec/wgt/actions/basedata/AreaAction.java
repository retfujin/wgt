package com.acec.wgt.actions.basedata;


import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.service.basedata.AreaManager;

public class AreaAction extends BaseAction {

	@Autowired
	private com.acec.wgt.service.basedata.AreaManager areaManager;
	
	private AreaEO entity;
	private Page<AreaEO> page = new Page<AreaEO>(15);//每页20条记录
	
	@Autowired
	private AreaManager AreaManager;

	/**
	 * 小区列表
	 * @return
	 */
	public String list(){
		page = areaManager.getListByPage(page);
		return "list";
	}
	
	/**
	 * 新增小区 
	 * @return
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 编缉小区资料
	 * @return
	 */
	public String edit(){
		entity =areaManager.getArea(entity.getId());
		return "edit";
	}
	
	/**
	 * 保存小区资料
	 * @return
	 */
	public void save(){
		String areaId=getRequest().getParameter("areaId");//小区编号
		String poolRatio=getRequest().getParameter("poolRatio");//公摊比例
		try{
			if(areaId!=null){
				AreaEO areaEO=areaManager.getAreaById(Integer.parseInt(areaId));//判断小区编号是否已设置
			
				if(areaEO!=null){
					write("{success:false,msg:'保存失败,小区编号已存在!'}");
					return ;
				}
			}
			
			if(poolRatio!=null&& !poolRatio.equals(""))
				entity.setPoolRatio(Float.parseFloat(poolRatio));
			else
				entity.setPoolRatio(0f);
			
			if(entity.getId()==null){
				String areaIds=(String)getSession().get("areaIds");
				getSession().put("areaIds",areaIds+","+areaId);
			}
			
			//设置物业公司id，name
			String companyId = (String)getSession().get("companyId");
			String companyName = (String)getSession().get("companyName");
			entity.setCompanyId(companyId);
			entity.setCompanyName(companyName);
			
			areaManager.saveArea(entity,areaId);			
			write("{success:true,msg:'保存成功'}");
		}catch(Exception ex){
			write("{success:false,msg:'保存失败。数据异常"+ex.getMessage()+"'}");
			logger.error("保存失败", ex);
		}
		return ;
	}
	
	//删除小区资料
	public void del(){
		try{
			areaManager.delArea(entity);
			write("{success:true,msg:'删除成功'}");
		}catch(Exception ex){
			logger.error("删除失败", ex);
			write("{success:false,msg:'删除失败。"+ex.getMessage()+"'}");
		}
		return ;
	}
		
	public Page<AreaEO> getPage() {
		return page;
	}
	public AreaEO getEntity() {
		return entity;
	}
	public void setEntity(AreaEO entity) {
		this.entity = entity;
	}
}
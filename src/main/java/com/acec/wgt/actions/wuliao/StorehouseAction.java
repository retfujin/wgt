package com.acec.wgt.actions.wuliao;

import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.wuliao.WLStorehouseEO;
import com.acec.wgt.service.wuliao.WLStorehouseManager;
import org.springframework.beans.factory.annotation.Autowired;

public class StorehouseAction extends BaseAction {
	private static final long serialVersionUID = -2621721299115843546L;
	@Autowired
	private WLStorehouseManager wlStorehouseManager;
	
	private WLStorehouseEO e;
	private Integer id;
	
	public String add(){
		return "add";
	}
	
	public String edit(){
        e=wlStorehouseManager.getById(id);
        return "edit";
    }
	
	public String list(){
        viewList=wlStorehouseManager.getAll();
        return "list";
    }
	 
	 
    public void save(){
        try{
        	if("tadd".equals(getRequest().getParameter("add"))){//有重复的编号
        		WLStorehouseEO a =wlStorehouseManager.getById(e.getId());
        		if(a!=null){
        			write("{success:false,msg:'已存在重复的编号了'}");
        			return;
        		}
        	}        	       	
        	wlStorehouseManager.save(e);
        	write("{success:true,msg:'保存成功'}");
        }catch(Exception ex){
        	write("{success:false,msg:'保存失败。数据异常"+ex.getMessage()+"'}");
			logger.error("保存失败", ex);
        }
        return ;
    }
    
    public void del(){
        try{
        	wlStorehouseManager.del(e);
        	write("{success:true,msg:'删除成功'}");
        }catch(Exception ex){
        	logger.error("删除失败", ex);
			write("{success:false,msg:'删除失败。"+ex.getMessage()+"'}");
        } 
        return ;
    }
    
    
    
    
    
	public WLStorehouseEO getE() {
		return e;
	}
	public void setE(WLStorehouseEO e) {
		this.e = e;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
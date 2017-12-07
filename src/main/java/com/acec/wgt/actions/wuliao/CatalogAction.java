package com.acec.wgt.actions.wuliao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.wuliao.WLCatalogEO;
import com.acec.wgt.service.wuliao.WLAssortmentManager;
import com.acec.wgt.service.wuliao.WLCatalogManager;

public class CatalogAction extends BaseAction {

	private static final long serialVersionUID = -2621721299115843546L;
	@Autowired
	private WLAssortmentManager assortmentManager;
	@Autowired
	private WLCatalogManager manager;

	private WLCatalogEO e;
	private Integer id;
	
	
	
	
	public String add(){
		viewList = assortmentManager.getAll();
		return "add";
	}
	
	public String edit(){
        e=manager.getEntity(id);
        viewList = assortmentManager.getAll();
        return "edit";
    }
	
	 public String lefttree(){
         viewList=assortmentManager.getTreeAll("catalog!list.action");
         return "lefttree";
     }
	 
	 //给出入库单选择物料目录用
	 public String choicelefttree(){
         viewList=assortmentManager.getTreeAll("catalog!list.action?choice=c");
         return "lefttree";
     }
	
	
	 public String list(){
		 viewList=manager.getAllForGradCode(getRequest().getParameter("gradCode"));
         if("c".equals(getRequest().getParameter("choice")))
        	 return "choicelist";
         else
        	 return "list";
     }
	 
	 
    public void save(){
        try{
        	if("tadd".equals(getRequest().getParameter("add"))){//新增的
        		if(manager.getById(Integer.valueOf(e.getId()))!=null)//有重复的编号
        			throw new RuntimeException("已存在重复的编号了");
        	}
        	manager.save(e);
        	write("{success:true,msg:'保存成功'}");
        }catch(Exception ex){
        	write("{success:false,msg:'保存失败。数据异常"+ex.getMessage()+"'}");
			logger.error("保存失败", ex);
        }
        return ;
    }
    
    public void del(){
        try{
        	manager.del(e);
        	write("{success:true,msg:'删除成功'}");
        }catch(Exception ex){
        	logger.error("删除失败", ex);
			write("{success:false,msg:'删除失败。"+ex.getMessage()+"'}");
        }
        return ;
    }
    
    
    
    
    
    
    
	public WLCatalogEO getE() {
		return e;
	}

	public void setE(WLCatalogEO e) {
		this.e = e;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
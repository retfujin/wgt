package com.acec.wgt.actions.wuliao;

import org.springframework.beans.factory.annotation.Autowired;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.wuliao.WLAssortmentEO;
import com.acec.wgt.service.wuliao.WLAssortmentManager;

public class AssortmentAction extends BaseAction {

	private static final long serialVersionUID = -2621721299115843546L;
	
	@Autowired
	private WLAssortmentManager manager;
	
	private WLAssortmentEO e;
	private Integer id;
	
	
	
	
	public String add(){
		return "add";
	}
	
	public String edit(){
        e=manager.getById(id);
        return "edit";
    }
	
	 public String lefttree(){
         viewList=manager.getTreeAll("assortment!list.action");
         return "lefttree";
     }
	
	
	 public String list(){
         viewList=manager.getAllForGradCode(getRequest().getParameter("gradCode"));
         return "list";
     }
	 
	 
    public void save(){
        try{
        	if(null==e.getGradCode()||"".equals(e.getGradCode())){//新增的
        		
        		if(manager.getById(Integer.valueOf(e.getId()))!=null)//有重复的编号
        			throw new RuntimeException("已存在重复的编号了");
        		
        		String _gradCode = manager.getSelfGradCode(getRequest().getParameter("upperGradCode"));
        		e.setGradCode(_gradCode);
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
    
    
    
    
    
	public WLAssortmentEO getE() {
		return e;
	}

	public void setE(WLAssortmentEO e) {
		this.e = e;
	}	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
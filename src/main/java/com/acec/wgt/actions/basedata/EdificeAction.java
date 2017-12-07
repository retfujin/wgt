package com.acec.wgt.actions.basedata;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.orm.Page;
import com.acec.wgt.models.baseData.EdificeEO;
import com.googlecode.jsonplugin.JSONException;
import com.googlecode.jsonplugin.JSONUtil;
import com.acec.wgt.models.ser.MenuTreeChecked;
import com.acec.core.utils.StringUtil;

public class EdificeAction extends
		com.acec.core.web.struts2.BaseFileUploadAction {

	private EdificeEO entity;

	@Autowired
	private com.acec.wgt.service.basedata.AreaManager areaManager;
	@Autowired
	private com.acec.wgt.service.basedata.EdificeManager edificeManager;

	private Page page = new Page(25);// 每页5条记录

	public String listquery(){
		viewList = areaManager.getAreaALL();
		return "listquery";
	}
	/**
	 * 楼栋住宅列表
	 * 
	 * @return
	 */
	public String list() {
		String areaId = getRequest().getParameter("areaId");
		String edificeId = getRequest().getParameter("edificeId") != null ? getRequest()
				.getParameter("edificeId") : "";
		String edificeType = getRequest().getParameter("edificeType") != null ? getRequest()
				.getParameter("edificeType") : "";
		StringBuilder sb = new StringBuilder();
		if (areaId != null && !areaId.equals(""))
			sb.append(" and area.id=").append(areaId);
		if (!edificeId.equals(""))
			sb.append(" and id like '%").append(edificeId).append("%'");
		if (!edificeType.equals(""))
			sb.append(" and edificeUse = '").append(edificeType).append("'");
		page = edificeManager.getListByPage(page, sb.toString());

		return "list";
	}

	public String add() {
		viewList = areaManager.getAreaALL();
		return "add";
	}

	// 编辑页面
	public String edit() {
		entity = edificeManager.get(entity.getId());
		return "edit";
	}

	// 保存区域
	public String save() {

		String id = getRequest().getParameter("id");
		if(null!=id && !"".equals(id)){
			if(id.contains("-")){
				forwardStr = "楼栋编号不正确，请重新填写";
				return "result";
			}
		}
		try {
			if (entity.getId() == null)
				entity.setId(entity.getArea().getId() + "-" + id);
			edificeManager.save(entity);			
			forwardStr = "保存成功";
		} catch (Exception ex) {
			forwardStr = "保存失败。数据异常" + ex.getMessage();
			logger.debug("保存失败", ex);
		}
		return "result";
	}

	// 删除楼栋
	public String del() {
		try {
			edificeManager.del(entity);
			forwardStr = "删除成功";
		} catch (Exception ex) {
			forwardStr = "删除失败，数据异常" + ex.getMessage();
			ex.printStackTrace();
		}
		return "result";
	}

	
	
	
	 // 发送短信时调用的树
    public void getSMSTreeMenu() throws IOException, JSONException {

		String pid = getRequest().getParameter("node");
		String checked = getRequest().getParameter("checked");
		String p_edificeId = getRequest().getParameter("node");
		if (StringUtil.isEmpty(pid) || "ROOT".equals(pid))
		    pid = "-1";
		try {
		    List<MenuTreeChecked> temp = edificeManager.getSMSTreeMenu(pid, checked, p_edificeId);
		    String jsonstr = JSONUtil.serialize(temp);
		    // log.debug(jsonstr);
		    write(jsonstr);
		} catch (Exception e) {
		    e.printStackTrace();
		}
    }
	
	
	
	/*
	 * 选择页面，树状
	 */
	public String choicetrees() {
		viewList = edificeManager.getEdificeTrees();
		return "choicetrees";
	}

	/*
	 * 选择页面，右侧房间列表
	 */
	public String choicehouse() {
		String edificeId = getRequest().getParameter("edificeId");
		viewList = edificeManager.findHouseForEdificeKit(edificeId);
		return "choicehouse";
	}
	
	public String choiceframe() {
		return "choiceframe";
	}
	
	// ajax
	public String getajaxedifice() {

		viewList = edificeManager.getList(Integer.parseInt(getRequest()
				.getParameter("areaId").toString()));
		return "getajaxedifice";

	}
	

	public Page getPage() {
		return page;
	}

	public EdificeEO getEntity() {
		return entity;
	}

	public void setEntity(EdificeEO entity) {
		this.entity = entity;
	}
}
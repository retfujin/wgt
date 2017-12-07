package com.acec.wgt.actions.basedata;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.baseData.OwnerDecorateEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.CellManager;
import com.acec.wgt.service.basedata.EdificeManager;
import com.acec.wgt.service.basedata.OwnerDecorateManager;

public class OwnerdecorateAction extends com.acec.core.web.struts2.BaseAction {

	private static final long serialVersionUID = -2001270286792277096L;

	@Autowired
	private OwnerDecorateManager ownerDecorateManager;
	@Autowired
	private AreaManager areaManager;
	@Autowired
	private EdificeManager edificeManager;
	@Autowired
	private CellManager cellManager;

	private OwnerDecorateEO entity2;// 装修记录表
	private String areaId;
	private String houseId;
	private String pageBar;
	private Page<HouseEO> page = new Page<HouseEO>(15);// 每页15条记录

	/**
	 * 新增装修记录
	 * 
	 * @return
	 */
	public String add() {
		viewList = areaManager.getAreaALL();// 得到所有管理处
		return "add";
	}

	// 得到所有的装修记录
	@SuppressWarnings("unchecked")
	public String list() {
		String areaId = getRequest().getParameter("areaId")!=null ? getRequest().getParameter("areaId") :"";
		String houseId = getRequest().getParameter("houseId")!=null ? getRequest().getParameter("houseId") :"";
		StringBuilder sb = new StringBuilder();
		if (!areaId.equals(""))
			sb.append(" and areaId=").append(areaId);
		if (!houseId.equals(""))
			sb.append(" and houseId='").append(houseId).append("' ");

		String pageNo = ServletActionContext.getRequest().getParameter("pageNo");
		if (pageNo == null)
			pageNo = "1";
		int no = Integer.parseInt(pageNo);
		PaginatorTag pt = ownerDecorateManager.getOwnerdecorateListByPage(no,15, sb.toString());
		pt.setUrl("ownerdecorate!list.action?areaId" + areaId + "&houseId="	+ houseId);
		pt.setShowTotal(true);
		pt.setShowAllPages(true);
		pt.setStrUnit("条");
		viewList = pt.getData();
		pageBar = pt.showPage();

		retList = areaManager.getAreaALL();// 得到所有管理处
		String export = getRequest().getParameter("exportSumbit");
		if ("导出".equals(export))
			return "listexport";
		else
			return "list";
	}

	// 编辑装修情况
	public String edit() {
		String id = getRequest().getParameter("id");
		entity2 = ownerDecorateManager.get(Integer.parseInt(id));
		viewList = areaManager.getAreaALL();// 得到所有管理处
		return "edit";

	}

	// 装修记录保存
	public void save() {
		String houseId = getRequest().getParameter("houseId");
		try {
			if (entity2.getId() == null && !"".equals(entity2)) {
				if (houseId != null
						&& !"".equals(houseId)) {
					Object[] obj = cellManager.getHouseAddress(houseId);
					
					entity2.setHouseId(houseId);
					entity2.setOwnerName(obj[2].toString());
					entity2.setHousedz(obj[0].toString());
					entity2.setDecoratetel(obj[3].toString());
				}
			}
			ownerDecorateManager.decorateSave(entity2);
			write("{success:true,msg:'保存成功'}");
		} catch (Exception ex) {
			ex.printStackTrace();
			write("{success:false,msg:'保存失败。数据异常"+ex.getMessage()+"'}");
		}
	}

	// 装修记录删除
	public void del() {
		try {
			String id = getRequest().getParameter("id");
			ownerDecorateManager.del(Integer.parseInt(id));
			write("{success:true,msg:'删除成功'}");
		} catch (Exception ex) {
			ex.printStackTrace();
			write("{success:false,msg:'删除失败。"+ex.getMessage()+"'}");
		}

	}

	/*
	 * 根据小区id取楼栋
	 */
	public String getEdifice() {
		String areaId = getRequest().getParameter("areaId");
		viewList = edificeManager.getAllEdifice(Integer.parseInt(areaId));
		return "ajaxedifice";
	}

	/*
	 * 根据小区id取楼栋
	 */
	public String getHouseInfo() {
		String edificeId = getRequest().getParameter("edificeId");
		viewList = edificeManager.findHouseForEdifice(edificeId);
		return "ajaxhouse";
	}

	
	
	
	
	public List getRetList() {
		return retList;
	}
	public void setRetList(List retList) {
		this.retList = retList;
	}
	public Page<HouseEO> getPage() {
		return page;
	}
	public OwnerDecorateEO getEntity2() {
		return entity2;
	}
	public void setEntity2(OwnerDecorateEO entity2) {
		this.entity2 = entity2;
	}
	public String getPageBar() {
		return pageBar;
	}
	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
}
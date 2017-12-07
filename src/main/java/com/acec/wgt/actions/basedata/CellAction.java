package com.acec.wgt.actions.basedata;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.EdificeEO;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.CellManager;
import com.acec.wgt.service.basedata.EdificeManager;
import com.acec.wgt.service.chargemanger.HouseChargeManager;

public class CellAction extends com.acec.core.web.struts2.BaseFileUploadAction {

	private static final long serialVersionUID = 1992078763723107932L;

	@Autowired
	private CellManager cellManager;
	@Autowired
	private AreaManager areaManager;
	@Autowired
	private EdificeManager edificeManager;
	@Autowired
	private HouseChargeManager houseChargeManager;

	private HouseEO entity;

	private Page page = new Page(15);// 每页15条记录

	private String pageBar;
	private String areaId;
	private String houseType;
	private String houseId;
	private String layerType;

	/**
	 * 房间列表
	 * 
	 * @return
	 */
	public String list() {
		String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
		String areaId = getRequest().getParameter("areaId") != null ? getRequest().getParameter("areaId") : "";
		String houseId = getRequest().getParameter("houseId") != null ? getRequest().getParameter("houseId") : "";
		String houseType = getRequest().getParameter("houseType") != null ? getRequest().getParameter("houseType") : "";
		String edificeId = getRequest().getParameter("edificeId") != null ? getRequest().getParameter("edificeId") : "";

		StringBuilder sb = new StringBuilder();
		if (!houseId.equals(""))
			sb.append(" and id like '%").append(houseId).append("%' ");
		if (!areaId.equals(""))
			sb.append(" and areaId = ").append(areaId);
		if (!houseType.equals(""))
			sb.append(" and houseType = '").append(houseType).append("'");
		if (!edificeId.equals(""))
			sb.append(" and edificeId = '").append(edificeId).append("'");
		page = cellManager.getListByPage(page, sb.toString());
		
		StringBuffer param = new StringBuffer();
		param.append(" where areaId in (").append(areaIds).append(") ");
		param.append(sb.toString());
		String building = cellManager.getSumBuilding(param.toString());
		getRequest().setAttribute("building", building);
		retList = areaManager.getAreaALL();// 得到所有管理处

		String pageNo = ServletActionContext.getRequest()
				.getParameter("pageNo");
		if (pageNo == null)
			pageNo = "1";
		int no = Integer.parseInt(pageNo);
		PaginatorTag pt = cellManager.getHouseListByPage(no, 15, sb.toString());
		pt.setUrl("cell!list.action" + getParamString(""));
		pt.setShowTotal(true);
		pt.setShowAllPages(true);
		pt.setStrUnit("条");
		viewList = pt.getData();
		pageBar = pt.showPage();

		return "list";
	}

	// 批量导入、下载页面
	public String init() {
		viewList = areaManager.getAreaALL();
		return "init";
	}

	// 下载excel文件
	public String downExcel() {
		String areaId = getRequest().getParameter("areaId");
		logger.debug("下载导入房间模板，小区id" + areaId);
		try {
			cellManager.writeExc(areaId);
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			forwardStr = "下载excel失败。原因：" + ex.getMessage();
			return "result";
		}
	}

	// 上传房间资料
	public void uploadExcel() {

		if (theFile != null) {
			logger.debug(contenttype);
			if (!contenttype.equals("application/vnd.ms-excel")&& !contenttype.equals("application/octet-stream")) {
				write("{success:false,msg:'上传的文件类型不正确。错误类型为：" + contenttype+"'}");
				return;
			}

			try {
				cellManager.saveForExc(theFile);
				write("{success:true,msg:'上传excel成功。'}");
			} catch (Exception ex) {
				logger.error("导入房间excel失败", ex);
				write("{success:false,msg:'导入excel失败。" + ex.getMessage() + "'}");
			}
		} else {
			write("{success:false,msg:'导入excel失败。没有获取到文件'}");
			logger.error("导入房间excel失败,没有获取到文件");
		}
		return ;
	}

	public void save() {

		String edificeId = getRequest().getParameter("edificeId") != null ? getRequest()
				.getParameter("edificeId") : "1";// 楼栋id
		EdificeEO vo = edificeManager.get(edificeId);

		String house_id = edificeId + "-" + entity.getHouseName();// 房间id
		try {
			// 得到该小区的公摊比例
			Float poolRatio = cellManager.getPoolRatio(entity.getAreaId());
			entity.setPoolArea(poolRatio * entity.getBuildnum()); // 计算公摊面积
			// =公摊比例*建筑面积
			if (entity.getEdificeId() == null)
				entity.setEdificeId(edificeId);
			else
				house_id = entity.getEdificeId() + "-" + entity.getHouseName();
			if (!entity.getIsSale().equals("入伙"))
				entity.setNoneOwner();
			cellManager.saveHouse(entity, house_id);
			if (edificeId.equals("")) {
				Float sumBuildNum = Float.parseFloat(cellManager
						.getSumBuildNum(entity.getEdificeId()));
				cellManager.updBuildNum(sumBuildNum, entity.getEdificeId());
			} else {
				Float sumBuildNum = Float.parseFloat(cellManager
						.getSumBuildNum(edificeId));
				cellManager.updBuildNum(sumBuildNum, edificeId);
			}

			write("{success:true,msg:'保存成功'}");
		} catch (Exception ex) {
			logger.error("保存房间失败", ex);
			write("{success:false,msg:'保存失败。" + ex.getMessage() + "'}");
		}

		return;
	}

	// 新增房间页面
	public String add() {
		viewList = areaManager.getAreaALL();
		// viewList1 = cellManager.getHabitationTypeALL();//入住类型列表
		return "add";
	}

	// 编辑房间
	public String edit() {

		String id = getRequest().getParameter("id");
		if (id != null) {
			entity = cellManager.getHouse(id);
		}
		return "edit";
	}

	public void del() {
		String id = getRequest().getParameter("id");
		List list = houseChargeManager.getChargeByHouseId(id);
		if (!list.isEmpty()) {
			write("{success:false,msg:'删除失败。原因：已经存在该房间的相关费用，不能删除!'}");
			return ;
		}
		try {
			if (id != null) {
				cellManager.delhouse(id);
			}
			write("{success:true,msg:'删除成功'}");
		} catch (Exception ex) {
			logger.error("删除房间失败", ex);
			write("{success:false,msg:'删除房间失败。" + ex.getMessage() + "'}");
		}
		
		return ;

	}
	
	/**
	 * 入住率统计
	 * 
	 * @return
	 */
	public String ratelist() {
		String areaId = getRequest().getParameter("areaId");
		String houseType = getRequest().getParameter("houseType");
		String layerType = getRequest().getParameter("layerType");
		retList = areaManager.getAreaALL();
		viewList = cellManager.getRateList(areaId, houseType, layerType);

		return "ratelist";
	}
	

	@SuppressWarnings("unchecked")
	public String ajaxhouseaddress() {
		String houseId = getRequest().getParameter("houseId");

		Object[] houseProperty = cellManager.getHouseAddress(houseId);
		Map map = new HashMap();
		if (houseProperty == null)
			map.put("houseAddress", "没有找到业主");
		else {
			map.put("houseAddress", houseProperty[0]);
			map.put("buildnum", houseProperty[1]);
			map.put("ownerName", houseProperty[2]);
			map.put("mobTel", houseProperty[3]);
		}
		Struts2Utils.renderJson(map);
		// Struts2Utils.renderText(houseAddress);
		return null;
	}

	public List getRetList() {
		return retList;
	}

	public void setRetList(List retList) {
		this.retList = retList;
	}

	public Page getPage() {
		return page;
	}
	public HouseEO getEntity() {
		return entity;
	}
	public void setEntity(HouseEO entity) {
		this.entity = entity;
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
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getHouseId() {
		return houseId;
	}
	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}
	public String getLayerType() {
		return layerType;
	}
	public void setLayerType(String layerType) {
		this.layerType = layerType;
	}	
}
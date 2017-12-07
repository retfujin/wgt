package com.acec.wgt.actions.basedata;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.baseData.OwnerMoveEO;
import com.acec.wgt.service.basedata.AreaManager;
import com.acec.wgt.service.basedata.OwnerMoveManager;

/**
 * Action
 * 
 * @author fu
 * @since
 */
public class OwnermoveAction extends BaseAction {

	private static final long serialVersionUID = -5231338458059127144L;

	@Autowired
	private OwnerMoveManager manager;
	@Autowired
	private AreaManager areaManager;

	private OwnerMoveEO entity;
	private Page page = new Page(15);
	private List retList;
	private String pageBar;
	private String areaId;
	private String ownerName;
	private String carryDate;

	/**
	 * 新增住户搬出搬入登记
	 * 
	 * @return
	 */
	public String add() {
		retList = areaManager.getAreaALL();// 得到所有管理处
		return "add";
	}

	/**
	 * 编缉住户搬出搬入登记
	 * 
	 * @return
	 */
	public String edit() {
		String id = getRequest().getParameter("id");
		retList = areaManager.getAreaALL();// 得到所有管理处
		entity = manager.get(Integer.parseInt(id));
		return "add";
	}

	/**
	 * 住户搬出搬入登记列表
	 * 
	 * @return
	 */
	public String list() {
		String areaId = getRequest().getParameter("areaId")!=null ? getRequest().getParameter("areaId") :"";
		String ownerName = getRequest().getParameter("ownerName")!=null ? getRequest().getParameter("ownerName"):"";
		String carryDate = getRequest().getParameter("carryDate")!=null ? getRequest().getParameter("carryDate"):"";
		StringBuilder sb = new StringBuilder();
		if (!areaId.equals(""))
			sb.append(" and areaId=").append(areaId);
		if (!ownerName.equals(""))
			sb.append(" and ownerName like '%").append(ownerName).append("%' ");
		if (!carryDate.equals(""))
			sb.append(" and carryDate='").append(carryDate).append("' ");

		String pageNo = ServletActionContext.getRequest()
				.getParameter("pageNo");
		if (pageNo == null)
			pageNo = "1";
		int no = Integer.parseInt(pageNo);
		PaginatorTag pt = manager.getOwnermoveListByPage(no, 15, sb.toString());
		pt.setUrl("ownermove!list.action?areaId=" + areaId + "&carryDate="+ carryDate + "&ownerName=" + ownerName);
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

	/**
	 * 保存住户搬出搬入登记
	 * 
	 * @return
	 */
	public void save() {
		try {
			manager.save(entity);
			write("{success:true,msg:'保存成功'}");
		} catch (Exception ex) {
			ex.printStackTrace();
			write("{success:false,msg:'保存失败。数据异常"+ex.getMessage()+"'}");
		}
	}

	/**
	 * 删除住户搬出搬入登记记录
	 * 
	 * @return
	 */
	public void del() {
		String id = getRequest().getParameter("id");
		try {
			manager.del(Integer.parseInt(id));
			write("{success:true,msg:'删除成功'}");
		} catch (Exception ex) {
			ex.printStackTrace();
			write("{success:false,msg:'删除失败。"+ex.getMessage()+"'}");
		}
		return ;
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

	public OwnerMoveEO getEntity() {
		return entity;
	}
	public void setEntity(OwnerMoveEO entity) {
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

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getCarryDate() {
		return carryDate;
	}

	public void setCarryDate(String carryDate) {
		this.carryDate = carryDate;
	}
	
}
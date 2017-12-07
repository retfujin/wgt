package com.acec.wgt.actions.basedata;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateWebUtils;
import com.acec.core.utils.CharTools;
import com.acec.wgt.models.baseData.CarportEO;
import com.acec.wgt.service.basedata.CarportManager;

public class GarageAction extends
		com.acec.core.web.struts2.BaseFileUploadAction {
	@Autowired
	private CarportManager carportManager;
	@Autowired
	private com.acec.wgt.service.basedata.AreaManager areaManager;

	private Page page = new Page(15);// 每页15条记录
	private CarportEO entity;
	private List retList;
	private List retList_1 ;//用于取收费类型的其它  页面查询需要的列表
	private String pageBar;
	
	private String areaId;
	private String type;
	private String local;
	private String carCode;
	private String state;
	/*
	 * 租售车位列表机动车
	 */
	public String list() {
		String pageNo = ServletActionContext.getRequest().getParameter("pageNo");
		String areaId = getRequest().getParameter("areaId")!=null ? getRequest().getParameter("areaId") :"";
		String type = getRequest().getParameter("type")!=null ? getRequest().getParameter("type") :"";
		String local = getRequest().getParameter("local")!=null ? getRequest().getParameter("local") :"";
		String carCode = getRequest().getParameter("carCode")!=null ? getRequest().getParameter("carCode") :"";
		String state = getRequest().getParameter("state")!=null ? getRequest().getParameter("state") :"";
		String hidd = getRequest().getParameter("hidd");
		String where = "";
		if(!"".equals(areaId))
			where += " and area.id = "+areaId;
		if(!"".equals(type))
			where += " and type = "+type;
		if(!"".equals(local))
			where += " and local like '%"+local+"%'";
		if(!"".equals(carCode))
			where += " and carCode like '%"+carCode+"%' ";
		if(!"".equals(state))
			where += " and state = '"+state+"' ";
		
		String page = "";
		if(!"".equals(areaId))
			page = "?areaId="+areaId;
		
		if(!"".equals(type))
			page = "&type="+type;
		if(!"".equals(local))
			page = "?local="+local;
		if(!"".equals(carCode))
			page = "?carCode="+carCode;
		if(!"".equals(state))
			page = "?state="+state;
		
		if (pageNo == null)
			pageNo = "1";
		
		int no = Integer.parseInt(pageNo);
		
//		if(null == hidd)
//		{
//			CharTools charTools = new CharTools();
//			try {
//				state = new String(state.getBytes("ISO8859-1"),"UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//			state = charTools.Utf8URLdecode(state);
//		}
		PaginatorTag pt = carportManager.getCarportByPage(no, 15, where);
		retList = areaManager.getAreaALL();// 得到所有管理
		pt.setUrl("garage!list.action" + page);
		pt.setShowTotal(true);
		pt.setShowAllPages(true);
		pt.setStrUnit("条");
		viewList = pt.getData();
		pageBar = pt.showPage();
		return "list";
	}

	/**
	 * 车位统计
	 * @return
	 */
	public String statistics(){
		Integer areaId = getRequest().getParameter("areaId") != null && !getRequest().getParameter("areaId").equals("")? Integer.parseInt(getRequest().getParameter("areaId")) : 0;
		String local=getRequest().getParameter("local")!=null ? getRequest().getParameter("local") :"";
		String hidd=getRequest().getParameter("hidd");
		if(null == hidd)
		{
			CharTools charTools = new CharTools();
			try {
				local = new String(local.getBytes("ISO8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			local = charTools.Utf8URLdecode(local);
		}
		viewList=areaManager.getAreaALL();
		retList_1=carportManager.getCarState();
		retList=carportManager.reportCar(areaId,local);
		
		return "statistics";
	}
	
	// 批量导入、下载页面
	public String initcar() {
		viewList = areaManager.getAreaALL();
		return "initcar";
	}

	// 下载excel文件
	public String downExcel() {
		String areaId = getRequest().getParameter("areaId");
		logger.debug("下载导入车位模板，小区id" + areaId);
		try {
			carportManager.writeExc(areaId);
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			forwardStr = "下载excel失败。原因：" + ex.getMessage();
			return "result";
		}

	}

	public void uploadExcel() {

		if (theFile != null) {
			logger.debug(contenttype);
			if (!contenttype.equals("application/vnd.ms-excel")	&& !contenttype.equals("application/octet-stream")) {
				write("{success:false,msg:'上传的文件类型不正确。错误类型为：" + contenttype+"'}");
				return;
			}

			try {
				carportManager.saveForExc(theFile);
				write("{success:true,msg:'上传excel成功。'}");
			} catch (Exception ex) {
				forwardStr = "导入excel失败。原因：" + ex.getMessage();
				write("{success:false,msg:'导入excel失败。" + ex.getMessage() + "'}");
			}
		} else {
			write("{success:false,msg:'导入excel失败。没有获取到文件'}");
			logger.error("导入车位excel失败,没有获取到文件");
		}

		return ;
	}

	/**
	 * 批量设置车位期初收费
	 * @return
	 */
	public String initcarport(){		
		return "initcarport";
	}
	
	// 下载excel文件
	public String downCarportExcel() {
		try {
			carportManager.writeCarportExc();
			return null;
		} catch (Exception ex) {
			ex.printStackTrace();
			forwardStr = "下载excel失败。原因：" + ex.getMessage();
			return "result";
		}

	}

	public void uploadCarportExcel() {
		if (theFile != null) {
			logger.debug(contenttype);
			if (!contenttype.equals("application/vnd.ms-excel")	&& !contenttype.equals("application/octet-stream")) {
				write("{success:false,msg:'上传的文件类型不正确。错误类型为：" + contenttype+"'}");
				return;
			}
			try {
				carportManager.saveCarportExc(theFile);
				carportManager.updateCarport();
				write("{success:true,msg:'上传excel成功。'}");
			} catch (Exception ex) {
				forwardStr = "导入excel失败。原因：" + ex.getMessage();
				write("{success:false,msg:'导入excel失败。" + ex.getMessage() + "'}");
			}
		} else {
			write("{success:false,msg:'导入批量设置车位期初收费excel失败,没有获取到文件'}");
			logger.error("导入批量设置车位期初收费excel失败,没有获取到文件");
		}
		return ;
	}
	
	
	
	
	
	public String add() {
		viewList = areaManager.getAreaALL();
		return "add";
	}

	public String edit() {
		String id = getRequest().getParameter("id");
		viewList = areaManager.getAreaALL();
		retList_1=carportManager.getCarState();
		entity = carportManager.getCarportById(Integer.parseInt(id));

		return "edit";
	}

	public void save() {
		try {
			
			float mianji= getRequestValue("mianji",0);
			entity.setMianji(mianji);
			
			if (null != entity.getType2() && entity.getType2().equals("其它车位"))
				entity.setIsFill("");

			carportManager.save(entity);
			write("{success:true,msg:'保存成功'}");
		} catch (Exception ex) {
			logger.error("保存车位基础资料失败", ex);
			write("{success:false,msg:'保存失败。数据异常"+ex.getMessage()+"'}");
		}
		return;
	}

	public void del() {
		try {
			carportManager.del(entity.getId());
			write("{success:true,msg:'删除成功'}");

		} catch (Exception ex) {
			logger.error("删除车位失败", ex);
			write("{success:false,msg:'删除失败。"+ex.getMessage()+"'}");
		}
		return;
	}

	public String getPageBar() {
		return pageBar;
	}
	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}
	public List getRetList() {
		return retList;
	}
	public void setRetList(List retList) {
		this.retList = retList;
	}
	public CarportEO getEntity() {
		return entity;
	}
	public void setEntity(CarportEO entity) {
		this.entity = entity;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List getRetList_1() {
		return retList_1;
	}
	public void setRetList_1(List retList_1) {
		this.retList_1 = retList_1;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
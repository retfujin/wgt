package com.acec.wgt.actions.ser;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.service.basedata.CellManager; 
import com.acec.wgt.service.sms.SMSSendManager;
import com.acec.commons.util.PaginatorTag;
import com.acec.commons.util.ReadTxtFile;
import com.acec.wgt.service.sms.SMSServiceImpl;
import com.acec.wgt.service.sms.SMSServiceImplService;
import com.acec.wgt.models.ser.SMSSend;
import com.acec.core.utils.SMSTools;
import com.acec.core.utils.StringUtil;

/**
 * 公告发布
 * @author 
 *
 */
public class BulletinAction extends com.bestnet.base.action.BaseFileUploadAction{

	private static int CONTENTNUM = 67;
	@Autowired
	private CellManager cellManager;
	@Autowired
	private SMSSendManager smsSendManager;
	private String pageBar;
	String pageNo="";
		
	private SMSSend entity;
	

	private File theFile;
	private String fileName;
	private String contenttype;
	private List retList;
	
	// 保存(单条发送)	
	public void addbull(){
		try {
		    String checkWord = SMSTools.checkWord(entity.getContent());
		    if (!checkWord.equals("")) {
		    	write("{success:false,msg:'发送内容中包含被屏蔽词:" + checkWord + "'}");
		    } else {
		    	String destPhoneList = getRequest().getParameter("destPhoneList");
				String userName = (String)getSession().get("userName");
				SMSServiceImpl impl = new SMSServiceImplService().getSMSServiceImplPort();
				
				//subid, subpassword,phone,content
				String msgid = impl.sendSMS(SMSTools.getSubId(), SMSTools.getSubPass(),destPhoneList, entity.getContent()+SMSTools.getSubName());						
				String param_msgid[] = msgid.split("\\|");
				SMSSend sms = new SMSSend();
				sms.setSubid(SMSTools.getSubId());
				sms.setSendName(userName);
				sms.setContent(entity.getContent()+SMSTools.getSubName());
				sms.setMobilePhones(destPhoneList);
				sms.setSendTime(new Timestamp(System.currentTimeMillis()));
				sms.setMsgid(param_msgid[1]);
				sms.setState(param_msgid[0]);
				int num = sms.getContent().length() % CONTENTNUM ==0 ? sms.getContent().length() / CONTENTNUM : ((sms.getContent().length() / CONTENTNUM)+1);
				sms.setContentNum(num);
				smsSendManager.save(sms);
				
				if(param_msgid[0].equals("00"))
					write("{success:true,msg:'信息已提交，请稍后进入信息发送列表查看！'}");
				else{
					String msg = "";
					if(param_msgid[0].equals("01"))
						msg = "用户鉴权失败";
					else if (param_msgid[0].equals("02"))
						msg = "内容为空";
					else if(param_msgid[0].equals("10"))
						msg = "余额不足";
					else
						msg = "其他错误";
					write("{success:false,msg:'"+msg+"'}");
				}
		    }
		} catch (Exception e) {
			try {
				write("{success:false,msg:'信息发送失败" + StringUtil.praseExceptionMessage(e) + "'}");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	 
 
	 public void batchSave() {
		try {
		    String content = entity.getContent()+SMSTools.getSubName();
		    if (StringUtil.isEmpty(content)) {
		    	write("{success:false,msg:'发送内容不能为空！'}");
		    } else {
				String checkWord = SMSTools.checkWord(content);
				if (!checkWord.equals("")) {
				    write("{success:false,msg:'发送内容中包含被屏蔽词:" + checkWord + "'}");
				} else {
				   
		
				    List l = new ArrayList();
				    if (theFile != null) {
						if (!contenttype.equals("text/plain")){
						    write("{success:false,msg:'文件类型只能为文本类型的文件！'}");
							return;
						}else {
						    ReadTxtFile r = new ReadTxtFile();
						    l = r.readTxtFile(theFile);
						}
				    }
				    if (l.size() > 0) {
						SMSServiceImpl impl = new SMSServiceImplService().getSMSServiceImplPort();
						int restNum = impl.getRestNum(SMSTools.getSubId());
			
						if (restNum < l.size()) {
						    write("{success:false,msg:'当前剩余短信条数为:" + restNum + "条，请充值！'}");
						    return;
						} else {
							String destPhoneList = "";
							for (int i = 0; i < l.size(); i++)
								destPhoneList = destPhoneList + l.get(i).toString()+",";
							//subid, subpassword,phone,content
							String msgid = impl.sendSMS(SMSTools.getSubId(), SMSTools.getSubPass(),destPhoneList, content);	
							String param_msgid[] = msgid.split("\\|");
							String userName = (String)getSession().get("userName");
							SMSSend sms = new SMSSend();
							sms.setSubid(SMSTools.getSubId());
							sms.setSendName(userName);
							sms.setContent(content);
							sms.setMobilePhones(destPhoneList);
							sms.setSendTime(new Timestamp(System.currentTimeMillis()));
							sms.setMsgid(param_msgid[1]);
							sms.setState(param_msgid[0]);
							int num = sms.getContent().length() % CONTENTNUM ==0 ? sms.getContent().length() / CONTENTNUM : ((sms.getContent().length() / CONTENTNUM)+1);
							sms.setContentNum(num);
							smsSendManager.save(sms);
							
							if(param_msgid[0].equals("00"))
								write("{success:true,msg:'信息已提交，请稍后进入信息发送列表查看！'}");
							else{
								String msg = "";
								if(param_msgid[0].equals("01"))
									msg = "用户鉴权失败";
								else if (param_msgid[0].equals("02"))
									msg = "内容为空";
								else if(param_msgid[0].equals("10"))
									msg = "余额不足";
								else
									msg = "其他错误";
								write("{success:false,msg:'"+msg+"'}");
							}
						}
				    }
				}
		    }
		} catch (Exception e) {
			try {
				write("{success:false,msg:'信息发送失败" + StringUtil.praseExceptionMessage(e) + "'}");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
    }

	/**
	 * 发送信息列表查询
	 * @return
	 */
	public String list(){
		String mobile=getRequest().getParameter("mobile") !=null ? getRequest().getParameter("mobile"):"";
		String beginDate = getRequest().getParameter("beginDate") !=null ? getRequest().getParameter("beginDate"):"";
		String endDate = getRequest().getParameter("endDate") !=null ? getRequest().getParameter("endDate"):"";
		StringBuilder sb=new StringBuilder();
		if(!mobile.equals(""))
			sb.append(" and mobilePhones like '%").append(mobile).append("%'");
		if(!"".equals(beginDate))
			sb.append(" and date_format(sendTime,'%Y-%m-%d')>='").append(beginDate).append("'");
		if(!"".equals(endDate))
			sb.append(" and date_format(sendTime,'%Y-%m-%d')<='").append(endDate).append("'");
		String pageNo = ServletActionContext.getRequest().getParameter("pageNo");
		if(pageNo==null)
			pageNo="1";
		int no = Integer.parseInt(pageNo);
		PaginatorTag pt = smsSendManager.getListByPage(no, 20, sb.toString());
		pt.setUrl("bulletin!list.action");
		pt.setShowTotal(true);
		pt.setShowAllPages(true);
		pt.setStrUnit("条");
		viewList = pt.getData();
		pageBar = pt.showPage();
		return "list";
	}
	
	
	/**
	 * 发送信息列表查询
	 * @return
	 */
	public String detaillist(){
		
		String msgid = getRequest().getParameter("msgid") !=null ? getRequest().getParameter("msgid"):"";
		
		SMSServiceImpl impl = new SMSServiceImplService().getSMSServiceImplPort();
		List<String> list = impl.recvMTState(SMSTools.getSubId(), SMSTools.getSubPass(),msgid,"");	
		retList = new ArrayList();
		if(!list.isEmpty())
			for(String s : list){
				String [] param = s.split("\\|");
				retList.add(param);
			}		
		return "detaillist";
	}
	
	
	
	public String addhouse(){
		return "addhouse";
	}

	
	
    public void saveHouse() {
		try {
		    String checkWord = SMSTools.checkWord(entity.getContent());
		    if (!checkWord.equals("")) {
		    	write("{success:false,msg:'发送内容中包含被屏蔽词:" + checkWord + "'}");
			} else {
				 
		
				String destHouseList = getRequest().getParameter("destParentList");
				String destParam = "";//发送的房间编号
				if (destHouseList != null) {
				    String[] houseStrs = destHouseList.split(",");
				    for (int i = 0; i < houseStrs.length; i++) {
					    String[] strs = houseStrs[i].split("-");
						if (strs.length == 3) {// 房间编号		    
						    destParam=destParam +"'"+houseStrs[i]+"',";
						}
				    }
					List<HouseEO> ls = cellManager.getList(destParam.substring(0,destParam.length()-1));
					String userName = (String)getSession().get("userName");
					
					String destPhoneList = "";
					for (int i = 0; i < ls.size(); i++) {
					    if (StringUtil.isMobileNO(ls.get(i).getMobTel()))
					    	destPhoneList = destPhoneList +ls.get(i).getMobTel()+",";
				    }
					SMSServiceImpl impl = new SMSServiceImplService().getSMSServiceImplPort();
					String msgid = impl.sendSMS(SMSTools.getSubId(), SMSTools.getSubPass(),destPhoneList, entity.getContent()+SMSTools.getSubName());	
					String param_msgid[] = msgid.split("\\|");
					SMSSend sms = new SMSSend();
					sms.setSubid(SMSTools.getSubId());
					sms.setSendName(userName);
					sms.setContent(entity.getContent()+SMSTools.getSubName());
					sms.setMobilePhones(destPhoneList);
					sms.setSendTime(new Timestamp(System.currentTimeMillis()));
					sms.setMsgid(param_msgid[1]);
					sms.setState(param_msgid[0]);
					int num = sms.getContent().length() % CONTENTNUM ==0 ? sms.getContent().length() / CONTENTNUM : ((sms.getContent().length() / CONTENTNUM)+1);
					sms.setContentNum(num);
					smsSendManager.save(sms);
					
					if(param_msgid[0].equals("00"))
						write("{success:true,msg:'信息已提交，请稍后进入信息发送列表查看！'}");
					else{
						String msg = "";
						if(param_msgid[0].equals("01"))
							msg = "用户鉴权失败";
						else if (param_msgid[0].equals("02"))
							msg = "内容为空";
						else if(param_msgid[0].equals("10"))
							msg = "余额不足";
						else
							msg = "其他错误";
						write("{success:false,msg:'"+msg+"'}");
					}
				} else {
				    write("{success:false,msg:'发送的地址为空'}");
				}
			}
		} catch (Exception e) {
		    log.error("保存SMSSend", e);
		    try {
			write("{success:false,msg:'" + StringUtil.praseExceptionMessage(e) + "'}");
		    } catch (IOException e1) {
			e1.printStackTrace();
		    }
		}
    }

    
	public String getPageBar() {
		return pageBar;
	}
	public void setPageBar(String pageBar) {
		this.pageBar = pageBar;
	}
	public SMSSend getEntity() {
		return entity;
	}
	public void setEntity(SMSSend entity) {
		this.entity = entity;
	}
	public File getTheFile() {
		return theFile;
	}
	public void setTheFile(File theFile) {
		this.theFile = theFile;
	}
	public String getTheFileFileName() {
		return fileName;
	}
	public void setTheFileFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTheFileContentType() {
		return contenttype;
	}
	public void setTheFileContentType(String contenttype) {
		this.contenttype = contenttype;
	}
	public String getPageNo() {
		return pageNo;
	}
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
	public List getRetList() {
		return retList;
	}
	public void setRetList(List retList) {
		this.retList = retList;
	}	
}
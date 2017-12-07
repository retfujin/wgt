package com.acec.wgt.actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.utils.GetMonth;
import com.acec.core.web.struts2.BaseAction;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.annotations.Token;
import com.acec.wgt.models.administration.EmployeeEO;
import com.acec.wgt.models.sys.SysUserEO;
import com.acec.wgt.service.administration.DepartureManager;
import com.acec.wgt.service.administration.EmployedManager;
import com.acec.wgt.service.administration.EmployeeManager;
import com.acec.wgt.service.administration.MobilizeManager;
import com.acec.wgt.service.administration.PositiveManager;
import com.acec.wgt.service.administration.RecruitmentManager;
import com.acec.wgt.service.administration.VacateManager;
import com.acec.wgt.service.contract.AssessmentManager;
import com.acec.wgt.service.contract.DemandManager;
import com.acec.wgt.service.contract.OvertimeManager;
import com.acec.wgt.service.contract.TerminationManager;
import com.acec.wgt.service.office.LeaveManager;
import com.acec.wgt.service.sys.AdminUserManager;
import com.acec.wgt.service.wuliao.ProcurementManager;
import com.opensymphony.xwork2.ActionContext;

@Results({@Result(name="default", location="/defaulta.jsp"),
	@Result(name="trees", location="/trees.jsp"),
	@Result(name="login", location="/login.jsp"),
	@Result(name="notice",location="/main.jsp"),
	@Result(name="tips",location="/tips.jsp")
	})
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private SysUserEO user =null;
	private static String diskId =null;

	
	@Autowired
	private AdminUserManager adminUserManager;
	
	@Autowired
	private EmployeeManager employeeManager;
	@Autowired
	private EmployedManager employedManager;//员工录用
	@Autowired
	private PositiveManager positiveManager;//员工转正
	@Autowired
	private DepartureManager departureManager;//员工离职
	@Autowired
	private MobilizeManager mobilizeManager;//内部岗位调动
	@Autowired
	private RecruitmentManager recruitmentManager;//招聘
	@Autowired
	private VacateManager vacateManager;//请假
	@Autowired
	private DemandManager demandManager;//培训需求申请
	@Autowired
	private AssessmentManager AssessmentManager;//劳动合同续签表
	@Autowired
	private TerminationManager terminationManager;//终止劳动合同
	@Autowired
	private OvertimeManager overtimeManager;//加班
	@Autowired
	private ProcurementManager procurementManager;//采购管理
	private List viewList1;
	
	@SuppressWarnings("unchecked")
	@Token
	public String loginCheck(){
		//首先检查硬盘序列号，不允许在其他硬盘安装
		if(checkDisk()==false){
//			forwardStr="不能在其他机器上使用";
			forwardStr="试用版已到期";
		}else{
//			String userName ="ffadmin";
//			String password ="ffadmin";
			String userName = getRequest().getParameter("userName");
			String password = getRequest().getParameter("password");
			String validateCode = getRequest().getParameter("validateCode");
//			 验证随即码是是否正确
			if (!validateCode.equals(ActionContext.getContext().getSession().get("rand"))){
				forwardStr="验证码不正确，请重新确认输入!";
				addFieldError("user.userName", forwardStr);
				return "login";
			}
			ActionContext.getContext().getSession().remove("rand");
 
			SysUserEO tuser = adminUserManager.checkUser(userName,password);
 
			//验证通过
			if(tuser!=null){
				Map map = ActionContext.getContext().getSession();
				map.put("userId", tuser.getId());//登陆用户名
				map.put("userName", tuser.getUserName());//登陆用户名
				map.put("companyId", tuser.getCompanyId());//物业公司编号
				map.put("companyName", tuser.getCompanyName());//物业公司名称
				map.put("adminType", tuser.getAdminType());//是否是管理员 0：是；1：否
				
				map.put("user", tuser);
				
				//取登录用户的员工信息
//				List<EmployeeEO> employeels = employeeManager.getEmployees(" and userId="+tuser.getId());
//				if(!employeels.isEmpty()){
//					map.put("employeeId", employeels.get(0).getId());
//					map.put("employeeName", employeels.get(0).getName());
//				}
				
				
				if(tuser.getAdminType().equals("0")){//是管理员，权限是物业公司所有小区
					map.put("areaIds",adminUserManager.findAllAreaId((String)map.get("companyId")));
				}else
					map.put("areaIds", tuser.getManagerarea());//所管理的小区
				if(null!=tuser.getRole())
					map.put("roleId",tuser.getRole().getId());//角色id(系统管理员没有)
				
				return "default";
			}
			forwardStr="用户名或密码不正确";
		}
		
		addFieldError("user.userName", forwardStr);
		return "login";
	}
	
	
	//首先检查硬盘序列号，不允许在其他硬盘安装
	private boolean checkDisk() {
		return true;
//		String endTime="2014-09-21";
//		String beginTime="";
//		try{
////			String moUrl="http://localhost:8080/limittime.jsp";
//			String moUrl="http://60.166.26.46:8081/cjcw/limittime.jsp"; 
//			URL url = new URL(moUrl);
//			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();  
//			
//			httpConn.setFollowRedirects(false);
//		
//			httpConn.setInstanceFollowRedirects(false);
//			httpConn.setDoOutput(true);
//			httpConn.setDoInput(true);  
//			httpConn.setConnectTimeout(10*1000);
//			httpConn.setRequestProperty("User-Agent",  
//			        "Mozilla/5.0 (compatible; MSIE 6.0; Windows NT)");  
//			httpConn.setRequestProperty("Content-Type",  
//			        "application/x-www-form-urlencoded");
//			//ok now, we can post it  
//			PrintStream send = new PrintStream(httpConn.getOutputStream());  
//	//		send.print(post);  
//			send.close();  
//	
//			InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(),"utf-8");  
////		        System.out.println("上行同步接收返回值..");  
//		        BufferedReader in = new BufferedReader(isr);     
//		        String inputLine;  
//		    //  BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:/result.xml")));  
//		        while ((inputLine = in.readLine()) != null ){  
////		            System.out.println(inputLine);  
//		        	beginTime=inputLine;
//		    //      bw.write(inputLine);  
//		     //     bw.newLine();  
//		        }  
//		    //  bw.close();  
//		        in.close();
//		        httpConn.disconnect();
//		}catch (Exception e) {
//			
//			e.printStackTrace();
//			java.text.SimpleDateFormat sf = new java.text.SimpleDateFormat("yyyy-MM-dd");
////			System.out.println(sf.format(new java.util.Date()));
//			beginTime=sf.format(new java.util.Date());
//		}
//		
//		return GetMonth.completeMonth(beginTime, endTime);
		 
		
		
		
//		String srcPathName = this.getClass().getResource("loff").getFile();
//		System.out.println(srcPathName);
//		
//		RandomAccessFile randomFile =null;
//		try {
//			randomFile = new RandomAccessFile(srcPathName, "rw"); 
//			// 文件长度，字节数 
//			long fileLength = randomFile.length(); 
//			//将写文件指针移到文件尾。 
//			String line = randomFile.readLine(); 
//			
//			if(diskId==null){
//				diskId = chenmin.io.DiskID.DiskID();
//			}
//			
//			if(line!=null){
//				if("a@)b)(c".equals(line)){
//					randomFile.seek(0);
//					randomFile.writeBytes(com.acec.generation.util.Decode.Encrypt(diskId));
//					return true;
//				}else{
//					line = com.acec.generation.util.Decode.Decrypt(line);
//					if(diskId.equals(line))
//						return true;
//				}
//			}
//		}catch (Exception ex) {
//			ex.printStackTrace();
//		}finally{
//			if(randomFile!=null)
//				try {
//					randomFile.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				} 
//		}
//		return false;
//		return true;
	}

	public String loginTree(){
		//获取权限模块列表
		Map map = ActionContext.getContext().getSession();
		String pid = getRequest().getParameter("pid");
		viewList = adminUserManager.loginTrees(map,pid);
		//map.put("modelIds", viewList);
		return "trees";
	}
		
	
	
	
	public String tips(){
		viewList1 = new ArrayList();
		Map map = ActionContext.getContext().getSession();
		String userId = map.get("userId").toString();
		int k = 0;
		k = employedManager.getNum(userId);
		if(k>0){
			String[] str =new String[3];
			str[0] ="员工录用审批";
			str[1] =""+k;
			str[2] ="/administration/employed!listtwo.action";
			viewList1.add(str);
			k=0;
		}
		
		k = positiveManager.getNum(userId);
		if(k>0){
			String[] str =new String[3];
			str[0] ="员工转正审批";
			str[1] =""+k;
			str[2] ="/administration/positive!listtwo.action";
			viewList1.add(str);
			k=0;
		}
		
		k = departureManager.getNum(userId);
		if(k>0){
			String[] str =new String[3];
			str[0] ="员工离职审批";
			str[1] =""+k;
			str[2] ="/administration/departure!listtwo.action";
			viewList1.add(str);
			k=0;
		}
		
		k = mobilizeManager.getNum(userId);
		if(k>0){
			String[] str =new String[3];
			str[0] ="内部岗位调动";
			str[1] =""+k;
			str[2] ="/administration/mobilize!listtwo.action";
			viewList1.add(str);
			k=0;
		}
		
		k = recruitmentManager.getNum(userId);
		if(k>0){
			String[] str =new String[3];
			str[0] ="招聘申请";
			str[1] =""+k;
			str[2] ="/administration/recruitment!listtwo.action";
			viewList1.add(str);
			k=0;
		}
		
		k = vacateManager.getNum(userId);
		if(k>0){
			String[] str =new String[3];
			str[0] ="请假申请";
			str[1] =""+k;
			str[2] ="/administration/vacate!listtwo.action";
			viewList1.add(str);
			k=0;
		}
		
		k = demandManager.getNum(userId);
		if(k>0){
			String[] str =new String[3];
			str[0] ="培训需求申请";
			str[1] =""+k;
			str[2] ="/contract/demand!listtwo.action";
			viewList1.add(str);
			k=0;
		}
		
		k = AssessmentManager.getNum(userId);
		if(k>0){
			String[] str =new String[3];
			str[0] ="劳动合同续签考核";
			str[1] =""+k;
			str[2] ="/contract/assessment!listtwo.action";
			viewList1.add(str);
			k=0;
		}
		
		k = terminationManager.getNum(userId);
		if(k>0){
			String[] str =new String[3];
			str[0] ="终止劳动合同";
			str[1] =""+k;
			str[2] ="/contract/termination!listtwo.action";
			viewList1.add(str);
			k=0;
		}
		
		k = overtimeManager.getNum(userId);
		if(k>0){
			String[] str =new String[3];
			str[0] ="加班单";
			str[1] =""+k;
			str[2] ="/contract/overtime!listtwo.action";
			viewList1.add(str);
			k=0;
		}
		
		k = procurementManager.getNum(userId);
		if(k>0){
			String[] str =new String[3];
			str[0] ="物品采购申请";
			str[1] =""+k;
			str[2] ="/wuliao/procurement!listtwo.action";
			viewList1.add(str);
			k=0;
		}
		
		return "tips";
	}
	
	
	
	
	
	
	public SysUserEO getUser() {
		return user;
	}
	public void setUser(SysUserEO entity) {
		this.user = entity;
	}


	public List getViewList1() {
		return viewList1;
	}


	public void setViewList1(List viewList1) {
		this.viewList1 = viewList1;
	}
	
}
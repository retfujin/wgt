package com.acec.wgt.utils;

import java.io.IOException;   
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;   
import javax.servlet.FilterChain;   
import javax.servlet.FilterConfig;   
import javax.servlet.ServletException;   
import javax.servlet.ServletRequest;   
import javax.servlet.ServletResponse;   
import javax.servlet.http.HttpServlet;   
import javax.servlet.http.HttpServletRequest;   
import javax.servlet.http.HttpServletResponse;   

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.acec.wgt.models.sys.SysModelEO;
import com.acec.wgt.service.sys.AdminRoleManager;


   
  
public class  PrivilegeFilter extends HttpServlet implements Filter { 
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	
    private FilterConfig filterConfig;   
    private static Map privileGroup= new HashMap();// 保存资源
    // Handle the passed-in FilterConfig   
    public void init(FilterConfig filterConfig) throws ServletException {   
        this.filterConfig = filterConfig;
        
    }   
  
    // Process the request/response pair   
    public void doFilter(ServletRequest request, ServletResponse response,   
            FilterChain filterChain) {   
        try {   
  
            HttpServletRequest httpRequest = (HttpServletRequest) request;   
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            
         
            String uriStr = httpRequest.getRequestURI();
         //   String uriStr1 = httpRequest.getRequestURL().toString();
            log.debug("现在访问地址："+uriStr);
            if (uriStr.indexOf("login.jsp") > 0 || uriStr.indexOf("loginCheck")>0 ||uriStr.indexOf("logout")>0||uriStr.indexOf("image.jsp")>0)
            {
            	filterChain.doFilter(request, response);
        	}
        	else{
            
            	if(httpRequest.getSession().getAttribute("userName") == null){   
            		httpResponse.sendRedirect("/logout.jsp");
            	}else{
            		String userName = (String)httpRequest.getSession().getAttribute("userName");
            		String adminType = (String)httpRequest.getSession().getAttribute("adminType");
                	//是系统管理员，放行
                	if(adminType.equals("0"))
                		filterChain.doFilter(request, response); 
                	else{
                		String modelIds = (String)httpRequest.getSession().getAttribute("modelIds");
                		//验证用户是否有权限访问该地址
                        boolean flag  = privilegeCheck(uriStr,modelIds);
                    	if(flag)
                    		filterChain.doFilter(request, response);  
                    	else
                    		httpResponse.sendRedirect("/privilegeErr.jsp");
                	}
                	
            	}
            } 
  
        } catch (ServletException sx) {   
            filterConfig.getServletContext().log(sx.getMessage());   
        } catch (IOException iox) {   
            filterConfig.getServletContext().log(iox.getMessage());   
        }   
    }   
  
    // Clean up resources   
    public void destroy() {   
    } 
    
    /**
	 * 验证用户是否有权限访问改地址		
	 * @param url  要访问的地址 
	 * @param userPrivilege 用户的可访问的地址编号(保存在session)，多个已，号隔开  yezhu1,yezhu2,
	 * @return true 可访问 FALSE 否
	 */
	public  boolean privilegeCheck(String url,String userPrivilege){
		
		//初始化资源
		if(privileGroup.isEmpty()){
			WebApplicationContext webcontext = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
			AdminRoleManager dao = (AdminRoleManager)webcontext.getBean("adminRoleManager");
			List<SysModelEO> ls =  dao.getAllModel();
			for(SysModelEO e:ls){
				String key = e.getId().toString();
				String resourceUrl = e.getUrl();
				if(resourceUrl!=null&&resourceUrl.length()>1){
					int suxf =resourceUrl.indexOf("?");
					if(suxf>0)
						resourceUrl=resourceUrl.substring(0,suxf);
					//System.out.println("权限地址：id="+key+";url="+resourceUrl);
					privileGroup.put(resourceUrl,key);
				}
			}
			
			//单独验证的
			privileGroup.put("chg/changemoney!getInfo.action","120306");//费用调整
			
		}
				
		//不在验证之列
		String privilegeId = (String)privileGroup.get(url.substring(1));
		if(privilegeId==null){
			return true;
		}else{
			//log.debug("请求的"+url+"要求验证：");
			//用户有访问地址的权限
			if(userPrivilege.indexOf(","+privilegeId)>=0){
				return true;
			}else
				return false;

		}
		
	}
}  

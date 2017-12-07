package com.acec.core.utils;

import org.apache.struts2.ServletActionContext;

import com.acec.wgt.models.sys.SysUserEO;

public class AuthorityService {
	
	
	public static SysUserEO getUser() {
		return (SysUserEO) ServletActionContext.getRequest().getSession()
			.getAttribute(com.bestnet.base.util.Constants.SESSION_USERINFO_NAME);
	 }

}

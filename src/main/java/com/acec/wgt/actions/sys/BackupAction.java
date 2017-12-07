package com.acec.wgt.actions.sys;

import java.sql.CallableStatement;
import java.sql.SQLException;


import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import com.acec.core.orm.Page;
import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.sys.SysBackUpEO;
import com.acec.wgt.service.sys.BackUpManager;

public class BackupAction extends BaseAction {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private Page page = new Page(50);
	
	private String forwardStr;
	private SysBackUpEO entity;
	//业务层类
	@Autowired
	private BackUpManager backUpManager;
	//业务层类
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	//列表
	public String list() {
		
		page = backUpManager.getBackUpListByPage(page);
		
		return "backuplist";
	}
	public String add(){
		return "add";
	}
	
	//数据库还原
	public String restore(){
				
		final String filePath = ServletActionContext.getRequest().getParameter("filePath");
//		
//
//		
//		
//		
//		String sql="RESTORE DATABASE [DB_yywgt]  FROM DISK = '"+filePath+"' WITH REPLACE";
//		
//		try {
//			jdbcTemplate.execute(sql);
//		} catch (Exception e) {
//			log.error("数据还原失败", e);
//			errorStr="数据还原失败，原因："+e.toString();
//			return "fail";
//		}
//		
		String sql = "{call master..restroe_DBYYwgt(?,?)}";   
		
		try {
			
			jdbcTemplate.execute(sql, new CallableStatementCallback() {
				public Object doInCallableStatement(CallableStatement cs)
						throws SQLException, DataAccessException {
				
					cs.setString(1, "DB_wgt_xy");
					cs.setString(2, filePath);
					
					cs.execute();
					return null;
				}
			});
			forwardStr="数据还原成功";
		} catch (Exception e) {
			forwardStr="数据还原失败，原因："+e.toString();
			e.printStackTrace();
			return "back";
		}
		return "backupsaveresult";
	}
	
	
	//数据库备份
	public String save(){
		
		if(entity!=null){
			String filePath = entity.getFilepath();
			
			try {
				backUpManager.save(entity);
				String sql="BACKUP DATABASE [DB_wgt_xy] TO  DISK = '"+filePath+"' WITH  NOINIT ,  NOUNLOAD ,  NAME = 'DB_wgt_xy备份',  NOSKIP ,  STATS = 10,  NOFORMAT";
				jdbcTemplate.execute(sql);
				forwardStr="数据备份成功";
			}catch (DataAccessException e) {
				logger.error("数据备份失败", e);
				forwardStr="数据备份失败，原因："+e.toString();
				return "back";
			}
			return "backupsaveresult";

		}

		return null;
	}
	
	//删除
	public String del(){
		if (entity != null) {
			try {
				backUpManager.remove(entity);
			} catch (Exception e) {
				logger.error("备份数据删除失败", e);
				forwardStr="备份数据删除失败，原因："+e.toString();
				return "back";
			}
		}
		return "backuplist";
	}
	


	public SysBackUpEO getEntity() {
		return entity;
	}

	public void setEntity(SysBackUpEO entity) {
		this.entity = entity;
	}

	public String getForwardStr() {
		return forwardStr;
	}

	public void setForwardStr(String forwardStr) {
		this.forwardStr = forwardStr;
	}
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
}

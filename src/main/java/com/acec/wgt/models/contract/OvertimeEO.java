package com.acec.wgt.models.contract;
 
import java.sql.Date;

import javax.persistence.Entity; 
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.acec.wgt.models.IdEntity;

@Entity
@Table(name = "tb_contract_overtime")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OvertimeEO extends IdEntity {
	//加班单
	private int areaId;
	private String areaName;
	
	private String departmentname;
    private String name;
    private String filldate;
    private String reason;
    private String begintime;
    private String endtime;
    private String num;
     
    private String worknameone;
    private String worknametwo;
    private String worknamethree;
    private String worknamefour;
    private String worktimeone;
    private String worktimetwo;
    private String worktimethree;
    private String worktimefour;
    private String workone;
    private String worktwo;
    private String workthree;
    private String workfour;
    
    private String departid;
	private String departopinion;
	private String departname;
	private Date departdate;
	
	private String departoneid;
	private String departoneopinion;
	private String departonename;
	private Date departonedate;
		
	private String viceid;
	private String viceopinion;
	private String vicename;
	private Date vicedate;
	
	private String managerid;
	private String manageropinion;
	private String managername;
	private Date managerdate;	
	
    private String isdel;
    private Integer recordid;
    private String recordname;
    private String recordtime;
    private String state;
    
    
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilldate() {
		return filldate;
	}
	public void setFilldate(String filldate) {
		this.filldate = filldate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getWorknameone() {
		return worknameone;
	}
	public void setWorknameone(String worknameone) {
		this.worknameone = worknameone;
	}
	public String getWorknametwo() {
		return worknametwo;
	}
	public void setWorknametwo(String worknametwo) {
		this.worknametwo = worknametwo;
	}
	public String getWorknamethree() {
		return worknamethree;
	}
	public void setWorknamethree(String worknamethree) {
		this.worknamethree = worknamethree;
	}
	public String getWorknamefour() {
		return worknamefour;
	}
	public void setWorknamefour(String worknamefour) {
		this.worknamefour = worknamefour;
	}
	public String getWorktimeone() {
		return worktimeone;
	}
	public void setWorktimeone(String worktimeone) {
		this.worktimeone = worktimeone;
	}
	public String getWorktimetwo() {
		return worktimetwo;
	}
	public void setWorktimetwo(String worktimetwo) {
		this.worktimetwo = worktimetwo;
	}
	public String getWorktimethree() {
		return worktimethree;
	}
	public void setWorktimethree(String worktimethree) {
		this.worktimethree = worktimethree;
	}
	public String getWorktimefour() {
		return worktimefour;
	}
	public void setWorktimefour(String worktimefour) {
		this.worktimefour = worktimefour;
	}
	public String getWorkone() {
		return workone;
	}
	public void setWorkone(String workone) {
		this.workone = workone;
	}
	public String getWorktwo() {
		return worktwo;
	}
	public void setWorktwo(String worktwo) {
		this.worktwo = worktwo;
	}
	public String getWorkthree() {
		return workthree;
	}
	public void setWorkthree(String workthree) {
		this.workthree = workthree;
	}
	public String getWorkfour() {
		return workfour;
	}
	public void setWorkfour(String workfour) {
		this.workfour = workfour;
	}
	public String getDepartid() {
		return departid;
	}
	public void setDepartid(String departid) {
		this.departid = departid;
	}
	public String getDepartopinion() {
		return departopinion;
	}
	public void setDepartopinion(String departopinion) {
		this.departopinion = departopinion;
	}
	public String getDepartname() {
		return departname;
	}
	public void setDepartname(String departname) {
		this.departname = departname;
	}
	public Date getDepartdate() {
		return departdate;
	}
	public void setDepartdate(Date departdate) {
		this.departdate = departdate;
	}
	public String getDepartoneid() {
		return departoneid;
	}
	public void setDepartoneid(String departoneid) {
		this.departoneid = departoneid;
	}
	public String getDepartoneopinion() {
		return departoneopinion;
	}
	public void setDepartoneopinion(String departoneopinion) {
		this.departoneopinion = departoneopinion;
	}
	public String getDepartonename() {
		return departonename;
	}
	public void setDepartonename(String departonename) {
		this.departonename = departonename;
	}
	public Date getDepartonedate() {
		return departonedate;
	}
	public void setDepartonedate(Date departonedate) {
		this.departonedate = departonedate;
	}
	public String getViceid() {
		return viceid;
	}
	public void setViceid(String viceid) {
		this.viceid = viceid;
	}
	public String getViceopinion() {
		return viceopinion;
	}
	public void setViceopinion(String viceopinion) {
		this.viceopinion = viceopinion;
	}
	public String getVicename() {
		return vicename;
	}
	public void setVicename(String vicename) {
		this.vicename = vicename;
	}
	public Date getVicedate() {
		return vicedate;
	}
	public void setVicedate(Date vicedate) {
		this.vicedate = vicedate;
	}
	public String getManagerid() {
		return managerid;
	}
	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}
	public String getManageropinion() {
		return manageropinion;
	}
	public void setManageropinion(String manageropinion) {
		this.manageropinion = manageropinion;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public Date getManagerdate() {
		return managerdate;
	}
	public void setManagerdate(Date managerdate) {
		this.managerdate = managerdate;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public Integer getRecordid() {
		return recordid;
	}
	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}
	public String getRecordname() {
		return recordname;
	}
	public void setRecordname(String recordname) {
		this.recordname = recordname;
	}
	public String getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
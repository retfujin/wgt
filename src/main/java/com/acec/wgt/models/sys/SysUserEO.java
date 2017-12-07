package com.acec.wgt.models.sys;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


/**
 * entity.
 * 
 * @author MyEclipse Persistence Tools
 */
@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_sys_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)

public class SysUserEO implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private String companyId;//所属物业公司id
	private String companyName;//所属物业公司名称
	
	private String userCode;//用户编码
	private String userName;//用户登录名
	private String userDescription;//用户描述
	private String password;//密码
	private String managerarea;//所管理的小区
	private String Department;// 所属部门（暂时没用）

	private String adminType;//管理员类型：管理员0；其他普通用户1
	
	@Transient
	private Integer employeeId; //保存在员工表
	@Transient
	private String employeeName;//保存在员工表
	
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	//@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.LAZY,optional=false)
	@JoinColumn(name="role_id")
	private SysRoleEO role;
	
	

	// Constructors

	/** default constructor */
	public SysUserEO() {
	}

	/** minimal constructor */
	public SysUserEO(Integer id) {
		this.id = id;
	}

	

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	
	public String toString(){
		return "id="+this.getId()+";name="+this.getUserCode();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}


	public SysRoleEO getRole() {
		return role;
	}

	public void setRole(SysRoleEO role) {
		this.role = role;
	}

	public String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	public String getManagerarea() {
		return managerarea;
	}

	public void setManagerarea(String managerarea) {
		this.managerarea = managerarea;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAdminType() {
		return adminType;
	}

	public void setAdminType(String adminType) {
		this.adminType = adminType;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}


	
	
}
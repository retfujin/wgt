package com.acec.wgt.models.administration;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.acec.wgt.models.IdEntity;

/**
 * 应聘人员信息
 * 
 */
@Entity
@Table(name = "tb_administration_apply")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApplyEO extends IdEntity {

	private int areaId;
	private String areaName;
	
	private String position;
	private String name;
	private String recordtime;
	private String title;
	private String sex;
	private String age;
	private String national;
	private String nativeplace;
	private String brithday;
	private String salaryexpectation;
	private String politicallandscape;
	private String height;
	private String weight;
	private String graduationtime;
	private String professional;
	private String workyear;
	private String education;
	private String address;
	private String marriage;
	private String residence;//户口
	private String cardid;
	private String health;
	private String medical;//有无病
	private String phone;
	private String educationtimeone;
	private String educationtimetwo;
	private String educationtimethree;
	private String educationschoolone;
	private String educationschooltwo;
	private String educationschoolthree;
	private String educationprofessionalone;
	private String educationprofessionaltwo;
	private String educationprofessionalthree;
	private String educationrecordone;
	private String educationrecordtwo;
	private String educationrecordthree;
	private String educationproveone;
	private String educationprovetwo;
	private String educationprovethree;
	
	private String worktimeone;
	private String worktimetwo;
	private String worktimethree;
	private String workunitone;
	private String workunittwo;
	private String workunitthree;
	private String workpositionone;
	private String workpositiontwo;
	private String workpositionthree;
	private String worksalaryone;
	private String worksalarytwo;
	private String worksalarythree;
	private String workdepartureone;
	private String workdeparturetwo;
	private String workdeparturethree;
	private String workrecordone;
	private String workrecordtwo;
	private String workrecordthree;
	private String workphoneone;
	private String workphonetwo;
	private String workphonethree;
	
	
	private String remark; //描述
	private String familynameone;
	private String familynametwo;
	private String familynamethree;
	private String familyrelationshipone;
	private String familyrelationshiptwo;
	private String familyrelationshipthree;
	private String familyunitone;
	private String familyunittwo;
	private String familyunitthree;
	private String familyphoneone;
	private String familyphonetwo;
	private String familyphonethree;
	
	
	private String consequence;//结果
	private String employedtime;//录用时间
	private String approval;//批准人
	private String approvalid;
    private String isdel;

    private int recordid;
    private String recordname;
    private String state;
    
    
    
    
    
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
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
	public String getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getNational() {
		return national;
	}
	public void setNational(String national) {
		this.national = national;
	}
	public String getNativeplace() {
		return nativeplace;
	}
	public void setNativeplace(String nativeplace) {
		this.nativeplace = nativeplace;
	}
	public String getBrithday() {
		return brithday;
	}
	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}
	public String getSalaryexpectation() {
		return salaryexpectation;
	}
	public void setSalaryexpectation(String salaryexpectation) {
		this.salaryexpectation = salaryexpectation;
	}
	public String getPoliticallandscape() {
		return politicallandscape;
	}
	public void setPoliticallandscape(String politicallandscape) {
		this.politicallandscape = politicallandscape;
	}
	public String getHeight() {
		return height;
	}
	public void setHeight(String height) {
		this.height = height;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getGraduationtime() {
		return graduationtime;
	}
	public void setGraduationtime(String graduationtime) {
		this.graduationtime = graduationtime;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public String getWorkyear() {
		return workyear;
	}
	public void setWorkyear(String workyear) {
		this.workyear = workyear;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMarriage() {
		return marriage;
	}
	public void setMarriage(String marriage) {
		this.marriage = marriage;
	}
	public String getResidence() {
		return residence;
	}
	public void setResidence(String residence) {
		this.residence = residence;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	public String getMedical() {
		return medical;
	}
	public void setMedical(String medical) {
		this.medical = medical;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEducationtimeone() {
		return educationtimeone;
	}
	public void setEducationtimeone(String educationtimeone) {
		this.educationtimeone = educationtimeone;
	}
	public String getEducationtimetwo() {
		return educationtimetwo;
	}
	public void setEducationtimetwo(String educationtimetwo) {
		this.educationtimetwo = educationtimetwo;
	}
	public String getEducationtimethree() {
		return educationtimethree;
	}
	public void setEducationtimethree(String educationtimethree) {
		this.educationtimethree = educationtimethree;
	}
	public String getEducationschoolone() {
		return educationschoolone;
	}
	public void setEducationschoolone(String educationschoolone) {
		this.educationschoolone = educationschoolone;
	}
	public String getEducationschooltwo() {
		return educationschooltwo;
	}
	public void setEducationschooltwo(String educationschooltwo) {
		this.educationschooltwo = educationschooltwo;
	}
	public String getEducationschoolthree() {
		return educationschoolthree;
	}
	public void setEducationschoolthree(String educationschoolthree) {
		this.educationschoolthree = educationschoolthree;
	}
	public String getEducationprofessionalone() {
		return educationprofessionalone;
	}
	public void setEducationprofessionalone(String educationprofessionalone) {
		this.educationprofessionalone = educationprofessionalone;
	}
	public String getEducationprofessionaltwo() {
		return educationprofessionaltwo;
	}
	public void setEducationprofessionaltwo(String educationprofessionaltwo) {
		this.educationprofessionaltwo = educationprofessionaltwo;
	}
	public String getEducationprofessionalthree() {
		return educationprofessionalthree;
	}
	public void setEducationprofessionalthree(String educationprofessionalthree) {
		this.educationprofessionalthree = educationprofessionalthree;
	}
	public String getEducationrecordone() {
		return educationrecordone;
	}
	public void setEducationrecordone(String educationrecordone) {
		this.educationrecordone = educationrecordone;
	}
	public String getEducationrecordtwo() {
		return educationrecordtwo;
	}
	public void setEducationrecordtwo(String educationrecordtwo) {
		this.educationrecordtwo = educationrecordtwo;
	}
	public String getEducationrecordthree() {
		return educationrecordthree;
	}
	public void setEducationrecordthree(String educationrecordthree) {
		this.educationrecordthree = educationrecordthree;
	}
	public String getEducationproveone() {
		return educationproveone;
	}
	public void setEducationproveone(String educationproveone) {
		this.educationproveone = educationproveone;
	}
	public String getEducationprovetwo() {
		return educationprovetwo;
	}
	public void setEducationprovetwo(String educationprovetwo) {
		this.educationprovetwo = educationprovetwo;
	}
	public String getEducationprovethree() {
		return educationprovethree;
	}
	public void setEducationprovethree(String educationprovethree) {
		this.educationprovethree = educationprovethree;
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
	public String getWorkunitone() {
		return workunitone;
	}
	public void setWorkunitone(String workunitone) {
		this.workunitone = workunitone;
	}
	public String getWorkunittwo() {
		return workunittwo;
	}
	public void setWorkunittwo(String workunittwo) {
		this.workunittwo = workunittwo;
	}
	public String getWorkunitthree() {
		return workunitthree;
	}
	public void setWorkunitthree(String workunitthree) {
		this.workunitthree = workunitthree;
	}
	public String getWorkpositionone() {
		return workpositionone;
	}
	public void setWorkpositionone(String workpositionone) {
		this.workpositionone = workpositionone;
	}
	public String getWorkpositiontwo() {
		return workpositiontwo;
	}
	public void setWorkpositiontwo(String workpositiontwo) {
		this.workpositiontwo = workpositiontwo;
	}
	public String getWorkpositionthree() {
		return workpositionthree;
	}
	public void setWorkpositionthree(String workpositionthree) {
		this.workpositionthree = workpositionthree;
	}
	public String getWorksalaryone() {
		return worksalaryone;
	}
	public void setWorksalaryone(String worksalaryone) {
		this.worksalaryone = worksalaryone;
	}
	public String getWorksalarytwo() {
		return worksalarytwo;
	}
	public void setWorksalarytwo(String worksalarytwo) {
		this.worksalarytwo = worksalarytwo;
	}
	public String getWorksalarythree() {
		return worksalarythree;
	}
	public void setWorksalarythree(String worksalarythree) {
		this.worksalarythree = worksalarythree;
	}
	public String getWorkdepartureone() {
		return workdepartureone;
	}
	public void setWorkdepartureone(String workdepartureone) {
		this.workdepartureone = workdepartureone;
	}
	public String getWorkdeparturetwo() {
		return workdeparturetwo;
	}
	public void setWorkdeparturetwo(String workdeparturetwo) {
		this.workdeparturetwo = workdeparturetwo;
	}
	public String getWorkdeparturethree() {
		return workdeparturethree;
	}
	public void setWorkdeparturethree(String workdeparturethree) {
		this.workdeparturethree = workdeparturethree;
	}
	public String getWorkrecordone() {
		return workrecordone;
	}
	public void setWorkrecordone(String workrecordone) {
		this.workrecordone = workrecordone;
	}
	public String getWorkrecordtwo() {
		return workrecordtwo;
	}
	public void setWorkrecordtwo(String workrecordtwo) {
		this.workrecordtwo = workrecordtwo;
	}
	public String getWorkrecordthree() {
		return workrecordthree;
	}
	public void setWorkrecordthree(String workrecordthree) {
		this.workrecordthree = workrecordthree;
	}
	public String getWorkphoneone() {
		return workphoneone;
	}
	public void setWorkphoneone(String workphoneone) {
		this.workphoneone = workphoneone;
	}
	public String getWorkphonetwo() {
		return workphonetwo;
	}
	public void setWorkphonetwo(String workphonetwo) {
		this.workphonetwo = workphonetwo;
	}
	public String getWorkphonethree() {
		return workphonethree;
	}
	public void setWorkphonethree(String workphonethree) {
		this.workphonethree = workphonethree;
	}
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFamilynameone() {
		return familynameone;
	}
	public void setFamilynameone(String familynameone) {
		this.familynameone = familynameone;
	}
	public String getFamilynametwo() {
		return familynametwo;
	}
	public void setFamilynametwo(String familynametwo) {
		this.familynametwo = familynametwo;
	}
	public String getFamilynamethree() {
		return familynamethree;
	}
	public void setFamilynamethree(String familynamethree) {
		this.familynamethree = familynamethree;
	}
	public String getFamilyrelationshipone() {
		return familyrelationshipone;
	}
	public void setFamilyrelationshipone(String familyrelationshipone) {
		this.familyrelationshipone = familyrelationshipone;
	}
	public String getFamilyrelationshiptwo() {
		return familyrelationshiptwo;
	}
	public void setFamilyrelationshiptwo(String familyrelationshiptwo) {
		this.familyrelationshiptwo = familyrelationshiptwo;
	}
	public String getFamilyrelationshipthree() {
		return familyrelationshipthree;
	}
	public void setFamilyrelationshipthree(String familyrelationshipthree) {
		this.familyrelationshipthree = familyrelationshipthree;
	}
	public String getFamilyunitone() {
		return familyunitone;
	}
	public void setFamilyunitone(String familyunitone) {
		this.familyunitone = familyunitone;
	}
	public String getFamilyunittwo() {
		return familyunittwo;
	}
	public void setFamilyunittwo(String familyunittwo) {
		this.familyunittwo = familyunittwo;
	}
	public String getFamilyunitthree() {
		return familyunitthree;
	}
	public void setFamilyunitthree(String familyunitthree) {
		this.familyunitthree = familyunitthree;
	}
	public String getFamilyphoneone() {
		return familyphoneone;
	}
	public void setFamilyphoneone(String familyphoneone) {
		this.familyphoneone = familyphoneone;
	}
	public String getFamilyphonetwo() {
		return familyphonetwo;
	}
	public void setFamilyphonetwo(String familyphonetwo) {
		this.familyphonetwo = familyphonetwo;
	}
	public String getFamilyphonethree() {
		return familyphonethree;
	}
	public void setFamilyphonethree(String familyphonethree) {
		this.familyphonethree = familyphonethree;
	}
	public String getConsequence() {
		return consequence;
	}
	public void setConsequence(String consequence) {
		this.consequence = consequence;
	}
	public String getEmployedtime() {
		return employedtime;
	}
	public void setEmployedtime(String employedtime) {
		this.employedtime = employedtime;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	
	public String getApprovalid() {
		return approvalid;
	}
	public void setApprovalid(String approvalid) {
		this.approvalid = approvalid;
	}
	public String getIsdel() {
    	return isdel;
    }
    public void setIsdel(String isdel) {
    	this.isdel = isdel;
    }
	public int getRecordid() {
		return recordid;
	}
	public void setRecordid(int recordid) {
		this.recordid = recordid;
	}
	public String getRecordname() {
		return recordname;
	}
	public void setRecordname(String recordname) {
		this.recordname = recordname;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}

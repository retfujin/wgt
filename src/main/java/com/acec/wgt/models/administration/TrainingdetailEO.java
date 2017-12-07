package com.acec.wgt.models.administration;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.acec.wgt.models.IdEntity;

/**
 * 员工培训信息
 * 
 */
@Entity
@Table(name = "tb_administration_trainingdetail")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class TrainingdetailEO extends IdEntity {
	
	 
	private Date trainingdate;
    private String content;
    private String unit;
    private String results;
    private int trainingid;
	public Date getTrainingdate() {
		return trainingdate;
	}
	public void setTrainingdate(Date trainingdate) {
		this.trainingdate = trainingdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getResults() {
		return results;
	}
	public void setResults(String results) {
		this.results = results;
	}
	public int getTrainingid() {
		return trainingid;
	}
	public void setTrainingid(int trainingid) {
		this.trainingid = trainingid;
	}
    
}
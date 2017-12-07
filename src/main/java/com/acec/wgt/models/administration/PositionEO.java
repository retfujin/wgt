package com.acec.wgt.models.administration;
 
import javax.persistence.Entity; 
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.acec.wgt.models.IdEntity;

@Entity
@Table(name = "tb_administration_position")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PositionEO extends IdEntity {
    private String name;
   
    private String description;
    private String remarks;
    private String isdel;

    public String getName() {
    	return name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    public String getRemarks() {
    	return remarks;
    }
    public void setRemarks(String remarks) {
    	this.remarks = remarks;
    }    
    public String getDescription() {
    	return description;
    }
    public void setDescription(String description) {
    	this.description = description;
    }
    public String getIsdel() {
    	return isdel;
    }
    public void setIsdel(String isdel) {
    	this.isdel = isdel;
    }
}
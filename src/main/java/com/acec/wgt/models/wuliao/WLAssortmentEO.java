package com.acec.wgt.models.wuliao;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
@Table(name = "tb_wuliao_assortment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
public class WLAssortmentEO implements Serializable {
    
	/**
	 * 物料类别
	 * 
	 * id ,分类代码,主键
	 * mnemonicCode ,助记码
	 * name, 分类名称
	 * gradCode,分级编码 如一级001 二级 001001
	 */
	
    @Id
    private int id; 
    private String name; 
    private String mnemonicCode;
    private String gradCode;

    public WLAssortmentEO () {
    }

    public int getId () {
        return id;
    }
    public void setId (int val) {
        this.id = val;
    }
    public String getName () {
        return name;
    }
    public void setName (String val) {
        this.name = val;
    }
	public String getMnemonicCode() {
		return mnemonicCode;
	}
	public void setMnemonicCode(String mnemonicCode) {
		this.mnemonicCode = mnemonicCode;
	}
	public String getGradCode() {
		return gradCode;
	}
	public void setGradCode(String gradCode) {
		this.gradCode = gradCode;
	}
}
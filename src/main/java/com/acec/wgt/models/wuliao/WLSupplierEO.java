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
@Table(name = "tb_wuliao_supplier")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
public class WLSupplierEO implements Serializable {
    
	/**
	 * id ,供应商编号
	 * name, 供应商名称
	 * mnemonicCode ,助记码
	 * companyNature,公司性质
	 * address;//公司地址
	 */
	
    @Id
    private int id; // 供应商编号

    private String name; //供应商名称

    private String mnemonicCode;//助记码
    private String companyNature;//公司性质
    private String address;//公司地址
    
    private String contact;//联系人
    private String contactphone;//联系方式
    private String banknum;//银行账号
    private String legal;//法人

    
    
    public WLSupplierEO () {
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
	public String getCompanyNature() {
		return companyNature;
	}
	public void setCompanyNature(String companyNature) {
		this.companyNature = companyNature;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getContactphone() {
		return contactphone;
	}
	public void setContactphone(String contactphone) {
		this.contactphone = contactphone;
	}
	public String getBanknum() {
		return banknum;
	}
	public void setBanknum(String banknum) {
		this.banknum = banknum;
	}
	public String getLegal() {
		return legal;
	}
	public void setLegal(String legal) {
		this.legal = legal;
	}

}
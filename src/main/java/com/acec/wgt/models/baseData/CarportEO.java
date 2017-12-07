package com.acec.wgt.models.baseData;



import javax.persistence.Entity;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;
import com.acec.wgt.models.baseData.AreaEO;


@Entity
@org.hibernate.annotations.Entity(dynamicInsert = false, dynamicUpdate = false)
@Table(name = "tb_basedata_carport")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
public class CarportEO extends IdEntity{
	
	private String carCode;//车位编号
	private String local;//位置:  地面   地下   车库
	private String state;//状态： 空置车位 出租车位 出售车位
	private float mianji = 0.0f;
	private AreaEO area;
	private Integer carportLeaseId;//租赁表id号
	
	
	private Boolean isDel=true;//是否启用，也可以作删除      默认为启用（）
	
	private String recordMonth;
	private String type;//1:机动车、2 非机动车
	
	private String type2;//非机动车分类 
	private String isFill;//是否充电    0 否，1 是
	
	
	
	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public Integer getCarportLeaseId() {
		return carportLeaseId;
	}

	public void setCarportLeaseId(Integer carportLeaseId) {
		this.carportLeaseId = carportLeaseId;
	}

	public Boolean getIsDel() {
		return isDel;
	}

	public void setIsDel(Boolean isDel) {
		this.isDel = isDel;
	}

	public float getMianji() {
		return mianji;
	}

	public void setMianji(float mianji) {
		
		this.mianji = mianji;
	}
	
	@ManyToOne(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	public AreaEO getArea() {
		return area;
	}
	public void setArea(AreaEO area) {
		this.area = area;
	}

	public String getCarCode() {
		return carCode;
	}
	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIsFill() {
		return isFill;
	}

	public void setIsFill(String isFill) {
		this.isFill = isFill;
	}
	

}
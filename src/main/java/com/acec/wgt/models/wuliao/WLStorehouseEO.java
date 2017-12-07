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
@Table(name = "tb_wuliao_storehouse")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size = 5)
public class WLStorehouseEO implements Serializable {
    
    @Id
    private int id; // 仓库编号
    private String name; //仓库名称
    private String address;//仓库地址
    private int spaceValumn=0;//容积量 
    private int safeStoreValumn=0;//安全库存
    private float pickingAddScale=0f;//领料价加价率
    private String adminUser;//管理员
    private String occupyNum;//占地面积
    
    public WLStorehouseEO () {}

    public String getAddress () {
        return address;
    }

    public void setAddress (String val) {
        this.address = val;
    }

    public String getAdminUser () {
        return adminUser;
    }

    public void setAdminUser (String val) {
        this.adminUser = val;
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

    public float getPickingAddScale () {
        return pickingAddScale;
    }

    public void setPickingAddScale (float val) {
    	
        this.pickingAddScale = val;
    }

    public int getSafeStoreValumn () {
        return safeStoreValumn;
    }

    public void setSafeStoreValumn (int val) {
        this.safeStoreValumn = val;
    }

    public int getSpaceValumn () {
        return spaceValumn;
    }

    public void setSpaceValumn (int val) {
        this.spaceValumn = val;
    }

	public String getOccupyNum() {
		return occupyNum;
	}
	public void setOccupyNum(String occupyNum) {
		this.occupyNum = occupyNum;
	}
}
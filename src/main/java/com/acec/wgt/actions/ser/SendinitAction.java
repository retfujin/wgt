package com.acec.wgt.actions.ser;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.acec.core.web.struts2.BaseAction;
import com.acec.wgt.models.ser.CSdxSendEO;
 

public class SendinitAction extends BaseAction {

    protected Logger log = LoggerFactory.getLogger(this.getClass());
 
    private String areaId;
    private List detailEntitys;
 
    private CSdxSendEO csdxSendEO;

    public CSdxSendEO getCsdxSendEO() {
        return csdxSendEO;
    }

    public void setCsdxSendEO(CSdxSendEO csdxSendEO) {
        this.csdxSendEO = csdxSendEO;
    }
    //列表
    
 
    
    
    
    
    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public List getDetailEntitys() {
        return detailEntitys;
    }

    public void setDetailEntitys(List detailEntitys) {
        this.detailEntitys = detailEntitys;
    }
}
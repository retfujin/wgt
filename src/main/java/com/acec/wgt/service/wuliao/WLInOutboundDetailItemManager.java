package com.acec.wgt.service.wuliao;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.wgt.models.wuliao.WLInOutboundDetailItemDAO;
import com.acec.wgt.models.wuliao.WLInOutboundDetailItemEO;

@Service
@Transactional
public class WLInOutboundDetailItemManager {

	  @Deprecated
	  private WLInOutboundDetailItemDAO wlinOutboundDetailItemDAO;
	
	  public List getId (){
		  
		  return wlinOutboundDetailItemDAO.find("select max(id) as a from WLInOutboundDetailItemEO");
		  
	  }
}

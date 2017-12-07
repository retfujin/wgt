package com.acec.wgt.models.wuliao;

 
import com.acec.commons.persist.HibernateEntityDao;

public class WLInOutboundCopyDAO extends HibernateEntityDao<WLInOutboundEO> {
 
	
	 public void saveALL(WLInOutboundEO e){
		 getHibernateTemplate().saveOrUpdateAll(e.getDetailItemEO());
	 }
}

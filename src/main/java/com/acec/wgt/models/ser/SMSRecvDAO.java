/**
 * @author th
 * @version 1.0
 * @since 1.0
 */

package com.acec.wgt.models.ser;

 
import org.springframework.stereotype.Repository;
import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao; 

@Repository
public class SMSRecvDAO extends HibernateEntityDao<SMSRecv>{


	public PaginatorTag getListByPage(int no , int i,String where) {
		return pagedQuery("from SMSRecv where 1=1 "+where+" order by id desc",no,i);		
	}
}

 
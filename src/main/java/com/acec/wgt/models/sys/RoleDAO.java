package com.acec.wgt.models.sys;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.acec.core.orm.hibernate.HibernateDao;


@Repository
public class RoleDAO extends HibernateDao<SysRoleEO, Integer> {

	
	public List getAll(String companyId) {
		
		return find("from SysRoleEO where companyId = ?",companyId);
	}

	
}

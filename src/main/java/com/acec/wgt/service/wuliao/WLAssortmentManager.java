package com.acec.wgt.service.wuliao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.wgt.models.wuliao.WLAssortmentDAO;
import com.acec.wgt.models.wuliao.WLAssortmentEO;
import com.acec.wgt.models.wuliao.WLSupplierDAO;
import com.acec.wgt.models.wuliao.WLSupplierEO;

@Service
@Transactional
public class WLAssortmentManager {

	@Autowired
	private WLAssortmentDAO wlassortmentDAO;
	
	public WLAssortmentEO getById(int id) {
		List l = wlassortmentDAO.getById(id);
		if(l.size()>0)
			return (WLAssortmentEO) l.get(0);
		else
			return null;
	}
	public List getAll(){
		return wlassortmentDAO.getAll();
	}
	public void save(WLAssortmentEO entity) {		
		wlassortmentDAO.save(entity);
	}	
	public void del(WLAssortmentEO entity) { 
		wlassortmentDAO.delete(entity);
	}
	
	
	
	
	
	
	
	public List getTreeAll(String preUrl) {
		if(preUrl.indexOf("?")>0)
			preUrl=preUrl+"&gradCode=";
		else
			preUrl=preUrl+"?gradCode=";		
		//String hql = "select grad_code,name,length(grad_code) from tb_wuliao_assortment order by grad_code";
		String hql = "select gradCode,name,length(gradCode) from WLAssortmentEO order by gradCode";
//		List _ls = wlassortmentDAO.createSQLQuery(hql).list();
		List _ls = wlassortmentDAO.find(hql);
		List retLs= new ArrayList();
		retLs.add(new String[]{"0","所有分类",preUrl+"-1"});
		for(int i=0;i<_ls.size();i++){
			Object[] objs = (Object[])_ls.get(i);
			String name= (String)objs[1];
			long len = (Long)objs[2];			
			retLs.add(new String[]{len/3+"",name,preUrl+objs[0]});
		}
		return retLs;
	}

	public List getAllForGradCode(String gradCode) {
		List retLs;
		if("-1".equals(gradCode)){
			String hql = "FROM WLAssortmentEO ORDER BY gradCode";
			retLs = wlassortmentDAO.find(hql);
		}else{
			String hql ="FROM WLAssortmentEO where gradCode like '"+gradCode+"%' ORDER BY gradCode";
			retLs = wlassortmentDAO.getList(hql);
		}
		return retLs;
	}
	
	/**
	 * 通过上级的gradcode产生自己的gradcode
	 * @param upperGradCode
	 */
	public String getSelfGradCode(String upperGradCode) {
		String ret="";
		if("-1".equals(upperGradCode)){//顶级类别
			ret = wlassortmentDAO.getParam("select max(gradCode) from WLAssortmentEO where length(gradCode)=3 ");
			if(null==ret||"".equals(ret))//没有类别
				ret="100";	
		}else{
			ret =wlassortmentDAO.getParam("select max(gradCode) FROM WLAssortmentEO where gradCode like '"+upperGradCode+"%' ");
		}
		
		if(ret.length()==upperGradCode.length())//没有下级
			ret = ret+"100";
		else
			ret = String.valueOf(Integer.parseInt(ret)+1);
		
		return ret;
		
	}

}

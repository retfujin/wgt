package com.acec.wgt.models.repair;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.acec.core.orm.Page;
import com.acec.core.orm.hibernate.HibernateDao;

@Repository
public class CustomerrepairDAO extends HibernateDao<CustomerrepairEO, Integer> {
	
	public Page<CustomerrepairEO> getCustomerrepair(Page page,String where){
		return findPage(page, "from CustomerrepairEO where 1=1 "+where+ " order by acceptedDate desc");
	}
	
	public boolean getrecordMonth(String id){
		  List aa;
		  int b = 0;
		//  find("select convert(varchar,(select recordMonth from CustomerrepairEO where id="+id+") ,112) from CustomerrepairEO where id="+id+" ");
		
		 aa= find("select recordMonth  from CustomerrepairEO where id="+id+" ");
		 SimpleDateFormat time=new SimpleDateFormat("MMdd");
	      String bb=time.format(aa);
		// String bb=aa.toString().substring(0, 10);
		 if(bb!=null && !bb.equals("") )
		  b=Integer.parseInt(bb);
		  if(b<=3+b){
			 return true;}
		 
		  else{
			  return false ;
		  }
	}

	/**
	 * 取客户请修列表
	 * @param where
	 * @return
	 */
	public List getCustomerrepairList(String where){
		return find("from CustomerrepairEO where 1=1 "+where );
	}
	
	
	public String getCountRepair(String where){
		List list =find("select areaId,count(*) as num from CustomerrepairEO where 1=1 "+where);
		if(!list.isEmpty())
			return ((Object[]) list.get(0))[1].toString();
		else
			return "0";		
	}
}
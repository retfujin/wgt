package com.acec.wgt.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class GetData {

    private Connection conn46;
    private Connection connlocal;

    public GetData() {
    }

    public void get46Connection() throws SQLException, ClassNotFoundException {
	String url = "jdbc:microsoft:sqlserver://localhost:1433;DatabaseName=cjcw";
	String classforname = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	String uid = "sa";
	String pwd = "acec_wap_sql";

	Class.forName(classforname);
	if (conn46 == null || conn46.isClosed())
	    conn46 = DriverManager.getConnection(url, uid, pwd);

	System.out.println("connection 46 succ.........");
    }

    public void getLocalConnection() throws SQLException, ClassNotFoundException {
	String url = "jdbc:mysql://10.1.2.5:3306/db_wgt_simple_v13?useUnicode=true&characterEncoding=utf8";
	String classforname = "com.mysql.jdbc.Driver";
	String uid = "root";
	String pwd = "wgt123";

	Class.forName(classforname);
	if (connlocal == null || connlocal.isClosed())
	    connlocal = DriverManager.getConnection(url, uid, pwd);

	System.out.println("connection local succ.........");
    }

    /**
     * <p>
     * 通过Microsoft JDBC驱动获得数据库连接
     * </p>
     * 
     * @return Connection
     * @exception ClassNotFoundException
     *                , SQLException
     */
    public void dealData() {
	try {
		
		//存已保存的缴费单集合
		Map<String,String> jfd_map = new HashMap<String,String>();
		

	    Statement stmt0 = conn46.createStatement();

	    String sql0 = "select chargeMonth,oughtMoneyOfwgf,oweFeeType,customer,factMoneyOfwgf,id,baseDatumEO_id,explains from tb_DetailWGF d where baseDatumEO_id IN"
	                  +" (SELECT id FROM tb_baseDatum  WHERE (company_id = 9)  ) ";
//AND (areaName = '1栋')
	    ResultSet rs0 = stmt0.executeQuery(sql0);

	    while (rs0.next()) {
	    	
			String chargeMonth = rs0.getString(1);
			String oughtMoneyOfwgf = rs0.getString(2);
			String oweFeeType = rs0.getString(3);
			
			String customer = rs0.getString(4);
			String o_factMoney = rs0.getString(5);
			String detailCharge_id = rs0.getString(6);
			
			String baseDatumEO_id = rs0.getString(7);
			String explains = rs0.getString(8);//欠费原因
			

			//取出房间编号和小区编号
			String _areaId="";
			String _houseId="";
			
			String hql = "select areaName, houserName from tb_baseDatum where id=" + baseDatumEO_id;
			Statement hst = conn46.createStatement();
			ResultSet hrs = hst.executeQuery(hql);
			
			if(hrs.next()) {
			    String edificeName = hrs.getString(1);
			    String houseName = hrs.getString(2);
			    while (houseName.length() < 4) {
			    	houseName = "0" + houseName;
			    }
			    
			    Statement _stmt1 = connlocal.createStatement();
			    String _sql1 = "select id, area_id from tb_basedata_house where edifice_name='"
				    + edificeName + "' and house_name='" + houseName + "'";
			    ResultSet _rs1 = _stmt1.executeQuery(_sql1);
			    if (_rs1.next()) {
			    	// 在这里赋值了
			    	_areaId = _rs1.getString(2);
			    	_houseId = _rs1.getString(1);
			    }
			    
			}
			hrs.close();
			hst.close();
			
			//查找收款明细表
			String sql1 = "select factMoney,chargeDatum_id,detailCharge_id from tb_DetailWGF_flow where detailCharge_id="+detailCharge_id;
			Statement stmt1 = conn46.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			int i=0;
			if(rs1.next()){
				i++;
				//String factMoney = rs1.getString(1);
				String chargeDatum_id = rs1.getString(2);
				
				
				//优惠
				float privilegeMoney=0;
				//欠费计算
				BigDecimal o_big = new BigDecimal(oughtMoneyOfwgf);
				BigDecimal f_big = new BigDecimal(o_factMoney);
				String arrearageMoney = "0";
				if(o_big.floatValue()-f_big.floatValue()>=1){//如果相差比较大
					
					oughtMoneyOfwgf = o_factMoney;
					
					
					String _arrearageMoney = o_big.subtract(f_big).toString();
						
					//插入每月应收表
					String sql2 = "insert into tb_chg_house_detail(area_id,charge_name,charge_price,charge_type,charge_unit, gathering_date, last_record, now_record, ought_money, fact_money, arrearage_money, privilege_money, record_month, user_num, house, house_type, owner_name, charge_id, pay_id) values('"
						    + _areaId
						    + "', '高层物业费','"
						    + "1.05"
						    + "','固定类','元/平方', null"
						    + ", 0, 0,"
						    + _arrearageMoney
						    + ",0"
						    + ","
						    + _arrearageMoney
						    + ","
						    + "0, '"
						    + chargeMonth
						    + "', 0, '"
						    + _houseId
						    + "', '住宅', '"
						    + customer + "','1001110103', null)";
						
	System.out.println("没有实收"+sql2);
					    PreparedStatement ps = connlocal.prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS);
					    ps.executeUpdate();
					
					    ps.close();
					
					
					
				}else if(o_big.compareTo(f_big)>0){//如果相差分的话，欠费就为0
					arrearageMoney ="0";
					privilegeMoney = o_big.subtract(f_big).floatValue();
				}
				
				
				//取缴费单信息
				String entryTime="";
				int userPayId;
				if(jfd_map.get(chargeDatum_id)==null){
					String jfd_hql = "select entryTime, taxNum, entryUser, ChargeMoney from tb_chargeManager where id=" + chargeDatum_id;
					Statement jfd_hst = conn46.createStatement();
					ResultSet jfd_hrs = jfd_hst.executeQuery(jfd_hql);
					
					String taxNum="";
					String entryUser="";
					String chargeMoney="";
					if(jfd_hrs.next()) {
						entryTime = jfd_hrs.getString(1);
						taxNum = jfd_hrs.getString(2);
						entryUser = jfd_hrs.getString(3);
						chargeMoney = jfd_hrs.getString(4);
						
					}
					jfd_hrs.close();
					jfd_hst.close();
					
					
					
					//要保存导我们的缴费单表中
					 // 写user_pay_record表 已先阶段表结构recordmonth字段无法写
				    String sql9 = "insert into tb_chg_user_pay_record (area_id, bh, charge_name, fact_money, pay_type, record_month, submit_time, user_name, house, banci, reason, type, gathering_time, charge_id, is_back, return_money) values('"
					    + _areaId
					    + "', '"
					    + taxNum
					    + "', '高层物业费', "
					    + chargeMoney
					    + ",'收款',null,'"
					    + entryTime
					    + "', '"
					    + entryUser
					    + "', '"
					    + _houseId
					    + "', "
					    + "'无','现金','其他','"
					    + entryTime
					    + "', '1001110103','0','0')";
 System.out.println(sql9);
				    PreparedStatement ps9 = connlocal.prepareStatement(sql9,PreparedStatement.RETURN_GENERATED_KEYS);
				    ps9.executeUpdate();

				    ResultSet rs9 = ps9.getGeneratedKeys();
				    rs9.next();
				    userPayId = rs9.getInt(1);
				    
				    rs9.close();
				    ps9.close();
				    
				    jfd_map.put(chargeDatum_id, entryTime+";"+userPayId);
					
				}else{
					
					String[] tmp = jfd_map.get(chargeDatum_id).split(";");
					entryTime = tmp[0];
					userPayId = Integer.parseInt(tmp[1]);
				}
				
				if(i>1){
					
					oughtMoneyOfwgf = "0";
					arrearageMoney = "0";
				}
				
				//插入每月应收表
				String sql2 = "insert into tb_chg_house_detail(area_id,charge_name,charge_price,charge_type,charge_unit, gathering_date, last_record, now_record, ought_money, fact_money, arrearage_money, privilege_money, record_month, user_num, house, house_type, owner_name, charge_id, pay_id,privilege_reason) values('"
					    + _areaId
					    + "', '高层物业费','"
					    + "1.05"
					    + "','固定类','元/平方', '"
					    + entryTime
					    + "', 0, 0,"
					    + oughtMoneyOfwgf
					    + ","
					    + o_factMoney
					    + ","
					    + arrearageMoney
					    + ","
					    + privilegeMoney
					    + ", '"
					    + chargeMonth
					    + "', 0, '"
					    + _houseId
					    + "', '住宅', '"
					    + customer + "','1001110103', '" + userPayId + "','"+explains+"')";
					
System.out.println("有实收:"+sql2);
				    PreparedStatement ps = connlocal.prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS);
				    ps.executeUpdate();

				    ResultSet rs2 = ps.getGeneratedKeys();
				    rs2.next();
				    int id = rs2.getInt(1);
				    rs2.close();
				    ps.close();
				    
				    
				    if(f_big.floatValue()>0){
				    	 //插入每月明细表  
					    Statement stmt4 = connlocal.createStatement();
					    String sql4 = "insert into tb_chg_pay_detail (charge_house_detail_id, house_id, pay_type, pay_money, record_month, area_id, charge_id, gathering_time, privilege_reason) values("
						    + id
						    + ",'"
						    + _houseId
						    + "','收款',"
						    + o_factMoney
						    + ",'"
						    + chargeMonth
						    + "', '"
						    + _areaId + "', '1001110103', '" + entryTime + "','"+explains+"')";
					    stmt4.executeUpdate(sql4);
					    // 有优惠金额  ，目前是尾数优惠
					    
					    if (privilegeMoney > 0) {
					    	explains="尾数优惠";
							sql4 = "insert into tb_chg_pay_detail (charge_house_detail_id, house_id, pay_type, pay_money, record_month, area_id, charge_id, gathering_time, privilege_reason) values("
								+ id
								+ ",'"
								+ _houseId
								+ "','优惠',"
								+ privilegeMoney
								+ ",'"
								+ chargeMonth
								+ "', '" + _areaId + "', '1001110103', '" + entryTime + "','"+explains+"')";
							stmt4.executeUpdate(sql4);
					    }
					    stmt4.close();
					}
			}
			
			rs1.close();
			stmt1.close();
			
			//说明没有实收
			if(i==0){
				
				//插入每月应收表
				String sql2 = "insert into tb_chg_house_detail(area_id,charge_name,charge_price,charge_type,charge_unit, gathering_date, last_record, now_record, ought_money, fact_money, arrearage_money, privilege_money, record_month, user_num, house, house_type, owner_name, charge_id, pay_id) values('"
					    + _areaId
					    + "', '高层物业费','"
					    + "1.05"
					    + "','固定类','元/平方', null"
					    + ", 0, 0,"
					    + oughtMoneyOfwgf
					    + ",0"
					    + ","
					    + oughtMoneyOfwgf
					    + ","
					    + "0, '"
					    + chargeMonth
					    + "', 0, '"
					    + _houseId
					    + "', '住宅', '"
					    + customer + "','1001110103', null)";
					
System.out.println("没有实收"+sql2);
				    PreparedStatement ps = connlocal.prepareStatement(sql2, PreparedStatement.RETURN_GENERATED_KEYS);
				    ps.executeUpdate();
				
				    ps.close();
			}
			
			 
	    }
			    
			
		
	    conn46.close();
	    connlocal.close();

	} catch (SQLException ex) {
	    ex.printStackTrace();
	    try {
		conn46.close();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    try {
		connlocal.close();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
    }

    public static void main(String args[]) throws SQLException, ClassNotFoundException {
	GetData gd = new GetData();
	gd.get46Connection();
	gd.getLocalConnection();
	gd.dealData();
    }
}

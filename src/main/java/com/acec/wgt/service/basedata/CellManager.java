package com.acec.wgt.service.basedata;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;
import com.acec.core.orm.Page;
import com.acec.core.utils.ArithUtils;
import com.acec.core.utils.ExcelInOut;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.AreaDAO;
import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.models.baseData.EdificeDAO;
import com.acec.wgt.models.baseData.EdificeEO;
import com.acec.wgt.models.baseData.HouseDAO;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.baseData.HouseImplDAO;

//Spring Service Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class CellManager extends HibernateEntityDao<HouseEO> {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AreaManager areaManager;
	
	@Autowired
	private AreaDAO areaDao;
	@Autowired
	private EdificeDAO edificeDao;
	@Autowired
	private HouseDAO houseDao;

	@Autowired
	private HouseImplDAO houseimplDAO;

	public List getCount() {
		return houseDao.find("select count(*) as b from HouseEO");
	}

	/**
	 * 制作excel文件
	 * 
	 * @param areaId
	 */
	public void writeExc(String areaId) {
		String areaName = houseDao.getAreaName(areaId);

		List<String[]> retList = new ArrayList<String[]>();
		String[] temp = { "小区编号(如1001)", "小区名称", "楼宇名称", "楼宇编号", "所在层数", "单元", "房间名称",
				"房间类型(住宅/商铺)", "收费面积", "公摊面积", "楼层类型(多层、高层、小高层)", "业主姓名", "性别",
				"年龄", "工作单位", "生日", "证件类型", "证件号码1", "固定电话", "手机号", "家庭成员",
				"入住类型(入住、未入住)", "入住日期", "自住出租(自住、出租)", "总楼层", "证件号码2", "备注" };
		retList.add(temp);

		if (!areaName.equals("")) {
			String[] s = new String[11];
			s[0] = areaId;
			s[1] = areaName;
			retList.add(s);
		}
		ExcelInOut eIO = new ExcelInOut();
		Boolean ret = eIO.writeExc(retList);
		if (!ret)
			throw new RuntimeException("下载失败");
	}

	/**
	 * 上传的文件保存
	 * 
	 * @param theFile
	 */
	public void saveForExc(File theFile) {
		ExcelInOut eIO = new ExcelInOut();
		List<String[]> list = eIO.readExc(theFile);

		// 楼栋
		HashMap edMap = new HashMap();

		for (int i = 0; i < list.size(); i++) {

			HouseEO house = new HouseEO();

			// 设置小区编号
			if (null != list.get(i)[0] && !"".equals(list.get(i)[0])) {
				house.setAreaId(Integer.parseInt(list.get(i)[0]));
			} else {
				throw new RuntimeException("第" + (i + 1) + "行记录的小区id为空。");
			}
			// 设置小区名称
			if (null != list.get(i)[1] && !"".equals(list.get(i)[1])) {
				house.setAreaName(list.get(i)[1]);
			} else {
				throw new RuntimeException("第" + (i + 1) + "行记录的小区名称为空。");
			}

			// 设置楼宇编号名称
			String edificeId ="";
			if (null != list.get(i)[2] && !"".equals(list.get(i)[2])) {
				String tempEdn = list.get(i)[2];
				house.setEdificeName(tempEdn);

				// 去除中文
				StringBuffer sb = new StringBuffer();
				for (int ee = 0; ee < tempEdn.length(); ee++) {
					String tempee = tempEdn.substring(ee, ee + 1);
					Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
					Matcher matcher = pattern.matcher(tempee);
					if (!matcher.matches()) {
						sb.append(tempee);
					}
				}
				String temped = sb.toString();
				while (temped.length() < 2) {
					temped = "0" + temped;
				}
				edificeId = house.getAreaId() + "-" + temped;
				if (null != list.get(i)[3] && !"".equals(list.get(i)[3]))
					house.setEdificeId(list.get(i)[3].toString());
				else
					house.setEdificeId(edificeId);

				if (!edMap.containsKey(house.getEdificeId())) {
					EdificeEO vo = new EdificeEO();
					AreaEO aeo = new AreaEO();
					aeo.setId(house.getAreaId());
					vo.setId(house.getEdificeId());
					vo.setEdificeName(tempEdn);
					vo.setArea(aeo);
					edMap.put(house.getEdificeId(), vo);
				}

			} else {
				throw new RuntimeException("第" + (i + 1) + "行记录的楼宇名称为空。");
			}

			// 设置层数
			if (null != list.get(i)[4] && !"".equals(list.get(i)[4])) {
				try {
					house.setLayer(Integer.parseInt(list.get(i)[4].toString()));
				} catch (Exception e) {
					throw new RuntimeException("第" + (i + 1)
							+ "行记录的所在层数不正确，请重新输入。");
				}
			}
			// 设置层数
			if (null != list.get(i)[24] && !"".equals(list.get(i)[24])) {
				try {
					house.setLayercount(Integer.parseInt(list.get(i)[24].toString()));
				} catch (Exception e) {
					throw new RuntimeException("第" + (i + 1)
							+ "行记录的总层数不正确，请重新输入。");
				}
			}

			// 单元
			if (list.get(i)[5] != null || !"".equals(list.get(i)[5])) {
				house.setCell(list.get(i)[5]);
			}
		//	else {
		//		throw new RuntimeException("第" + (i + 1) + "行记录的单元名称为空。");
		//	}

			// 房间名称编号
			if (list.get(i)[6] == null || "".equals(list.get(i)[6]))
				throw new RuntimeException("第" + (i + 1) + "行记录的房间名称为空。");
			else {
				String houseName = list.get(i)[6];
				// 去除中文
				StringBuffer sb = new StringBuffer();
				for (int ee = 0; ee < houseName.length(); ee++) {
					String tempee = houseName.substring(ee, ee + 1);
					Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");
					Matcher matcher = pattern.matcher(tempee);
					if (!matcher.matches()) {
						sb.append(tempee);
					}
				}
				houseName = sb.toString();
				while (houseName.length() < 4) {
					houseName = "0" + houseName;
				}
				house.setId(house.getEdificeId() +"-"+houseName);
				house.setHouseName(houseName);
			}

			// 房间地址
			house.setHouseAddress(house.getAreaName() + house.getEdificeName()
					+ house.getHouseName());

			// 房屋类型
			house.setHouseType(list.get(i)[7]);

			// 收费面积
			if (list.get(i)[8] == null || "".equals(list.get(i)[8]))
				throw new RuntimeException("第" + (i + 1) + "行记录的收费面积为空。");
			else {
				try {
					house.setBuildnum(Float.valueOf(list.get(i)[8]));
				} catch (Exception e) {
					throw new RuntimeException("第" + (i + 1) + "行记录的收费面积格式不正确。");
				}
			}

			// 公摊面积
			if (list.get(i)[9] != null && !"".equals(list.get(i)[9]))
			{
				try {
					house.setPoolArea(Float.valueOf(list.get(i)[9]));
				} catch (Exception e) {
					throw new RuntimeException("第" + (i + 1) + "行记录的公摊面积格式不正确。");
				}
			}

			// 楼层类型
			house.setLayerType(list.get(i)[10]);

			// 业主信息
			if (list.get(i)[11] == null || "".equals(list.get(i)[11])) {
				house.setIsSale("空置");
			} else {
				house.setIsSale("入伙");
				house.setOwnerName(list.get(i)[11]);
				house.setJob(list.get(i)[14]);
				house.setPaperNum(list.get(i)[17]);
				house.setSex(list.get(i)[12]);
				house.setAge(list.get(i)[13]);
				house.setBirthday(list.get(i)[15]);
				try{
					if("".equals(IDCardValidate(house.getPaperNum()))){
						if(house.getPaperNum().length()==18){
							String cardid = house.getPaperNum();
							String year = cardid.substring(6, 10);
							String birth = year+"-"+cardid.substring(10, 12)+"-"+cardid.substring(12, 14);
							String sex = cardid.substring(16, 17);
							if(Integer.parseInt(sex)%2==0)
								sex = "女";
							else
								sex = "男";
							Calendar cal = Calendar.getInstance();
							int nowyear = cal.get(Calendar.YEAR);
							
							house.setSex(sex);
							house.setAge(nowyear-Integer.parseInt(year)+"");
							house.setBirthday(birth);
						}
					}
				}catch (Exception e) {
					throw new RuntimeException("身份证号码有");
				}
				house.setPaperType(list.get(i)[16]);
				house.setPaperNumtwo(list.get(i)[25]);
				house.setPhone(list.get(i)[18]);
				house.setMobTel(list.get(i)[19]);
				house.setFamilymember(list.get(i)[20]);
				house.setOccupancyType(list.get(i)[21]);
				
				//入住日期
				if(list.get(i)[22]!=null&&!list.get(i)[22].equals("")){
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						java.sql.Date date = new java.sql.Date(sdf.parse(
								list.get(i)[22]).getTime());
						house.setInDate(date);
					} catch (Exception e) {
						throw new RuntimeException("第" + (i + 1)
								+ "行记录的业主入住时间格式不正确。");
					}
					house.setHousetypeSub2(list.get(i)[23]);
				}
				
			}

			house.setRemark(list.get(i)[26]);

			houseDao.save(house);
			// saveHouse(house, house.getEdificeId() + "-" +
			// house.getHouseName());

			if (i % 22 == 0) {
				houseDao.getSession().flush();
				houseDao.getSession().clear();
			}
		}

		// 保存出现过的楼栋
		Iterator iter = edMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			edificeDao.save((EdificeEO) entry.getValue());
		}
	}

	/**
	 * 取所有房间
	 * 
	 * @param page
	 * @param where
	 * @return
	 */
	public Page getListByPage(Page page, String where) {
		String areaIds = (String) Struts2Utils.getSession().getAttribute(
				"areaIds");
		return houseDao.findListByPage(page, areaIds, where);
	}

	/**
	 * 通过房间id找房间
	 * 
	 * @param id
	 * @return
	 */
	public HouseEO getHouse(String id) {
		return houseDao.get(id);
	}

	/**
	 * 通过房间areaId取所有房间
	 * 
	 * @param id
	 * @return Map houseId,HouseEO
	 */
	public Map getHouseMap(Integer areaId) {
		List<HouseEO> temp = houseDao.findHouseListByAreaId(areaId);
		Map m = new HashMap();
		for (HouseEO h : temp) {
			m.put(h.getId(), h);
		}
		return m;
	}

	/**
	 * 根据楼栋编号取房间
	 * 
	 * @param edificeUID
	 * @return
	 */
	public List getHouse(String houseId, String edificeID) {
		return houseDao.getHouseListByHouseId(houseId);
	}

	/**
	 * 根据小区id得到公摊比例
	 */
	public Float getPoolRatio(int areaId) {
		return areaDao.getPoolRatio(areaId);
	}

	/**
	 * 得到小区的总面积
	 */
	public String getSumBuildNum(String edificeId) {
		EdificeEO edificeEO = edificeDao.get(edificeId);
		return houseDao.getSumBuildNum(edificeEO.getId());
	}

	public String getSumBuilding(String param) {
		return houseDao.getSumBuilding(param);
	}
	
	/**
	 * 更新楼栋中的建筑面积
	 */
	public void updBuildNum(Float sumBuildNum, String edificeId) {
		edificeDao.updateBuilderNum(sumBuildNum, edificeId);
	}

	/**
	 * 根据楼栋得到房屋朝向
	 */
	public String getHouseOrientation(String id) {
		EdificeEO edificeEO = edificeDao.get(id);
		return edificeEO.getHouseOrientation();
	}

	/**
	 * 保存，在新增保存时，调用HouseChargeManager设置房间的4个收费项，水和电,热水、暖气
	 * 
	 * @param entity
	 */
	public void saveHouse(HouseEO entity, String house_id) {
		if (entity.getId() == null) {
			AreaEO area = areaDao.get(entity.getAreaId());
			String areaName = area.getAreaName();

			EdificeEO edifice = edificeDao.get(entity.getEdificeId());
			String edificeName = edifice.getEdificeName();
			entity.setAreaName(areaName);
			entity.setEdificeName(edificeName);
			entity.setHouseAddress(areaName + edificeName
					+ entity.getHouseName());
			entity.setId(house_id);
			houseDao.save(entity);

		} else {
			// 设置地址
			entity.setHouseAddress(entity.getAreaName()
					+ entity.getEdificeName() + entity.getHouseName());
			houseDao.save(entity);
		}
	}

	public void delhouse(String id) {
		houseDao.delete(id);
	}

	/**
	 * 带条件查询HouseEO 返回page
	 * 
	 * @param page
	 * @param str
	 *            多参数的条件返回值
	 * @return
	 */
	public Page getHouse(Page page, String str) {
		String areaIds = (String) Struts2Utils.getSession().getAttribute(
				"areaIds");
		return houseDao.findListByPage(page, areaIds, str);
	}
	
	/**
	 * 带条件查询得到房间编号集合
	 * @param where sql条件    以and开头 
	 * @return list
	 */
	public List getHouseIdALL(String where) {
		String areaIds = (String) Struts2Utils.getSession().getAttribute(
				"areaIds");
		List ls = houseDao
				.find("select new map(id as id) from HouseEO where areaId in("+areaIds+") "+where);
		return ls;
	}

	/**
	 * 带条件查询HouseEO 返回PaginatorTag
	 * 
	 * @param int no , int i
	 * @param where
	 *            多参数的条件返回值
	 * @return
	 */
	public PaginatorTag getHousePage(String where, int no, int i) {
		String areaIds = (String) Struts2Utils.getSession().getAttribute(
				"areaIds");

		return houseimplDAO.findListByPage(areaIds, where, no, i);
	}

	/**
	 * 取小区总人数
	 * 
	 * @return
	 */
	public int getCountAreaOwners(int areaId) {
		// TODO Auto-generated method stub

		List l = houseDao.getCountHouseByAreaId(areaId,"");
		if (null != l.get(0))
			return (Integer) l.get(0);
		else
			throw new RuntimeException("小区总房间数为0");
	}

	/**
	 * 取某个小区的某栋的某单元总房间数
	 * 
	 * @param cell
	 *            单元号
	 * @param id
	 *            楼栋id
	 * @return
	 */
	public int getHouseCell(String cell, String edificeId) {
		// TODO Auto-generated method stub

		List l = houseDao.getCountByCellAedificeId(cell, edificeId);
		if (null != l.get(0))
			return (Integer) l.get(0);
		else
			throw new RuntimeException("单元总房间数为0");
	}

	/**
	 * 取小区房间总数
	 * 
	 * @param areaId
	 * @return
	 */
	public Long getCountHouse(Integer areaId) {
		
		List l = houseDao.getCountHouseByAreaId(areaId,"");

		Long d = (Long) l.get(0);

		return d;
	}

	/**
	 * 通过房间ID得到房间
	 * 
	 * @param houseUID
	 */
	public HouseEO getHouseByUID(String houseId) {
		String[] strs = houseId.split("-");
		if (strs.length == 3) {
			List<HouseEO> _ls = houseDao.getHouseListByHouseId(houseId);
			if (!_ls.isEmpty())
				return _ls.get(0);
			else
				return null;
		} else {
			return null;
		}

	}

	/**
	 * 检测houseId是否合法
	 * 
	 * @param areaId
	 * @param houseId
	 * @return true 存在 false不存在
	 */
	public boolean checkHouse(int areaId, String houseId) {
		List l = houseDao.getHouseListByAreaIdAhouseId(areaId, houseId);
		if (l.isEmpty())
			return false;
		else
			return true;
	}

	/**
	 * 通过id找到房间地址,房间面积，业主姓名，联系电话
	 * 
	 * @param houseId
	 * @return
	 */
	public Object[] getHouseAddress(String houseId) {
		List ls = houseDao
				.find("select houseAddress,buildnum,ownerName,mobTel from HouseEO where id=?",
						houseId);
		if (!ls.isEmpty())
			return (Object[]) ls.get(0);

		return null;
	}

	/**
	 * 根据条件取出房间编号
	 * 
	 * @return
	 */
	public List getAllByHouseId(String houseId) {
		String areaIds = (String) Struts2Utils.getSession().getAttribute(
				"areaIds");
		return houseDao.getAllByHouseId(areaIds, houseId);
	}

	public PaginatorTag getHouseListByPage(int no, int i, String where) {
		String areaIds = (String) Struts2Utils.getSession().getAttribute(
				"areaIds");
		return pagedQuery("from HouseEO where areaId in(" + areaIds + ") "
				+ where + " order by id", no, i);
	}

	/**
	 * 取权限下所有管理处的房间资料
	 * 
	 * @return
	 */
	public List getHouseList() {
		return houseDao.getHouseList();
	}

	/**
	 * 根据小区编号得到该小区的所有房间信息
	 * 
	 * @param areaId
	 * @return
	 */
	public List getHouseListByAreaId(int areaId, String houseId) {
		return houseDao.getHouseListByAreaId(areaId, houseId);
	}

	/**
	 * 得到业主姓名
	 * 
	 * @param id
	 * @return
	 */
	public String getOwnerName(String houseId) {

		return houseDao.findUnique("select ownerName from HouseEO where id = '"
				+ houseId + "' ");

	}

	public List<HouseEO> getList(String houseIds){
		return houseDao.find("from HouseEO where id in ("+houseIds+")");
	}
	
	/**
	 * 得到业主姓名,通过条件
	 * 
	 * @param areaId
	 * @param edificeId
	 * @param houseId
	 * @param houseType
	 * @return map(String,Object[]{houseId,ownerName})
	 */
	public Map<String, Object[]> getAllOwnerName(int areaId, String edificeId,
			String houseId, String houseType) {
		Map<String, Object[]> m = new HashMap<String, Object[]>();
		String where = "";
		if (houseId != null && !houseId.equals("") && !houseId.equals("0"))
			where += " and id='" + houseId + "'";
		else if (edificeId != null && !edificeId.equals("")
				&& !edificeId.equals("0"))
			where += " and edificeId='" + edificeId + "' ";
		else if (areaId > 0)
			where += " and areaId=" + areaId;
		else
			where = " and 1=2";

		if (!"".equals(houseType))
			where += " and houseType='" + houseType + "'";
		List l = houseDao.getHouseIdAownerNameBywhere(where);
		for (int i = 0; i < l.size(); i++) {
			Object[] temp = (Object[]) l.get(i);
			String _houseId = temp[0].toString();
			// String ownerName = temp[1].toString();
			m.put(_houseId, temp);

		}
		return m;
	}
	
	public List getHouseByEdificeId(String edificeId){
		return houseDao.find("from HouseEO where edificeId='"+edificeId+"' ");
	}

	/**
	 * 入住率统计表
	 * 
	 * @param where
	 *            住宅 商铺
	 * @return
	 */
	public List getRateList(String areaId, String houseType, String layerType) {
		// 项目部名称 项目总户数 已入住户数 未入住户数 入住率
		List<AreaEO> list = areaManager.getAreaALL();
		List retList = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			String[] str = new String[5];

			AreaEO area = (AreaEO) list.get(i);
			StringBuffer sb1 = new StringBuffer();
			if (null != areaId && !"".equals(areaId)) {
				sb1.append(" and areaId='").append(areaId).append("' ");
			}
			if (null != houseType && !"".equals(houseType)) {
				sb1.append(" and houseType='").append(houseType).append("' ");
			}
			if (null != layerType && !"".equals(layerType)) {
				sb1.append(" and layerType='").append(layerType).append("' ");
			}
			List l1 = houseDao.getCountHouseByAreaId(area.getId(),sb1.toString());
			
			if (l1.isEmpty() || l1.size() == 0)
				continue;
			List l2 = houseDao.getCountHouseByAreaId(area.getId(),
					sb1.toString()+" and isSale='入伙'");
			if (l2.isEmpty() || l2.size() == 0)
				continue;
			str[0] = area.getAreaName();
			str[1] = String.valueOf(l1.get(0).toString());
			str[2] = String.valueOf(l2.get(0).toString());
			str[3] = String.valueOf(Integer.parseInt(l1.get(0).toString()) - Integer.parseInt(l2.get(0).toString()));
			if(Float.valueOf(l1.get(0).toString())>0){
				str[4] = String.valueOf(
						ArithUtils.div(Float.valueOf(l2.get(0).toString()),Float.valueOf(l1.get(0).toString()), 2) * 100) + "%";
			}else{
				str[4] = "0%";
			}
			
			retList.add(str);
		}
		return retList;
	}
	
	
	
	
	
	
	/*********************************** 身份证验证开始 ****************************************/	
	/**
	 * 身份证号码验证 
	 * 1、号码的结构
	 * 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码，
	 * 八位数字出生日期码，三位数字顺序码和一位数字校验码。
	 * 2、地址码(前六位数） 
	 * 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。 
	 * 3、出生日期码（第七位至十四位）
	 * 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。 
	 * 4、顺序码（第十五位至十七位）
	 * 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号，
	 * 顺序码的奇数分配给男性，偶数分配给女性。 
	 * 5、校验码（第十八位数）
	 * （1）十七位数字本体码加权求和公式 S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
	 * Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
	 * 2 （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0
	 * X 9 8 7 6 5 4 3 2
	 */

	/**
	 * 功能：身份证的有效验证
	 * @param IDStr 身份证号
	 * @return 有效：返回"" 无效：返回String信息
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public static String IDCardValidate(String IDStr) throws ParseException {
		String errorInfo = "";// 记录错误信息
		String[] ValCodeArr = { "1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2" };
		String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
		String Ai = "";
		// ================ 号码的长度 15位或18位 ================
		if (IDStr.length() != 15 && IDStr.length() != 18) {
			errorInfo = "身份证号码长度应该为15位或18位。";
			return errorInfo;
		}
		// =======================(end)========================

		// ================ 数字 除最后以为都为数字 ================
		if (IDStr.length() == 18) {
			Ai = IDStr.substring(0, 17);
		} else if (IDStr.length() == 15) {
			Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
		}
		if (isNumeric(Ai) == false) {
			errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
			return errorInfo;
		}
		// =======================(end)========================

		// ================ 出生年月是否有效 ================
		String strYear = Ai.substring(6, 10);// 年份
		String strMonth = Ai.substring(10, 12);// 月份
		String strDay = Ai.substring(12, 14);// 月份
		if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
			errorInfo = "身份证生日无效。";
			return errorInfo;
		}
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
				|| (gc.getTime().getTime() - s.parse(
						strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
			errorInfo = "身份证生日不在有效范围。";
			return errorInfo;
		}
		if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
			errorInfo = "身份证月份无效";
			return errorInfo;
		}
		if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
			errorInfo = "身份证日期无效";
			return errorInfo;
		}
		// =====================(end)=====================

		// ================ 地区码时候有效 ================
		Hashtable h = GetAreaCode();
		if (h.get(Ai.substring(0, 2)) == null) {
			errorInfo = "身份证地区编码错误。";
			return errorInfo;
		}
		// ==============================================

		// ================ 判断最后一位的值 ================
		int TotalmulAiWi = 0;
		for (int i = 0; i < 17; i++) {
			TotalmulAiWi = TotalmulAiWi
					+ Integer.parseInt(String.valueOf(Ai.charAt(i)))
					* Integer.parseInt(Wi[i]);
		}
		int modValue = TotalmulAiWi % 11;
		String strVerifyCode = ValCodeArr[modValue];
		Ai = Ai + strVerifyCode;

		if (IDStr.length() == 18) {
			if (Ai.equals(IDStr) == false) {
				errorInfo = "身份证无效，不是合法的身份证号码";
				return errorInfo;
			}
		} else {
			return "";
		}
		// =====================(end)=====================
		return "";
	}

	/**
	 * 功能：设置地区编码
	 * @return Hashtable 对象
	 */
	@SuppressWarnings("unchecked")
	private static Hashtable GetAreaCode() {
		Hashtable hashtable = new Hashtable();
		hashtable.put("11", "北京");
		hashtable.put("12", "天津");
		hashtable.put("13", "河北");
		hashtable.put("14", "山西");
		hashtable.put("15", "内蒙古");
		hashtable.put("21", "辽宁");
		hashtable.put("22", "吉林");
		hashtable.put("23", "黑龙江");
		hashtable.put("31", "上海");
		hashtable.put("32", "江苏");
		hashtable.put("33", "浙江");
		hashtable.put("34", "安徽");
		hashtable.put("35", "福建");
		hashtable.put("36", "江西");
		hashtable.put("37", "山东");
		hashtable.put("41", "河南");
		hashtable.put("42", "湖北");
		hashtable.put("43", "湖南");
		hashtable.put("44", "广东");
		hashtable.put("45", "广西");
		hashtable.put("46", "海南");
		hashtable.put("50", "重庆");
		hashtable.put("51", "四川");
		hashtable.put("52", "贵州");
		hashtable.put("53", "云南");
		hashtable.put("54", "西藏");
		hashtable.put("61", "陕西");
		hashtable.put("62", "甘肃");
		hashtable.put("63", "青海");
		hashtable.put("64", "宁夏");
		hashtable.put("65", "新疆");
		hashtable.put("71", "台湾");
		hashtable.put("81", "香港");
		hashtable.put("82", "澳门");
		hashtable.put("91", "国外");
		return hashtable;
	}

	/**
	 * 功能：判断字符串是否为数字
	 * @param str
	 * @return
	 */
	private static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 功能：判断字符串是否为日期格式
	 * @param str
	 * @return
	 */
	public static boolean isDate(String strDate) {
		Pattern pattern = Pattern
				.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher m = pattern.matcher(strDate);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}
	}

	

}
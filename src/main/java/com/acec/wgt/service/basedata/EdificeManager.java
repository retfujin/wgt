package com.acec.wgt.service.basedata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.core.orm.Page;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.baseData.AreaEO;
import com.acec.wgt.models.baseData.EdificeDAO;
import com.acec.wgt.models.baseData.EdificeEO;
import com.acec.wgt.models.baseData.HouseDAO;
import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.ser.MenuTree;
import com.acec.wgt.models.ser.MenuTreeChecked;
import com.acec.wgt.models.ser.SelectVO;

/**
 * 模块的业务逻辑Facade类.
 * 
 * 使用Spring annotation定义依赖注入和事务管理.
 * 
 */
// Spring Bean的标识.
@Service
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class EdificeManager {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EdificeDAO edificeDao;
    @Autowired
    private HouseDAO houseDao;
    @Autowired
    private AreaManager areaManager;
    
    /**
     * 得到一个楼栋的所有的房间
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public List findHouseForEdifice(String edificeId) {
	return edificeDao.getHouseByEdificeId(edificeId);
    }

    /**
     * 得到除售出外的所有房间
     * 
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public List findHouseForEdificeKit(String edificeId) {
	return edificeDao.getHouseByEdificeIdKit(edificeId);
    }

    @Transactional(readOnly = true)
    public EdificeEO get(String id) {
    	return edificeDao.get(id);
    }

    /**
     * 分页得到所有楼栋
     * 
     * @param page
     * @return
     */
    @Transactional(readOnly = true)
    public Page<EdificeEO> getListByPage(Page page, String where) {
	String areaIds = (String) Struts2Utils.getSession().getAttribute("areaIds");
	return edificeDao.getEdificeListByPage(page, areaIds, where);
    }

    @Transactional(readOnly = true)
    public List<EdificeEO> getList(Integer areaId) {
	return edificeDao.getListByAreaId(areaId);
    }

    public void save(EdificeEO entity) {
	edificeDao.save(entity);
	List houses = edificeDao.getHouseByEdificeId(entity.getId());
	if (!houses.isEmpty()) {
	    for (int i = 0; i < houses.size(); i++) {
		HouseEO house = (HouseEO) houses.get(i);
		houseDao.save(house);
	    }
	}
    }

    public void del(EdificeEO entity) {
	List ls = findHouseForEdifice(entity.getId());
	if (ls.isEmpty()) {
	    edificeDao.delete(entity);
	} else {
	    throw new RuntimeException("存在归属该楼栋的房间，不能删除。请先删除房间后再试！。。。。");
	}
    }

    @Transactional(readOnly = true)
    public EdificeEO getEdificeById(String edificeId) {
	List l = edificeDao.getListEdificeByEdificeId(edificeId);
	if (l.size() > 0)
	    return (EdificeEO) l.get(0);
	else
	    return null;
    }

    /**
     * 通过小区id找所有楼栋
     * 
     * @param areaId
     * @return
     */
    @Transactional(readOnly = true)
    public List getAllEdifice(int areaId) {
	return edificeDao.getListByAreaId(areaId);
    }

    /**
     * 得到树状菜单的所有楼栋
     * 
     * @return
     */
    @Transactional(readOnly = true)
    public List getEdificeTrees() {
	List<AreaEO> ls = areaManager.getAreaALL();
	List<String[]> returnList = new ArrayList<String[]>();
	for (AreaEO area : ls) {

	    returnList.add(new String[] { "0", area.getAreaName(), "" });
	    Integer areaId = area.getId();
	    List<EdificeEO> els = getList(areaId);
	    for (EdificeEO edifice : els) {
		returnList.add(new String[] { "1", edifice.getEdificeName(),
			"edifice!choicehouse.action?edificeId=" + edifice.getId() });
	    }
	}
	return returnList;
    }
    
    
    @SuppressWarnings("unchecked")
    public List<MenuTreeChecked> getSMSTreeMenu(String pid, String checked, String p_edificeId) {
		List<MenuTreeChecked> mList = new ArrayList<MenuTreeChecked>();
		List<EdificeEO> eList = edificeDao.getList();
		
		if ("-1".equals(pid)) {// 一级菜单，固定的
			int i=0;
			for(EdificeEO e : eList){
				MenuTreeChecked menu = new MenuTreeChecked();
				menu.setId(e.getId());
				menu.setText(e.getArea().getAreaName()+e.getEdificeName());
				menu.setLeaf(false);
				menu.setCls("fold");
				menu.setCatid(e.getId());
				mList.add(menu);
				i++;
			}
		}else if (pid.equals(p_edificeId)) {
		    Map paraMap = new HashMap();
		    paraMap.put("edificeId", p_edificeId);
		    List<HouseEO> houseList = houseDao.getHouseList(p_edificeId);
		    List<SelectVO> voList = new ArrayList<SelectVO>();
		    for(HouseEO h : houseList){
		    	SelectVO s=new SelectVO();
		    	s.setId(h.getId());
		    	s.setName(h.getId());
		    	voList.add(s);
		    }		    
		    int j = 0;
		    for (SelectVO vo : voList) {
				MenuTreeChecked menu = new MenuTreeChecked();
				menu.setCatid(vo.getId());
				menu.setCatName(vo.getId());
				menu.setLeaf(true);
				menu.setCls("file");
				if ("true".equals(checked))
				    menu.setChecked(true);
				menu.setId(vo.getId());
				menu.setText(vo.getName());
				menu.setLeafType("leaf");
				menu.setUrl("");
				mList.add(menu);
				j++;
		    }
		}
		return mList;
    }
}
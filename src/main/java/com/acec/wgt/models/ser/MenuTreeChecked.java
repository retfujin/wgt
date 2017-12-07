package com.acec.wgt.models.ser;

import java.util.ArrayList;
import java.util.List;

/**
 * 页面显示构建ext
 * @author Administrator 
 */
public final class MenuTreeChecked
{
	private String id;
	private String text;
	private String iconCls;
	private boolean leaf;
	private String leafType;
	private String cls;	
	private String description;
	private String hrefTarget;
	private String url;
	private boolean expanded; 
	private String uiProvider;	
	private List<MenuTreeChecked> children;
	private boolean checked;
	
	private String catid="";
	private String catName="";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getLeafType() {
		return leafType;
	}
	public void setLeafType(String leafType) {
		this.leafType = leafType;
	}
	public String getCls() {
		return cls;
	}
	public void setCls(String cls) {
		this.cls = cls;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHrefTarget() {
		return hrefTarget;
	}
	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isExpanded() {
		return expanded;
	}
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
	public String getUiProvider() {
		return uiProvider;
	}
	public void setUiProvider(String uiProvider) {
		this.uiProvider = uiProvider;
	}
	public List<MenuTreeChecked> getChildren() {
		return children;
	}
	public void setChildren(List<MenuTreeChecked> children) {
		this.children = children;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public String getCatid() {
		return catid;
	}
	public void setCatid(String catid) {
		this.catid = catid;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	
	
	
}

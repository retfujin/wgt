package com.acec.wgt.models.ser;

import java.util.List;

/**
 * 页面显示构建ext
 * @author Administrator 
 */
public final class MenuTree
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
	private List<MenuTree> children;
	
	
	private String catid="";
	private String catName="";

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}

	public String getCatid() {
		return catid;
	}

	public void setCatid(String catid) {
		this.catid = catid;
	}
	
	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the text
	 */
	public String getText()
	{
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text)
	{
		this.text = text;
	}

	/**
	 * @return the leaf
	 */
	public boolean isLeaf()
	{
		return leaf;
	}

	/**
	 * @param leaf
	 *            the leaf to set
	 */
	public void setLeaf(boolean leaf)
	{
		this.leaf = leaf;
	}

	/**
	 * @return the cls
	 */
	public String getCls()
	{
		return cls;
	}

	/**
	 * @param cls
	 *            the cls to set
	 */
	public void setCls(String cls)
	{
		this.cls = cls;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the hrefTarget
	 */
	public String getHrefTarget()
	{
		return hrefTarget;
	}

	/**
	 * @param hrefTarget
	 *            the hrefTarget to set
	 */
	public void setHrefTarget(String hrefTarget)
	{
		this.hrefTarget = hrefTarget;
	}

	/**
	 * @return the url
	 */
	public String getUrl()
	{
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url)
	{
		this.url = url;
	}

	/**
	 * @return the children
	 */
	public List<MenuTree> getChildren()
	{
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<MenuTree> children)
	{
		this.children = children;
	}

	/**
	 * @return the expanded
	 */
	public boolean isExpanded() {
		return expanded;
	}

	/**
	 * @param expanded the expanded to set
	 */
	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	/**
	 * @return the iconCls
	 */
	public String getIconCls() {
		return iconCls;
	}

	/**
	 * @param iconCls the iconCls to set
	 */
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	/**
	 * @return the uiProvider
	 */
	public String getUiProvider() {
		return uiProvider;
	}

	/**
	 * @param uiProvider the uiProvider to set
	 */
	public void setUiProvider(String uiProvider) {
		this.uiProvider = uiProvider;
	}

	public String getLeafType() {
		return leafType;
	}

	public void setLeafType(String leafType) {
		this.leafType = leafType;
	}

	
}

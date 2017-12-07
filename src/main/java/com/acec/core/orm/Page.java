package com.acec.core.orm;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装.
 * 注意所有序号从1开始.
 * 
 * @param <T> Page中记录的类型.
 * 
 * @author 
 */
public class Page<T> {
	//-- 公共变量 --//
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	//-- 分页参数 --//
	protected int pageNo = 1;
	protected int pageSize = 1;
	protected String orderBy = null;
	protected String order = null;
	protected boolean autoCount = true;

	//-- 返回结果 --//
	protected List<T> result = Collections.emptyList();
	protected long totalCount = -1;

	
	 String strUnit ="条";//计数单位
	 boolean showTotal = true;//是否显示总数量
	 boolean showAllPages = true;//是否显示总页码
	 
	 String url = "";//转向的地址
	 
	 


	public void setShowTotal(boolean showTotal) {
		this.showTotal = showTotal;
	}
	public void setShowAllPages(boolean showAllPages) {
		this.showAllPages = showAllPages;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	//-- 构造函数 --//
	public Page() {
	}

	public Page(int pageSize) {
		this.pageSize = pageSize;
	}

	//-- 访问查询参数函数 --//
	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	public Page<T> pageNo(final int thePageNo) {
		setPageNo(thePageNo);
		return this;
	}

	/**
	 * 获得每页的记录数量,默认为1.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量,低于1时自动调整为1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	public Page<T> pageSize(final int thePageSize) {
		setPageSize(thePageSize);
		return this;
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 */
	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	/**
	 * 获得排序字段,无默认值.多个排序字段时用','分隔.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序字段,多个排序字段时用','分隔.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	public Page<T> orderBy(final String theOrderBy) {
		setOrderBy(theOrderBy);
		return this;
	}

	/**
	 * 获得排序方向.
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 设置排序方式向.
	 * 
	 * @param order 可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) {
		//检查order字符串的合法值
		String[] orders = StringUtils.split(StringUtils.lowerCase(order), ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr) && !StringUtils.equals(ASC, orderStr))
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
		}

		this.order = StringUtils.lowerCase(order);
	}

	public Page<T> order(final String theOrder) {
		setOrder(theOrder);
		return this;
	}

	/**
	 * 是否已设置排序字段,无默认值.
	 */
	public boolean isOrderBySetted() {
		return (StringUtils.isNotBlank(orderBy) && StringUtils.isNotBlank(order));
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数, 默认为false.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	public Page<T> autoCount(final boolean theAutoCount) {
		setAutoCount(theAutoCount);
		return this;
	}

	//-- 访问查询结果函数 --//

	/**
	 * 取得页内的记录列表.
	 */
	public List<T> getResult() {
		return result;
	}

	/**
	 * 设置页内的记录列表.
	 */
	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * 取得总记录数, 默认值为-1.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	/**
	 * 设置总记录数.
	 */
	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */
	public long getTotalPages() {
		if (totalCount < 0)
			return -1;

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号, 序号从1开始.
	 * 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext())
			return pageNo + 1;
		else
			return pageNo;
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始.
	 * 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre())
			return pageNo - 1;
		else
			return pageNo;
	}
	
	 public String getShowPage(){
	        long n = 0;
	        StringBuffer buf = new StringBuffer();
	        String strUrl;
	        n = getTotalPages();
	        buf.append("<table align='center'><tr><td>");
	        if (showTotal == true)
	            buf.append("共 <b>" + totalCount + "</b> " + strUnit
	                    + "  ");
	        strUrl = JoinChar(url);
	        if (pageNo < 2) {
	            buf.append("首页 上一页 ");
	        } else {
	            buf.append("<a href='" + strUrl + "page.pageNo=1' title='首页'>首页</a> ");
	            buf.append("<a href='" + strUrl + "page.pageNo=" + (pageNo - 1)
	                    + "' title='上一页'>上一页</a> ");
	        }
	        if (n - pageNo < 1)
	            buf.append("下一页 尾页");
	        else {
	            buf.append("<a href='" + strUrl + "page.pageNo=" + (pageNo + 1)
	                    + "' title='下一页'>下一页</a> ");
	            buf.append("<a href='" + strUrl + "page.pageNo=" + n + "' title='尾页'>尾页</a>");
	        }
	        buf.append(" 页次：<strong><font color=red>" + pageNo
	                + "</font>/" + n + "</strong>页 ");
	        buf.append(" <b>" + pageSize + "</b>" + strUnit + "/页");
	        if (showAllPages == true) {
	            buf
	            .append(" 转到：<select name='page.pageNo' size='1' onchange=\"javascript:window.location='"
	                    + strUrl
	                    + "page.pageNo="
	                    + "'+this.options[this.selectedIndex].value;\">");
	            for (int i = 1; i <= n; i++) {
	                buf.append("<option value='" + i + "'");
	                if(pageNo == i)
	                    buf.append(" selected ");
	                buf.append(">第" + i + "页</option>");
	            }
	            buf.append("</select>");
	        }
	        buf.append("</td></tr></table>");
	        return (buf.toString());
	    }
	 
	  /**
	     *  向地址中加入 ? 或 &
	     * @param strUrl
	     *            ----网址.
	     * @return 加了 ? 或 & 的网址.
	     */
	    protected String JoinChar(String strUrl) {
	        String result = "";
	        if (strUrl.equals("") || strUrl.length() <= 0) {
	            return result;
	        }
	        if (strUrl.indexOf("?") < strUrl.length()) {
	            if (strUrl.indexOf("?") > -1) {
	                if (strUrl.indexOf("&") < strUrl.length()) {
	                    result = strUrl + "&";
	                } else {
	                    result = strUrl;
	                }
	            } else {
	                result = strUrl + "?";
	            }
	        } else {
	            result = strUrl;
	        }
	        return result;
	    }
}

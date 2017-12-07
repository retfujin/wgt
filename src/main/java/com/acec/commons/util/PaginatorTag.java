package com.acec.commons.util;

import java.util.ArrayList;
import java.util.List;
public class PaginatorTag{
 //   protected Log log = LogFactory.getLog(this.getClass());
//以下是一标签中的一些属性，后面有较详细的介绍
    int currentPage = 1;//当前页码
    String url = "";//转向的地址
    long totalSize = 0;//总的记录数
    int perPage = 20;//每页显示的记录数目
    boolean showTotal = true;//是否显示总数量
    boolean showAllPages = false;//是否显示总页码
    String strUnit ="";//计数单位
    
    private List data = new ArrayList(); // 当前页中存放的记录,类型一般为List
    
    private List<Object[]> list;
    /**
     * 默认构造器
     */
    public PaginatorTag(){}
    
    
    public PaginatorTag(int startIndex, long totalCount, int pageSize, List list) {
    	setCurrentPage(startIndex);
    	setTotalPut(totalCount);
		setMaxPerPage(pageSize);
		setShowTotal(true);
		setShowAllPages(true);
		setStrUnit("条");
		setData(list);
		setList(list);
	}
 
	//得到当前页码
    public int getCurrentPage() {
        return currentPage;
    }
    //设置当前页码
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    //得到每页显示记录的数目
    public int getMaxPerPage() {
        return perPage;
    }
    //设置每页显示的记录数目
    public void setMaxPerPage(int perPage) {
        this.perPage = perPage;
    }
    //判断是否显示总的页码数目
    public boolean isShowAllPages() {
        return showAllPages;
    }
    //设置是否显示总的页码数目
    public void setShowAllPages(boolean showAllPages) {
        this.showAllPages = showAllPages;
    }
    //判断是否显示总的记录数目
    public boolean isShowTotal() {
        return showTotal;
    }
    //设置是否显示总的记录数目
    public void setShowTotal(boolean showTotal) {
        this.showTotal = showTotal;
    }
    //得到计数单位
    public String getStrUnit() {
        return strUnit;
    }
    //设置计数单位
    public void setStrUnit(String strUnit) {
        this.strUnit = strUnit;
    }
    //得到总的记录数目
    public long getTotalPut() {
        return totalSize;
    }
    //设置总的记录数目
    public void setTotalPut(long totalSize) {
        this.totalSize = totalSize;
    }
    //得到转向的链接地址
    public String getUrl() {
        return url;
    }
    //设置链接地址
    public void setUrl(String url) {
        this.url = url;
    }
	/**
	 * 获取任一页第一条数据在数据集的位置.
	 *
	 * @param pageNo   从1开始的页号
	 * @param pageSize 每页记录条数
	 * @return 该页第一条数据
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}
    
    
    /**
     * 作 用：显示“上一页 下一页”等信息
     *
     * @param url
     *            ----链接地址
     * @ param totalSize
     *            ----总数量
     * @ param perPage
     *            ----每页数量
     * @param showTotal
     *            ----是否显示总数量
     * @param showAllPages
     *            ---是否用下拉列表显示所有页面以供跳转。有某些页面不能使用，否则会出现JS错误。
     * @param strUnit
     *            ----计数单位
     * @return .
     * @ throws IOException
     */
    public String showPage(int currentPage,String url, int totalSize, int perPage,
            boolean showTotal, boolean showAllPages, String strUnit){
        int n = 0;
        StringBuffer buf = new StringBuffer();
        String strUrl;
        n = (totalSize + perPage -1) / perPage;
        buf.append("<table align='center'><tr><td>");
        if (showTotal == true)
            buf.append("共 <b>" + totalSize + "</b> " + strUnit
                    + "  ");
        strUrl = JoinChar(url);
        if (currentPage < 2) {
            buf.append("首页 上一页 ");
        } else {
            buf.append("<a href='" + strUrl + "pageNo=1' title='首页'>首页</a> ");
            buf.append("<a href='" + strUrl + "pageNo=" + (currentPage - 1)
                    + "' title='上一页'>上一页</a> ");
        }
        if (n - currentPage < 1)
            buf.append("下一页 尾页");
        else {
            buf.append("<a href='" + strUrl + "pageNo=" + (currentPage + 1)
                    + "' title='下一页'>下一页</a> ");
            buf.append("<a href='" + strUrl + "pageNo=" + n + "' title='尾页'>尾页</a>");
        }
        buf.append(" 页次：<strong><font color=red>" + currentPage
                + "</font>/" + n + "</strong>页 ");
        buf.append(" <b>" + perPage + "</b>" + strUnit + "/页");
        if (showAllPages == true) {
            buf
                    .append(" 转到：<select name='page' size='1' onchange=\\\"javascript:window.location='"
                            + strUrl
                            + "pageNo="
                            + "'+this.options[this.selectedIndex].value;\\\">");
            for (int i = 1; i <= n; i++) {
                buf.append("<option value='" + i + "'");
                if(currentPage == i)
                    buf.append(" selected ");
                buf.append(">第" + i + "页</option>");
            }
            buf.append("</select>");
        }
        buf.append("</td></tr></table>");
        return (buf.toString());
    }
    
    public String showPage(){
        long n = 0;
        StringBuffer buf = new StringBuffer();
        String strUrl;
        n = (totalSize + perPage -1) / perPage;
        buf.append("<table align='center'><tr><td>");
        if (showTotal == true)
            buf.append("共 <b>" + totalSize + "</b> " + strUnit
                    + "  ");
        strUrl = JoinChar(url);
        if (currentPage < 2) {
            buf.append("首页 上一页 ");
        } else {
            buf.append("<a href='" + strUrl + "pageNo=1' title='首页'>首页</a> ");
            buf.append("<a href='" + strUrl + "pageNo=" + (currentPage - 1)
                    + "' title='上一页'>上一页</a> ");
        }
        if (n - currentPage < 1)
            buf.append("下一页 尾页");
        else {
            buf.append("<a href='" + strUrl + "pageNo=" + (currentPage + 1)
                    + "' title='下一页'>下一页</a> ");
            buf.append("<a href='" + strUrl + "pageNo=" + n + "' title='尾页'>尾页</a>");
        }
        buf.append(" 页次：<strong><font color=red>" + currentPage
                + "</font>/" + n + "</strong>页 ");
        buf.append(" <b>" + perPage + "</b>" + strUnit + "/页");
        if (showAllPages == true) {
            buf
            .append(" 转到：<select name='page' size='1' onchange=\\\"javascript:window.location='"
                    + strUrl
                    + "pageNo="
                    + "'+this.options[this.selectedIndex].value;\\\">");
            for (int i = 1; i <= n; i++) {
                buf.append("<option value='" + i + "'");
                if(currentPage == i)
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
	public List getData() {
		return data;
	}
	public void setData(List data) {
		this.data = data;
	}

	public List<Object[]> getList() {
		return list;
	}

	public void setList(List<Object[]> list) {
		this.list = list;
	}
	


}
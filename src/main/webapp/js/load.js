//项目页面加载完要执行此函数以初始化一些控件的事件及样式及工具条信息等等
/**
Writer:jiangyifeng (HT02154)
Date:2005年7月12日
Remark:This script is extendedable.
*/
function loadAll(){
	try{
		loopTDBackgroundColor("edittable","td1","td2");
		loopTRBackgroundColor("chosetable","tr1","tr2","nowrap","td");
		setAllButtonStyle('hand');//把按钮的鼠标over设为手型
		setClassNoOverwrite("select","selection");//CSS中有selection的风格
       	setClassNoOverwrite("password","smallInput");//
		setClassNoOverwrite("textarea","textarea");//
		setAttributeNodeClassNoOverwrite("input","text","smallInput");
		setAttributeNodeClassNoOverwrite("input","password","smallInput");
		setAttributeNodeClassNoOverwrite("input","submit","submit");
		setAttributeNodeClassNoOverwrite("input","reset","submit");
   		setAttributeNodeClassNoOverwrite("input","button","submit");
		setAttributeNodeClassNoOverwrite("input","checkbox","checkBox");
		setAttributeNodeClassNoOverwrite("input","radio","radio");
//		setClassNoOverwriteByElementId("submit",'submit');
//		setClassNoOverwriteByElementId("reset",'reset');
//		setClassNoOverwriteByElementId("back",'back');
//		setClassNoOverwriteByElementId("search",'search');
//		setAttributeNoOverwrite('submit','value','提交');
//		setAttributeNoOverwrite('reset','value','重置');
//		setAttributeNoOverwrite('back','value','返回');
//		setAttributeNoOverwrite('search','value','搜索');
		setWindowTitle("企业代理服务器--中国移动");
		clearAllMargin(window.document.body);//清除所有边距
}
catch(e){
	//alert("load script error!");
	//alert(e);
	//alert(e.getMessage());
	}
	
}
 window.attachEvent("onload",loadAll);


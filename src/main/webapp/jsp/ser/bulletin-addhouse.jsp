<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<jsp:include page="../extcomm.jsp"></jsp:include>
<html>
<head><meta http-equiv="x-ua-compatible" content="ie=7" />
	<%@ include file="/commons/meta.jsp"%>
	<title>业主发布</title>
	<script type="text/javascript" src="/js/jquery-1.3.2.min.js"></script>
	<script type="text/javascript" src="/js/jquery.form.js"></script>
</head>
<body> 
<script type="text/javascript">
var checkRight = parent.checkRight;
Ext.BLANK_IMAGE_URL = '../../ExtJS/resources/images/default/s.gif';
var treepanel;
Ext.onReady(function(){
	// shorthand
	var Tree = Ext.tree;
	
	var treeLoader = new Tree.TreeLoader({
		dataUrl:'/../basedata/edifice!getSMSTreeMenu.action'
	});
	
	treeLoader.on("beforeload", function(treeLoader, node) {
	    treeLoader.baseParams.checked = node.attributes.checked;
	                     },treeLoader);
	
	
	treepanel = new Tree.TreePanel({
		el:'tree-div',
		useArrows:true,
		autoScroll:true,
		animate:true,
		enableDD:true,
		containerScroll: true,
		loader: treeLoader
	});
	
	
	 
	
	treepanel.on('checkchange',function(node,checked){
	    //treecheck(node,checked);//调用下列方法
	    node.expand();
	    node.attributes.checked = checked;
	    node.eachChild(function(child) {  
		    child.ui.toggleCheck(checked);    
		    child.attributes.checked = checked;    
		    child.fireEvent('checkchange', child, checked);    
	    });    	
	},treepanel);
	

	////树形控件的选择
	 var treecheck=function(node,checked){
	    // node.expand();
	     if(node.hasChildNodes()){
	       ChildWithParent(node,checked);
	     }
	     //如果是选中状态,则选中所有父节点
	     if(checked){
	       ParentWithChild1(node,checked);   
	     }
	     //如果不选中
	     else{
	        ParentWithChild2(node,checked);
	     }
	 }
	 //选择结点，影响所有子节点
	 var ChildWithParent=function(node,bool){
	  if(node){
	   node.attributes.checked = bool;
	   if(node.hasChildNodes()){
		    node.eachChild(function(child){
			     child.ui.toggleCheck(bool);
			     child.attributes.checked = bool;
			     ChildWithParent(child,bool);
		    });
	   }
	  }
	 }
	 //如果是选中状态，则选中父节点---递归
	 var ParentWithChild1=function(node,bool){   
	  if(node.parentNode.getUI().checkbox){
	   var parent=node.parentNode;
	   parent.attributes.checked=bool;
	   parent.getUI().checkbox.checked=bool;
	   ParentWithChild1(parent,bool);
	  }
	 }
	 //如果是不选中状态，则遍历父节点，如果该父节点下的所有节点都为不选中，则取消选中
	 var ParentWithChild2=function(node,bool){
	  if(node.parentNode.getUI().checkbox){
	   var parent=node.parentNode;    
	   var temp=false;
	   parent.eachChild(function(child){
		    if(child.getUI().checkbox.checked==true){
		     temp=true;
		     return false;
		    }    
	   });   
	   if(!temp){
	    parent.attributes.checked=bool;
	    parent.getUI().checkbox.checked=bool;
	    temp=false;
	    ParentWithChild2(parent,bool);
	   }
	  }
	 }
	//set the root node
		var root = new Tree.AsyncTreeNode({
			text: '所有业主',
			draggable:false,
		//	checked :false,
			id:'ROOT'
		});
		treepanel.setRootNode(root);
	// render the tree
	treepanel.render();
	treepanel.expand();
});

var nodevalue="";//定义一个全局变量，保存节点的id或值
function getAllRoot(value){
	var rootNode=value.getRootNode();//获取根节点
	//nodevalue+=rootNode.id;//获取跟节点的值
	findchildnode(rootNode); //开始递归
	nodevalue= nodevalue.substr(0, nodevalue.length - 1);
	//alert(nodevalue);
	return nodevalue;
}
//获取所有的子节点   
function findchildnode(node){
	var childnodes = node.childNodes;
	var nd;
	for(var i=0;i<childnodes.length;i++){ //从节点中取出子节点依次遍历
		nd = childnodes[i];
		if(nd.attributes.checked==true)
			nodevalue += nd.id + ",";
		if(nd.hasChildNodes()){ //判断子节点下是否存在子节点
			findchildnode(nd); //如果存在子节点 递归
		}   
	}
}
</script>
</head>
<body>
	<div id="tree-div" style="overflow:auto; height:650px;width:250px;border:1px solid #c3daf9;"></div>
	<div id="apDiv1" style="position:absolute; width:505px; height:321px; z-index:1; left: 256px; top: 6px;">
        <form id="myForm" name="myForm" method="post" onsubmit="checkData()">
         <table width="100%" border="0" align="center"
			class="table_general form_show">
          <tr>
              <td class="tdright">说明：<br/>
              		1.如果只勾选楼栋，则表示发送整个楼栋的业主短信，<br/>
              		2.如果同时勾选了房间，则表示只发送勾选的房间的业主短信。<br/>
              		<br/>
              		<br/>
              </td>
             </tr>
            <tr>
              <td class="tdright">发送内容:</td>
             </tr>
             <tr>
              <td>
              	<s:textarea id="content" name="entity.content" cols="50" rows="10" theme="simple"/>
                <input type="text" name="subname" value="【长江物业】" readonly="readonly" size="10">
              </td>
            </tr> 
            <tr align="center">
              <td>
                <input type="hidden" name=destParentList id="destParentList">
                <input type="submit" id="signup"  name="submit1" value="发送" class="a">
              </td>
              
            </tr>
          </table>
      </form>
</div>


</body>
<script type="text/javascript">
$(document).ready(
	function() {
	 var options = { 
			  beforeSubmit:  showRequest,
			  success:       showResponse,
			  url:       "bulletin!saveHouse.action",
			  dataType:  'json' 
		};
		$('#myForm').ajaxForm(options); 
	}
	
);
function showRequest(formData, jqForm, options) {
	//添加js验证
	//简单的不能为空验证
	if(document.getElementById("content").value=='')
	{
		alert("发送内容不能为空");
		return false;
	}
	document.getElementById("signup").disabled=true;
    return true;
}
function showResponse(responseText, statusText, xhr, $form)  {
	if(responseText.success){
		alert(responseText.msg);
		window.returnValue = "success";
		window.close();
		}else{
			if (responseText.msg == "10"){
				window.showModalDialog("sms-alert.jsp", "提示", "dialogWidth=800px;dialogHeight=480px");
			}else{
				alert("保存失败"+responseText.msg);
			}
		}
		document.getElementById("signup").disabled=false;
} 

function checkData(){
	nodevalue = "";
	getAllRoot(treepanel);
	myForm.destParentList.value = nodevalue;
//	alert(nodevalue);
	
}

</script>
</html>


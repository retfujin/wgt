<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<%@ include file="/commons/meta.jsp" %>
<base target="_self"></base>
<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var frm = document.getElementById("ff");
					if(frm.name.value=='')
					{
						alert("权限组名不能为空");
						return false;
					}
					var objs = frm.getElementsByTagName("input");
					var bl=false;
					for(var i=0; i<objs.length; i++) {
					    if(objs[i].type.toLowerCase() == "checkbox" ){
					      if(objs[i].checked ==true){
					      	bl=true;
					      	
					      	break;
					      }
					    }
					  }
					if(bl==false){
						alert("请给权限组分配权限");
						return false;
					}
					return true;
				},
				success:function(data){
					var responseText =  eval('(' + data + ')');
					if(responseText.success){
						alert('保存成功');
						window.returnValue = "success";
						window.close();
					}else{
						alert(responseText.msg);
					}
				}
			});

		});
</script>
<script type="text/javascript">

function getResource(ids, parentId, types){     
    var resource = document.getElementById(ids);   
    if(resource.checked==true){   
        fresourceChecked(resource.id,resource.getAttribute('pId'));   
        sresourceChecked(resource.id,resource.getAttribute('pId'));                    
        resource.checked=true;   
    }else{   
          unResourceChecked(resource.id,resource.getAttribute('pId'));     
          resource.checked=false;      
    }   
}    
function fresourceChecked(ids,parentId){   
   var resource = document.getElementsByName('entity.models.id');   
   for(var i = 0;i<resource.length;i++){   
       if(resource[i].id==parentId){   
          resource[i].checked = true;    
          fresourceChecked(resource[i].id,resource[i].getAttribute('pId'));   
       }   
   }     
}   
   
function sresourceChecked(ids,parentId){   
   var resource = document.getElementsByName('entity.models.id');   
   for(var i = 0;i<resource.length;i++){   
       if(resource[i].getAttribute('pId')==ids){   
          resource[i].checked = true;    
          sresourceChecked(resource[i].id,resource[i].getAttribute('pId'));   
       }   
   }     
}   
    
function unResourceChecked(ids,parentId){   
     var resource = document.getElementsByName('entity.models.id');   
     for(var i = 0;i<resource.length;i++){   
       if(resource[i].getAttribute('pId')==ids){   
          resource[i].checked = false;   
          unResourceChecked(resource[i].id,resource[i].getAttribute('pId'));   
       }   
   }           
}    
 

</script>
<body>
  
  <form id="ff" action="role!save.action" name="frmAdd"  method="post">
  <s:hidden name="entity.id"></s:hidden>
<table width="90%" border="1" align="center" cellpadding="2" cellspacing="1" class="tablegray">
	<tr>
      <td >权限组名：<s:textfield name="entity.name" id="name" size="25"  maxlength="20" theme="simple"/>
      <font color="#FF0000">*</font>
      </td>
    </tr>
       <tr>
      <td>权限组描述：<s:textarea name="entity.description" id="description" cols="50" rows="4" theme="simple"/>
        </td>
    </tr>
   
    <tr>
      <td>权限&nbsp;<font color="#FF0000">*</font></td>
      
    </tr>
   
    <s:iterator value="viewList" status="stuts">
    	<s:if test="grade==0">
    		<s:set name="r0" value="id"/>
    	<s:if test="#stuts.index>1"><% out.println("</td></tr>"); %></s:if>
    	<tr>
		      <td>  
		      <input type="checkBox" name="entity.models.id" type="0" pId="0" value="<s:property value="id"/>" id="<s:property value="#r0"/>" onclick="getResource(<s:property value="#r0"/>,0,0)" <s:if test="isZhong==1">checked</s:if>/>
		      <s:property value="name"/>
		      </td>
    	</tr>
    	<tr><td>
    	</s:if>
    	<s:elseif test="grade==1">
    		<s:set name="r1" value="id"/>
		      &nbsp;&nbsp;&nbsp;&nbsp;<input type="checkBox" name="entity.models.id" type="1" pId="<s:property value="#r0"/>" value="<s:property value="id"/>" id="<s:property value="#r1"/>" onclick="getResource(<s:property value="#r1"/>,<s:property value="#r0"/>,1)" <s:if test="isZhong==1">checked</s:if>/>
		      <s:property value="name"/>
    	</s:elseif>
    	<s:elseif test="grade==2">
    		<s:set name="r2" value="id"/>
		      [<input type="checkBox" name="entity.models.id" value="<s:property value="id"/>" type="2" pId="<s:property value="#r1"/>" id="<s:property value="#r2"/>" onclick="getResource(<s:property value="#r2"/>,<s:property value="#r1"/>,2)" <s:if test="isZhong==1">checked</s:if>/>
		      <s:property value="name"/>]
    	</s:elseif>
    </s:iterator>
   
    <tr>
      <td align="center"><input type="submit" name="submit11" value="保存" class="a">&nbsp;&nbsp;&nbsp;&nbsp;<input name="reset" type="reset" value="重填" class="a"></td>
  
    </tr>
    </table>
</form>
  </body>
</html>

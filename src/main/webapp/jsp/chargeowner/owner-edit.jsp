<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<%@ include file="/commons/meta.jsp"%>

<script type="text/javascript" src="../../easyui/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="../../easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
		$(function(){
			$('#ff').form({
				onSubmit: function(){
					var inputs=document.getElementsByTagName("input");
					var chkboxs;
					for(var i=0;i<inputs.length;i++)
					{
					    if(inputs[i].type == "checkbox")
					    {   
					        if(inputs[i].checked)
					        { 
					          inputs[i].value=1;
					        }
					    }
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
</head>
<body>



<div class="main_right fl">
   	<div class="table_area">
           <div class="table_name">
              <p>房间收费客户</p> 
           </div>
          <form id="ff"  name="form1" method="post" action="owner!saveChargeHouseById.action">
           <table class="table_border" width="99%" cellpadding="2" cellspacing="1" align="center">
       		<tr align="center">
                    <td class="td_header">序号</td>
					<td class="td_header">收费项目</td>
			    	<td class="td_header">单价</td>
			    	<td class="td_header">计算公式</td>
			    	<td class="td_header">公式说明</td>
            </tr>
            <s:iterator value="viewList" status="stuts">
            <tr>
				<td class="td_c"><input type="hidden" name="speci" id="speci" value="1"/>${stuts.index+1}</td>
				<td class="td_c">
			  		<input type="checkBox" name="viewList[${stuts.index}].isSelect" value="${isSelect}" id="isSelect" 
			  			<s:if test="isSelect==1">checked="checked"</s:if> 
			  		>
			  	</td>
			   	<td class="td_c">
			   		${chargeName}
					<input type="hidden" value="${chargeBasedataId}" name="viewList[${stuts.index}].chargeBasedataId">
					<input type="hidden" value="${houseId}" name="viewList[${stuts.index}].houseId">
					<input type="hidden" value="${isMeter}" name="viewList[${stuts.index}].isMeter">
			   	</td>
			   	<td class="td_c">
			   		${priceValue}
			   	</td>
			   	<td class="td_c">
				   		<input	 type="hidden" 	 value="${nowRecord}" name="viewList[${stuts.index}].nowRecord">
				   	
				   		<input type="hidden" value="${backRecord}" name="viewList[${stuts.index}].backRecord">
					${chargeExpression }
				</td>
				<td class="td_c">${expExplain }</td>
                </tr>	
               
              </s:iterator>
               <tr class="footer">
                        <td colspan="5">
                         <input type="hidden" name="house.id" value="${house.id}"> 
                        	<input type="submit" class="save" value="保存" name="submit111" /> 
                        </td>
                    </tr>
      		</table>
           
            </form>
           </div>
       </div>
       <!---table_area结束--->
   </div>
    
    
   
</body>
</html>

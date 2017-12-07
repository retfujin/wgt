<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<base target="_self"/>
<%@ include file="/commons/meta.jsp" %>
<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
<SCRIPT type="text/javascript" src="/js/hanx.js"></SCRIPT>
<body>


 <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>业户表更换</p> 
            </div>
            <form action="ownermeterrecord!changeMeterSave.action" name="form2" method="post">
                <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
                    <tr>
                        <td height="35" align="center">房间编号</td>
                        <td><s:textfield name="houseMeter1.house.id" readonly="true" theme="simple"/></td>                       
                    </tr>
                    <tr>
                        <td height="35" align="center">表类型</td>
                        <td><s:textfield name="houseMeter1.meterType" readonly="true" theme="simple"/></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">原表末次表数</td>
                        <td><input type="text" name="oldMeterNum" onkeypress="return inputOnlyNumber()"></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">新表期初表数</td>
                        <td><input type="text" name="houseMeter1.initNum" onkeypress="return inputOnlyNumber()"></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">新表末次表数</td>
                        <td><input type="text" name="houseMeter1.nowRecord" onkeypress="return inputOnlyNumber()"></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">更换人</td>
                        <td><input type="text" name="houseMeter1.changeName"></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">更改时间</td>
                        <td><input type="text" name="houseMeter1.changeTime" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
                    </tr>
                    <tr>
                        <td height="35" align="center">更换原因</td>
                        <td><input type="text" name="houseMeter1.changeReason"></td>
                    </tr>
                    <tr>
                        <td height="35" align="left" colspan="4">
                                                                注：带<font color="red">*</font>为必须填写
                        </td>
                    </tr>
                    <tr class="footer">
                        <td colspan="4">
                        	<input type="submit" class="save" value="保存" name="submit111" /> 
                            <input type="reset" class="reset" value="重填" />
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <!----add结束--->  
      
</body>
</html>
     
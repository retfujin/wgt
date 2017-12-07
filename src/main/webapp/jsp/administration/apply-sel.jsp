<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.acec.wgt.models.sys.SysUserEO"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<base target="_self"/>
<%@ include file="/commons/meta.jsp"%>
<body>
    <div class="add">
    	<div class="add_area">
            <div class="add_name">
               <p>应聘人员登记</p> 
            </div>
          <table class="add_table" cellpadding="2" cellspacing="1" align="center" width="99%" align="center" >
		  <tr>
		  	<td>应聘职位</td>
		  	<td colspan="3"><s:property value="entity.position"></s:property>&nbsp;</td>
		  	<td>登记日期</td>
		  	<td colspan="3"><s:property value="entity.recordtime.toString().substring(0,10)"></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td >姓名</td>
		    <td ><s:property value="entity.name"></s:property>&nbsp; </td>
		    <td >性别 </td>
		    <td ><s:property value="entity.sex" />&nbsp;</td>
		    <td >年龄 </td>
		    <td ><s:property value="entity.age" ></s:property>&nbsp;</td>
		    <td >民族 </td>
		    <td ><s:property value="entity.national" ></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td >籍贯 </td>
		    <td  colspan="3"><s:property value="entity.nativeplace"></s:property>&nbsp;</td>
		    <td >出生年月 </td>
		    <td ><s:property value="entity.brithday"></s:property>&nbsp;</td>
		    <td >期望薪金 </td>
		    <td ><s:property value="entity.salaryexpectation" ></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td >政治面貌 </td>
		    <td  colspan="2"><s:property value="entity.politicallandscape" ></s:property>&nbsp;</td>
		    <td >身高 </td>
		    <td ><s:property value="entity.height" ></s:property>&nbsp;</td>
		    <td >体重 </td>
		    <td  colspan="2"><s:property value="entity.weight" />&nbsp;</td>
		  </tr>
		  <tr>
		    <td >毕业时间 </td>
		    <td  colspan="2"><s:property value="entity.graduationtime"></s:property>&nbsp;</td>
		    <td >专业 </td>
		    <td ><s:property value="entity.professional" ></s:property>&nbsp;</td>
		    <td >工作年限 </td>
		    <td colspan="2"><s:property value="entity.workyear"></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td >最高学历 </td>
		    <td colspan="2"><s:property value="entity.education" ></s:property>&nbsp;</td>
		    <td >户口所在地 </td>
		    <td ><s:property value="entity.residence"  ></s:property>&nbsp;</td>
		    <td >婚姻状况 </td>
		    <td colspan="2"><s:property value="entity.marriage"  ></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td >家庭住址 </td>
		    <td colspan="4"><s:property value="entity.address"></s:property>&nbsp;</td>
		    <td >身份证号 </td>
		    <td colspan="2"><s:property value="entity.cardid"></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td >健康状况 </td>
		    <td colspan="2"><s:property value="entity.health" ></s:property>&nbsp;</td>
		    <td >有无病史 </td>
		    <td colspan="2"><s:property value="entity.medical"></s:property>&nbsp;</td>
		    <td >联系电话 </td>
		    <td ><s:property value="entity.phone"></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td rowspan="4">教育<br>与<br>培训 </td>
		    <td >起止时间 </td>
		    <td colspan="2">学校或其它教育机构 </td>
		    <td >专业 </td>
		    <td >学历/证书 </td>
		    <td colspan="2">证明人 </td>
		  </tr>
		  <tr>
		    <td ><s:property value="entity.educationtimeone"  ></s:property>&nbsp;</td>
		    <td colspan="2"><s:property value="entity.educationschoolone" ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.educationprofessionalone" ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.educationrecordone" ></s:property>&nbsp;</td>
		    <td colspan="2"><s:property value="entity.educationproveone"></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td ><s:property value="entity.educationtimetwo" ></s:property>&nbsp;</td>
		    <td colspan="2"><s:property value="entity.educationschooltwo" ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.educationprofessionaltwo"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.educationrecordtwo"  ></s:property>&nbsp;</td>
		    <td colspan="2"><s:property value="entity.educationprovetwo"  ></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td ><s:property value="entity.educationtimethree"  ></s:property>&nbsp;</td>
		    <td colspan="2"><s:property value="entity.educationschoolthree"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.educationprofessionalthree"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.educationrecordthree"  ></s:property>&nbsp;</td>
		    <td colspan="2"><s:property value="entity.educationprovethree"  ></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td rowspan="4">主要<br>工作<br>经历 </td>
		    <td >起止时间 </td>
		    <td >工作单位 </td>
		    <td >职务 </td>
		    <td >月薪 </td>
		    <td >离职原因 </td>
		    <td >证明人 </td>
		    <td >联系电话 </td>
		  </tr>
		  <tr>
		    <td ><s:property value="entity.worktimeone"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workunitone"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workpositionone"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.worksalaryone"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workdepartureone"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workrecordone"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workphoneone"  ></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td ><s:property value="entity.worktimetwo"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workunittwo"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workpositiontwo"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.worksalarytwo"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workdeparturetwo"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workrecordtwo"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workphonetwo"  ></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td ><s:property value="entity.worktimethree"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workunitthree"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workpositionthree"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.worksalarythree"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workdeparturethree"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workrecordthree"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.workphonethree"  ></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td >个人<br>能力<br>描述 </td>
		    <td colspan="7">
		    <s:property  value="entity.remark" />&nbsp;</td>
		  </tr>
		  <tr>
		    <td rowspan="4">家庭<br>成员<br>情况 </td>
		    <td >姓名</td>
		    <td >与本人关系</td>
		    <td colspan="3">工作单位及职务</td>
		    <td colspan="2">联系电话 </td>
		  </tr>
		  <tr>
		    <td ><s:property value="entity.familynameone"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.familyrelationshipone"  ></s:property>&nbsp;</td>
		    <td colspan="3"><s:property value="entity.familyunitone"  ></s:property>&nbsp;</td>
		    <td colspan="2"><s:property value="entity.familyphoneone"  ></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td ><s:property value="entity.familynametwo"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.familyrelationshiptwo" ></s:property>&nbsp;</td>
		    <td colspan="3"><s:property value="entity.familyunittwo"  ></s:property>&nbsp;</td>
		    <td colspan="2"><s:property value="entity.familyphonetwo"  ></s:property>&nbsp;</td>
		  </tr>
		  <tr>
		    <td ><s:property value="entity.familynamethree"  ></s:property>&nbsp;</td>
		    <td ><s:property value="entity.familyrelationshipthree"  ></s:property>&nbsp;</td>
		    <td colspan="3"><s:property value="entity.familyunitthree"  ></s:property>&nbsp;</td>
		    <td colspan="2"><s:property value="entity.familyphonethree"  ></s:property>&nbsp;</td>
		  </tr> 
		  <tr>
		    <td >面试结果 </td>
		    <td ><s:property value="entity.consequence" ></s:property>&nbsp;</td>
		    <td >录用时间 </td>
		    <td ><s:property value="entity.employedtime" ></s:property>&nbsp;</td>
		    <td >批准人 </td>
		    <td colspan="3"><s:property value="entity.approval" ></s:property>&nbsp;</td>
		  </tr>
		  <tr class="footer">
                        <td colspan="8">
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
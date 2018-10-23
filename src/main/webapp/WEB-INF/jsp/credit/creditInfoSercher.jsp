<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>教师失信事件查询</title>
        <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div class="mini-toolbar">
            <form id="printForm" action="<%=request.getContextPath()%>/ajax/suggest_exportSuggestRecord.do" target="printFrame">
                <table style="width: 100%;table-layout:fixed;"  >
                    <tr>
                        <td style="width:8%" align="left"> 事件编号：</td>
                        <td style="width:17%">
                            <input id="codeInput" name="code" class="mini-textbox" style="width: 100%;" onenter="onKeyEnter"/>
                        </td>
                        <td style="width: 8%" align="left">事件标题:</td>
                        <td style="width: 17%">
                            <input id="nameInput" name="title" class="mini-textbox" style="width: 100%;" onenter="onKeyEnter"/>
                        </td>
                        <td id="applyuserText" style="width:5%" align="left"><c:if test="${role == 'admin'}">&nbsp;&nbsp;创建人:</c:if></td>
                        <td style="width:17%">
                            <c:if test="${role == 'admin'}"><input id="applyuserInput" name="applyuser" class="mini-textbox" style="width: 100%;" onenter="onKeyEnter"/></c:if>
                        </td>
<!--                         <td style="width: 8%"></td> -->
                    </tr>
                    <tr>
                        <td style="width: 7%" align="left">&nbsp;&nbsp;审批状态:</td>
                        <td style="width: 17%">
                            <input id="statusInput" name="applystatus" class="mini-combobox" style="width: 100%;" textField="text" valueField="id" onenter="onKeyEnter"
                                url="<%=request.getContextPath()%>/ajax/suggest_getSuggestProcessStatusList.do" showNullItem="true" nullItemText=""/>
                        </td>  
                        <td style="width:7%" align="left">&nbsp;&nbsp;部门：</td>
                        <td style="width:17%">
                            <input id="applybankInput" name="applybank" class="mini-treeselect" style="width: 100%;"
                            textField="jgmc" valueField="jgh" parentField="sjjg" onenter="onKeyEnter"
                            url="<%=request.getContextPath()%>/ajax/suggestInfo_selectInstitutionList.do?" allowInput="true" expandOnLoad="0" showNullItem="true" nullItemText=""/>
                        </td>
                        <td style="width: 8%">
                            <a class="mini-button mini-button-success" onclick="exportSuggestMss()" style="width:60px;">导出</a>
                        </td>
                        <td style="width: 8%">
                            <a class="mini-button mini-button-success" onclick="exportSuggestByMonth()" style="display:none;width:160px;">按月导出</a>
                        </td>
                    </tr>
                    <tr>
                      <td style="width:7%" align="left">&nbsp;&nbsp;创建时间:</td>
                        <td style="width:17%">
                            <input id="fromDateInpt" name="createDateBegin" class="mini-datepicker" style="width:100%;" onenter="onKeyEnter"/>
                        </td>
                        <td style="width:7%" align="left">&nbsp;&nbsp;--至--</td>
                        <td style="width:17%">
                            <input id="endDateInpt" name="createDateEnd" class="mini-datepicker" style="width:100%;" onenter="onKeyEnter"/>
                        </td>
                        <td style="width: 8%">
                            <a class="mini-button mini-button-success" onclick="doSearch()" style="width:60px;">查询</a>
                        </td> 
                        <td style="width: 8%">
                            <a id="add" class="mini-button mini-button-success" style="width:160px;" onclick="add()">添加事件申请</a>
                        </td>
                      
                    </tr>
                </table>
            </form>
        </div>
        <div class="mini-fit" style="margin:0px 0">
            <div id="suggestDataGrid" class="mini-datagrid"  allowAlternating="true" style="width:100%;height:100%;" allowResize="true" allowCellEdit="true"
                 url="<%=request.getContextPath()%>/ajax/suggest_suggestInfoList.do"  idField="" multiSelect="true">
                <div property="columns">  
                    <div type="indexcolumn" width="40" align="center" headerAlign="center">序号</div>
                    <div field="code" width="120" align="center" headerAlign="center" allowSort="true">事件编号</div>
                    <div field="suggestTitle" width="120" align="center" headerAlign="center" allowSort="false">事件标题</div>
                    <div field="applybankName" width="120" align="center" headerAlign="center" allowSort="false">创建部门</div>
                    <div field="applyuserName" name="applyuserName" id = "applyuserName" width="120" align="center" headerAlign="center" allowSort="false">创建人</div>
                    <div field="applyTime" width="120" align="center" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss" allowSort="false">创建时间</div>
                    <div field="approveStatusName" width="150" align="center" headerAlign="center" allowSort="false">审批状态</div>
                    <div name="active" width="150" headerAlign="center" align="center" renderer="onActionRenderer" cellStyle="padding:0;">操作</div>
                </div>
            </div>
        </div>
        <div id="exportByMonthWindow" class="mini-window" style="display:none;width:500px;height:300px;border:1px solid #e0e0e0;background:#f3f3f4;">
            <div id="exportByMonthForm" style="margin-top:60px;">
                <table style="margin-left: 10%;table-layout:fixed;width:80%">
                    <tbody>
                    <tr>
                        <td style="width: 100px;"><label>导出月份：</label></td>
                        <td>
                            <input name="suggestMonth" id="suggestMonth" required="true" class="mini-monthpicker" style="width:100%;" />
                        </td>
                    </tr>

                    </tbody>
                </table>
            </div>
            <div style="width:100%;height:5%;text-align:center;margin-top:50px;">
                <a class="mini-button" onclick="exportConfirm()" iconCls="icon-save">确认</a>
                <a class="mini-button" onclick="hideWindow('exportByMonthWindow')" iconCls="icon-no" style="margin-left:50px;">关闭</a>
            </div>
        </div>
        <iframe id="printFrame" style="display:none;"></iframe>
        <script type="text/javascript">
            mini.parse();
            var grid = mini.get("suggestDataGrid"); 
            var role = "${role}";
            var userNo = "${userNo}";
            $(document).ready(function(){
                var date = new Date();
                date.setMonth(date.getMonth()-1);
                mini.get("suggestMonth").setValue(date);
            	if(role != "admin"){
            	    //mini.get("applyuserText").hide();
            	    //mini.get("applyuserInput").hide();
            		grid.hideColumn("applyuserName");
            	}
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/suggest_initSearch.do",
                    data:{id: "<%=request.getParameter("id")%>"},
                    type: "post",
                    dataType: 'text',
                    success: function (text) {
                        var data = mini.decode(text);
                        if (data.addFlag == true) {
                            $("#add").show();
                        }
                    }
                });
                grid.load(); 
            });
            function onActionRenderer(e) {
                var grid = e.sender;
                var record = e.record;
                var user = record.applyUser;
                var status = record.applyStatus;
                var uid = record._uid;
                var s = "";
                if(status != 'processEnd' && status != "suggestionApplyEnd" && user == userNo){
	               	s = s + '<a class="Edit_Button" style="width:80px;color:grey;" href="#">查看</a>';
                }else{
	               	s = s + '<a class="Edit_Button" style="width:80px;" href="javascript:detailRow(\'' + uid + '\')">查看</a>';
                }
                if(status == "adminAccept" && userNo == user){
	               	s = s + '<a class="Edit_Button" style="width:80px;" href="javascript:recallRow(\'' + uid + '\')">撤回</a>';
                }
                return s;
            }
            function detailRow(row_uid){
                var row = grid.getRowByUID(row_uid);
                if(row){
                    var url = '<%=request.getContextPath()%>/showSuggestInfoApply.do?id='+row.id + '&suggestCode=' + row.code + '&flag=search';
                    parent.showTabForUser("suggestInfo" + row.id,"合理化建议信息详情",url);
                }else {
                    mini.alert("请选中一条记录！");
                } 
            }

            function add(){
                  var url = '<%=request.getContextPath()%>/addCreditInfoApply.do?flag=add';
                  parent.showTabForUser("creditAdd","诚信事件新增",url);
            }

            function exportSuggestByMonth() {
                var win = mini.get("exportByMonthWindow");
                win.showAtPos("center", "middle");
            }

            function exportConfirm() {
                var suggestMonth = mini.get("suggestMonth").getFormValue();
                if (suggestMonth == "") {
                    mini.alert("请选择需打印月份");
                }
                window.open("<%=request.getContextPath()%>" + "/ajax/suggest_exportByMonth.do?suggestMonth="+suggestMonth);
                hideWindow('exportByMonthWindow');
            }

            function doSearch(){
                //获取查询条件参数
                var searchFields = getSearchFields();
                searchFields.cgEnd = false;
                grid.load(searchFields);
            }

            function getSearchFields(){
                var code = mini.get("codeInput").getValue();
                var name = mini.get("nameInput").getValue();
                var applystatus = mini.get("statusInput").getValue();
                if("${role}" == "admin"){
                    var applyuser = mini.get("applyuserInput").getValue();
                }
                var applybank = mini.get("applybankInput").getValue();
                var createDateBegin = mini.get("fromDateInpt").getFormValue();
                var createDateEnd = mini.get("endDateInpt").getFormValue();

                //搜索条件对象
                var searchFields = {};

                if( code !="" ){
                    searchFields.code=code;
                }
                if( name!=""){
                    searchFields.title=name;
                }
                if( applystatus !=""){
                    searchFields.applystatus=applystatus;
                }
                if("${role}" == "admin"){
                    if( applyuser != "") {
                        searchFields.applyuser=applyuser;
                    }
                }

                if( applybank != "") {
                    searchFields.applybank=applybank;
                }
                if( createDateBegin != ""){
                    searchFields.createDateBegin=createDateBegin;
                }
                if( createDateEnd!="" ){
                    searchFields.createDateEnd=createDateEnd;
                }
                return searchFields;
            }
            function ontimeValidation(){
           	   var selectDate = mini.get("deadline").getValue();
           	   var today = new Date();
           	   if(selectDate < today){
     	      	   mini.alert("请输入正确办结时间");
     	      	   mini.get("deadline").setValue("");
           	   }
           }
            
            function recallRow(row_uid){
            	var row = grid.getRowByUID(row_uid);
            	$.ajax({
                    url: "<%=request.getContextPath()%>/ajax/suggest_recallTask.do",
                    data:{id: row.id},
                    type: "post",
                    dataType: 'text',
                    success: function (text) {
                    	if(text == "SUCCESS"){
                    		mini.alert("撤回成功!");
                    	}else if(text == "CHANGED"){
                    		mini.alert("任务已审核,撤回失败!");
                    	}else{
                    		mini.alert("撤回失败!");
                    	}
                   		grid.load(); 
                    }
                });
            }
            
            function exportSuggestMss(){
                $("#printForm").submit();
            }

            // 关闭弹出页面
            function hideWindow(winId) {
                var win = mini.get(winId);
                win.hide();
            }
            function onKeyEnter(e) {
                doSearch();
            }
        </script>
   </body>
</html>
<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>我的待办</title>
        <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet" />
    </head>
    <body>
        <div id="searchInfoForm" class="mini-toolbar" style="padding:2px;border-bottom:0;">
            <table style="width:100%;table-layout:fixed;" cellpadding="4" cellspacing="2">
                <tr>
                    <td style="width:10%;text-align:left;">诚信事件编号：</td>
                    <td style="width:20%">
                        <input id="codeInput" name="code" class="mini-textbox" style="width:100%" onenter="onKeyEnter"/>
                    </td>
                    <td style="width:10%;text-align:left;">诚信事件标题:</td>
                    <td style="width:20%">
						<input id="nameInput" name="name" class="mini-textbox" style="width:100%" onenter="onKeyEnter"/>
                    </td>
                    <td style="width:5%;text-align:left;"></td>
                    <td style="width:20%">
                    </td>
                    <%--<td style="width:5%;text-align:left;">申请人：</td>
                    <td style="width:20%">
                        <input id="applyuserInput" name="applyuser" class="mini-textbox" style="width: 100%;" onenter="onKeyEnter"/>
                    </td>--%>
                    <%--<td style="width:100px;text-align:center;" rowspan="2"><a class="mini-button" width="90%" onclick="search()">查询</a>
                    <a class="mini-button" style="display: none" width="90%" id="multiApprove" onclick="multiApprove()">批量审批</a>
                    </td>--%>
                </tr>
                <tr>
                	<td style="width:10%" align="left">审批状态:</td>
                    <td style="width:20%">
                            <input id="statusInput" name="applyStatus" class="mini-combobox" style="width: 100%;" textField="text" valueField="id" onenter="onKeyEnter"
                                url="<%=request.getContextPath()%>/ajax/suggest_getSuggestProcessStatusList.do" showNullItem="true" nullItemText=""/>
                    </td>  
                    <td style="width:10%;text-align:left;">申请时间：</td>
                    <td>
                        <input id="fromDateInpt" name="createDateBegin" class="mini-datepicker" style="width:100%;" onenter="onKeyEnter"/>
                    </td>
                    <td style="width:5%;text-align:center;">--&nbsp;至&nbsp;--</td>
                    <td style="width:20%">
                        <input id="endDateInpt" name="createDateEnd" class="mini-datepicker" style="width:100%;" onenter="onKeyEnter"/>
                    </td>
                    <td style="width:100px;text-align:center;" rowspan="2"><a class="mini-button" width="50%" onclick="search()">查询</a></td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">
            <div id="suggestWorkItemGrid" class="mini-datagrid"  allowAlternating="true" style="width:100%;height:100%;" allowResize="true"
                 url="<%=request.getContextPath()%>/ajax/suggest_showSuggestInfoUndoneList.do" >
                <div property="columns">
                    <div type="indexcolumn" width="40" align="center" headerAlign="center">序号</div>
                    <div width="50" align="center" headerAlign="center" renderer="onRenderOperate" >操作</div>
                    <div field="code" width="100" align="center" headerAlign="center" allowSort="true">诚信事件编号</div>
                    <div field="creditTitle" width="140" align="center" headerAlign="center" >诚信事件标题</div>
                    <div field="applybankName" width="100" align="center" headerAlign="center" >申请部门</div>
                    <div field="applyTime" width="100" align="center" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">申请时间</div>
                    <div field="currentUserName" width="100" align="center" headerAlign="center" >当前处理人</div>
                    <div field="approveStatusName" width="130" align="center" headerAlign="center" >审批状态</div>
                </div>
            </div>
        </div>
        
        <script type="text/javascript">
            mini.parse();
            var grid = mini.get("suggestWorkItemGrid"); 
            grid.load();
            //var role = "${role}";
            //$(document).ready(function() {
	            //if(role == "administrator"){
	            	//$("#multiApprove").show();
	            //}
            //});
            // 查询按钮按下的事件
            function search() {
            	//获取查询条件参数
                var searchFields = getSearchFields();
                grid.load(searchFields);
            }
            function getSearchFields(){
            	var code = mini.get("codeInput").getValue();
                var name = mini.get("nameInput").getValue();
                //var applyuser = mini.get("applyuserInput").getValue();
                var createDateBegin = mini.get("fromDateInpt").getFormValue();
                var createDateEnd = mini.get("endDateInpt").getFormValue();
                var applystatus = mini.get("statusInput").getFormValue();

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
                //if( applyuser != "") {
                    //searchFields.applyuser=applyuser;
                //}
                if( createDateBegin != ""){
                    searchFields.createDateBegin=createDateBegin;
                }
                if( createDateEnd!="" ){
                    searchFields.createDateEnd=createDateEnd;
                }
                return searchFields;
            }
            
            function onRenderOperate(e) {
                var grid = e.sender;
                var record = e.record;
                var uid = record._uid;
                var s = "";
                if(record.statusFlag == "refuse"){
                	s = s + '<a class="Edit_Button" style="width:80px;" href="javascript:editRow(\'' + uid + '\')">编辑</a>';
                } else {
                    s = s + '<a class="Edit_Button" style="width:80px;" href="javascript:showRow(\'' + uid + '\')">详情</a>';
                }
                return s;
            }
            
            function showSuggestInfo(uid) {
                var row = grid.getRowByUID(uid);
                if(row) {
                    mini.open({
                        url: "<%=request.getContextPath()%>/showSuggestProcessDetail.do?suggestCode=" + row.code,
                        title: "诚信事件流程详情", width: 800, height: 602,
                        onload: function() {
                            var iframe = this.getIFrameEl();
                            var data = {};
                            iframe.contentWindow.SetData(data);
                        },
                        ondestroy: function(action) {
                            if(action == "ok") {
                                grid.reload();
                            }
                        }
                    });
                }
            }
            function onKeyEnter(e) {
            	search();
           }
           function showRow(row_uid){
               var row = grid.getRowByUID(row_uid);
               if(row){
                   var url = '<%=request.getContextPath()%>/showSuggestInfoApply.do?id='+row.id + '&suggestCode=' + row.code + '&flag=other';
                   parent.showTabForUser("suggestInfoUndo" + row.id,"诚信事件审批",url);
               }else {
                   mini.alert("请选中一条记录！");
               } 
           }
           function editRow(row_uid){
               var row = grid.getRowByUID(row_uid);
               if(row){
                   var url = '<%=request.getContextPath()%>/showSuggestInfoApply.do?id='+row.id + '&suggestCode=' + row.code + '&flag=edit';
                   parent.showTabForUser("suggestInfoUndo" + row.id,"诚信事件信息编辑",url);
               }else {
                   mini.alert("请选中一条记录！");
               }               
           }
           function multiApprove(){
               var url = '<%=request.getContextPath()%>/showMultiApprove.do?';
               parent.showTabForUser("suggestInfo","诚信事件批量审批",url);
           }
        </script>
    </body>
</html>
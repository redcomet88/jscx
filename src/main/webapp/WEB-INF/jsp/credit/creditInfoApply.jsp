<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>合理化建议增加或修改页面</title>
    <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
    <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet" />
    <style type="text/css">
       .errorText {
         color:red;
       }
       body{
              font-size: 14px;
              color: #005da2;
              font-family: '黑体';
            }
    </style>
</head>
 <body>
		<div style="filter:alpha(opacity=90,style=0);height:30px;width:100%;top:0px;position:fixed;z-index:9999;background-color: #c6c6c6;padding-left: 5px;padding-top: 1px">
                    <div id="saveDiv" >
			             <a id="save" class="mini-button"  onclick="save" style="display: none">提交</a>
			             <a id="passCommit" class="mini-button"  onclick="taskCommit('pass')">下一任务</a>
			             <a id="refuseCommit" class="mini-button"  onclick="taskCommit('refuse')" style="display: none">退回重办</a>
			             <a id="transferTask" class="mini-button"  onclick="transferOption()" style="display: none">流转任务</a>
			             <a class="mini-button" onclick="close">退出</a>
                    </div>
         </div>
         <div style="padding-top: 28px;padding-bottom:28px">
         	    <div style="width：100%;height: 100%;padding: 5px;overflow: auto;">
         	    	<div id="suggestInfo" style="padding:10px,10px;border:1px solid #005da2;background-color: #ffffff">
         	    		<form id="suggestForm">
         	    			<input id="transferUser" name="transferUser" class = "mini-hidden">
         	    			<div style="width:100%;text-align: center;font-size: 20px;font-family:微软雅黑; padding-top: 10px;padding-bottom: 10px">
                                    <label for="textbox1$text" style="color:#64307a ">教师诚信事件单</label>
                            </div>
                            <div style="border:1px solid #005da2;border-left: hidden;border-right: hidden;">
                            	<table border="1px" style="width:100%;table-layout:fixed;border-collapse: collapse;border:1px solid #005da2;border-left: none;border-right: none">
                            		<tr>
                            			<td style="border: 1px solid #005da2;width:200px;height:50px;text-align:center;" ><label>诚信事件标题</label></td>
                            			<td style="width: 350px;border-right:hidden"><input id="suggestTitle" name="suggestTitle" class="mini-textbox" style="width:500px;" value="${suggestTitle }" required="true" onvaluechanged="checkText"/></td>
                            			<td style="width: 200px;border-right:hidden"></td>
                            			<td></td>
                            		</tr>
                            		<tr>
                            			<td style="border: 1px solid #005da2;width:120px;height:190px;text-align:center;" ><label>事件具体内容</label></td>
                                        <td style="border-right:hidden;" colspan="3"><textarea id="suggestContent" name="suggestContent" class="mini-textarea" style="height:180px;width:90%;" value="${suggestContent }" required="true" onvaluechanged="checkText"></textarea></td>
                            		</tr>
                            		<tr>
                            			<td style="border: 1px solid #005da2;width:120px;height:50px;text-align:center;" ><label>诚信事件编号</label></td>
                            			<td style="border: 1px solid #005da2;height:50px;" ><input id="code" name="code" class="mini-textbox" style="width: 80%;" value="${suggestBh }" enabled="false"/></td>
                            			<td style="border: 1px solid #005da2;height:50px;text-align:center;"><label>上传日期</label></td>
                            			<td style="border: 1px solid #005da2;height:50px;"><input id="applyTime" name="applyTime" class="mini-textbox" style="width:80%;" value="${applytime }" enabled="false" /></td>
                            		</tr>
									<tr>
										<td style="border: 1px solid #005da2;width:120px;height:50px;text-align:center;" ><label>一级指标</label></td>
										<td style="border: 1px solid #005da2;height:50px;" ><input id="topCombo" name="topCombo" class="mini-combobox" style="width: 80%;"  url="<%=request.getContextPath()%>/ajax/credit_creditIndexOption.do"
                                                 emptyText="----快速选择----" textField="name" valueField="id" onvaluechanged="onCreditIndexChanged" /></td>
										<td style="border: 1px solid #005da2;height:50px;text-align:center;"><label>二级指标</label></td>
										<td style="border: 1px solid #005da2;height:50px;"><input id="secondaryCombo" name="secondaryCombo" class="mini-combobox" style="width:100%;" url=""
                                                emptyText="----快速选择----" textField="name" valueField="id" onvaluechanged=""/></td>
									</tr>
                            		<tr>
                            			<td style="border: 1px solid #005da2;width:200px;height:100px;text-align:center;" ><label>主管部门意见</label></td>
                            			<td style="width: 80%;border-right:hidden"><textarea id="column1" name="column1" class="mini-textarea" style="width:500px;height:80px;" value="${column1 }" required="false" ></textarea></td>
                            			<td style="width: 200px;border-right:hidden"></td>
                            			<td></td>
                            		</tr>
                            		<tr>
                            			<td style="border: 1px solid #005da2;width:120px;height:60px;text-align:center;" ><label>主办部室</label></td>
                            			<td style="border: 1px solid #005da2;height:60px;" >
                                            <input id="mainDepartment" allowInput="true" name="mainDepartment" textField="jgmc" valueField="jgh" class="mini-treeselect" 
                                            parentField="sjjg" style="width: 80%;" onvalidation="onzbbsValidation" requiredErrorText="主办部室不能为空" required="true" 
                                            expandOnLoad="0" url="<%=request.getContextPath()%>/ajax/suggestInfo_selectZbbs.do" value="${maindepartment}"
                                            allowInput="true" onblur="inputMouseMove('mainDepartment')"/>
                            			   <div id="zbbs_error" class="errorText"></div>
                            			</td>
                            			<td style="border: 1px solid #005da2;height:60px;text-align:center;"><label>协办部室</label></td>
                            			<td style="border: 1px solid #005da2;height:60px;">
                                            <input id="minorDepartment" name="minorDepartment" textField="jgmc" valueField="jgh" multiSelect="true" class="mini-treeselect" 
                                            parentField="sjjg" style="width:80%;" url="<%=request.getContextPath()%>/ajax/suggestInfo_selectZbbs.do"  value="${minordepartment}"
                                            expandOnLoad="0" allowInput="true" onblur="inputMouseMove('minorDepartment')"/>
                                        </td>
                            		</tr>

                            	</table>
                            </div>
                            <div id="transferWindow" class="mini-window" style="width:500px;height:300px;border:1px solid #e0e0e0;background:#f3f3f4;line-h">
                            	<div style="width:100%;height:85%;top:30px;">
					                <table id="transferTable" border="0" cellpadding="10" >
					                    <tr>
					                        <td style="text-align:right;width:120px;">
					                            <label for="textbox1$text">将此任务流转给：</label>
					                        </td>
					                        <td colspan="2">
					                            <input id="transferName" name="transferName" class="mini-combobox" onvaluechanged="onTransferValidation" textField="ygxm" valueField="dah"  style="width:100%;" required="true" url = "<%=request.getContextPath()%>/ajax/suggestInfo_selectTransferUserList.do?id=${id}&applyStatus=${applystatus}" />
					                        </td>
					                    </tr>
					                    <tr>
					                        <td ></td>
					                        <td align="right">
					                            <a class="mini-button" onclick="taskCommit('transfer')" >保存</a>
					                        </td>
					                        <td align="left">
					                            <a class="mini-button" onclick="hideWindow('transferWindow')">取消</a>
					                        </td>
					                    </tr>
					                </table>
					            </div>
                            </div>
                            <div id="approveWindow" class="mini-window" style="width:500px;height:300px;border:1px solid #e0e0e0;background:#f3f3f4;line-h">
				            <div id="suggestApproveForm" style="margin-top:30px;">
				                <input name="id" class="mini-hidden" value="${id}" />
				                <table style="margin-left: 10%;table-layout:fixed;width:80%">
				                    <tbody>
				                        <tr>
				                            <td style='width: 100px;'><label>审批意见选择：</label></td>
				                            <td colspan="2"><input id="ywOption" class="mini-combobox" url="<%=request.getContextPath()%>/ajax/cg_approveOption.do"
				                                style="width: 100%" emptyText="----快速选择----" textField="optionName" valueField="optionName" onvaluechanged="onYwOptionChanged"
				                                showNullItem="true"/>
				                            </td>
				                        </tr>
				                        <tr>
				                            <td style="width: 100px;"><label>审批意见：</label></td> 
				                            <td colspan="2"> 
				                                <input name="spResult" id="spResult" value="" requiredErrorText="审批意见不能为空"
				                                class="mini-textarea" style="height:80px; width:100%;" 
				                                required="true" vtype="maxLength:200" />
				                                <span id="spResult_error" class="errorText"></span>
				                            </td>
				                        </tr>
				                    </tbody>
				                </table>
				            </div>
				            <div style="width:100%;height:5%;text-align:center;margin-top:30px;">
				                <a class="mini-button" onclick="saveOption()" iconCls="icon-save">收藏意见</a>
				                <a class="mini-button" onclick="optionConfirm()" iconCls="icon-save" style="margin-left:20px;">确认</a>
				                <a class="mini-button" onclick="hideWindow('approveWindow')" iconCls="icon-no" style="margin-left:20px;">关闭</a>
				            </div>
				        </div>
				        <div id="isNeedWindow" class="mini-window" style="width:350px;height:160px;border:1px solid #e0e0e0;background:#f3f3f4;line-h">
				        	<div id="formTranferTask" style="height:100%;left:5px;top:6px;border:1px solid #e0e0e0;background:#f3f3f4;">
					            <input id="flag" class="mini-hidden"/>
					            <input id="editFlg" class="mini-hidden"/>
					            <input id="isNeedId" class="mini-hidden"/>
					            <div style="width:100%;height:85%;top:30px;">
					                <table id="isNeedTable" border="0" cellpadding="10" >
					                    <tr>
					                        <td style="text-align:right;width:50%;">
					                            <label id="label1" style="float:left;display:none">是否需要行领导审批:&nbsp;</label>
									             <label id="label2" style="float:left;display:none">是否需要重新审批:&nbsp;</label>
									             <label id="label3" style="float:left;display:none">是否需要行领导复审:&nbsp;</label>
					                        </td>
					                        <td style="text-align:right;width:50%;">
					                            <input id="isNeedStep" name="isNeedStep" class="mini-radiobuttonlist"  url="<%=request.getContextPath()%>/resources/scripts/miniui/data/isNeedStep.txt" style="width:100%;" required="true" value = "0"/>
					                        </td>
					                    </tr>
					                </table>
				                    <div style="width:100%;height:5%;text-align:center;margin-top:30px;">
			                            <a class="mini-button" onclick="taskCommit('pass')" >保存</a>
			                            <a class="mini-button" onclick="hideWindow('isNeedWindow')" style="margin-left:20px;">关闭</a>
				                    </div>
					            </div>
					        </div>
				        </div>
         	    		</form>
         	    	</div>
         	    </div>
         </div>
         <div style="filter:alpha(opacity=90,style=0);height:30px;width:100%;bottom:0px;position:fixed;z-index:9999;background-color: #c6c6c6;padding-left: 5px;padding-top: 1px">
             <div id="fillingDiv">
                 <a id="fillingOption" class="mini-button"  onclick="fillingOption()" style="display: none">填写意见</a>
                 <a id="deleteOption" class="mini-button"  onclick="deleteOption" style="display: none">删除意见</a>
                 <a id="showLzrz" class="mini-button" onclick="showLzrz" style="display: none">流转日志</a>
                 <a id="uploadAttachment" class="mini-button" onclick="uploadAttachment" style="display: none">上传附件</a>
                 <a id="findAttachment" class="mini-button" onclick="findAttachment" style="display: none">附件</a>
                 <a id="print"  class="mini-button" onclick="printSuggestRecord" >打印</a>
                 <a id="bangzhu"  class="mini-button" onclick="showLct" style="display: none">帮助</a>
             </div>
         </div>
         <script type="text/javascript">
         mini.parse();
         var flag = "${flag}";
         var applyStatus = "${applystatus}";
         var leadercomment="${leadercomment}";
         var currentUser = "${currentUser}";
         var lastHandleResult = "${handleResult2}";
         var roleName = "${roleName}";
         var id = "${id}";
         var showUpload = "false";
         $(document).ready(function() {
        	 if(applyStatus != 'departmentAcceptStart'){
            	 $("#handleMemberTr").hide();
             }
        	 if("add" == flag){
        		 $("#save").show();
        		 $("#handleResultTr").hide();
        		 $("#leaderTr").hide();
        		 $("#signTr").hide();
        		 $("#print").hide();
        		 $("#passCommit").hide();
        		 $("#uploadAttachment").hide();
                 showUpload = "true";
        		 $("#findAttachment").show();
        		 mini.get("applyTime").disable();
                 mini.get("column1").disable();
                 mini.get("mainDepartment").disable();
                 mini.get("minorDepartment").disable();
                 mini.get("handleTime").disable();
                 mini.get("deadline").disable();
                 mini.get("signLeader").disable();
                 mini.get("specificInfo").disable();
                 mini.get("signOpinion").disable();
                 mini.get("handleResult").disable();
                 mini.get("whetherReasonable").disable();
                 mini.get("whetherAccept").disable();
                 mini.get("column2").disable();
                 mini.get("leaderIndicate").disable();
        	 }else if("search" == flag){
    		    $("#handleResultTr").hide();
    		    $("#signTr").hide();
    		    $("#handleMemberTr").hide();
                 $("#findAttachment").show();
    		    if(roleName != "suggestor"){
	        		$("#showLzrz").show();
    		    }else{
    		    	$("#leaderTr").hide();
    		    	$("#print").hide();
    		    }
             	$("#save").hide();
         		$("#isNeedStep").hide();
         		$("#refuseCommit").hide();
         		$("#passCommit").hide();
         		mini.get("suggestTitle").disable();
                mini.get("suggestContent").setAllowInput(false);
                mini.get("applyTime").disable();
                mini.get("column1").setAllowInput(false);
                mini.get("mainDepartment").disable();
                mini.get("minorDepartment").disable();
                mini.get("handleTime").disable();
                mini.get("deadline").disable();
                mini.get("signLeader").disable();
                mini.get("specificInfo").setAllowInput(false);
                mini.get("signOpinion").disable();
                mini.get("handleResult").setAllowInput(false);
                mini.get("whetherReasonable").disable();
                mini.get("whetherAccept").disable();
                mini.get("column2").setAllowInput(false);
                mini.get("leaderIndicate").setAllowInput(false);
             }else{
            	 if(("other" == flag && ( applyStatus == 'managerReview' || applyStatus == 'departmentAcceptEnd')) || ("edit" == flag && applyStatus == 'departmentAcceptEnd')){
            		 //$("#uploadAttachment").show();
                     showUpload = "true";
	            	 $("#opinionLabel2").show();
	            	 $("#opinionLabel1").hide();
                     $("#fillingOption").show();
                     $("#deleteOption").show();
            	 }else{
            		 //$("#uploadAttachment").show();
                     showUpload = "true";
	            	 $("#opinionLabel1").show();
	            	 $("#opinionLabel2").hide();
                     $("#fillingOption").show();
                     $("#deleteOption").show();
            	 }
				 if (roleName == "suggestor") {

                     $("#findAttachment").show();
				 }
            	 if(applyStatus != 'suggestionApplyStart' && applyStatus != 'suggestionApplyEnd') {
            		 $("#findAttachment").show();
	                 $("#showLzrz").show();
            	 } else {
     		    	$("#print").hide();
            	 }
            	 if(applyStatus == 'suggestionApplyStart' || applyStatus == 'suggestionApplyEnd'){
	                 $("#leaderTr").hide();
	                 $("#uploadAttachment").hide();
                     showUpload = "false";
            	 }
                 $("#passCommit").show();
                 if(applyStatus != 'suggestionApplyStart'  && applyStatus != 'adminView' && applyStatus != 'departmentHandle'){
	                 $("#fillingOption").show();
	                 $("#deleteOption").show();
                 }
            	 $("#signTr").hide();
                 if("other" == flag && (applyStatus == 'suggestionApplyStart')){
	            	 $("#handleResultTr").hide();
                 }
                 if(applyStatus == 'departmentHandle'){
                	 $("#transferTask").show();
                 }
                 if(applyStatus != 'suggestionApplyStart' && applyStatus != 'departmentHandle' && applyStatus != 'suggestionApplyEnd'){
                	 $("#refuseCommit").show();
                 }
                 if(applyStatus != 'suggestionApplyStart'){
                	 mini.get("suggestTitle").disable();
                	 mini.get("suggestContent").setAllowInput(false);
                 }
                 if(applyStatus == 'managerCheck'){
                	 mini.get("deadline").enable();
                 }
                 if(applyStatus != 'adminAccept' && applyStatus != 'managerCheck'){
                     mini.get("mainDepartment").disable();
                     mini.get("minorDepartment").disable();
            		 mini.get("signLeader").disable();
            		 mini.get("column1").disable();
               	 }
                 if(applyStatus != 'leaderCheck' && applyStatus != 'managerCheck'){
                     mini.get("signOpinion").disable();
                 }
                 if(applyStatus == 'managerCheck' && applyStatus == 'adminAccept'){
                     mini.get("column1").setAllowInput(true);
                 }
                 if(applyStatus != 'departmentHandle'){
                	 mini.get("whetherReasonable").disable();
                	 mini.get("whetherAccept").disable();
                	 mini.get("specificInfo").setAllowInput(false);
                 }
                 if(applyStatus != 'departmentAcceptEnd'){
                     mini.get("column2").setAllowInput(false);
                 }
           		 
                 if(applyStatus != 'adminAccept' && applyStatus != 'managerCheck' && applyStatus != 'adminView' && applyStatus != 'managerReview' && applyStatus != 'leaderComment'){
                	 mini.get("leaderIndicate").setAllowInput(false);
                 }
             }
         });
         
         function save(){
        	 var suggestForm = new mini.Form("#suggestForm");
        	 suggestForm.validate();

             if (suggestForm.isValid() == false) {
                 getValidateFocus(suggestForm);
                 return;
               } 
             var suggestFormDate = suggestForm.getData();
             var suggestFormTransfer = mini.encode(suggestFormDate);
    		 var tipMsg = "是否确认添加诚信事件";
    		 var step = mini.get("isNeedStep").getValue();
             mini.confirm(tipMsg, "确定？",function(action) {
                 if(action=='ok'){
                     msgid = mini.loading("诚信事件申请提交中，请稍后......", "诚信事件提交");
                     $.ajax({
                         url: "<%=request.getContextPath()%>/ajax/suggestInfo_addCreditInfo.do",
                         type: "post",
                         data: {suggestFormTransfer: suggestFormTransfer,step:step},
                         dataType: 'text',
                         success: function (text) {
                             if(text=='success'){
                                  mini.alert("诚信事件申请提交成功", "提醒", function(action) {
                                      refreshAll();
                                  });
                             }
                             mini.parse();
                             mini.hideMessageBox(msgid);
                         }
                     });
                 }
                 
             });
         }

         var topCombo = mini.get("topCombo");
         var secondaryCombo = mini.get("secondaryCombo");

         function onCreditIndexChanged(e) {
             var id = topCombo.getValue();
             secondaryCombo.setValue("");
             var url = "<%=request.getContextPath()%>/ajax/credit_creditIndexOption.do?topIndexId=" + id + "&level=2";
             secondaryCombo.setUrl(url);
             secondaryCombo.select(0);
         }


         function taskCommit(result) {
       		if(mini.get("spResult").getValue() == "" && result != "transfer" && applyStatus != 'suggestionApplyStart' && applyStatus != 'adminView' && applyStatus != 'adminAccept' && applyStatus != 'departmentHandle') {
                mini.alert("请填写处理意见！");
                return;
            }
       	    var suggestForm = new mini.Form("#suggestForm");
      	 	setRequiredValidate(result);
            suggestForm.validate();
            var error = mini.get("whetherReasonable").getErrorText();
            if (error != "") {
            	mini.get("specificInfo").focus();
            	return;
            }
            error = mini.get("whetherAccept").getErrorText();
            if (error != "") {
                mini.get("specificInfo").focus();
                return;
            }
            if (suggestForm.isValid() == false) {
                getValidateFocus(suggestForm);
                return;
            } 
             var step = mini.get("isNeedStep").getValue();
       	  	 //if(result == 'pass' && (applyStatus == 'managerCheck'||applyStatus == 'managerReview') && step != null && step == ""){
       	  	 //	isNeedOption();
       	  	 //	return;
       	  	// }
             var suggestForm = new mini.Form("#suggestForm");
             var suggestInForm = suggestForm.getData();
             var suggestForm = mini.encode(suggestInForm);
        	 mini.get("isNeedWindow").hide();
      		 var tipMsg = null;
      		 if(result == 'pass'){
    	  		 tipMsg = "是否通过该诚信事件审批";
      		 }else if(result == 'transfer'){
    	  		 tipMsg = "是否流转该诚信事件任务";
      		 }else if(result == 'refuse'){
    	  		 tipMsg = "是否确认退回";
      		 }
             if(result == 'transfer'&& mini.get("transferName").getValue() == "") {
                 mini.alert("请先选择流转人！");
                 return;
             }
             mini.confirm(tipMsg, "确定？",function(action) {
                 if(action=='ok'){
                     msgid = mini.loading("诚信事件提交中，请稍后......", "诚信事件提交");
                     if( applyStatus == 'adminView' || applyStatus == 'departmentHandle'){
    	                 mini.get("handleResult").setValue("");
                     }
                     $.ajax({
                         url: "<%=request.getContextPath()%>/ajax/suggestInfo_updateSuggestInfo.do",
                         type: "post",
                         data: {suggestForm: suggestForm , id:id , result:result , step:step},
                         dataType: 'text',
                         success: function (text) {
                             if(text=='success'){
                                  mini.alert("诚信事件提交成功", "提醒", function(action) {
                                      refreshAll();
                                  });
                             }else if (text == "done"){
                                 mini.alert("当前待办已被处理，您已不是当前处理人！", "提醒", function(action) {
                                     refreshAll();
                                 });
                             }
                             mini.parse();
                             mini.hideMessageBox(msgid);
                         }
                     });
                 }else{
                	 mini.get("isNeedStep").setValue('');
                 }
             });
           }
           function refreshAll() {
               // 刷新首页待办
               CloseWindow("ok");
               var mainTabs = parent.getMainTabs();
               $.each(mainTabs.tabs, function(i, tab){
                   if(tab.title == '合理化建议申请查询' || tab.title == '合理化建议流程待办') {
                       mainTabs.reloadTab(tab);
                   }
               });
               refreshMainTabDaiBan();
           }
       //查看流转日志
         function showLzrz(){
             var suggestCode = "${suggestBh}";
             mini.open({
                 url: '<%=request.getContextPath()%>/showSuggestLzrz.do',
                 title: "流转日志信息", width: 800, height:600,
                 onload: 
                     function () {
                     var iframe = this.getIFrameEl();
                     var data = {suggestCode:suggestCode};
                     iframe.contentWindow.SetData(data);
                 },
                 ondestroy: function (action) {
                 }
             }); 
         }
         function isNeedOption() {
             var win = mini.get("isNeedWindow");
             showSelectLable();
             win.showAtPos("center", "middle");
         }
         function showSelectLable(){
        	 if(applyStatus == 'managerCheck'){
            	 $("#label1").show();
            	 $("#isNeedStep").show();
               	 mini.get("isNeedStep").setValue("0");
        	 }else if(applyStatus == 'managerReview'){
            	 $("#label3").show();
            	 $("#isNeedStep").show();
               	 mini.get("isNeedStep").setValue("1");
        	 }
         }
         function updateError(e) {
             var id = e.sender.name + "_error";
             var el = document.getElementById(id);
             if (el) {
                 el.innerHTML = e.errorText;
             }
         }
         function fillingOption() {
             var win = mini.get("approveWindow");
             win.showAtPos("center", "middle");
         }
         function transferOption() {
             var win = mini.get("transferWindow");
             win.showAtPos("center", "middle");
         }
         function onTransferValidation(){
      	   var transferUser = mini.get("transferName").getValue();
      	   mini.get("transferUser").setValue(transferUser);
         }
         function setRequiredValidate(result){
       	     if(result == "refuse" || result == "transfer"){
       	    	if(applyStatus == 'adminAccept' || applyStatus == 'managerCheck'){
                    mini.get("mainDepartment").setRequired(false);
               	}
                if(applyStatus == 'managerCheck'){
                    mini.get("column1").setRequired(false);
                }
                if(applyStatus == 'departmentHandle'){
		           	 mini.get("whetherReasonable").setRequired(false);
		           	 mini.get("whetherAccept").setRequired(false);
		           	 mini.get("specificInfo").setRequired(false);
                }
                if(applyStatus == 'departmentAcceptEnd'){
                    mini.get("column2").setRequired(false);
                }
                if(applyStatus == 'departmentAcceptStart'){
                    mini.get("officerUser").setRequired(false);
                }
       		 }else{
       			if(applyStatus == 'adminAccept'){
                    mini.get("mainDepartment").setRequired(true);
               	}
       			if(applyStatus == 'managerCheck'){
          		 	mini.get("signLeader").setRequired(true); 
               	}
                if(applyStatus == 'leaderComment'){
                    mini.get("signOpinion").setRequired(true);
                }
                if(applyStatus == 'managerCheck' && applyStatus == 'adminAccept'){
                    mini.get("column1").setRequired(true);
                }
                if(applyStatus == 'departmentHandle'){
		           	 mini.get("whetherReasonable").setRequired(true);
		           	 mini.get("whetherAccept").setRequired(true);
		           	 mini.get("specificInfo").setRequired(true);
                }
                if(applyStatus == 'departmentAcceptStart'){
                    mini.get("officerUser").setRequired(true);
                }
                if(applyStatus == 'departmentAcceptEnd'){
                    mini.get("column2").setRequired(true);
                }
       		 }
         }
         
         function onzbbsValidation(e){
        	 updateError(e);
         }
         // 审批意见快速选择
         function onYwOptionChanged(e) {
             mini.get("spResult").setValue(e.value);
         }
       //收藏意见
         function saveOption(){
             var suggestApproveForm = new mini.Form("suggestApproveForm");
             suggestApproveForm.validate();
             if(!suggestApproveForm.isValid()) {
                 getValidateFocus(suggestApproveForm);
                 return;
             }
             var spResult = mini.get("spResult").getValue();
             mini.confirm("确认收藏意见？", "提醒", function(action) {
                 if(action == "ok") {
                     $.ajax({
                         url: '<%=request.getContextPath()%>/ajax/cg_saveOption.do',
                         data: {optionName:spResult},
                         dataType:'text',
                         type:"post",
                         success: function(result) {
                             if(result == "SUCCESS") {
                                 mini.get("ywOption").load("<%=request.getContextPath()%>/ajax/cg_approveOption.do");
                                 mini.alert("保存成功！");
                             } else  if(result == "EXIST"){
                                 mini.alert("意见已存在，无需再次收藏！");
                             }
                         }
                     });
                 }
             });
         }
         // 确认审批意见
         function optionConfirm() {
      	   	 var suggestApproveForm = new mini.Form("suggestApproveForm");
             suggestApproveForm.validate();
             if(!suggestApproveForm.isValid()) {
                 getValidateFocus(suggestApproveForm);
                 return;
             }
             mini.get("handleResult").setValue("");
             var spResult = mini.get("spResult").getValue();
             mini.get("handleResult").setValue(spResult);
             if(applyStatus == 'managerCheck'){
          	   mini.get("column1").setValue(spResult);
             }
             if(applyStatus == 'adminAccept'){
                 mini.get("column1").setValue(spResult);
             }
             if(applyStatus == 'leaderCheck'){
          	   mini.get("signOpinion").setValue(spResult);
             }
             if(applyStatus == 'leaderComment'){
            	 mini.get("leaderIndicate").setValue(spResult);
             }
             if(applyStatus == 'departmentAcceptEnd'){
            	 mini.get("column2").setValue(spResult);
             }
             $("#opinionLabel2").show();
             $("#opinionLabel1").hide();
             hideWindow("approveWindow");
         }
         // 删除审批意见
         function deleteOption() {
             mini.get("spResult").setValue("");
             if(applyStatus == 'managerCheck'){
          	   mini.get("column1").setValue("");
             }
             if(applyStatus == 'adminAccept'){
                 mini.get("column1").setValue("");
             }
             if(applyStatus == 'leaderCheck'){
          	   mini.get("signOpinion").setValue("");
             }
             if(applyStatus == 'leaderComment'){
              	 mini.get("leaderIndicate").setValue("");
             }
             if(applyStatus == 'departmentAcceptEnd'){
            	 mini.get("column2").setValue("");
             }
             if("other" == flag && applyStatus != 'managerCheck' && applyStatus != 'departmentAcceptEnd' && applyStatus != 'managerReview'){
	             $("#opinionLabel1").show();
	       		 $("#opinionLabel2").hide();
             }
             mini.get("handleResult").setValue(lastHandleResult.replace("&nbsp;&nbsp;&nbsp;&nbsp;","\t\t"));
         }
      // 关闭弹出页面
         function hideWindow(winId) {
             var win = mini.get(winId);
             win.hide();
             if(winId=="isNeedWindow"){
          	   mini.get("isNeedStep").setValue("");
             }
         }
      // 关闭窗口
         function CloseWindow(action) {
             if (window.CloseOwnerWindow) {
                 return window.CloseOwnerWindow(action);
             } else {
                 window.close();
             }
         }
         // 取消按钮按下时关闭窗口
         function close() {
             CloseWindow("cancel");
         }
         function uploadAttachment(){
      	   var suggestBianHao = mini.get("code").getValue();
             mini.open({
                 url: "<%=request.getContextPath()%>/showSuggestAttachmentsUploadPage.do",
                 title: "附件上传", width: 500, height: 300,
                 onload: function () {
                     var iframe = this.getIFrameEl();
                     iframe.contentWindow.SetData(suggestBianHao);
                 },
                 ondestroy: function (action) {  
                 }
             });
         }
         
         function findAttachment(){
      	   var suggestBianHao = mini.get("code").getValue();
             mini.open({
                 url: "<%=request.getContextPath()%>/findSuggestAttachmentsUploadPage.do",
                 title: "查看附件", width: 1000, height: 500,
                 onload: function () {
                     var iframe = this.getIFrameEl();
                     //iframe.contentWindow.SetData(suggestBianHao);
                     var data = { suggestBianHao: suggestBianHao, showUpload: showUpload };
                     iframe.contentWindow.SetData(data);
                 },
                 ondestroy: function (action) {  
                 }
             });
         }
       //备注长度验证
         function checkText(e){
      	 var text = e.value;
             if(text.length>450){
                 e.errorText = "长度过长！";
                 e.isValid = false;
             }
         }
       
       //打印记录
         function printSuggestRecord(e) {
             var id = "${id}", code = "${suggestBh}";
             window.open("<%=request.getContextPath()%>/showSuggestPrintInfo.do?id="+id+"&suggestCode="+code);
         }
         </script>
	</body>
</html>
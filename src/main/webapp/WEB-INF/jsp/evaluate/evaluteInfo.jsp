<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>中层干部民主评测表</title>
        <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet" />
        <style type="text/css">
            .mini-radiobuttonlist table label{
                width:70px;
            }
             /* body{
                overflow:hidden;
            } */
        </style>
    </head>
    <body id="userBody" style="display: none">
        <div id="userDiv" style="height:100%;border:0px solid #e0e0e0;">
            <div style="height:26px;width:100%;text-indent:5px;font-weight:bold;font-family:Simsun;font-size:14px;line-height:26px;"> 老师基本信息
            </div>
            <form id="userForm">
            <table id="userTbl" border="0" cellpadding="10" cellspacing="2" style="width:85%;height:95%;" align="right">
                <tr>
                    <td style="text-align:right;width:90px;">工号：</td>
                    <td style="width:80%;">
                        <input id="dah" name="dah" class="mini-textbox" style="width:250px;" required="true" vtype="rangeChar:1,10;" value="${userInfo.dah}"/>
                        <input id="action" name="action" class="mini-hidden" value="${action}" />
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">姓名：</td>
                    <td>
                        <input id="ygxm" name="ygxm" class="mini-textbox" style="width:250px;" required="true" vtype="rangeChar:1,120" value="${userInfo.ygxm}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;width:90px;">门禁卡卡号：</td>
                    <td style="width:80%;">
                        <input id="mjkkh" name="mjkkh" class="mini-textbox" style="width:250px;" vtype="rangeChar:1,20;" value="${userInfo.mjkkh}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">部门：</td>
                    <td>
                        <input id="jgh" name="jgh" class="mini-treeselect" style="width:250px;" multiSelect="true" value="${jgxx}"
                        textField="jgmc" valueField="jgh" parentField="sjjg" url="<%=request.getContextPath()%>/ajax/user_getJgList.do"
                        checkRecursive="false" showFolderCheckBox="true" showClose="true" expandOnLoad="0" required="true" oncloseclick="onCloseClick">
                        </input>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">岗位：</td>
                    <td>
                        <input id="gwdj" name="gwdj" class="mini-combobox" textField="name" valueField="enKey"
                        style="width:250px;" url="<%=request.getContextPath()%>/ajax/user_getGwList.do" value="${userInfo.gwdj}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">性别：</td>
                    <td>
                        <input id="sex" name="sex" class="mini-radiobuttonlist" style="width:250px;" required="true"
                        url="<%=request.getContextPath()%>/ajax/user_getSexList.do" value="${userInfo.sex}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">邮箱：</td>
                    <td>
                        <input id="email" name="email" class="mini-textbox" style="width:250px;" vtype="email" required="true" value="${userInfo.email}"/>
                    </td>
                </tr>
                <tr>
                <td style="text-align:right;">手机号：</td>
                    <td>
                        <input id="mobile" name="mobile" class="mini-textbox" style="width:250px;" required="true" onvalidation="onPhoneValidation" value="${userInfo.mobile}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">身份证号码：</td>
                    <td>
                        <input id="sfzh" name="sfzh" class="mini-textbox" style="width:250px;" onvalidation="onIDCardsValidation" required="true" value="${userInfo.sfzh}"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">备注：</td>
                    <td>
                        <input id="bz" name="bz" class="mini-textarea" style="width:250px;" vtype="rangeChar:0,120" value="${userInfo.bz}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                    </td>
                    <td >
                        <a class="mini-button" id="save" onclick="save" style="margin-left:60px;">保存</a>
                        <a class="mini-button" onclick="closeTab">取消</a>
                    </td>
                </tr>
            </table>
            </form>
        </div>

        <script type="text/javascript">
            mini.parse();
            $("#userBody").fadeTo("slow", 1);
            var paramAction = mini.get("action").getValue();
            if (mini.get("action").getValue() == "edit") {
                mini.get("dah").setAllowInput(false);
                mini.get("ygxm").setEnabled(false);
                mini.get("jgh").setEnabled(false);
                mini.get("sex").setEnabled(false);
                //mini.get("email").setEnabled(false);
                //mini.get("mobile").setEnabled(false);
                //mini.get("sfzh").setEnabled(false);
                mini.get("mjkkh").setEnabled(false);
                mini.get("gwdj").setEnabled(false);
            }
            
            // 标准方法接口定义
            function SetData(data) {
                paramAction = data.actionFlag;
                if (data.actionFlag == "add") {
                } else if (data.actionFlag == "edit" || data.actionFlag == "start") {
                    var form = new mini.Form("#userTbl");
                    // 跨页面传递的数据对象，克隆后才可以安全使用
                    data = mini.clone(data);
                    $.ajax({
                        url: "<%=request.getContextPath()%>/ajax/user_getUser.do",
                        data: { dah: data.dah },
                        type: "post",
                        dataType: 'text',
                        cache: false,
                        success: function (text) {
                            var o = mini.decode(text);
                            form.setData(o.user);
                            form.setChanged(false);
                            mini.get("jgh").setValue(o.jgxx);
                            mini.get("dah").setAllowInput(false);
                            //mini.get("ygxm").setEnabled(false);
                            //mini.get("jgh").setEnabled(false);
                            //mini.get("sex").setEnabled(false);
                            //mini.get("sex").setEnabled(false);
                            //mini.get("email").setEnabled(false);
                            //mini.get("mobile").setEnabled(false);
                            //mini.get("sfzh").setEnabled(false);
                            mini.parse();
                        }
                    });
                }
            }

            // 身份证验证
            function onIDCardsValidation(e) {
                var id = mini.get("sfzh").getValue();
                if(id != "") {
                    if (e.isValid) {
                        var pattern = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
                        if (pattern.test(e.value) == false) {
                            e.errorText = "必须输入15~18位数字";
                            e.isValid = false;
                        }
                    }
                }
            }
            
            // 保存该画面数据
            function save() {
                var form = new mini.Form("#userTbl");
                form.validate();
                if (form.isValid() == true) {
                    if (paramAction == "add") {
                        mini.confirm("是否确定创建该员工？", "确定？",function(action) {
                            if (action == "ok") {
                                $.ajax({
                                    url: "<%=request.getContextPath()%>/ajax/user_addUser.do",
                                    type: "post",
                                    dataType: 'text',
                                    data: $("#userForm").serializeArray(),
                                    success: function (result) {
                                        if (result == "SUCCESS") {
                                            mini.alert("员工创建成功！", "提醒", function(action) { CloseWindow("ok");});
                                        } else {
                                            mini.alert("该员工已创建，不能重复创建！");

                                        }
                                    }
                                });
                            }
                        });
                    } else if (paramAction == "edit" || paramAction == "start") {
                        var message;
                        var alertMessage;
                        if (paramAction == "edit") {
                            message = "是否确定更新员工信息？";
                            alertMessage = "员工信息更新成功！";
                        } else {
                            message = "是否确定启用该员工？";
                            alertMessage = "该员工已启用！";
                        }
                        mini.confirm(message, "确定？",function(action) {
                            if (action == "ok") {
                                $.ajax({
                                    url: "<%=request.getContextPath()%>/ajax/user_updateUser.do",
                                    type: "post",
                                    dataType: 'text',
                                    data: $("#userForm").serializeArray(),
                                    success: function (result) {
                                        if (result == "SUCCESS") {
                                            mini.alert(alertMessage, "提醒", function(action) { CloseWindow("ok");});
                                        }
                                    }
                                });
                            }
                        });
                    }
                    
                }else{
                    form.getErrors()[0].focus();
                }
            }

            // 关闭该窗口
            function closeTab() {
                CloseWindow("cancel");
            }

            // 关闭窗口
            function CloseWindow(action) {
                if (window.CloseOwnerWindow) {
                    return window.CloseOwnerWindow(action);
                } else {
                    window.close();
                }
            }
            
            // 机构控件清空
            function onCloseClick(e) {
                var obj = e.sender;
                obj.setText("");
                obj.setValue("");
            }
        </script>
    </body>
</html>
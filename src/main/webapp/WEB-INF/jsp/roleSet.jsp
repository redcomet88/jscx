<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>角色分配</title>
        <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet" />
        <style type="text/css">
            body{
                overflow:hidden;
            }
        </style>
    </head>
    <body id="userBody" style="display: none">
        <div id="formRole" style="height:100%;left:5px;top:6px;border:1px solid #e0e0e0;background:#f3f3f4;">
            <input id="dah" name="dah" class="mini-hidden"/>
            <div style="width:100%;height:85%;float:left;">
                <table id="roleTable" border="0" cellpadding="10" cellspacing="2" style="width:100%;">
                    <tr>
                        <td colspan="3">
                            <input labelField="true" label="员工姓名：" labelStyle="width:70px;" id="ygxm" name="ygxm" class="mini-textbox" style="width:40%;" enabled="false"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width:40%">
                            <div id="listAllRole" class="mini-listbox" style="width:100%;height:200px;" showCheckBox="true" multiSelect="true">
                                <div property="columns">
                                    <div header="全部角色" field="roleName"></div>
                                </div>
                            </div>
                        </td>
                        <td style="width:20%;text-align:center;">
                            <input type="button" value=">" onclick="add()" style="width:40px;"/><br />
                            <input type="button" value="&lt;" onclick="removes()" style="width:40px;"/><br />
            
                        </td>
                        <td style="width:40%">
                            <div id="listRole" class="mini-listbox" style="width:100%;height:200px;" showCheckBox="true" multiSelect="true">
                                <div property="columns">
                                    <div header="分配角色" field="roleName"></div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </table>
            </div>
            <div style="width:100%;height:15%;float:left;text-align:center;margin-top:15px;">
                <a class="mini-button" onclick="save" >保存</a>
                <a class="mini-button" onclick="close" >取消</a>
            </div>
        </div>
        <script type="text/javascript">
            mini.parse();
            $("#userBody").fadeTo("slow", 1);
            var listAllRole = mini.get("listAllRole");
            var listRole = mini.get("listRole");

            // 标准方法接口定义
            function SetData(data) {
                if (data.action == "edit") {
                    // 跨页面传递的数据对象，克隆后才可以安全使用
                    data = mini.clone(data);
                    mini.get("ygxm").setValue(data.ygxm);
                    mini.get("dah").setValue(data.dah);
                    $.ajax({
                        url: "<%=request.getContextPath()%>/ajax/role_initRoleSet.do",
                        data:{dah: data.dah},
                        type: "post",
                        dataType: 'text',
                        success: function (text) {
                            var data = mini.decode(text);
                            listAllRole.loadData(data.data);
                            listRole.loadData(data.userRoles);
                        }
                    });
                }
            }

            function add() {
                var items = listAllRole.getSelecteds();
                listAllRole.removeItems(items);
                listRole.addItems(items);
            }

            function removes() {
                var items = listRole.getSelecteds();
                listRole.removeItems(items);
                listAllRole.addItems(items);
            }

            // 保存按钮按下时提交表单
            function save() {
                var data = listRole.getData();
                if (data == "") {
                    mini.alert("请至少分配一个角色！");
                    return;
                }
                var json = mini.encode(data);
                var dah = mini.get("dah").getValue();

                // 分配角色
                mini.confirm("是否确定给用户分配这个角色？", "确定？",function(action) {
                    if (action == "ok") {
                        $.ajax({
                            url: "<%=request.getContextPath()%>/ajax/role_saveUserRole.do",
                            type: "post",
                            data: {dah: dah, jsonRole: json },
                            dataType: 'text',
                            success: function (text) {
                                if (text == "SUCCESS") {
                                    mini.alert("角色分配成功！", "提醒", function(action) { CloseWindow("ok");});
                                }
                            }
                        });
                    }
                });
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
        </script>
    </body>  
</html>  
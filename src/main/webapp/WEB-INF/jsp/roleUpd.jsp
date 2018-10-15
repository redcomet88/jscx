<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>角色权限</title>
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
            <form id="roleForm">
            <input id="flag" name="flag" class="mini-hidden"/>
            <input id="editFlag" name="editFlag" class="mini-hidden"/>
            <input id="id" name="id" class="mini-hidden"/>
            <input id="menu" name="menu" class="mini-hidden"/>
            <div style="width:50%;height:85%;float:left;">
                <table id="roleTable" border="0" cellpadding="10" cellspacing="2" style="width:100%;">
                    <tr>
                        <td style="text-align:right;width:90px;">
                            <label for="textbox1$text">角色名：</label>
                        </td>
                        <td style="width:70%;">
                            <input id="roleName" name="roleName" class="mini-textbox" style="width:100%;" allowInput="false"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="text-align:right;">
                            <label for="textbox1$text">角色描述：</label>
                        </td>
                        <td>
                            <input id="roleDescription" name="roleDescription" class="mini-textarea" style="width:100%;"/>
                        </td>
                    </tr>
                </table>
            </div>
            </form>
            <div style="width:1%;height:85%;float:left;">
                <div style="width:1px;height:100%;margin-top:12px;float:left;border-left:1px dashed #cccccc;"></div>
            </div>
            <div style="width:49%;height:85%;float:left;">
                <ul id="menuTree" class="mini-tree" style="width:100%;height:100%;padding:5px;margin-top:5px;" 
                    showTreeIcon="true" textField="text" idField="id" parentField="pid" resultAsTree="false"    
                    allowSelect="false" enableHotTrack="false" expandOnLoad="false"
                    showCheckBox="true" checkRecursive="true"></ul>
            </div>
            <div style="width:100%;height:100%;float:left;text-align:center;margin-top:15px;">
                <a class="mini-button" onclick="save" >保存</a>
                <a class="mini-button" onclick="close" >取消</a>
            </div>
        </div>
        <script type="text/javascript">
            mini.parse();
            $("#userBody").fadeTo("slow", 1);
            var form = new mini.Form("formRole");

            // 标准方法接口定义
            function SetData(data) {
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/role_getmenuFunctionList.do",
                    data:{},
                    type: "post",
                    dataType: 'text',
                    async: false,
                    success: function (text) {
                        var data = mini.decode(text);
                        var tree = mini.get("menuTree");
                        tree.loadList(data.data);
                    }
                });
                // 跨页面传递的数据对象，克隆后才可以安全使用
                data = mini.clone(data);
                if (data.action == "new") {
                    mini.get("editFlag").setValue("1");
                    mini.get("roleName").setAllowInput(true);
                } else if (data.action == "edit") {
                    mini.get("editFlag").setValue("2");
                    mini.get("id").setValue(data.id);
                    mini.get("roleName").setValue(data.roleName);
                    mini.get("roleDescription").setValue(data.roleDescription);
                    mini.get("flag").setValue(data.flag);
                    $.ajax({
                        url: "<%=request.getContextPath()%>/ajax/role_getRoleMenu.do",
                        data:{roleId : data.id},
                        type: "post",
                        dataType: 'text',
                        success: function (text) {
                            var data = mini.decode(text);
                            var tree = mini.get("menuTree");
                            tree.setValue(data.data);
                        }
                    });
                }
            }

            // 保存按钮按下时提交表单
            function save() {
                form.validate();
                if (form.isValid() == false) {form.getErrors()[0].focus();return;}

                var menu = mini.get("menuTree").getValue(true);
                if (menu == "") {
                    mini.alert("请选择权限！");
                    return;
                }
                mini.get("menu").setValue(menu);
                // 保存角色
                mini.confirm("是否保存该角色权限？", "确定？",function(action) {
                    if (action == "ok") {
                        $.ajax({
                            url: "<%=request.getContextPath()%>/ajax/role_saveRole.do",
                            type: "post",
                            data: $("#roleForm").serializeArray(),
                            dataType: 'text',
                            success: function (text) {
                                if (text == "SUCCESS") {
                                    mini.alert("保存成功！", "提醒", function(action) { CloseWindow("ok");});
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
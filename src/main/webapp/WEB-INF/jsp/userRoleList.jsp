<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>用户角色管理</title>
        <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet" />
        <style type="text/css">
            body{
                overflow:hidden;
            }
        </style>
    </head>
    <body id="userBody" style="display: none">
        <div class="mini-toolbar" style="padding:2px;border-bottom:0;">
            <table style="width:100%;table-layout:fixed;">
                <tr>
                    <td style="width:30%;">
                        <a class="mini-button" id="roleSet" onclick="roleSet()">角色分配</a>
                        <a class="mini-button" id="dataAuthoritySet" onclick="dataAuthoritySet()">数据权限分配</a>
                    </td>
                    <td style="width:90px;text-align:right;"">部门：</td>
                    <td style="width:20%;">
                        <input id="jgh" class="mini-treeselect" style="width:100%;"
                            textField="jgmc" valueField="jgh" parentField="sjjg" showClose="true" 
                            url="<%=request.getContextPath()%>/ajax/cg_getJgList.do" oncloseclick="onCloseClick" expandOnLoad="0" />
                    </td>
                    <td style="width:90px;text-align:right;"">角色名称：</td>
                    <td style="width:15%;">
                        <input id="roleName" class="mini-textbox" emptyText="请输入角色名称" style="width:100%;" onenter="onKeyEnter"/>
                    </td>
                    <td style="width:90px;text-align:right;"">老师姓名：</td>
                    <td style="width:15%;">
                        <input id="ygxm" class="mini-textbox" emptyText="请输入员工姓名" style="width:100%;" onenter="onKeyEnter"/>
                    </td>
                    <td style="width:70px;">
                        <a class="mini-button" id="search" onclick="search()">查询</a>
                    </td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">  
            <div id="userRoleDatagrid" class="mini-datagrid"  allowAlternating="true" style="width:100%;height:100%;"
            url="<%=request.getContextPath()%>/ajax/role_getUserRoles.do" allowResize="true" idField="id" pageSize="50">
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center">序号</div>
                    <div field="dah" width="16%" allowSort=true>档案号</div>
                    <div field="ygxm" width="28%" allowSort="false">老师姓名</div>
                    <div field="jgmc" width="28%" allowSort="false">部门</div>
                    <div field="roleName" width="28%" allowSort="false">角色名称</div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            mini.parse();
            $("#userBody").fadeTo("slow", 1);
            $(document).ready(function() {
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/role_initUserRoleList.do",
                    data:{id: "<%=request.getParameter("id")%>"},
                    type: "post",
                    dataType: 'text',
                    success: function (text) {
                        var data = mini.decode(text);
                        if (data.roleSetFlag == false) {
                            mini.get("roleSet").setEnabled(false);
                        }
                        if (data.dataSetFlag == false) {
                            mini.get("dataAuthoritySet").setEnabled(false);
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        mini.alert(jqXHR.responseText);
                    }
                });
            });
            
            var grid = mini.get("userRoleDatagrid");
            grid.load();
            grid.sortBy("dah", "ASC");

            function onKeyEnter(e) {
                search();
            }

            // 查询按钮按下的事件
            function search() {
            	var jgh = mini.get("jgh").getFormValue();
            	var roleName = mini.get("roleName").getValue();
                var ygxm = mini.get("ygxm").getValue();
                grid.load({ 'jgh':jgh, 'roleName': roleName, 'ygxm':ygxm });
            }

            // 给用户分配角色
            function roleSet() {
                var row = grid.getSelected();
                if (row) {
                    mini.open({
                        url: "<%=request.getContextPath()%>/showRoleSet.do",
                        title: "角色分配", width: 600, height: 360,
                        onload: function () {
                            var iframe = this.getIFrameEl();
                            var data = { action: "edit", dah: row.dah, ygxm: row.ygxm};
                            iframe.contentWindow.SetData(data);
                        },
                        ondestroy: function (action) {
                            if (action == "ok") {
                                grid.reload();
                            }
                        }
                    });
                    
                } else {
                    mini.alert("请选中一条记录");
                }
            }

            // 给用户分配数据权限
            function dataAuthoritySet() {
                var row = grid.getSelected();
                if (row) {
                    mini.open({
                        url: "<%=request.getContextPath()%>/showDataAuthoritySet.do",
                        title: "数据权限分配", width: 600, height: 360,
                        onload: function () {
                            var iframe = this.getIFrameEl();
                            var data = { action: "edit", dah: row.dah, ygxm: row.ygxm};
                            iframe.contentWindow.SetData(data);
                        },
                        ondestroy: function (action) {
                            if (action == "ok") {
                                grid.reload();
                            }
                        }
                    });
                    
                } else {
                    mini.alert("请选中一条记录");
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
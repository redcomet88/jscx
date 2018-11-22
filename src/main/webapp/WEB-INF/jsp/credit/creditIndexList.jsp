<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>教师诚信指标一览</title>
        <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet" />
        <style type="text/css">
            body{
                overflow:hidden;
            }
        </style>
    </head>
    <body id="logBody" style="display: none">
        <div class="mini-toolbar" style="padding:2px;border-bottom:0;">
            <table style="width:100%;table-layout:fixed;">
                <tr>
                    <td style="width:30%"></td>
                    <td style="width:120px">
                        <a class="mini-button" style="width:120px" iconCls="icon-add" id="add" onclick="add()">创建指标</a>
                    </td>
                    <td style="width:50px;">一级指标：</td>
                    <td style="width:80px">
                        <input id="topCombo" name="topCombo" class="mini-combobox" style="width:100%;" url="<%=request.getContextPath()%>/ajax/credit_creditIndexOption.do"
                               emptyText="----快速选择----" textField="name" valueField="id"  />
                    </td>
                    <td style="width:1%"></td>
                    <td style="width:50px;">指标名称：</td>
                    <td style="width:80px">
                        <input id="name" name="name" class="mini-textbox" style="width:100%;"/>
                    </td>
                    <td style="width:1%"></td>


                    <td style="width:70px"><a class="mini-button" id="search" onclick="search()" style="width:100%;">查询</a></td>
                </tr>

            </table>
        </div>
        <div class="mini-fit">  
            <div id="datagrid1" class="mini-datagrid"  allowAlternating="true" style="width:100%;height:100%;"
            url="<%=request.getContextPath()%>/ajax/credit_creditIndexList.do" allowResize="true" idField="id" pageSize="50">
                <div property="columns">
                    <div type="indexcolumn" width="40" headerAlign="center">序号</div>
                    <div name="active" width="120" headerAlign="center" align="center" renderer="onActionRenderer" cellStyle="padding:0;">操作</div>
                    <div field="topIndexName" width="150" allowSort="true" align="center" headerAlign="center">一级指标</div>
                    <div field="name" width="600" allowSort="false" align="left" headerAlign="center">二级指标</div>
                    <div field="weight" width="50" allowSort="false" align="right" headerAlign="center">权重</div>
                    <div field="creditAction" width="600" allowSort="false" align="left" headerAlign="center">失信行为</div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            mini.parse();
            $("#logBody").fadeTo("slow", 1);
            var editFlag = true;
            var delFlag = true;

            $(document).ready(function() {
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/credit_initIndexList.do",
                    data:{id: "<%=request.getParameter("id")%>"},
                    type: "post",
                    dataType: 'text',
                    success: function (text) {
                        var data = mini.decode(text);
                        // 绑定机构列表
                        var tree = mini.get("jgh");
                        tree.loadList(data.jgxx);

                        editFlag = data.editFlag;
                        delFlag = data.delFlag;
                    }
                });
            });
            
            var grid = mini.get("datagrid1");  
            grid.load();
            grid.sortBy("datetime", "desc");

            // 操作列的生成
            function onActionRenderer(e) {
                var grid = e.sender;
                var record = e.record;
                var uid = record._uid;
                var rowIndex = e.rowIndex;

                var s = '';

                // 编辑权限设定
                if (editFlag) {
                    s = s + '<a class="Edit_Button" href="javascript:editRow(\'' + uid + '\')">编辑</a>';
                } else {
                    s = s + '<a class="Edit_Button" style="color:gray" href="#">编辑</a>';
                }
                return s;
            }

            // 编辑操作按下的事件
            function editRow(row_uid) {
                var row = grid.getRowByUID(row_uid);
                if (row) {
                    mini.open({
                        url: "<%=request.getContextPath()%>/showIndexUpdPop.do",
                        title: "编辑诚信指标信息", width: 800, height: 640,
                        onload: function () {
                            var iframe = this.getIFrameEl();
                            var data = { actionFlag: "edit", id: row.id };
                            iframe.contentWindow.SetData(data);
                        },
                        ondestroy: function (action) {
                            if (action == "ok") {
                                grid.reload();
                            }
                        }
                    });
                }
            }

            // 查询按钮按下的事件
            function search() {
                var topIndexName = mini.get("topCombo").getText();
                var name = mini.get("name").getValue();
                grid.load({ 'topIndexName': topIndexName, 'name': name});
            }

        </script>
    </body>  
</html>  
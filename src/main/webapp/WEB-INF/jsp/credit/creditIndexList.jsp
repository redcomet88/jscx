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
                    <td style="width:70px;">一级指标：</td>
                    <td style="width:10%">
                        <input id="jgh" name="jgh" class="mini-treeselect" style="width:100%;" textField="jgmc"
                        valueField="jgh" parentField="sjjg" expandOnLoad="0" allowInput="true">
                        </input>
                    </td>
                    <td style="width:1%"></td>
                    <td style="width:70px;">指标名称：</td>
                    <td style="width:10%">
                        <input id="ygxm" name="ygxm" class="mini-textbox" style="width:100%;"/>
                    </td>
                    <td style="width:1%"></td>

                    <td style="width:70px;">创建时间：</td>
                    <td style="width:10%">
                        <input id="fromDate" name="fromDate" class="mini-datepicker" style="width:100%;"/>
                    </td>
                    <td style="width:4%">
                        &nbsp;～&nbsp;
                    </td>
                    <td style="width:10%">
                        <input id="toDate" name="toDate" class="mini-datepicker" style="width:100%;"/>
                    </td>
                    <td style="width:70px"><a class="mini-button" id="search" onclick="search()" style="width:100%;">查询</a></td>
                </tr>
            </table>
        </div>
        <div class="mini-fit">  
            <div id="datagrid1" class="mini-datagrid"  allowAlternating="true" style="width:100%;height:100%;"
            url="<%=request.getContextPath()%>/ajax/credit_creditIndexList.do" allowResize="true" idField="id" pageSize="50">
                <div property="columns">
                    <div type="indexcolumn" width="5%" headerAlign="center">序号</div>
                    <div field="topIndexName" width="8%" allowSort="true" align="center" headerAlign="center">一级指标</div>
                    <div field="name" width="42%" allowSort="false" align="left" headerAlign="center">二级指标</div>
                    <div field="creditAction" width="45%" allowSort="false" align="left" headerAlign="center">失信行为</div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            mini.parse();
            $("#logBody").fadeTo("slow", 1);
            $(document).ready(function() {
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/log_initLogList.do",
                    data:{id: "<%=request.getParameter("id")%>"},
                    type: "post",
                    dataType: 'text',
                    success: function (text) {
                        var data = mini.decode(text);
                        // 绑定机构列表
                        var tree = mini.get("jgh");
                        tree.loadList(data.jgxx);
                        if (!data.searchFlag) {
                            mini.get("search").setEnabled(false);
                        }
                    }
                });
            });
            
            var grid = mini.get("datagrid1");  
            grid.load();
            grid.sortBy("datetime", "desc");

            // 查询按钮按下的事件
            function search() {
                var jgh = mini.get("jgh").getValue();
                var type = mini.get("type").getValue();
                var ygxm = mini.get("ygxm").getValue();
                var object = mini.get("object").getValue();
                var fromDate = mini.get("fromDate").getFormValue();
                var toDate = mini.get("toDate").getFormValue();
                grid.load({ 'jgh': jgh, 'type': type,
                     'ygxm': ygxm,'object' : object ,'fromDate': fromDate, 'toDate':toDate});
            }

        </script>
    </body>  
</html>  
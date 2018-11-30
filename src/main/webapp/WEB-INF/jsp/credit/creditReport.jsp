<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>诚信报表</title>
        <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet" />
    </head>
    <body id="peopleBody" style="display: none">
         &nbsp;&nbsp; <h2 align="center">报表功能建设中……</h2>
         <div class="mini-toolbar" style="padding:2px;border-bottom:0;">
             <table style="width:100%;table-layout:fixed;">
                 <tr>
                     <td style="width:50%"></td>
                     <td style="width:50px;">院部：</td>
                     <td style="width:80px">
                         <input id="deptId" name="deptId" class="mini-treeselect" style="width:100%;" url="<%=request.getContextPath()%>/ajax/cg_getJgList.do"
                                emptyText="----快速选择----" textField="jgmc" valueField="jgh"  parentField="sjjg" showClose="true" expandOnLoad="0" />
                     </td>
                     <td style="width:1%"></td>
                     <td style="width:50px;">工号：</td>
                     <td style="width:80px">
                         <input id="ygxm" name="ygxm" class="mini-textbox" style="width:100%;"/>
                     </td>
                     <td style="width:1%"></td>


                     <td style="width:70px"><a class="mini-button" id="search" onclick="search()" style="width:100%;">查询</a></td>
                 </tr>
             </table>
         </div>

         <!-- 表格部分 -->
         <div class="mini-fit">
             <div id="datagrid1" class="mini-datagrid"  allowAlternating="true" style="width:100%;height:100%;"
                  url="<%=request.getContextPath()%>/ajax/credit_creditPeopleList.do" allowResize="true" idField="id" pageSize="50">
                 <div property="columns">
                     <div type="indexcolumn" width="40" headerAlign="center">序号</div>
                     <div field="ygxm" width="150" allowSort="true" align="center" headerAlign="center">姓名</div>
                     <div field="deptName" width="120" allowSort="false" align="left" headerAlign="center">部门</div>
                     <div field="creditCount" width="100" allowSort="false" align="right" headerAlign="center">失信次数</div>
                     <div field="creditScore" width="100" allowSort="false" align="left" headerAlign="center">诚信得分</div>
                     <div name="active" width="120" headerAlign="center" align="center" renderer="onActionRenderer" cellStyle="padding:0;">操作</div>
                 </div>
             </div>
         </div>
         <script type="text/javascript">
             mini.parse();
             $("#peopleBody").fadeTo("slow", 1);

             var grid = mini.get("datagrid1");

             // 查询按钮按下的事件
             function search() {
                 var dept = mini.get("deptId").getValue();
                 var dah = mini.get("ygxm").getValue();
                 grid.load({ 'dept': dept, 'dah': dah});
             }
             grid.load();
         </script>
    </body>
</html>
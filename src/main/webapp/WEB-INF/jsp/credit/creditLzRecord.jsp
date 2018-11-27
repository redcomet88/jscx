<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>合理化建议流转日志查询</title>
        <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet" />
    </head>
    <body>        
        <div class="mini-fit" style="margin:0px 0" title="流转日志">
                <div id="datagrid1" class="mini-datagrid"  allowAlternating="true" style="width:100%;height:100%; font-size: large;"
                        url="<%=request.getContextPath()%>/ajax/suggest_processLogList.do" allowResize="true" showPager="false">
                    <div property="columns">                          
                        <div field="currentUserName" headerAlign="center" align="center" width="12%">处理人</div>
                        <div field="nextUserName" headerAlign="center" align="center" width="12%">下步处理人</div>
                        <div field="handleTime" headerAlign="center" align="center" width="20%" dateFormat="yyyy-MM-dd HH:mm:ss">处理时间</div>
                        <div field="approveStatusName" headerAlign="center" align="center" width="18%">审批状态</div>
                        <div field="oprationResult" headerAlign="center" align="center" width="18%">操作结果</div>
                        <div field="oprationDetail" headerAlign="center" align="center" width="20%">操作详情</div>
                    </div>
                </div>
        </div>        
        
        <script type="text/javascript">
            mini.parse();
            var grid = mini.get("datagrid1");
            // 标准方法接口定义
            function SetData(data) {
               var suggestCode = data.suggestCode;
               grid.load({'suggestid':suggestCode});
            }          

            grid.on("preload", function (e) {
                if (e.total == -1) {
                    window.parent.location.href="<%=request.getContextPath()%>/jsp/error.jsp?flg=1";
                } else if (e.total == -3) {
                    //没有权限
                    window.parent.location.href="<%=request.getContextPath()%>/jsp/error.jsp?flg=2";
                } else if (e.total == -6) {
                    //未知异常
                    window.parent.location.href="<%=request.getContextPath()%>/jsp/error.jsp?flg=6";
                }
            });
            
        </script>
   </body>   
</html>
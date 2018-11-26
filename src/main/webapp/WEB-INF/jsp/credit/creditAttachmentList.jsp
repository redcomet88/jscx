
<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>申请原始材料列表kk</title>
        <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type=text/css rel=stylesheet />
        <style type="text/css">
            .mini-radiobuttonlist table label{
                width:70px;
            }
            body{
                overflow:hidden;
            }
        </style>
    </head>
    <body>
        <iframe id="screct_frame1" name="screct_frame1" style="display:none;"></iframe>
        <form id="downloadForm" enctype="multipart/form-data" action="<%=request.getContextPath()%>/fileDownloadCg.do" method="post" target="screct_frame1">
            <input id="path" name="path" class="mini-hidden" />
            <input id="fileName" name="fileName" class="mini-hidden" />
            <input id="suggestcode" name="suggestcode" class="mini-hidden"/>
            <input id="showUpload" name="showUpload" class="mini-hidden"/>
        </form>
        <div id="cftyFileForm" style="overflow:auto;height:460px;border:1px solid #e0e0e0;background:#f3f3f4;">
            <a id="uploadAttachment" class="mini-button" onclick="uploadAttachment" style="display: none">上传附件</a><br/>

            <font size="3" id="advicer" style="display: none">&nbsp;&nbsp;建议人</font>

            <div id="datagrid1" class="mini-datagrid"  allowAlternating="true" style="width:100%;height:30%;display: none" allowResize="true" idField="zh" pageSize="10" showPager="false"
                url="<%=request.getContextPath()%>/listSuggestAttachment.do" >
                <font size="3" style="height: 50px">&nbsp;&nbsp;申请人</font>
                <div property="columns">
                    <div type="indexcolumn" headerAlign="center">序号</div>
                    <div name="active" width="100" headerAlign="center" align="center" renderer="onActionRenderer" cellStyle="padding:0;">操作</div>
                    <div field="suggestbh" width="100" headerAlign="center" align="center">事件编号</div>
                    <div field="uploadName" name="uploadName" style="display: none" width="100" headerAlign="center" align="center">上传者</div>
                    <div field="description" width="100" headerAlign="center" align="center">文件描述</div>
                    <div field="fileName" width="140" align="center" headerAlign="center">文件名</div>
                    <div field="uploadTime" width="140" align="center" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">上传时间</div>
                </div>
            </div>




        </div>
        <script type="text/javascript">
            mini.parse();
            var downloadPermission = "0";
            var previewPermission = "0";
            var deletePermission = "0";
            //var grid = mini.get("datagrid1");
            var showUpload = "false";
            var suggestBianHao = mini.get("suggestcode").getValue();
            // 标准方法接口定义
            function SetData(data) {
                // 跨页面传递的数据对象，克隆后才可以安全使用
                var data = mini.clone(data);
                mini.get("suggestcode").setValue(data.suggestBianHao);
                mini.get("showUpload").setValue(data.showUpload);
                showUpload = data.showUpload;
                if(showUpload == "true"){
                    mini.get("uploadAttachment").show();
                }

                $.ajax({
                    url:"<%=request.getContextPath()%>/getSuggestAttachmentPermission.do",
                    data: {suggestCode: data.suggestBianHao},
                    type: "POST",
                    dataType: "text",
                    success: function (jsonStr) {
                        if (jsonStr != null) {

                            var result = mini.decode(jsonStr);
                            if(result.advicer == "true"){
                                document.getElementById("advicer").style.display = "";
                                mini.get("datagrid1").show();
                                mini.get("datagrid1").load({'suggestbh':data.suggestBianHao,"area":"1"});
                            }
                            if(result.officer == "true"){
                                document.getElementById("officer").style.display = "";
                                mini.get("datagrid2").show();
                                mini.get("datagrid2").load({'suggestbh':data.suggestBianHao,"area":"2"});
                            }
                            if(result.governor == "true"){
                                document.getElementById("governor").style.display = "";
                                mini.get("datagrid3").show();
                                mini.get("datagrid3").load({'suggestbh':data.suggestBianHao,"area":"3"});
                            }
                            if(result.advicer != "true" && result.officer != "true" && result.governor != "true"){
                                mini.get("datagrid1").show();
                                mini.get("datagrid1").load({'suggestbh':data.suggestBianHao,"area":"1"});
                            }
                        }
                        else {
                            mini.alert("获取附件操作权限失败");
                        }
                    }
                });
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
            // 操作列的生成
            function onActionRenderer(e) {
                var grid = e.sender.id;
                //mini.alert(grid);
                var record = e.record;
                var uid = record._uid;
                var rowIndex = e.rowIndex;
                var path = record.filePath + "/" + record.webFileName;
                var fileName = record.fileName;

                var s = '';
                s += '<a class="Edit_Button" href="javascript:downloadRow(\'' + path + '\',\'' + fileName + '\')">下载</a>';


                //if (deletePermission == "0") {
                if(record.uploadName == "${user.ygxm}"){
                    s += '<a class="Edit_Button" href="javascript:delRow(\'' + uid + ','+grid+'\')">删除</a>';
                }
                else {
                    s += '<a class="Edit_Button" style="width:80px;color:grey;" href="javascript:void(0)">删除</a>';
                }

                return s;
            }

            //预览
           function previewRow(path){
            	if(path.endsWith(".txt") || path.endsWith(".html") || path.endsWith(".xml"))
            		window.open("<%=request.getContextPath()%>"+path);
            	else 
					window.open("<%=request.getContextPath()%>/previewSuggestAttachment.do?path="+path);
			}
            // 下载
            function downloadRow(path, fileName){
                mini.get('path').setValue(path);
                mini.get('fileName').setValue(fileName);
                $("#downloadForm").submit();
            }
            // 删除
            function delRow(uid){
                var uid2 = uid.split(",",1);
                var strs = uid.split(",");
                //var datagrid = uid.substring(2);
                var datagrid = strs[strs.length-1];
                var grid = mini.get("datagrid1");
                if(datagrid == "datagrid2"){
                    grid = mini.get("datagrid2");
                    //mini.alert("datagrid2");
                }else if (datagrid == "datagrid3"){
                    grid = mini.get("datagrid3");
                    //mini.alert("datagrid3");
                }
                var row = grid.getRowByUID(parseInt(uid2));
                //mini.alert(uid2+" "+datagrid+grid+row);
            	mini.confirm("删除文件", "确定？", function(action) {
                    if (action == "ok") {
                        $.ajax({
                            url: "<%=request.getContextPath()%>/delSuggestAttachment.do",
                            data:{id : row.id, filePath : row.filePath, webFileName : row.webFileName },
                            //data:{id : "", filePath : "", webFileName : "" },
                            type: "post",
                            dataType: 'text',
                            success: function (text) {
                                var success = "SUCCESS";
                                var fail = "FAIL";
                                if (text == success) {
                                    mini.alert("删除成功", "提醒", function(action){grid.reload();});
                                } else if (text == fail) {
                                    mini.alert("删除文件失败");
                                } 
                            },
                            error: function (jqXHR, textStatus, errorThrown) {
                                mini.alert(jqXHR.responseText);
                            }
                        });
                    }
                });
            }

            function uploadAttachment(){
                suggestBianHao = mini.get("suggestcode").getValue();
                mini.open({
                    url: "<%=request.getContextPath()%>/showSuggestAttachmentsUploadPage.do",
                    title: "附件上传", width: 500, height: 300,
                    onload: function () {
                        var iframe = this.getIFrameEl();
                        iframe.contentWindow.SetData(suggestBianHao);
                    },
                    ondestroy: function (action) {
                        $.ajax({
                            url:"<%=request.getContextPath()%>/getSuggestAttachmentPermission.do",
                            data: {suggestCode: suggestBianHao},
                            type: "POST",
                            dataType: "text",
                            success: function (jsonStr) {

                                if (jsonStr != null) {
                                    var result = mini.decode(jsonStr);

                                    if(result.advicer == "true"){
                                        //mini.alert("closeadvicer");
                                        document.getElementById("advicer").style.display = "";
                                        mini.get("datagrid1").hide();
                                        mini.get("datagrid1").show();
                                        mini.get("datagrid1").load({'suggestbh':suggestBianHao,"area":"1"});
                                    }
                                    if(result.officer == "true"){
                                        //mini.alert("officer");
                                        if(result.advicer != "true"){
                                            mini.get("datagrid1").hide();
                                        }
                                        document.getElementById("officer").style.display = "";
                                        mini.get("datagrid2").show();
                                        mini.get("datagrid2").load({'suggestbh':suggestBianHao,"area":"2"});
                                    }
                                    if(result.governor == "true"){
                                        //mini.alert("governor");
                                        if(result.advicer != "true"){
                                            mini.get("datagrid1").hide();
                                        }
                                        document.getElementById("governor").style.display = "";
                                        mini.get("datagrid3").show();
                                        mini.get("datagrid3").load({'suggestbh':suggestBianHao,"area":"3"});
                                    }
                                    if(result.advicer != "true" && result.officer != "true" && result.governor != "true"){
                                        mini.get("datagrid1").show();
                                        //mini.alert("none");
                                    }

                                }
                                else {

                                    mini.alert("获取附件操作权限失败");
                                }
                            }
                        });
                        //mini.get("datagrid1").reload();
                        //mini.get("datagrid2").reload();
                        //mini.get("datagrid3").reload();
                    }
                });
            }
        </script>
    </body>
</html>
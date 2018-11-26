<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>上传附件</title>
        <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet" />
        <script src="<%=request.getContextPath()%>/resources/js/common/ajaxfileupload.js" type="text/javascript"></script>
        <style type="text/css">
            body{
                overflow:hidden;
            }
        </style>
    </head>
    <body id="userBody" style="display: none">
        <div id="attachmentForm" style="height:500px;left:5px;top:6px;border:1px solid #e0e0e0;background:#f3f3f4;">
            <iframe id="screct_frame" name="screct_frame" style="display:none;"></iframe>
            <form id="uploadAttachForm" method="post" enctype="multipart/form-data" action="" target="screct_frame">
                <input id="editFlag" name="editFlag" class="mini-hidden"/>
                <input id="id" name="id" class="mini-hidden"/>
                <input id="suggestbh" name="suggestbh" class="mini-hidden"/>
                <table id="attachmentTbl" border="0" cellpadding="10" cellspacing="4" style="width:100%;table-layout:fixed;">
                    <tr>
                        <td style="width:10%;"></td>
                        <td style="width:90px;text-align:right;">
                            <label for="textbox1$text">附件描述：</label>
                        </td>
                        <td style="width:80%;">
                            <input id="description" name="description" class="mini-textbox" style="width:100%;"/>
                        </td>
                        <td style="width:10%;"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td style="text-align:right;" valign="top">
                            <label for="textbox1$text">上传附件：</label>
                        </td>
                        <td id="file">
                            <input class="mini-htmlfile" name="Fdata" id="file1" style="width:100%;" limitType="*.doc;*.docx;*.xls;*.xlsx;*.ppt;*.htm;*.html;*.txt;*.zip;*.rar;*.pdf;*.jgp;*.png"/>
                        </td>
                        <td></td>
                    </tr>
                </table>
            </form>
            <div style="width:100%;height:60px;float:left;text-align:center;margin-top:30px">
                <a class="mini-button" onclick="saveData">提交</a>
                <a class="mini-button" onclick="close">取消</a>
            </div>
            <div style="margin-top:1px;margin-left:30px;font-size:14px;color:red">
              <span>※1 上传文件支持以下类型：doc,docx,xls,xlsx,ppt,xml,html,txt,zip,rar,pdf,jpg,png</span>
              <br />
              <span>※2 上传文件大小限制在10M以内</span>
            </div>
        </div>
        <script type="text/javascript">
            mini.parse();
            $("#userBody").fadeTo("slow", 1);
            var form = new mini.Form("#uploadAttachForm");
            // 标准方法接口定义
            function SetData(data) {
            	// 跨页面传递的数据对象，克隆后才可以安全使用kkk
                var data = mini.clone(data);
            	mini.get("suggestbh").setValue(data);
            }
            // 提交钮按下的事件
            function saveData() {
                // 画面表单控件验证
                form.validate();
                if (form.isValid() == true) {
                    mini.confirm("是否确定上传附件？", "确定？", function(action) {
                        if (action == "ok") {
                        	var inputFile = $("#file1 > input:file")[0];
                        	var description = mini.get("description").getValue();
                        	var suggestbh = mini.get("suggestbh").getValue();
                        	$.ajaxFileUpload({
                                url: "<%=request.getContextPath()%>/suggestbh_uploadSuggestAttachment.do",                 //用于文件上传的服务器端请求地址
                                fileElementId: inputFile,               //文件上传域的ID
                                data: {description:description,suggestbh:suggestbh},        //描述
                                dataType: 'text',                //返回值类型 一般设置为json
                                success: function(data, status)    //服务器成功响应处理函数
                                {
                                    var result = mini.decode(data);
                                    if (data != null) {
                                        if (result.status == "SUCCESS") {
                                            mini.confirm("上传成功,是否关闭上传页面！", "确认", function(action) {
                                                if (action == "ok") {
                                                    CloseWindow("ok");
                                                } else {
                                                    clearAttachFile();
                                                }
                                            });
                                        } else {
                                            mini.alert(result.message);
                                        }
                                    }
                                },
                                error: function (data, status, e)   //服务器响应失败处理函数
                                {
                                    if (status == null) {
                                        mini.alert("上传文件异常,可能是由于文件超过20M或其他异常原因","提醒",function(action){CloseWindow("ok");});
                                    } else {
                                        mini.alert(e);
                                    }
                                },
                                complete: function () {
                                    var jq = $("#file1 > input:file");
                                    jq.before(inputFile);
                                    jq.remove();
                                }
                            });
                        }
                    });
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
            
          //清除附件内容 
            function clearAttachFile() {
                mini.get("description").setValue("");
            }
        </script>
    </body>
</html>
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
        <div id="formData" style="height:100%;left:5px;top:6px;border:1px solid #e0e0e0;background:#f3f3f4;">
            <input id="dah" name="dah" class="mini-hidden"/>
            <div style="width:50%;height:85%;float:left;">
                <table id="authTable" border="0" cellpadding="10" cellspacing="2" style="width:100%;">
                    <tr>
                        <td>
                            <input labelField="true" label="员工姓名：" labelStyle="width:70px;" id="ygxm" name="ygxm" class="mini-textbox" style="width:90%;" enabled="false"/>
                        </td>
                    </tr>
                </table>
            </div>
            <div style="width:1%;height:85%;float:left;">
                <div style="width:1px;height:100%;margin-top:12px;float:left;border-left:1px dashed #cccccc;"></div>
            </div>
            <div style="width:49%;height:85%;float:left;">
                <ul id="bank" class="mini-tree" style="width:100%;height:100%;padding:5px;margin-top:5px;" 
                    showTreeIcon="true" textField="jgmc" idField="jgh" parentField="sjjg" resultAsTree="false"
                    allowSelect="false" enableHotTrack="false" expandOnLoad="0"
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
            // 标准方法接口定义
            function SetData(data) {
                if (data.action == "edit") {
                    // 跨页面传递的数据对象，克隆后才可以安全使用
                    data = mini.clone(data);
                    mini.get("ygxm").setValue(data.ygxm);
                    mini.get("dah").setValue(data.dah);
                    $.ajax({
                        url: "<%=request.getContextPath()%>/ajax/role_initDataAuthoritySet.do",
                        data:{dah: data.dah},
                        type: "post",
                        dataType: 'text',
                        success: function (text) {
                            var data = mini.decode(text);
                            var bank = mini.get("bank");
                            bank.loadList(data.data);
                            bank.setValue(data.dataAuthority);
                        }
                    });
                }
            }

            // 保存按钮按下时提交表单
            function save() {
                var dah = mini.get("dah").getValue();
                var bank = mini.get("bank").getValue();
                if (bank == "") {
                    mini.alert("请选择数据权限！");
                    return;
                }
                // 保存角色
                mini.confirm("是否确定保存数据权限分配？", "确定？",function(action) {
                    if (action == "ok") {
                        $.ajax({
                            url: "<%=request.getContextPath()%>/ajax/role_saveDataAuthority.do",
                            type: "post",
                            data: { jg: bank, dah: dah },
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
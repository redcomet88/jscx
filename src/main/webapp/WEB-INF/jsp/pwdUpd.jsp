<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>用户密码修改</title>
        <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet" />
        <style type="text/css">
            body{
                overflow:hidden;
            }
        </style>
    </head>
    <body id="userBody" style="display: none">
        <div id="form1" style="height:100%;left:5px;top:6px;border:1px solid #e0e0e0;background:#f3f3f4;">
        <div style="height:26px;width:100%;text-indent:5px;font-weight:bold;font-family:Simsun;font-size:14px;line-height:26px;">用户密码修改</div>
            <table id="pwdTbl" border="0" cellpadding="15" cellspacing="2" style="width:80%;" align="center">
                <tr>
                    <td style="text-align:right;width:30%;">
                        <label for="textbox1$text">输入旧密码：</label>
                    </td>
                    <td style="width:70%;">
                        <input id="oldPwd" name="oldPwd" class="mini-password" style="width:250px;" required="true" />
                    </td>
                    
                </tr>
                <tr>
                    <td style="text-align:right;">
                        <label for="textarea1$text">输入新密码：</label>
                    </td>
                    <td style="width:70%;">
                        <input id="newPwd" name="newPwd" class="mini-password" style="width:250px;" required="true" onvalidation="onPwdValidation1"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">确认新密码：</td>
                    <td>
                        <input id="newPwdConfirm" name="newPwdConfirm" class="mini-password" style="width:250px;" required="true" onvalidation="onPwdValidation2" />
                    </td>
                </tr>
                <tr>
                    <td>
                    </td>
                    <td style="padding-left:125px;">
                        <a class="mini-button" id="save" onclick="save" >确认修改</a>
                        <a class="mini-button" onclick="closeTab" style="width:66px">取消</a>
                    </td>
                </tr>
            </table>
        </div>

        <script type="text/javascript">
            mini.parse();
            $("#userBody").fadeTo("slow", 1);
            // 保存该画面数据
            function save() {
                var form = new mini.Form("#pwdTbl");
                form.validate();
                if (form.isValid() == true) {
                    mini.confirm("是否确定修改密码？", "确定？",function(action) {
                        if (action == "ok") {
                            $.ajax({
                                url: "<%=request.getContextPath()%>/ajax/user_updatePwd.do",
                                type: "post",
                                dataType: 'text',
                                data: { oldPwd : mini.get("oldPwd").getValue(), newPwd : mini.get("newPwd").getValue() },
                                success: function (result) {
                                    if (result=="SUCCESS") {
                                        parent.removeTab("密码修改成功！");
                                    } else if(result == "FAIL"){
                                        mini.alert("旧密码不正确！", "提醒", function(action) {
                                            form.reset();
                                        });
                                    }
                                }
                            });
                        }
                    });
                }else{
                    form.getErrors()[0].focus();
                }
            }

            // 新密码验证
            function onPwdValidation1(e) {
                var newPwd = mini.get("newPwd").getValue();
                var oldPwd = mini.get("oldPwd").getValue();
                if(newPwd == oldPwd){
                    e.errorText = "新密码不能与旧密码重复！";
                    e.isValid = false;
                }
                if (e.value.length < 6) {
                    e.errorText = "密码不能少于6个字符";
                    e.isValid = false;
                }
            }
          
            // 新密码验证
            function onPwdValidation2(e) {
                var newPwd = mini.get("newPwd").getValue();
                var newPwdConfirm = mini.get("newPwdConfirm").getValue();
                if(newPwd != newPwdConfirm){
                    e.errorText = "新密码两次输入不一致！";
                    e.isValid = false;
                }
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
        </script>
    </body>
</html>
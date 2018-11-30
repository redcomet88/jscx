<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>诚信一级指标信息维护</title>
        <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet" />
        <style type="text/css">
            .mini-radiobuttonlist table label{
                width:70px;
            }
             /* body{
                overflow:hidden;
            } */
        </style>
    </head>
    <body id="userBody" style="display: none">
        <div id="userDiv" style="height:100%;border:0px solid #e0e0e0;">
            <div style="height:26px;width:100%;text-indent:5px;font-weight:bold;font-family:Simsun;font-size:14px;line-height:26px;">诚信指标信息
            </div>
            <form id="userForm">
            <table id="userTbl" border="0" cellpadding="10" cellspacing="2" style="width:85%;height:95%;" align="right">
                <tr>
                    <td style="text-align:right;width:90px;">操作类型：</td>
                    <td style="width:80%;">
                        <input id="opt" name="opt" class="mini-radiobuttonlist" style="width:250px;" required="f"
                               url="<%=request.getContextPath()%>/resources/data/op.txt" "/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;width:90px;">一级指标：</td>
                    <td style="width:80%;">
                        <input id="id" name="id" class="mini-combobox"  url="<%=request.getContextPath()%>/ajax/credit_creditIndexOption.do"
                               style="width:250px;" required="true" vtype="rangeChar:1,10;" value="${indexInfo.dah}"  textField="name" valueField="id"/>
                        <input id="action" name="action" class="mini-hidden" value="${action}" />
                        <input id="level" name="level" class="mini-hidden" value="1" />
                        <input id="weight" name="weight" class="mini-hidden" value="1.0" />
                    </td>
                </tr>
                <!--
                <tr>
                    <td style="text-align:right;width:90px;">权重：</td>
                    <td style="width:80%;">
                        <input id="weight" name="weight" class="mini-textbox" onvalidation="onWeightValidation" style="width:250px;" vtype="rangeChar:1,20;" value="${indexInfo.weight}"/>
                        (权重为0~100%之间)
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">部门：</td>
                    <td>
                        <input id="column1" name="column1" class="mini-treeselect" style="width:250px;" value="${jgxx}"
                        textField="jgmc" valueField="jgh" parentField="sjjg" url="<%=request.getContextPath()%>/ajax/user_getJgList.do"
                         expandOnLoad="0" required="true" >
                        </input>
                    </td>
                </tr>-->
                <tr>
                    <td style="text-align:right;">指标名称：</td>
                    <td>
                        <input id="name" name="name" class="mini-textbox" style="width:250px;" value="${indexInfo.creditAction}"/>
                    </td>
                </tr>

                <tr>
                    <td>
                    </td>
                    <td >
                        <a class="mini-button" id="save" onclick="save" style="margin-left:60px;">保存</a>
                        <a class="mini-button" onclick="closeTab">取消</a>
                    </td>
                </tr>
            </table>
            </form>
        </div>

        <script type="text/javascript">
            mini.parse();
            $("#userBody").fadeTo("slow", 1);
            var paramAction = mini.get("action").getValue();
            mini.get("opt").setValue("add");
            mini.get("parrentId").setEnabled(false);
            mini.get("parrentId").setText("");

            // 标准方法接口定义
            function SetData(data) {
                paramAction = data.actionFlag;
                if (data.actionFlag == "add") {
                    mini.get("level").setValue("1");
                } else if (data.actionFlag == "edit" || data.actionFlag == "start") {
                    var form = new mini.Form("#userTbl");
                    // 跨页面传递的数据对象，克隆后才可以安全使用
                    data = mini.clone(data);
                    $.ajax({
                        url: "<%=request.getContextPath()%>/ajax/credit_getCreditIndex.do",
                        data: { id: data.id },
                        type: "post",
                        dataType: 'text',
                        cache: false,
                        success: function (text) {
                            var o = mini.decode(text);
                            form.setData(o.data);
                            form.setChanged(false);
                            //mini.get("ygxm").setEnabled(false);
                            mini.get("level").setEnabled(false);
                            //mini.get("jgh").setEnabled(false);
                            //mini.get("sex").setEnabled(false);
                            //mini.get("sex").setEnabled(false);
                            //mini.get("email").setEnabled(false);
                            //mini.get("mobile").setEnabled(false);
                            //mini.get("sfzh").setEnabled(false);
                            mini.parse();
                        }
                    });
                }
            }

            // 身份证验证
            function onWeightValidation(e) {
                var weight = mini.get("weight").getValue();
                if(weight != "") {
                    if (parseFloat(weight)<0||parseFloat(weight)>100) {
                            e.errorText = "权重必须在0~100之间";
                            e.isValid = false;
                    }
                }
            }
            
            // 保存该画面数据
            function save() {
                var form = new mini.Form("#userTbl");
                form.validate();
                var opt = mini.get("opt");
                if (form.isValid() == true) {
                    if (opt.getValue() == "add") {
                        mini.confirm("是否确定创建该一级指标？", "确定？",function(action) {
                            if (action == "ok") {
                                $.ajax({
                                    url: "<%=request.getContextPath()%>/ajax/credit_createCreditIndex.do",
                                    type: "post",
                                    dataType: 'text',
                                    data: $("#userForm").serializeArray(),
                                    success: function (result) {
                                        if (result == "SUCCESS") {
                                            mini.alert("二级指标创建成功！", "提醒", function(action) { CloseWindow("ok");});
                                        } else {
                                            mini.alert("该指标已创建，不能重复创建！");

                                        }
                                    }
                                });
                            }
                        });
                    } else if (opt.getValue() == "update"|| opt.getValue() == "2") {
                        var message;
                        var alertMessage;
                        if (opt.getValue() == "update") {
                            message = "是否确定更新一级指标信息？";
                            alertMessage = "指标信息更新成功！";
                        } else {
                            //预留功能点
                        }
                        mini.confirm(message, "确定？",function(action) {
                            if (action == "ok") {
                                $.ajax({
                                    url: "<%=request.getContextPath()%>/ajax/credit_updateCreditIndex.do",
                                    type: "post",
                                    dataType: 'text',
                                    data: $("#userForm").serializeArray(),
                                    success: function (result) {
                                        if (result == "SUCCESS") {
                                            mini.alert(alertMessage, "提醒", function(action) { CloseWindow("ok");});
                                        }
                                    }
                                });
                            }
                        });
                    }
                    
                }else{
                    form.getErrors()[0].focus();
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
            
            // 机构控件清空
            function onCloseClick(e) {
                var obj = e.sender;
                obj.setText("");
                obj.setValue("");
            }

            var opt = mini.get("opt");
            opt.on("valuechanged", function (e) {

                if( "add" == this.getValue())
                {
                   mini.get("parrentId").setEnabled(false);
                   mini.get("parrentId").setText("");
                }
                else if("update" == this.getValue())
                {
                    mini.get("parrentId").setEnabled(true);
                }
            });
        </script>
    </body>
</html>
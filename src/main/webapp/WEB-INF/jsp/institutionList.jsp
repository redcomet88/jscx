<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>机构管理</title>
        <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet" />
    </head>
    <body id="jgBody" style="display: none">
        <div class="mini-toolbar" style="padding:2px;border-bottom:0;">
            <table style="width:50%;table-layout:fixed;">
                <tr>
                    <td style="width:99%;">
                        <a class="mini-button" id="add" onclick="onAddBefore()" style="width:110px;">插入节点(前)</a>
                        <a class="mini-button" id="add" onclick="onAddAfter()" style="width:110px;">插入节点(后)</a>
                        <a class="mini-button" id="add" onclick="onAddNode()" style="width:110px;">插入子节点</a>
                        <a class="mini-button" id="add" onclick="onEditNode()" style="width:90px;">编辑节点</a>
                        <a class="mini-button" id="add" onclick="onRemoveNode()" style="width:90px;">删除节点</a>
                        <span class="separator"></span>
                        <a class="mini-button" id="save" onclick="saveData()" style="width:90px;font-weight:bold;">保存</a>
                    </td>
                </tr>
            </table>
            <table style="width:50%;table-layout:fixed;">
                <tr>
                    <td>
                        <ul id="jgTree" class="mini-tree" url="<%=request.getContextPath()%>/ajax/user_getJgList.do" style="width:200px;padding:5px;" 
                        showTreeIcon="true" textField="jgmc" idField="jgh" parentField="sjjg" resultAsTree="false"
                        allowDrag="true" allowDrop="true" expandOnLoad="0">
                        </ul>
                    </td>
                </tr>
            </table>
        </div>
        <div style="display:none;width:350px;" id="jgDiv" title="机构信息" class="mini-window">
            <table border="0" cellpadding="8" style="width:100%;table-layout:fixed;">
                <tr>
                    <td style="width:60px;">机构号：</td>
                    <td style="width:90%;">
                        <input id="jgh" name="jgh" class="mini-textbox" style="width:90%;"/>
                    </td>
                </tr>
                <tr>
                    <td>机构名：</td>
                    <td>
                        <input id="jgmc" name="jgmc" class="mini-textbox" style="width:90%;"/>
                        <input id="action" name="action" class="mini-hidden"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <a class="mini-button" id="save" onclick="save" style="margin-left:60px;">保存</a>
                        <a class="mini-button" onclick="close">取消</a>
                    </td>
                </tr>
            </table>
        </div>
        <script type="text/javascript">
            mini.parse();
            $("#jgBody").fadeTo("slow", 1);
            var tree = mini.get("jgTree");
            var jgDiv = mini.get("jgDiv");
            var node;
            var addJghs = [];
            function save(e) {
                var jgh = mini.get("jgh").getValue();
                var jgmc = mini.get("jgmc").getValue();
                var action = mini.get("action").getValue();
                if (action == "") {
                    node.jgh = jgh;
                    node.jgmc = jgmc;
                    tree.updateNode(node);
                    close();
                } else {
                    // 检查机构是否重复
                    $.ajax({
                        url: "<%=request.getContextPath()%>/ajax/jg_checkJgsfcf.do",
                        data:{jgh: jgh},
                        type: "post",
                        async: false,
                        dataType: 'text',
                        success: function (text) {
                            if (text == "FAIL") {
                                mini.alert("机构号已经存在，请换一个机构号");
                            } else {
                                // 验证添加的机构号是否跟其他新加的重复
                                for (var i = 0; i < addJghs.length; i++) {
                                    if (addJghs[i] == jgh) {
                                        mini.alert("机构号已经存在，请换一个机构号");
                                        return false;
                                    }
                                }
                                addJghs.push(jgh);
                                var newNode = {"jgh": jgh, "jgmc": jgmc};
                                tree.addNode(newNode, action, node);
                                tree.updateNode(node);
                                close();
                            }
                        }
                    });
                }
            }

            // 关闭弹出窗口时清空机构信息
            function close() {
                mini.get("jgh").setValue("");
                mini.get("jgmc").setValue("");
                mini.get("action").setValue("");
                jgDiv.hide();
            }
            
            // 在选择节点前增加
            function onAddBefore(e) {
                node = tree.getSelectedNode();
                if (node == null) {
                    mini.alert("请选择一个节点");
                } else {
                    if (node.sjjg == "0706678KJ") {
                        mini.get("jgh").setAllowInput(true);
                        mini.get("action").setValue("before");
                        jgDiv.show();
                    } else {
                        mini.alert("不能在这个机构前创建新的机构");
                    }
                }
            }
            
            // 在选择节点后增加
            function onAddAfter(e) {
                node = tree.getSelectedNode();
                if (node == null) {
                    mini.alert("请选择一个节点");
                } else {
                    if (node.sjjg == "0706678KJ") {
                        mini.get("jgh").setAllowInput(true);
                        mini.get("action").setValue("after");
                        jgDiv.show();
                    } else {
                        mini.alert("不能在这个机构后创建新的机构");
                    }
                }
            }
            
            // 在选择节点下增加一个子节点
            function onAddNode(e) {
                node = tree.getSelectedNode();
                if (node == null) {
                    mini.alert("请选择一个节点");
                } else {
                    if (node.jgh == "0706678KJ") {
                        mini.get("jgh").setAllowInput(true);
                        mini.get("action").setValue("add");
                        jgDiv.show();
                    } else {
                        mini.alert("不能在这个机构下创建新的机构");
                    }
                }
            }
            
            // 编辑节点
            function onEditNode(e) {
                node = tree.getSelectedNode();
                if (node == null) {
                    mini.alert("请选择一个节点");
                } else {
                    if (node.sjjg == "0706678KJ") {
                        mini.get("jgh").setValue(node.jgh);
                        mini.get("jgh").setAllowInput(false);
                        mini.get("jgmc").setValue(node.jgmc);
                        mini.get("action").setValue("");
                        jgDiv.show();
                    } else {
                        mini.alert("不能编辑这个机构");
                    }
                }
            }
            
            // 删除节点
            function onRemoveNode(e) {
                var node = tree.getSelectedNode();
                if (node == null) {
                    mini.alert("请选择一个节点");
                } else {
                    if (node.sjjg == "0706678KJ") {
                        mini.confirm("确定删除选中节点?", "确定？", function(action) {
                            if (action == "ok") {
                                tree.removeNode(node);
                            }
                        });
                    } else {
                        mini.alert("不能删除这个机构");
                    }
                }
            }

            // 保存机构信息
            var msgid;
            function saveData() {
                var data = tree.getData();
                var removed = tree.getChanges("removed");
                var json = mini.encode(data);
                var removedJson = mini.encode(removed);

                msgid = mini.loading("数据保存中，请稍后......", "保存数据");
                mini.confirm("确定保存机构?", "确定？", function(action) {
                    if (action == "ok") {
                        $.ajax({
                            url: "<%=request.getContextPath()%>/ajax/jg_saveJgData.do",
                            data: { data: json, removed: removedJson },
                            type: "post",
                            dataType: 'text',
                            success: function (text) {
                                mini.hideMessageBox(msgid);
                                mini.alert("机构保存成功");
                                addJghs = [];
                            }
                        });
                    }
                });
            }
        </script>
    </body>  
</html>  
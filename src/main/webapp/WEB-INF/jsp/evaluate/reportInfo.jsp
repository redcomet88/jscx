<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>中层干部述职信息浏览</title>
    <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
    <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet" />
    <link href="../demo.css" rel="stylesheet" type="text/css" />
    <style type="text/css">
        .mini-radiobuttonlist table label{
            width:70px;
        }
    </style>
</head>
<body>
<div class="mini-fit">
    <!-- 内联的详细信息框 -->
    <div id="editForm1" style="display:none;padding:5px;">

        <input class="mini-hidden" name="id"/>
        <table style="width:100%;">
            <!--<tr>
                <td style="width:180px;" rowspan="3"> <img id="teacher_img" style="width:100px;height:140px" src=""/></td>
                <td style="width:120px;text-align:center;">工号：</td>
                <td style="width:150px;"><input name="user.dah" class="mini-textbox" style="border: 0px;outline:none;"/></td>
            </tr>-->
            <tr>
                <td style="width:105px;" rowspan="3"> <img id="teacher_img" style="width:100px;height:140px" src=""/></td>
                <td style="width:120px;text-align:center;">工号：</td>
                <td><span class="mini-textbox-border" style="border: 0px;background-color: transparent"><input id="dah1" type="text" class="mini-textbox-input" autocomplete="off" placeholder=""  name="user.dah" disabled="true"></span></td>
            </tr>
            <tr>
                <td style="width:120px;text-align:center;">职务：</td>
                <td style="width:720px;"><span class="mini-textbox-border" style="width:720px;border: 0px;background-color: transparent"><input id="jgh1" type="text" class="mini-textbox-input" autocomplete="off" placeholder="" disabled="true"></span></td>
            </tr>
            <tr>
                <td style="width:150px;text-align:center;">述职报告：</td>
                <td style="width:150px;"><a id="view_report" class="mini-button" iconCls="icon-search"  href="">查看</a></td>
            </tr>
        </table>
    </div>

    <div id="datagrid1" class="mini-datagrid"  allowAlternating="true" style="width:100%;height:100%;"
         url="<%=request.getContextPath()%>/ajax/evaluate_evaluateReportList.do"
         allowResize="true" idField="id" pageSize="10"
         allowCellEdit="true" allowCellSelect="true"
         onshowrowdetail="onShowRowDetail" >
        <div property="columns">
            <div type="expandcolumn" >#</div>
            <div type="indexcolumn" width="40" headerAlign="center">序号</div>
            <div  width="120" align="center" headerAlign="center" allowSort="false" renderer="onNameRenderer">姓名</div>
            <div type="comboboxcolumn" autoShowPopup="true" field="zzsx" width="100" allowSort="true"  align="center" headerAlign="center">政治思想
                <!--  <input property="editor" class="mini-combobox"  style="width:100%;" data="eva_option"/> -->
            </div>
            <div type="comboboxcolumn" autoShowPopup="true" field="ywzs" width="100" allowSort="true"  align="center" headerAlign="center">业务知识
                <!--  <input property="editor" class="mini-combobox"  style="width:100%;" data="eva_option"/> -->
            </div>
            <div type="comboboxcolumn" autoShowPopup="true" field="gztd" width="100" allowSort="true"  align="center" headerAlign="center">工作态度
                <!--  <input property="editor" class="mini-combobox"  style="width:100%;" data="eva_option"/> -->
            </div>
            <div type="comboboxcolumn" autoShowPopup="true" field="wcgz" width="100" allowSort="true"  align="center" headerAlign="center">完成工作
                <!--  <input property="editor" class="mini-combobox"  style="width:100%;" data="eva_option"/> -->
            </div>
            <div type="comboboxcolumn" autoShowPopup="true" field="zjsf" width="100" allowSort="true"  align="center" headerAlign="center">遵纪守法
                <!--  <input property="editor" class="mini-combobox"  style="width:100%;" data="eva_option"/> -->
            </div>
            <div type="comboboxcolumn" autoShowPopup="true" field="zhpj" width="100" allowSort="true"  align="center" headerAlign="center">综合评价
                <!--  <input property="editor" class="mini-combobox"  style="width:100%;" data="eva_option"/> -->
            </div>
            <div type="comboboxcolumn" autoShowPopup="true" field="rzjy" width="100" allowSort="true"  align="center" headerAlign="center">任职建议
                <!--  <input property="editor" class="mini-combobox"  style="width:100%;" data="job_option"/> -->
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">
    var eva_option = [{id: 0, text:'未填写' }, { id: 1, text: '优秀' }, { id: 2, text: '称职'},{id: 3, text:'基本称职'},{id: 4, text:'不称职'}];
    var job_option = [{id: 0, text:'未填写' },{ id: 1, text: '提拔' }, { id: 2, text: '留任'},{id: 3, text:'调整'}];

    //var eva_option = [{id: 0, text:'未填写' }, { id: 1, text: '优秀' }, { id: 2, text: '称职'},{id: 3, text:'基本称职'},{id: 4, text:'不称职'}];
    //var job_option = [{id: 0, text:'未填写' },{ id: 1, text: '提拔' }, { id: 2, text: '留任'},{id: 3, text:'调整'}];


    mini.parse();
    var editForm = document.getElementById("editForm1");

    var grid = mini.get("datagrid1");
    grid.load();



    //貌似取不到这个id值,我们采取折中办法
    /*
    var zhpj = mini.get("zhpj");
    zhpj.setIdField(1);
    */

    function onNameRenderer(e) {
        var grid = e.sender;
        var record = e.record;
        var uid = record._uid;
        var rowIndex = e.rowIndex;
        var path = "/resources/fileupload/report/" +  record.evaDah + ".swf";

        var s  = '<a class="Edit_Button" style="color:dodgerblue;font-size:14px;" href="javascript:previewRow(\'' + path + '\')">' + record.evaUserName + '</a>';

        return s;
    }

    function onSelectionChanged(e) {
        var grid = e.sender;
        var record = grid.getSelected();
        if (record) {
            editRow(record._uid);
        } else {
            form.reset();
        }
    }

    function onShowRowDetail(e) {
        var row = e.record;

        //将editForm元素，加入行详细单元格内
        var td = grid.getRowDetailCellEl(row);
        td.appendChild(editForm);
        editForm.style.display = "";

        //述职报告地址生成
        var path = "/resources/fileupload/report/" +  row.evaDah + ".swf";

        //表单加载员工信息
        var form = new mini.Form("editForm1");
        var teacher_img = mini.get("teacher_img");

        if (grid.isNewRow(row)) {
            form.reset();
        } else {
            grid.loading();
            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/user_getUser.do",
                data: { dah:  row.evaDah },
                success: function (text) {
                    var o = mini.decode(text);
                    o.jgh = row.evaZw;
                    form.setData(o);
                    // $("#dah").innerText = o.user.dah;
                    // $("#dah").attr("innerHTML",o.user.dah);
                    $("#dah1").attr("value",o.user.dah);
                    $("#jgh1").attr("value",o.jgh);


                    $("#teacher_img").attr("src","<%=request.getContextPath()%>/resources/images/teacher/" + o.user.dah  + ".jpg");
                    //teacher_img.src = "<%=request.getContextPath()%>/resources/images/teacher/" + o.user.dah + ".jpg";
                    $("#view_report").attr("onclick","javascript:previewRow('" + path + "')");

                    grid.unmask();
                }
            });
        }
    }

    //预览
    function previewRow(path){
        if(path.endsWith(".txt") || path.endsWith(".html") || path.endsWith(".xml"))
            window.open("<%=request.getContextPath()%>"+path);
        else
            window.open("<%=request.getContextPath()%>/previewJobReport.do?path="+path);
    }

    function editRow(row_uid) {
        var row = grid.getRowByUID(row_uid);
        if (row) {

            var form = new mini.Form("editForm1");
            if (grid.isNewRow(row)) {
                form.reset();
            } else {
                form.loading();
                $.ajax({
                    url: "<%=request.getContextPath()%>/ajax/user_getUser.do",
                    data: { dah:  row.evaDah },
                    success: function (text) {
                        var o = mini.decode(text);
                        form.setData(o);
                        form.unmask();
                    }
                });
            }
            grid.doLayout();
        }
    }

</script>
</body>
</html>
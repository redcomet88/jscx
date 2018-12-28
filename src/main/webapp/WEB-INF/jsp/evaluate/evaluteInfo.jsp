<%@page language="java"  pageEncoding="utf8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>中层干部民主评测表</title>
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

        <div class="mini-toolbar" style="padding:2px;border-bottom:0;">
            <table style="width:100%;table-layout:fixed;">
                <tr>
                    <td style="width:70%"></td>
                    <td style="width:120px">
                        <a class="mini-button" style="width:140px"  id="choose" onclick="chooseAll()">全选合格</a>
                    </td>
                    <td style="width:120px">
                        <a class="mini-button" style="width:140px"  id="subm" onclick="submitTen()">提交评测</a>
                    </td>
                </tr>

            </table>
        </div>

        <div id="datagrid1" class="mini-datagrid"  allowAlternating="true" style="width:100%;height:100%;"
             url="<%=request.getContextPath()%>/ajax/evaluate_evaluateRecordList.do"
             allowResize="true" idField="id" pageSize="10" showPager="false"
             allowCellEdit="true" allowCellSelect="true"
             onshowrowdetail="onShowRowDetail" >
            <div property="columns">
                <div type="expandcolumn" >#</div>
                <div type="indexcolumn" width="40" headerAlign="center">序号</div>
                <div  width="120" align="center" headerAlign="center" allowSort="false" renderer="onNameRenderer">姓名</div>
                <div type="comboboxcolumn" autoShowPopup="true" field="zzsx" width="100" allowSort="true"  align="center" headerAlign="center">政治思想
                   <input property="editor" class="mini-combobox"  id="combo_eva" style="width:100%;" data="eva_option"/>
                </div>
                <div type="comboboxcolumn" autoShowPopup="true" field="ywzs" width="100" allowSort="true"  align="center" headerAlign="center">业务知识
                      <input property="editor" class="mini-combobox"  style="width:100%;" data="eva_option"/>
                </div>
                <div type="comboboxcolumn" autoShowPopup="true" field="gztd" width="100" allowSort="true"  align="center" headerAlign="center">工作态度
                      <input property="editor" class="mini-combobox"  style="width:100%;" data="eva_option"/>
                </div>
                <div type="comboboxcolumn" autoShowPopup="true" field="wcgz" width="100" allowSort="true"  align="center" headerAlign="center">完成工作
                      <input property="editor" class="mini-combobox"  style="width:100%;" data="eva_option"/>
                </div>
                <div type="comboboxcolumn" autoShowPopup="true" field="zjsf" width="100" allowSort="true"  align="center" headerAlign="center">遵纪守法
                      <input property="editor" class="mini-combobox"  style="width:100%;" data="eva_option"/>
                </div>
                <div type="comboboxcolumn" autoShowPopup="true" field="zhpj" width="100" allowSort="true"  align="center" headerAlign="center">综合评价
                      <input property="editor" class="mini-combobox"  style="width:100%;" data="eva_option"/>
                </div>
                <div type="comboboxcolumn" autoShowPopup="true" field="rzjy" width="100" allowSort="true"  align="center" headerAlign="center">任职建议
                      <input property="editor" class="mini-combobox"  style="width:100%;" data="job_option" />
                </div>
            </div>
        </div>

    </div>
    <script type="text/javascript">
       var eva_option = [{id: 0, text:'未填写' }, { id: 1, text: '优秀' }, { id: 2, text: '合格'},{id: 3, text:'基本合格'},{id: 4, text:'不合格'}];
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

       function onComboValidation(e) {
           var items = this.findItems(e.value);
           console.log(e.value);
           if (!items || items.length == 0 || e.value == 1.0 ) {
               e.isValid = false;
               e.errorText = "存在未填写的内容，请检查";
           }
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

        //全部选择优秀
       function chooseAll() {
           grid.load({ 'choose': '1'});
       }

       //提交
       function submitTen(){
          var numb = 0;
          if(grid.pageSize > grid.totalCount)
              numb = grid.totalCount;
          else
              numb = grid.pageSize;

          for (var i=0;i<numb;i++) {
               var x = new Object();
               x = grid.data[i];
               if(x.zzsx == 0.0 || x.ywzs == 0.0 || x.gztd == 0.0 || x.wcgz == 0.0 || x.zjsf == 0.0 || x.zhpj == 0.0
                   || x.rzjy == 0.0 ){
                   mini.alert("第[" + (i+1) +"]行尚有未选择的选项，请您选择后提交");
                   //console.log(i);
                   return;
               }
           }
           //console.log("下来了");

           for (var i=0;i<numb;i++){
              var data = grid.data[i];
              data.finished = 1;
              //console.log(data);
              $.ajax({
                   url: "<%=request.getContextPath()%>/ajax/evaluate_updateEvaRecord.do",
                   type: "post",
                   dataType: 'text',
                   data: data,
                  success: function (result) {

                }
              });
               //grid.load({ 'choose': '0'});
           }
           //这里应该 grid reload一下
           grid.load({ 'choose': '0'});
       }

       //检查是否有未选择的
       function checkChosen() {
           for (var i=0;i<grid.totalCount;i++) {
               var x = new Object();
               x = grid.data[i];
               if(grid.data[i].zzsx == 0.0 || x.ywzs == 0.0 || x.gztd == 0.0 || x.wcgz == 0.0 || x.zjsf == 0.0 || x.zhpj == 0.0
                   || x.rzjy == 0.0 ){
                   alert("第[" + (i+1) +"]行尚有未选择的选项，请您选择后提交");
               }
           }
       }

       grid.on("load",function(e){
           var  num=grid.getData().length;
           console.log(num);
           if(num == 0)
               mini.alert("您已完成此次评测，谢谢！");
           return true;

       })


    </script>
    </body>
</html>
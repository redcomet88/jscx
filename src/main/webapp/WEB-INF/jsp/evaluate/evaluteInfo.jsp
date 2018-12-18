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
        <div id="datagrid1" class="mini-datagrid"  allowAlternating="true" style="width:100%;height:100%;"
             url="<%=request.getContextPath()%>/ajax/credit_creditIndexList.do"
             allowResize="true" idField="id" pageSize="50"
             allowCellEdit="true" allowCellSelect="true" >
            <div property="columns">
                <div type="indexcolumn" width="40" headerAlign="center">序号</div>
                <div field="eva_dah" name="eva_dah" id = "applyuserName" width="120" align="center" headerAlign="center" allowSort="false">姓名</div>
                <div type="comboboxcolumn" autoShowPopup="true" field="zzsx" width="100" allowSort="true"  align="center" headerAlign="center">政治思想
                   <input property="editor" class="mini-combobox" style="width:100%;" data="eva_option"/>
                </div>
                <div type="comboboxcolumn" autoShowPopup="true" field="ywzs" width="100" allowSort="true"  align="center" headerAlign="center">业务知识
                    <input property="editor" class="mini-combobox" style="width:100%;" data="eva_option"/>
                </div>
                <div type="comboboxcolumn" autoShowPopup="true" field="gztd" width="100" allowSort="true"  align="center" headerAlign="center">工作态度
                    <input property="editor" class="mini-combobox" style="width:100%;" data="eva_option"/>
                </div>
                <div type="comboboxcolumn" autoShowPopup="true" field="wcgz" width="100" allowSort="true"  align="center" headerAlign="center">完成工作
                    <input property="editor" class="mini-combobox" style="width:100%;" data="eva_option"/>
                </div>
                <div type="comboboxcolumn" autoShowPopup="true" field="zjsf" width="100" allowSort="true"  align="center" headerAlign="center">遵纪守法
                    <input property="editor" class="mini-combobox" style="width:100%;" data="eva_option"/>
                </div>
                <div type="comboboxcolumn" autoShowPopup="true" field="zhpj" width="100" allowSort="true"  align="center" headerAlign="center">综合评价
                    <input property="editor" id="zhpj" class="mini-combobox" style="width:100%;" data="eva_option"/>
                </div>
                <div type="comboboxcolumn" autoShowPopup="true" field="rzjy" width="100" allowSort="true"  align="center" headerAlign="center">任职建议
                    <input property="editor" class="mini-combobox" style="width:100%;" data="job_option"/>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        var eva_option = [{ id: 1, text: '优秀' }, { id: 2, text: '称职'},{id: 3, text:'基本称职'},{id: 4, text:'不称职'}];
        var job_option = [{ id: 1, text: '提拔' }, { id: 2, text: '留任'},{id: 3, text:'调整'}];

        mini.parse();

        var grid = mini.get("datagrid1");
        grid.load();

        //貌似取不到这个id值,我们采取折中办法
        /*
        var zhpj = mini.get("zhpj");
        zhpj.setIdField(1);
        */


    </script>
    </body>
</html>
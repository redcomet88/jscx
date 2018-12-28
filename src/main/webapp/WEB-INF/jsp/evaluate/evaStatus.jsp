<%@page language="java" pageEncoding="utf8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>诚信驾驶舱</title>
    <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
    <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet"/>
    <script src="<%=request.getContextPath()%>/resources/echarts/echarts.js"></script>
    <link href="<%=request.getContextPath()%>/resources/css/c.css" rel="stylesheet" type="text/css" />

</head>
<body id="pageBody" style="display: none">
<div >
    <div style="float:left;width:19%;height:80px;margin-right:1%;">
        <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="td5" rowspan="2" width="20%">&nbsp;</td>
                <td  class="td1"><span class="c">总评测人数</span></td>
            </tr>
            <tr>
                <td  class="td1"><span class="c">${totalSummary.totalNum }</span></td>
            </tr>
        </table>
    </div>
    <div style="float:left;width:19%;height:80px;margin-right:1%;">
        <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="td6" rowspan="2" width="20%">&nbsp;</td>
                <td class="td2"><span class="c">已完成</span></td>
            </tr>
            <tr>
                <td class="td2"><span class="c">${totalSummary.processNum }</span></td>
            </tr>
        </table>
    </div>
    <div style="float:left;width:19%;height:80px;margin-right:1%;">
        <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="td7" rowspan="2" width="20%">&nbsp;</td>
                <td class="td3"><span class="c">未完成</span></td>
            </tr>
            <tr>
                <td class="td3"><span class="c">${totalSummary.finishNum }</span></td>
            </tr>
        </table>
    </div>
    <!--
    <div style="float:left;width:19%;height:80px;margin-right:1%;">
        <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="td10" rowspan="2" width="20%">&nbsp;</td>
                <td class="td9"><span class="c">失信人次</span></td>
            </tr>
            <tr>
                <td class="td9"><span class="c">${totalSummary.unCreditNum }</span></td>
            </tr>
        </table>
    </div>
    <div style="float:left;width:19%;height:80px;margin-right:1%;">
        <table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
                <td class="td8" rowspan="2" width="20%">&nbsp;</td>
                <td class="td4"><span class="c">预留</span></td>
            </tr>
            <tr>
                <td class="td4"><span class="c">${totalSummary.conflictNum }</span></td>
            </tr>
        </table>
    </div> -->
</div>




<script type="text/javascript">
    mini.parse();
    $("#pageBody").fadeTo("slow", 1);
    var demoChart;
    var activeTabName ="totalSum";

    // 图表开始
    require.config({
        paths: {
            echarts : '${path}/resources/echarts'
        }
    })
    function initChart(){
        // 图表开始
        require([ 'echarts','echarts/chart/line','echarts/chart/bar'], function(ec) {
            // 绘制饼图

            if(activeTabName =="totalSum"){
                demoChart  = ec.init(document.getElementById('summaryChart'));
            }else{
                demoChart  = ec.init(document.getElementById('summaryChart2'));
                var ecConfig = require('echarts/config');
                demoChart.on(ecConfig.EVENT.CLICK, ecClick);
            }

            doSearch();

        });
        return true;
    }
    $(document).ready(function() {
        initChart();


    });

    function onKeyEnter(e) {
        doSearch();
    }

    //查询
    function doSearch(){
        //获取查询条件参数
        var searchFields = getSearchFields();
        var url ="${path}/ajax/zcgl_getTotalSummaryByDate.do" ;
        if(activeTabName == "zcProportion"){
            //按照分类统计
            url ="${path}/ajax/zcgl_getZcTopTypeStatistic.do";
            searchFields={};
            searchFields.modelFlag = 0;
        }
        demoChart.showLoading();
        $.ajax({
            url: url,
            type: "get",
            dataType: 'text',
            data : searchFields ,
            success: function (result) {
                result = mini.decode(result);
                if(result.total != 0)
                    displayData(result,searchFields);
                else{
                    mini.alert("未查询到数据");
                    demoChart.hideLoading();
                    demoChart.clear();
                }
                <%--displayData(result,searchFields);--%>
            }
        });

    }

    function getSearchFields(){

        var jghs = mini.get("jgInpt").getValue();

        var searchFields = new Object();
        searchFields.statisticType = mini.get("statisticTypeInpt").getValue();
        if( jghs != ""){
            searchFields.jghs=jghs;
        }
        return searchFields;
    }

    // //解析数据，展示图表
    function displayData(resultData,searchFields){
        var dataList = resultData.data;
        var size = resultData.total;
        var xAxis_data =[];
        var series_data=[];
        var myseries =[];
        var option ;
        var legend_data=['总数量'];//默认为总数量

        if(activeTabName == "totalSum"){

            if(searchFields.statisticType == "totalNum"){
                //按照机构部门统计
                for(var i = 0 ; i<size; i++){
                    xAxis_data[i] = dataList[i].createDate;
                    series_data[i]=dataList[i].totalNum;
                }
                var myserie = new Object();
                myserie.data=series_data;
                myserie.markPoint= { data : [ {type : 'max', name: '最大值'},{type : 'min', name: '最小值'}]}
                myserie.markLine ={data:[{type : 'average', name: '平均值'}]};
                myserie.name='总数量';
                myserie.type='line';
                myserie.itemStyle={normal:{color:'#3399FF'}};
                myseries[0]=myserie;
            } else if(searchFields.statisticType == "totalAmount") {
                //统计金额
                legend_data=['总金额'];
                for(var i = 0 ; i<size; i++){
                    xAxis_data[i] = dataList[i].createDate;
                    series_data[i]=dataList[i].totalAmount;
                }

                var myserie = new Object();
                myserie.data=series_data;
                myserie.markPoint= { data : [ {type : 'max', name: '最大值'},{type : 'min', name: '最小值'}]}
                myserie.markLine ={data:[{type : 'average', name: '平均值'}]};
                myserie.name='总金额';
                myserie.type='line';
                myserie.itemStyle={normal:{color:'#66CC99'}};

                myseries[0]=myserie;

            }

            //折线图表配置参数对象
            option = {
                title : {
                    text: '分类图',
                    subtext: ''
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:legend_data
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType : {show: true, type: [ 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                dataZoom : {
                    show : true,
                    realtime: true,
                    start : 1,
                    end : 100
                },
                xAxis : [
                    {
                        type : 'category',
                        boundaryGap : false,
                        data :xAxis_data
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        axisLabel : {
                            formatter: '{value}'
                        }
                    }
                ],
                series : [
                    {
                        name:'',
                        type:'line',
                        data:series_data,
                        markPoint : {
                            data : [
                                {type : 'max', name: '最大值'},
                                {type : 'min', name: '最小值'}
                            ]
                        },
                        markLine : {
                            data : [
                                {type : 'average', name: '平均值'}
                            ]
                        }
                    }
                ]
            };


        }else {
            //资产分类占比
            //柱状图表配置参数
            var yAxis_data = [];
            var totalNum = ${totalSummary.totalNum };
            legend_data=['']
            //按分类汇总
            for(var i = 0; i < size; i++) {
                yAxis_data[i] = dataList[i].topTypeName;
                var one = new Object();
                one.name = dataList[i].topTypeName;
                one.value = ((dataList[i].totalNum/totalNum).toFixed(5)*100);
                one.typeIds=dataList[i].typeId;
                series_data[i] = one;
            }
            var myserie = new Object();
            myserie.data=series_data;
            myserie.name='';
            myserie.type='bar';
            myserie.itemStyle={normal:{color:'#3399FF'}};
            myseries[0]=myserie;

            option = {
                title : {
                    text: '总数分类占比',
                    subtext: ''
                },
                tooltip : {
                    trigger: 'axis'
                },
                legend: {
                    data:legend_data
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        magicType: {show: true, type: [ 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'value',
                        axisLabel : {
                            formatter: '{value} %'
                        }
                    }
                ],
                yAxis : [
                    {
                        type : 'category',
                        data :yAxis_data,
                    }
                ],
                series :myseries
            };


        }

        demoChart.clear();
        if(size >0) {
            option.series=myseries;
            demoChart.setOption(option, false);
        }else{
            demoChart.setOption(option, false);
        }
        demoChart.hideLoading();
    }

    //当统计类型改变时
    function onstatisticTypeChanged(e){
        doSearch();
    }

    function onJgChanged(e){
        doSearch();
    }


    function onActiveChanged(e){
        var tab = e.tab;
        activeTabName=tab.name;
        initChart();
    }

    //echarts点击事件
    function ecClick(param) {

        var data = param.data;
        if(data.name == "最大值" || data.name == "最小值" || data.name == "平均值") {
            return;
        }
        var searchFields = getSearchFields();
        searchFields.typeIds = data.typeIds;
        parent.showTabForUser("brandStatistic",'分类占比图',"<%=request.getContextPath()%>/showZcStatisticOfType.do?searchFields=" + mini.encode(searchFields));
    }
</script>
</body>
</html>
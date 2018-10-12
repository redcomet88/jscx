home.jsp<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf8"%>
<div class="main">
    <!--Tabs-->
    <div id="mainTabs" class="mini-tabs bg-toolbar main-tabs" activeIndex="0" style="width:100%;height:100%;" plain="false" contextMenu="#tabsMenu">
        <div title="首页" name="first" showCloseButton="false" class="calendarDiv">
            <div class="duty_mgmt">
                <div class="subfield">快捷菜单</div>

            </div>
            <div class="duty_mgmt">
                <div class="subfield">我的待办事项</div>
                <div class="tab-container" style="width: 97%;">
                    <div id="header">
                        <ul class="tabList" id="ul_all">
                            <li id="li_all" class="selected">
                                <a href="javascript:void(0)">全部(<span id="all">${allWorkItemCount}</span>)</a>
                            </li>
                            <li id="li_caigou">
                                <a href="javascript:void(0)">审批(<span id="caigou">${cgWorkItemCount}</span>)</a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">事件(<span id="event">${eventWorkItemCount}</span>)</a>
                            </li>

                        </ul>
                    </div>
                    <div class="content">
                        <div class="prompt_message" style="display: none">
                            <span>没有待办事项。</span>
                        </div>
                        <div class="work_message" style="display: none">
                            <ul id="workItems">
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<ul id="tabsMenu" class="mini-contextmenu" onbeforeopen="onBeforeOpen" style="display:none">
    <li iconCls="icon-close" onclick="closeTab">关闭当前标签页</li>
    <li iconCls="icon-upgrade" onclick="closeLeftBut">关闭左边标签[首页除外]</li>
    <li iconCls="icon-downgrade" onclick="closeRightBut">关闭右边标签</li>
    <li iconCls="icon-remove" onclick="closeAll">关闭其他[首页除外]</li>
    <li iconCls="icon-goto" onclick="closeAllOther">关闭所有[首页除外]</li>
</ul>
<script type="text/javascript">
    mini.parse();
    var searchFlag = true;
    var grid = mini.get("ckSummaryDataGrid");
    
    var lcsqFlag = ${lcsqFlag};
    if(lcsqFlag){
        $("#lcsq").show();
    }

    //处理ajax异常
    $(document).ajaxComplete(function(evt, request, settings) {
        var text = request.responseText;
        var obj = mini.decode(text);
        if (obj.error != 0 && obj.error != undefined) {
            mini.alert(obj.message, "提醒", function(e) {
                window.parent.removeTab("");
            });
        }
    });

    $(document).ready(function() {
        createMessage(0);
    });

    //tab的右键功能
    var tabs = mini.get("mainTabs");
    var currentTab = null;
    function onBeforeOpen(e) {
        currentTab = tabs.getTabByEvent(e.htmlEvent);
        if (!currentTab) {
            e.cancel = true;
        }
    }

    //关闭当前
    function closeTab() {
        if(tabs.getTab("first") != currentTab ){
            tabs.removeTab(currentTab);
        } else {
            mini.alert("抱歉，首页标签无法关闭");
        }
    }

    //关闭其他[首页除外]
    function closeAll() {
        var but = [currentTab];
        but.push(tabs.getTab("first"));
        tabs.removeAll(but);
    }

    //关闭所有
    function closeAllOther(){
         var but = [];
         but.push(tabs.getTab("first"));
         tabs.removeAll(but);
    }

    //关闭左边
    function closeLeftBut() {
       var tabArray = mini.get("mainTabs").getTabs ();
        var cur = currentTab;
        var but = [currentTab];
        but.push(cur);
        but.push(tabs.getTab("first"));
         var index = cur._id;
         for(var i = 0; i < tabArray.length ; i++){
             if(tabArray[i]._id > index){
                 but.push(tabArray[i]);
             }
         }
         tabs.removeAll(but);
    }

    //关闭右边
    function closeRightBut() {
      var tabArray = mini.get("mainTabs").getTabs();
      var cur = currentTab;
      var but=[cur]; 
       var index = cur._id;
       for(var i = 0; i < tabArray.length ; i++){
           if(tabArray[i]._id < index){
              but.push(tabArray[i]);
           }
       }
       tabs.removeAll(but);
    }

    $(".tabList > li").click(function () {
        //待办下标，0:全部待办 1:仓库待办 2:事件服务待办
        var index = $(this).index();
        $(".tabList > .selected").removeClass("selected");
        $(this).addClass("selected");
        createMessage(index);
    });

    //动态显示首页待办

    //显示待办处理页面
    function showOpeartePage(key, id, processId, type, status) {
        if(type == "入库") {
            showMenuPage(0);
            setTimeout("rkOpen('" + id +"');", 1000);
        }else if(type == "出库") {
            showMenuPage(1);
            setTimeout("ckOpen('" + id +"');", 1000);
        }else if(type == "替班" || type == "换班") {
            showMenuPage(3);
        }else if(type == "事件" || type == "变更" || type == "问题" || type == "批量上线") {
            showMenuPage(2);
            eventOpearte(id);
        } else if(type == "采购") {
            showMenuPage(4);
            setTimeout("cgOperate('" + key + "','" + id + "','" + status + "')", 1000);
        } else if(type == "督查督办") {
            showMenuPage(5);
            setTimeout("dcdbOperate('" + key + "','" + id + "','" + status + "')", 1000);
        } else if(type == "合理化建议") {
            showMenuPage(6);
            setTimeout("suggestOperate('" + key + "','" + id + "','" + status + "')", 1000);
        } else if(type == "理财") {
            showMenuPage(7);
            setTimeout("lcOperate('" + key + "','" + id + "','" + status + "')", 1000);
        } else if (type == "印控") {
            
            showMenuPage(8);
            setTimeout("scOperate('" + id + "','" + status + "')", 1000);
        } else if (type == "董事会"){
        	
        	showMenuPage(9);
        	setTimeout("dshOperate('" + id + "','" + status + "')", 1000);
        }
    }

    //打开menu页面
    function showMenuPage(flag) {
        ajaxJson("<%=request.getContextPath()%>/ajax/user_getMenu.do", true, {flag: flag}, function(data) {
            var menu = mini.decode(data);
            parent.showTab(menu);
        });
    }

    //ajax请求post方法
    function ajaxJson(url, async, params, callback) {
        $.ajax({
            type: "post",
            dataType: 'text',
            async: async,
            url: url,
            data: params,
            success: function(data) {
                callback(data);
            }
        })
    }
</script>
home.jsp<%@page language="java" contentType="text/html; charset=gbk" pageEncoding="utf8"%>
<div class="main">
    <!--Tabs-->
    <div id="mainTabs" class="mini-tabs bg-toolbar main-tabs" activeIndex="0" style="width:100%;height:100%;" plain="false" contextMenu="#tabsMenu">
        <div title="首页" name="first" showCloseButton="false" class="calendarDiv">
            <div class="duty_mgmt">
                <div class="subfield">快捷菜单</div>
                <jsp:include page="mainScrollMenu.jsp"/>
                <%--<div width="99%">--%>
                    <%--<ul class="menu-list">--%>
                        <%--<li>--%>
                            <%--<a href="#" style=" text-decoration: none;" onclick="showTabForUser('cgInfoAdd','采购新增', 'showCgInfoApply.do?flag=add')">--%>
                                <%--<i class="menu-icon search"></i>--%>
                                <%--<span>采购申请</span>--%>
                            <%--</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<a href="#" style=" text-decoration: none;" onclick="showTabForUser('suggestInfoAdd','事件新增', 'showSuggestInfoApply.do?flag=add')">--%>
                                <%--<i class="menu-icon apply"></i>--%>
                                <%--<span>事件申请</span>--%>
                            <%--</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<a href="#" style=" text-decoration: none;" onclick="showTabForUser('suggestInfoAdd','出入库新增', 'showSuggestInfoApply.do?flag=add')">--%>
                                <%--<i class="menu-icon apply"></i>--%>
                                <%--<span>出入库申请</span>--%>
                            <%--</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<a href="#" style=" text-decoration: none;" onclick="showTabForUser('suggestInfoAdd','合理化建议新增', 'showSuggestInfoApply.do?flag=add')">--%>
                                <%--<i class="menu-icon apply"></i>--%>
                                <%--<span>合理化建议申请</span>--%>
                            <%--</a>--%>
                        <%--</li>--%>
                        <%--<li>--%>
                            <%--<a id="lcsq" href="#" style=" text-decoration: none;display: none" onclick="showTabForUser('lcInfoAdd','理财新增', 'showLcInfoApply.do?flag=add')">--%>
                                <%--<i class="menu-icon apply"></i>--%>
                                <%--<span>理财申请</span>--%>
                            <%--</a>--%>
                        <%--</li>--%>
                    <%--</ul>--%>
                <%--</div>--%>
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
                                <a href="javascript:void(0)">采购(<span id="caigou">${cgWorkItemCount}</span>)</a>
                            </li>
                            <li>
                                <a href="javascript:void(0)">事件(<span id="event">${eventWorkItemCount}</span>)</a>
                            </li>
                            <li>
                                 <a href="javascript:void(0)">仓库(<span id="ck">${ckWorkItemCount}</span>)</a>
                            </li>
<!--                             <li> -->
<%--                                 <a href="javascript:void(0)">排班(<span id="paiban">${pbWorkItemCount}</span>)</a> --%>
<!--                             </li> -->
                            <li id="li_dcdb">
                                <a href="javascript:void(0)">督查督办(<span id="dcdb">${dcdbWorkItemCount}</span>)</a>
                            </li>
                            <li id="li_suggest">
                                <a href="javascript:void(0)">合理化建议(<span id="suggest">${suggestWorkItemCount}</span>)</a>
                            </li>
                            <li id="li_lc">
                                <a href="javascript:void(0)">理财(<span id="lc">${lcWorkItemCount}</span>)</a>
                            </li>
                            <li id="li_sc">
                                <a href="javascript:void(0)">印控(<span id="sealControl">${scWorkItemCount}</span>)</a>
                            </li>
                            <li id="li_dsh">
                                <a href="javascript:void(0)">董事会(<span id="dsh">${dshWorkItemCount}</span>)</a>
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
    function createMessage(index) {
        $("#workItems").empty();
        var params = {index: index};
        var url = "<%=request.getContextPath()%>/ajax/getAllWorkItems.do";
        //var msgid = mini.get("header").loading("待办查询中，请稍后...", "待办查询");
        mini.mask({
            el:document.getElementById("mainTabs"),
            cls:'mini-mask-loading',
            html:'待办查询中，请稍后...'
        });
        ajaxJson(url, true, params, function callback(data){
            $("#workItems").empty();
            var data = mini.decode(data);
            var error = data.error;
            if (error == -1) {
            } else {
                var result = data.data;
                if(index == 0) {

                    $("#all").text(data.totalCount);
                    $("#caigou").text(data.cgCount);
                    $("#event").text(data.eventCount);
                    $("#ck").text(data.ckCount);
                    $("#dcdb").text(data.dcdbCount);
                    $("#suggest").text(data.suggestCount);
                    $("#lc").text(data.lcCount);
                    $("#sealControl").text(data.scCount);
                    $("#dsh").text(data.dshCount);

                } else if (index == 1) {

                    $("#caigou").text(result.length);

                } else if(index == 2) {

                    $("#event").text(result.length);

                } else if(index == 3) {

                    $("#ck").text(result.length);

                // } else if(index == 4) {
                //
                //     $("#paiban").text(result.length);

                } else if(index == 4) {

                    $("#dcdb").text(result.length);

                } else if(index == 5) {

                    $("#suggest").text(result.length);

                } else if(index == 6) {

                    $("#lc").text(result.length);
                } else if (index == 7) {

                    $("#sealControl").text(result.length);
                } else if (index == 8) {
                	
                	$("#dsh").text(result.length);
                }

                if(result.length > 0) {
                    $(result).each(function(i) {
                        var createTime = mini.formatDate(this.createTime, "yyyy-MM-dd");
                        var title = this.title;
                        if(title != null && title.length>20){
                            title = title.substring(0,20)+" . . .";
                        }
                        var li0 = "<li><img src='<%=request.getContextPath()%>/resources/img/NewInfo.png'></img><a href='javascript:showOpeartePage(\"" +
                          this.key + "\",\"" + this.id + "\",\"" + this.processId + "\",\"" + this.type + "\",\"" + this.status + "\");'>[" + this.type + "]" + this.id + "</a>";
                        var li1 = "<span id = 'titleName' title=\""+this.title+"\">" + title + "</span>";
                        var li2 = "<span>（时间：" + createTime + "&nbsp&nbsp创建人：" + this.createUser + "）</span></li>";
                        var li = "";
                        if(this.type == "替班" || this.type == "换班") {
                            li = li0 + li2;
                        }else {
                            li = li0 + li1 + li2;
                        }
                        $("#workItems").append(li);
                    });
                    $(".prompt_message").hide();
                    $(".work_message").show();
                }else {
                    $(".prompt_message").show();
                }
            }
            //mini.hideMessageBox(msgid);
            mini.unmask(document.getElementById("mainTabs"));
        });
    }

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

    function cgOperate(key,id, status) {
        var url = '<%=request.getContextPath()%>/showCgInfoApply.do?id='+key + '&cgCode=' + id;
        var title = "";
        if(status == "refuse" || status == "recycleState" || status == "temporaryXj") {
            url = url + '&flag=edit';
            title = "采购信息编辑";
        } else {
            url = url + '&flag=other';
            title = "采购申请审批";
        }
        parent.showTabForUser("cgInfo"+key,title,url);
    }

    function dcdbOperate(key,id, status) {
        var url = '<%=request.getContextPath()%>/showDcdbInfoApply.do?id='+key + '&dcdbCode=' + id;
        var title = "";
        if(status == "refuse") {
            url = url + '&flag=edit';
            title = "督查督办信息编辑";
        } else {
            url = url + '&flag=other';
            title = "督查督办申请审批";
        }
        parent.showTabForUser("dcdbInfo"+key,title,url);
    }

    function suggestOperate(key,id, status) {
        var url = '<%=request.getContextPath()%>/showSuggestInfoApply.do?id='+key + '&suggestCode=' + id;
        var title = "";
        if(status == "refuse") {
            url = url + '&flag=edit';
            title = "合理化建议信息编辑";
        } else {
            url = url + '&flag=other';
            title = "合理化建议申请审批";
        }
        parent.showTabForUser("suggestInfo"+key,title,url);
    }

    function lcOperate(key,id, status) {
        var url = '<%=request.getContextPath()%>/showLcInfoApply.do?id='+key + '&lcCode=' + id;
        var title = "";
        if(status == "refuse"|| status == "recycleState") {
            url = url + '&flag=edit';
            title = "理财产品信息编辑";
        } else {
            url = url + '&flag=sp';
            title = "理财产品申请审批";
        }
        parent.showTabForUser("lcInfo"+key,title,url);
    }

    function rkOpen(id) {
        ajaxJson("<%=request.getContextPath()%>/ajax/rk_getRkWorkItem.do", true, {rkId: id}, function(data) {
            var result = mini.decode(data);
            var url = "<%=request.getContextPath()%>/showRkDetail.do";
            openCkDetail(url, {row : result}, "入库详细");
        });
    }

    function ckOpen(id) {
        ajaxJson("<%=request.getContextPath()%>/ajax/ck_getZcWplyInfo.do", true, {wpLyId: id}, function(data) {
            var result = mini.decode(data);
            if(result.flag) {
                var url = "<%=request.getContextPath()%>/showCkDetail.do";
                openCkDetail(url, {row : result}, "物品领用详细");
            }else {
                var url = "<%=request.getContextPath()%>/showCkConfirmDetail.do";
                openCkDetail(url, {wpLyId: result.wpLyId, workItemID: result.workItemID }, "出库");
            }
        });
    }

    //仓库出入库审批窗口
    function openCkDetail(url, data, title) {
        mini.open({
            url: url,
            title: title, width: 1080, height: 600,
            onload: function () {
                var iframe = this.getIFrameEl();
                iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                if (action == "ok") {
                    var mainTabs = parent.getMainTabs();
                    mainTabs.reloadTab(mainTabs.getActiveTab());
                }
            }
        });
    }

    //事件服务待办处理
    function eventOpearte(id) {
        ajaxJson("<%=request.getContextPath()%>/ajax/event_getWorkItem.do", true, {id:id}, function(data){
            var record = mini.decode(data);
            var url = "";
            if(record.eventName=="问题"){
                if(record.status==1){
                    url = '<%=request.getContextPath()%>/showEprobManaging.do?eProbId=' + id + '&editFlag=' + 6;
                    parent.showTabForUser(id,"指定问题审批人",url);
                }else if( record.status==2){
                    var url = '<%=request.getContextPath()%>/showEprobManaging.do?eProbId='+id+'&editFlag='+3; 
                    parent.showTabForUser(id,"问题处理",url);
                }else if (record.status==3){
                    //解决方案
                    var url = '<%=request.getContextPath()%>/showEprobManaging.do?eProbId='+id+'&editFlag='+2; 
                    parent.showTabForUser(id,"问题处理",url);
                }else if(record.status==4){
                    //测试
                    var url = '<%=request.getContextPath()%>/showEprobManaging.do?eProbId='+id+'&editFlag='+4; 
                    parent.showTabForUser(id,"问题处理",url);
                }else if(record.status==5){
                    //科技部审批
                    var url = '<%=request.getContextPath()%>/showEprobManaging.do?eProbId='+id+'&editFlag='+3; 
                    parent.showTabForUser(id,"问题处理",url);
                }else if(record.status==6){
                    //变更登记
                    var url = '<%=request.getContextPath()%>/showEprobManaging.do?eProbId='+id+'&editFlag='+5; 
                    parent.showTabForUser(id,"问题处理",url);
                }else if(record.status==9){
                    //方案设计
                    var url = '<%=request.getContextPath()%>/showEprobManaging.do?eProbId='+id+'&editFlag='+2; 
                    parent.showTabForUser(id,"问题处理",url);
                }else if(record.status == 10){
                    //实施
                    var url = '<%=request.getContextPath()%>/showEprobManaging.do?eProbId='+id+'&editFlag='+2; 
                    parent.showTabForUser(id,"问题处理",url);
                }else if(record.status == 11){
                    //指定测试人员
                     var url = '<%=request.getContextPath()%>/showEprobManaging.do?eProbId='+id+'&editFlag='+7; 
                     parent.showTabForUser(id,"问题处理",url);
                }
            }else if(record.eventName=="变更"){
                var url = '<%=request.getContextPath()%>/showEChangeManaging.do?eChangeId='+id + '&editFlag=' + 1; 
                if(record.status == 1) {
                    parent.showTabForUser(id,"指定变更审批人",url);
                }else if(record.status == 2) {
                    parent.showTabForUser(id,"变更审核",url);
                }else if(record.status == 3) {
                    parent.showTabForUser(id,"变更实施",url);
                }else if (record.status == 8){
                    //科技审批代办
                    parent.showTabForUser(id,"科技审批",url);
                }else if (record.status == 9){
                    //补审批
                    parent.showTabForUser(id,"变更审批",url);
                }else if(record.status==7){
                    //资源准备
                    parent.showTabForUser(id,"上线资源准备",url);
                }else if (record.status == 0){
                    //回退修改
                    parent.showTabForUser(id,"变更修改",url);
                }
            }else if(record.eventName =="事件") {
                //根据该事件的当前状态来决定进行哪个操作0新建，1已分派
                if(record.status == 0) {
                    url = '<%=request.getContextPath()%>/showEventManaging.do?eventId=' + id + '&editFlag=' + 4;
                    parent.showTabForUser(id,"重新登记服务",url);
                }
                if(record.status ==1){
                    url = '<%=request.getContextPath()%>/showEventManaging.do?eventId=' +id + '&editFlag=' + 1;
                    parent.showTabForUser(id,"服务回退/分派",url);
                }else if(record.status == 2){
                    //事件处理（填写解决方案）
                    url = '<%=request.getContextPath()%>/showEventManaging.do?eventId=' +id + '&editFlag=' + 2;
                    parent.showTabForUser(id,"服务处理",url);
                }else if(record.status==3){
                    url = '<%=request.getContextPath()%>/showEventManaging.do?eventId=' +id + '&editFlag=' + 3;
                    parent.showTabForUser(id,"回退/关闭IT服务",url);
                }
            }else if(record.eventName =="批量上线") {
                if(record.status == 1) {
                    url = '<%=request.getContextPath()%>/showBatchEChangeInfo.do?batchId=' +id + '&editFlag=' + 1;
                    parent.showTabForUser(id,"批量变更审批",url);
                }else if(record.status == 2) {
                    url = '<%=request.getContextPath()%>/showBatchEChangeInfo.do?batchId=' +id + '&editFlag=' + 2;
                    parent.showTabForUser(id,"批量上线执行",url);
                }
            }
        });
    }
    
    function scOperate(id, status) {

        var url = '<%=request.getContextPath()%>/showSCProcessOperation.do?code=' + id;
        var title = "";

        if(status == "refuse") {

            url = url + '&operationFlag=edit';
            title = "编辑用印记录";

        } else {

            url = url + '&operationFlag=approve';
            title = "审批用印记录";
        }

        parent.showTabForUser(id, title, url);
    }

    function dshOperate(id, status){
        if(status == "dbLeaderSP" || status == "newCreate") {
            var heightForRole = 600;
            if(status == "newCreate"){
                heightForRole = 630;
            }
        	mini.open({
        		url: "<%=request.getContextPath()%>/dbh_showCollection.do?",
                title: "编辑会议议题征集信息", width: 900, height:heightForRole,
                onload: function () {
                    var iframe = this.getIFrameEl();
                    var data = { flag:"edit",id:id};
                    iframe.contentWindow.SetData(data);
                },
                ondestroy: function (action) {
                    if (action == "ok") {
                        grid.reload();
                    }
                }
            });
        } else if(status == "cbbmDoing"){
        	var url = '<%=request.getContextPath()%>/dbh_showMotionApply.do?id=' + id;
            parent.showTabForUser("motion" + id,"议题管理",url);
        }
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
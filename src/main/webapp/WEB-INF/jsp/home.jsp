<%@page language="java"  pageEncoding="utf8"%>
<%--
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

--%>

<!doctype html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>教师诚信-首页</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta http-equiv = "X-UA-Compatible" content = "IE=10" />
        <!-- 如果小于IE7 -->
        <!--[if lte IE 7 ]>  
        <script>
            location = "googleFrameDownload.jsp";
        </script>
        <![endif]-->
        <script src="<%=request.getContextPath()%>/resources/scripts/boot.js" type="text/javascript"></script>
        <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/resources/calendar/cupertino/theme.css' />
        <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/resources/calendar/fullcalendar/fullcalendar.css' />
        <link rel='stylesheet' type='text/css' href='<%=request.getContextPath()%>/resources/calendar/fullcalendar/fullcalendar.print.css' media='print' />
        <script type='text/javascript' src='<%=request.getContextPath()%>/resources/calendar/jquery/jquery-ui-1.8.23.custom.min.js'></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/resources/calendar/fullcalendar/fullcalendar.min.js'></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/resources/js/common/DateUtil.js'></script>
        <script src="<%=request.getContextPath()%>/resources/echarts/echarts.js"></script>
        <link href="<%=request.getContextPath()%>/resources/css/miniui_style.css" type="text/css" rel="stylesheet" />
    
        <link href="<%=request.getContextPath()%>/resources/css/common/base.css" rel="stylesheet"></link>
        <link href="<%=request.getContextPath()%>/resources/css/pages/duty_mgmt.css" rel="stylesheet"></link>
        <link href="<%=request.getContextPath()%>/resources/css/pages/index.css" rel="stylesheet"></link>
        <link href="<%=request.getContextPath()%>/resources/css/home/home.css" rel="stylesheet"></link>

        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/common/jquery.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/home/home.js"></script>


        <link href="<%=request.getContextPath()%>/resources/CustomHomeTheme/third-party/scrollbar/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
        <script src="<%=request.getContextPath()%>/resources/CustomHomeTheme/third-party/scrollbar/jquery.mCustomScrollbar.concat.min.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/CustomHomeTheme/frame1/res/menu/menu.css" rel="stylesheet" type="text/css" />
        <script src="<%=request.getContextPath()%>/resources/CustomHomeTheme/frame1/res/menu/menu.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath()%>/resources/CustomHomeTheme/frame1/res/menutip.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/resources/CustomHomeTheme/frame1/res/tabs.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/resources/CustomHomeTheme/frame1/res/frame.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/resources/CustomHomeTheme/frame1/res/index.css" rel="stylesheet" type="text/css" />
        <link href="<%=request.getContextPath()%>/resources/CustomHomeTheme/fonts/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />


        <style type="text/css">
            .header
            {
                background:url(<%=request.getContextPath()%>/resources/scripts/miniui/themes/icons/header.gif) repeat-x 0 -1px;
            }
            .calendarDiv {
                           margin-top: 40px;
                           text-align: center;
                           font-size: 13px;
                           font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
                       }

            #calendar {
                width: 900px;
                margin: 0 auto;
                background:url(<%=request.getContextPath()%>/resources/calendar/fullcalendar/bg.png);
                background-repeat:repeat;
            }
            .tabList {
            width: 70%;
            border-bottom: 1px solid #eee;
            margin-left: 5px;
            float: left
            }
            ul {
                padding: 0;
                margin: 0;
            }
            .tab-container {
                border: thin solid #eee;
            }
            #header {
                height: 35px;
                line-height: 33px;
                 background-color: #eee;
            }
            #header .tabList li {
                float: left;
                margin: 0 5px;
                padding: 0 5px;
            }
            #header .tabList li a {
                text-decoration: none;
                color: #747482
            }
            #header .tabList li.selected {
                border-bottom: 2px solid #3ABAEB;
            }
            #header .tabList li.selected a {
                color: #3ABAEB;
            }
            .content {
                margin: 5px;
                padding: 5px;
                height: 100%;
                overflow: auto;
                overflow-x: hidden;
            }
            .content a{
                text-decoration: none;
            }
            .prompt_message {
                margin: 20px 0;
                text-align: center;
                height: 30px;
                line-height: 30px;
            }
            .prompt_message span {
                display: inline-block;
                background: url(<%=request.getContextPath()%>/resources/img/prompt_message.png) left center no-repeat;
                padding-left: 30px;
                margin: 0 auto;
            }
            .work_message {
                margin: 0 10px;
                padding: 5px;
            }
            .work_message #workItems li{
                padding: 5px;
                height:30px;
                line-height:20px;
                border-bottom: 1px dotted #A5ACB5;
            }
            .work_message #workItems img{
                margin-right: 10px;
                vertical-align: middle;
            }
            .work_message #workItems span {
                margin-left: 20px;
                color: gray;
            }
        </style>
    </head>
    <body id="homeBody" style="display: none">


       <jsp:include page="head.jsp"></jsp:include>
        <div class="container">
        <jsp:include page="menuBar.jsp"></jsp:include>
        <jsp:include page="mainTabs.jsp"></jsp:include>
        <div class="belong-to">Copyright©苏州市职业大学版权所有</div>
        <%-- </div>--%>>
        </div>
        <script type="text/javascript">
            mini.parse();
            $("#homeBody").fadeTo("slow", 1);

            function getMainTabs() {
                return mini.get("mainTabs");
            }
    
            function showTab(node) {
                var tabs = mini.get("mainTabs");
                var id = "tab$" + node.id;
                var tab = tabs.getTab(id);
                if (!tab) {
                    tab = {};
                    tab.name = id;
                    tab.title = node.name;
                    tab.showCloseButton = true;
                    tab.url = node.url + "?id=" + node.id;
    
                    tabs.addTab(tab);
                }
                tabs.activeTab(tab);
                tabs.loadTab(node.url + "?id=" + node.id, tab);
            }
    
            function onNodeSelect(e) {
                var node = e.node;
                var isLeaf = e.isLeaf;
    
                if (isLeaf) {
                    showTab(node);
                }
            }
    
            function showTabForUser(paramId, title, url) {
                var tabs = mini.get("mainTabs");
    
                var id = "tab$" + paramId;
                var tab = tabs.getTab(id);
                if (!tab) {
                    tab = {};
                    tab.name = id;
                    tab.title = title;
                    tab.showCloseButton = true;
                    tab.url = url;
    
                    tabs.addTab(tab);
                } else {
                    tab.title = title;
                    tab.url = url;
                }
                tabs.activeTab(tab);
                tabs.loadTab(tab.url, tab);
            }
    
            function showTabRkInfo(node) {
                var tabs = mini.get("mainTabs");
    
                var id = "tab$" + node.id + node.rkId;
                var tab = tabs.getTab(id);
                if (!tab) {
                    tab = {};
                    tab.name = id;
                    tab.title = node.text;
                    tab.showCloseButton = true;
                    tab.url = node.url + "?rkId=" + node.rkId + "&action=" + node.action + "&menuId="+node.menuId;
                    tabs.addTab(tab);
                }
                tabs.activeTab(tab);
            }
    
            function removeTab(msg) {
                if (msg == "") {
                    var tabs = mini.get("mainTabs");
                    var tab = tabs.getActiveTab();
                    if (tab && tab.title != "首页") {
                        tabs.removeTab(tab);
                    }

                } else {
                    mini.alert(msg, "提醒", function(action) {
                        if (msg == "您尚未登录或会话已过期！") {
                            window.location.href = "<%=request.getContextPath()%>/reLogin.do";
                        } else {
                            var tabs = mini.get("mainTabs");
                            var tab = tabs.getActiveTab();
                            if (tab) {
                                tabs.removeTab(tab);
                            }
                        }
                    });
                }
            }
    
            function showTabCkInfo(node) {
                var tabs = mini.get("mainTabs");
    
                var id = "tab$" + node.id + node.ckId;
                var tab = tabs.getTab(id);
                if (!tab) {
                    tab = {};
                    tab.name = id;
                    tab.title = node.text;
                    tab.showCloseButton = true;
                    tab.url = node.url + "?ckId=" + node.ckId + "&action=" + node.action + "&menuId="+node.menuId;
                    tabs.addTab(tab);
                }
                tabs.activeTab(tab);
            }

            function showBrandTab(node) {
                var tabs = mini.get("mainTabs");
    
                var id = "tab$" + node.id + node.typeId;
                var tab = tabs.getTab(id);
                if (!tab) {
                    tab = {};
                    tab.name = id;
                    tab.title = node.text;
                    tab.showCloseButton = true;
                    tab.url = encodeURI(encodeURI(node.url + "?id=" + node.typeId + "&name=" + node.typeName));
    
                    tabs.addTab(tab);
                   
                }
                tabs.activeTab(tab);
            }
    
            function onGoToUrlChanged(e) {
                var item = e.selected;
                if (item != null && item.id != null) {
                    showTab(item);
                }
            }
    
            function removeUserTab(id){
                var tabs = mini.get("mainTabs");
                var id = "tab$" +id;
                var tab =tabs.getTab(id);
                //mini.alert(id+tab);
                if(tab){
                   tabs.removeTab(id);
                }
            }
    
            function quickGo(idParam, name, url) {
                var tabs = mini.get("mainTabs");
                var id = "tab$" + idParam;
                var tab = tabs.getTab(id);
                if (!tab) {
                    tab = {};
                    tab.name = id;
                    tab.title = name;
                    tab.showCloseButton = true;
                    tab.url = url + "?id=" + idParam;
    
                    tabs.addTab(tab);
                }
                tabs.activeTab(tab);
                tabs.loadTab(url + "?id=" + idParam, tab);
            }
            
            // 刷新tab后保留查询条件
            function onTabRefresh(flag) {
                var mainTabs = mini.get("mainTabs");
                $.each(mainTabs.tabs, function(i, tab) {
                    if(tab.name == 'tab$menu005300' || tab.name == 'tab$menu005200') {
                        if (flag == 1) {
                            mainTabs.activeTab(tab);
                        }
                        var iframe = mainTabs.getTabIFrameEl(tab);
                        iframe.contentWindow.reload();
                    }
                    else if (tab.name == "tab$menu013001" || tab.name == "tab$menu013002") {

                        if (flag == 1) {

                            mainTabs.activeTab(tab);
                        }

                        var iframe = mainTabs.getTabIFrameEl(tab);
                        iframe.contentWindow.reload();
                    }
                    else if (tab.name == "tab$menu014001" || tab.name == "tab$menu014002") {

                        if (flag == 1) {

                            mainTabs.activeTab(tab);
                        }

                        var iframe = mainTabs.getTabIFrameEl(tab);
                        iframe.contentWindow.reload();
                    }
                });
            }
        </script>
    </body>
</html>

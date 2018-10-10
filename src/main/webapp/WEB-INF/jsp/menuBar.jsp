<%@page language="java" contentType="text/html; charset=gbk" pageEncoding="utf8"%>




<%--<div class="left-menu">
             <ul id="tree1" class="mini-tree" url="<%=request.getContextPath()%>/ajax/user_getMenus.do" style="padding:5px;"  iconField="icon"
                 textField="name" idField="id" onnodeclick="onNodeSelect" value="base" parentField="pId" expandOnNodeClick="true" resultAsTree="false"
                 showExpandButtons="false" enableHotTrack="true"    showTreeLines="false" showTreeIcon="true"
             >
            </ul>
        </div>--%>

<div class="sidebar">
    <div class="sidebar-toggle"><i class = "fa fa-fw fa-dedent" ></i></div>

    <div id="mainMenu" class=""></div>

</div>
<script type="text/javascript">
        mini.parse();
        
        function onNodeSelect(e) {
            var node = e.node;
            var isLeaf = e.isLeaf;

            if (isLeaf) {
                showTab(node);
            }
        }

        $(function () {

            //menu
            var menu = new Menu("#mainMenu", {
                itemclick: function (item) {
                    if (!item.children) {
                        activeTab(item);
                    }
                }
            });

          //  $(".sidebar").mCustomScrollbar({ autoHideScrollbar: true });

            new MenuTip(menu);

            $.ajax({
                url: "<%=request.getContextPath()%>/ajax/user_getMenusTree.do",
                data:{'test':'123'},
                type: "post",
                async: false,
                cache:false,
                dataType: 'text',
                success: function (text) {
                    var data = mini.decode(text);
                    menu.loadData(data);
                }
            });

            $(".menu-title").click(function (event) {
                $(".menu-title.selected").removeClass("selected");
                $(this).addClass("selected");
            });

            //toggle
            $("#toggle, .sidebar-toggle").click(function () {
                $('body').toggleClass('compact');
                mini.layout();
            });

            //dropdown
            $(".dropdown-toggle").click(function (event) {
                $(this).parent().addClass("open");
                return false;
            });

            $(document).click(function (event) {
                $(".dropdown").removeClass("open");
            });



        });

        function activeTab(item) {
            var tabs = mini.get("mainTabs");
            var tab = tabs.getTab("tab$"+item.id);
            if (!tab) {
                tab = { name: "tab$"+item.id, title: item.text, url: item.url+ "?id=" + item.id, iconCls: item.iconCls, showCloseButton: true };
                tab = tabs.addTab(tab);
            }
            tabs.activeTab(tab);
            tabs.loadTab(item.url + "?id=" + item.id, tab);
        }
</script>
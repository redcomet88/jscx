<%@page language="java" contentType="text/html; charset=gbk" pageEncoding="utf8"%>
<%--
<div class="pf-hd">
    <i class="logo"></i>
    <i class="name"></i>
    <a href="http://192.168.169.45:8080/ssoms/" ><i class="logout"></i></a>
    <i class="split"></i>
    <!-- <a onclick="showTabForUser('updPwd','密码修改','<%=request.getContextPath()%>/showPwdUpd.do')" >
    <i class="lock"></i>
    </a>
    <a onclick="showTabForUser('userUpd','员工信息修改','<%=request.getContextPath()%>/showUserUpd.do')" >
    <i class="avatar"></i>
    </a> -->
    <span class="username"> ${ygxm}</span>
    <span style="float: right;margin-top: 28px;padding: 0 0 0 15px;font-size: 14px;line-height: 14px;">${jgmc}</span>
    <span class="user-label">当前用户：</span>
    <i class="skin"></i>
    <!-- <i class="split"></i>
    <div id="goToUrl" name="goToUrl" class="mini-autocomplete fast-input" style="width:150px;" onvaluechanged="onGoToUrlChanged"
           valueField="id" textField="name" url="<%=request.getContextPath()%>/ajax/user_getMenusQuick.do">
     </div>
    <span class="tip">快捷跳转：</span> -->
</div>
--%>



<div class="navbar">
    <div class="navbar-header">
        <div class="navbar-brand"><img style="top: -10px; left: -11px; width: 99%;" src="<%=request.getContextPath()%>/resources/CustomHomeTheme/frame1/res/images/wjrcb-logo.png" /> </div>
        <div class="navbar-brand navbar-brand-compact"> <img style="position:absolute;clip:rect(0px 43px 50px 0px);  width:280px;height:40px;" src="<%=request.getContextPath()%>/resources/CustomHomeTheme/frame1/res/images/wjrcb-logo.png" /> </div>
    </div>
    <ul class="nav navbar-nav">
     <li><a id="toggle"><span class="fa fa-bars" ></span></a></li>
         <li class=""><span style="line-height:50px;font-size: 23px;  font-weight: bold;" >锦管家</span></li>
        <%--    <li class="icontop"><a href="#"><i class="fa fa-puzzle-piece"></i><span >开发文档</span></a></li>
     <li class="icontop"><a href="#"><i class="fa fa-sort-amount-asc"></i><span >人力资源</span></a></li>
     <li class="icontop"><a href="#"><i class="fa  fa-cog"></i><span >系统设置</span></a></li>--%>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <%--<li ><a href="#"><i class="fa fa-paper-plane"></i> 代办事项</a></li>
        <li><a href="#"><i class="fa fa-pencil-square-o"></i> 修改密码</a></li>
        <li class="dropdown">
            &lt;%&ndash;<a class="dropdown-toggle userinfo">&ndash;%&gt;
                &lt;%&ndash;<img class="user-img" src="res/images/user.jpg" />个人资料<i class="fa fa-angle-down"></i>&ndash;%&gt;
            &lt;%&ndash;</a>&ndash;%&gt;
            <ul class="dropdown-menu pull-right">
                <li ><a href="#"><i class="fa fa-eye "></i> 用户信息</a></li>
                <li><a href="http://192.168.169.45:8080/ssoms/"><i class="fa fa-user"></i> 退出登录</a></li>
            </ul>
        </li>--%>

        <li ><a href="#"> <span class="fa fa-user"> ${ygxm}</span> </a> </li>
        <li ><a href="#"> <span class="fa fa-sitemap"> ${jgmc}</span> </a> </li>
        <li ><a href="http://192.168.169.45:8080/ssoms/" ><i class="fa fa-power-off">退出登录</i></a> </li>

    </ul>
</div>
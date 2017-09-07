<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="welcome.title"/> — 主页</title>
    <meta name="description" content="" />
    <link rel="stylesheet" type="text/css" href="/css/core.css"/>
    <link rel="stylesheet" type="text/css" href="/css/frame.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.10.3.custom.min.css"/>
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="/js/frame.js"></script>
</head>
<body class="frame index-frame iframe">
<div id="frame-wrapper">
    <jsp:include page="inc/topBar.jsp" />
    <jsp:include page="inc/hdader.jsp"/>
    <div id="content">
        <div class="mainbar">
            <div class="module">
                <iframe src="/home/account" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" id="mainframe" name="mainframe"></iframe>
            </div>
        </div>
        <div class="sidebar"><a href="javascript:void(0)" class="sideBar-trigger">&nbsp;</a>
            <div class="module">
                <ul id="side-menu">
                    <li><h3>HOME：</h3></li>
                    <crius:userRights url="/home/account"><li><a href="/home/account" target="mainframe" class="selected">帐号信息</a></li></crius:userRights>
                    <crius:userRights url="/home/permission/user"><li><a href="/home/permission/user" target="mainframe">权限分配</a></li></crius:userRights>
                    <crius:userRights url="/home/operatorLog"><li><a href="/home/operatorLog" target="mainframe">操作记录</a></li></crius:userRights>
                </ul>
            </div>
        </div>
</div>
<div id="footer"></div>
</div>
<script src="/js/crius.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
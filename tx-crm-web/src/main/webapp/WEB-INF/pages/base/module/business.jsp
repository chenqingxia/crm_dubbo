<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>多米科技后台系统 — 商户</title>
    <meta name="description" content="wifi云平台-crm系统" />
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
    <jsp:include page="inc/hdader.jsp"><jsp:param name="nav" value="business"/></jsp:include>
    <div id="content">
        <div class="mainbar">
            <div class="module">
                <iframe src="/business/shopuser" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" id="mainframe" name="mainframe"></iframe>
            </div>
        </div>
        <div class="sidebar"><a href="javascript:void(0)" class="sideBar-trigger">&nbsp;</a>
            <div class="module">
                <ul id="side-menu">
                    <li><h3>BUSINESS：</h3></li>
                    <li><a href="/business/shopuser" target="mainframe" class="selected">商家用户管理</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div id="footer"></div>
</div>
<script src="/js/crius.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
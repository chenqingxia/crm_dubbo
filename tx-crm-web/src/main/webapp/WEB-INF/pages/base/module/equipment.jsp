<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>多米科技后台系统 —设备</title>
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
    <jsp:include page="inc/hdader.jsp"><jsp:param name="nav" value="equipment"/></jsp:include>
    <div  id="content">
        <div class="mainbar">
            <div class="module">
                <iframe src="/equipment/device" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" id="mainframe" name="mainframe"></iframe>
            </div>
        </div>
        <div class="sidebar"><a href="javascript:void(0)" class="sideBar-trigger">&nbsp;</a>
            <div class="module">
                <ul id="side-menu">
                    <li><h3>EQUIPMENT：</h3></li>
                    <li><a href="/equipment/device" target="mainframe" class="selected">路由器管理</a></li>
                    <!--li><a href="/card/CardFee" target="mainframe" >收卡内部费率设定</a></li-->
                </ul>
            </div>
        </div>
    </div>
    <div id="footer"></div>
</div>
<script src="/js/crius.js" type="text/javascript" charset="utf-8"></script>
</body>
</html>
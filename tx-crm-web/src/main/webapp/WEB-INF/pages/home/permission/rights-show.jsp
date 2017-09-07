<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title> 权限 - ${rights.id} </title>
    <link rel="stylesheet" type="text/css" href="/css/core.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.10.3.custom.min.css"/>
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="js/crius.js" charset="utf-8"></script>
</head>
<body>
<div id="wrapper">
    <table class="form label-colored-form">
        <tbody><tr class="sprite">
            <td class="label-td"><label>权限编号：</label></td>
            <td>${rights.id}</td>
        </tr>
        <tr class="sprite">
            <td class="label-td"><label>权限名称：</label></td>
            <td>${rights.name}</td>
        </tr>
        <tr class="sprite">
            <td class="label-td"><label>上级权限编号：</label></td>
            <td>
                <c:if test="${rights.parentId eq '-1'}">无上级权限</c:if>
                <c:if test="${rights.parentId ne '-1'}">${rights.parentId}</c:if>
            </td>
        </tr>
        <tr class="sprite">
            <td class="label-td"><label>对应的URL：</label></td>
            <td>${rights.url}</td>
        </tr>
        <tr class="sprite">
            <td class="label-td"><label>权限描述：</label></td>
            <td>${rights.description}</td>
        </tr>
        </tbody>
    </table>
    <div class="centered fill pusht">
        <crius:userRights url="/home/permission/rights/edit"><a class="btn btn-primary" href="/home/permission/rights/${rights.id}/edit">编辑</a></crius:userRights>
        &nbsp;&nbsp;
        <button class="btn" onclick="dialog_close();">关闭</button>
    </div>
</div>
<c:if test="${resultState eq 'success'}">
    <script type="text/javascript">
        dialog_simple_ok('恭喜，修改权限成功！', false);
        parent.$("#mainframe")[0].contentWindow.flushPage("#rights_dataTable");
    </script>
</c:if>
<c:if test="${resultState eq 'error'}">
    <script type="text/javascript">
        dialog_simple_fail('抱歉，修改权限失败', false);
    </script>
</c:if>
</body>
</html>
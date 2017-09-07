<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>账号信息</title>
    <link rel="stylesheet" type="text/css" href="/css/core.css" />
</head>
<body>
<div id="wrapper">
    <table class="form label-colored-form">
        <tbody>
        <tr class="sprite">
            <td class="colored" colspan="2">个人信息：</td>
        </tr>
        <tr class="sprite">
            <td class="label-td"><label>帐号：</label></td>
            <td>${user.no}</td>
        </tr>
        <tr class="sprite">
            <td class="label-td"><label>姓名：</label></td>
            <td>${user.name}</td>
        </tr>
        <tr class="sprite">
            <td class="label-td"><label>手机：</label></td>
            <td colspan="2">${user.mobile}</td>
        </tr>
        <tr class="sprite">
            <td class="label-td"><label>邮箱：</label></td>
            <td>${user.email}</td>
        </tr>
        <tr class="sprite">
            <td class="label-td"><label>描述：</label></td>
            <td>${user.description}</td>
        </tr>
        <tr class="sprite" height="35px">
            <td class="colored" colspan="2" >帐号信息：</td>
        </tr>
        <tr class="sprite">
            <td class="label-td"><label>最后登录ip：</label></td>
            <td>${user.lastIp}</td>
        </tr>

        </tbody>
    </table>
</div>
</body>
</html>
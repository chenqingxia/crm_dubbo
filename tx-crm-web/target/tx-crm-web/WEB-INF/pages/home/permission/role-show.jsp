<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title> 角色 - ${role.id} </title>
    <link rel="stylesheet" type="text/css" href="/css/core.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.10.3.custom.min.css"/>
    <style type="text/css">
        .highColor {
            color: #08C;
            font-weight: bold;
        }
    </style>
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="/js/crius.js" charset="utf-8"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            // 获取所有的权限
            $.getJSON("/home/permission/role/rights/list",{id:$("#roleId").val()}, function(json) {
                var html = "<table><tr>";
                $.each(json.data,function(i, item) {
                    al
                    if (item.level === 1) {
                        html += "</tr><tr><td><input type='checkbox' id='" + item.url + "' name='rights' value='" + item.id + "'" + item.checked + " disabled />&nbsp;<label for='" + item.url + "' class='inline-label'>" + item.name + "</label></td></tr>";
                    } else if (item.level === 2) {
                        html += "<tr><td style='padding-left:20px;'><input type='checkbox' id='" + item.url + "' name='rights' value='" + item.id + "'" + item.checked + " disabled />&nbsp;<label for='" + item.url + "' class='inline-label'>" + item.name + "</label></td></tr><tr>";
                    } else if (item.level === 3) {
                        html += "<td style='padding-left:40px;'><input type='checkbox' id='" + item.url + "' name='rights' value='" + item.id + "'" + item.checked + " disabled />&nbsp;<label for='" + item.url + "' class='inline-label'>" + item.name + "</label></td>";
                    }
                });
                html += "</table>"

                $("#allRights").html(html);

                parent.$('#dialog iframe').height($(document).height()).parent().scrollTop(0);

                $("input:checkbox[checked]").each(function() {
                    $(this).next().addClass("highColor");
                });
            });
        });
    </script>
</head>
<body>
<div id="wrapper">
    <table class="form label-colored-form">
        <input type="hidden" value="${role.id}" id="roleId"/>
        <tbody><tr class="sprite">
            <td class="label-td"><label>角色名称：</label></td>
            <td>${role.name}</td>
            <td class="label-td"><label>角色描述：</label></td>
            <td>${role.description}</td>
        </tr>
        <tr class="sprite">
            <td class="label-td"><label>对应权限：</label></td>
            <td colspan="3">
                <div id="allRights"></div>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="centered fill pusht">
        <crius:userRights url="/home/permission/role/edit"><a class="btn btn-primary" href="/home/permission/role/${role.id}/edit">编辑</a></crius:userRights>
        &nbsp;&nbsp;
        <button class="btn" onclick="dialog_close();">关闭</button>
    </div>
</div>
<c:if test="${resultState eq 'success'}">
    <script type="text/javascript">
        dialog_simple_ok('恭喜，修改角色成功，而且其对应权限修改完成', false);
        parent.$("#mainframe")[0].contentWindow.flushPage("#role_dataTable");
    </script>
</c:if>
<c:if test="${resultState eq 'fail'}">
    <script type="text/javascript">
        dialog_simple_fail('角色修改成功，但是角色对应权限修改失败，请重新修改角色权限！', false);
        parent.$("#mainframe")[0].contentWindow.flushPage("#role_dataTable");
    </script>
</c:if>
<c:if test="${resultState eq 'error'}">
    <script type="text/javascript">
        dialog_simple_fail('抱歉，修改角色失败', false);
    </script>
</c:if>
</body>
</html>
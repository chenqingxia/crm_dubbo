<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title> 角色 - 修改页面 </title>
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
                    if(item.parentId===-1) {
                        html += "</tr><tr style='font-size:15px;'><td><input type='checkbox' id='" + item.url + "' name='rights' value='" + item.id + "'" + item.checked + "   />&nbsp;<label for='" + item.url + "' class='inline-label'>" + item.name + "</label></td></tr>";
                        $.each(json.data,function(i, item2) {
                            if(item2.parentId==item.id&&item.parentId===-1) {
                                html += "</tr><tr style='font-size:15px;'><td style='padding-left:25px;'><input type='checkbox' id='" + item2.url + "' name='rights' value='" + item2.id + "'" + item2.checked + "   />&nbsp;<label for='" + item2.url + "' class='inline-label'>" + item2.name + "</label></td></tr>";
                                $.each(json.data,function(i, item3) {
                                    if(item3.parentId==item2.id&&item2.parentId==item.id&&item.parentId===-1) {
                                        html += "</tr><tr style='font-size:15px;'><td style='padding-left:50px;'><input type='checkbox' id='" + item3.url + "' name='rights' value='" + item3.id + "'" + item3.checked + "   />&nbsp;<label for='" + item3.url + "' class='inline-label'>" + item3.name + "</label></td></tr>";

                                    }
                                });
                            }
                        });
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
<div id="wrapper" >
    <br>
    <form action="/home/permission/role/${role.id}" method="post" id="roleForm">
        <input type="hidden" value="${role.id}" id="roleId"/>
        <table class="form label-colored-form">
            <tbody><tr class="sprite">
                <td class="label-td"><label>角色名称：</label></td>
                <td><input type="text" name="name" disabled  value="${role.name}"/></td>
                <td class="label-td"><label>角色描述：</label></td>
                <td><input type="text" name="description"  required  value="${role.description}" /></td>
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
            <button class="btn btn-primary">提交</button>
            &nbsp;&nbsp;
            <a class="btn" onclick="window.history.go(-1)">返回</a>
        </div>
    </form>
</div>
</body>
</html>
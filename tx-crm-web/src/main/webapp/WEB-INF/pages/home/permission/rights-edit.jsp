<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title> 权限 - 编辑 </title>
    <link rel="stylesheet" type="text/css" href="/css/core.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.10.3.custom.min.css"/>
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="/js/crius.js" charset="utf-8"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            // 当加载页面的时候，同时ajax请求
            $.getJSON("/home/permission/rights/list",function(json) {
                var sel =  $("#sel_parentId").val();
                $("#sel_parentId").html(" <option value='-1'>无上级权限</option>");
                $.each(json.data,function(i, item) {
                    if (sel == item.id){
                        var option = "<option value='"+item.id+"' selected='selected'>"+item.name+"（"+item.id+"）</option>";
                        $("#sel_parentId").append(option);
                    } else {
                        var option = "<option value='"+item.id+"'>"+item.name+"（"+item.id+"）</option>";
                        $("#sel_parentId").append(option);
                     }
                });
            });
        });
    </script>
</head>
<body>
<div id="wrapper">
    <form action="/home/permission/rights/${rights.id}" method="post">
        <table class="form label-colored-form">
            <tbody><tr class="sprite">
                <td class="label-td"><label>权限编号：</label></td>
                <td><input type="text" name="id" value="${rights.id}" disabled /></td>
            </tr>
            <tr class="sprite">
                <td class="label-td"><label>权限名称：</label></td>
                <td><input type="text" name="name" value="${rights.name}" disabled /></td>
            </tr>
            <tr class="sprite">
                <td class="label-td"><label>对应的URL：</label></td>
                <td><input type="text" name="url" value="${rights.url}" required /></td>
            </tr>
            <tr class="sprite">
                <td class="label-td"><label>权限描述：</label></td>
                <td><input type="text" name="description" value="${rights.description}" required /></td>
            </tr>
            <tr class="sprite">
                <td class="label-td"><label>上级权限：</label></td>
                <td>
                    <select name="parentId" id="sel_parentId" required class="free">
                        <option value="${rights.parentId}"></option>
                    </select>
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title> 权限-新增页面 </title>
    <link rel="stylesheet" type="text/css" href="/css/core.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.10.3.custom.min.css"/>
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="/js/crius.js" charset="utf-8"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            // 当加载页面的时候，同时ajax请求
            $.getJSON("/home/permission/rights/list",function(json) {
                $.each(json.data,function(i, item) {
                    var option = "<option value='"+item.id+"'>"+item.name+"（"+item.id+"）</option>";
                    $("#sel_parentId").append(option);
                });
            });

            // 权限名称唯一性判断
            var name_msg = false;
            $("input[name=name]").focusout(function() {
                $.get("/home/permission/rights/search",{name:$(this).val()},function(data) {
                    if (data === "error") {
                        dialog_simple_fail("权限名称已存在", false);
                        name_msg = false;
                    } else {
                        name_msg = true;
                    }
                });
            });

            $("#rightsForm").submit(function(e) {
                if (name_msg) {
                    name_msg = false;
                    return true;
                } else {
                    e.preventDefault();
                    dialog_simple_fail("权限名称已存在,请重新输入！！！", false);
                }
            });

        });
    </script>
</head>
<body>
<div id="wrapper">
    <br>
    <form action="/home/permission/rights" method="post" id="rightsForm">
        <table class="form label-colored-form">
            <tbody><tr class="sprite">
                <td class="label-td"><label>权限名称：</label></td>
                <td><input type="text" name="name" required /></td>
            </tr>
            <tr class="sprite">
                <td class="label-td"><label>对应的URL：</label></td>
                <td><input type="text" name="url"  required /></td>
            </tr>
            <tr class="sprite">
                <td class="label-td"><label>权限描述：</label></td>
                <td><input type="text" name="description"  required /></td>
            </tr>
            <tr class="sprite">
                <td class="label-td"><label>上级权限：</label></td>
                <td>
                    <select name="parentId" id="sel_parentId" required class="free">
                        <option value='-1'>无上级权限</option>
                    </select>
                </td>
            </tr>
            </tbody>
        </table>
    <div class="centered fill pusht">
        <button class="btn btn-primary">添加</button>
        &nbsp;&nbsp;
        <a class="btn" onclick="dialog_close();">取消</a>
    </div>
    </form>
</div>
<c:if test="${resultState eq 'success'}">
    <script type="text/javascript">
        dialog_simple_ok('恭喜，添加权限成功！', false);
         parent.$("#mainframe")[0].contentWindow.flushPage("#rights_dataTable");
    </script>
</c:if>
<c:if test="${resultState eq 'error'}">
    <script type="text/javascript">
        dialog_simple_fail('抱歉，添加权限失败', false);
    </script>
</c:if>
</body>
</html>
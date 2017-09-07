<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title> 角色 - 新增页面 </title>
    <link rel="stylesheet" type="text/css" href="/css/core.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.10.3.custom.min.css"/>
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="/js/crius.js" charset="utf-8"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            // 获取所有的权限
            $.getJSON("/home/permission/rights/list",function(json) {
                var html = "<table><tr>";
                $.each(json.data,function(i, item) {
                    if(item.parentId===-1) {
                        html += "</tr><tr style='font-size:15px;'><td><input type='checkbox' id='" + item.url + "' name='rights' value='" + item.id + "'  />&nbsp;<label for='" + item.url + "' class='inline-label'>" + item.name + "</label></td></tr>";
                        $.each(json.data,function(i, item2) {
                            if(item2.parentId==item.id&&item.parentId===-1) {
                                html += "</tr><tr style='font-size:15px;'><td style='padding-left:25px;'><input type='checkbox' id='" + item2.url + "' name='rights' value='" + item2.id + "'  />&nbsp;<label for='" + item2.url + "' class='inline-label'>" + item2.name + "</label></td></tr>";
                                $.each(json.data,function(i, item3) {
                                    if(item3.parentId==item2.id&&item2.parentId==item.id&&item.parentId===-1) {
                                        html += "</tr><tr style='font-size:15px;'><td style='padding-left:50px;'><input type='checkbox' id='" + item3.url + "' name='rights' value='" + item3.id + "'  />&nbsp;<label for='" + item3.url + "' class='inline-label'>" + item3.name + "</label></td></tr>";

                                    }
                                });
                            }
                        });
                    }
                });
                html += "</table>"
                $("#allRights").html(html);

                parent.$('#dialog iframe').height($(document).height()).parent().scrollTop(0);
            });

            // 角色名称唯一性判断
            var name_msg = false;
            $("input[name=name]").focusout(function() {
                $.get("/home/permission/role/search",{name:$(this).val()},function(data) {
                    if (data === "error") {
                        dialog_simple_fail("角色名称已存在", false);
                        name_msg = false;
                    } else {
                        name_msg = true;
                    }
                });
            });

            $("#roleForm").submit(function(e) {
                if (name_msg) {
                    name_msg = false;
                    return true;
                } else {
                    e.preventDefault();
                    dialog_simple_fail("角色名称已存在,请重新输入！！！", false);
                }
            });
        });
    </script>
</head>
<body>
<div id="wrapper" >
    <br>
    <form action="/home/permission/role" method="post" id="roleForm">
        <table class="form label-colored-form">
            <tbody><tr class="sprite">
                <td class="label-td"><label>角色名称：</label></td>
                <td><input type="text" name="name" required /></td>
                <td class="label-td"><label>角色描述：</label></td>
                <td><input type="text" name="description"  required /></td>
            </tr>
            <tr class="sprite">
                <td class="label-td"><label>对应权限：</label></td>
                <td colspan="3">
                    <div id="allRights"></div>
                </td>
            </tr>
            </tbody>
        </table>

        <div class="centered fill pusht" id="tool">
            <button class="btn btn-primary">添加</button>
            &nbsp;&nbsp;
            <a class="btn" onclick="dialog_close();">取消</a>
        </div>
    </form>
</div>
<c:if test="${resultState eq 'success'}">
    <script type="text/javascript">
        dialog_simple_ok('恭喜，添加角色成功，而且其权限分配完成', false);
         parent.$("#mainframe")[0].contentWindow.flushPage("#role_dataTable");
    </script>
</c:if>
<c:if test="${resultState eq 'fail'}">
    <script type="text/javascript">
        dialog_simple_fail('角色添加成功，但是角色分配权限失败，请给角色重新分配权限！', false);
        parent.$("#mainframe")[0].contentWindow.flushPage("#role_dataTable");
    </script>
</c:if>
<c:if test="${resultState eq 'error'}">
    <script type="text/javascript">
        dialog_simple_fail('抱歉，添加角色失败', false);
    </script>
</c:if>
</body>
</html>
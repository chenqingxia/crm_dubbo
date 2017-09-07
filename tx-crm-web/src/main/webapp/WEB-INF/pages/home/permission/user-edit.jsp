<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>员工修改页面</title>
    <link rel="stylesheet" type="text/css" href="/css/core.css"/>
    <link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.10.3.custom.min.css"/>
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script type="text/javascript" src="/js/crius.js" charset="utf-8"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            // 给确认按钮绑定点击事件
            $("#btn_confirm").click(function(e) {
                e.preventDefault();
                var locked = $("input:radio[name=locked]:checked").val();
                $.post("/home/permission/user/${user.no}/edit", {locked:locked}, function(result) {
                    if (result === 'success') {
                        dialog_simple_ok("用户锁定修改成功");
                        if(locked === '1') {
                            $("#mainframe",parent.document.body).contents().find('#sel_users :selected').css("background-color","");
                        } else {
                            $("#mainframe",parent.document.body).contents().find('#sel_users :selected').css("background-color","#E2E8F2");
                        }
                    } else {
                        dialog_simple_fail("用户锁定修改失败");
                    }
                });
                dialog_close();
            });
        });
    </script>
</head>
<body>
<div id="wrapper">
    <form method="post">
        <br>
        <p class="centered" style="font-size: 14px;font-weight: bold;">
            <span>用户：</span>
            ${user.name}&nbsp;&nbsp;&nbsp;&nbsp;${user.no}</p>
        <p class="centered">
            <span>锁定：</span>
            <input class="input-r" id="locked_0" name="locked" type="radio" value="0"<c:if test="${user.locked eq 0}"> checked="checked"</c:if>/>
            <label class="inline-label" for="locked_0">是</label>
            &nbsp;
            <input class="input-r" id="locked_1" name="locked" type="radio" value="1"<c:if test="${user.locked eq 1}"> checked="checked"</c:if>/>
            <label class="inline-label" for="locked_1">否</label>
        </p>
        <p class="centered">
            <button class="btn btn-primary" id="btn_confirm">确认</button>
            &nbsp;
            <a class="btn" onclick="dialog_close();">取消</a>
        </p>
    </form>
</div>
</body>
</html>
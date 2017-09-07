<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>wifi云平台-crm系统-用户登录</title>
    <meta property="qc:admins" content="271556720266760116375747716" />
    <link href="http://pic.ofcard.com/themes/b2c/beyJobs/favicon.ico" rel="shortcut icon" type="image/ico" />
    <script type="text/javascript" src="http://pic.ofcard.com/jslib/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="http://pic.ofcard.com/nevinUI/1.0.0/nevinUI.js"></script>
    <script type="text/javascript" src="http://pic.ofcard.com/nevinUI/1.0.0/IE_CSS_Selector_fix.js"></script>
    <script type="text/javascript" src="http://pic.ofcard.com/themes/boss/front/tools.js"></script>
    <script language="javascript" src="http://pic.ofcard.com/themes/boss/js/jquery.form.js"></script>
    <script type="text/javascript" src="http://pic.ofcard.com/themes/admin/js/security.js"></script>
    <script type="text/javascript" src="http://pic.ofcard.com/themes/boss/js/jquery.cookie.js"></script>
    <link href="http://pic.ofcard.com/themes/ofpay/sigma/style.css" type="text/css" rel="stylesheet"/>
    <script>
        function validate(){
            var nickName = $("#username").val();
            var logPsw = $("#password").val();
            if(nickName==''){
                alert("用户名不能为空!");
                $("#username").focus();
                return false;
            }
            if(logPsw==''){
                alert("密码不能为空!");
                $("#password").focus();
                return false;
            }


            randomping();
            var ping = $("#ping").val();
            var password = $("#password").val();

            return true;
        }

        $(document).ready(function () {
            $("#verifychkcode_li").find("input").removeClass("input_t");

            if(!placeholderSupport()){   // 判断浏览器是否支持 placeholder
                $('[placeholder]').focus(function() {
                    var input = $(this);
                    if (input.val() == input.attr('placeholder')) {
                        input.val('');
                        input.css("color","#000000");
                        input.removeClass('placeholder');
                    }
                }).blur(function() {
                            var input = $(this);
                            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                                input.addClass('placeholder');
                                input.css("color","#bbbbbb");
                                input.val(input.attr('placeholder'));
                            }
                        }).blur();
            };

            $("[name='submit_btn']").click(function(){
                if (!validate()) { return false; }
                $('#LoginForm').submit();
            });

            document.onkeydown = function (e) {
                var ev = document.all ? window.event : e;
                if (ev.keyCode == 13) {
                    $("[name='submit_btn']").click();
                }
            }
        });

        function placeholderSupport() {
            return 'placeholder' in document.createElement('input');
        }

        function boundrandom(n, bound) {
            var rnd = "";
            for ( var i = 0; i < n; i++) {
                if (i > 0)
                    rnd += ",";
                rnd += parseInt(Math.random() * bound);
            }
            return rnd;
        }

        function randomping() {
            var randstr = boundrandom(10, 62);
            var BaseStr = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz0123456789";
            var str = "";
            var randarr = randstr.split(",");
            for ( var i = 0; i < randarr.length; i++) {
                str += BaseStr.substring(randarr[i],
                        parseInt(randarr[i]) + 1);
            }
            $("#ping").val(str);
        }

        function fEvent(sType,oInput){
            switch (sType){
                case "focus" :
                    oInput.isfocus = true;
                    oInput.style.backgroundColor='#FFFFD8';
                case "mouseover" :
                    oInput.style.borderColor = '#FFE222';
                    break;
                case "blur" :
                    oInput.isfocus = false;
                    oInput.style.backgroundColor="";
                case "mouseout" :
                    if(!oInput.isfocus){
                        oInput.style.borderColor='#CCCCCC';
                    }
                    break;
            }
        }

        var VerifyCodeTimes=1;

    </script>

    <script type="text/javascript" src="http://qzonestyle.gtimg.cn/qzone/openapi/qc_loader.js"
            data-appid="101046639" data-redirecturi="http://main.ofpay.com/qc_callback.jsp" charset="utf-8"></script>
    <style type="text/css">
        body#login_page #header #logo h1 a {
            background: url("/images/logo_24.png") no-repeat scroll -180px -219px transparent;
            height: 43px;
            width: 116px;
            display:block
        }
        .error{
            color: red;
        }
    </style>
</head>

<body id="login_page">
<div id="header">
    <div id="logo">
        <h1><a href="/" title="wifi云平台-crm系统"><img src="/images/logo_25.png"><span class="hide">wifi云平台-crm系统</span></a></h1>
    </div>
</div>
<input type="hidden" id="needChkCode" value="0"/> <!-- 是否需要验证码 -->
<!--form name='LoginForm' id="LoginForm" method="post" autocomplete="off"-->
<form action="/home/login" method="post" name="LoginForm" id="LoginForm">
    <input type="hidden" name="ping" id="ping" />
    <input type="hidden" name="xsrf" value="1"/>
    <div id="container">
        <div id="loginBox">
            <div class="module">
                <ul class="tab mtitle cf">
                </ul>
                <div class="mbody">
                    <div class="showOne cf">
                        <ul class="loginForm">
                            <c:if test="${resultState eq 'fail'}">
                                <p id="errMsg" class="error">${errMsg}</p>
                            </c:if>
                            <li><input type="text" name="no" id="no" class="input_t hint" placeholder="用户名" autofocus required/></li>
                            <li><input type="password" name="password" id="password" class="input_p" maxlength="128" placeholder="密码" required/></li>

                            <li class="cf">
                                <button class="f_left" id="loginBtn" name="submit_btn" type="button">&nbsp;</button>

                            </li>
                            <li>
                                <div class="login_bottom">
                                    <p class="reg_login_bottom">还没帐号？立即<a href="/register">注册</a></p>
                                </div>
                            </li>

                        </ul>
                    </div>

                </div>
            </div>
        </div>
    </div>
</form>
<!-- 百度统计 -->
<script type="text/javascript">
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F6d51f3e538ac92d52fbe3249476f87b9' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>
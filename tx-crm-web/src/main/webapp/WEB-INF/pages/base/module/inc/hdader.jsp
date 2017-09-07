<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<style type="text/css">
    .hyperlink_disable {
        pointer-events: none;
        cursor: default;
        color: #666;
    }
</style>
<div id="header">
    <div class="module">
        <h1 id="logo"><a href="/module/home"><img  src="/images/logo2.png" alt="" title="后台管理系统"/></a></h1>
        <div id="mainnav">
            <ul class="tab tab-colored">
                <crius:userRights url="/module/business"><li><a href="/module/business" class="<c:if test="${param.nav eq 'business'}">selected</c:if>"><span>商户管理</span></a></li></crius:userRights>
                <crius:userRights url="/module/order"><li><a href="/module/equipment" class="<c:if test="${param.nav eq 'equipment'}">selected</c:if>"><span>设备管理</span></a></li></crius:userRights>
            </ul>
        </div>
    </div>
</div>
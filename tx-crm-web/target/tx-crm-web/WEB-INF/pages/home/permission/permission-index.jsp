<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<title> 权限分配 </title>
<link rel="stylesheet" type="text/css" href="/css/core.css" />
<link rel="stylesheet" type="text/css" href="/css/jquery-ui-1.10.3.custom.min.css"/>
<link rel="stylesheet" type="text/css" href="/css/tab.css"/>
<style type="text/css">
    .text-over-flow {
        width: 530px;
        text-overflow: ellipsis;
        white-space: nowrap;
        overflow: hidden;
    }
    .highColor {
        color: #08C;
        font-weight: bold;
    }
</style>
<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="/js/crius.js" charset="utf-8"></script>
<script type="text/javascript" src="/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/js/jquery.dataTables.init.js"></script>
<script type="text/javascript">
// 表格刷新
function flushPage(table) {
    $(table).refreshCurrent();
};

$(document).ready(function() {
    // 实现Tab页的效果
    // Default Action
    $(".tab_content").hide(); // Hide all content
    $("ul.tabs li:first").addClass("active").show(); // Activate first tab
    $(".tab_content:first").show(); // Show first tab content
    // On Click Event
    $("ul.tabs li").click(function() {
        $("ul.tabs li").removeClass("active"); // Remove any "active" class
        $(this).addClass("active"); // Add "active" class to selected tab
        $(".tab_content").hide(); // Hide all tab content
        var activeTab = $(this).find("a").attr("href"); // Find the rel attribute value to identify the active tab + content
        $(activeTab).fadeIn(); // Fade in the active content
        return false;
    });

    // 初始化页面，（user的tab页里面的内容）

    $("#li_role").click(function () {
        $("input[name=name]").val("");
        $("input[name=description]").val("");
        $('#role_dataTable').dataTables( {
            "sAjaxSource": "/home/permission/role/list",
            "fnServerData": function ( sSource, aoData, fnCallback ) {
                var postData = aoData.concat($('#role_searchForm').serializeArray());
                $.post( sSource, postData, function (json) {
                    fnCallback(json.data);
                    //取消全选
                    $.each($("input[name='role_id[]']"), function() {
                        $(this).click(function() {
                            var ckValue = $(this).prop('checked');
                            if(ckValue == false) {
                                $("#role_ids").prop("checked", ckValue);
                            }
                        });
                    });
                },"json");
            },
            "aoColumns" : [{
                "mDataProp": function ( aData, type, val ) {
                    val ='<input type="checkbox" name="role_id[]" id="id_'+aData.id+'" value="'+aData.id+'"/>';
                    return val;
                },
                "sName":"id",
                "bSortable": false
            },{ "sName": "r1.id", "mDataProp": "id", "bSortable": true
            },{
                "mDataProp": function ( aData, type, val ) {
                    val = '<a class="link_detail" href="/home/permission/role/'+aData.id+'">'+aData.name+'</a>';
                    return val;
                },
                "sName": "r1.name",
                "bSortable": false
            },{ "sName": "description", "mDataProp": "description", "bSortable": false
            },{
                "mDataProp": function ( aData, type, val ) {
                    if(aData.rights == null || aData.rights == "") {
                        val = "";
                    } else {
                        val = "<div class='text-over-flow'>"+aData.rights+"</div>";
                    }
                    return val;
                },
                "sName": "rights",
                "bSortable": false
            }]
        });
    });



    $("#li_rights").click(function () {
        // 切换TAB页的时候请空表单数据
        $("input[name=name]").val("");
        $("input[name=description]").val("");
        $('#rights_dataTable').dataTables( {
            "sAjaxSource": "/home/permission/rights/list",
            "fnServerData": function ( sSource, aoData, fnCallback ) {
                var postData = aoData.concat($('#rights_searchForm').serializeArray());
                $.post( sSource, postData, function (json) {
                    fnCallback(json.data);
                    // 取消全选
                    $.each($("input[name='rights_id[]']"), function() {
                        $(this).click(function() {
                            var ckValue = $(this).prop('checked');
                            if(ckValue == false) {
                                $("#rights_ids").prop("checked", ckValue);
                            }
                        });
                    });
                },"json");
            },
            "aoColumns" : [{
                "mDataProp": function ( aData, type, val ) {
                    val ='<input type="checkbox" name="rights_id[]" id="id_'+aData.id+'" value="'+aData.id+'"/>';
                    return val;
                },
                "sName":"id",
                "bSortable": false
            },{ "sName": "id", "mDataProp": "id", "bSortable": true
            },{
                "mDataProp": function ( aData, type, val ) {
                    val = '<a class="link_detail" href="/home/permission/rights/'+aData.id+'">'+aData.name+'</a>';
                    return val;
                },
                "sName": "name",
                "bSortable": true
            },{
                "mDataProp": function ( aData, type, val ) {
                    if (aData.parentId === -1) {
                        val = "无上级权限";
                    } else {
                        val = aData.parentId;
                    }
                    return val;
                },
                "sName": "parent_id",
                "bSortable": true
            },{ "sName": "url", "mDataProp": "url", "bSortable": false
            },{ "sName": "description", "mDataProp": "description", "bSortable": false
            }]
        });
    });

    // method------------------------------------
    // 详情
    $('#rights_dataTable').on('click', '.link_detail', function(e) {
        e.preventDefault();
        var str_href = $(this).attr("href");
        dialog_iframe({url:str_href, title:"权限【"+str_href.split('/')[4]+"】详细：", width:500, height:330});
    });
    $('#role_dataTable').on('click', '.link_detail', function(e) {
        e.preventDefault();
        var str_href = $(this).attr("href");
        dialog_iframe({url:str_href, title:"角色【"+str_href.split('/')[4]+"】详细：", width:900});
    });


    // 新增
    $("#btn_save_rights").on("click", function(e) {
        e.preventDefault();
        var str_href = $(this).attr("href");
        dialog_iframe({url:str_href, title:"新增权限：", width:500, height:310});
    });

    $("#btn_save_role").on("click", function(e) {
        e.preventDefault();
        var str_href = $(this).attr("href");
        dialog_iframe({url:str_href, title:"新增角色：", width:900});
    });

    // 全选框
    $('#rights_ids').click(function() {
        var ckValue = $(this).prop('checked');
        $.each($("input[name='rights_id[]']"), function() {
            $(this).prop("checked", ckValue);
        });
    });
    $('#role_ids').click(function() {
        var ckValue = $(this).prop('checked');
        $.each($("input[name='role_id[]']"), function() {
            $(this).prop("checked", ckValue);
        });
    });


    // 删除按钮
    $('#btn_remove_rights').click(function() {
        var values = new Array();
        $.each($("input[name='rights_id[]']:checked"), function() {
            values.push($(this).val());
        });
        if (values.length === 0) {
            dialog_simple_fail('没有选择权限');
            return;
        }
        dialog_confirm({content: "确定你要这么做？删除权限是非常危险的动作，请慎重！！！", yes:function(){
            $.post("/home/permission/rights/delete", { ids: values}, function(data) {
                if (data === 'success') {
                    dialog_simple_ok("恭喜，删除操作成功！！");
                    $("#rights_ids").prop("checked", false);
                    flushPage("#rights_dataTable");
                } else if (data === "error"){
                    dialog_simple_fail("抱歉，删除操作失败");
                } else {
                    dialog_simple_fail(data);
                }
            });
        }})
    });

    $('#btn_remove_role').click(function() {
        var values = new Array();
        $.each($("input[name='role_id[]']:checked"), function() {
            values.push($(this).val());
        });
        if (values.length === 0) {
            dialog_simple_fail('没有选择角色');
            return;
        }
        dialog_confirm({content: "确定你要这么做？删除角色是非常危险的动作，请慎重！！！", yes:function(){
            $.post("/home/permission/role/delete", { ids: values}, function(data) {
                if (data === 'success') {
                    dialog_simple_ok("恭喜，删除操作成功！！");
                    $("#role_ids").prop("checked", false);
                    flushPage("#role_dataTable");
                } else if (data === "error"){
                    dialog_simple_fail("抱歉，删除操作失败");
                } else {
                    dialog_simple_fail(data);
                }
            });
        }})
    });

    // ------------------------------------  用户管理  -------------------------------------------------------



    $.getJSON("/home/permission/rights/list",function(json) {
        //alert("rightsjson="+json.data);
        var html = "<table><tr>";
        $.each(json.data,function(i, item) {
            if(item.parentId===-1) {
                html += "</tr><tr style='font-size:15px;'><td><input type='checkbox' id='" + item.url + "' name='rights' value='" + item.id + "' disabled />&nbsp;<label for='" + item.url + "' class='inline-label'>" + item.name + "</label></td></tr>";
                $.each(json.data,function(i, item2) {
                    if(item2.parentId==item.id&&item.parentId===-1) {
                        html += "</tr><tr style='font-size:15px;'><td style='padding-left:25px;'><input type='checkbox' id='" + item2.url + "' name='rights' value='" + item2.id + "' disabled />&nbsp;<label for='" + item2.url + "' class='inline-label'>" + item2.name + "</label></td></tr>";
                        $.each(json.data,function(i, item3) {
                            if(item3.parentId==item2.id&&item2.parentId==item.id&&item.parentId===-1) {
                                html += "</tr><tr style='font-size:15px;'><td style='padding-left:50px;'><input type='checkbox' id='" + item3.url + "' name='rights' value='" + item3.id + "' disabled />&nbsp;<label for='" + item3.url + "' class='inline-label'>" + item3.name + "</label></td></tr>";

                            }
                        });
                    }
                });
            }
        });
        html += "</table>"
        $("#allRights").html(html);
    });

    $("#li_user").click(function (e) {
        e.preventDefault();
        $("#side-menu li a",parent.document.body)[1].click();
    });

    $(document).ajaxStart(function(){
        $('#loading_role').show();
        $('#loading_rights').show();
    }).ajaxStop(function(){
        $('#loading_role').hide();
        $('#loading_rights').hide();
    });

    $('#link_locked_user').on('click', function(e) {
        e.preventDefault();
        var str_href = $(this).attr("href");
        var no = $("#sel_users option:selected").val();
        if (no === undefined) {
            dialog_simple_fail('请选择用户');
            return;
        }
        dialog_iframe({url:str_href +'/'+no, title:"修改用户帐号锁定状态", width:380, height:190});
    });

    // 搜索(点击搜索按钮触发)(支持输入工号。姓名)
    $('#btn_search_user').click(function() {
        var value = $(this).prev().val();
        if (value.length === 0) {
            return;
        }
        $("#sel_users option").each(function() {
            if ($(this).html().indexOf(value) >= 0) {
                $(this).prop("selected",true).change();
            } else {
                $(this).prop("selected",false);
            }
        });
    });


    // 点击下拉框的option绑定一件事件
    // 自己不可以修改自己的权限，状态
    $("#sel_users").change(function() {
        $(".input-append input").val($(this).val());
        var no = $("#sel_users option:selected").val();
        var name = $('#sel_users option:selected').data('name');
        if (no === "${sessionScope.no}") {
            $("#link_locked_user").css("display","none");
            $("#link_remove_user").css("display","none");
            $("input:checkbox[name='roles']").prop("disabled", true);
            $("#btn_update").css("display","none");
            $("#btn_reset").css("display","none");
        } else {
            $("#link_locked_user").show();
            $("#link_remove_user").show();
            $("input:checkbox[name='roles']").prop("disabled", false);
            $("#btn_update").show();
            $("#btn_reset").show();
        }
        showRoleAndPermissions(no, name);
    });

    // 修改用户对应的角色（点击修改按钮触发）
    $("#btn_update").click(function() {
        var id = $('#sel_users option:selected').data('id');
        var no = $('#sel_users option:selected').val();
        if (id === null) {
            return;
        }
        var $button = $(this).prop('disabled', true);
        var postData = $('#form_role').serializeArray();
        var jqxhr = $.post('/home/permission/user/' + id + "/" + no + "/editRole", postData, function(result) {
            if (result == 'success') {
                dialog_simple_ok();
                // 修改成功刷新页面
                window.parent.frames['mainframe'].location.reload();
            } else {
                dialog_simple_fail();
            }
        });
        jqxhr.always(function(){ $button.prop('disabled', false); });

    });

    // 给重置按钮绑定事件
    $("#btn_reset").click(function() {
        var no = $("#sel_users option:selected").val();
        var name = $('#sel_users option:selected').data('name');
        if (no !== undefined) {
            showRoleAndPermissions(no,name);
        }
    });

    // 删除用户
    $('#link_remove_user').click(function() {
        var id = $('#sel_users option:selected').data('id');
        var no = $("#sel_users option:selected").val();
        if (id === null) {
            dialog_simple_fail('请选择用户');
            return;
        }
        dialog_confirm({content: "确定你要这么做？删除用户是非常危险的动作，请慎重！！！", yes:function(){
            $.post("/home/permission/user/" + no + "/delete", {id: id}, function(data) {
                if (data === 'success') {
                    dialog_simple_ok("恭喜，用户删除成功！！");
                    window.parent.frames['mainframe'].location.reload();
                } else if (data === "error"){
                    dialog_simple_fail("抱歉，用户删除失败");
                }
            });
        }})
    });

    // 点击角色的时候，展现是改角色对应的权限
    $("#role_table").on('click', '.inline-label', function() {
        $("#userOrRoleRights").html("角色：" + $(this).html() + " —对应的权限：<img src='/images/ajax-loader-snake.gif' id='loading_rights' style='display: none;'/>");
        $("#userOrRoleToRights").html("<input type='button' value='>>' disabled/><br/><br/><input type='button' value='<<' disabled/>");
        var $box_rights = $("input:checkbox[name='rights']").prop("checked", false);
        $box_rights.each(function() {
            $(this).next().removeClass("highColor");
        });
        $.post("/home/permission/role/rights/list",{roleId:$(this).prev().val()},  function(json) {
            $.each(json.data, function (i, item) {
                $box_rights.each(function() {
                    if(item.rightsId == $(this).val()) {
                        $(this).prop("checked", true);
                        $(this).next().addClass("highColor");
                    }
                });
            });
        }, "json");

    });

});

var showRoleAndPermissions = function(no, name) {
    var $box_role = $("input:checkbox[id^=box_]").prop("checked", false);
    var $box_rights = $("input:checkbox[name='rights']").prop("checked", false);
    $("#userOrRoleRights").html("用户：" + name + " —对应的权限：<img src='/images/ajax-loader-snake.gif' id='loading_rights' style='display: none;'/>");
    $("#userOrRoleToRights").html("");
    $box_role.each(function() {
        $(this).next().removeClass("highColor");
    });
    $box_rights.each(function() {
        $(this).next().removeClass("highColor");
    });
    $.post("/home/permission/user/" + no, function(json) {
        $.each(json.data, function (i, item) {
            $box_role.each(function() {
                if(item.roleId == $(this).val()) {
                    $(this).prop("checked", true);
                    $(this).next().addClass("highColor");
                }
            });
            $box_rights.each(function() {
                if(item.rightsId == $(this).val()) {
                    $(this).prop("checked", true);
                    $(this).next().addClass("highColor");
                }
            });
        });
    }, "json");
}

</script>
</head>
<body>
<div id="wrapper">
    <ul class="tabs">
        <li id="li_user"><a href="#user">用户管理</a></li>
        <crius:userRights url="/home/permission/role"><li id="li_role"><a href="#role">角色管理</a></li></crius:userRights>
        <crius:userRights url="/home/permission/rights"><li id="li_rights"><a href="#rights">权限管理</a></li></crius:userRights>
    </ul>
    <div class="tab_container">
        <%-- 用户管理：用于展示所有用户，以及其对应的角色、其对应的权限、维护用户角色直接的关系，并且管理用户帐号的冻结与否 --%>
        <div id="user" class="tab_content">
            <table style="margin-top:10px;">
                <tbody> <tr>
                    <td colspan="5">
                        <div class="input-append" >
                            <input type="text" size="100"/><button class="btn" id="btn_search_user">搜索用户</button>
                            &nbsp;&nbsp;
                            <crius:userRights url="/home/permission/user/locked"><a href="/home/permission/user" id="link_locked_user" class="btn view-stat">修改用户帐号锁定状态</a></crius:userRights>
                            &nbsp;&nbsp;
                            <crius:userRights url="/home/permission/user/delete"><a href="javascript:void(0)" id="link_remove_user" class="btn view-stat">删除用户</a></crius:userRights>
                        </div>
                    </td>
                </tr>
                <tr height="15px"> <td colspan="5"></td></tr>
                <tr>
                    <td width="8%" style="vertical-align: top">
                        <select id="sel_users" size="30" style="font-size: 13px;">
                            <c:forEach items="${users}" var="user" varStatus="status">
                                <option value="${user.no}" data-id="${user.id}" data-name="${user.name}" <c:if test="${user.locked eq 0}"> style="background-color:#E2E8F2;"</c:if>>${status.count}.用户：${user.no}&nbsp;&nbsp;${user.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td width="4%" class="centered">
                        <input type="button" value=" >> " disabled />
                        <br/><br/>
                        <input type="button" value=" << " disabled />
                    </td>
                    <td width="15%" style="vertical-align: top">
                        <form id="form_role">
                            <table class="table table-bordered table-striped" style="margin-top: 0px;margin-bottom: 0px;height: 520px;" id="role_table">
                                <thead>
                                <tr>
                                    <th id="userToRole">用户对应的角色：<img src="/images/ajax-loader-snake.gif" id="loading_role" style="display: none;"/></th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${roles}" var="role" varStatus="status">
                                    <tr>
                                        <td><input name="roles" id="box_${role.id}" value="${role.id}" class="input-c" type="checkbox">&nbsp;
                                            <label for="" class="inline-label">${role.name}</label></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                                <tfoot><tr>
                                    <td colspan="3" class="centered">
                                        <crius:userRights url="/home/permission/user/edit"><input type="button" value="修改" class="btn btn-primary" id="btn_update"/>
                                            &nbsp;&nbsp;
                                            <input type="button" value="重置" class="btn" id="btn_reset"/></crius:userRights>
                                    </td>
                                </tr>
                                </tfoot>
                            </table>
                        </form>
                    </td>
                    <td width="3%" class="centered" id="userOrRoleToRights">
                    </td>
                    <td style="vertical-align: top">
                        <table class="form" style="border: 1px solid #ddd; border-bottom: 0">
                            <thead>
                            <tr>
                                <th style="padding-left: 20px; text-align: left; height: 25px;" id="userOrRoleRights">用户对应的权限：<img src="/images/ajax-loader-snake.gif" id="loading_rights" style="display: none;"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td >
                                    <div id="allRights"></div>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                </tbody>
            </table>

        </div>

        <%-- 角色管理 --%>
        <div id="role" class="tab_content">
            <div class="search-form pusht">
                <form id="role_searchForm" onsubmit="$('#role_dataTable').refreshData();return false;">
                    <div class="trigger-content">
                        <button class="btn btn-primary">搜索</button>
                    </div>
                    <ul>
                        <li>
                            <div class="input-prepend"> <span class="add-on">角色名称：</span>
                                <input type="text" name="name" class="input-60p" >
                            </div>
                        </li>
                        <li>
                            <div class="input-prepend"> <span class="add-on">角色描述：</span>
                                <input type="text" name="description" class="input-60p">
                            </div>
                        </li>
                    </ul>
                </form>
            </div>

            <div class="table-toolbar pusht">
                <crius:userRights url="/home/permission/role/delete"><div class="btn-group left"> <a href="javascript:void(0)" id="btn_remove_role" class="btn view-stat">删除</a> </div></crius:userRights>
                <crius:userRights url="/home/permission/role/new"><div class="btn-group left"> <a href="/home/permission/role/new" id="btn_save_role" class="btn view-stat">新增角色</a> </div></crius:userRights>
            </div>

            <div class="dataTable-wrapper">
                <table id="role_dataTable" class="table table-bordered table-striped centered">
                    <thead>
                    <tr role="row">
                        <th width="40px"><input type="checkbox" name="role_ids" id="role_ids" /></th>
                        <th>编号</th>
                        <th>角色名称</th>
                        <th>角色描述</th>
                        <th width="550px">对应的权限</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>

        <%-- 权限管理 --%>
        <div id="rights" class="tab_content">
            <div class="search-form pusht">
                <form id="rights_searchForm" onsubmit="$('#rights_dataTable').refreshData();return false;">
                    <div class="trigger-content">
                        <button class="btn btn-primary">搜索</button>
                    </div>
                    <ul>
                        <li>
                            <div class="input-prepend"> <span class="add-on">权限名称：</span>
                                <input type="text" name="name" placeholder="" class="input-60p">
                            </div>
                        </li>
                        <li>
                            <div class="input-prepend"> <span class="add-on">权限描述：</span>
                                <input type="text" name="description" placeholder="" class="input-large">
                            </div>
                        </li>
                    </ul>
                </form>
            </div>

            <div class="table-toolbar pusht">
                <crius:userRights url="/home/permission/rights/delete"><div class="btn-group left"> <a href="javascript:void(0)" id="btn_remove_rights" class="btn view-stat">删除</a> </div></crius:userRights>
                <crius:userRights url="/home/permission/rights/new"><div class="btn-group left"> <a href="/home/permission/rights/new" id="btn_save_rights" class="btn view-stat">新增权限</a> </div></crius:userRights>
            </div>

            <div class="dataTable-wrapper">
                <table id="rights_dataTable" class="table table-bordered table-striped centered">
                    <thead>
                    <tr role="row">
                        <th width="40px"><input type="checkbox" name="rights_ids" id="rights_ids" /></th>
                        <th>编号</th>
                        <th>权限名称</th>
                        <th>上级权限编号</th>
                        <th>对应的URL</th>
                        <th>权限描述</th>
                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
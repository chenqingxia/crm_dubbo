<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>
<!doctype html>
<html lang="zh-CN">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>商家用户管理</title>
    <jsp:include page="/common/commonFile.jsp"/>
    <script type="text/javascript">
        Date.prototype.pattern=function(fmt) {
            var o = {
                "M+" : this.getMonth()+1, //月份
                "d+" : this.getDate(), //日
                "h+" : this.getHours()%12 == 0 ? 12 : this.getHours()%12, //小时（12小时制）
                "H+" : this.getHours(), //小时（24小时制）
                "m+" : this.getMinutes(), //分
                "s+" : this.getSeconds() //秒
            };
            if(/(y+)/.test(fmt)){
                fmt = fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
            }
            for(var k in o){
                if(new RegExp("("+ k +")").test(fmt)){
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
                }
            }
            return fmt;
        }

        // 获得当天凌晨时间
        var fnGetToday = function() {
            var now = new Date();
            return new Date(now.getFullYear(),now.getMonth(),now.getDate())
                    .pattern("yyyy-MM-dd");
        }

        // 获得当前时间
        var fnGetNow = function() {
            var now = new Date();
            return now.pattern("yyyy-MM-dd HH:mm:ss");
        }

        // 页面加载完毕时执行
        $(document).ready(function() {
            // 初始化时间控件
            // $("#startTime1").datetimepicker().val(fnGetToday);
            // $("#startTime2").datetimepicker().val(fnGetToday);
            $("#endTime1").datepicker();
            $("#endTime2").datepicker();
            var now = new Date();
            $("#startTime1").datepicker().val(new Date(now.getTime() - 86400000).pattern("yyyy-MM-dd"));
            $("#startTime2").datepicker().val(now.pattern("yyyy-MM-dd"));


            $("#statistics_info").hide();
            // 加载统计信息

        });

        //请求统计数据
        var fnCount = function() {
            $.post("/order/predial/countInfo", $("#searchForm").serializeArray(), function(json) {
                $("#s_orderCount").text(json.data.orderCount);
                if(json.data.sumOrderVal!=null){
                    $("#s_sumOrderVal").text(json.data.sumOrderVal);
                }
                if(json.data.sumSalePrice!=null){
                    $("#s_sumSalePrice").text(json.data.sumSalePrice);
                }
            }, "json");
        }

        function search(){
            /// fnCount();
            $(".toggle-content").hover(function() {$(this).find(".dropdown-menu").show(); }, function() {$(this).find(".dropdown-menu").hide();});
            $("#dataTable").dataTables({
                "sAjaxSource":"/business/shopuser/list",
                "fnServerData":function(sSource, aoData, fnCallback) {
                    var postData = aoData.concat($("#searchForm").serializeArray());
                    $.post(sSource, postData, function(json) {
                        fnCallback(json.data);
                    }, "json");
                },
                "aoColumns":[
                    {"sName":"Login_Name", "mDataProp":"Login_Name", "bSortable":true
                    }
                ]
            });

            $("#dataTable").columnManager({"listTargetID":"column-edit-01"});

        }
    </script>

</head>
<body>
<div id="wrapper">
    <div class="breadCrumb">
        <a href="" onclick="parent.location.href='/module/home'"><i class="icon icon-home icon-orange"></i> 首页 </a>
        &nbsp;&gt;&nbsp;
        <a href="" onclick="parent.location.href='/module/business'">商户</a>
        &nbsp;&gt;&nbsp;
        <span>商家用户管理</span>
    </div>

    <div class="search-form pusht">
        <form id="searchForm" onsubmit="$('#dataTable').refreshData();return false;">
            <ul>
            <li>
                <div class="input-prepend">
                    <span class="add-on">登陆名：</span>
                    <input type="text" name="mobileNo" id="mobileNo" class="input-40p">
                </div>
            </li>
            <li>
                <div class="input-prepend">
                    <span class="add-on">商户名：</span>
                    <input type="text" name="faceVal" id="faceVal" class="input-40p">
                </div>
            </li>
            <li>
                <div class="input-prepend">
                    <span class="add-on">状态：</span>
                    <select name="state">
                        <option value="">全部</option>
                        <option value="0">正常</option>
                        <option value="1">删除</option>

                    </select>
                </div>
            </li>
            <br>

            <li>
                <div class="input-prepend">
                    <span class="add-on">创建时间：</span>
                    <input type="text" name="startTime1" id="startTime1" class="input-combo-time">
                    <span  class="add-on">至</span>
                    <input type="text" name="startTime2" id="startTime2" class="input-combo-time">
                </div>
            </li>
            <li>
                <div class="input-prepend">
                    <span class="add-on">修改时间：</span>
                    <input type="text" name="endTime1" id="endTime1" class="input-combo-time">
                    <span  class="add-on">至</span>
                    <input type="text" name="endTime2" id="endTime2" class="input-combo-time">
                </div>
            </li>
            <li>
                <div>
                    <button class="btn btn-primary" onclick="search()">搜索</button>
                </div>
            </li>
            </ul>
        </form>
    </div>

    <div class="alert alert-info" id="moneydiv">
        <table id="statistics_infoleft">
            <tr>
                <td><h5>商户数量：</h5></td>
                <td width="80px" id="s_orderCount"></td>
                <td><h5>有效商户数量：</h5></td>
                <td id="s_sumOrderVal" style="color:cornflowerblue; width:80px;"></td>
                <td><h5>删除商户数量：</h5></td>
                <td id="s_sumSalePrice" style="color:green; width:80px;"></td>
            </tr>
        </table>
    </div>
    <!--div class="table-toolbar pusht">
        <ul class="horizonal">
            <li>
                <a id="export" type="export" class="btn">下载查询结果</a>
                <span class="char">（最多支持7天范围内数据）</span>
            </li>
        </ul>
    </div> -->

    <div class="table-toolbar pusht">
        <div class="btn-group right toggle-content">
            <a class="btn" href="#">
                <i class="icon icon-ok"></i>
                更改列显示
            </a>
            <a class="btn" href="#">
                <i class="icon icon-chevron-down"></i>
            </a>
            <div class="dropdown-menu twin-cols-dropdown-menu" id="column-edit-01"></div>
        </div>
    </div>

    <div class="dataTable-wrapper">
        <table id="dataTable" class="table table-bordered table-striped centered">
            <thead>
            <tr role="row">
            <tr role="row">
                <th>登陆名</th>
                <th>商户名</th>
                <th>商户地址</th>
                <th>创建时间</th>
                <th>修改时间</th>
                <th>用户状态</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2020/11/19
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<c:set var="img1" value="${pageContext.request.contextPath}/upload/value"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>应学后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>
    <script>
        $(function () {
            //初始化表单属性
            $("#vTable").jqGrid({
                url: "${path}/admain/log",  //分页查询   page  rows  total recoreds  page  rows
                <%--editurl: "${path}/video/edit",--%>
                datatype: "json",
                rowNum: 10,  //每页展示是条数
                rowList: [10, 20, 30],
                pager: '#vPager',
                styleUI: "Bootstrap",
                height: "auto",
                autowidth: true,
                viewrecords: true,  //是否展示数据总条数
                colNames: ['ID', '名称','时间', '操作', '状态'],
                colModel: [
                    {name: 'id', width: 55},
                    {name: 'name', editable: true, width: 90},
                    {
                        name: 'time', width: 100,height:400, align: "center",
                    },
                    {
                        name: 'option', editable: true, width: 100,align: "center",
                    },
                    {name: 'status', width: 80, align: "center"},
                ]
            });

            //处理曾删改查操作   工具栏
            $("#vTable").jqGrid('navGrid', '#vPager',
                {edit: false, add: false, del: false,search:false,edittext: "修改", addtext: "添加", deltext: "删除"},
                {


                }, //执行修改之后的额外操作
                {

                }, //执行添加之后的额外操作
                {

                } //执行删除之后的额外操作
            );
        });


    </script>
</head>
<body>
<div class="panel panel-info">

    <%--面板头--%>
    <div class="panel panel-heading">日志信息
    </div>

    <%--选项卡--%>
    <div class="nav nav-tabs">
        <li class="active"><a href="">日志信息</a></li>
    </div>

    <%--初始化表单--%>
    <table id="vTable"/>

    <%--工具栏--%>
    <div id="vPager"/>

</div>
</body>
</html>

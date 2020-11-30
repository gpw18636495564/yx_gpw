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

        $(function(){
            pageInit();
        });

        function pageInit(){
            $("#cateTable").jqGrid({
                url : "${path}/cate/cateAll",
                datatype : "json",
                rowNum : 10,
                rowList : [ 5, 10],
                pager : '#catePage',
                sortname : 'id',
                viewrecords : true,
                styleUI:"Bootstrap",
                autowidth:true,
                cellEdit:true,//开启单元格的修改操作 配合editurl操作
                editurl:"${pageContext.request.contextPath}/cate/edit", //add添加 exit修改 del删除
                height:"auto",
                colNames : [ 'ID', '类别名称', '级别'],
                colModel : [
                    {name : 'id',index : 'id'},
                    {name : 'cate_name',index : 'invdate',editable:true},
                    {name : 'levels',index : 'name',align: 'center'},
                ],
                subGrid : true,  //开启子表格
                // subgrid_id:是在创建表数据时创建的div标签的ID
                //row_id是该行的ID
                subGridRowExpanded : function(subgrid_id, row_id) {
                    addSubGrid(subgrid_id, row_id);
                }
            }).navGrid(//开启增删改
                "#catePage",
                {add:true,edit:true,del:true,search:false,refresh:true},  //对展示增删改按钮配置
                {
                    closeAfterEdit: true,//关闭面板
                    reloadAfterSubmit: true,
                    afterSubmit:function (data) {
                        console.log(data)

                        //设置提示信息
                        $("#message").html(data.responseJSON.message);

                        //展示警告框
                        $("#showMsg").show();

                        setTimeout(function () {
                            $("#showMsg").hide();
                        },3000);
                        return "hello";
                    },
                },//编辑面板的配置
                {   closeAfterAdd: true,
                    afterSubmit:function (data) {
                        console.log(data)

                        //设置提示信息
                        $("#message").html(data.responseJSON.message);

                        //展示警告框
                        $("#showMsg").show();

                        setTimeout(function () {
                            $("#showMsg").hide();
                        },3000);
                        return "hello";
                    },
                    reloadAfterSubmit: true,
                },//添加面板的配置
                {   closeAfterDelete: true,
                    reloadAfterSubmit: true,
                    afterSubmit:function (data) {
                        console.log(data)

                        //设置提示信息
                        $("#message").html(data.responseJSON.message);

                        //展示警告框
                        $("#showMsg").show();

                        setTimeout(function () {
                            $("#showMsg").hide();
                        },3000);
                        return "hello";
                    },
                },//删除的配置
                {
                    sopt:['cn','eq'],//用来展示那些搜索的运算符
                },
            );
        }


        //开启子表格的样式
        function addSubGrid(subgridId, rowId){

            var subgridTableTd= subgridId + "Table";
            var pagerId= subgridId+"Page";


            $("#"+subgridId).html("" +
                "<table id='"+subgridTableTd+"' />" +
                "<div id='"+pagerId+"' />"
            );



            $("#" + subgridTableTd).jqGrid({
                url : "${path}/cate/cateAll?id="+ rowId,
                datatype : "json",
                rowNum : 20,
                pager : "#"+pagerId,
                sortname : 'num',
                sortorder : "asc",
                styleUI:"Bootstrap",
                autowidth:true,
                height:"auto",
                colNames : [ 'ID', '类别名称', '级别'],
                colModel : [
                    {name : "id",  index : "num",key : true,
                    },
                    {name : "cate_name",index : "item",editable:true},
                    {name : "levels",index : "qty",align :"center"},
                ],
                cellEdit:true,//开启单元格的修改操作 配合editurl操作
                editurl:"${pageContext.request.contextPath}/cate/edit?parent_id="+rowId,
            });

            $("#" + subgridTableTd).jqGrid('navGrid',"#" + pagerId, {edit : true,add : true,del : true,search:false},{
                closeAfterEdit: true,//关闭面板
                    reloadAfterSubmit: true,
                    afterSubmit:function (data) {
                        console.log(data)

                        //设置提示信息
                        $("#message").html(data.responseJSON.message);

                        //展示警告框
                        $("#showMsg").show();

                        setTimeout(function () {
                            $("#showMsg").hide();
                        },3000);
                        return "hello";
                    },
            },//编辑面板的配置
            {   closeAfterAdd: true,
                reloadAfterSubmit: true,
                afterSubmit:function (data) {
                    console.log(data)

                    //设置提示信息
                    $("#message").html(data.responseJSON.message);

                    //展示警告框
                    $("#showMsg").show();

                    setTimeout(function () {
                        $("#showMsg").hide();
                    },3000);
                    return "hello";
                },
            },//添加面板的配置
            {   closeAfterDelete: true,
                reloadAfterSubmit: true,
                afterSubmit:function (data) {
                    console.log(data)

                    //设置提示信息
                    $("#message").html(data.responseJSON.message);

                    //展示警告框
                    $("#showMsg").show();

                    setTimeout(function () {
                        $("#showMsg").hide();
                    },3000);
                    return "hello";
                },
            },//删除的配置
            {
                sopt:['cn','eq'],//用来展示那些搜索的运算符
            },);
        }
    </script>
</head>
<body>
<div class="panel panel-success">
    <div class="panel-heading">类别信息
    </div>
    <div class="panel-body">
        <div>
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">分类管理</a></li>
                <li role="presentation" class="active">  <%--警告框--%>
                    <div id="showMsg" style="width:300px; display: none" class="alert alert-danger alert-dismissible center-block" role="alert">
                        <span id="message"/>
                    </div>
                </li>
            </ul>
            <!-- Tab panes -->
        </div>

        <%--表单--%>
        <table id="cateTable" />

        <%--分页工具栏--%>
        <div id="catePage" />
    </div>
</div>
</body>
</html>

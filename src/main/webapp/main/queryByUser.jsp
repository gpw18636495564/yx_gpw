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
            $("#list").jqGrid({
                styleUI:"Bootstrap",
                url:"${path}/user/pageAll",
                datatype:"json",
                celledit:true,
                autowidth: true,
                height:true,
                colNames: ["ID","昵称","头像","手机号","简介","状态","分数","注册时间","性别","地址"],
                colModel:[
                    {name:'id',align:"center"},
                    {name:"nick_name",align:"center",editable:true,},
                    {name:"pic_img",align:"center",editable:true,edittype:"file",
                        formatter:function (value,option,row) {
                         return "<img src='"+value+"' style='height:50px;width:50px'/>";
                        }
                    },
                    {name:"phone",align:"center",editable:true,},
                    {name:"brief",align:"center",editable:true,},
                    {name:"state",align:"center",
                        formatter:function (value,option,row) {
                            if(value=='正常') {
                                return "<button type='button' class='btn btn-success' onclick='u_update(\""+row.id+"\")'>" + value + "</button>";
                            }return "<button type='button' class='btn btn-danger' onclick='u_update(\""+row.id+"\")'>" + value+ "</button>";
                    }},
                    {name:"score",align:"center"},
                    {name:"create_date",align:"center",
                    },
                    {name:"sex",align:"center",editable:true,},
                    {name:"address",align:"center",editable:true,}
                ],
                pager:"#aa",
                page:1,//指定初始页码
                rowNum:10,
                rowList:[20,10,30],
                viewrecords:true,//是否显示数据库的条数
                sortname:"create_date",//指定排序的字段
                caption:"",//指定表格标题
                autowidth:true,//自己适应父容器
                cellEdit:true,//开启单元格的修改操作 配合editurl操作
                editurl:"${pageContext.request.contextPath}/user/edit", //add添加 exit修改 del删除
                hiddengrid:false,
                hidegrid: false,
                multiselect:false,
                rownumbers:true,
                toolbar:["true","bottom"]
            }).navGrid(//开启增删改
                "#aa",
                {add:true,edit:false,del:true,search:true,refresh:true},  //对展示增删改按钮配置
                {
                    closeAfterEdit: true,//关闭面板
                    reloadAfterSubmit: true,
                },//编辑面板的配置
                {   closeAfterAdd: true,
                    reloadAfterSubmit: true,
                    afterSubmit: function (data) {  //添加成功之后执行的内容
                        //1.信息入库   返回id
                        //2.文件上传
                        $.ajaxFileUpload({
                            url: "${path}/user/headUpload",
                            type: "post",
                            data: {"id": data.responseText},
                            fileElementId: "pic_img", //文件选择框的id属性，即<input type="file" id="picImg" name="picImg">的id
                            success: function () {
                                //上传成功 所作的事情
                                //刷新页面
                                $("#list").trigger("reloadGrid");
                            }
                        });
                        return "hello";
                    }
                },//添加面板的配置
                {   closeAfterDelete: true,
                    reloadAfterSubmit: true
                },//删除的配置
                {
                    sopt:['cn','eq'],//用来展示那些搜索的运算符
                    closeAfterSearch: true,
                    reloadAfterSubmit: true
                },
            );
            $("#use").click(function(){
                $.post("${path}/user/excel",function(result){
                    console.log(result);
                },"JSON");
            });
        });
        function u_update(id) {
            $.post("${path}/user/u_update",{"id":id},function(date){
                $("#list").trigger("reloadGrid");
            },"TEXT");
        }
    </script>
</head>
<body>
<div class="panel panel-danger">
    <div class="panel-heading">用户信息</div>
    <div class="panel-body">
        <div>
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">用户管理</a></li>
            </ul>
            <!-- Tab panes -->
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="home">
                    <button type="button" class="btn btn-success" id="use">导出用户信息</button>
                    <button type="button" class="btn btn-info">导入用户</button>
                    <button type="button" class="btn btn-warning">测试按钮</button>
                </div>
            </div>
        </div>
        <table id="list"></table>
        <!--分页工具栏-->
        <div id="aa"></div>
    </div>
</div>
</body>
</html>

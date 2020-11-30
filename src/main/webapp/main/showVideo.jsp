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
                url: "${path}/video/pageAll",  //分页查询   page  rows  total recoreds  page  rows
                editurl: "${path}/video/edit",
                datatype: "json",
                rowNum: 10,  //每页展示是条数
                rowList: [10, 20, 30],
                pager: '#vPager',
                styleUI: "Bootstrap",
                height: "auto",
                autowidth: true,
                viewrecords: true,  //是否展示数据总条数
                colNames: ['ID', '名称','视频封面', '视频', '上传时间', '描述','所属类别', '用户Id', '类别id'],
                colModel: [
                    {name: 'id', width: 55},
                    {name: 'name', editable: true, width: 90},
                    {
                        name: 'cover_path', width: 100,height:400, align: "center",
                        formatter: function (cellvalue, options, rowObject) {
                            return "<img id='media' src='" + cellvalue + "' controls width='100px' heigt='400px' />";
                            //return "<video id='media' src='/upload/video/"+cellvalue+"' controls width='400px' heigt='800px' poster='"+rowObject.cover+"'/>";
                        }
                    },
                    {
                        name: 'video_path', editable: true, width: 200, height:400, align: "center", edittype: "file",
                        formatter: function (cellvalue, options, rowObject) {
                            console.log(cellvalue);
                            return "<video id='media' src='" + cellvalue + "' controls width='200px' heigt='200px' />";
                            //return "<video id='media' src='/upload/video/"+cellvalue+"' controls width='400px' heigt='800px' poster='"+rowObject.cover+"'/>";
                        }
                    },
                    {name: 'upload_time', width: 80, align: "right"},
                    {name: 'brief', editable: true, width: 100},
                    {name: 'category_id', width: 80, align: "center"},
                    {name: 'user_id', width: 80, align: "right"},
                    {name: 'category_id', width: 150, sortable: false}
                ]
            });

            //处理曾删改查操作   工具栏
            $("#vTable").jqGrid('navGrid', '#vPager',
                {edit: true, add: true, del: true, edittext: "修改", addtext: "添加", deltext: "删除"},
                {
                    closeAfterEdit: true,  //关闭对话框
                    // beforeShowForm: function (obj) {
                    //     obj.find("#videoPath").attr("disabled", true);//禁用input
                    // }
                    afterSubmit: function (data) {
                        $.ajaxFileUpload({
                            fileElementId: "video_path",    //需要上传的文件域的ID，即<input type="file">的ID。
                            url: "${path}/video/uploadVdieo", //后台方法的路径
                            type: 'post',   //当要提交自定义参数时，这个参数要设置成post
                            data: {id: data.responseText},  //responseText: "74141b4c-f337-4ab2-ada2-c1b07364adee"
                            success: function () {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
                                //刷新页面
                                $("#vTable").trigger("reloadGrid");
                            }
                        });
                        //必须要有返回值
                        return "hello";
                    }
                }, //执行修改之后的额外操作
                {
                    closeAfterAdd: true, //关闭添加的对话框
                    afterSubmit: function (data) {
                        $.ajaxFileUpload({
                            fileElementId: "video_path",    //需要上传的文件域的ID，即<input type="file">的ID。
                            url: "${path}/video/uploadVdieo", //后台方法的路径
                            type: 'post',   //当要提交自定义参数时，这个参数要设置成post
                            data: {id: data.responseText},  //responseText: "74141b4c-f337-4ab2-ada2-c1b07364adee"
                            success: function () {   //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
                                //刷新页面
                                $("#vTable").trigger("reloadGrid");
                            }
                        });
                        //必须要有返回值
                        return "hello";
                    }
                }, //执行添加之后的额外操作
                {
                    closeAfterDelete: true,
                    reloadAfterSubmit: true

                } //执行删除之后的额外操作
            );
        });


    </script>
</head>
<body>
<div class="panel panel-warning">

    <%--面板头--%>
    <div class="panel panel-heading">视频管理
    </div>

    <%--选项卡--%>
    <div class="nav nav-tabs">
        <li class="active"><a href="">视频信息</a></li>
    </div>

    <%--警告提示框--%>
    <div id="deleteMsg" class="alert alert-danger" style="height: 50px;width: 250px;display: none" align="center">
        <span id="showMsg"/>
    </div>

    <%--初始化表单--%>
    <table id="vTable"/>

    <%--工具栏--%>
    <div id="vPager"/>

</div>
</body>
</html>

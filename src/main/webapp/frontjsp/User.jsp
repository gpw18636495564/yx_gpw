<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2020/11/21
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>登录/注册页面</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
           $("#code").click(function(){
               var phone=$("#exampleInputEmail1").val();
               console.log(phone);
               $.post("${path}/user/code",{phone:phone},function(date){
               },"JSON");
           }) ;
        });
    </script>
</head>
<body>
<div class="container-fluid row">
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModal">
        注册
    </button>

    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <form role="form" action="${path}/user/insert" method="post" class="login-form" id="loginForm" enctype="multipart/form-data">
                    <span id="msgDiv">${requestScope.message}</span>
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">琪学APP注册:</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="exampleInputEmail1">手机号:</label>
                        <input type="text" class="form-control" id="exampleInputEmail1" name="phone" placeholder="请输入手机号进行注册">
                    </div>
                    <div class="form-group">
                        <label for="nick_name">昵称:</label>
                        <input type="text" class="form-control" id="nick_name" name="nick_name" placeholder="请输入昵称">
                    </div>
                    <div class="form-group">
                        <label for="code1">验证码:</label>
                        <input type="text" class="form-control" id="code1" name="code" placeholder="请输入收到的验证码">
                        <button type="button"  class="btn btn-info btn-sm" id="code">点击发送验证码</button>
                    </div>
                    <div class="form-group">
                        <label for="exampleInputFile"></label>
                        <input type="file" id="exampleInputFile" name="file"><p class="help-block">请选择自己喜欢的头像进行注册</p>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success">注册并登录</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">取消退出</button>
                </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>

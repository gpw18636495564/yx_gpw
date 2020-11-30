<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

</head>
<body>
<!--顶部导航-->
<nav class="navbar navbar-default col-sm-12">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><span class="glyphicon glyphicon-education"></span> 琪学视频App后天管理系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎:<span style="color: blue">${sessionScope.login.name}</span></a></li>
                <li><a href="${path}/admain/exit">退出  <span class="glyphicon glyphicon-log-in"></span> </a> </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
    <!--栅格系统-->
<div class="container-fluid row">
        <!--左边手风琴部分-->
        <div class="panel-group col-sm-2" id="accordion" role="tablist" aria-multiselectable="true">
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingOne">
                    <h4 class="panel-title">
                            <button type="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" class="btn btn-danger btn-block active "><span class="glyphicon glyphicon-user"></span>  用户管理</button>
                    </h4>
                </div>
                <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                    <div class="panel-body">
                        <a type="button" class="btn btn-danger center-block" href="javascript:$('#mainId').load('${path}/main/queryByUser.jsp')">用户展示</a>
                    </div>
                    <div class="panel-body">
                        <a type="button" class="btn btn-danger center-block" href="javascript:$('#mainId').load('${path}/xiaogongneng/Echarts.jsp')">用户统计</a>
                    </div>
                    <div class="panel-body">
                        <a type="button" class="btn btn-danger center-block" href="javascript:$('#mainId').load('${path}/xiaogongneng/chain.jsp')">用户分布</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingTwo">
                    <h4 class="panel-title">
                            <button type="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" class="btn btn-success btn-block active "><span class="glyphicon glyphicon-credit-card"></span>  分类管理</button>
                    </h4>
                </div>
                <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                    <div class="panel-body">
                        <a type="button" class="btn btn-success center-block" href="javascript:$('#mainId').load('${path}/main/showCategory.jsp')">分类展示</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingThree">
                    <h4 class="panel-title">
                        <button type="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree" class="btn btn-warning btn-block active "><span class="glyphicon glyphicon-film"></span>   视频管理</button>
                    </h4>
                </div>
                <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                    <div class="panel-body">
                        <a type="button" class="btn btn-warning center-block" href="javascript:$('#mainId').load('${path}/main/showVideo.jsp')">视频展示</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingfore">
                    <h4 class="panel-title">
                        <button type="button" data-toggle="collapse" data-parent="#accordion" href="#collapsefore" aria-expanded="false" aria-controls="collapsefore" class="btn btn-info btn-block active "><span class="glyphicon glyphicon-tasks"></span>  日志管理</button>
                    </h4>
                </div>
                <div id="collapsefore" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingfore">
                    <div class="panel-body">
                        <a type="button" class="btn btn-info center-block" href="javascript:$('#mainId').load('${path}/main/Log.jsp')">日志展示</a>
                    </div>
                </div>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="fivefore">
                    <h4 class="panel-title">
                        <button type="button" data-toggle="collapse" data-parent="#accordion" href="#collapsefive" aria-expanded="false" aria-controls="collapsefive" class="btn btn-primary btn-block active "><span class="glyphicon glyphicon-tasks"></span>  反馈管理</button>
                    </h4>
                </div>
                <div id="collapsefive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingfive">
                    <div class="panel-body">
                        <a type="button" class="btn btn-primary center-block" href="javascript:$('#mainId').load('${path}/main/Feed.jsp')">反馈信息</a>
                    </div>
                </div>
            </div>
        </div>
        <!--巨幕开始-->
    <div class="col-sm-10" id="mainId">
    <div class="jumbotron col-sm-12">
        <h1>欢迎来到琪学视频App后台管理系统</h1>
    </div>
        <!--右边轮播图部分-->
    <div id="carousel-example-generic" class="carousel slide col-sm-12 center-block" data-interval="2000">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
            <li data-target="#carousel-example-generic" data-slide-to="1"></li>
            <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            <li data-target="#carousel-example-generic" data-slide-to="3"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner center-block"role="listbox" style="height: 350px">
            <div class="item active">
                <img src="${path}/bootstrap/img/pic1.jpg" width="100%" alt="...">
                <div class="carousel-caption">
                </div>
            </div>
            <div class="item">
                <img src="${path}/bootstrap/img/pic2.jpg"width="100%" alt="...">
                <div class="carousel-caption">
                </div>
            </div>
            <div class="item">
                <img src="${path}/bootstrap/img/pic3.jpg"width="100%" alt="...">
                <div class="carousel-caption">
                </div>
            </div>
            <div class="item">
                <img src="${path}/bootstrap/img/pic4.jpg"width="100%" alt="...">
                <div class="carousel-caption">
                </div>
            </div>
        </div>

        <!-- Controls -->
        <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
    </div>
</div>
    <!--栅格系统-->
<hr/>
<!--页脚-->
<div class="panel-footer col-sm-12 text-center">
    @百知教育 GPW@186364955654.com
</div>
</body>
</html>

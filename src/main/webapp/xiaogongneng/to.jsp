<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>琪学聊天群</title>
        <!--[if lte IE 8]>//如果需要支持低版本的IE8及以下
        <script  type="text/javascript" src="https://cdn.goeasy.io/json2.js"></script>
        <![endif]-->
        <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.1.1.js"></script>
        <script src="${path}/bootstrap/js/jquery.min.js"></script>
        <script>

            var goEasy = new GoEasy({
                host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
                appkey: "BC-449e58270cd84abb855e9ccce2ac322e", //替换为您的应用appkey
            });



            var contentMsg;

            /*接收消息*/
            goEasy.subscribe({
                channel: "qi_xue", //替换为您自己的channel
                onMessage: function (message) {

                    //获取内容
                    var message=message.content;

                    //判断消息是否时自己发送的
                    //自己发送的  不展示
                    //别人发送的消息  展示
                    if(message!=contentMsg){
                        //渲染页面
                        //设置样式
                        var divStyle="" +
                            "<div style='width: auto;height: 30px' >" +
                            "<div style='float: left;background-color: #bbddff;border-radius:100px'>" +
                            ""+message+"" +
                            "</div>" +
                            "</div>";

                        //渲染页面
                        $("#showMsg").append(divStyle);
                    }
                }
            });


            $(function(){

                //点击发送按钮执行
                $("#sendMsg").click(function(){

                    //获取输入框的内容
                    var content= $("#msg").val();

                    //赋值
                    contentMsg=content;

                    //清空输入框
                    $("#msg").val("");

                    //发送消息
                    goEasy.publish({
                        channel: "qi_xue", //替换为您自己的channel
                        message: content //替换为您想要发送的消息内容
                    });

                    //设置样式
                    var divStyle="" +
                        "<div style='width: auto;height: 30px' >" +
                        "<div style='float: right;background-color: #ccaadd;border-radius:100px'>" +
                        ""+content+"" +
                        "</div>" +
                        "</div>";

                    //渲染页面
                    $("#showMsg").append(divStyle);

                });
            });


        </script>
    </head>
    <body>

        <div align="center">

            <h1>琪学聊天群</h1>
            <%--大框--%>
            <div style="width: 600px;height: 800px;border: 3px #1c94c4 solid">
                <%--内容展示区域--%>
                <div id="showMsg" style="width: 594px;height: 700px;border: 3px #5cb85c solid"></div>
                <%--内容编辑区域--%>
                <div style="width: 594px;height: 88px;border: 3px #f9dd34 solid">
                    <%--输入框--%>
                    <input id="msg" type="text" style="width: 450px;height: 82px;border: 3px #ccaadd solid" />
                    <%--发送按钮--%>
                    <input id="sendMsg" type="button" value="发送"  style="width: 94px;height: 88px;border: 3px #93c3cd solid"  >
                </div>
            </div>
        </div>

    </body>
</html>
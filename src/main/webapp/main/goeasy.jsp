<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2020/11/28
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>琪学聊天室</title>
    <!--[if lte IE 8]>//如果需要支持低版本的IE8及以下
    <script  type="text/javascript" src="https://cdn.goeasy.io/json2.js"></script>
    <![endif]-->
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.1.1.js"></script>
    <script>
        var goEasy = new GoEasy({
            host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-449e58270cd84abb855e9ccce2ac322e", //替换为您的应用appkey
        });
        //GoEasy-OTP可以对appkey进行有效保护,详情请参考
        //接收消息​ ​
        goEasy.subscribe({
            channel: "qi_xue", //替换为您自己的channel
            onMessage: function (message) {
                console.log("Channel:" + message.channel + " content:" + message.content);
            }
        });
        //发送消息
        goEasy.publish({
            channel: "my_channel", //替换为您自己的channel
            message: "Hello, GoEasy!" //替换为您想要发送的消息内容
        });
        //取消消息接收
        goEasy.unsubscribe ({
            channel: "my_channel" //替换为您自己的channel
        });

    </script>
</head>
<body>
计划书卡活动时间
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2020/11/26
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
<%--引入ECharts--%>
    <script src="${path}/bootstrap/js/echarts.js"></script>
    <script src="${path}/bootstrap/js/china.js"></script>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <!--[if lte IE 8]>//如果需要支持低版本的IE8及以下
    <script  type="text/javascript" src="https://cdn.goeasy.io/json2.js"></script>
    <![endif]-->
    <script type="text/javascript" src="https://cdn.goeasy.io/goeasy-1.1.1.js"></script>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        $(function () {
        var myChart = echarts.init(document.getElementById('main'));
        $.get('${path}/user/queryAll',function(data){
            // 指定图表的配置项和数据
            var option = {
                //标题
                title: {
                    text: '琪学每月用户统计表'
                },
                tooltip: {},  //鼠标提示
                legend: {     //选项卡
                    data:['男','女']
                },
                xAxis: {   //横坐标
                    // data: ["一月份","二月份","三月份","四月份","五月份","六月份","七月份","八月份","九月份","十月份","十一月份","十二月份"]
                data: data.months,
                },
                yAxis: {},   //纵坐标
                series: [{   //数据系列
                    name: '男',   //选项卡名字
                    type: 'bar',  //柱状图
                    data: data.nan,
                },{   //数据系列
                    name: '女',
                    type: 'line',  //折线图 line
                    data: data.nv,
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        });

        });
    </script>
    <script type="text/javascript">
        /*初始化GoEasy对象*/
        var goEasy = new GoEasy({
            host:'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
            appkey: "BC-449e58270cd84abb855e9ccce2ac322e", //替换为您的应用appkey
        });

        $(function(){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            /*接收消息*/
            goEasy.subscribe({
                channel: "qi_xue", //替换为您自己的channel
                onMessage: function (message) {

                    var datas=message.content;

                    //将json字符串转为javascript对象
                    var data=JSON.parse(datas);

                    // 指定图表的配置项和数据
                    var option = {
                        //标题
                        title: {
                            text: '琪学每月用户统计表'
                        },
                        tooltip: {},  //鼠标提示
                        legend: {     //选项卡
                            data:['男','女']
                        },
                        xAxis: {   //横坐标
                            // data: ["一月份","二月份","三月份","四月份","五月份","六月份","七月份","八月份","九月份","十月份","十一月份","十二月份"]
                            data: data.months,
                        },
                        yAxis: {},   //纵坐标
                        series: [{   //数据系列
                            name: '男',   //选项卡名字
                            type: 'bar',  //柱状图
                            data: data.nan,
                        },{   //数据系列
                            name: '女',
                            type: 'line',  //折线图 line
                            data: data.nv,
                        }]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }
            });
        });
    </script>
</head>
<body>
<div id="main" style="width: 100%;height: 600px;"></div>
</body>
</html>

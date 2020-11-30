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
    <script type="text/javascript">


        $(function(){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));


            $.get("${path}/user/city",function(datas){
                console.log(datas)
                var series=[];

                for(var i=0;i<datas.length;i++){

                    var data=datas[i];

                    series.push(
                        {
                            name: data.sex,
                            type: 'map',
                            mapType: 'china',
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data: data.citys
                        }
                    )
                }

                // 指定图表的配置项和数据
                var option = {
                    title : {
                        text: '琪学用户注册分布图',
                        subtext: '❤❤❤',
                        left: 'center'
                    },
                    tooltip : {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data:['男','女']
                    },
                    visualMap: {
                        min: 0,
                        max: 50,
                        left: 'left',
                        top: 'bottom',
                        text:['高','低'],           // 文本，默认为数值文本
                        calculable : true
                    },
                    toolbox: {
                        show: true,
                        orient : 'vertical',
                        left: 'right',
                        top: 'center',
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    series : series
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            },"JSON");

        });
    </script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 100%;height:600px;"></div>

</body>
</html>

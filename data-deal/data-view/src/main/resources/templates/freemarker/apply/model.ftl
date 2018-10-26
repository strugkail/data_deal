<#include "/freemarker/base.ftl">
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>应聘数据</title>
    <style>
        div{
            width: 830px;
            height:458px;
            float: left;
            border: white 1px;
            background-color: #f5f5f5
        }
    </style>
    <script>
        $(function () {
            initFeedbackEcharts();
            initTypeEcharts();
            initAddressEcharts();
            initCompanySizeEcharts();
        });
        function initCompanySizeEcharts(){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('size'));
            var yData = ${y_data_size};
            // 时间轴
            var xData = ${x_data_size};
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '投递数量'
                },
                tooltip: {},
                legend: {
                    data:['投递量']
                },
                xAxis: {
                    data: xData
                },
                yAxis: {},
                series: [{
                    name: '投递量',
                    type: 'bar',
                    data: yData
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
        function initAddressEcharts(){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('address'));
            var yData = ${y_data_address};
            // 时间轴
            var xData = ${x_data_address};
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '投递数量'
                },
                tooltip: {},
                legend: {
                    data:['投递量']
                },
                xAxis: {
                    data: xData
                },
                yAxis: {},
                series: [{
                    name: '投递量',
                    type: 'bar',
                    data: yData
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
        function initTypeEcharts(){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('Type'));
            var yData = ${y_data};
            // 时间轴
            var xData = ${x_data_1};
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '投递数量'
                },
                tooltip: {},
                legend: {
                    data:['投递量']
                },
                xAxis: {
                    data: xData
                },
                yAxis: {},
                series: [{
                    name: '投递量',
                    type: 'bar',
                    data: yData
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
        function initFeedbackEcharts(){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('Feedback'));
            var yData1 = ${y_data_1};
            var yData2 = ${y_data_2};
            var yData3 = ${y_data_3};
            var yData4 = ${y_data_4};
            // 时间轴
            var xData = ${x_data_2};
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '反馈数量'
                },
                tooltip: {},
                legend: {
                    data:['已到达','已查阅','已查收','感兴趣']
                },
                xAxis: {
                    data: xData
                },
                yAxis: {},
                series: [{
                    name: '已到达',
                    type: 'bar',
                    data: yData1
                },{
                    name: '已查阅',
                    type: 'bar',
                    data: yData2
                }, {
                    name: '已查收',
                    type: 'bar',
                    data: yData3
                    },{
                    name: '感兴趣',
                    type: 'bar',
                    data: yData4
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    </script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="Feedback"></div>
<div id="Type"></div>
<div id="address"></div>
<div id="size"></div>
</body>
</html>
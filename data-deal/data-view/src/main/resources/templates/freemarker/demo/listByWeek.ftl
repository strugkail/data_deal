<#include "/freemarker/base.ftl">
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>study freemarker</title>
    <script>
        $(function () {
            initEcharts();
        });
        function initEcharts(){
            var arrays = new Array();
            $("input").each(function () {
                arrays.push($(this).val());
            });
            var data = arrays;
            initLeft(data);
            initRight(data);
            initBottomLeft(data);
            initBottomRight(data);
        }
        function initLeft(data){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('left'));
            // 指定图表的配置项和数据
            var option = {
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: data,
                    type: 'line',
                    areaStyle: {}
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
        function initRight(data){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('right'));
            // 指定图表的配置项和数据
            option = {
                xAxis: {
                    type: 'category',
                    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: data,
                    type: 'line',
                    smooth: true
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
        function initBottomLeft(data){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('bottom-left'));
            // 指定图表的配置项和数据
            option = {
                xAxis: {
                    type: 'category',
                    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    data: data,
                    type: 'line'
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }

        function initBottomRight(data){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('bottom-right'));
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: '产品销量'
                },
                tooltip: {},
                legend: {
                    data:['销量']
                },
                xAxis: {
                    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: data
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
    </script>
</head>
<body>
<div class="container">
    <h2>${msg}</h2>
</div>
<div ng-app="myApp" ng-controller="myCtrl">
    <p>周一 : <input type="text" ng-model="C_name" value="{{C_name}}" oninput="initEcharts()" ></p>
    <p>周二 : <input type="text" ng-model="Y_name" value="{{Y_name}}" oninput="initEcharts()" ></p>
    <p>周三 : <input type="text" ng-model="X_name" value="{{X_name}}" oninput="initEcharts()" ></p>
    <p>周四 : <input type="text" ng-model="K_name" value="{{K_name}}" oninput="initEcharts()" ></p>
    <p>周五 : <input type="text" ng-model="G_name" value="{{G_name}}" oninput="initEcharts()" ></p>
    <p>周六 : <input type="text" ng-model="W_name" value="{{W_name}}" oninput="initEcharts()" ></p>
    <p>周日 : <input type="text" ng-model="D_name" value="{{D_name}}" oninput="initEcharts()" ></p>
</div>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="left" style="width: 300px;height:200px;float: left"></div>
<div id="right" style="width: 300px;height:200px;float: left"></div>
<div id="bottom-left" style="width: 300px;height:200px;float: left"></div>
<div id="bottom-right" style="width: 300px;height:200px;float: left"></div>
<script>
    var app = angular.module('myApp', []);
    app.controller('myCtrl', function($scope) {
        $scope.C_name = "12";
        $scope.Y_name = "13";
        $scope.X_name = "23";
        $scope.K_name = "33";
        $scope.G_name = "11";
        $scope.W_name = "12";
        $scope.D_name = "28";
    });
</script>
</body>
</html>
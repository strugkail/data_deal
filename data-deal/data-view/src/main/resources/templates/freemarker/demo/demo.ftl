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
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));
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
                    data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
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
    <p>衬衫 : <input type="text" ng-model="C_name" value="{{C_name}}" oninput="initEcharts()" ></p>
    <p>羊毛衫 : <input type="text" ng-model="Y_name" value="{{Y_name}}" oninput="initEcharts()" ></p>
    <p>雪纺衫 : <input type="text" ng-model="X_name" value="{{X_name}}" oninput="initEcharts()" ></p>
    <p>裤子 : <input type="text" ng-model="K_name" value="{{K_name}}" oninput="initEcharts()" ></p>
    <p>高跟鞋 : <input type="text" ng-model="G_name" value="{{G_name}}" oninput="initEcharts()" ></p>
    <p>袜子 : <input type="text" ng-model="W_name" value="{{W_name}}" oninput="initEcharts()" ></p>
</div>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script>
    var app = angular.module('myApp', []);
    app.controller('myCtrl', function($scope) {
        $scope.C_name = "12";
        $scope.Y_name = "13";
        $scope.X_name = "23";
        $scope.K_name = "33";
        $scope.G_name = "11";
        $scope.W_name = "12";
    });
</script>
</body>
</html>
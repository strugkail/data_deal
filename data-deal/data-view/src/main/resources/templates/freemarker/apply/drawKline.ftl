<#include "/freemarker/base.ftl">
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>draw K Line</title>
    <style>
    </style>
    <script>
        $(function () { init();});
    </script>
</head>
<body>
<div class="form-group" style="margin-top: 88px;margin-bottom:200px;text-align: center;">
    <div class="row">
        <select id="year" class="form-control" style="width: 200px;margin-left: 35%;">
            <option value="">--请选择年份--</option>
            <option>2015</option>
            <option>2016</option>
            <option>2017</option>
            <option>2018</option>
            <option>2019</option>
        </select>&nbsp;&nbsp;&nbsp;&nbsp;
        <#--<input id="year" onfocus="WdatePicker({dateFmt:'yyyy'})" class="Wdate form-control" style="margin-left:35%;width:200px"/>&nbsp;&nbsp;&nbsp;-->
        <select id="code" class="form-control" style="width: 200px">
            <option value="">--请选择股票代码--</option>
            <option>1399001</option>
            <option>1399002</option>
            <option>1399003</option>
            <option>1399004</option>
            <option>1399005</option>
        </select>&nbsp;&nbsp;&nbsp;&nbsp;
        <input onclick="init()" class="btn btn-primary" type="button" value="查询"/>
    </div>
</div>
<div id="main" style="height: 800px;width: 100%;"></div>
</body>
<script>
    var upColor = '#ec0000';
    var upBorderColor = '#8A0000';
    var downColor = '#00da3c';
    var downBorderColor = '#008F28';

    function init() {
        var url = "/draw/getCodeData";
        // var year = $("#year").val();
        var code = $("#code").val();
        var scale = 60;
        var datalen = 1023;
        // year = year==""?"2018":year;
        code = code==""?"sz002095":code;
        $.ajax({
            type: "post",
            data: {
                // year: year,
                scale : scale,
                datalen : datalen,
                code: code
            },
            async: true,
            url: url,
            beforeSend: function (XMLHttpRequest) {
//ShowLoading();
            },
            success: function (data, textStatus) {
                data0 = "";
                data0 = splitData(data.data);
                initOption();
                var myChart = echarts.init(document.getElementById('main'));
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            },
            complete: function (XMLHttpRequest, textStatus) {
//HideLoading();
            },
            error: function () {
//请求出错处理
            }
        });
    }

    // 数据意义：开盘(open)，收盘(close)，最低(lowest)，最高(highest)
    var data0;

    function splitData(rawData) {
        var categoryData = [];
        var values = []
        for (var i = 0; i < rawData.length; i++) {
            categoryData.push(rawData[i].splice(0, 1)[0]);
            values.push(rawData[i])
        }
        return {
            categoryData: categoryData,
            values: values
        };
    }

    function calculateMA(dayCount) {
        var result = [];
        for (var i = 0, len = data0.values.length; i < len; i++) {
            if (i < dayCount) {
                result.push('-');
                continue;
            }
            var sum = 0;
            for (var j = 0; j < dayCount; j++) {
                sum += parseInt(data0.values[i - j][1]);
            }
            result.push(sum / dayCount);
        }
        return result;
    }
    var option;
function initOption(){
    option = {
        title: {
            text: '上证指数',
            left: 0
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        legend: {
            data: ['日K', 'MA5', 'MA10', 'MA20', 'MA30']
        },
        grid: {
            left: '10%',
            right: '10%',
            bottom: '15%'
        },
        xAxis: {
            type: 'category',
            data: data0.categoryData,
            scale: true,
            boundaryGap: false,
            axisLine: {onZero: false},
            splitLine: {show: false},
            splitNumber: 20,
            min: 'dataMin',
            max: 'dataMax'
        },
        yAxis: {
            scale: true,
            splitArea: {
                show: true
            }
        },
        dataZoom: [
            {
                type: 'inside',
                start: 50,
                end: 100
            },
            {
                show: true,
                type: 'slider',
                y: '90%',
                start: 50,
                end: 100
            }
        ],
        series: [
            {
                name: '日K',
                type: 'candlestick',
                data: data0.values,
                itemStyle: {
                    normal: {
                        color: upColor,
                        color0: downColor,
                        borderColor: upBorderColor,
                        borderColor0: downBorderColor
                    }
                },
                markPoint: {
                    label: {
                        normal: {
                            formatter: function (param) {
                                return param != null ? Math.round(param.value) : '';
                            }
                        }
                    },
                    data: [
                        {
                            name: 'XX标点',
                            coord: ['2013/5/31', 2300],
                            value: 2300,
                            itemStyle: {
                                normal: {color: 'rgb(41,60,85)'}
                            }
                        },
                        {
                            name: 'highest value',
                            type: 'max',
                            valueDim: 'highest'
                        },
                        {
                            name: 'lowest value',
                            type: 'min',
                            valueDim: 'lowest'
                        },
                        {
                            name: 'average value on close',
                            type: 'average',
                            valueDim: 'close'
                        }
                    ],
                    tooltip: {
                        formatter: function (param) {
                            return param.name + '<br>' + (param.data.coord || '');
                        }
                    }
                },
                markLine: {
                    symbol: ['none', 'none'],
                    data: [
                        [
                            {
                                name: 'from lowest to highest',
                                type: 'min',
                                valueDim: 'lowest',
                                symbol: 'circle',
                                symbolSize: 10,
                                label: {
                                    normal: {show: false},
                                    emphasis: {show: false}
                                }
                            },
                            {
                                type: 'max',
                                valueDim: 'highest',
                                symbol: 'circle',
                                symbolSize: 10,
                                label: {
                                    normal: {show: false},
                                    emphasis: {show: false}
                                }
                            }
                        ],
                        {
                            name: 'min line on close',
                            type: 'min',
                            valueDim: 'close'
                        },
                        {
                            name: 'max line on close',
                            type: 'max',
                            valueDim: 'close'
                        }
                    ]
                }
            },
            {
                name: 'MA5',
                type: 'line',
                data: calculateMA(5),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA10',
                type: 'line',
                data: calculateMA(10),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA20',
                type: 'line',
                data: calculateMA(20),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },
            {
                name: 'MA30',
                type: 'line',
                data: calculateMA(30),
                smooth: true,
                lineStyle: {
                    normal: {opacity: 0.5}
                }
            },

        ]
    };}

</script>
</html>
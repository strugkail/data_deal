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
<div style="height: 100%">
    <div class="form-group" style="margin-top: 88px;margin-bottom:35px;text-align: center;">
        <div class="col-sm-3" style="width: 200px;float: left;margin-left: 15%;">
            <select id="scale" onchange="init()" class="selectpicker show-tick" data-live-search="true">
                <option value="5">--请选择分钟数据--</option>
                <option value="5">5 minutes</option>
                <option value="10">10 minutes</option>
                <option value="30">30 minutes</option>
                <option value="60">60 minutes</option>
            </select>&nbsp;&nbsp;&nbsp;&nbsp;
        </div>
        <div class="col-sm-3" style="width: 240px;float: left;margin-left: 15%;">
            <select id="code" onchange="init()" class="selectpicker show-tick" data-live-search="true">
                <option value="sz002095">--请选择股票--</option>
                <#assign userMap=codeMap/>
                <#assign  keys=userMap?keys/>
                <#list keys as key>
                <option value="${userMap[key]!''}">${key}</option>
                </#list>
            </select>
        </div>
        <div class="col-sm-3" style="width: 240px;float: left;">
        <input onclick="init()" class="btn btn-primary" type="button" value="查询"/>
        </div>
    </div>
    <div id="main" style="height: 80%;width: 100%;"></div>
</div>
</body>
<script>
    var upColor = '#ec0000';
    var upBorderColor = '#8A0000';
    var downColor = '#00da3c';
    var downBorderColor = '#008F28';

    var source_data = new Array();
    function init() {
        var url = "/draw/getCodeData";
        var scale = $("#scale").val();
        var code = $("#code").val();
        var datalen = 1023;
        scale = scale==""?"2018":scale;
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
        var values = [];
        for (var i = 0; i < rawData.length; i++) {
            categoryData.push(rawData[i].splice(0, 1)[0]);
            values.push(rawData[i]);
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
            left: '5%',
            right: '5%',
            bottom: '1%'
        },
        xAxis: {
            type: 'category', //坐标轴类型，类目轴
            data: data0.categoryData,
            scale: true,//只在数字轴中有效
            boundaryGap: false,//刻度作为分割线，标签和数据点会在两个刻度上
            axisLine: {onZero: false},
            splitLine: {show: false},//是否显示坐标轴轴线
            splitNumber: 20,//坐标轴的分割段数，预估值，在类目轴中无效
            min: 'dataMin',//特殊值，数轴上的最小值作为最小刻度
            max: 'dataMax'//特殊值，数轴上的最大值作为最大刻度
        },
        yAxis: {
            scale: true,//坐标刻度不强制包含零刻度
            splitArea: {
                show: true //显示分割区域
            }
        },
        dataZoom: [ //用于区域缩放
            {
                filterMode:'filter',  //当前数据窗口外的数据被过滤掉来达到数据窗口缩放的效果  默认值filter
                type: 'inside', //内置型数据区域缩放组件
                start: 50,  //数据窗口范围的起始百分比
                end: 100    //数据窗口范围的结束百分比
            },
            {
                show: true,
                type: 'slider',//滑动条型数据区域缩放组件
                y: '90%',
                start: 50,
                end: 100
            }
        ],
        series: [ //图表类型
            {
                name: '日K',
                type: 'candlestick',//K线图
                data: data0.values,//y轴对应的数据
                itemStyle: {
                    normal: {
                        color: upColor,
                        color0: downColor,
                        borderColor: upBorderColor,
                        borderColor0: downBorderColor
                    }
                },
                markPoint: { //图表标注
                    label: { //标注的文本
                        normal: { //默认不显示标注
                            show:true,
                            //position:['20%','30%'],
                            formatter: function (param) { //标签内容控制器
                                return param != null ? Math.round(param.value) : '';
                            }
                        }
                    },
                    data: [     //标注的数据数组
                        {
                            name: 'XX标点',
                            coord: ['2018-06-22 10:30:00', 2300],   //指定数据的坐标位置
                            value: 2300,
                            itemStyle: {     //图形样式
                                normal: {color: 'rgb(41,60,85)'}
                            }
                        },
                        {
                            name: 'highest value',
                            type: 'max',    //最大值
                            valueDim: 'highest' //在highest维度上的最大值 最高价
                        },
                        {
                            name: 'lowest value',
                            type: 'min',
                            valueDim: 'lowest'  //最低价
                        },
                        {
                            name: 'average value on close',
                            type: 'average',
                            valueDim: 'close'   //收盘价
                        }
                    ],
                    tooltip: {  //提示框
                        formatter: function (param) {
                            return param.name + '<br>' + (param.data.coord || '');
                        }
                    }
                },
                markLine: {
                    symbol: ['none', 'none'],//标线两端的标记类型
                    data: [
                        [
                            {
                                name: 'from lowest to highest',
                                type: 'min',    //设置该标线为最小值的线
                                valueDim: 'lowest', //指定在哪个维度上的最小值
                                symbol: 'circle',
                                symbolSize: 10, //起点标记的大小
                                label: {    //normal默认，emphasis高亮
                                    normal: {show: false},//不显示标签
                                    emphasis: {show: false} //不显示标签
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
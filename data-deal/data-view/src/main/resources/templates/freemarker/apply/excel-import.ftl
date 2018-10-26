<#include "/freemarker/base.ftl">
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>show time</title>
    <style>
        .modal-header{
            margin-left: 20%;
            margin-right: 20%;
        }
        .modal-body{
            margin-left: 20%;
            margin-right: 20%;
        }
    </style>
</head>
<body>
    <div style="height: 50%">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="false">
                <li class="fa fa-remove" onclick="$('.modal-body').css('display','none');"></li>
            </button>
            <h5 class="modal-title" style="cursor: pointer" onclick="$('.modal-body').css('display','block');">Excel文件上传</h5>
        </div>
        <div class="modal-body">
            <form id="importFile" name="importFile" class="form-horizontal" method="post"
                  enctype="multipart/form-data">
                <div class="box-body" >
                    <div>
                        <label class="control-label">请选择要导入的Excel文件：</label>
                        <input id="excelFile" name="excelFile" class="file-loading" type="file" multiple accept=".xls,.xlsx"> <br>
                    </div>
                </div>
            </form>
        </div>
        <div id="main" style="width: 600px;height:400px;"></div>
    </div>

</body>
<script>
    initUpload("excelFile","/data/upload");
    function initUpload(ctrlName, uploadUrl) {
        var control = $('#' + ctrlName);
        control.fileinput({
            language: 'zh', //设置语言
            uploadUrl: uploadUrl, //上传的地址
            uploadAsync: true, //默认异步上传
            showCaption: true,//是否显示标题
            showUpload: true, //是否显示上传按钮
            browseClass: "btn btn-primary", //按钮样式
            allowedFileExtensions: ["xls", "xlsx"], //接收的文件后缀
            maxFileCount: 10,//最大上传文件数限制
            previewFileIcon: '<i class="glyphicon glyphicon-file"></i>',
            showPreview: true, //是否显示预览
            previewFileIconSettings: {
                'docx': '<i ass="fa fa-file-word-o text-primary"></i>',
                'xlsx': '<i class="fa fa-file-excel-o text-success"></i>',
                'xls': '<i class="fa fa-file-excel-o text-success"></i>',
                'pptx': '<i class="fa fa-file-powerpoint-o text-danger"></i>',
                'jpg': '<i class="fa fa-file-photo-o text-warning"></i>',
                'pdf': '<i class="fa fa-file-archive-o text-muted"></i>',
                'zip': '<i class="fa fa-file-archive-o text-muted"></i>',
            },
            uploadExtraData: function () {
                var extraValue = "test";
                return {"excelType": extraValue};
            }
        });
    }
    $("#excelFile").on("fileuploaded", function (event, data, previewId, index) {
        console.log("2____"+data.response.data);
        $("#show").append("<div>123456qwerty</div>");
    });
    $(function () {
        var myChart = echarts.init(document.getElementById('main'));
        var option = {
            title: {
                text: '深圳月最低生活费组成（单位:元）',
                subtext: 'From ExcelHome',
                sublink: 'http://e.weibo.com/1341556070/AjQH99che'
            },
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                },
                formatter: function (params) {
                    var tar = params[0];
                    return tar.name + '<br/>' + tar.seriesName + ' : ' + tar.value;
                }
            },
            toolbox: {
                show : true,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            xAxis : [
                {
                    type : 'category',
                    splitLine: {show:false},
                    data : ['总费用','房租','水电费','交通费','伙食费','日用品数']
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name:'辅助',
                    type:'bar',
                    stack: '总量',
                    itemStyle:{
                        normal:{
                            barBorderColor:'rgba(0,0,0,0)',
                            color:'rgba(0,0,0,0)'
                        },
                        emphasis:{
                            barBorderColor:'rgba(0,0,0,0)',
                            color:'rgba(0,0,0,0)'
                        }
                    },
                    data:[0, 1700, 1400, 1200, 300, 0]
                },
                {
                    name:'生活费',
                    type:'bar',
                    stack: '总量',
                    itemStyle : { normal: {label : {show: true, position: 'inside'}}},
                    data:[2900, 1200, 300, 200, 900, 300]
                }
            ]
        };
        myChart.setOption(option);
    });
</script>
</html>
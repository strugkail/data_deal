<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <title>study freemarker</title>
    <#include "/freemarker/base.ftl">
    <script>
        $(function(){
            $('#table_div').load('http://localhost:9999/image/table');
        });
        function crawlData(url,content){
            location.href='http://localhost:9999/reptile/start?url='+url+'&content='+content;
        }
        function page(n,s){
            loadPage(n,s,'','');
        }
        function loadPage(n,s,dateStart,dateEnd){
            $('#table_div').html("");
            $('#table_div').load('http://localhost:9999/image/table?pageNo='+n+'&pageSize='
                    +s+'&dateStart='+dateStart+'&dateEnd='+dateEnd);
        }
    </script>

</head>
<body>
    <div class="form-group" style="margin-top: 88px;text-align: center;width: 75%;margin-bottom: 20px;margin-left:350px;" >
        <div class="row">
            <span style="margin-left:270px;float: left">每页显示:</span>
            <input type="text" value="30"  class="form-control" id="pageSize" style="width: 42px;float: left;margin-right:50px;" />
            <input type="text" class="form-control" id="pageUrl" style="width:330px;float:left;margin-right: 35px;"/>
            <input type="button" class="btn btn-primary" onclick="crawlData($('#pageUrl').val(),'')" value="爬取数据" style="float:left;margin-right: 35px;"/>
            <input type="button" class="btn btn-clean" onclick="page(1,$('#pageSize').val())" value="刷新" style="float:left;"/>
        </div>
        <div class="row" style="text-align: center">
            <input id="dateStart"  onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" style="width:200px"/>&nbsp;至&nbsp;
            <input id="dateEnd" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="Wdate" style="width:200px"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <input  onclick="loadPage(1,$('#pageSize').val(),$('#dateStart').val(),$('#dateEnd').val());"
                class="btn btn-primary" type="button" value="查询"/>
        </div>

    </div>
    <div id="table_div" style="margin-top:20px;margin-left:350px;"></div>
</body>
</html>
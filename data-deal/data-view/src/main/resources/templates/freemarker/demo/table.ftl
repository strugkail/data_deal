<#include "/freemarker/base.ftl">
    <style>
        #contentTable tbody td {
            text-align: center;
        }
        #contentTable thead th {
            text-align: center;
        }
    </style>
    <table id="contentTable" style="table-layout:fixed; width: 75%;"
           class="table table-striped table-bordered table-condensed">
        <thead>
        <tr class="text-center">
            <th style="width: 30px">序号</th>
            <th>图片</th>
            <th>图片链接</th>
            <th>创建时间</th>
            <th>创建人</th>
            <th style="width: 155px">操作</th>
        </tr>
        </thead>
        <tbody>
            <#list imageList as image>
            <tr class="text-center">
                <td style="color: #0B61A4;font-size: 20px;" class="text-center">${image_index+1}</td>
                <#--<td><img src="data:image/png;base64,${image.base64str}" width="150px" height="135px;" /></td>-->
                <td ><img src="${image.imgurl}" width="150px" height="135px;" /></td>
                <td><a href="${image.imgurl}" target="_blank" style="word-wrap:break-word;">${image.imgurl}</a></td>
                <td>${image.createTime?string("yyyy-MM-dd HH:mm:ss zzzz")}</td>
                <td>${image.createName}</td>
                <td><input type="button" value="删除" class="btn btn-danger"></td>
            </tr>
            </#list>
        </tbody>
    </table>
    <div class="pagination" style="margin-left:300px;">${page}</div>
<%--
  Created by IntelliJ IDEA.
  User: Lolita
  Date: 18/11/27
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $('#tree').treegrid({
        url: '${pageContext.request.contextPath}/selectAlbums',
        idField: 'id',
        treeField: 'title',
        columns: [[
            {field: 'title', title: '标题', width: 100},
            {field: 'size', title: '大小', width: 100},
            {field: 'duration', title: '描述', width: 100},
            {field: 'downPath', title: '下载路径', width: 100},
            {field: 'uploadDate', title: '上传时间', width: 100}
        ]],
        fitColumns: true,
        fit: true,
        pageSize: 5,
        onDblClickCell: function (index, row) {
            if (row != null && row.downPath != null) {
                $('#Audio-dialog').dialog("open");
                //  alert("${pageContext.request.contextPath}/upload/" + row.downPath)
                $("#audio").prop("src", "${pageContext.request.contextPath}/upload/" + row.downPath)
            } else {
                $.messager.alert('警告', '请选中要播放的章节');
            }
        },
        pageList: [5, 10, 15, 20, 25],
        pagination: true, toolbar: [{
            iconCls: 'icon-tip',
            text: '专辑详情',
            handler: function () {
                var row = $('#tree').treegrid("getSelected")
                if (row != null && row.score != null) {
                    $('#Album-dialog').dialog("open");
                    $('#album').form('load', row);
                } else {
                    $.messager.alert('警告', '请选中要查看的专辑');
                }
            }
        }, '-', {
            iconCls: 'icon-add',
            text: '添加专辑',
            handler: function () {
            }
        }, '-', {
            iconCls: 'icon-add',
            text: '添加章节',
            handler: function () {
                var row = $('#tree').treegrid("getSelected")
                alert(row.score)
                if (row != null && row.score != null) {
                    $('#addChapter').dialog("open")
                    $("#pid").val(row.id)
                } else {
                    $.messager.alert('警告', '请选中要添加章节的专辑');
                }
            }
        }, '-', {
            iconCls: 'icon-undo',
            text: '下载',
            handler: function () {
                var row = $('#tree').treegrid("getSelected")
                if (row != null && row.downPath != null) {
                    window.location.href = "${pageContext.request.contextPath}/download?title=" + row.title + "&filePath=" + row.downPath;
                } else {
                    $.messager.alert('警告', '请选中要下载的章节');
                }
            }
        }],
    });

    //form提交表单
    function addChapter() {
        $('#addChapters').form('submit', {
            url: "${pageContext.request.contextPath}/addChapter",
            onSubmit: function () {
                var isOK = $(this).form("validate");
                //  alert(isOK);
                return isOK;
            },
            success: function (data) {
                if (data == "true") {
                    $("#addChapter").dialog("close");
                    $('#tree').datagrid("reload");
                } else {
                    $.messager.alert('提示', '添加失败！', 'warning');
                }

            }
        });
    }
</script>


<table id="tree"></table>

<div id="addChapter" class="easyui-dialog" data-options="closed:true">
    <form id="addChapters" style="margin:30px" enctype="multipart/form-data" method="post">
        <table>
            <tr>
                <td> 标 题：</td>
                <td>
                    <input class="easyui-validatebox" name="title"
                           data-options="required:true,missingMessage:'请填写标题'"/>
                </td>
            </tr>
            <tr>
                <td> 音 频：</td>
                <td><input class="easyui-filebox" name="file"
                           data-options="required:true,missingMessage:'请选择一个音频'"/></td>
            </tr>
        </table>
        <center>
            <input type="hidden" id="pid" name="pid">
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onClick="addChapter()">确认</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
        </center>
    </form>
</div>
<div id="Album-dialog" class="easyui-dialog" data-options="closed:true">
    <form id="album" style="margin:30px" enctype="multipart/form-data" method="post">
        <table>
            <tr>
                <td> 标 题：</td>
                <td>
                    <input class="easyui-validatebox" name="title"/>
                </td>
            </tr>
            <tr>
                <td> 作 者：</td>
                <td>
                    <input class="easyui-validatebox" name="author"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="Audio-dialog" class="easyui-dialog" data-options="closed:true">
    <iframe frameborder="no" border="0" marginwidth="0" marginheight="0" width=330 height=86 id="audio"
            src=""></iframe>
</div>
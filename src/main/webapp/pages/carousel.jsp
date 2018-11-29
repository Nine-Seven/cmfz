<%--
  Created by IntelliJ IDEA.
  User: Lolita
  Date: 18/11/27
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {

        //table标签
        $('#dg').edatagrid({
            fitColumns: true,
            updateUrl: '${pageContext.request.contextPath}/updateCarousel',
            url: '${pageContext.request.contextPath}/selectCarousels',
            columns: [[
                {field: 'title', title: '标题', width: 100},
                {field: 'desc', title: '简述', width: 100},
                {
                    field: 'status', title: '是否展示', width: 100, editor: {
                        type: 'text',
                        options: {required: true}
                    }
                },
                {field: 'date', title: '添加时间', width: 100}
            ]],
            pageSize: 5,
            pageList: [5, 10, 15, 20, 25],
            pagination: true,
            fit: true,
            iconCls: 'icon-search',
            toolbar: [{
                iconCls: 'icon-add',
                text: '添加',
                handler: function () {
                    $("#addProdia").dialog("open");
                }
            }, '-', {
                iconCls: 'icon-save',
                text: '保存',
                handler: function () {
                    $('#dg').edatagrid("saveRow")
                }
            }],
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="${pageContext.request.contextPath}/img/' + rowData.imgPath + '" style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>标题: ' + rowData.title + '</p>' +
                    '<p>简述: ' + rowData.desc + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }

        });

    })

    //form提交表单
    function doAdd() {
        $('#addForm').form('submit', {
            url: "${pageContext.request.contextPath}/addCarousel",
            onSubmit: function () {
                var isOK = $(this).form("validate");
                //  alert(isOK);
                return isOK;
            },
            success: function (data) {
                if (data == "true") {
                    $("#addProdia").dialog("close");
                    $('#dg').datagrid("reload");
                } else {
                    $.messager.alert('提示', '添加失败！', 'warning');
                }

            }
        });
    }
</script>

<table id="dg"></table>

<div id="addProdia" class="easyui-dialog" data-options="closed:true">
    <form id="addForm" style="margin:30px" enctype="multipart/form-data" method="post">
        <table>
            <tr>
                <td> 标 题：</td>
                <td>
                    <input class="easyui-validatebox" name="title"
                           data-options="required:true,missingMessage:'请填写标题'"/>
                </td>
            </tr>
            <tr>
                <td>轮播图：</td>
                <td><input class="easyui-filebox" name="pic"
                           data-options="required:true,missingMessage:'请选择一张图片'"/></td>

            </tr>
            <tr>
                <td>简 述：</td>
                <td><input class="easyui-validatebox" name="desc"
                           data-options="required:true,missingMessage:'请填写简述'"/></td>

            </tr>
            <tr>
                <td> 是否展示：</td>
                <td><input class="easyui-validatebox" name="status"
                           data-options="required:true,missingMessage:'请填写是否展示'"/></td>

            </tr>
        </table>
        <center>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" onClick="doAdd()">确认</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
        </center>
    </form>
</div>
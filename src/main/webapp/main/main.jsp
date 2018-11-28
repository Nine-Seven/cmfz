<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/themes/icon.css"/>
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.edatagrid.js"></script>
    <script type="text/javascript">
        function openPage(url, title) {
            var isExists = $("#tt").tabs("exists", title);
            //alert(isExists)
            if (isExists) {
                //存在
                $("#tt").tabs("select", title);
            } else {
                $("#tt").tabs("add", {
                    title: title,
                    closable: true,
                    selected: true,
                    iconCls: "icon-edit",
                    //content: '<iframe src="${pageContext.request.contextPath}/pages/' + url + '" width="100%" height="100%"></iframe>'
                    href: "${pageContext.request.contextPath}/pages/" + url
                });
            }
        }

        <!--菜单处理-->
        $.ajax({
            url: "${pageContext.request.contextPath}/menu",
            type: "post",
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    var str = "";
                    for (var j = 0; j < data[i].menus.length; j++) {
                        str += "<p style='text-align: center'><a  href='javascript:void(0)' onclick='openPage(\"" + data[i].menus[j].url + "\",\"" +
                            data[i].menus[j].title + "\")'>" + data[i].menus[j].title + "</a><p>";
                    }
                    // alert(str)
                    $('#aa').accordion('add', {
                        title: data[i].title,
                        content: str,
                        iconCls: "icon-cut",
                        selected: false
                    });
                    // str="";
                }

            }
        })


    </script>

</head>

<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">
        欢迎您:${sessionScope.login.name}
        &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a
            href="${pageContext.request.contextPath}/loginOut"
            class="easyui-linkbutton"
            data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体">&copy;百知教育 htf@zparkhr.com.cn</div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="aa" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tt" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url(image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;"></div>
    </div>
</div>
</body>
</html>
<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>持名法州后台管理中心</title>

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="this is my page">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="css/common.css" type="text/css"></link>
    <link rel="stylesheet" href="css/login.css" type="text/css"></link>
    <script type="text/javascript" src="script/jquery.js"></script>
    <script type="text/javascript" src="script/common.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
    <link type="text/css" rel="stylesheet" href="themes/icon.css"/>
    <link type="text/css" rel="stylesheet" href="themes/default/easyui.css"/>
    <script type="text/javascript">

        $(function () {

            $(".easyui-validatebox").validatebox("disableValidation");
            $(".easyui-validatebox").validatebox("enableValidation");

            //点击更换验证码：
            $("#captchaImage").click(function () {//点击更换验证码
                document.getElementById('captchaImage').src = '${pageContext.request.contextPath}/code?time=' + (new Date()).getTime();
            });
        });

        function doLogin() {
            $('#loginForm').form('submit', {
                url: "login",
                onSubmit: function () {
                    var isOK = $(this).form("validate");
                    //  alert(isOK);
                    return isOK;
                },
                success: function (data) {
                    if (data == "true") {
                        location.href = "main/main.jsp"
                    } else {
                        $.messager.alert('提示', '账号或密码错误！', 'warning');
                    }

                }
            });
        }

    </script>
</head>
<body>

<div class="login">
    <form id="loginForm" action="" method="post">

        <table>
            <tbody>
            <tr>
                <td width="190" rowspan="2" align="center" valign="bottom">
                    <img src="img/header_logo.gif"/>
                </td>
                <th>
                    用户名:
                </th>
                <td>
                    <input type="text" name="name" class="easyui-validatebox" value="xxx" maxlength="20"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <th>
                    密&nbsp;&nbsp;&nbsp;码:
                </th>
                <td>
                    <input type="password" name="password" class="easyui-validatebox" value="xxx" maxlength="20"
                           autocomplete="off"
                           class="easyui-validatebox"
                           data-options="required:true"/>
                </td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <th>验证码:</th>
                <td>
                    <input type="text" id="enCode" name="code" class="text captcha" maxlength="4" autocomplete="off"/>
                    <img id="captchaImage" class="captchaImage" src="${pageContext.request.contextPath}/code"
                         title="点击更换验证码"/>
                </td>
            </tr>
            <tr>
                <td>
                    &nbsp;
                </td>
                <th>
                    &nbsp;
                </th>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <th>&nbsp;</th>
                <td>
                    <input type="button" class="homeButton" value="" onclick=""><input type="button"
                                                                                       class="loginButton"
                                                                                       value="登录"
                                                                                       onclick="doLogin()">
                </td>
            </tr>
            </tbody>
        </table>
        <div class="powered">COPYRIGHT © 2008-2017.</div>
        <div class="link">
            <a href="http://www.chimingfowang.com/">持名佛网首页</a> |
            <a href="http://www.chimingbbs.com/">交流论坛</a> |
            <a href="">关于我们</a> |
            <a href="">联系我们</a> |
            <a href="">授权查询</a>
        </div>
    </form>
</div>
</body>
</html>
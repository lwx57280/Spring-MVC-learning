<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>servlet async support</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
</head>
<body>
    <script src="../assets/js/jquery-1.9.1.js" type="text/javascript"></script>
    <script type="text/javascript">
        deferred();     //1、页面打开向后台发送请求
        function deferred() {
            $.get('defer',function (data) {
                console.log(data);      //2、在控制台输出服务端推送的数据
                deferred();             //3、一次请求完成后再向后台发送请求
            })
        }
    </script>
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>HttpMessageConverter</title>
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
    <div id="resp"></div>
    <input type="button" onclick="req();" value="请求"/>
    <script src="../assets/js/jquery-1.9.1.js" type="text/javascript"></script>
    <script type="text/javascript">
        function req() {
            $.ajax({
                url:"convert",
                data:"1-wangyunfei",        //1、注意这里的格式，后台处理按此格式，用“-”隔开。
                type:"POST",
                contentType:"application/x-wisely",     //2、contentType设置的媒体类型是自定义的application/x-wisely
                success:function (data) {
                    $("#resp").html(data);
                }
            })
        }
    </script>
</body>
</html>
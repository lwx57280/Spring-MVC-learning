<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
    <title>SSE Demo</title>
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
    <div id="msgFromPush"></div>
    <script src="../assets/js/jquery-1.9.1.js" type="text/javascript"></script>
    <script type="text/javascript">
        if(!!window.EventSource){
            var source = new EventSource("push");
            var s='';
            source.addEventListener('message',function (e) {    //2、添加SSE客户端监听，在此获得服务器推送的消息
                s+=e.data+"<br/>";
                $("#msgFromPush").html(s);
            });
            source.addEventListener('open',function (e) {
                console.log("连接打开。。。");
            },false);
            source.addEventListener('error',function (e) {
                if(e.readyState==Event.CLOSED){
                    console.log("连接关闭...");
                }else{
                    console.log(e.readyState);
                }
            },false);
        }else {
            console.log("你的浏览器不支持SSE");
        }
    </script>
</body>
</html>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<body>
<h2>Hello World!</h2>
<form action="/helloController/pojoType" method="post">
    姓名:<input type="text" name="name"/><br/>
    年龄:<input type="text" name="age"/><br/>
    地址编号:<input type="text" name="address.id"/><br/>
    地址:<input type="text" name="address.name"/><br/>
    <input type="submit" value="提交"/><br/>
</form>
<a href="/helloController/addList">用户列表</a>

<a href="/helloController/addSet">用户Set列表</a>
<a href="/helloController/addMap">用户Map列表</a>
<a href="/helloController/jsonAjax">Ajax</a>
</body>
</html>

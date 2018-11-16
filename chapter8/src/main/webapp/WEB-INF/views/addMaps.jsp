<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <h2>UserMap</h2>
    <%--JSP与List和Set不同的是，不能通过下标区分不同的对象，改为通过key值区分--%>
    <form action="/helloController/mapType" method="post">
        用户1姓名:<input type="text" name="users['a'].name"/><br/>
        用户1年龄:<input type="text" name="users['a'].age"/><br/>
        用户2姓名:<input type="text" name="users['b'].name"/><br/>
        用户2年龄:<input type="text" name="users['b'].age"/><br/>
        用户3姓名:<input type="text" name="users['c'].name"/><br/>
        用户3年龄:<input type="text" name="users['c'].age"/><br/>
        <input type="submit" value="提交"/>
    </form>
</body>
</html>

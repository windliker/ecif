<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <h2>Hello Maven + SSM + GIT</h2>
    <!-- href="./user/queryUsers" -->
    <a href="./user/queryUsers">查询</a>
    <form method="post" action="/user/addUser">
        <div>
            <div>
                <label>id：</label><input type="text" name="id"/>
            </div>
            <div>
                <label>姓名：</label><input type="text" name="username"/>
            </div>
            <div>
                <label>年龄：</label><input type="text" name="age"/>
            </div>
            <div>
                <input type="submit" value="提交">
            </div>
        </div>
    </form>
</body>
</html>

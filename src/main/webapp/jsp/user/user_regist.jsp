<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<html>
<head></head>
<body>
<form action="${pageContext.request.contextPath}/user/regist" method="post">
    用户名（手机号）:<input type="text" name="phone" /><br/>
    密码:<input type="text" name="password" /><br />
    法名:<input type="text" name="dharmaname" /><br/>
    省份:<input type="text" name="province" /><br />
    城市:<input type="text" name="city" /><br />
    性别:<input type="text" name="gender" /><br />
    个性签名:<input type="text" name="personalSign" /><br/>
    头像路径:<input type="text" name="profile" value="/img/2.png"/><br />
    <input type="submit" value="提交"/>
</form>
</body>
</html>
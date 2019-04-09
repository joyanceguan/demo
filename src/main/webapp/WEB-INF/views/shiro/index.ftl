<html>
<body>
hello ${userName}!!


<@shiro.hasPermission name="user:add">
<br>add
</@shiro.hasPermission>
<@shiro.hasPermission name="user:delete">
<br>delete
</@shiro.hasPermission>
<@shiro.hasPermission name="user:query">
<br>query
</@shiro.hasPermission>

<a href="${contextPath}/shiro/query">可以试下有没有权限</a>
</body>
</html>
<html>
<body>
<input type="text" name="username">
<input type="text" name="password">
<input type="button" name="登录" value="登录" onclick="login();">
</body>
<script src="${contextPath}/js/jquery.min.js"></script>
<script>
function login(){
	var contextPath = '${contextPath}';
	var username = $("input[name='username']").val();
	var password = $("input[name='password']").val();
	$.ajax({
		async:false,
		method:"post",
		data:{userName:username,passwd:password},
		dataType: "json",
		url:contextPath+"/shiro/rlogin",
		type:"POST",
		success:function(data) {
	        window.location.href=contextPath+"/shiro/index?username="+username;
	    }, 
	    error:function(){
	        console.log("服务器繁忙...");
	    }
	});
}
</script>
</html>
 <html>
 <body>
 <script type="text/javascript">
    function reloadCode(){
       var time = new Date();
       // 给URL传递参数可以清空浏览器的缓存，让浏览器认为这是一个新的请求
       document.getElementById('safecode').src = '${contextPath}/verifycode/generate?d=' + time;
    }
 </script>    

   <form action="${contextPath}/verifycode/verify"method="post">
     验证码：<img src="${contextPath}/verifycode/generate" alt="验证码" id="safecode">
     <input type="text" id="verifyCode" name="verifyCode" size="6" />
     <a href="javascript:reloadCode();">看不清楚</a><br>
     <input type="submit" value="登录" />
   </form>
</body>
</html>

 
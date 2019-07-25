<%--
  Created by IntelliJ IDEA.
  User: zhengl
  Date: 2019/7/19
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<script src="../boot/js/jquery-3.3.1.min.js"></script>

<script type="text/javascript" src="https://cdn-hangzhou.goeasy.io/goeasy.js"></script>

<script type="text/javascript">
   $(function () {
       var goEasy = new GoEasy({
           appkey: 'BC-6c8fedfaac52425286c22b50d4ba6770'


       });
       goEasy.subscribe({
           channel:'demo_channel',
           onMessage: function(message){
               alert('收到：'+message.content);
           }
       });
   })

</script>
<html>
<head>
    <title>Title</title>
</head>
<body>
hello
</body>
</html>

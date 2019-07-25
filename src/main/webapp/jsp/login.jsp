<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<!doctype html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="../boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="../jqgrid/extend/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="../boot/js/jquery-3.3.1.min.js"></script>
    <script src="../boot/js/bootstrap.3.3.7.min.js"></script>
    <script src="../jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="../jqgrid/extend/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="../boot/js/ajaxfileupload.js"></script>
    <title>持明法洲后台管理系统</title>
    <script>
        function LoadSubmit() {
            $.ajax({
                url:"${pageContext.request.contextPath}/admin/login",
                type:"post",
                datatype:"json",
                data:$("#loginForm").serialize(),
                success:function(data){
                    if (data.code == '200'){
                        location.href="${pageContext.request.contextPath}/jsp/main.jsp"
                    }else{
                        $("#message").html(data.message)
                    }
                }
            })
        }
    </script>
</head>
<body>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-3"></div>
        <div class="col-md-6">
            <div class="jumbotron">
                <div class="container">
                    <h3>欢迎登录持明法洲后台系统</h3>
                </div>
            </div>
           <div class="row">
               <div class="col-sm-2"></div>
               <div class="col-sm-4"> <span id="message" style="color: red"></span></div>
           </div>
            <form id="loginForm" class="form-horizontal" action="javascript:void(0)">
                <div class="form-group">
                    <label for="inputEmail3" class="col-sm-2 control-label">姓名</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="inputEmail3" placeholder="请输入账号" name="username">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label" >密码</label>
                    <div class="col-sm-6">
                        <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码" name="password">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword4" class="col-sm-2 control-label">验证码</label>
                    <div class="col-sm-3">
                        <input type="text" class="form-control" id="inputPassword4" placeholder="请输入验证码" name="clientCode">
                    </div>
                    <div class="col-sm-4">
                        <img src="${pageContext.request.contextPath}/code/img">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button  class="btn btn-default" onclick="LoadSubmit()">登录</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-3"></div>
    </div>
</div>
</body>
</html>

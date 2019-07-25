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
</head>
<body>
    <%--标题导航栏--%>
<div class="container-fluid" >
    <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-left ">
                    <li>
                        <strong class="navbar-brand">持明法洲后台管理系统v1.0</strong></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li> <strong class="navbar-brand">欢迎：<strong>${sessionScope.admin.username} </strong><span class="glyphicon glyphicon-user" aria-hidden="true"></span></strong>
                        </li>
                    <li> <strong class="navbar-brand">退出登录</strong>
                        <a class="navbar-brand" href="/cmfz/admin/logout"> <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span></a></li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
    <%--页面主体--%>
    <div class="row">
        <%--主左主体--%>
        <div class="col-md-2">
            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                轮播图
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                                <ul class="nav nav-pills">
                                    <li role="presentation" ><a href="javascript:$('#contentRight').load('${pageContext.request.contextPath}/jsp/carousel.jsp')">轮播图管理</a></li>
                                </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                专辑
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <ul class="nav nav-pills">
                                <li role="presentation" ><a href="javascript:$('#contentRight').load('${pageContext.request.contextPath}/jsp/album.jsp')">专辑和章节管理</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                文章
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">
                            <ul class="nav nav-pills">
                                <li role="presentation" ><a href="javascript:$('#contentRight').load('${pageContext.request.contextPath}/jsp/article.jsp')">文章管理</a></li>
                           </ul>
                         </div>

                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFour">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFour" aria-expanded="false" aria-controls="collapseFour">
                                用户
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFour">
                        <div class="panel-body">
                            <ul class="nav nav-pills">
                                <li role="presentation" ><a href="javascript:$('#contentRight').load('${pageContext.request.contextPath}/jsp/user/user.jsp')">用户管理</a></li>
                                <li role="presentation" ><a href="javascript:$('#contentRight').load('${pageContext.request.contextPath}/jsp/user/user_countRegist.jsp')">每月注册用户</a></li>
                                <li role="presentation" ><a href="javascript:$('#contentRight').load('${pageContext.request.contextPath}/jsp/user/user_distribution.jsp')">全国用户分布图</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%--主体右--%>
        <div class="col-md-10" id="contentRight">
            <div class="jumbotron">
                <h2>欢迎来到持明法洲后台管理系统</h2>
            </div>
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                </ol>
                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="${pageContext.request.contextPath}/img/1.png" class="center-block">
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/img/2.png"  class="center-block">
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/img/3.png" class="center-block">
                    </div>
                    <div class="item">
                        <img src="${pageContext.request.contextPath}/img/4.png"  class="center-block">
                    </div>
                </div>
                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

        </div>
    </div>
    <%--页尾--%>
    <nav class="navbar navbar-default navbar-fixed-bottom">
        <div class="container text-center">
            <span >@百知教育 baizhi@zparkhr.com.cn</span>
        </div>
    </nav>
</div>

    <div class="modal fade" tabindex="-1" id="queryArticleModal">
        <div class="modal-dialog">
            <div class="modal-content" style="width: 800px;">
                <div class="modal-header">
                    <button class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">预览内容</h4>
                </div>
                <div class="modal-body">
                    <div id="article"></div>

                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div>


</body>
</html>
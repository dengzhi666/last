<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div class="page-header">
    <h2>用户管理</h2>
</div>
<script type="text/javascript">
    $(function(){
        $("#userTable").jqGrid({
            url:"${pageContext.request.contextPath}/user/queryAll",
            datatype:"json",
            autowidth:true,
            height:250,
            width:200,
            multiselect:true,
            rownumbers:true,
            colNames:["编号","手机号","密码","盐","法号","省份","城市","性别","个性签名","简介","状态","注册时间","操作"],
            colModel:[
                {name:"id"},
                {name:"phone"},
                {name:"password"},
                {name:"salt"},
                {name:"dharmaname"},
                {name:"province"},
                {name:"city"},
                {name:"gender"},
                {name:"personalSign"},
                {name:"profile"},
                {name:"status"},
                {name:"registTime"},
                {name:"xx",formatter:function (celVal, row, jsonRow) {
            <%--return "<a class='btn btn-primary' role='button' href='${pageContext.request.contextPath}/user/update?id="+jsonRow.id+"&status="+jsonRow.status+"'>改变状态</a>";--%>
            return "<button class='btn btn-primary' onclick='updateStatus(\""+jsonRow.id+"\",\""+jsonRow.status+"\")'>改变状态</button>";
        }}
            ],
            // 分页相关
            pager:"#userpager",
            viewrecords:true,
            rowNum:10,// 每页显示的行数
            rowList:[ 5, 10, 20], // 行数的数组
            page:1,  // 默认显示的页码
            styleUI:"Bootstrap",
            //增删改相关
            editurl:"${pageContext.request.contextPath}/carousel/edit" // 增删改提交到的的资源地址，使用oper参数进行区分（添加add 修改edit 删除del）
            // 参数1：固定定法
            // 参数2：分页工具栏的选择器
        }).navGrid("#user",{add:false,edit:false,del:false,refresh:false});

    })
    function updateStatus(aa,bb) {
        $.post("${pageContext.request.contextPath}/user/update","id="+aa+"&status="+bb,function () {
            jQuery("#userTable").trigger("reloadGrid");
        })
    }
    
    function inputExcel() {
        $.ajaxFileUpload({
            contentType: 'multipart/form-data',
            url:"${pageContext.request.contextPath}/user/inputUser",
            type:'post',
            secureuri:false,
            datatype:"json",
            fileElementId:'file',//注：file id须用单引号引起来
            success:function () {
                jQuery("#userTable").trigger("reloadGrid");
            }
        })
    }

    function outputUser() {
        location.href="${pageContext.request.contextPath}/user/outputUser"
    }
</script>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    用户导入
</button>
<!-- Modal -->
<div class="modal fade" id="myModal"  role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">请选择导入的文件 </h4>
            </div>
            <form method="post" action="javascript:void(0)" enctype="multipart/form-data">
            <div class="modal-body">
                <input type="file" id="file"  name="file">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="inputExcel()">Save changes</button>
            </div>
            </form>
        </div>
    </div>
</div>
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" id="userOutput" onclick="outputUser()">
用户导出
</button>
<div>
    <%--表格开始--%>
    <table border="1" class="table table-bordered "   id="userTable">
    </table>
    <div id="userpager"></div>
    <%--表格结束--%>
</div>




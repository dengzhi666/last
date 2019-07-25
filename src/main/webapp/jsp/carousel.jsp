<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div class="page-header">
    <h2>轮播图管理</h2>
</div>
<script type="text/javascript">
    $(function(){
        // 初始化jqGrid
        $("#carouselTable").jqGrid({
            url:"${pageContext.request.contextPath}/carousel/queryAll",
            datatype:"json",
            autowidth:true,
            height:250,
            multiselect:true,
            rownumbers:true,
            colNames:["编号","轮播图名称","轮播图图片","状态","创建时间"],
            colModel:[
                {name:"id"},
                {name:"title",editable:true},
                {name:"imgpath",editable:true,
                    edittype:"file",formatter:function (cellvalue) {
                        return "<img width='100px' height='70px' src='${pageContext.request.contextPath}/upload/"+cellvalue+"' />"
                    }
                },
                {name:"status",editable:true},
                {name:"createTime",editable:true,
                    edittype:"date"},
            ],
            // 分页相关
            pager:"#pager",
            viewrecords:true,
            rowNum:5,// 每页显示的行数
            rowList:[2, 5, 10, 15], // 行数的数组
            page:1,  // 默认显示的页码
            styleUI:"Bootstrap",
            //增删改相关
            editurl:"${pageContext.request.contextPath}/carousel/edit" // 增删改提交到的的资源地址，使用oper参数进行区分（添加add 修改edit 删除del）
            // 参数1：固定定法
            // 参数2：分页工具栏的选择器
        }).navGrid("#pager",{add:true,edit:true,del:true,refresh:true});

        //添加数据
        $("#add_carouselTable").click(function () {
            $("#imgpath").attr("name","img")
            $("#sData").click(function () {
                $.ajaxFileUpload({
                    contentType: 'multipart/form-data',
                    url:"${pageContext.request.contextPath}/carousel/add?"+$("#FrmGrid_carouselTable").serialize(),
                    type:'post',
                    secureuri:false,
                    fileElementId:'imgpath',//注：file id须用单引号引起来
                    success:function () {
                        //重新加载表格内的数据
                        jQuery("#carouselTable").trigger("reloadGrid");
                    }
                })
            })
        })
    })

</script>
<div>
     <%--表格开始--%>
    <table border="1" class="table table-bordered "   id="carouselTable">
    </table>
    <div id="pager"></div>
    <%--表格结束--%>
</div>




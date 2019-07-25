<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div class="page-header">
    <h2>专辑管理</h2>
</div>
<script type="text/javascript">
    $(function(){
        $("#albumlTable").jqGrid({
            url:"${pageContext.request.contextPath}/album/queryAll",
            datatype:"json",
            autowidth:true,
            height:200,
            width:200,
            multiselect:true,
            rownumbers:true,
            colNames:["编号","专辑名称","专辑封面","章节数量","专辑得分","专辑作者","播音员","专辑简介","出版时间"],
            colModel:[
                {name:"id"},
                {name:"title",editable:true},
                {name:"cover",editable:true,
                    edittype:"file"
                    ,formatter:function (cellvalue) {
                    return "<img width='100px' height='70px' src='${pageContext.request.contextPath}/upload/"+cellvalue+"' />"
                    }
                },
                {name:"count"},
                {name:"score",editable:true},
                {name:"author",editable:true},
                {name:"broadcast",editable:true},
                {name:"brief",editable:true},
                {name:"publishTime",editable:true,
                    edittype:"date"},
            ],
            // 分页相关
            pager:"#albumpager",
            viewrecords:true,
            rowNum:10,// 每页显示的行数
            rowList:[ 5, 10, 20], // 行数的数组
            page:1,  // 默认显示的页码

            subGrid : true,
            caption : "Grid as Subgrid",
            subGridRowExpanded : function(subgrid_id, row_id) {
                var subgrid_table_id, pager_id;
                subgrid_table_id = subgrid_id + "_t";
                pager_id = "p_" + subgrid_table_id;
                $("#" + subgrid_id).html(
                    "<table id='" + subgrid_table_id
                    + "' class='scroll'></table><div id='"
                    + pager_id + "' class='scroll'></div>");
                jQuery("#" + subgrid_table_id).jqGrid(
                    {
                        styleUI:"Bootstrap",
                        multiselect:true,
                        rownumbers:true,
                        url : "${pageContext.request.contextPath}/chapter/queryAll?albumId=" + row_id,
                        datatype : "json",
                        colNames : [ '编号', '专辑编号', '音频名称', '音频大小','音频路径 ' ,'上传时间 ','操作 '],
                        colModel : [
                            {name : "id"},
                            {name : "albumId"},
                            {name : "title",editable:true},
                            {name : "chapterSize",editable:true},
                            {name : "downpath",editable:true, edittype:"file"},
                            {name : "uploadTime",editable:true, edittype:"date"},
                            {name:"xx" ,formatter:function (celVal, row, jsonRow) {
                                    // return " <button onclick='downloadRow("+jsonRow.downpath+")' class='btn btn-primary'>下载</button>";
                                    return " <a class=\"btn btn-primary btn-lg active\" role=\"button\" href=\"${pageContext.request.contextPath}/chapter/download?filename="+jsonRow.downpath+"\">下载</a>";

                                } }
                        ],
                        rowNum : 10,
                        rowList:[ 5, 10, 15], // 行数的数组
                        page:1,  // 默认显示的页码
                        pager : pager_id,
                        viewrecords:true,
                        sortname : 'num',
                        sortorder : "asc",
                        height : '100%',
                        editurl:"${pageContext.request.contextPath}/carousel/edit"
                    });
                jQuery("#" + subgrid_table_id).jqGrid('navGrid',
                    "#" + pager_id, {} ,{
                        // 修改部分
                        closeAfterEdit:true,
                    },{
                        // 添加部分
                        closeAfterEdit:true,
                        afterSubmit:function(){
                            $("#downpath").attr("name","img")
                            $.ajaxFileUpload({
                                contentType: 'multipart/form-data',

                                url:"${pageContext.request.contextPath}/chapter/upload?"+$("#FrmGrid_albumlTable_1995_t").serialize(),
                                fileElementId:"downpath",
                                data:{"albumId":row_id},
                                type:"post",
                                success:function(){
                                    $("#" + subgrid_table_id).trigger("reloadGrid");
                                }
                            })
                            return "[true]";
                        }
                    } );
            },
            styleUI:"Bootstrap",
            //增删改相关
            editurl:"${pageContext.request.contextPath}/carousel/edit" // 增删改提交到的的资源地址，使用oper参数进行区分（添加add 修改edit 删除del）
            // 参数1：固定定法
            // 参数2：分页工具栏的选择器
        }).navGrid("#albumpager",{add:true,edit:true,del:true,refresh:true});
        $("#add_albumlTable").click(function () {
            $("#cover").attr("name","img")
            $("#sData").click(function () {
                $.ajaxFileUpload({
                    contentType: 'multipart/form-data',
                    url:"${pageContext.request.contextPath}/album/add?"+$("#FrmGrid_albumlTable").serialize(),
                    type:'post',
                    secureuri:false,
                    fileElementId:'cover',//注：file id须用单引号引起来
                    success:function () {
                        //重新加载表格内的数据
                        jQuery("#" + subgrid_table_id).trigger("reloadGrid");
                    }
                })
            })
        })



    })






</script>



<div>
    <%--表格开始--%>
    <table border="1" class="table table-bordered "   id="albumlTable">
    </table>
    <div id="albumpager"></div>
    <%--表格结束--%>
</div>




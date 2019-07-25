<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<div class="page-header">
    <h2>轮播图管理</h2>
</div>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
<script type="text/javascript">
    $(function(){
        // 初始化jqGrid
        $("#articleTable").jqGrid({
            url:"${pageContext.request.contextPath}/article/queryAll",
            datatype:"json",
            autowidth:true,
            height:200,
            multiselect:true,
            rownumbers:true,
            colNames:["编号","上师编号","标题","内容","上传时间"],
            colModel:[
                {name:"id"},
                {name:"guruId",editable:true},
                {name:"title",editable:true },
                {name:"content",editable:true,formatter:function(cellvalue, options, rowObject){
                        return "<button type='button' class='btn btn-primary' onclick='queryArticle(\""+rowObject.id +"\")'> 浏览</button>"
                    }
                },
                {name:"publishTime",editable:true,
                    edittype:"date"},
            ],
            // 分页相关
            pager:"#articlepager",
            viewrecords:true,
            rowNum:5,// 每页显示的行数
            rowList:[2, 5, 10, 15], // 行数的数组
            page:1,  // 默认显示的页码
            styleUI:"Bootstrap",
            //增删改相关
            editurl:"${pageContext.request.contextPath}/article/edit" // 增删改提交到的的资源地址，使用oper参数进行区分（添加add 修改edit 删除del）
            // 参数1：固定定法
            // 参数2：分页工具栏的选择器
        }).navGrid("#articlepager",{add:false},{
            closeAfterAdd:true,
            afterSubmit:function () {
                jQuery("#articleTable").trigger("reloadGrid");
            }
        },{
            closeAfterEdit:true,
            afterSubmit:function () {
                jQuery("#articleTable").trigger("reloadGrid");
            }
        });
        KindEditor.create('#editor_id',{
            width : '750px',
            uploadJson:'${pageContext.request.contextPath}/article/upload',
            fileManagerJson:'${pageContext.request.contextPath}/article/showAll',
            allowFileManager:true,
            filePostName:'file',
            afterBlur:function(){
                this.sync();

            }
        });
    })
    //预览的模态框
   function queryArticle(id){
           $.ajax({
               url:"${pageContext.request.contextPath}/article/queryOne",
               type:"post",
               datatype:"json",
               data:"id="+id,
               success:function (data) {
                   $("#queryArticleModal").modal({
                       backdrop:false,
                       keyboard:false,
                       // show:false
                   });
                   $("#article").html(data.content)
               }
           })
   }

    //添加的模态框
    function addArticle(){
        $.ajax({
            url:"${pageContext.request.contextPath}/article/addArticle",
            type:"post",
            datatype:"json",
            data:$("#articleForm").serialize(),
            success:function(){
                jQuery("#articleTable").trigger("reloadGrid");
            }
        })
    }
</script>
<!-- Button trigger modal -->
<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
    文章上传
</button>
<!-- Modal -->
<div class="modal fade" id="myModal"  role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content" style="width: 800px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
            </div>
            <form class="form-horizontal" action="javascript:void(0);" id="articleForm">
                <div class="modal-body">
                        <div class="form-group">
                            <label for="text1" class="col-sm-2 control-label">上师编号</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="text1" placeholder="上师编号" name="guruId">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="text2" class="col-sm-2 control-label">标题</label>
                            <div class="col-sm-8">
                                <input type="text" class="form-control" id="text2" placeholder="标题" name="title">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="date" class="col-sm-2 control-label">出版时间</label>
                            <div class="col-sm-8">
                                <input type="date" class="form-control" id="date" placeholder="Password" name="publishTime">
                            </div>
                        </div>
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                                    文本内容
                        </textarea>


                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" onclick="addArticle()"> Save changes</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div>
     <%--表格开始--%>
    <table border="1" class="table table-bordered "   id="articleTable">
    </table>
    <div id="articlepager"></div>
    <%--表格结束--%>
</div>




<%@page isELIgnored="false" pageEncoding="UTF-8" contentType="text/html; UTF-8" %>

<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.ready(function(K) {
        window.editor = K.create('#editor_id',{
            width : '600px',
            uploadJson:'${pageContext.request.contextPath}/article/upload',
            fileManagerJson:'${pageContext.request.contextPath}/article/showAll',
            allowFileManager:true,
            filePostName:'file',
            afterBlur:function(){
                this.sync();
            }
        });
    });
</script>

    <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                文本内容
    </textarea>
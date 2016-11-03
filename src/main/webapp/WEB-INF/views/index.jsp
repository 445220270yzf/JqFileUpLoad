<%--
  Created by IntelliJ IDEA.
  User: yzf
  Date: 2016/11/3
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title></title>
    <script src="${ctx}/static/js/jquery-3.1.1/jquery-3.1.1.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/jQuery-File-Upload/js/load-image.all.min.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/jQuery-File-Upload/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/jQuery-File-Upload/js/jquery.iframe-transport.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/jQuery-File-Upload/js/jquery.fileupload.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/jQuery-File-Upload/js/jquery.fileupload-process.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/jQuery-File-Upload/js/jquery.fileupload-image.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/jQuery-File-Upload/js/jquery.fileupload-audio.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/jQuery-File-Upload/js/jquery.fileupload-video.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/jQuery-File-Upload/js/jquery.fileupload-validate.js" type="text/javascript"></script>
    <script>
         $(function () {
            $("#fileupload").fileupload({
                url:'${ctx}/upLoad',
                dataType: 'json',
                autoUpload: true,
                acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i,
                maxFileSize: 5000000,
                progressall:function (e,data) {
                    $("span").html("");
                    var progress = parseInt(data.loaded / data.total * 100, 10);
                    $("span").append(progress);
                },
                done: function (e, data) {
                    alert(data.result.info)
                }
            });
         });
    </script>
</head>
<body>
    <input id="fileupload" name="file" type="file" multiple>
    已经上传了<span></span>%
</body>
</html>

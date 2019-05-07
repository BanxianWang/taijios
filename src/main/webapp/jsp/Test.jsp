<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/27 0027
  Time: 19:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script>
        $(function () {
            $("#btn").click(function () {
                var id = $("#id").val();
                $.ajax({
                    url: "sevenday",
                    type: "POST",
                    dataType: "json",
                    success: function (result) {
                        alert(result)
                    }
                })
            })
        })
    </script>
</head>
<body>
    <input type="text" id="id"/>
    <input type="button" id="btn" value="查找">

</body>
</html>

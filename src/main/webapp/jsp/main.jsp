<%--
  Created by IntelliJ IDEA.
  User: wrm
  Date: 2019/4/21
  Time: 20:15
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
                    show();
            })

            $("#btn1").click(function () {
                var oldtime = $("#oldtime").val()
                var newtime = $("#newtime").val()
                $.ajax({
                    url: "/jsp/getAllTemperature",
                    data: {machineID: 1, oldtime: oldtime,newtime:newtime,word:"1"},
                    type: "POST",
                    dataType: "json",
                    success: function (result) {
                        $("#machineTable tr").remove();

                        $.each(result, function (index, item) {


                        })

                    }
                })


            })

        })
        
        function show() {
            var id = $("#id").val()
            var city = $("#city").val()
            $.ajax({
                url: "/jsp/getAllMachines",
                data: {id: id, city: city},
                type: "POST",
                dataType: "json",
                success: function (result) {
                    $("#machineTable tr").remove();
                    var temperature = "已开启";
                    var runningstate = "已开启";
                    $.each(result, function (index, item) {
                        if (item.temperature == 0) {
                            temperature = "未开启"
                        }
                        if (item.runningstate == 0) {
                            runningstate = "未开启"
                        }
                        var tr = "<tr>" +
                            "<td>" + item.id + "</td>" +
                            "<td>" + item.city + "</td>" +
                            "<td>" + temperature + "</td>" +
                            "<td>" + item.usetime + "秒</td>" +
                            "<td>" + runningstate + "</td>" +
                            "<td><a href='javascrip:void(0)' onclick='del(" + item.id + ")'>删除</a></td>"
                        "</tr>"
                        $("#machineTable").append(tr);
                    })

                }
            })
        }

        function formatDate(time){
            var date = new Date(time);
            var str = date.getFullYear() + "-";
            str += ((date.getMonth()+1)<10?("0"+(date.getMonth()+1)):(date.getMonth()+1)) +"-" ;
            str += (date.getDate()<10?("0"+ date.getDate()): date.getDate()) +" ";
            str += (date.getHours()<10?("0"+date.getHours()):date.getHours()) + ":";
            str += (date.getMinutes()<10?("0"+date.getMinutes()):date.getMinutes()) + ":";
            str += date.getSeconds()<10?("0"+date.getSeconds()):date.getSeconds();
            return str;
        }


        
        function del(id) {
            if (confirm("确定要删除该条信息嘛？")){
                $.ajax({
                    url: "/jsp/delMachine",
                    data: {id: id},
                    type: "POST",
                    dataType: "text",
                    success: function (result) {
                        if (result == "true") {
                            alert("删除成功！")
                            show()
                        }else {
                            alert("删除失败！")
                        }
                    }
                })
            }
        }
    </script>
</head>
<body>
    <input type="text" id="id">
    <input type="text" id="city">
    <input type="button" id="btn" value="登录">
    <input type="text" id="oldtime">
    <input type="text" id="newtime">
    <input type="button" id="btn1" value="登录1">
    <table id="machineTable"></table>





    <form method="post" action="/jsp/addMachine">
        是否有控件<input type="text" name="temperature">
        使用时长<input type="text" name="usetime">
        是否开启<input type="text" name="runningstate">
        城市：<input type="text" name="city">
        <input type="submit" value="aaa">

    </form>
</body>
</html>

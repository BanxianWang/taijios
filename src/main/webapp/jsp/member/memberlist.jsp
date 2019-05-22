<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>用户列表页</title>
    <link rel="stylesheet" href="../../statics/css/font.css">
    <link rel="stylesheet" href="../../statics/css/xadmin.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="../../statics/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../statics/js/xadmin.js"></script>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

    <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
    <!--[if lt IE 9]>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<script>
    var currentPage = 1;
    $(function () {
        showList(currentPage);
        bindEvent();


        /*setInterval(getsevenDay(), 1000);*/
    })

    /*  function getsevenDay() {
          $.post("sevenday", "data=0", function (rtndata) {
              if (rtndata == "true") {
                  alert(1)
              }
          }, "text");
      }*/

    function showList(currentPage) {
        var username = $("#username").val();
        var phone = $("#phone").val();
        $.ajax({
            url: "/jsp/user/userlist",
            data: {"currentPage": currentPage, "username": username, "phone": phone},
            type: "POST",
            dataType: "json",
            success: function (msg) {
                var str = "";
                $(msg.list).each(function (i) {
                    var b = msg.list[i];
                    var state = b.state == 0 ? "已启用" : "已停用";
                    str += "<tr>"
                    str += "<td>"
                    str += "<span>" + b.id + "</span>"
                    str += "</td>"
                    str += "<td>"
                    str += "<span>" + b.username + "</span>"
                    str += "</td>"
                    str += "<td>"
                    str += "<span>"
                    str += b.gender == 0 ? "男" : "女"
                    str += "</span>"
                    str += "</td>"
                    str += "<td>"
                    str += "<span>" + b.phone + "</span>"
                    str += "</td>"
                    str += "<td>"
                    str += "<span>" + b.email + "</span>"
                    str += "</td>"
                    str += "<td>"
                    str += "<span>" + b.address + "</span>"
                    str += "</td>"
                    str += "<td>"
                    str += "<span>" + b.registerDate + "</span>"
                    str += "</td>"
                    str += "<td>"
                    str += "<span id='sss' class='layui-btn layui-btn-normal layui-btn-mini'\">" + state + "</span>"
                    str += "</td>"
                    str += "<td class='td-manage'>"
                    str += "<a onclick='member_stop(this,10001)' userId='" + b.id + "' id='changestate' href='javascript:void(0);'  title='" + state + "'>"
                    str += "<i class='layui-icon'>&#xe601;</i></a>"
                    str += " <a title=\"编辑\"  onclick=\"x_admin_show('编辑','memberpassword.jsp',600,400,'"+b.id+"')\" href=\"javascript:void(0);\">\n" +
                        "<i class=\"layui-icon\">&#xe642;</i>\n" +
                        "</a>"
                    str += "<a onclick=\"x_admin_show('修改密码','memberpassword.jsp',600,400,'"+b.id+"')\" title=\"修改密码\"  userId='\" + b.id + \"' href=\"javascript:void(0);\">\n" +
                        "<i class=\"layui-icon\">&#xe631;</i>\n" +
                        " </a>"
                    str += "  <a    userId='" + b.id + "' title=\"删除\" onclick=\"member_del(this,'要删除的id')\"    href=\"javascript:void(0);\">\n" +
                        " <i class=\"layui-icon\">&#xe640;</i>\n" +
                        "  </a>"
                    str += "</td>"
                    str += "</tr>"

                });
                $(".layui-table tr:not(:first)").remove();
                $(".layui-table").append(str);
                $("#count").html(msg.count);
                $("#currentPage").html(currentPage);
                $("#totalPage").html(msg.totalPage);
            }
        })
    }

    function bindEvent() {

        $("#btn").click(function () {
            currentPage = 1;
            showList(currentPage);
        })

        $(".next").click(function () {

            if (currentPage >= parseInt($("#totalPage").html())) {
                return;
            }
            currentPage++;
            showList(currentPage);

        })


        $(".prev").click(function () {
            if (currentPage == 1) {
                return;
            }
            currentPage--;
            showList(currentPage);
        })

        $(".first").click(function () {
            currentPage=1;
            showList(currentPage);
        })


        $(".last").click(function () {
            currentPage = parseInt($("#totalPage").html())
            showList(currentPage);
        })

        $(".page-btn").click(function () {
            var inputPage = $("#inputPage").val();
            if (inputPage < 1) {
                alert("请正确输入页面数")
                return;
            }

            if (inputPage > parseInt($("#totalPage").html())) {
                alert("请正确输入页面数")
                return;
            }
            currentPage = inputPage;
            showList(currentPage);
        })
    }

    /*用户-停用*/
    function member_stop(obj, id) {

        if ($(obj).attr('title') == '已启用') {
            layer.confirm('确认要停用吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                //发异步把用户状态进行更改

                var changestate = $(obj).attr('title') == "已启用" ? 1 : 0;
                var userId = $(obj).attr('userId');
                $.ajax({
                    url: "/jsp/user/changes",
                    dataType: "json",
                    data: {"changestate": changestate, "userId": userId},
                    type: "post",
                    success: function (msg) {
                        if (msg.count == 1) {
                            $("#sss").html("已停用");
                            $(obj).attr('title', '已停用')
                            $(obj).find('i').html('&#xe62f;');
                            $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                            layer.msg('已停用!', {icon: 5, time: 1000});
                        }
                    }
                })

            })

        } else if ($(obj).attr('title') == '已停用') {
            layer.confirm('确认要启用吗？', {
                btn: ['确定', '取消'] //按钮
            }, function () {

                var changestate = $(obj).attr('title') == "已启用" ? 1 : 0;
                var userId = $(obj).attr('userId');
                $.ajax({
                    url: "/jsp/user/changes",
                    dataType: "json",
                    data: {"changestate": changestate, "userId": userId},
                    type: "post",
                    success: function (msg) {
                        if (msg.count == 1) {
                            $("#sss").html("已启用");
                            $(obj).attr('title', '已启用')
                            $(obj).find('i').html('&#xe601;');
                            $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                            layer.msg('已启用!', {icon: 6, time: 1000});
                        }
                    }
                })


            })


        }


    }


</script>


<body>
<div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a href="">演示</a>
        <a>
          <cite>导航元素</cite></a>
      </span>
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right"
       href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
            <input type="text" name="username" id="username" placeholder="请输入用户名" autocomplete="off"
                   class="layui-input">
            <input class="layui-input" placeholder="手机号" name="phone" id="phone">

            <input type="button" value="查找" id="btn" class="layui-btn">
        </form>
    </div>

    <table class="layui-table">
        <thead>
        <tr>
            <th>ID</th>
            <th>用户名</th>
            <th>性别</th>
            <th>手机</th>
            <th>邮箱</th>
            <th>地址</th>
            <th>创建时间</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
    </table>
    <div class="page">
        <ul class="page-num-ul clearfix" style="display: inline-block">
            <li style="display: inline-block;position: relative;left: -180px;">共<span id="count"
                                                                                      style="border: none;"></span>条记录&nbsp;&nbsp;
                <span id="currentPage" style="border: none;"></span>/<span id="totalPage" style="border: none;"></span>页
            </li>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0)" class="first" style="border: none;">首页</a>
            <a href="javascript:void(0)" class="prev" style="border: none;">上一页</a>


            <a href="javascript:void(0)" class="next" style="border: none;">下一页</a>
            <a href="javascript:void(0)" class="last" style="border: none;">尾页</a>

            &nbsp;&nbsp;&nbsp;&nbsp;
        </ul>
        <span class="page-go-form" style="border: none;position: relative;left: 180px;"><label>跳转至</label>
	     <input type="text" name="inputPage" id="inputPage" class="page-key"/>页
	     <button type="button" class="page-btn" onClick=''>GO</button>
		</span>
    </div>

</div>
<script>
    layui.use('laydate', function () {
        var laydate = layui.laydate;

        //执行一个laydate实例
        laydate.render({
            elem: '#start' //指定元素
        });

        //执行一个laydate实例
        laydate.render({
            elem: '#end' //指定元素
        });
    });


    /*用户-删除*/
    function member_del(obj, id) {

        layer.confirm('确认要删除吗？', function () {
            //发异步删除数据
            var userId = $(obj).attr('userId');
            $.ajax({
                url: "/jsp/user/deleteUser",
                dataType: "json",
                data: {"userId": userId},
                type: "post",
                success: function (msg) {
                    if (msg.count == 1) {
                        $(obj).parents("tr").remove();
                        layer.msg('已删除!', {icon: 1, time: 1000});
                        showList(1);
                        //location.replace(location.href);
                    }
                }
            })


        });
    }


    function delAll(argument) {

        var data = tableCheck.getData();

        layer.confirm('确认要删除吗？' + data, function (index) {
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
    }
</script>
<script>var _hmt = _hmt || [];
(function () {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>
</body>

</html>
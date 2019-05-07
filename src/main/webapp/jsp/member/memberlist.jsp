
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>欢迎页面-X-admin2.0</title>
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
    $(function(){
        showList(currentPage)

        $(".layui-btn").click(function(){
            showList(currentPage);
        })

        setInterval(getsevenDay(),1000);
    })

    function getsevenDay(){
        $.post("sevenday","data=0",function(rtndata){
                if(rtndata=="true"){
                    alert(1)
                }
        },"text");
    }

    function showList(currentPage){
        $.ajax({
            url: "/jsp/user/userlist",
            data:{"currentPage":currentPage},
            type: "POST",
            dataType: "json",
            success: function (result) {
                var str = "";

                $(result.list).each(function(i){
                    var b = result.list[i]

                    str += "<tr>"
                    str += "<td>"
                    str += "<div class='layui-unselect layui-form-checkbox' lay-skin='primary' data-id='2'><i class='layui-icon'>&#xe605;</i></div>"
                    str += "</td>"
                    str += "<td>"
                    str += "<span>"+b.id+"</span>"
                    str += "</td>"
                    str += "<td>"
                    str += "<span>"+b.username+"</span>"
                    str += "</td>"
                    str += "<td>"
                    str += "<span>"+b.gender+"</span>"
                    str += "</td>"
                    str += "<td>"
                    str += "<span>"+b.phone+"</span>"
                    str += "</td>"
                    str += "<td>"
                    str += "<span>"+b.email+"</span>"
                    str += "</td>"
                    str += "<td>"
                    str += "<span>"+b.address+"</span>"
                    str += "</td>"
                    str += "<td>"
                    str += "<span>"+b.createDate+"</span>"
                    str += "</td>"
                    str += "<td>"
                    str += "<span class='layui-btn layui-btn-normal layui-btn-mini'\">"
                    str += b.state == 0 ? "未启用" : "已启用"
                    str += "</span>"
                    str += "</td>"
                    str += "<td class='td-manage'>"
                    str += "<a onclick='member_stop(this,\"10001\")' href='javascript:;'  title='启用'>"
                    str += "<i class=\"layui-icon\">&#xe601;</i></a>"
                    str += "<a title=\"编辑\"  onclick=\"x_admin_show('编辑','member-edit.html',600,400)\" href=\"javascript:;\">"
                    str += "<i class=\"layui-icon\">&#xe642;</i></a>"
                    str += " <a onclick=\"x_admin_show('修改密码','member-password.html',600,400)\" title=\"修改密码\" href=\"javascript:;\">"
                    str += "<i class=\"layui-icon\">&#xe631;</i></a>"
                    str += "<a title=\"删除\" onclick=\"member_del(this,'要删除的id')\" href=\"javascript:;\">"
                    str += "<i class=\"layui-icon\">&#xe640;</i> </a>"
                    str += "</td>"
                    str += "</tr>"

                })
                $(".layui-table tr:not(:first)").remove();
                $(".layui-table").append(str)
                $("#count").html(msg.count);
                $("#currentPage").html(currentPage);
                $("#totalPage").html(msg.totalPage)
            }
        })
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
    <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
</div>
<div class="x-body">
    <div class="layui-row">
        <form class="layui-form layui-col-md12 x-so">
            <input class="layui-input" placeholder="性别" name="gender" id="gender">
            <input class="layui-input" placeholder="开始日" name="start" id="start">
            <input class="layui-input" placeholder="截止日" name="end" id="end">

            <input type="text" name="username"  placeholder="请输入用户名" autocomplete="off" class="layui-input">
            <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
    </div>
    <xblock>
        <button class="layui-btn layui-btn-danger" onclick="delAll()"><i class="layui-icon"></i>批量禁用</button>
        <button class="layui-btn" onclick="x_admin_show('添加用户','./member-add.html',600,400)"><i class="layui-icon"></i>添加</button>
        <span class="x-right" style="line-height:40px">共有数据：88 条</span>
    </xblock>
    <table class="layui-table">
        <thead>
        <tr>
            <th>
                <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>ID</th>
            <th>用户名</th>
            <th>性别</th>
            <th>手机</th>
            <th>邮箱</th>
            <th>地址</th>
            <th>加入时间</th>
            <th>状态</th>
            <th>操作</th></tr>
        </thead>
        <tbody>

        </tbody>
    </table>
    <div class="page">
        <ul class="page-num-ul clearfix" style="display: inline-block">
            <li style="display: inline-block;position: relative;left: -180px;">共<span id="count" style="border: none;"></span>条记录&nbsp;&nbsp; <span id="currentPage"style="border: none;"></span>/<span id="totalPage"style="border: none;"></span>页</li>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="javascript:void(0)" class="first"style="border: none;">首页</a>
            <a href="javascript:void(0)" class="prev"style="border: none;">上一页</a>


            <a href="javascript:void(0)" class="next"style="border: none;">下一页</a>
            <a href="javascript:void(0)" class="last"style="border: none;">最后一页</a>

            &nbsp;&nbsp;&nbsp;&nbsp;
        </ul>
        <span class="page-go-form"style="border: none;position: relative;left: 180px;"><label>跳转至</label>
	     <input type="text" name="inputPage" id="inputPage" class="page-key" />页
	     <button type="button" class="page-btn" onClick=''>GO</button>
		</span>
    </div>

</div>
<script>
    layui.use('laydate', function(){
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

    /*用户-停用*/
    function member_stop(obj,id){
        layer.confirm('确认要停用吗？',function(index){

            if($(obj).attr('title')=='启用'){

                //发异步把用户状态进行更改
                $(obj).attr('title','停用')
                $(obj).find('i').html('&#xe62f;');

                $(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
                layer.msg('已停用!',{icon: 5,time:1000});

            }else{
                $(obj).attr('title','启用')
                $(obj).find('i').html('&#xe601;');

                $(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
                layer.msg('已启用!',{icon: 5,time:1000});
            }

        });
    }

    /*用户-删除*/
    function member_del(obj,id){
        layer.confirm('确认要删除吗？',function(index){
            //发异步删除数据
            $(obj).parents("tr").remove();
            layer.msg('已删除!',{icon:1,time:1000});
        });
    }



    function delAll (argument) {

        var data = tableCheck.getData();

        layer.confirm('确认要删除吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
    }
</script>
<script>var _hmt = _hmt || []; (function() {
    var hm = document.createElement("script");
    hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
    var s = document.getElementsByTagName("script")[0];
    s.parentNode.insertBefore(hm, s);
})();</script>
</body>

</html>
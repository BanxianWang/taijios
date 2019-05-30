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

<body>
<div class="x-body">
    <form class="layui-form" method="post">
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="x-red">*</span>昵称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_username" name="username" required="" lay-verify="nikename"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_password" class="layui-form-label">
                <span class="x-red">*</span>旧密码
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_password" name="password" disabled="disabled" lay-verify="password"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_pass" class="layui-form-label">
                <span class="x-red">*</span>新密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_pass" name="newpass" required="" lay-verify="newpass"
                       autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-mid layui-word-aux">
                6到12个字符
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
                <span class="x-red">*</span>确认密码
            </label>
            <div class="layui-input-inline">
                <input type="password" id="L_repass" name="repass" required="" lay-verify="repass"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_email" class="layui-form-label">
                <span class="x-red">*</span>邮箱
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_email" name="email" required="" lay-verify="email"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_address" class="layui-form-label">
                <span class="x-red">*</span>地址
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_address" name="address" required="" lay-verify="address"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_phone" class="layui-form-label">
                <span class="x-red">*</span>手机号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_phone" name="phone" required="" lay-verify="phone"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_repass" class="layui-form-label">
            </label>
            <button class="layui-btn" lay-filter="save" lay-submit="">
                修改
            </button>

        </div>
    </form>
</div>
<script>
    $(function () {
        var id = sessionStorage.getItem("id");
        $.ajax({
            url: "/taijios/jsp/user/showUser",
            data: {"id": id},
            type: "POST",
            dataType: "json",
            success: function (msg) {
                $("#L_username").val(msg.username);
                $("#L_password").val(msg.password);
                $("#L_email").val(msg.email);
                $("#L_address").val(msg.address);
                $("#L_phone").val(msg.phone);
            }
        });
    });
    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer;


        //自定义验证规则
        form.verify({
            nikename: function (value, item) { //value：表单的值、item：表单的DOM对象
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test( $("#L_username").val())) {
                    return '用户名不能有特殊字符';
                }
                if (/(^\_)|(\__)|(\_+$)/.test( $("#L_username").val())) {
                    return '用户名首尾不能出现下划线\'_\'';
                }
                if (/^\d+\d+\d$/.test( $("#L_username").val())) {
                    return '用户名不能全为数字';
                }
            },
            repass: function (value) {
                if ($('#L_pass').val() != $('#L_repass').val()) {
                    return '两次密码不一致';
                }
                if ($('#L_pass').val() == $('#L_password').val()) {
                    return '新密码和老密码相同请重新输入！';
                }
            }

            ,phone:[/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/,'请输入正确的手机号']
            ,newpass: [/^[\S]{6,12}$/,'密码必须6到12位,且不能出现空格']

        });
        //监听提交
        form.on('submit(save)', function () {
            var repwd = $("#L_repass").val(); //获取旧名称值
            var id = sessionStorage.getItem("id");
            var username = $("#L_username").val();
            var email = $("#L_email").val();
            var address = $("#L_address").val();
            var phone = $("#L_phone").val();

            //发异步，把数据提交给php
            $.ajax({
                type: "post",
                url: '/taijios/jsp/user/updatePass',
                async: false,//同步提交。不设置则默认异步，异步的话，最后执行ajax
                data: {"repwd": repwd, "id": id,"username":username,"email":email,"address":address,"phone":phone},
                dataType: "json",
                success: function (msg) {
                    if (msg == "1") {
                        layer.alert("修改成功", {icon: 6}, function () {
                            // 获得frame索引
                            var index = parent.layer.getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                        });
                    }
                }
            });
            return false;

        }


        )




    });
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
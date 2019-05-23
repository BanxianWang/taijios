<!DOCTYPE html>
<html>


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
    <form class="layui-form">
        <div class="layui-form-item">
            <label for="L_username" class="layui-form-label">
                <span class="x-red">*</span>昵称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_username" name="username" disabled="disabled" lay-verify="nikename"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_password" class="layui-form-label">
                <span class="x-red">*</span>密码
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_password" name="password" disabled="disabled" lay-verify="password"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label for="L_email" class="layui-form-label">
                <span class="x-red">*</span>邮箱
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_email" name="email" disabled="disabled" lay-verify="email"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_address" class="layui-form-label">
                <span class="x-red">*</span>地址
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_address" name="address" disabled="disabled" lay-verify="address"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label for="L_phone" class="layui-form-label">
                <span class="x-red">*</span>手机号
            </label>
            <div class="layui-input-inline">
                <input type="text" id="L_phone" name="phone" disabled="disabled" lay-verify="phone"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">
                <span class="x-red">*</span>状态
            </label>
            <div class="layui-input-inline">
                <input type="radio" name="state" value="0" title="已启用"  id="ss" />
                <input type="radio" name="state" value="1"  title="已停用" id="sss" />
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">
            </label>
            <button class="layui-btn" lay-filter="add" lay-submit="">
                返回
            </button>
        </div>
    </form>
</div>
<script>

    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer;

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

                if (msg.state == 0) {
                    $("#ss").attr("checked",true);
                    // $("input:radio[value='0']").attr('checked',true);
                } else {
                    $("#sss").attr("checked",true);
                    //$("input:radio[value='1']").attr('checked',true);
                }
                form.render();

            }
        });

        //监听提交
        form.on('submit(add)', function (data) {
            var index = parent.layer.getFrameIndex(window.name);
            //关闭当前frame
            parent.layer.close(index);
        });


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
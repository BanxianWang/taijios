<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>时光四人组管理系统</title>

    <link rel="stylesheet" href="../../lib/css/pintuer.css">
    <link rel="stylesheet" href="../../lib/css/admin.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="../../lib/js/jquery.js"></script>
    <script src="../../lib/js/pintuer.js"></script>
</head>
<body style="background-color:#f2f9fd;">
<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1><img src="../lib/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />时光四人组管理系统</h1>
    </div>
    <div class="head-l"><a class="button button-little bg-green" href="personnew.html" >个人信息</a>  &nbsp;&nbsp;
        <a class="button button-little bg-red" href="login.html" >
            <span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="leftnav">
    <div class="leftnav-title"><strong><span class="icon-list"></span>菜单列表</strong></div>
    <h2><span class="icon-user"></span>基本设置</h2>
    <ul>
        <li><a href="pass.html" target="right"><span class="icon-caret-right"></span>修改密码</a></li>
        <li><a href="personnew.html" target="right"><span class="icon-caret-right"></span>个人资料</a></li>
    </ul>


    <h2><span class="icon-pencil-square-o"></span>我的项目</h2>
    <ul >
        <li><a href="tableproject.html" target="right"><span class="icon-caret-right"></span>所有项目</a></li>
        <li><a href="saveProject.html" target="right"><span class="icon-caret-right"></span>保存阶段</a></li>
        <li><a href="setupProject.html" target="right"><span class="icon-caret-right"></span>立项阶段</a></li>
        <li><a href="midStage.html" target="right"><span class="icon-caret-right"></span>中期阶段</a></li>
        <li><a href="finishStage.html" target="right"><span class="icon-caret-right"></span>结题阶段</a></li>
        <li><a href="Nopassed.html" target="right"><span class="icon-caret-right"></span>结题未通过</a></li>
    </ul>
    <h2><span class="icon-pencil-square-o"></span>我的成果</h2>
    <ul>
        <li><a href="table.html" target="right"><span class="icon-caret-right"></span>所有成果</a></li>

        <li><a href="listreward.html" target="right"><span class="icon-caret-right"></span>防震减灾成果奖</a></li>
        <li><a href="listpaper.html" target="right"><span class="icon-caret-right"></span>论文</a></li>
        <li><a href="listbook.html" target="right"><span class="icon-caret-right"></span>著作</a></li>
        <li><a href="liststandard.html" target="right"><span class="icon-caret-right"></span>标准</a></li>
        <li><a href="listcopyright.html" target="right"><span class="icon-caret-right"></span>著作权</a></li>
        <li><a href="listthings.html" target="right"><span class="icon-caret-right"></span>新产品</a></li>
        <li><a href="listpatent.html" target="right"><span class="icon-caret-right"></span>专利</a></li>


    </ul>
    <h2><span class="icon-pencil-square-o"></span>常用下载</h2>
    <ul>
        <li><a href="download.html" target="right"><span class="icon-caret-right"></span>文件下载</a></li>
    </ul>
    <h2><span class="icon-pencil-square-o"></span>登陆日志</h2>
    <ul>
        <li><a href="cate.html" target="right"><span class="icon-caret-right"></span>个人日志</a></li>
    </ul>
</div>
<script type="text/javascript">
    $(function(){

        $(".leftnav h2").click(function(){
            $(this).next().slideToggle(200);
            $(this).toggleClass("on");
        })
        $(".leftnav ul li a").click(function(){
            $("#a_leader_txt").text($(this).text());
            $(".leftnav ul li a").removeClass("on");
            $(this).addClass("on");
        })

        $(".icon-home").click(function(){
            $("#a_leader_txt").text("所有公告");
            $(".leftnav ul li a").removeClass("on");
            $(this).addClass("on");
        })

    });


</script>
<ul class="bread">
    <li><a href="info.html" target="right" class="icon-home"> 首页</a></li>
    <li><a href="##" id="a_leader_txt">公告信息</a></li>
</ul>
<div class="admin">
    <iframe scrolling="auto" rameborder="0" src="jsp/user/userlist.jsp" name="right" width="100%" height="100%"></iframe>
    <%-- <h1 style="text-align: center">来了老弟</h1>--%>
</div>
<div style="text-align:center;">
</div>
</body>
</html>
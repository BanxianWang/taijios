<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台系统管理</title>
    <link rel="stylesheet" href="statics/css/font.css">
    <link rel="stylesheet" href="statics/css/xadmin.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="statics/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="statics/js/xadmin.js"></script>

</head>
<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo"><a href="javascript:void(0)">后台管理系统</a></div>
    <div class="left_open">
        <i title="展开左侧栏" class="iconfont">&#xe699;</i>
    </div>
  <%--  <ul class="layui-nav left fast-add" lay-filter="">
        <li class="layui-nav-item">
            <a href="javascript:;">+新增</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a onclick="x_admin_show('资讯','http://www.baidu.com')"><i class="iconfont">&#xe6a2;</i>资讯</a></dd>
                <dd><a onclick="x_admin_show('图片','http://www.baidu.com')"><i class="iconfont">&#xe6a8;</i>图片</a></dd>
                <dd><a onclick="x_admin_show('用户','jsp/admin/adminadd.jsp')"><i class="iconfont">&#xe6b8;</i>用户</a></dd>
            </dl>
        </li>
    </ul>--%>
    <ul class="layui-nav right" lay-filter="">
        <li class="layui-nav-item" id="layui-nav-item">
            <a href="javascript:;">${loginss.username}</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
                <dd><a href="javascript:void(0)">个人信息</a></dd>
                <dd><a href="javascript:void(0)">切换帐号</a></dd>
                <dd><a href="/taijios/loginOut">退出</a></dd>
            </dl>
        </li>
        <li class="layui-nav-item to-index"><a href="javascript:void(0)">前台首页</a></li>
    </ul>

</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>用户管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="jsp/member/memberlist.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>用户列表</cite>

                        </a>
                    </li >
                    <li>
                        <a _href="jsp/member/memberedit.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>用户信息</cite>

                        </a>
                    </li>

                </ul>
            </li>




            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>用户数据</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="jsp/NB.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>数据列表</cite>
                        </a>
                    </li >
                </ul>
            </li>

            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6ce;</i>
                    <cite>系统统计</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="jsp/echarts/echarts1.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>拆线图</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="jsp/echarts/echarts2.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>柱状图</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="jsp/echarts/echarts3.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>地图</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="jsp/echarts/echarts4.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>饼图</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="jsp/echarts/echarts5.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>雷达图</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="jsp/echarts/echarts6.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>k线图</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="jsp/echarts/echarts7.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>热力图</cite>
                        </a>
                    </li>
                    <li>
                        <a _href="jsp/echarts/echarts8.jsp">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>仪表图</cite>
                        </a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
        <ul class="layui-tab-title">
            <li>我的桌面</li>
        </ul>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='jsp/welcome.jsp' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<!-- 右侧主体结束 -->
<!-- 中部结束 -->
<!-- 底部开始 -->
<div class="footer">
    <div class="copyright">Copyright ©2017 All Rights Reserved</div>
</div>
<!-- 底部结束 -->

</body>
</html>
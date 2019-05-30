<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>index</title>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="../statics/css/comon0.css">
</head>
<script>

    $(window).on('load',function () {
        $(".loading").fadeOut()
    })

    $(document).ready(function () {
        var whei = $(window).width()
        $("html").css({ fontSize: whei / 20 })
        $(window).resize(function () {
            var whei = $(window).width()
            $("html").css({ fontSize: whei / 20 })
        });
    });
</script>
<script type="text/javascript" src="../statics/js/echarts.min.js"></script>
<script language="JavaScript" src="../statics/js/js.js"></script>
<body>
<div class="canvas" style="opacity: .2">
    <iframe frameborder="0" src="../statics/js/index.html" style="width: 100%; height: 100%"></iframe>
</div>
<div class="loading">
    <div class="loadbox"> <img src="../statics/images/loading.gif"> 页面加载中... </div>
</div>
<div class="head">
    <h1>全国钛极OS设备情况大数据统计</h1>
    <div class="weather"><!--<img src="images/weather.png"><span>多云转小雨</span>--><span id="showTime"></span></div>

    <script>
        var t = null;
        t = setTimeout(time, 1000);
        function time() {
            clearTimeout(t);
            dt = new Date();
            var y = dt.getFullYear();
            var mt = dt.getMonth() + 1;
            var day = dt.getDate();
            var h = dt.getHours();
            var m = dt.getMinutes();
            var s = dt.getSeconds();
            document.getElementById("showTime").innerHTML = y + "年" + mt + "月" + day + "-" + h + "时" + m + "分" + s + "秒";
            t = setTimeout(time, 1000);
        }

        $(function(){
            $.ajax({
                url: "/taijios/jsp/getSumCount",
                type: "post",
                dataType: "json",
                success: function(data) {
                    $("#sumCount").html(data);
                }
            });
            $.ajax({
                url: "/taijios/jsp/getNowYearCount",
                type: "post",
                dataType: "json",

                success: function(data) {
                    $("#nowCount").html(data);

                }
            });

        })

    </script>


</div>
<div class="mainbox">
    <ul class="clearfix">
        <li>
            <div class="boxall" style="height: 5rem">
                <div class="alltitle">每月新增用户信息</div>
                <div class="allnav" id="echart1"></div>
                <div class="boxfoot"></div>
            </div>
            <div class="boxall" style="height: 5rem">
                <div class="alltitle">拥有设备人数最多的前5个省份</div>
                <div class="allnav" id="echart2"></div>
                <div class="boxfoot"></div>
            </div>

        </li>
        <li>
            <div class="bar">
                <div class="barbox">
                    <ul class="clearfix">
                        <li class="pulll_left counter" id="sumCount"></li>
                        <li class="pulll_left counter" id="nowCount"></li>
                    </ul>
                </div>
                <div class="barbox2">
                    <ul class="clearfix">
                        <li class="pulll_left">总用户数量 </li>
                        <li class="pulll_left">2019年新增用户数量</li>
                    </ul>
                </div>
            </div>
            <div class="map">
                <div class="map1"><img src="../statics/images/lbx.png"></div>
                <div class="map2"><img src="../statics/images/jt.png"></div>
                <div class="map3"><img src="../statics/images/map.png"></div>
                <div class="map4" id="map_1"></div>
            </div>
        </li>
        <li>
            <div class="boxall" style="height:5rem">
                <div class="alltitle">各设备售出情况</div>
                <div class="allnav" id="echart4"></div>
                <div class="boxfoot"></div>
            </div>
            <div class="boxall" style="height: 5rem">
                <div class="alltitle">服务器请求走线图</div>
                <div class="allnav" id="echart5"></div>
                <div class="boxfoot"></div>
            </div>

        </li>
    </ul>
</div>
<div class="back"></div>


<script type="text/javascript" src="../statics/js/china.js"></script>
<script type="text/javascript" src="../statics/js/area_echarts.js"></script>


</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台系统管理</title>
    <link rel="stylesheet" href="../../statics/css/font.css">
    <link rel="stylesheet" href="../../statics/css/xadmin.css">
</head>
<body>
<div class="x-body">

    <!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
    <div id="main" style="width: 100%;height:400px;"></div>

</div>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="//cdn.bootcss.com/echarts/3.3.2/echarts.min.js" charset="utf-8"></script>
<script type="text/javascript">
$(function(){
    bindInfo(document.getElementById('main'));
    getDate()

})
function getDate(){
    $.ajax({
        url: "/jsp/getDate",
        type: "post",
        dataType: "json",

        success: function(data) {
            alert(data);
        }
    });
}

    function bindInfo(dm){
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(dm);

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: ''
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data:['注册人数']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月']
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    name:'注册人数',
                    type:'line',
                    stack: '总量',
                    data:[820, 932, 901, 934, 1290, 1330, 1320,345,2342,3443,456,456]
                }
            ]
        };


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    }
</script>

</body>
</html>
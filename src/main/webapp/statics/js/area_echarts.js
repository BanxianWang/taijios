

//ajax获取各城市用户数量
$.ajax({
    url: "/taijios/jsp/getLocalDistribution",
    type: "post",
    dataType: "json",
    success: function (msg) {
        aa(msg.all);
    }

})


function aa(msg) {

    var max = 0;
    //包含23个城市的数组
    var data =  [{
        name: '江苏',
        value: 0
    },
        {
            name: '天津',
            value: 0
        },
        {
            name: '北京',
            value: 0
        },
        {
            name: '上海',
            value: 0
        },
        {
            name: '河北',
            value: 0
        },
        {
            name: '河南',
            value: 0
        },
        {
            name: '云南',
            value: 0
        },
        {
            name: '湖南',
            value: 0
        },
        {
            name: '安徽',
            value: 0
        },
        {
            name: '山东',
            value: 0
        },
        {
            name: '浙江',
            value: 0
        },
        {
            name: '江西',
            value: 0
        },
        {
            name: '湖北',
            value: 0
        },
        {
            name: '福建',
            value: 0
        },
        {
            name: '贵州',
            value: 0
        },
        {
            name: '广东',
            value: 0
        },
        {
            name: '四川',
            value: 0
        },
        {
            name: '台湾',
            value: 0
        },
        {
            name: '香港',
            value: 0
        },
        {
            name: '澳门',
            value: 0
        },


        {
            name: '云南',
            value: 0
        },
        {
            name: '辽宁',
            value: 0
        },
        {
            name: '黑龙江',
            value: 0
        },
        {
            name: '新疆',
            value: 0
        },
        {
            name: '广西',
            value: 0
        },
        {
            name: '甘肃',
            value: 0
        },
        {
            name: '山西',
            value: 0
        },
        {
            name: '内蒙古',
            value: 0
        },
        {
            name: '陕西',
            value: 0
        },
        {
            name: '吉林',
            value: 0
        },
        {
            name: '青海',
            value: 0
        },
        {
            name: '西藏',
            value: 0
        },

        {
            name: '宁夏',
            value: 0
        },
        {
            name: '海南',
            value: 0
        },
        {
            name: '重庆',
            value: 0
        }

    ];


    //将得到的数据循环放入data数组
    for(var i = 0;i<23;i++){
        for(var j = 0;j<msg.length;j++){
            //当查的数据城市与data名相同时，将值放入
            if(data[i].name==msg[j].city){
                data[i].value = msg[j].num;

                //获取最多用户城市的用户数
                if(data[i].value>max){
                    max = data[i].value;
                }
            }
        }

    }

    var option = {
        //鼠标悬浮时显示的数据
        tooltip: {
            show: true,
            formatter: function (params) {
                return params.name + '：' + params.data['value'] + '人'
            },
        },


        visualMap: {
            type: 'continuous',
            orient: 'horizontal',
            itemWidth: 10,
            itemHeight: 80,
            text: ['高', '低'],
            showLabel: true,
            seriesIndex: [0],
            min: 0,
            max: max,
            inRange: {
                color: ['#6FCF6A', '#FFFD64', '#FF5000']
            },
            textStyle: {
                color: '#7B93A7'
            },
            left: '300',
            bottom: 15
        },
        geo: {
            //roam: true,
            map: 'china',
            left: '322',
            right:'322'
            // label: {
            //     emphasis: {
            //         show: false
            //     }
            // },
            // itemStyle: {
            //     emphasis: {
            //         areaColor: '#fff464'
            //     }
            // }
        },
        series: [{
            name: 'mapSer',
            type: 'map',
            roam: false,
            layoutSize: 100,
            geoIndex: 0,
            label: {
                show: false,
            },
            data: data

        }]
    };
    var myChart = echarts.init(document.getElementById('map_1'));
    myChart.setOption(option);


    //适应窗口比例
    window.addEventListener("resize", function () {
        myChart.resize();
    });

}

// window.onresize = myChart.resize;
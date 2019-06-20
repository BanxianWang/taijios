setTimeout(aa(), 100);

function aa() {
    //数据纯属虚构

    var data = [{
        name: '江苏',
        value: 5000
    },
        {
            name: '天津',
            value: 1000
        },
        {
            name: '北京',
            value: 3000
        },
        {
            name: '上海',
            value: 2000
        },
        {
            name: '河北',
            value: 2000
        },
        {
            name: '河南',
            value: 3000
        },
        {
            name: '云南',
            value: 1000
        },
        {
            name: '湖南',
            value: 2000
        },
        {
            name: '安徽',
            value: 3000
        },
        {
            name: '山东',
            value: 3500
        },
        {
            name: '浙江',
            value: 3000
        },
        {
            name: '江西',
            value: 2000
        },
        {
            name: '湖北',
            value: 2000
        },
        {
            name: '福建',
            value: 2000
        },
        {
            name: '贵州',
            value: 1200
        },
        {
            name: '广东',
            value: 2500
        },
        {
            name: '四川',
            value: 1000
        },
        {
            name: '台湾',
            value: 500
        },
        {
            name: '香港',
            value: 200
        },
        {
            name: '澳门',
            value: 500
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
            name: '贵州',
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
            value: 500
        }

    ];

    var option = {

        tooltip: {
            show: true,
            formatter: function (params) {
                if (params.data['value'] != undefined) {
                    return params.name + '：' + params.data['value'] + '人'
                } else {
                    return params.name + '：0 人'

                }
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
            max: 5000,
            inRange: {
                color: ['#6FCF6A', '#FFFD64', '#FF5000']
            },
            textStyle: {
                color: '#7B93A7'
            },
            left: '300',
            bottom: 30
        },
        geo: {
            // roam: true,
            map: 'china',
            left: '270',
            right:'270'
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


    window.addEventListener("resize", function () {
        myChart.resize();
    });

}

// window.onresize = myChart.resize;
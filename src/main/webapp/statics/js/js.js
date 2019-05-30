
$(function () {
getDate();
getLocalDistribution();
echarts_4();
echarts_5();


})

//每月新增用户情况


function getDate(){
    var myChart =echarts.init(document.getElementById('echart1'));
    $.ajax({
        url: "/jsp/getDate",
        type: "post",
        dataType: "json",

        success: function(data) {
            echarts_1(myChart,data);

        }
    });
}
function echarts_1(myChart,data) {

    option = {
        //  backgroundColor: '#00265f',
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },
        grid: {
            left: '0%',
            top:'10px',
            right: '0%',
            bottom: '4%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            //boundaryGap: false,
            axisLabel:  {
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize:10,
                },
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.2)'
                }

            },

            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月','8月','9月','10月','11月','12月'],

        }, {

            axisPointer: {show: false},
            axisLine: {  show: false},
            position: 'bottom',
            offset: 20,



        }],

        yAxis: [{
            type: 'value',
            axisLabel: {
                //formatter: '{value} %'
                show:true,
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: '12',
                },
            },
            axisTick: {
                show: false,
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: "rgba(255,255,255,.1	)",
                    width: 1,
                    type: "solid"
                },
            },
            splitLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                }
            }
        }],
        series: [
            {
                name: '人数',
                type: 'line',
                smooth: true,
                symbol: 'circle',
                symbolSize: 5,
                showSymbol: false,
                lineStyle: {

                    normal: {
                        color: '#0184d5',
                        width: 2
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(1, 132, 213, 0.4)'
                        }, {
                            offset: 0.8,
                            color: 'rgba(1, 132, 213, 0.1)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#0184d5',
                        borderColor: 'rgba(221, 220, 107, .1)',
                        borderWidth: 12
                    }
                },
                data:data

            },

        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize",function(){
        myChart.resize();
    });
}


//拥有设备人数最多的前5个省份，显示数量
function getLocalDistribution(){
    $.ajax({
        url: "/jsp/getLocalDistribution",
        type: "post",
        dataType: "json",

        success: function(data) {
            echarts_2(data);

        }
    });
}


function echarts_2(data) {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echart2'));

    option = {
        //  backgroundColor: '#00265f',
        tooltip: {
            trigger: 'axis',
            axisPointer: { type: 'shadow'}
        },
        grid: {
            left: '0%',
            top:'10px',
            right: '0%',
            bottom: '4%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            data: data.city,
            axisLine: {
                show: true,
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                    width: 1,
                    type: "solid"
                },
            },

            axisTick: {
                show: false,
            },
            axisLabel:  {
                interval: 0,
                // rotate:50,
                show: true,
                splitNumber: 15,
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: '12',
                },
            },
        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                //formatter: '{value} %'
                show:true,
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: '12',
                },
            },
            axisTick: {
                show: false,
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: "rgba(255,255,255,.1	)",
                    width: 1,
                    type: "solid"
                },
            },
            splitLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                }
            }
        }],
        series: [
            {

                type: 'line',
                data: data.num,
                itemStyle: {
                    normal: {
                        color:'#27d08a',
                        opacity: 1,
                        barBorderRadius: 5,
                    }
                }
            }

        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize",function(){
        myChart.resize();
    });
}



function echarts_5() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echart5'));

    option = {
        //  backgroundColor: '#00265f',
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            }
        },

        grid: {
            left: '0%',
            top:'10px',
            right: '0%',
            bottom: '2%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月','8月','9月','10月','11月','12月'],
            axisLine: {
                show: true,
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                    width: 1,
                    type: "solid"
                },
            },

            axisTick: {
                show: false,
            },
            axisLabel:  {
                interval: 0,
                // rotate:50,
                show: true,
                splitNumber: 15,
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: '12',
                },
            },
        }],
        yAxis: [{
            type: 'value',
            axisLabel: {
                //formatter: '{value} %'
                show:true,
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: '12',
                },
            },
            axisTick: {
                show: false,
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: "rgba(255,255,255,.1	)",
                    width: 1,
                    type: "solid"
                },
            },
            splitLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)",
                }
            }
        }],
        series: [{
            type: 'line',
            data: [2, 3, 3, 9, 15, 12, 6, 4, 6, 7, 4, 10],
            barWidth:'35%', //柱子宽度
            // barGap: 1, //柱子之间间距
            itemStyle: {
                normal: {
                    color:'#2f89cf',
                    opacity: 1,
                    barBorderRadius: 5,
                }
            }
        }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize",function(){
        myChart.resize();
    });
}


//各个模块卖出数量，按月累增
function echarts_4() {
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('echart4'));

    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                lineStyle: {
                    color: '#dddc6b'
                }
            }
        },
        legend: {
            top:'0%',
            data:['温度模块','湿度模块'],
            textStyle: {
                color: 'rgba(255,255,255,.5)',
                fontSize:'12',
            }
        },
        grid: {
            left: '10',
            top: '30',
            right: '10',
            bottom: '10',
            containLabel: true
        },

        xAxis: [{
            type: 'category',
            boundaryGap: false,
            axisLabel:  {
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize:12,
                },
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.2)'
                }

            },

            data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月','8月','9月','10月','11月','12月'],

        }, {

            axisPointer: {show: false},
            axisLine: {  show: false},
            position: 'bottom',
            offset: 20,



        }],

        yAxis: [{
            type: 'value',
            axisTick: {show: false},
            axisLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.1)'
                }
            },
            axisLabel:  {
                textStyle: {
                    color: "rgba(255,255,255,.6)",
                    fontSize:12,
                },
            },

            splitLine: {
                lineStyle: {
                    color: 'rgba(255,255,255,.1)'
                }
            }
        }],
        series: [
            {
                name: '温度模块',
                type: 'line',
                smooth: true,
                symbol: 'circle',
                symbolSize: 5,
                showSymbol: false,
                lineStyle: {

                    normal: {
                        color: '#0184d5',
                        width: 2
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(1, 132, 213, 0.4)'
                        }, {
                            offset: 0.8,
                            color: 'rgba(1, 132, 213, 0.1)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#0184d5',
                        borderColor: 'rgba(221, 220, 107, .1)',
                        borderWidth: 12
                    }
                },
                data: [3, 4, 3, 4, 3, 4, 3, 6, 2, 4, 2, 4,3, 4, 3, 4, 3, 4, 3, 6, 2, 4, 2, 4]

            },
            {
                name: '湿度模块',
                type: 'line',
                smooth: true,
                symbol: 'circle',
                symbolSize: 5,
                showSymbol: false,
                lineStyle: {

                    normal: {
                        color: '#00d887',
                        width: 2
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgba(0, 216, 135, 0.4)'
                        }, {
                            offset: 0.8,
                            color: 'rgba(0, 216, 135, 0.1)'
                        }], false),
                        shadowColor: 'rgba(0, 0, 0, 0.1)',
                    }
                },
                itemStyle: {
                    normal: {
                        color: '#00d887',
                        borderColor: 'rgba(221, 220, 107, .1)',
                        borderWidth: 12
                    }
                },
                data: [5, 3, 5, 6, 1, 5, 3, 5, 6, 4, 6, 4, 8, 3, 5, 6, 1, 5, 3, 7, 2, 5, 1, 4]

            },

        ]

    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    window.addEventListener("resize",function(){
        myChart.resize();
    });
}

		
		
		


		










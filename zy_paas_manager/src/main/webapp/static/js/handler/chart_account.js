﻿/**
 * 客户图表-加载界面图表JS
 * 
 * @author allen.yuan
 * @date 2016-11-28
 */
var ChartLoader = {
	params : {
		accountType : 0, // 账号类型
		apiAccount: '',// 账号
		businessId: '-1',//业务Id
		monitorType: 0,// 监控类型
		monitorDate: ''// 监控时间
	},
	checkData: function(){

		// 构建查询条件
		this.params.apiAccount = $('#apiAccount').val();
		this.params.businessId = $('#businessId').val();
		this.params.monitorType= $('#monitorType').val(); 
		this.params.monitorDate= $('#monitorDate').val();
		 
		if(empty(this.params.monitorDate)){
		   layer.tips('请选择监控时间.', '#monitorDate');
		   return false;
		}
	},
	loadData: function(pageNum) {
		
		this.checkData();
		var loadUrl = '/chart/queryAccountChartData';
		$.ajax({
			  type: 'POST',
			  url: loadUrl,
	          dataType: 'json', 
			  data: {'params' : JSON.stringify(this.params)},
			  success: function(data) {
				 initChart(data);//初始化报表数据
	          }
	   });
	}
};

$(function() {
	
	// 点击加载图表
	$('#btnchart').click(function() { 
		ChartLoader.loadData();
	});
	
	ChartLoader.loadData();//首次加载数据
	
	// 每隔5分自动调用方法，实现图表的实时更新  
    window.setInterval('ChartLoader.loadData()',1000*60*5);   
});

function initChart(_data){
	var line = document.getElementById('line-chart');    
    var echart = echarts.init(line ,'macarons');
    
    var serieArrays ;
    if(_data.seriesList.length == 3){// 只有B路
    	serieArrays = new Array(3);
    	serieArrays[0]= {
                name: _data.seriesList[0].name,
                type:'line',
                data: _data.seriesList[0].data,
                markPoint : {
                    data : [
                        {type : 'max', name: '最高值'},
                        {type : 'min', name: '最低值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
        };
    	serieArrays[1]= {
                name: _data.seriesList[1].name,
                type:'line',
                data: _data.seriesList[1].data,
                markPoint : {
                    data : [
                        {type : 'max', name: '最高值'},
                        {type : 'min', name: '最低值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
        };
    	serieArrays[2]= {
                name: _data.seriesList[2].name,
                type:'line',
                data: _data.seriesList[2].data,
                markPoint : {
                    data : [
                        {type : 'max', name: '最高值'},
                        {type : 'min', name: '最低值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
        }
    }
    if(_data.seriesList.length == 6){// 有A,B路
    	serieArrays = new Array(6);
    	serieArrays[0]= {
                name: _data.seriesList[0].name,
                type:'line',
                data: _data.seriesList[0].data,
                markPoint : {
                    data : [
                        {type : 'max', name: '最高值'},
                        {type : 'min', name: '最低值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
        };
    	serieArrays[1]= {
                name: _data.seriesList[1].name,
                type:'line',
                data: _data.seriesList[1].data,
                markPoint : {
                    data : [
                        {type : 'max', name: '最高值'},
                        {type : 'min', name: '最低值'}
                    ]

                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
        };
    	serieArrays[2]= {
                name: _data.seriesList[2].name,
                type:'line',
                data: _data.seriesList[2].data,
                markPoint : {
                    data : [
                        {type : 'max', name: '最高值'},
                        {type : 'min', name: '最低值'}
                    ]

                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
        };
    	serieArrays[3]= {
                name: _data.seriesList[3].name,
                type:'line',
                data: _data.seriesList[3].data,
                markPoint : {
                    data : [
                        {type : 'max', name: '最高值'},
                        {type : 'min', name: '最低值'}
                    ]

                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
        };
    	serieArrays[4]= {
                name: _data.seriesList[4].name,
                type:'line',
                data: _data.seriesList[4].data,
                markPoint : {
                    data : [
                        {type : 'max', name: '最高值'},
                        {type : 'min', name: '最低值'}
                    ]

                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
        };
    	serieArrays[5]= {
                name: _data.seriesList[5].name,
                type:'line',
                data: _data.seriesList[5].data,
                markPoint : {
                    data : [
                        {type : 'max', name: '最高值'},
                        {type : 'min', name: '最低值'}
                    ]

                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
        }
    }
    var option = {
    	    title : {
    	        text: _data.title.text,
    	        subtext: _data.title.subtext
    	    },
    	    tooltip : {
    	        trigger: 'axis'
    	    },
    	    legend: {
    	        data: _data.legends.legend
    	    },
    	    toolbox: {
    	        show : true,
    	        right : 16,
    	        feature : {
    	            mark : {show: true},
    	            dataView : {show: false, readOnly: false},
    	            magicType : {show: true, type: ['line', 'bar']},
    	            restore : {show: true},
    	            saveAsImage : {show: true}
    	        }
    	    },
    	    calculable : true,
    	    xAxis : [
    	        {
    	            type : 'category',
    	            boundaryGap : false,
    	            axisLabel:{
    	            	rotate:45,//倾斜度 -90 至 90 默认为0  
                        margin:5, 
    	                interval:5 //0：表示全部显示不间隔；auto:表示自动根据刻度个数和宽度自动设置间隔个数
    	            },
    	            data : _data.axis.xAxis
    	        }
    	    ],
    	    grid: { // 控制图的大小，调整下面这些值就可以，
                x: 80,
                x2: 100,
                y2: 150,// y2可以控制 X轴跟Zoom控件之间的间隔，避免以为倾斜后造成 label重叠到zoom上
            },
    	    yAxis : [
    	        {    
    	            type : 'value',
    	            axisLabel : {
    	                formatter: '{value} '+_data.axis.yAxisUnit
    	            }
    	        }
    	    ],
    	    series : serieArrays 
    	};
    
    window.onresize = echart.resize;    
    echart.setOption(option);    
}
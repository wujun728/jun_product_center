$(function () {
	/*var token = CoreUtil.getData("access_token");
	console.log(token);
	if(token == undefined){
		window.location = 'login.html';
	}
	var tokenQuery = token.replace("#", "%23");
	console.log(tokenQuery);*/
	
  dataArr =[];
  dataLen = 0,dataLen2 = 0;
  flag = false;
  getData();
});
// 获取最外层json数据
function getData() {
  $.ajax({
    type:'get',
    url:'/public/getAllProjectList?t='+(new Date()).valueOf(),
    //url:'./configInfo/config.json?authorization=' + tokenQuery,
    success:function (res) {
        dataLen =res.data.length;
        res.data.sort(function (a, b) {
          return parseInt(b.actualStartTime.replace(/\//g, ''), 10) - parseInt(a.actualStartTime.replace(/\//g, ''), 10);//降序
        });
      res.data.forEach(function(item,index){
        getData1(item.url,dataLen,index)
      });

    }
  });

}
// 获取个项目json数据
function getData1 (item,dataLen,index) {

  $.ajax({
    type:'get',
    url:'./public/getAllProjectDetail/'+item+'?t='+(new Date()).valueOf(),
    //url:'./configInfo/'+item+'?authorization=' + tokenQuery,
    success:function (res) {
      dataLen2++;
      var jsonUrl= item;
      renderData (res,jsonUrl);
      dataArr.push(res);
      if( dataLen == dataLen2) {
        getEchartData(dataArr);
        statusClick(dataArr);
        getPersonData(dataArr);
      }
    }
  });

}
//拼接html代码
function renderData (res,jsonUrl)  {
	var process = getProcess(res.schedule.estimatedStartTime,res.schedule.estimatedEndTime);
  var oneMonth = monthDiff(res.schedule.actualStartTime);
  var num=0;
  if(res.resources.affiliate.length === 1 && !res.resources.affiliate[0]){
    num = 1
  }else {
    num=res.resources.affiliate.length + 1;
  }
  var html = '<li class="list-li">'
              +'<span class="down " onclick="clickArrow(this)"> <i></i></span>';
//              +'<a class="icon iconfont icon-github1 edit-json" href="https://github.com/configInfo/'+jsonUrl+'" title="编辑" target="_blank"> <i></i></a>'
              if(res.schedule.delay){
                html += '<span class="delay"></span><i class="delay-txt">延期</i>'
              }
              if(res.base.url){
                if(res.base.mobile) {
                  html += '<div class="title-wrapper clearfix row"><a class="title-txt  col-lg-5 col-md-5 col-sm-6 col-xs-9" href="http://192.168.1.6:8124/phoneView.html?url=" target="_blank"><span class="fl">' + res.base.name + '<span class="num">(' + num + '人)</span></span>'
                }else {
                  html += '<div class="title-wrapper clearfix row" id="projectNameDiv"><a class="title-txt  col-lg-5 col-md-5 col-sm-6 col-xs-9"   onclick="clickProjectName(\''+res.base.url+'\')"    > <span class="fl">' + res.base.name + '<span class="num">(' + num + '人)</span></span>'
//                  html += '<div class="title-wrapper clearfix row"><a class="title-txt  col-lg-5 col-md-5 col-sm-6 col-xs-9" href="#"  onclick="clickProjectName("' + res.base.url + '")"  ><span class="fl">' + res.base.name + '<span class="num">(' + num + '人)</span></span>'
                }
              }else {
                html+='<div class="title-wrapper title-wrapper-none-url clearfix row"><a class="title-txt  col-lg-5 col-md-5 col-sm-6 col-xs-9 " >'+res.base.name+'<span class="num">('+num+'人)</span>';
              }
              if(res.base.mobile) {
                html +='<i></i></a>'
              }else {
                html +='</a>'
              }
            if (res.schedule.status == '未启动'){
              html += '<span class="status  developing col-lg-2 col-md-2  col-sm-2  col-xs-3 "><i></i>'+res.schedule.status+'</span>'
            }else if (res.schedule.status == '进行中') {
              html += '<span class="status  measured col-lg-2 col-md-2  col-sm-2  col-xs-3"><i></i>'+res.schedule.status+'</span>'
            }else if (res.schedule.status == '已完成') {
              html += '<span class="status  finished col-lg-2 col-md-2  col-sm-2  col-xs-3"><i></i>'+res.schedule.status+'</span>'
            }else if (res.schedule.status == '挂起') {
              html += '<span class="status  frozen col-lg-2 col-md-2  col-sm-2  col-xs-3"><i></i>'+res.schedule.status+'</span>'
            }else if (res.schedule.status == '审核中') {
              html += '<span class="status  frozen col-lg-2 col-md-2  col-sm-2  col-xs-3"><i></i>'+res.schedule.status+'</span>'
            }
            if((res.schedule.process + 20) < process && (process < 100)){
              html +='<div class="process-wrapper  col-lg-4 col-md-4 col-sm-4 col-xs-12 ">'
                +'<div class="row">'
                +'<span class="process-txt fl col-lg-5 col-md-5 col-sm-6 col-xs-4">进度：'+res.schedule.process+'%</span>'
                +'<div class="process fl col-lg-7 col-md-7 col-sm-6 col-xs-8"><span class="abnormal" style="width:'+res.schedule.process+'%;"></span></div>'
                +'</div>'
                +'</div>';
            }else {
              html +='<div class="process-wrapper  col-lg-4 col-md-4  col-sm-4 col-xs-12">'
                +'<div class="row">'
                +'<span class="process-txt fl col-lg-5 col-md-5 col-sm-6 col-xs-4">进度：'+res.schedule.process+'%</span>'
                +'<div class="process fl col-lg-7 col-md-7 col-sm-6 col-xs-8"><span class="normal" style="width:'+res.schedule.process+'%;"></span></div>'
                +'</div>'
                +'</div>';
            }
          html +='</a></div>';
                    html +='<div class="details-wrapper clearfix">'

            html +='<ul class="details clearfix row">'
                    +'<li class="col-lg-5 col-md-5  col-sm-5 col-xs-12">'
                      +'<ul class="row details-ul">'
                          +'<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12">'
                            +'<span class="l-title">负责人：</span>'
                            +'<span class="r-con">'+res.resources.charge+'</span>'
                          +'</li>'
                          +'<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12">';
                      if(res.resources.affiliate.length > 4){
                        html+='<span class="l-title">参与人：</span>'
                              +'<span class="r-con tooltip-show" data-toggle="tooltip" data-placement="bottom" title="'+res.resources.affiliate+'">'+res.resources.affiliate.slice(0,4)+'...'+'</span>'
                      }else {
                        html+='<span class="l-title">参与人：</span>'
                              +'<span class="r-con">'+res.resources.affiliate+'</span>'
                      }
                      html +='</li>'
                            +'<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12">'
                              +'<span class="l-title l-title-demander">项目经理：</span>'
                              +'<span class="r-con">'+res.resources.manager+'</span>'
                            +'</li>'
                            +'<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12">'
                              +'<span class="l-title">项目类别：</span>'
                              +'<span class="r-con">'+res.resources.type+'</span>'
                            +'</li>'

                      +'</ul>'
                    +'</li>'
                    +'<li class="col-lg-3 col-md-3  col-sm-3  col-xs-12">'
                      +'<ul class="row details-ul">'
                          +'<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12">'
                            +'<span class="l-title">task总数：</span>'
                            +'<span class="r-con">'+res.bug.total+'</span>'
                          +'</li>'
                          +'<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12">'
                            +'<span class="l-title">已完成：</span>'
                            +'<span class="r-con">'+res.bug.resolved+'</span>'
                          +'</li>'
                          +'<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12">'
                            +'<span class="l-title">未完成：</span>'
                            +'<span class="r-con">'+res.bug.unsolved+'</span>'
                          +'</li>'
                          +'<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12">'
                            +'<span class="l-title">合同金额：</span>'
                            +'<span class="r-con tech-wrapper">';

                      var icons ={
                        'jquery':'icon-jquery',
                        'html5':'icon-h5',
                        'h5':'icon-h5',
                        'css3':'icon-css',
                        '百度地图':'icon-baiduditu',
                        'map':'icon-baiduditu',
                        'bootstrap':'icon-bootstrap',
                        'layui':'icon-layer',
                        'element-ui':'icon-eleme',
                        'element':'icon-eleme',
                        'elementui':'icon-eleme',
                        'mint-ui':'icon-yezi',
                        'mintui':'icon-yezi',
                        'mint':'icon-yezi',
                        'threejs':'icon-ThreeJs',
                        'd3':'icon-D',
                        'echart':'icon-echart',
                        'gulp':'icon-gulp',
                        'webpack':'icon-webpack1',
                        'less':'icon-less',
                        'sass':'icon-sass',
                        'handle':'icon-qishihorseman1',
                        'node.js':'icon-nodejs1',
                        'node':'icon-nodejs1',
                        'angular.js':'icon-angularjs',
                        'angularjs':'icon-angularjs',
                        'angular':'icon-angularjs',
                        'font-awesome':'icon-font-awesome',
                        'awesome':'icon-font-awesome',
                        'iconfont':'',
                        'vue':'icon-vuejs',
                        'vue.js':'icon-vuejs',
                        'vuejs':'icon-vuejs',
                        'react.js':'icon-react',
                        'reactjs':'icon-react',
                        'react':'icon-react',
                        'cms':'icon-cms',
                        'CMS':'icon-cms',
                        'svg':'icon-svg1160608easyiconnet'
                      };
                        if(res.schedule.technology) {
//                          res.schedule.technology.forEach(function (item) {
//                            html +='<a class="tech-icon iconfont '+icons[item.toLowerCase()]+'" title="'+item+'"></a>';
							html +='<a>'+res.schedule.money+'</a>';
//                          })
                        }


                        html+='</span>'
                          +'</li>';


            html +='</ul>'
                    +'</li>'
                    +'<li class="col-lg-4 col-md-4  col-sm-4 col-xs-12">'
                      +'<ul class="row details-ul">'
                        +'<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12">'
                          +'<span class="l-title">预计开始时间：</span>'
                          +'<span class="r-con">'+res.schedule.estimatedStartTime+'</span>'
                        +'</li>'
                        +'<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12 li-padding">'
                          +'<span class="l-title">预计结束时间：</span>'
                          +'<span class="r-con">'+res.schedule.estimatedEndTime+'</span>'
                        +'</li>'
                        +'<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12">'
                          +'<span class="l-title">实际开始时间：</span>'
                          +'<span class="r-con">'+res.schedule.actualStartTime+'</span>'
                        +'</li>'
                        +'<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12 li-padding">';
                  if(!res.schedule.actualEndTime){
                    html +='<span class="l-title">实际结束时间：</span>'
                      +'<span class="r-con">未定</span>'
                  }else{
                    html +='<span class="l-title">实际结束时间：</span>'
                      +'<span class="r-con">'+res.schedule.actualEndTime+'</span>'
                  }
                  html+='</li>'
                      +'</ul>'
                    +'</li>'
                    +'<li class="full-li col-lg-12 col-md-12  col-sm-12 col-xs-12">'
                      +'<span class="l-title">客户：</span>'
                      +'<span class="r-con">'+res.resources.demander+'</span>'
                    +'</li>';
                      if(res.base.desc){
                html +='<li class="full-li col-lg-12 col-md-12  col-sm-12 col-xs-12">'
                      +'<span class="l-title">项目介绍：</span>'
                      +'<span class="r-con">'+res.base.desc+'</span>'
                      +'</li>'
                      }else {
                html +='<li class="full-li col-lg-12 col-md-12  col-sm-12 col-xs-12">'
                        +'<span class="l-title">项目介绍：</span>'
                        +'<span class="r-con">暂无</span>'
                      +'</li>'
                      }
                      if(res.others.remark) {
                html +='<li class="full-li col-lg-12 col-md-12  col-sm-12 col-xs-12">'
                      +'<span class="l-title">备注：</span>'
                      +'<span class="r-con">'+res.others.remark+'</span>'
                      +'</li>'
                      }else {
                html +='<li class="full-li col-lg-12 col-md-12  col-sm-12 col-xs-12">'
                          +'<span class="l-title">备注：</span>'
                          +'<span class="r-con">暂无</span>'
                        +'</li>'
                      }
                  html+='</ul>'
                +'</div>'
              +'</li>'
  $('#listWrapper').append(html);

}
//点击下拉按钮展开或收缩
function clickArrow (e) {
    if ($(e).hasClass('active')){
      $(e).removeClass('active');
      $(e).siblings('.details-wrapper').height(0);
    }else {
      $('.down').removeClass('active');
      $('.details-wrapper').height(0);
      var height = $(e).siblings('.details-wrapper').children('.details').height()+ 30;
      $(e).siblings('.details-wrapper').height(height);
      $(e).addClass('active');
    }
}

//根据预计起止时间计算进度条
function getProcess (start, end) {
	var current = new Date().getTime();
	var startTime = new Date(start).getTime();
	var endTime = new Date(end).getTime();
	if (current > endTime ){
		return 100;
	}else {
		return ((current - startTime)/(endTime - startTime) * 100).toFixed(2);
	}
}
// 近一个月的项目默认展开
function monthDiff(time) {
  var today = new Date();
  var year = today.getFullYear();
  var month = today.getMonth();
  var day = today.getDate();
  if(month == 0){
    year = year -1;
    month = 12;
  }
  var prevMonth = year+ '-'+month+'-'+day;
  var prevMonthNum = new Date(prevMonth).getTime();
  var timeNum = new Date(time).getTime();
  if(timeNum > prevMonthNum){
    return true;
  }else return false;
}




// 获取echart所需数据
function getEchartData(data){
  var develop=0,measured=0,finished=0,frozen=0,suspend=0;
  var developArr=[],measuredArr=[],finishedArr=[],frozenArr=[],suspendArr=[];

  // 未启动项目投入人员-柱图
  var staffInput={
    name:[],
    seriesData:[]
  };
  // 进行中项目bug堆积图
  var bugData={
    name:[],
    resolved:[],
    unsolved:[]
  };

  // 项目类型-饼图
  var projectType={
    // typeName:['评估','审计','涉税','造价','代理记账','招标代理'],
    // seriesData:[0,0,0,0,0,0]
    seriesData:[
      {name:'评估',value:0},
      {name:'审计',value:0},
      {name:'涉税',value:0},
      {name:'造价',value:0},
      {name:'代理记账',value:0},
      {name:'招标代理',value:0}
    ]
  };
  data.forEach(function(item,i){
    var data=[];
    if(item.schedule.status == '未启动'){
      develop += 1;
      developArr.push(item.base.name)
      staffInput.name.push(item.base.name);
      if(item.resources.affiliate.length === 1 && !item.resources.affiliate[0]){
        staffInput.seriesData.push({status:'未启动',name:item.base.name,value: 1,value1:item.resources.charge})
      }else{
        staffInput.seriesData.push({status:'未启动',name:item.base.name,value:item.resources.affiliate.length + 1,value1:item.resources.charge+','+item.resources.affiliate.join(',')})
      }
    }else if(item.schedule.status == '进行中') {
      measured += 1;
      measuredArr.push(item.base.name)
      bugData.name.push(item.base.name);
      bugData.resolved.push(item.bug.resolved);
      bugData.unsolved.push(item.bug.unsolved);
      staffInput.name.push(item.base.name);
      if(item.resources.affiliate.length === 1 && !item.resources.affiliate[0]){
        staffInput.seriesData.push({status:'进行中',name:item.base.name,value: 1,value1:item.resources.charge})
      }else{
        staffInput.seriesData.push({status:'进行中',name:item.base.name,value:item.resources.affiliate.length + 1,value1:item.resources.charge+','+item.resources.affiliate.join(',')})
      }
      // staffInput.seriesData.push({status:'进行中',name:item.base.name,value:item.resources.affiliate.length + 1,value1:item.resources.charge+','+item.resources.affiliate.join(',')})

    }else if(item.schedule.status == '已完成') {
      finished += 1;
      finishedArr.push(item.base.name)
    }else if(item.schedule.status == '挂起') {
      frozen += 1;
      frozenArr.push(item.base.name)
    }else if(item.schedule.status == '审核中') {
      suspend += 1;
      suspendArr.push(item.base.name)
    }

    if(item.resources.type == '评估'){
      projectType.seriesData[0].value++;
    }else if(item.resources.type == '审计') {
      projectType.seriesData[1].value++;
    }else if(item.resources.type == '涉税') {
      projectType.seriesData[2].value++;
    }else if(item.resources.type == '造价') {
      projectType.seriesData[3].value++;
    }else if(item.resources.type == '代理记账') {
      projectType.seriesData[4].value++;
    }else if(item.resources.type == '招标代理') {
      projectType.seriesData[5].value++;
    }

  })
  // 项目状态-饼图
  var projectEchartData={
    seriesData:[
      {name:'未启动',value:develop,value1:developArr},
      {name:'进行中',value:measured,value1:measuredArr},
      {name:'已完成',value:finished,value1:finishedArr},
      {name:'挂起',value:frozen,value1:frozenArr},
      {name:'审核中',value:suspend,value1:suspendArr}
    ]
  };
  projectEchartData.seriesData.forEach(function(item,i){
    if(!item.value){
      projectEchartData.seriesData.splice(i,1);
    }
  })
  projectType.seriesData.forEach(function(item,i){
    if(!item.value){
      projectType.seriesData.splice(i,1);
    }
  })
  staffInput.seriesData.sort(function(a,b){
    return a.value - b.value;
  })
  chart1(projectEchartData.seriesData)
  chart2(staffInput)
  chart3 (bugData)
  chart4(projectType.seriesData)
}
//echarts随浏览器变化样式重置
function echartsResize(obj) {
  window.addEventListener("resize", function () {
    var time = null;
    clearTimeout(time);
    time = setTimeout(function () {
      obj.resize();
    }, 100);
  });
}
// 项目状态-饼图
function chart1(data){
  var myChart = echarts.init(document.getElementById('chart1'));
  myChart.setOption({
    tooltip: {
      trigger: 'item',
      confine:true,
      formatter: function(params){
        var html='';
        var len = params.data.value1.length;
        params.data.value1.sort(function(a,b){
          return b.length-a.length;
        })
        return html='<div>'
                        +'<div><span>'+params.name+'：</span><span>'+params.value+'个</span></div>'
                        +'<div>'
                            +'<span style="float:left;">项目列表：</span>'
                            +'<p style="float:left;">'+params.data.value1.join('<br/>')+'</p>'
                        +'</div>'
                    +'</div>'

      }
    },
    series: [
      {
        name:'项目状态',
        type:'pie',
        center:['50%','50%'],
        radius: ['28%', '43%'],
        label: {
          normal: {
            position: 'outside',
            show: true,
            formatter: "{b}:{c}个",
            textStyle: {
              fontSize:14
            }
          }
        },
        itemStyle: {
          normal: {
            color: function(params) {
              var colorList = ['#6ac73b','#5ea8fd','#f85812','#ff9900','#8e72fa'];
              return colorList[params.dataIndex]
            },
            borderColor:'#fff',
            borderWidth:2
          }
        },
        data:data
      }
    ]
  })
  echartsResize(myChart)
}

// 人员投入-柱状图
function chart2(data) {
  var yData = [];
  data.seriesData.forEach(function(item){
    yData.push(item.name)
  })
  var i=0,j=0;
  data.seriesData.forEach(function(item){
   if(item.status == '未启动'){
     i += item.value;
     item.itemStyle={
       normal:{
         color:'#3dbc75'
       }
     }
   }else if(item.status == '进行中'){
     j += item.value;
     item.itemStyle={
       normal:{
         color:'#66ccff'
       }
     }
   }
  })
  $('.develop-num').text(i)
  $('.measured-num').text(j)
  var myChart = echarts.init(document.getElementById('chart2'));
  myChart.setOption({
    tooltip : {
      trigger: 'axis',
      confine:true,
      axisPointer : {
        type : 'shadow',
        shadowStyle:{
          color:'rgba(200,200,200,.5)'
        }
      },
      formatter: function(params){
        return params[0].name+'：'+params[0].value+'人'+'<br/>'
                +'参与人：'+params[0].data.value1
      },
      padding:10
    },
    grid: {
      left: 20,
      right: 20,
      bottom: 30,
      top: 50,
      containLabel: true
    },
    xAxis :
      {
        type : 'value',
        max:data.seriesData[data.seriesData.length -1].value + 3,
        axisTick: {
          show:false
        },
        axisLabel:{
          textStyle:{
            color:'#555',
            fontSize:14
          }
        },
        axisLine:{
          lineStyle:{
            color:'#ccc',
            width:1
          }
        },
        splitLine:{
          show:false
        }
      },
    yAxis :
      {
        type : 'category',
        data : yData,
        axisTick: {
          show:false
        },
        axisLabel:{
          formatter:function(params){
            if(params.length > 5) {
              return params.slice(0,5)+'...'
            }else {
              return params
            }
          },
          textStyle:{
            color:'#555'
          }
        },
        axisLine:{
          lineStyle:{
            color:'#ccc',
            width:1
          }
        },
        splitLine:{
          show:false
        }
      },
    series : [
      {
        name:'项目人员投入 ',
        type:'bar',
        barWidth:'50%',
        label:{
          normal:{
            show:true,
            position:'right',
            formatter:function(params){
              return params.value+'人'
            },
            textStyle:{
            }
          }
        },
        data:data.seriesData
      }
    ]
  })
  echartsResize(myChart)
}
// 进行中项目bug情况统计
function chart3 (data) {
  var myChart = echarts.init(document.getElementById('chart3'));
  myChart.setOption({
    tooltip : {
      trigger: 'axis',
      confine:true,
      axisPointer : {
        type : 'shadow',
        shadowStyle:{
          color:'rgba(200,200,200,.5)'
        }
      },
      formatter: function(params){
        return params[0].name+'<br/>'
                +'task总数：'+(params[0].value+ params[1].value)+'个'+'<br/>'
                +params[0].seriesName+'：'+params[0].value+'个'+'<br/>'
                +params[1].seriesName+'：'+params[1].value+'个'
      }
    },
    legend: {
      right:10,
      top: 10,
      itemWidth:10,
      itemHeight:10,
      icon:'circle',
      data:['已解决','未解决']
    },
    color:["#00aeff","#ff9900"],
    grid: {
      left: 10,
      right: 10,
      bottom: 50,
      top: 30,
      containLabel: true
    },
    xAxis:  {
      type: 'category',
      axisLine:{
        lineStyle:{
          color:'#ccc',
          width:1
        }
      },
      axisTick:{show: false},
      axisLabel:{
        interval:0,
        rotate:45,
        textStyle:{
          color:'#555',
          fontSize:12
        },
        formatter:function(params){
          if(params.length > 4){
            return params.slice(0,4)+'...'
          }else {
            return params
          }
        }
      },
      data: data.name
    },
    yAxis:[
      {
        type: 'value',
        max: 300,
        axisLine:{
          lineStyle:{
            color:'#ccc',
            width:1
          }
        },
        splitLine:{show: false},
        axisLabel:{
          textStyle:{
            color:'#555'
          }
        }
      }
    ],
    series: [
      {
        name: '已解决',
        type: 'bar',
        stack: '总量',
        barWidth:'30%',
        label: {
          normal: {
            show: false,
            position: 'insideRight'
          }
        },
        data: data.resolved
      },
      {
        name: '未解决',
        type: 'bar',
        stack: '总量',
        label: {
          normal: {
            show: false,
            position: 'insideRight'
          }
        },
        data: data.unsolved
      }
    ]
  })
  echartsResize(myChart)
}
// 项目类型统计情况-饼图
function chart4(data){
  var myChart = echarts.init(document.getElementById('chart4'));
  myChart.setOption({
    tooltip: {
      trigger: 'item',
      confine:true,
      formatter: "{a} <br/>{b}: {c}个 ({d}%)"
    },
    legend: {
      show:false
    },
    color:['#6ac73b','#5ea8fd','#f85812','#ff9900','#8e72fa'],
    series: [
      {
        name:'项目类型',
        type:'pie',
        radius: '55%',
        roseType:'angle',
        data:data
      }
    ]
  })
  echartsResize(myChart)
}
// 筛选条件点击筛选
function statusClick(dataArr){
  dataArr.sort(function (a, b) {
    return parseInt(b.schedule.actualStartTime.replace(/-/g, ''), 10) - parseInt(a.schedule.actualStartTime.replace(/-/g, ''), 10);//降序
  });
  var value = '全部',value1= '全部';
  var statusArrData=[],typeArrData=[];
  $('.status-list span').click(function () {
    var statusArr=[];
    $('#listWrapper').html('');
    $(this).addClass('active').siblings().removeClass('active');
    value = $(this).text();

    if (typeArrData.length > 0) {
      if(value !== '全部') {
        typeArrData.forEach(function(item,index){
          if(item.schedule.status == value) {
            statusArr.push(item)
          }
        })
        statusArrData = statusArr;
        if (statusArr.length > 0){
          statusArr.sort(function (a, b) {
            return parseInt(b.schedule.actualStartTime.replace(/-/g, ''), 10) - parseInt(a.schedule.actualStartTime.replace(/-/g, ''), 10);//降序
          });
          statusArr.forEach(function(item,index){
            renderData (item)
          })
        }else {
          var html = '<li><p class="no-result">没有符合条件的数据！</p></li>';
          $('#listWrapper').append(html);
        }
      }else {
        statusArrData = dataArr;
        typeArrData.sort(function(a,b){
          return parseInt(b.schedule.actualStartTime.replace(/-/g, ''), 10) - parseInt(a.schedule.actualStartTime.replace(/-/g, ''), 10);//降序
        })
        typeArrData.forEach(function(item,index){
          renderData (item)
        })
      }
    }else {
      if(value !== '全部') {
        dataArr.forEach(function(item,index){
          if(item.schedule.status == value) {
            statusArr.push(item)
          }
        })
        statusArrData = statusArr;
        if (statusArr.length > 0){
          statusArr.sort(function (a, b) {
            return parseInt(b.schedule.actualStartTime.replace(/-/g, ''), 10) - parseInt(a.schedule.actualStartTime.replace(/-/g, ''), 10);//降序
          });
          statusArr.forEach(function(item,index){
            renderData (item)
          })
        }else {
          var html = '<li><p class="no-result">没有符合条件的数据！</p></li>';
          $('#listWrapper').append(html);
        }
      }else {
        statusArrData = dataArr;
        if (value1 == '全部'){
          dataArr.forEach(function(item,index){
            renderData (item)
          })
        }
      }
    }
  });
  $('.type-list span').click(function () {
    var typeArr=[];
    $('#listWrapper').html('');
    $(this).addClass('active').siblings().removeClass('active');
    value1 = $(this).text();

    if(statusArrData.length > 0){
      if(value1 !== '全部') {
        statusArrData.forEach(function(item,index){
          if(item.resources.type == value1) {
            typeArr.push(item)
          }
        })
        typeArrData = typeArr;
        if (typeArr.length > 0){
          typeArr.sort(function (a, b) {
            return parseInt(b.schedule.actualStartTime.replace(/-/g, ''), 10) - parseInt(a.schedule.actualStartTime.replace(/-/g, ''), 10);//降序
          });
          typeArr.forEach(function(item,index){
            renderData (item)
          })
        }else {
          var html = '<li><p class="no-result">没有符合条件的数据！</p></li>';
          $('#listWrapper').append(html);
        }
      }else {
        typeArrData = dataArr;
        statusArrData.sort(function (a, b) {
          return parseInt(b.schedule.actualStartTime.replace(/-/g, ''), 10) - parseInt(a.schedule.actualStartTime.replace(/-/g, ''), 10);//降序
        });
        statusArrData.forEach(function(item,index){
          renderData (item)
        })
      }
    }else {
      if(value1 !== '全部') {
        dataArr.forEach(function(item,index){
          if(item.resources.type == value1) {
            typeArr.push(item)
          }
        })
        typeArrData = typeArr;
        if (typeArr.length > 0){
          typeArr.sort(function (a, b) {
            return parseInt(b.schedule.actualStartTime.replace(/-/g, ''), 10) - parseInt(a.schedule.actualStartTime.replace(/-/g, ''), 10);//降序
          });
          typeArr.forEach(function(item,index){
            renderData (item)
          })
        }else {
          var html = '<li><p class="no-result">没有符合条件的数据！</p></li>';
          $('#listWrapper').append(html);
        }
      }else {
        typeArrData = dataArr;
        if(value == '全部'){
          dataArr.forEach(function(item,index){
            renderData (item)
          })
        }

      }
    }
  });


}

//获取人员分配情况数据
function getPersonData (data) {
  var person = [
    {
      name:'group0',
      data:['余梅','王钶','汪小菊','雷婷','高双华','黄银平','吴亚娟','余佩','何俊','王璇','陈满红','吴韵','吴佳乐']
    },
    {
      name:'group1',
      data:['郑霞','李诗帆','金波','姚佳']
    },
    {
      name:'group2',
      data:['宋坤','叶靖','郑军']
    },
    {
      name:'group3',
      data:['罗凯','殷杰','陈巍升','方熙希','谢枫','杨露','叶增鑫']
    },
    {
      name:'group4',
      data:['纪爱君','邱静','王丹','孙礼文','陶慧','吴莎']
    }
    ,
    {
      name:'group5',
      data:['刘建勋','梁倩','雷丽莎','蔡紫薇','龙谦','徐君晗','陈璐','孙佳颖','艾和英']
    }
  ]
  var person1 = [];
  var groupPerson = {
    group0:{
      data1:[],
      data2:[]
    },
    group1:{
      data1:[],
      data2:[]
    },
    group2:{
      data1:[],
      data2:[]
    },
    group3:{
      data1:[],
      data2:[]
    },
    group4:{
      data1:[],
      data2:[]
    },
    group5:{
      data1:[],
      data2:[]
    }
  }
  //获取所有参与项目的人员
  data.forEach(function(item, index){
    if(person1.indexOf(item.resources.charge) === -1){
      person1.push(item.resources.charge)
    }
    if(item.resources.affiliate.length > 0 && item.resources.affiliate[0]){
      item.resources.affiliate.forEach(function(item,index){
        if(person1.indexOf(item) === -1){
          person1.push(item)
        }
      })
    }
  })
  var len = person1.length,len1 = person.length;
  //参与项目人员分组
  for(var i = 0; i < len;i++){
    for(var j = 0;j < len1;j++){
      for(var m = 0; m < person[j].data.length; m++){
        if(person1[i] == person[j].data[m]){
          groupPerson[person[j].name].data1.push(person1[i])
        }
      }
    }
  }
  //未参与参与项目人员分组
  groupPerson.group0.data2=compare(groupPerson.group0.data1,person[0].data);
  groupPerson.group1.data2=compare(groupPerson.group1.data1,person[1].data);
  groupPerson.group2.data2=compare(groupPerson.group2.data1,person[2].data);
  groupPerson.group3.data2=compare(groupPerson.group3.data1,person[3].data);
  groupPerson.group4.data2=compare(groupPerson.group4.data1,person[4].data);
  groupPerson.group5.data2=compare(groupPerson.group5.data1,person[5].data);
  var chart5Data = [
    {name:'审计部',value:person[0].data.length,value1:groupPerson.group0.data1,value2:groupPerson.group0.data2},
    {name:'绩效评价部',value:person[1].data.length,value1:groupPerson.group1.data1,value2:groupPerson.group1.data2},
    {name:'造价部',value:person[2].data.length,value1:groupPerson.group2.data1,value2:groupPerson.group2.data2},
    {name:'评估部',value:person[3].data.length,value1:groupPerson.group3.data1,value2:groupPerson.group3.data2},
    {name:'行政部',value:person[4].data.length,value1:groupPerson.group4.data1,value2:groupPerson.group4.data2},
    {name:'管理咨询部',value:person[5].data.length,value1:groupPerson.group5.data1,value2:groupPerson.group5.data2},
  ]
  console.log(chart5Data);
  chart5(chart5Data)
}
// 筛选出未参与项目的人员
function compare(arr1,arr2){
  var temp = []; //临时数组1
  var temparray = [];//临时数组2
  for (var i = 0; i < arr1.length; i++) {
    temp[arr1[i]] = true;
  }
  for (var j = 0; j < arr2.length; j++) {
    if (!temp[arr2[j]]) {
      temparray.push(arr2[j]);
    }
  }
  return temparray;
}

// 部门人员分配情况
function chart5(data){
  var myChart = echarts.init(document.getElementById('chart5'));
  myChart.setOption({
    tooltip: {
      trigger: 'item',
      formatter: function(params){

        if(params.data.value2.length > 0){
          return params.data.name +'：'+ params.data.value+'人'+'<br/>'
            + '已投入('+params.data.value1.length+'人)：'+params.data.value1+'<br/>'
            + '未投入('+params.data.value2.length+'人)：'+params.data.value2
        }else {
          return params.data.name +'：'+ params.data.value+'人'+'<br/>'
          + '已投入('+params.data.value1.length+'人)：'+params.data.value1
        }


      }
    },
    backgroundColor:'#fff',

    color:['#6ac73b','#5ea8fd','#f85812','#ff9900','#8e72fa'],
    series: [
      {
        name:'人员分配',
        type:'pie',
        radius:[0,'58%'],
        label:{
          normal:{
            formatter:'{b}:{c}人'
          }
        },
        data:data
      }
    ]
  })
  echartsResize(myChart)
}

function drawChart(data) {
  var teams = ['审计部',  '绩效评价部',  '造价部',  '评估部',  '行政部',  '管理咨询部'];
  var persons = [
    { name: "余梅", team: 0 },
{ name: "王钶", team: 0 },
{ name: "汪小菊", team: 0 },
{ name: "雷婷", team: 0 },
{ name: "高双华", team: 0 },
{ name: "黄银平", team: 0 },
{ name: "吴亚娟", team: 0 },
{ name: "余佩", team: 0 },
{ name: "何俊", team: 0 },
{ name: "王璇", team: 0 },
{ name: "陈满红", team: 0 },
{ name: "吴韵", team: 0 },
{ name: "吴佳乐", team: 0 },
{ name: "郑霞", team: 1 },
{ name: "李诗帆", team: 1 },
{ name: "金波", team: 1 },
{ name: "姚佳", team: 1 },
{ name: "宋 坤", team: 2 },
{ name: "叶靖", team: 2 },
{ name: "郑军", team: 2 },
{ name: "罗凯", team: 3 },
{ name: "殷杰", team: 3 },
{ name: "陈巍升", team: 3 },
{ name: "方熙希", team: 3 },
{ name: "谢枫", team: 3 },
{ name: "杨露", team: 3 },
{ name: "叶增鑫", team: 3 },
{ name: "纪爱君", team: 4 },
{ name: "邱静", team: 4 },
{ name: "王丹", team: 4 },
{ name: "孙礼文", team: 4 },
{ name: "陶慧", team: 4 },
{ name: "吴莎", team: 4 },
{ name: "刘建勋", team: 5 },
{ name: "梁倩", team: 5 },
{ name: "雷丽莎", team: 5 },
{ name: "蔡紫薇", team: 5 },
{ name: "龙谦", team: 5 },
{ name: "徐君晗", team: 5 },
{ name: "陈璐", team: 5 },
{ name: "孙佳颖", team: 5 },
{ name: "艾和英", team: 5 }
];
  var projects = [];
  data.forEach(function (value, index) {
    if (value.schedule.status == '进行中' || value.schedule.status == '未启动') {
      var obj = {
        persons: []
      };
      obj.name = value.base.name;
      obj.persons = value.resources.affiliate;
      obj.persons.push(value.resources.charge);
      projects.push(obj);
    }
  });
  // 封装一些配置参数
  var datas = persons.map(p => {
    return {
      name: p.name,
      symbolSize: 10,
      category: p.team
    }
  });
  datas.push(...projects.map((p, i) => {
    return {
      name: p.name,
      symbolSize: 20 + p.persons.length * 5,
      category: teams.length
    }
  }));
  var links = [];
  projects.forEach(pro => {
    links.push(...pro.persons.map(per => {
      return {
        source: pro.name,
        target: per,
        value: 200
      }
    }))
  });
  var categories = teams.map(team => {
    return {
      name: team
    }
  });
  categories.push(...projects.map(p => {
    return {
      name: p.name
    }
  }));

  // ECharts配置参数
  var option = {
    legend: {
      data: teams
    },
    color:['#6ac73b','#5ea8fd','#f85812','#ff9900','#8e72fa','#00aeff'],
    series: [{
      name: 'Les Miserables',
      type: 'graph',
      layout: 'force',
      data: datas,
      links: links,
      categories: categories,
      roam: true,
      draggable: true,
      animation: true,
      focusNodeAdjacency: true,
      label: {
        normal: {
          show: true,
          position: 'right'
        }
      },
      force: {
        initLayout: 'force',
        edgeLength: [30, 80],
        repulsion: 150
      }
    }]
  };
  // 画图
  var myChart = echarts.init(document.getElementById('chart_force'));
  myChart.setOption(option);
  // 事件
  myChart.on('legendselectchanged', function (params) {
    var groupList=[
      ['李猛','郭惠敏','汝银娟','胡杰','彭庆凯','闫磊','张涛','周志国'],
      ['王帅','邢玮','杜万福','吉亚峰','林源','任传真','商业庆','魏阁','杨杰','杨羽珂'],
      ['魏彬','陈胜','褚甜甜','郭志敏','李鹏飞','柳杨','邵金东','温兴月'],
      ['杨勇冠','王杰','杨微','冯彦文','王斌彦','郑梦丽'],
      ['任新杰','韩凯波','冯红阳','李妮','吕颖萍','徐媛媛','颜庭光']
    ];
    var allProjects=datas.slice(persons.length);
    var selectedProjects=new Set();
    var showProjects=[];

    var opt = myChart.getOption();

    opt.legend[0].data.forEach(function(value,index){
      if (params.selected[value]) {
        var mapGroup=new Set();
        var g=groupList[index];
        opt.series[0].links.forEach(function(value,index){
          if(g.includes(value.target)){
            mapGroup.add(value.source);
          }
        });
        allProjects.forEach(function(value,index){
          if(mapGroup.has(value.name)){
            selectedProjects.add(value);
          }
        });
      }
    });
    showProjects=Array.from(selectedProjects); 
    var person=opt.series[0].data.slice(0,40);
    var newData=person.concat(showProjects);
    opt.series[0].data=newData;

    myChart.setOption(opt);
  });
  myChart.setOption(option);

}

$('#force-modal').on('click',function(){
  $('#myModal').modal();
  drawChart(dataArr);
})

$('#projectNameDiv').on('click',function(){
   alert("pid url info");
})

var layer ;
layui.use('layer', function(){
    layer = layui.layer;
});

function clickProjectName(pid) {
        CoreUtil.setData("data_doProjectDetail",pid);
        console.log("pid="+pid);
        layer.open({
            type: 2,
            skin: 'layui-layer-admin',
            title: "项目明细",
            shadeClose : false,
            area: ['80%', '80%'], //宽高
            shade: 0.6, //遮罩透明度
            maxmin: true, //允许全屏最小化
            anim: 1, //0-6的动画形式，-1不开启
            content:  '/table/doProjectDetailV2.html',
        });
};



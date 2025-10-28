$(function () {

  dataArr =[];
  dataLen = 0,dataLen2 = 0;
  flag = false;
  getData();
});
// 获取最外层json数据
function getData() {
  $.ajax({
    type:'get',
    url:'./configInfo/config.json?t='+(new Date()).valueOf(),
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
    url:'./configInfo/'+item+'?t='+(new Date()).valueOf(),
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
              +'<span class="down " onclick="clickArrow(this)"> <i></i></span>'
              +'<a class="icon iconfont icon-github1 edit-json" href="https://github.com/jusfoun-FE/projectManage/edit/master/configInfo/'+jsonUrl+'" title="编辑" target="_blank"> <i></i></a>';
              if(res.schedule.delay){
                html += '<span class="delay"></span><i class="delay-txt">延期</i>'
              }
              if(res.base.url){
                if(res.base.mobile) {
                  html += '<div class="title-wrapper clearfix row"><a class="title-txt  col-lg-5 col-md-5 col-sm-6 col-xs-9" href="http://192.168.1.6:8124/phoneView.html?url=' + res.base.url + '" target="_blank"><span class="fl">' + res.base.name + '<span class="num">(' + num + '人)</span></span>'
                }else {
                  html += '<div class="title-wrapper clearfix row"><a class="title-txt  col-lg-5 col-md-5 col-sm-6 col-xs-9" href="' + res.base.url + '" target="_blank"><span class="fl">' + res.base.name + '<span class="num">(' + num + '人)</span></span>'
                }
              }else {
                html+='<div class="title-wrapper title-wrapper-none-url clearfix row"><a class="title-txt  col-lg-5 col-md-5 col-sm-6 col-xs-9 " >'+res.base.name+'<span class="num">('+num+'人)</span>';
              }
              if(res.base.mobile) {
                html +='<i></i></a>'
              }else {
                html +='</a>'
              }
            if (res.schedule.status == '开发中'){
              html += '<span class="status  developing col-lg-2 col-md-2  col-sm-2  col-xs-3 "><i></i>'+res.schedule.status+'</span>'
            }else if (res.schedule.status == '已提测') {
              html += '<span class="status  measured col-lg-2 col-md-2  col-sm-2  col-xs-3"><i></i>'+res.schedule.status+'</span>'
            }else if (res.schedule.status == '已完成') {
              html += '<span class="status  finished col-lg-2 col-md-2  col-sm-2  col-xs-3"><i></i>'+res.schedule.status+'</span>'
            }else if (res.schedule.status == '已冻结') {
              html += '<span class="status  frozen col-lg-2 col-md-2  col-sm-2  col-xs-3"><i></i>'+res.schedule.status+'</span>'
            }else if (res.schedule.status == '暂停开发') {
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
                            +'<span class="l-title">bug总数：</span>'
                            +'<span class="r-con">'+res.bug.total+'</span>'
                          +'</li>'
                          +'<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12">'
                            +'<span class="l-title">已解决：</span>'
                            +'<span class="r-con">'+res.bug.resolved+'</span>'
                          +'</li>'
                          +'<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12">'
                            +'<span class="l-title">未解决：</span>'
                            +'<span class="r-con">'+res.bug.unsolved+'</span>'
                          +'</li>'
                          +'<li class="col-lg-12 col-md-12 col-sm-12 col-xs-12">'
                            +'<span class="l-title">使用技术：</span>'
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
                          res.schedule.technology.forEach(function (item) {
                            html +='<a class="tech-icon iconfont '+icons[item.toLowerCase()]+'" title="'+item+'"></a>';
                          })
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
                      +'<span class="l-title">需求方：</span>'
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

  // 开发中项目投入人员-柱图
  var staffInput={
    name:[],
    seriesData:[]
  };
  // 已提测项目bug堆积图
  var bugData={
    name:[],
    resolved:[],
    unsolved:[]
  };

  // 项目类型-饼图
  var projectType={
    // typeName:['合资公司','独立纵队','总部平台','内部研发平台'],
    // seriesData:[0,0,0,0]
    seriesData:[
      {name:'合资公司',value:0},
      {name:'独立纵队',value:0},
      {name:'总部平台',value:0},
      {name:'内部研发平台',value:0}
    ]
  };
  data.forEach(function(item,i){
    var data=[];
    if(item.schedule.status == '开发中'){
      develop += 1;
      developArr.push(item.base.name)
      staffInput.name.push(item.base.name);
      if(item.resources.affiliate.length === 1 && !item.resources.affiliate[0]){
        staffInput.seriesData.push({status:'开发中',name:item.base.name,value: 1,value1:item.resources.charge})
      }else{
        staffInput.seriesData.push({status:'开发中',name:item.base.name,value:item.resources.affiliate.length + 1,value1:item.resources.charge+','+item.resources.affiliate.join(',')})
      }
    }else if(item.schedule.status == '已提测') {
      measured += 1;
      measuredArr.push(item.base.name)
      bugData.name.push(item.base.name);
      bugData.resolved.push(item.bug.resolved);
      bugData.unsolved.push(item.bug.unsolved);
      staffInput.name.push(item.base.name);
      if(item.resources.affiliate.length === 1 && !item.resources.affiliate[0]){
        staffInput.seriesData.push({status:'已提测',name:item.base.name,value: 1,value1:item.resources.charge})
      }else{
        staffInput.seriesData.push({status:'已提测',name:item.base.name,value:item.resources.affiliate.length + 1,value1:item.resources.charge+','+item.resources.affiliate.join(',')})
      }
      // staffInput.seriesData.push({status:'已提测',name:item.base.name,value:item.resources.affiliate.length + 1,value1:item.resources.charge+','+item.resources.affiliate.join(',')})

    }else if(item.schedule.status == '已完成') {
      finished += 1;
      finishedArr.push(item.base.name)
    }else if(item.schedule.status == '已冻结') {
      frozen += 1;
      frozenArr.push(item.base.name)
    }else if(item.schedule.status == '暂停开发') {
      suspend += 1;
      suspendArr.push(item.base.name)
    }

    if(item.resources.type == '合资公司'){
      projectType.seriesData[0].value++;
    }else if(item.resources.type == '独立纵队') {
      projectType.seriesData[1].value++;
    }else if(item.resources.type == '总部平台') {
      projectType.seriesData[2].value++;
    }else if(item.resources.type == '内部研发平台') {
      projectType.seriesData[3].value++;
    }

  })
  // 项目状态-饼图
  var projectEchartData={
    seriesData:[
      {name:'开发中',value:develop,value1:developArr},
      {name:'已提测',value:measured,value1:measuredArr},
      {name:'已完成',value:finished,value1:finishedArr},
      {name:'已冻结',value:frozen,value1:frozenArr},
      {name:'暂停开发',value:suspend,value1:suspendArr}
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
   if(item.status == '开发中'){
     i += item.value;
     item.itemStyle={
       normal:{
         color:'#3dbc75'
       }
     }
   }else if(item.status == '已提测'){
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
// 已提测项目bug情况统计
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
                +'bug总数：'+(params[0].value+ params[1].value)+'个'+'<br/>'
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
      data:['李猛','郭惠敏','汝银娟','胡杰','彭庆凯','闫磊','张涛','周志国']
    },
    {
      name:'group1',
      data:['王帅','邢玮','杜万福','吉亚峰','林源','任传真','商业庆','魏阁','杨杰','杨羽珂']
    },
    {
      name:'group2',
      data:['魏彬','陈胜','褚甜甜','郭志敏','李鹏飞','柳杨','邵金东','温兴月']
    },
    {
      name:'group3',
      data:['杨勇冠','王杰','杨微','冯彦文','王斌彦','郑梦丽']
    },
    {
      name:'group4',
      data:['任新杰','韩凯波','冯红阳','李妮','吕颖萍','徐媛媛','颜庭光']
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
  var chart5Data = [
    {name:'公共组',value:person[0].data.length,value1:groupPerson.group0.data1,value2:groupPerson.group0.data2},
    {name:'项目一组',value:person[1].data.length,value1:groupPerson.group1.data1,value2:groupPerson.group1.data2},
    {name:'项目二组',value:person[2].data.length,value1:groupPerson.group2.data1,value2:groupPerson.group2.data2},
    {name:'项目三组',value:person[3].data.length,value1:groupPerson.group3.data1,value2:groupPerson.group3.data2},
    {name:'项目四组',value:person[4].data.length,value1:groupPerson.group4.data1,value2:groupPerson.group4.data2},
  ]
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
  var teams = ['公共组', '一组', '二组', '三组', '四组'];
  var persons = [{
    name: "闫磊",
    team: 0
  }, {
    name: "彭庆凯",
    team: 0
  }, {
    name: "胡杰",
    team: 0
  }, {
    name: "周志国",
    team: 0
  }, {
    name: "郭惠敏",
    team: 0
  }, {
    name: "汝银娟",
    team: 0
  }, {
    name: "李猛",
    team: 0
  }, {
    name: "张涛",
    team: 0
  }, {
    name: "杜万福",
    team: 1
  }, {
    name: "吉亚峰",
    team: 1
  }, {
    name: "魏阁",
    team: 1
  }, {
    name: "王帅",
    team: 1
  }, {
    name: "商业庆",
    team: 1
  }, {
    name: "任传真",
    team: 1
  }, {
    name: "杨羽珂",
    team: 1
  }, {
    name: "杨杰",
    team: 1
  }, {
    name: "邢玮",
    team: 1
  }, {
    name: "林源",
    team: 1
  }, {
    name: "冯彦文",
    team: 3
  }, {
    name: "王斌彦",
    team: 3
  }, {
    name: "杨勇冠",
    team: 3
  }, {
    name: "王杰",
    team: 3
  }, {
    name: "杨微",
    team: 3
  }, {
    name: "郑梦丽",
    team: 3
  }, {
    name: "柳杨",
    team: 2
  }, {
    name: "陈胜",
    team: 2
  }, {
    name: "邵金东",
    team: 2
  }, {
    name: "褚甜甜",
    team: 2
  }, {
    name: "郭志敏",
    team: 2
  }, {
    name: "温兴月",
    team: 2
  }, {
    name: "李鹏飞",
    team: 2
  }, {
    name: "魏彬",
    team: 2
  }, {
    name: "任新杰",
    team: 4
  }, {
    name: "冯红阳",
    team: 4
  }, {
    name: "韩凯波",
    team: 4
  }, {
    name: "李妮",
    team: 4
  }, {
    name: "颜庭光",
    team: 4
  }, {
    name: "吕颖萍",
    team: 4
  }, {
    name: "徐媛媛",
    team: 4
  }];
  var projects = [];
  data.forEach(function (value, index) {
    if (value.schedule.status == '已提测' || value.schedule.status == '开发中') {
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



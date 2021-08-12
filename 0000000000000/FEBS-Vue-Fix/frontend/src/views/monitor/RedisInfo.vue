<template>
  <div style="width: 100%;margin-top: 1rem">
    <a-row :gutter="8">
      <a-col :span="12">
        <div  id="lineChartMemory" style="height: 350px;" ></div>
      </a-col>
      <a-col :span="12">
        <div  id="lineChartKey" style="height: 350px;" ></div>
      </a-col>
    </a-row>
    <a-row :gutter="8">
      <a-divider orientation="left">Redis详细信息</a-divider>
      <table style="border-bottom: 1px solid #f1f1f1;">
        <tr v-for="(info, index) in redisInfo" :key="index" style="border-top: 1px solid #f1f1f1;">
          <td style="padding: .7rem 1rem">{{info.key}}</td>
          <td style="padding: .7rem 1rem">{{info.description}}</td>
          <td style="padding: .7rem 1rem">{{info.value}}</td>
        </tr>
      </table>
    </a-row>
  </div>
</template>
<script>
import axios from 'axios'
import moment from 'moment'

export default {
  name: 'RedisInfo',
  data () {
    return {
      loading: true,
      redisInfo: [],
      timer: null,
      categoriesOne: [],
      dataOne: [],
      chartOne: '',
      categoriesTwo: [],
      dataTwo: [],
      chartTwo: ''
    }
  },
  beforeDestroy () {
    if (this.timer) {
      clearInterval(this.timer)
    }
  },
  mounted () {
    this.getData()
    this.timer = setInterval(() => {
      this.getData()
    }, 3000)
    this.$get('redis/info').then((r) => {
      this.redisInfo = r.data.data
    })
  },
  methods: {
    getData () {
      if (this.$route.path.indexOf('redis') !== -1) {
        axios.all([
          this.$get('redis/keysSize'),
          this.$get('redis/memoryInfo')
        ]).then((r) => {
          let currentMemory = r[1].data.used_memory / 1000
          let currentSize = r[0].data.dbSize
          let time = moment().format('hh:mm:ss')
          this.dataOne.push(currentMemory)
          this.categoriesOne.push(time)
          this.dataTwo.push(currentSize)
          this.categoriesTwo.push(time)
          if (this.dataOne.length >= 6) {
            this.categoriesOne.shift()
            this.dataOne.shift()
          }
          if (this.dataTwo.length >= 6) {
            this.categoriesTwo.shift()
            this.dataTwo.shift()
          }
          this.lineChartOne('lineChartMemory')
          this.lineChartTwo('lineChartKey')
          if (this.loading) {
            this.loading = false
          }
        }).catch((r) => {
          this.loading = false
          console.error(r)
          this.$message.error('获取Redis信息失败')
          if (this.timer) {
            clearInterval(this.timer)
          }
        })
      }
    },
    lineChartOne (chartId) {
      this.chartOne = this.$echarts.init(document.getElementById(chartId))
      this.chartOne.setOption({
        color: ['#1890ff', '#fcc550', '#1890ff', '#ff9933', '#fa541c', '#42b983', '#722ed1', '#60FFF0', '#52c41a', '#42b983'],
        title: {
          text: 'Redis内存实时占用情况（kb）',
          show: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'line'
          }
        },
        legend: false,
        xAxis: {
          type: 'category',
          boundaryGap: true,
          data: this.categoriesOne,
          axisLabel: {
            textStyle: {
              fontSize: '12'
            }
          }
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            textStyle: {
              fontSize: '12'
            }
          }
        },
        series: [{
          name: '内存(kb)',
          data: this.dataOne,
          type: 'line',
          areaStyle: {}
        }]
      }, true)
    },
    lineChartTwo (chartId) {
      this.chartTwo = this.$echarts.init(document.getElementById(chartId))
      this.chartTwo.setOption({
        color: ['#f5564e', '#fcc550', '#1890ff', '#ff9933', '#fa541c', '#42b983', '#722ed1', '#60FFF0', '#52c41a', '#42b983'],
        title: {
          text: 'Redis key实时数量（个）',
          show: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'line'
          }
        },
        legend: false,
        xAxis: {
          type: 'category',
          boundaryGap: true,
          data: this.categoriesTwo,
          axisLabel: {
            textStyle: {
              fontSize: '12'
            }
          }
        },
        yAxis: {
          type: 'value',
          axisLabel: {
            textStyle: {
              fontSize: '12'
            }
          }
        },
        series: [{
          name: 'key数量',
          data: this.dataTwo,
          type: 'line',
          areaStyle: {}
        }]
      }, true)
    }
  }
}
</script>
<style>

</style>

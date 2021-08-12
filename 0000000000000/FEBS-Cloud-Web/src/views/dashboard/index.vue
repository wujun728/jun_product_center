<template>
  <div class="main">
    <el-row :gutter="10">
      <el-col :xs="24" :sm="12">
        <div class="app-container" style="height: 120px">
          <div class="user-wrapper">
            <div class="user-header">
              <img alt="avatar" :src="avatar">
            </div>
            <div class="user-info">
              <div class="random-message">
                {{ welcomeMessage }}
              </div>
              <div class="user-dept">
                <span>{{ user.deptName ? user.deptName : $t('common.noDept') }}</span> | <span>{{ user.roleName ? user.roleName : $t('common.noRole') }}</span>
              </div>
              <div class="user-login-info">
                {{ $t('common.lastLoginTime') }}：<span id="last-login-time">{{ user.lastLoginTime ? user.lastLoginTime : $t('common.firstLogin') }}</span>
              </div>
            </div>
          </div>
        </div>
      </el-col>
      <el-col :xs="24" :sm="4">
        <div class="app-container" style="height: 120px;padding: 0">
          <el-card :body-style="{ padding: '0px' }" shadow="never" style="height: 120px">
            <div class="count-header">
              <img alt="" :src="resolveIcon('count1.svg')">
              <span class="des">{{ $t('common.todayIp') }}</span>
              <countTo class="countTo todayIp" :start-val="0" :end-val="todayIp" :duration="3000" />
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :xs="24" :sm="4">
        <div class="app-container" style="height: 120px;padding: 0">
          <el-card :body-style="{ padding: '0px' }" shadow="never" style="height: 120px">
            <div class="count-header">
              <img alt="" :src="resolveIcon('count2.svg')">
              <span class="des">{{ $t('common.todayVisit') }}</span>
              <countTo class="countTo todayVisit" :start-val="0" :end-val="todayVisit" :duration="3000" />
            </div>
          </el-card>
        </div>
      </el-col>
      <el-col :xs="24" :sm="4">
        <div class="app-container" style="height: 120px;padding: 0">
          <el-card :body-style="{ padding: '0px' }" shadow="never" style="height: 120px">
            <div class="count-header">
              <img alt="" :src="resolveIcon('count3.svg')">
              <span class="des">{{ $t('common.TotalVisit') }}</span>
              <countTo class="countTo totalVisit" :start-val="0" :end-val="totalVisit" :duration="3000" />
            </div>
          </el-card>
        </div>
      </el-col>
    </el-row>
    <el-row :gutter="10">
      <el-col :xs="24" :sm="12">
        <div class="app-container">
          <div id="visit-count-chart" style="width: 100%;height: 370px" />
        </div>
      </el-col>
      <el-col :xs="24" :sm="12">
        <div class="app-container">
          <el-table
            :data="server"
            border
            class="server-table"
            style="width: 100%"
          >
            <el-table-column
              prop="name"
              label="服务名"
            />
            <el-table-column
              prop="port"
              label="端口"
            >
              <template slot-scope="scope">
                <el-tag
                  :type="scope.row.id % 2 === 0 ? 'primary' : 'success'"
                  disable-transitions
                >
                  {{ scope.row.port }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column
              prop="description"
              label="描述"
            />
          </el-table>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
import echarts from 'echarts'
import { parseTime } from '@/utils'
import countTo from 'vue-count-to'
import resize from '@/components/Charts/mixins/resize'

export default {
  name: 'Dashboard',
  components: { countTo },
  filters: {
    portFilter(v) {
      const map = {
        0: 'danger',
        1: 'success'
      }
      return map[status]
    }
  },
  mixins: [resize],
  data() {
    return {
      server: [{
        id: 1,
        name: 'FEBS-Auth',
        port: '8101',
        description: '微服务认证服务器'
      },
      {
        id: 2,
        name: 'FEBS-Gateway',
        port: '8301',
        description: '微服务网关'
      },
      {
        id: 3,
        name: 'FEBS-Server-System',
        port: '8201',
        description: '微服务子系统，系统模块'
      },
      {
        id: 4,
        name: 'FEBS-Server-Test',
        port: '8202',
        description: '微服务子系统，Demo模块'
      },
      {
        id: 5,
        name: 'FEBS-Server-Generator',
        port: '8203',
        description: '微服务子系统，代码生成模块'
      },
      {
        id: 6,
        name: 'FEBS-Server-Job',
        port: '8204',
        description: '微服务子系统，任务调度模块'
      },
      {
        id: 7,
        name: 'FEBS-TX-Manager',
        port: '8501',
        description: '分布式事务控制中心'
      }
      ],
      welcomeMessage: '',
      todayIp: 0,
      todayVisit: 0,
      totalVisit: 0,
      chart: null
    }
  },
  computed: {
    user() {
      return this.$store.state.account.user
    },
    avatar() {
      return require(`@/assets/avatar/${this.user.avatar}`)
    }
  },
  mounted() {
    this.welcomeMessage = this.welcome()
    this.initIndexData()
  },
  methods: {
    resolveIcon(icon) {
      return require(`@/assets/icons/${icon}`)
    },
    welcome() {
      const date = new Date()
      const hour = date.getHours()
      const time = hour < 6 ? this.$t('common.goodMorning') : (hour <= 11 ? this.$t('common.goodMorning') : (hour <= 13 ? this.$t('common.goodAfternoon') : (hour <= 18 ? this.$t('common.goodAfternoon') : this.$t('common.goodEvening'))))
      const welcomeArr = [
        this.$t('common.randomMessage.a'),
        this.$t('common.randomMessage.b'),
        this.$t('common.randomMessage.c'),
        this.$t('common.randomMessage.d'),
        this.$t('common.randomMessage.e'),
        this.$t('common.randomMessage.f'),
        this.$t('common.randomMessage.g'),
        this.$t('common.randomMessage.h'),
        this.$t('common.randomMessage.i')
      ]
      const index = Math.floor((Math.random() * welcomeArr.length))
      return `${time}, ${this.user.username}, ${welcomeArr[index]}`
    },
    initIndexData: function() {
      this.$get('system/user/index').then((r) => {
        const data = r.data.data
        this.todayIp = data.todayIp
        this.totalVisit = data.totalVisitCount
        this.todayVisit = data.todayVisitCount
        const tenVisitCount = []
        const dateArr = []
        for (let i = 10; i >= 0; i--) {
          const time = parseTime(new Date(new Date().getTime() - 24 * 60 * 60 * 1000 * i), '{m}-{d}')
          let contain = false
          for (const o of data.lastTenVisitCount) {
            if (o.days === time) {
              contain = true
              tenVisitCount.push(o.count)
            }
          }
          if (!contain) {
            tenVisitCount.push(0)
          }
          dateArr.push(time)
        }
        const tenUserVisitCount = []
        for (let i = 10; i >= 0; i--) {
          const time = parseTime(new Date(new Date().getTime() - 24 * 60 * 60 * 1000 * i), '{m}-{d}')
          let contain = false
          for (const o of data.lastTenUserVisitCount) {
            if (o.days === time) {
              contain = true
              tenUserVisitCount.push(o.count)
            }
          }
          if (!contain) {
            tenUserVisitCount.push(0)
          }
        }
        this.chart = echarts.init(document.getElementById('visit-count-chart'))
        const option = {
          backgroundColor: '#FFF',
          title: {
            text: this.$t('common.visitTitle') + '\n',
            textStyle: {
              fontSize: 14
            }
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'shadow'
            }
          },
          legend: {
            data: [this.$t('common.you'), this.$t('common.total')],
            top: '18'
          },
          grid: {
            left: '3%',
            right: '5%',
            bottom: '3%',
            containLabel: true,
            show: false
          },
          toolbox: {
            feature: {
              dataView: { show: false, readOnly: false }
            }
          },
          xAxis: {
            type: 'category',
            boundaryGap: true,
            splitLine: {
              show: false
            },
            data: dateArr,
            axisLine: {
              lineStyle: {
                color: '#333'
              }
            }
          },
          yAxis: [
            {
              type: 'value',
              splitLine: {
                lineStyle: {
                  type: 'dashed',
                  color: '#DDD'
                }
              },
              axisLine: {
                show: false,
                lineStyle: {
                  color: '#333'
                }
              },
              nameTextStyle: {
                color: '#999'
              },
              splitArea: {
                show: false
              }
            }],
          series: [
            {
              name: this.$t('common.you'),
              smooth: true,
              type: 'line',
              color: 'rgb(0, 143, 251)',
              areaStyle: {
                color: {
                  type: 'linear',
                  x: 0,
                  y: 0,
                  x2: 0,
                  y2: 1,
                  colorStops: [{
                    offset: 0,
                    color: 'rgba(0, 143, 251, 0.8)'
                  },
                  {
                    offset: 1,
                    color: '#fff'
                  }
                  ],
                  globalCoord: false
                }
              },
              lineStyle: {
                normal: {
                  width: 3
                }
              },
              data: tenUserVisitCount
            },
            {
              name: this.$t('common.total'),
              smooth: true,
              type: 'line',
              color: 'rgba(82, 222, 151, 1)',
              areaStyle: {
                color: {
                  type: 'linear',
                  x: 0,
                  y: 0,
                  x2: 0,
                  y2: 1,
                  colorStops: [{
                    offset: 0,
                    color: 'rgba(82, 222, 151, 0.8)'
                  },
                  {
                    offset: 1,
                    color: '#fff'
                  }
                  ],
                  globalCoord: false
                }
              },
              lineStyle: {
                normal: {
                  width: 3
                }
              },
              data: tenVisitCount
            }
          ]
        }
        this.chart.setOption(option)
      })
    }
  }
}
</script>
<style lang="scss">
  .main {
    .el-card {
      border: none;
      border-radius: 2px;
    }
    .el-table.server-table {
      th {
        height: 2.7rem;
        padding: 7px 0;
        border-right: none;
      }
      td {
        padding: 7px 0;
        border-right: none;
      }
    }
    .count-header {
      padding-left: 1rem;
      height: 120px;
      line-height: 120px;
      text-align: left;
      vertical-align: center;
      img {
        width: 3.8rem;
        margin-top: 1.8rem;
        vertical-align: top;
      }
      span {
        font-size: 15px;
        color: #606266;
        font-weight: 600;
        &.des {
          margin-left: 9px;
        }
      }
    }
    .todayIp {
      color: #27c6da !important;
    }
    .todayVisit {
      color: #fc573b !important;
    }
    .totalVisit {
      color: #be63f9 !important;
    }
  }
</style>
<style lang="scss" scoped>
  .main {
    padding: 10px;
    .app-container {
      margin: 0 0 10px 0;
    }
    .user-container {
      padding: 15px;
    }
    .user-wrapper {
      width: 100%;
      display: inline-block;
      .user-header {
        display: inline-block;
        vertical-align: middle;
        img {
          width: 5rem;
          margin: .5rem 1rem;
          border-radius: 50%
        }
      }
      .user-info {
        display: inline-block;
        vertical-align: middle;
        .random-message {
          font-size: 1rem;
          margin-bottom: .5rem;
          color: #333;
        }
        .user-dept, .user-login-info {
          color: rgba(0, 0, 0, 0.45);
          margin-bottom: .5rem;
          font-size: .9rem;
          line-height: 1.1rem;
        }
      }
    }
    .user-visits {
      text-align: center;
      padding-right: 2rem;
      margin-top: 1rem;
      vertical-align: middle;
      .num {
        font-weight: 600;
      }
    }
    .project-wrapper {
      padding: 0;
      .project-header {
        padding: 18px;
        margin-bottom: 16px;
      }
      table {
        width: 100%;
        border-collapse: collapse;
        td {
          width: 50%;
          border-top: 1px solid #f1f1f1;
          border-bottom: 1px solid #f1f1f1;
          padding: .7rem;
          .project-avatar-wrapper {
            display:inline-block;
            float:left;
            margin-right:.7rem;
            .project-avatar {
              color: #42b983;
              background-color: #d6f8b8;
            }
          }
          &:nth-child(odd) {
            border-right: 1px solid #f1f1f1;
          }
        }
      }
      .project-detail {
        display:inline-block;
        float:left;
        text-align:left;
        width: 75%;
        .project-name {
          font-size:.9rem;
          margin-top:-2px;
          font-weight:600;
        }
        .project-desc {
          color:rgba(0, 0, 0, 0.45);
          p {
            margin: 5px 0 0 0;
            font-size: .85rem;
            line-height: 1.4rem;
            white-space:normal;
          }
        }
      }
    }
    @media screen and (max-width: 1366px)
    {
      .user-info {
        max-width: 25rem;
      }
    }
    @media screen and (max-width: 1300px)
    {
      .user-info {
        max-width: 19rem;
      }
    }

    @media screen and (max-width: 1120px)
    {
      .user-info {
        max-width: 17rem;
      }
    }
  }
</style>

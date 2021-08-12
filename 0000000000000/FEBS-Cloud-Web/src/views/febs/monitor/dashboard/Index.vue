<template>
  <div class="app-container">
    <el-col v-for="(i, index) in pages" :key="index" :xs="12" :sm="6">
      <el-card class="box-card" :style="{background: i.bgc}" @click.native="open(i.url)">
        <div class="text item">
          <div class="left">
            <img :src="resolveIcon(i.icon)" alt="">
          </div>
          <div class="right">
            <span class="header" :style="{color: i.c}">{{ i.name }}</span> <br>
            <span>{{ i.description }}</span>
          </div>
        </div>
      </el-card>
    </el-col>
  </div>
</template>
<script>
import { pages } from '@/settings'

export default {
  name: 'MonitorDashboard',
  data() {
    return {
      pages: [
        {
          icon: 'nacos.svg',
          name: 'Nacos面板',
          url: pages.nacosUrl,
          description: 'Nacos，微服务注册中心&配置中心',
          c: '#1890ff',
          bgc: 'rgba(236,251,252,0.3)'
        },
        {
          icon: 'elk.svg',
          name: 'ELK面板',
          url: pages.kibanaUrl,
          description: 'ELK，微服务日志中心，统一管理日志',
          c: '#1890ff',
          bgc: 'rgba(236,251,252,0.3)'
        },
        {
          icon: 'skywalking.svg',
          name: 'SkyWalking面板',
          url: pages.skywalkingUrl,
          description: 'SkyWalking，追踪微服务请求',
          c: '#1890ff',
          bgc: 'rgba(236,251,252,0.3)'
        },
        {
          icon: 'grafana.svg',
          name: 'Grafana面板',
          url: pages.grafanaUrl,
          description: 'Grafana，监控Redis,Docker,MySQL等',
          c: '#1890ff',
          bgc: 'rgba(236,251,252,0.3)'
        },
        {
          icon: 'doc.svg',
          name: 'Document中心',
          url: pages.docUrl,
          description: 'Knife4j，微服务API接口文档中心',
          c: '#1890ff',
          bgc: 'rgba(236,251,252,0.3)'
        },
        {
          icon: 'admin.svg',
          name: 'Admin面板',
          url: pages.springBootAdminUrl,
          description: 'Admin，基于SBA的微服务监控中心',
          c: '#1890ff',
          bgc: 'rgba(236,251,252,0.3)'
        },
        {
          icon: 'tx.svg',
          name: 'Tx面板',
          url: pages.txUrl,
          description: 'Tx-Manager，分布式事务管理中心',
          c: '#1890ff',
          bgc: 'rgba(236,251,252,0.3)'
        }
      ]
    }
  },
  methods: {
    resolveIcon(icon) {
      return require(`@/assets/icons/${icon}`)
    },
    open(url) {
      window.open(url, '_blank')
    }
  }
}
</script>
<style scoped>
  .item {
    display: flex;
    flex-wrap: wrap;
  }
  .app-container {
    display: inline-block;
    width: 98.6%;
  }
  .box-card {
    margin: 13px;
    cursor: pointer;
  }
  .header {
    font-weight: 600;
    font-size: 1rem;
    display: inline-flex;
    margin-bottom: 8px;
  }
  .left {
    height: 100%;
  }
  img {
    width: 3.4rem;
    display: inline-block;
  }
  .right {
    margin-left: 10px
  }
</style>

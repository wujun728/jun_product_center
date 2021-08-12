<template>
  <div class="app-container">
    <el-input v-model="preCronValue" placeholder="输入 cron 表达式值" >
      <el-button slot="append" type="primary" @click="setCronValue">解析到 UI</el-button>
    </el-input>
    <easy-cron v-model="cronValue" />
  </div>
</template>

<script>
  import InputCron from '@/components/easy-cron-elementui/input-cron'
  import EasyCron from '@/components/easy-cron-elementui/cron-main'
  
  export default {
    name: "cron",
    components: {
      EasyCron,
      InputCron
    },
    data(){
      return {
        cronValue: null,
        preCronValue: null
      }
    },
    methods: {
      setCronValue(){
        this.cronValue = this.preCronValue
      }
    }
  }
</script>

<style scoped>

</style>
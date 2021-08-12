<template>
  <div class="config-list">
    <el-radio-group v-model="type" style="font-size: 12px">
      <div class="item">
        <el-radio label="TYPE_NOT_SET" class="choice" :disabled="disableChoice" size="mini" :border="true">不设置
        </el-radio>
        <span class="tip-info"> 日和周只能设置其中之一</span>
      </div>
      <div class="item">
        <el-radio label="TYPE_RANGE" class="choice" :disabled="disableChoice" size="mini" :border="true"
                  style="margin-right: 10px">区间
        </el-radio>
        从
        <el-select v-model="valueRange.start" :disabled="type!==TYPE_RANGE || disableChoice" style="width: 110px"
                   size="mini">
          <el-option v-for="(v, k) of WEEK_MAP" :value="v" :label="k" :key="`week-pre-Lf13-${v}`">{{ k }}</el-option>
        </el-select>
        至
        <el-select v-model="valueRange.end" :disabled="type!==TYPE_RANGE || disableChoice" style="width: 110px"
                   size="mini">
          <el-option v-for="(v, k) of WEEK_MAP" :value="v" :label="k" :key="`week-next-1fas-${v}`">{{ k }}</el-option>
        </el-select>
      </div>
      <div class="item">
        <el-radio label="TYPE_LOOP" class="choice" :disabled="disableChoice" size="mini" :border="true"
                  style="margin-right: 15px">循环
        </el-radio>
        从
        <el-select v-model="valueLoop.start" :disabled="type!==TYPE_LOOP || disableChoice" style="width: 110px"
                   size="mini">
          <el-option v-for="(v, k) of WEEK_MAP" :value="v" :label="k" :key="`week-pre-Lf13-${v}`">{{ k }}</el-option>
        </el-select>
        至
        <el-select v-model="valueLoop.end" :disabled="type!==TYPE_LOOP || disableChoice" style="width: 110px"
                   size="mini">
          <el-option v-for="(v, k) of WEEK_MAP" :value="v" :label="k" :key="`week-pre-Lf13-${v}`">{{ k }}</el-option>
        </el-select>
        间隔
        <el-input-number :disabled="type!==TYPE_LOOP || disableChoice" :max="maxValue" :min="minValue" :precision="0"
                         v-model="valueLoop.interval" style="width: 85px" size="mini"/>
        天
      </div>
      <div class="item">
        <el-radio label="TYPE_SPECIFY" class="choice" :disabled="disableChoice" size="mini" :border="true">Specific
        </el-radio>
        <div class="list">
          <el-checkbox-group v-model="valueList">
            <el-checkbox class="list-check-item" v-for="(v, k) of WEEK_MAP"
                         :label="v" :key="`key-01jfs-${v}`" :disabled="type!==TYPE_SPECIFY || disableChoice"
                         style="width: 80px">
              <span>{{k}}</span></el-checkbox>
          </el-checkbox-group>
        </div>
      </div>
    </el-radio-group>
  </div>
</template>

<script>
  import mixin from './mixin'
  import {WEEK_MAP_EN, replaceWeekName} from './const.js'

  const WEEK_MAP = {
    'Sunday': 0,
    'Monday': 1,
    'Tuesday': 2,
    'Wednesday': 3,
    'Thursday': 4,
    'Friday': 5,
    'Saturday': 6
  }

  export default {
    name: 'week',
    mixins: [mixin],
    props: {
      day: {
        type: String,
        default: '*'
      }
    },
    data() {
      return {
        WEEK_MAP,
        WEEK_MAP_EN
      }
    },
    computed: {
      disableChoice() {
        // default: '?'  modify by henry @20190620 week: '?', replace ? to *
        return (this.day && this.day !== '?') || this.disabled
        // return (this.day && this.day !== '*') || this.disabled
      }
    },
    watch: {
      value_c(newVal, oldVal) {
        // 如果设置日，那么星期就直接不设置
        this.updateValue()
      },
      day(newVal) {
        // console.info('new day: ' + newVal)
        this.updateValue()
      }
    },
    methods: {
      updateValue() {
        this.$emit('change', this.disableChoice ? '?' : this.value_c)
      },
      preProcessProp(c) {
        return replaceWeekName(c)
      }
    },
    created() {
      this.DEFAULT_VALUE = '*'
      // 0,7表示周日 1表示周一
      this.minValue = 0
      this.maxValue = 6
      this.valueRange.start = 0
      this.valueRange.end = 6
      this.valueLoop.start = 2
      this.valueLoop.start = 6
      this.valueLoop.interval = 1
      this.parseProp(this.prop)
    }
  }
</script>

<style scoped>

  .config-list {
    text-align: left;
    margin: 0 10px 10px 10px;
  }

  .item {
    margin-top: 5px;
  }

  .tip-info {
    color: #999;
  }

  .choice {
    border: 1px solid transparent;
    padding: 5px 8px;
  }

  .choice:hover {
    border: 1px solid #2d8cf0;
  }

  .list {
    margin: 0 20px;
  }

  .list-check-item {
    padding: 1px 3px;
    width: 4em;
  }
</style>

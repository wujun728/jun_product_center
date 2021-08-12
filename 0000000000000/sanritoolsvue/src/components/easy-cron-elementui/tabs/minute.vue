<template>
  <div class="config-list">
    <el-radio-group v-model="type" style="font-size: 12px">
      <div class="item">
        <el-radio label="TYPE_EVERY" class="choice" :disabled="disabled" size="mini" :border="true">每分
        </el-radio>
      </div>
      <div class="item">
        <el-radio label="TYPE_RANGE" class="choice" :disabled="disabled" size="mini" :border="true"
                  style="margin-right: 10px">区间
        </el-radio>
        <span>从</span>
        <el-input-number :disabled="type!==TYPE_RANGE || disabled" :max="maxValue" :min="minValue" :precision="0"
                         v-model="valueRange.start" style="width: 90px" size="mini"></el-input-number>
        <span>分
          至</span>
        <el-input-number :disabled="type!==TYPE_RANGE || disabled" :max="maxValue" :min="minValue" :precision="0"
                         v-model="valueRange.end" style="width: 90px" size="mini"></el-input-number>
        分
      </div>
      <div class="item">
        <el-radio label="TYPE_LOOP" class="choice" :disabled="disabled" size="mini" :border="true"
                  style="margin-right: 15px">循环
        </el-radio>
        从
        <el-input-number :disabled="type!==TYPE_LOOP || disabled" :max="maxValue" :min="minValue" :precision="0"
                         v-model="valueLoop.start" style="width: 90px" size="mini"/>
        分到
        <el-input-number :disabled="type!==TYPE_LOOP || disabled" :max="maxValue" :min="minValue" :precision="0"
                         v-model="valueLoop.end" style="width: 90px" size="mini"/>
  
        间隔
        <el-input-number :disabled="type!=='TYPE_LOOP' || disabled" :max="maxValue" :min="minValue" :precision="0"
                         v-model="valueLoop.interval" style="width: 90px" size="mini"/>
        分
      </div>
      <div class="item">
        <el-radio label="TYPE_SPECIFY" class="choice" :disabled="disabled" size="mini" :border="true">指定
        </el-radio>
        <div class="list">
          <el-checkbox-group v-model="valueList">
            <!--<el-checkbox class="list-check-item" v-for="i in maxValue+1"-->
            <!--:label="String(i-1)" :key="`key-${i-1}`"-->
            <!--:disabled="type!==TYPE_SPECIFY || disabled"></el-checkbox>-->
            <el-checkbox class="list-check-item" v-for="i in digitize(maxValue) "
                         :label="i" :key="`${i}`" name="i"
                         :disabled="type!==TYPE_SPECIFY || disabled">{{i}}
            </el-checkbox>
          </el-checkbox-group>
        </div>
      </div>
    </el-radio-group>
  </div>
</template>

<script>
  import mixin from './mixin'

  export default {
    name: 'minute',
    mixins: [mixin],
    data() {
      return {}
    },
    watch: {
      value_c(newVal, oldVal) {
        this.$emit('change', newVal)
      }
    },
    created() {
      this.DEFAULT_VALUE = '*'
      this.minValue = 0
      this.maxValue = 59
      this.valueRange.start = 0
      this.valueRange.end = 59
      this.valueLoop.start = 0
      this.valueLoop.end = 59
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

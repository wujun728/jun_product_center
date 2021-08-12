<template>
  <div class="config-list">
    <el-radio-group v-model="type" style="font-size: 12px">
      <div class="item">
        <el-radio label="TYPE_EVERY" class="choice" :disabled="disabled" size="mini" :border="true">每时
        </el-radio>
      </div>
      <div class="item">
        <el-radio label="TYPE_RANGE" class="choice" :disabled="disabled" size="mini" :border="true"
                  style="margin-right: 10px">区间
        </el-radio>
        从
        <el-input-number :disabled="type!==TYPE_RANGE || disabled" :max="maxValue" :min="minValue" :precision="0"
                         v-model="valueRange.start" style="width: 90px" size="mini"/>
        时
        至
        <el-input-number :disabled="type!==TYPE_RANGE || disabled" :max="maxValue" :min="minValue" :precision="0"
                         v-model="valueRange.end" style="width: 90px" size="mini"/>
        时
      </div>
      <div class="item">
        <el-radio label="TYPE_LOOP" class="choice" :disabled="disabled" size="mini" :border="true"
                  style="margin-right: 15px">循环
        </el-radio>
        从
        <el-input-number :disabled="type!==TYPE_LOOP || disabled" :max="maxValue" :min="minValue" :precision="0"
                         v-model="valueLoop.start" style="width: 90px" size="mini"/>
        时至
        <el-input-number :disabled="type!==TYPE_LOOP || disabled" :max="maxValue" :min="minValue" :precision="0"
                         v-model="valueLoop.end" style="width: 90px" size="mini"/>
        时间隔
        <el-input-number :disabled="type!==TYPE_LOOP || disabled" :max="maxValue" :min="minValue" :precision="0"
                         v-model="valueLoop.interval" style="width: 90px" size="mini"/>
        时
      </div>
      <div class="item">
        <el-radio label="TYPE_SPECIFY" class="choice" :disabled="disabled" size="mini" :border="true">指定
        </el-radio>
        <div class="list">
          <el-checkbox-group v-model="valueList">
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
    // methods: {
    //   digitize(n) {
    //     let array = []
    //     for (let i = 0; i <= n; i++) {
    //       array.push(i)
    //     }
    //     console.log(array)
    //     return array
    //   }
    // },
    created() {
      this.DEFAULT_VALUE = '*'
      this.minValue = 0
      this.maxValue = 23
      this.valueRange.start = 0
      this.valueRange.end = 23
      this.valueLoop.start = 0
      this.valueLoop.end = 23
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

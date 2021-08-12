<template>
  <div class="input-cron">
    <el-input :placeholder="placeholder" style="float: left; width: 450px" v-model="editCronValue" :disabled="disabled">
      <a slot="append" @click="showConfigDlg" class="config-btn" :disabled="disabled">
        <i class="el-icon-date" style="margin-right: 5px;"></i>
        Cron
      </a>
    </el-input>
    <el-dialog title="Config Cron Expression" :show-close="true" :width="`${width+100}px`" top=3vh
               :visible.sync="dialogVisible">
      <div>
        <easy-cron v-model="editCronValue" :style="`width: ${width}px`" :exeStartTime="exeStartTime"
                   :hideYear="hideYear" :remote="remote" :hideSecond="hideSecond"></easy-cron>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import EasyCron from './cron-main'

  export default {
    name: 'input-cron',
    model: {
      prop: 'cronValue',
      event: 'change'
    },
    props: {
      cronValue: {
        type: String,
        default: ''
      },
      width: {
        type: Number,
        default: 1000
      },
      disabled: {
        type: Boolean,
        default: false
      },
      placeholder: {
        type: String,
        default: 'Please input cron expression '
      },
      exeStartTime: {
        type: [Number, String, Object],
        default: 0
      },
      hideSecond: {
        type: Boolean,
        default: false
      },
      hideYear: {
        type: Boolean,
        default: false
      },
      remote: {
        type: Function,
        default: null
      }
    },
    data() {
      return {
        editCronValue: this.cronValue,
        // show: false
        dialogVisible: false
      }
    },
    watch: {
      cronValue(newVal, oldVal) {
        if (newVal === this.editCronValue) {
          return
        }
        this.editCronValue = newVal
      },
      editCronValue(newVal, oldVal) {
        this.$emit('change', newVal)
      }
    },
    methods: {
      showConfigDlg() {
        if (!this.disabled) {
          this.dialogVisible = true
        }
      }
    },
    components: {
      EasyCron
    }
  }
</script>

<style scoped>
  .input-cron, .input-ui {
  /* display: inline-block;*/
  
  }

  .input-cron .ivu-input-wrapper {
    width: 100% !important;
  }

  .config-btn {
    cursor: pointer;
  }

  .config-btn:hover {
    color: #2D8CF0;
  }
</style>

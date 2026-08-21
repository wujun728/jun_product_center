
<template>
  <div>
    <el-dialog :visible.sync="open" :show-title="false" width="500px" append-to-body :before-close="close">
      <div class="dialog-content">
        <div class="left">
          <div class="dialog-header"></div>
          <div class="user-avatar">
            <el-avatar :size="80" :src="avatar(formData.avatar)" />
          </div>
          <div class="user-info">
            <span class="name">{{ formData.nickName }}</span>
          </div>
        </div>
        <div class="right">
          <!-- 详细信息项 -->
          <div class="body">
            <el-descriptions :column="1">
              <el-descriptions-item label="姓名" label-class-name="item-label-right">{{ formData.nickName }}</el-descriptions-item>
              <el-descriptions-item label="部门" label-class-name="item-label-right">{{ dept(formData.dept) }}</el-descriptions-item>
              <el-descriptions-item label="员工状态" label-class-name="item-label-right">{{ empStatus(formData.status) }}</el-descriptions-item>
              <el-descriptions-item label="入职时间" label-class-name="item-label-right">{{ parseTime(formData.createTime, '{y}-{m}-{d}')}}</el-descriptions-item>
              <el-descriptions-item label="手机号" label-class-name="item-label-right">{{ formData.phonenumber }}</el-descriptions-item>
              <el-descriptions-item label="邮箱" label-class-name="item-label-right">{{ formData.email }}</el-descriptions-item>
              <el-descriptions-item label="职位" label-class-name="item-label-right">{{ position }}</el-descriptions-item>
            </el-descriptions>
          </div>
        </div>
      </div>
      <div class="dialog-footer">
        <el-button plain @click="close" size="mini">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>
  
<script>
import { getUserDetail } from "@/api/system/user";

export default {
  dicts: ["schedule_remind_time", "schedule_type", "schedule_level", "schedule_remind_way", "schedule_repeat_way"],
  data() {
    return {
      loding: true,
      formData: {},
      position: null,
      baseApi: process.env.VUE_APP_BASE_API,
    };
  },
  props: {
    userId: {},
    open: {
      type: Boolean,
      default: false,
    },
  },
  watch: {
    userId: function (newVal, oldVal) {
      if (!newVal) return;
      if (newVal != oldVal || !this.formData.userId) {
        this.getUserInfo();
      }
    },
    immediate: true,
  },
  computed: {
    avatar() {
      return (val) => {
        if (!val) return;
        return this.baseApi + val;
      };
    },
    dept() {
      return (val) => {
        if (!val) return "";
        return val.deptName;
      };
    },
    post() {
      return (val) => {
        if (!val || val.length <= 0) return "";
        return val.map((item) => item.postName).join("、");
      };
    },
    empStatus() {
      return (val) => {
        return val === "0" ? "在职" : "离职";
      };
    },
  },
  methods: {
    /** 获取详情 */
    getUserInfo() {
      this.loding = true;
      getUserDetail(this.userId).then((res) => {
        if (res.code == 200) {
          this.formData = res.data;
          if (res.posts && res.posts.length >= 0) {
            this.position = res.posts.map((item) => item.postName).join("、");
          }
        }
        this.loding = false;
      });
    },
    /** 取消 */
    close() {
      this.formData = {};
      this.position = null;
      this.$emit("close");
    },
  },
};
</script>
  
<style scoped lang="scss">
.dialog-content {
  display: flex;
  height: 100%;
  border-radius: 2px;
  .left {
    width: 40%;
    text-align: center;
    border-right: 1px solid #f0f0f0;

    .dialog-header {
      background: url("../../../../assets/images/contact-header.jpg") center no-repeat content-box;
      background-size: cover;
      color: #fff;
      font-weight: bold;
      font-size: 16px;
      min-height: 80px;
      border-top-left-radius: 2px;
      border-top-right-radius: 2px;
    }

    .user-avatar {
      margin-bottom: 10px;
      margin-top: -20px;
    }
    .user-info {
      .name {
        font-size: 16px;
        font-weight: bold;
        color: #333;
      }
    }
  }
  .right {
    width: 60%;

    .body {
      margin: 5px;
    }
    ::v-deep .el-descriptions {
      padding: 10px;

      .el-descriptions__title {
        display: none;
      }
      .el-descriptions__table {
        border-collapse: separate;
        border-spacing: 0 10px;
      }
      .el-descriptions__cell {
        padding: 0;
      }
      .item-label-right {
        color: #909399;
        text-align: right !important;
        min-width: 80px;
        padding-right: 10px;
      }
    }
  }
}
.dialog-footer {
  text-align: center;
  padding: 20px;
}
::v-deep .el-dialog__header {
  display: none;
}

::v-deep .el-dialog__body {
  padding: 0;
}

::v-deep .el-descriptions__label {
  text-align: right !important;
  padding-right: 10px;
}
</style>
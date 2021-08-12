<template>
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="超时设置" name="first">
      <el-form :model="editForm" label-width="150px" ref="editForm">
				<el-form-item label="正常订单超过" prop="payTimeout">
					<el-input v-model="editForm.payTimeout" auto-complete="off" >
             <template slot="append">分</template>
          </el-input>未付款，订单自动关闭
				</el-form-item>
        <el-form-item label="发货超过" prop="shippingTimeout">
					<el-input v-model="editForm.shippingTimeout" auto-complete="off">
            <template slot="append">天</template>
          </el-input>未收货，订单自动完成
				</el-form-item>
        <el-form-item label="订单完成超过" prop="commentTimeout">
					<el-input v-model="editForm.commentTimeout" auto-complete="off">
            <template slot="append">天</template>
          </el-input>自动五星好评
				</el-form-item>
        <el-form-item label="订单完成超过" prop="orderTimeout">
					<el-input v-model="editForm.orderTimeout" auto-complete="off">
            <template slot="append">天</template>
          </el-input>自动结束交易，不能申请售后
				</el-form-item>
        <el-form-item>
          <el-button type="primary" @click.native="editSubmit" >提交</el-button>
         </el-form-item>
			</el-form>
    </el-tab-pane>
  </el-tabs>
</template>
<script>
import { getConfig,updateEntity} from '@/api/orderSetting'

  export default {
    data() {
      return {
        activeName: 'first',
        listLoading:true,
        editForm:{

        }
      };
    },
    created() {
      this.getList()
    },
    methods: {
      handleClick(tab, event) {
        console.log(tab, event);
      },
      getList() {//获取数据
          this.listLoading = true
          getConfig('').then(response => {
            this.editForm = response.data
            this.listLoading = false
          })
      },//编辑
      editSubmit: function () {
        this.$refs.editForm.validate((valid) => {
          if (valid) {
            this.$confirm('确认提交吗？', '提示', {}).then(() => {
              // this.editLoading = true;
              //NProgress.start();
              let para = Object.assign({}, this.editForm);
              updateEntity(para).then((res) => {
                // this.editLoading = false;
                //NProgress.done();
                this.$message({
                  message: '提交成功',
                  type: 'success'
                });
                // this.editFormVisible = false;
                this.getList();
              });
            });
          }
        });
      },

    }
  };
</script>

<style scoped>
.el-input {
  width: 130px;
}
</style>

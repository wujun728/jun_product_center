<template>
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="基本设置" name="first">
      <el-form :model="editForm" label-width="80px" ref="editForm">
				<el-form-item label="服务器地址" prop="host">
					<el-input v-model="editForm.host" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="备注" prop="remark">
					<el-input type="textarea" v-model="editForm.remark" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item>
          <el-button type="primary" @click.native="editSubmit" >提交</el-button>
         </el-form-item>
			</el-form>
    </el-tab-pane>
    <el-tab-pane label="邮件设置" name="second">
       <el-form :model="editForm" label-width="80px" ref="editForm">
				<el-form-item label="SMTP服务器" prop="emailHost">
					<el-input v-model="editForm.emailHost" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="SMTP 端口" prop="emailPort">
					<el-input v-model="editForm.emailPort" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="邮箱帐号" prop="emailUsername">
					<el-input v-model="editForm.emailUsername" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="邮箱密码" prop="emailPassword">
					<el-input v-model="editForm.emailPassword" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="邮件编码" prop="emailEncoding">
					<el-input v-model="editForm.emailEncoding" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="邮件主题" prop="messageCodeSubject">
					<el-input v-model="editForm.messageCodeSubject" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="邮件内容" prop="messageCodeText">
					<el-input type="textarea" v-model="editForm.messageCodeText" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item>
          <el-button type="primary" @click.native="editSubmit" >提交</el-button>
         </el-form-item>
			</el-form>
    </el-tab-pane>
  </el-tabs>
</template>
<script>
import { getConfig,updateConfig} from '@/api/config'

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
              let para = Object.assign({}, this.editForm);
              updateConfig(para).then((res) => {
                this.$message({
                  message: '提交成功',
                  type: 'success'
                });
                this.getList();
              });
            });
          }
        });
      },

    }
  };
</script>

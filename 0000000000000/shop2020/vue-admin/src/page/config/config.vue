<template>
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane label="基本设置" name="first">
      <el-form :model="editForm" label-width="80px" ref="editForm">
				<el-form-item label="服务器地址" prop="host">
					<el-input v-model="editForm.host" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="tplSolution" prop="tplSolution">
					<el-input v-model="editForm.tplSolution" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="tongjiCode" prop="tongjiCode">
					<el-input type="textarea" v-model="editForm.tongjiCode" auto-complete="off"></el-input>
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
    <el-tab-pane label="短信设置" name="third">
        <el-form :model="editForm" label-width="80px" ref="editForm">
				<el-form-item label="短信地址" prop="dxHost">
					<el-input v-model="editForm.dxHost" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="appkey" prop="dxAppkey">
					<el-input v-model="editForm.dxAppkey" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="secret" prop="dxSecret">
					<el-input v-model="editForm.dxSecret" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="短信签名" prop="dxSign">
					<el-input v-model="editForm.dxSign" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="注册短信模版" prop="dxTpl">
					<el-input type="textarea" v-model="editForm.dxTpl" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item>
          <el-button type="primary" @click.native="editSubmit" >提交</el-button>
         </el-form-item>
			</el-form>
    </el-tab-pane>
    <el-tab-pane label="其它" name="fourth">

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
              // this.editLoading = true;
              //NProgress.start();
              let para = Object.assign({}, this.editForm);
              updateConfig(para).then((res) => {
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

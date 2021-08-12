<template>
  <div class="app-container">
    <el-card class="filter-container" shadow="never" style="margin-top: 15px">
        <div>
          <i class="el-icon-search"></i>
          <span>筛选搜索</span>
          <el-button
            style="float: right"
            type="primary"
            v-on:click="getList"
            size="small">
            查询结果
          </el-button>
        </div>
        <div style="margin-top: 15px">
          <el-form :inline="true" :model="listQuery" size="small" label-width="140px">
            <el-form-item label="输入搜索：">
              <el-input style="width: 203px" v-model="listQuery.name" placeholder="名称/关键字"></el-input>
            </el-form-item>
          </el-form>
        </div>
    </el-card>


    <div class="operate-container" shadow="never" style="margin-top: 15px">
        <div slot="header" class="clearfix">
          <el-button size="mini" class="btn-add" @click="handleAdd" v-has="'sys:user:add'">添加</el-button>
          <el-button size="mini" type="danger" @click="delall()" :disabled="this.multipleSelection.length===0" v-has="'sys:user:delete'">批量删除</el-button>
        </div>
       <el-table @selection-change="handleSelectionChange" v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%;margin-top: 15px">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column align="center" label="ID">
          <template slot-scope="scope">
            <span>{{ scope.row.id }}</span>
          </template>
        </el-table-column>

        <el-table-column  align="center" label="登录名">
          <template slot-scope="scope">
            <span>{{ scope.row.username }}</span>
          </template>
        </el-table-column>

        <el-table-column  align="center" label="手机">
          <template slot-scope="scope">
            <span>{{ scope.row.phone }}</span>
          </template>
        </el-table-column>

        <el-table-column  align="center" label="邮箱">
          <template slot-scope="scope">
            <span>{{ scope.row.email }}</span>
          </template>
        </el-table-column>

        <el-table-column  align="center" label="IP">
          <template slot-scope="scope">
            <span>{{ scope.row.unifiedUser.lastLoginIp }}</span>
          </template>
        </el-table-column>

        <el-table-column  align="center" label="是否已启用">
          <template slot-scope="scope">
             <span>
              <el-button type="success" icon="el-icon-check" @click="diableUpdate(scope.$index, scope.row)" circle v-if="!scope.row.unifiedUser.disabled"></el-button>
              <el-button type="danger" icon="fa fa-ban" @click="enableUpdate(scope.$index, scope.row)" circle v-if="scope.row.unifiedUser.disabled"></el-button>
            </span>
          </template>
        </el-table-column>

        <el-table-column align="center" label="操作" width="180">
          <template slot-scope="scope">
            <el-button size="small" @click="handleChat(scope.$index, scope.row)"  v-has="'sys:user:chat'">私信</el-button>
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)" v-has="'sys:user:edit'">编辑</el-button>
					  <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)" v-has="'sys:user:delete'">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNo" :limit.sync="listQuery.pageSize" @pagination="getList" />



		<!--新增界面-->
		<el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
				<el-form-item label="管理员" prop="username">
					<el-input v-model="addForm.username" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="初始密码" prop="password">
					<el-input v-model="addForm.password" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="手机" prop="phone">
					<el-input v-model="addForm.phone" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="邮箱" prop="email">
					<el-input v-model="addForm.email" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="备注" prop="remark">
					<el-input v-model="addForm.remark" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="角色" prop="role">
           <el-checkbox-group v-model="checkedRoles">
            <el-checkbox v-for="role in roleList" :label="role.id" :key="role.id"
                  :value="role.id">{{role.name}}</el-checkbox>
          </el-checkbox-group>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="addFormVisible=false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
			</div>
		</el-dialog>

    <!--编辑界面-->
		<el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
			<el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
					<el-form-item label="管理员" prop="username">
					<el-input v-model="editForm.username" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="初始密码" prop="password">
					<el-input v-model="editForm.password" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="手机" prop="phone">
					<el-input v-model="editForm.phone" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="邮箱" prop="email">
					<el-input v-model="editForm.email" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="备注" prop="remark">
					<el-input v-model="editForm.remark" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="角色" prop="role">
           <el-checkbox-group v-model="checkedRoles">
            <el-checkbox v-for="role in roleList" :label="role.id" :key="role.id"
                  :value="role.id">{{role.name}}</el-checkbox>
          </el-checkbox-group>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>
  </div>
</template>

<script>
import { fetchList,deleteEntity,removeEntity,addEntity,updateEntity} from '@/api/user'
import { diableEntity,enableEntity} from '@/api/unifiedUser'
import { getAllRole} from '@/api/role'

import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'bannerAdvList',
  components: { Pagination },
  data() {
    return {
      checkedRoles: [],//当前选中的角色数据
      roleList:[],//全部角色list
      multipleSelection: [],//当前选择
      list: null,//当前list
      total: 0,
      listLoading: true,
      listQuery: { //查询分页
        name:"",
        pageNo: 1,
        pageSize: 10
      },
      addFormVisible: false,//新增界面是否显示
      addLoading: false,
      addFormRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ]
      },
      //新增界面数据
      addForm: {
        name: '',
      },
      editFormVisible: false,//编辑界面是否显示
      editLoading: false,
      editFormRules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' }
        ],
      },
      //编辑界面数据
      editForm: {
        id: 0,
        name: '',
      }

    }
  },
  created() {
    this.getList();
    this.getRoleList();
  },
  methods: {
     //roleList
    getRoleList() {
       getAllRole().then(response => {
         this.roleList = response.data;
      })
    },
    //全选
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    //列表
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
         this.list = response.data.datas
         this.total = response.data.totalCount
         this.listLoading = false
      })
    },
    //删除
    handleDel: function (index, row) {
      this.$confirm('确认删除该记录吗?', '提示', {
        type: 'warning'
      }).then(() => {
        this.listLoading = true;
        //NProgress.start();
        let para = row.id;
        deleteEntity(para).then((res) => {
          this.listLoading = false;
          //NProgress.done();
          this.$message({
            message: '删除成功',
            type: 'success'
          });
          this.getList();
        });
      }).catch(() => {

      });
    },
    //批量删除
    delall() {
      if (this.multipleSelection.length > 0) {
        this.idlist = this.multipleSelection.map(obj => {
          return obj.id;
        });
        let str = this.idlist.join(",");
         deleteEntity(str).then(res => {
          if (res.code == 200) {
            this.$message({
							message: '删除成功',
							type: 'success'
						});
            this.getList();
          }
        });
      } else {
        this.$message.error("请选择要删除的数据");
      }
    },
    //显示新增界面
    handleAdd: function () {
      this.addFormVisible = true;
      this.addLoading = false;
      this.addForm = {
        name: '',
      };
      this.checkedRoles=[];
    },
    //显示编辑界面
    handleEdit: function (index, row) {
      this.checkedRoles=[];
      this.editFormVisible = true;
      this.addLoading = false;
      this.editForm = Object.assign({}, row);
      if(this.editForm.roles&&this.editForm.roles.length>0){
        this.editForm.roles.map(item => {
          this.checkedRoles.push(item.id);
        })
      }
     },
    //显示详情节目
    handleChat: function (index, row) {
        this.$router.push("/user/chat?id="+row.id);
    },
    //新增
    addSubmit: function () {
       this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.addLoading = true;
            //NProgress.start();
            if(this.checkedRoles!=null&&this.checkedRoles.length>0){
              this.addForm.roles=[];
              this.checkedRoles.map(item => {
                this.addForm.roles.push({id:item});
              });
            }
            let para = Object.assign({}, this.addForm);
            addEntity(para).then((res) => {
              this.addLoading = false;
              //NProgress.done();
              this.$message({
                message: '提交成功',
                type: 'success'
              });
              this.$refs['addForm'].resetFields();
              this.addFormVisible = false;
              this.getList();
            });
          });
        }
      });
    },
    //编辑
    editSubmit: function () {
      this.$refs.editForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.editLoading = true;
            //NProgress.start();
            if(this.checkedRoles!=null&&this.checkedRoles.length>0){
              this.editForm.roles=[];
              this.checkedRoles.map(item => {
                this.editForm.roles.push({id:item});
              });
            }
            let para = Object.assign({}, this.editForm);
             updateEntity(para).then((res) => {
              this.editLoading = false;
              //NProgress.done();
              this.$message({
                message: '提交成功',
                type: 'success'
              });
              this.$refs['editForm'].resetFields();
              this.editFormVisible = false;
              this.getList();
            });
          });
        }
      });
    },
    //禁用
    diableUpdate:function(index, row) {
      this.$confirm('确认禁用吗?', '提示', {
        type: 'warning'
      }).then(() => {
        this.listLoading = true;
        //NProgress.start();
        let para = row.id;
        diableEntity(para).then((res) => {
          this.listLoading = false;
          //NProgress.done();
          this.$message({
            message: '禁用成功',
            type: 'success'
          });
          this.getList();
        });
      }).catch(() => {

      });
    },
    //禁用
    enableUpdate:function(index, row) {
      this.$confirm('确认启用吗?', '提示', {
        type: 'warning'
      }).then(() => {
        this.listLoading = true;
        //NProgress.start();
        let para = row.id;
        enableEntity(para).then((res) => {
          this.listLoading = false;
          //NProgress.done();
          this.$message({
            message: '启用成功',
            type: 'success'
          });
          this.getList();
        });
      }).catch(() => {

      });
    }

  }
}
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>

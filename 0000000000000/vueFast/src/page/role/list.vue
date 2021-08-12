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
          <el-button size="mini" class="btn-add" @click="handleAdd" v-has="'sys:role:add'">添加</el-button>
          <el-button size="mini" type="danger" @click="delall()" :disabled="this.multipleSelection.length===0" v-has="'sys:role:delete'">批量删除</el-button>
        </div>
       <el-table @selection-change="handleSelectionChange" v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%;margin-top: 15px">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column align="center" label="ID">
          <template slot-scope="scope">
            <span>{{ scope.row.id }}</span>
          </template>
        </el-table-column>

        <el-table-column  align="center" label="名称">
          <template slot-scope="scope">
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>

        <el-table-column  align="center" label="描述">
          <template slot-scope="scope">
            <span>{{ scope.row.description }}</span>
          </template>
        </el-table-column>


        <el-table-column  align="center" label="排序">
          <template slot-scope="scope">
            <span>{{ scope.row.sort }}</span>
          </template>
        </el-table-column>

        <el-table-column align="center" label="操作" width="180">
          <template slot-scope="scope">
            <el-button type="text"  size="mini" @click="handleMenu(scope.$index, scope.row)" v-has="'sys:role:perm'">授权</el-button>
            <el-button type="text"  size="mini" @click="handleEdit(scope.$index, scope.row)" v-has="'sys:role:edit'">编辑</el-button>
					  <el-button type="text" size="mini" @click="handleDel(scope.$index, scope.row)" v-has="'sys:role:delete'">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNo" :limit.sync="listQuery.pageSize" @pagination="getList" />



		<!--新增界面-->
		<el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false" customClass="customWidth">
			<el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
				<el-form-item label="名称" prop="name">
					<el-input v-model="addForm.name" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="排序号" prop="sort">
					<el-input type="number" v-model="addForm.sort" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="描述" prop="description">
					<el-input v-model="addForm.description" auto-complete="off"></el-input>
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
					<el-form-item label="名称" prop="name">
					<el-input v-model="editForm.name" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="排序号" prop="sort">
					<el-input type="number" v-model="editForm.sort" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="描述" prop="description">
					<el-input v-model="editForm.description" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>

    <!--角色授权界面-->
		<el-dialog title="授权界面" :visible.sync="menuFormVisible" :close-on-click-modal="false">
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
					<el-form-item label="是否全部" prop="allperms">
          <el-checkbox v-model="editForm.allperms">全部权限</el-checkbox>
				</el-form-item>
      </el-form>
      <div style="padding-left:15px;padding-top:0px;padding-bottom:14px;">
			  <el-checkbox v-model="checkAll" @change="handleCheckAll" border size="medium" :disabled="editForm.allperms"><b>全选</b></el-checkbox>
		  </div>
      <el-tree :data="menuList" size="mini" show-checkbox node-key="id" :props="defaultProps" :disabled="editForm.allperms"
      default-expand-all   :default-checked-keys="currentRoleMenus"
			style="width: 100%;pading-top:20px;" ref="menuTree" :render-content="renderContent" element-loading-text="拼命加载中" :check-strictly="true"
			@check-change="handleMenuCheckChange">
      </el-tree>
      <div slot="footer" class="dialog-footer">
				<el-button @click.native="menuFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="menuSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>
  </div>
</template>

<script>
import { fetchList,deleteEntity,removeEntity,addEntity,updateEntity,saveRoleMenus} from '@/api/role'
import { getNodes} from '@/api/menu'

import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import TreeSelect from '@/components/tree-select';

export default {
  name: 'roleList',
  components: { Pagination,TreeSelect},
  props: {
    children: {
      type: Array,
      default () {
        return [];
      }
    },
    hasChildren: {
      type: Boolean,
      default: true
    },
  },
  data() {
    return {
      menuList:[],
      nodeKey: 'id',//栏目的id
      currentRoleMenus: [],//
      defaultProps: {
        children: 'children',
        label:"name",
      },
      checkAll: false,
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
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ]
      },
      //新增界面数据
      addForm: {
        name: '',
      },
      editFormVisible: false,//编辑界面是否显示
      editLoading: false,
      editFormRules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ]
      },
      //编辑界面数据
      editForm: {
        id: 0,
        name: '',
      },
      menuFormVisible: false,//授权界面是否显示

    }
  },
  created() {
    this.getList();
    //获取树形
    this.getMenuList();
  },
  methods: {
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
    },
    //显示编辑界面
    handleEdit: function (index, row) {
      this.editFormVisible = true;
      this.addLoading = false;
      this.editForm = Object.assign({}, row);
    },
    //新增
    addSubmit: function () {
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.addLoading = true;
            //NProgress.start();
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
            let para = Object.assign({}, this.editForm);
            para.menus=null;
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
    //栏目列表json
    getMenuList() {
      let para = {id:""}
      getNodes(para).then(response => {
          this.menuList = response.data
      })
    },
    //显示编辑界面
    handleMenu: function (index, row) {
      this.menuFormVisible = true;
      this.editForm = Object.assign({}, row);
       this.currentRoleMenus=this.editForm.menus;
      //重新设置
      this.$refs.menuTree.setCheckedNodes(this.currentRoleMenus)
    },
    menuSubmit: function () {
      let checkedNodes = this.$refs.menuTree.getCheckedNodes(false, true)
			let roleMenus = []
			for(let i=0, len=checkedNodes.length; i<len; i++) {
				let roleMenu = {id:checkedNodes[i].id }
				roleMenus.push(roleMenu)
			}
      this.$confirm('确认提交吗？', '提示', {}).then(() => {
             //NProgress.start();
            let para = Object.assign({}, this.editForm);
            para.menus=roleMenus;//设置分类的权限
            saveRoleMenus(para).then((res) => {
              this.editLoading = false;
              //NProgress.done();
              this.$message({
                message: '提交成功',
                type: 'success'
              });
              this.menuFormVisible = false;
              this.$refs['editForm'].resetFields();
              this.getList();
            });
          });
      // alert(JSON.stringify(this.currentRoleMenus))
    },
		// 树节点选择监听
		handleMenuCheckChange(data, check, subCheck) {
			if(check) {
				// 节点选中时同步选中父节点
        let pid = data.pid
        if(pid==0||pid==null){
          // if(data.children != null) {
					//   data.children.forEach(element => {
					// 	this.$refs.menuTree.setChecked(element.id, true, false)
					// });
				  // }
        }
        this.$refs.menuTree.setChecked(pid, true, true)
        // if(data.children != null) {
				// 	  data.children.forEach(element => {
				// 		this.$refs.menuTree.setChecked(element.id, true, false)
				// 	});
				// }
			} else {
        let pid = data.pid
        if(pid==undefined||pid==null){
          pid = data.id
        }
        this.$refs.menuTree.setChecked(pid, false, false)
				// 节点取消选中时同步取消选中子节点
				if(data.children != null) {
					  data.children.forEach(element => {
						this.$refs.menuTree.setChecked(element.id, false, false)
					});
				}
			}
		},
		// 重置选择
		resetSelection() {
			this.checkAll = false
			this.$refs.menuTree.setCheckedNodes(this.currentRoleMenus)
		},
		// 全选操作
		handleCheckAll() {
			if(this.checkAll) {
				let allMenus = []
				this.checkAllMenu(this.menuList, allMenus)
				this.$refs.menuTree.setCheckedNodes(allMenus)
			} else {
				this.$refs.menuTree.setCheckedNodes([])
			}
		},
		// 递归全选
		checkAllMenu(menuList, allMenus) {
			menuList.forEach(menu => {
				allMenus.push(menu)
				if(menu.children) {
					this.checkAllMenu(menu.children, allMenus)
				}
			});
		},
		renderContent(h, { node, data, store }) {
			return (
        <div class="column-container">
          <span style="text-algin:center;margin-right:80px;">{data.name}</span>
          <span style="text-algin:center;margin-right:80px;">
            <el-tag type={data.type === 0?'':data.type === 0?'success':'info'} size="small">
              {data.type === undefined?'目录':data.type === 0?'菜单':'按钮'}
            </el-tag>
          </span>
          <span style="text-algin:center;margin-right:80px;"> <i class={data.icon}></i></span>
          <span style="text-algin:center;margin-right:80px;">{data.path?data.path:'\t'}</span>
        </div>);
      	},

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
.customWidth{
    width:80%;
}
</style>

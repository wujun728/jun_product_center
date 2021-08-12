<template>
  <div class="app-container">
    <!-- <el-card class="filter-container" shadow="never" style="margin-top: 15px">
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
    </el-card> -->


    <div class="operate-container" shadow="never" style="margin-top: 15px">
        <div slot="header" class="clearfix">
          <el-button size="mini" class="btn-add" @click="handleAdd" v-has="'sys:menu:add'">添加</el-button>
          <!-- <el-button size="mini" type="danger" @click="delall()" :disabled="this.multipleSelection.length===0">批量删除</el-button> -->
        </div>
        <!-- default-expand-all -->
       <el-table @selection-change="handleSelectionChange" v-loading="listLoading" :data="menuList" border fit highlight-current-row style="width: 100%;margin-top: 15px"

         row-key="id" v-bind="$attrs"
         :tree-props="{children: 'children', hasChildren: true}" >
        <!-- <el-table-column type="selection" width="55"></el-table-column> -->
         <el-table-column align="center" label="名称">
          <template slot-scope="scope">
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="action">
          <template slot-scope="scope">
            <span>{{ scope.row.path }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="icon">
          <template slot-scope="scope">
            <span>{{ scope.row.icon }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="显示">
          <template slot-scope="scope">
            <span>{{ is_showMap[scope.row.status] }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="类型">
          <template slot-scope="scope">
            <span>{{ menu_typeMap[scope.row.type] }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="授权标识">
          <template slot-scope="scope">
            <span>{{ scope.row.perms }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="排序">
          <template slot-scope="scope">
            <span>{{ scope.row.sort }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作" width="180">
          <template slot-scope="scope">
            <el-button size="small" @click="handleAddNext(scope.$index, scope.row)" v-has="'sys:menu:add'">添加下级</el-button>
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)" v-has="'sys:menu:edit'">编辑</el-button>
					  <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)" v-if="scope.row.children.length<=0" v-has="'sys:menu:delete'">删除</el-button>
          </template>
        </el-table-column>
       </el-table>
    </div>
    <!-- <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNo" :limit.sync="listQuery.pageSize" @pagination="getList" /> -->



		<!--新增界面-->
		<el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
				<el-form-item label="名称" prop="name">
					<el-input v-model="addForm.name" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="父节点" prop="menuId">
           <tree-select :data="menuList"
                 :defaultProps="defaultProps"
                 :nodeKey="nodeKey" :checkedKeys="defaultCheckedKeys"
                 @popoverHide="popoverHide"></tree-select>
				</el-form-item>
        <el-form-item label="类型" prop="type">
					<el-select v-model="addForm.type" placeholder="请选择" size="mini">
               <el-option v-for="(item,index) in menu_typeList"
                    :key="index"
                    :label="item.name"
                    :value="item.value"></el-option>
            </el-select>
				</el-form-item>
				<el-form-item label="action" prop="action">
					<el-input v-model="addForm.path" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="icon" prop="icon">
					<el-input v-model="addForm.icon" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="page" prop="page">
					<el-input v-model="addForm.page" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="权限" prop="perms">
					<el-input v-model="addForm.perms" auto-complete="off"></el-input>
				</el-form-item>
				<el-form-item label="状态" prop="status">
					<el-select v-model="addForm.status" placeholder="请选择" size="mini">
               <el-option v-for="(item,index) in is_showList"
                    :key="index"
                    :label="item.name"
                    :value="item.value"></el-option>
            </el-select>
				</el-form-item>
				<el-form-item label="排序号" prop="sort">
					<el-input v-model="addForm.sort" auto-complete="off"></el-input>
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
        <el-form-item label="父节点" prop="menuId">
				  <tree-select :data="menuList"
                 :defaultProps="defaultProps"
                 :nodeKey="nodeKey" :checkedKeys="defaultCheckedKeys"
                 @popoverHide="popoverHide"></tree-select>
				</el-form-item>
        <el-form-item label="类型" prop="type">
					<el-select v-model="editForm.type" placeholder="请选择" size="mini">
               <el-option v-for="(item,index) in menu_typeList"
                    :key="index"
                    :label="item.name"
                    :value="item.value"></el-option>
            </el-select>
				</el-form-item>
        <el-form-item label="action" prop="action">
          <el-input v-model="editForm.path" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="icon" prop="icon">
          <el-input v-model="editForm.icon" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="page" prop="page">
          <el-input v-model="editForm.page" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="perms">
					<el-input v-model="editForm.perms" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="状态" prop="status">
            <el-select v-model="editForm.status" placeholder="请选择" size="mini">
                <el-option v-for="(item,index) in is_showList"
                    :key="index"
                    :label="item.name"
                    :value="item.value"></el-option>
            </el-select>
        </el-form-item>
 				<el-form-item label="排序号" prop="sort">
					<el-input v-model="editForm.sort" auto-complete="off"></el-input>
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

import { fetchList,deleteEntity,removeEntity,addEntity,updateEntity,getNodes} from '@/api/menu'

import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import TreeSelect from '@/components/tree-select';

export default {
  name: 'bannerAdvList',
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
    }
  },
  data() {
    return {
      is_showList:[],//是否显示list
      is_showMap:{},//是否显示
      menu_typeList:[],//菜单类型list
      menu_typeMap:{},//类型类型
      menuList:[],//所有的菜单
      nodeKey: 'id',//栏目的id
      defaultCheckedKeys: [],//栏目默认选中的值
      defaultProps: {
        children: 'children',
        label:"name"
      },
      multipleSelection: [],//当前选择
      list: null,//当前list
      total: 0,
      listLoading: false,
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
      }

    }
  },
  created() {
    this.getList()
    //获取数据字典
    this.get_dictionary_list('is_show').then(response => {
      this.is_showList=response.data;
      var map = {};
      this.is_showList.forEach(item=>{
          map[item.value+''] = item.name;
      });
      this.is_showMap=map;
    })
    this.get_dictionary_list('menu_type').then(response => {
      this.menu_typeList=response.data;
      var map = {};
      this.menu_typeList.forEach(item=>{
          map[item.value+''] = item.name;
      });
      this.menu_typeMap=map;
    })
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
          this.getMenuList();
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
            this.getMenuList();
           }
        });
      } else {
        this.$message.error("请选择要删除的数据");
      }
    },
    //显示新增界面
    handleAdd: function () {
      this.addFormVisible = true;
      this.addLoading=false;
      this.addForm = {
        name: '',
        status: '0',
        pid: "",
        type: '0',
      };
    },
    //显示编辑界面
    handleEdit: function (index, row) {
      this.editFormVisible = true;
      this.editLoading=false;
      this.editForm = Object.assign({}, row);
      //选中
      if(this.editForm.pid){
        this.defaultCheckedKeys = [this.editForm.pid];
      }else{
        this.defaultCheckedKeys = [];
      }
    },
    handleAddNext: function (index, row) {
       this.defaultCheckedKeys = [row.id];
       this.handleAdd();
       this.addForm.pid=row.id;
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
               this.getMenuList();
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
             updateEntity(para).then((res) => {
              this.editLoading = false;
              //NProgress.done();
              this.$message({
                message: '提交成功',
                type: 'success'
              });
              this.$refs['editForm'].resetFields();
              this.editFormVisible = false;
              this.getMenuList();
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
    //栏目选中
    popoverHide (checkedIds, checkedData) {
      if(this.addFormVisible){
        this.addForm.pid=checkedIds;
      }else{
        this.editForm.pid=checkedIds;
      }
     },
     handelIncrease(step) {
        //alert(JSON.stringify(step));
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
</style>

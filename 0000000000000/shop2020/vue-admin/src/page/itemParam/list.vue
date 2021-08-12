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
          <el-button size="mini" class="btn-add" @click="handleAdd" v-has="'sys:itemParam:add'">添加</el-button>
          <el-button size="mini" type="danger" @click="delall()" :disabled="this.multipleSelection.length===0" v-has="'sys:itemParam:delete'">批量删除</el-button>
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

        <el-table-column  align="center" label="path">
          <template slot-scope="scope">
            <span>{{ scope.row.path }}</span>
          </template>
        </el-table-column>

        <el-table-column  align="center" label="排序">
          <template slot-scope="scope">
            <span>{{ scope.row.sort }}</span>
          </template>
        </el-table-column>

        <el-table-column align="center" label="操作" width="180">
          <template slot-scope="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)" v-has="'sys:itemParam:edit'">编辑</el-button>
					  <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)" v-has="'sys:itemParam:delete'">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNo" :limit.sync="listQuery.pageSize" @pagination="getList" />



		<!--新增界面-->
		<el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
			<el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
				<el-form-item label="名称" prop="name">
					<el-input v-model="addForm.name" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="path" prop="path">
					<el-input v-model="addForm.path" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="排序" prop="sort">
					<el-input v-model="addForm.sort" auto-complete="off"></el-input>
				</el-form-item>

        <el-form-item label="模版">
            <el-input v-model="addAttr.name" placeholder="自定义" class="input-with-select">
            <el-select v-model="addAttr.name"  slot="prepend" placeholder="请选择" style="width:100px;">
              <el-option label="尺寸" value="尺寸"></el-option>
              <el-option label="颜色" value="颜色"></el-option>
              <el-option label="大小" value="大小"></el-option>
            </el-select>
            <el-button slot="append" icon="el-icon-plus" @click.native="addAttrOne"></el-button>
          </el-input>
       </el-form-item>
          <!-- 动态的1级规格-->
          <el-card class="box-card" :key="item.name" v-for="(item,index) in attributesDataList" style="margin-top:10px">
            <div slot="header" class="clearfix">
              <el-tag  closable @close="deleteAttrOne(index)">{{item.name}}</el-tag>
             </div>
            <!-- 动态的2级规格-->
            <el-tag
              :key="tag"
              type='info'
              v-for="(tag,i) in item.attrs"
              closable
              :disable-transitions="false"
              @close="deleteAttrTwo(item,i)" style="margin-top:10px">
              {{tag}}
            </el-tag>

            <el-input
              class="input-new-tag"
              v-if="item.inputVisible"
              v-model="item.inputValue"
              ref="saveTagInput"
              size="small"
              @keyup.enter.native="handleInputConfirm(item)"
              @blur="handleInputConfirm(item)" style="margin-top:10px">
            </el-input>
            <el-button v-else class="button-new-tag" size="small" @click="showInput(item)" style="margin-top:10px">+ New Tag</el-button>
          </el-card>



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
        <el-form-item label="path" prop="path">
					<el-input v-model="editForm.path" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="排序" prop="sort">
					<el-input v-model="editForm.sort" auto-complete="off"></el-input>
				</el-form-item>

        <el-form-item label="模版">
            <el-input v-model="addAttr.name" placeholder="自定义" class="input-with-select">
            <el-select v-model="addAttr.name"  slot="prepend" placeholder="请选择" style="width:100px;">
              <el-option label="尺寸" value="尺寸"></el-option>
              <el-option label="颜色" value="颜色"></el-option>
              <el-option label="大小" value="大小"></el-option>
            </el-select>
            <el-button slot="append" icon="el-icon-plus" @click.native="addAttrOne"></el-button>
          </el-input>
       </el-form-item>
          <!-- 动态的1级规格-->
          <el-card class="box-card" :key="item.name" v-for="(item,index) in attributesDataList" style="margin-top:10px">
            <div slot="header" class="clearfix">
              <el-tag  closable @close="deleteAttrOne(index)">{{item.name}}</el-tag>
             </div>
            <!-- 动态的2级规格-->
            <el-tag
              :key="tag"
              type='info'
              v-for="(tag,i) in item.attrs"
              closable
              :disable-transitions="false"
              @close="deleteAttrTwo(item,i)" style="margin-top:10px">
              {{tag}}
            </el-tag>

            <el-input
              class="input-new-tag"
              v-if="item.inputVisible"
              v-model="item.inputValue"
              ref="saveTagInput"
              size="small"
              @keyup.enter.native="handleInputConfirm(item)"
              @blur="handleInputConfirm(item)">
            </el-input>
            <el-button v-else class="button-new-tag" size="small" @click="showInput(item)" style="margin-top:10px">+ New Tag</el-button>
          </el-card>

			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>
  </div>
</template>

<script>
import { fetchList,deleteEntity,removeEntity,addEntity,updateEntity} from '@/api/itemParam'

import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'itemParamList',
  components: { Pagination },
  data() {
    return {
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
      //新增规格参数使用
      addAttr: {
        name: '',
      },
      attributesList:[],//临时用于存放规格一级的类别
      attributesDataList:[],//自定义规格的使用工具类
    }
  },
  created() {
    this.getList()
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
      this.addAttr={
        name: '',
      }
      this.attributesDataList=[];//所有的内容
      this.attributesList=[];//临时参数
    },
    //显示编辑界面
    handleEdit: function (index, row) {
      this.editFormVisible = true;
      this.addLoading = false;
      this.editForm = Object.assign({}, row);
      this.addAttr={
        name: '',
      }
      this.attributesDataList=[];//所有的内容
      this.attributesList=[];//临时参数
      //修改规格等参数
      if(this.editForm.content){
        let attributes=JSON.parse(this.editForm.content);//转数组
        attributes.map(item=>{
            this.attributesDataList.push({
              attrs: item.params,//标签值
              name: item.group,//标签值
              inputVisible: false,
              inputValue: ''
            });
            this.attributesList.push(item.name);
        })
      }
    },
    //新增
    addSubmit: function () {
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.addLoading = true;
            //NProgress.start();
             //如果增加了规格参数
            let attributesList=[];
            if(this.attributesDataList&&this.attributesDataList.length>0){
                this.attributesDataList.map(item=>{
                  attributesList.push({group:item.name,params:item.attrs});
                })
            }
            this.addForm.content=JSON.stringify(attributesList);
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
            let attributesList=[];
            if(this.attributesDataList&&this.attributesDataList.length>0){
                this.attributesDataList.map(item=>{
                  attributesList.push({group:item.name,params:item.attrs});
                })
            }
            this.editForm.content=JSON.stringify(attributesList);
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
    //模版
    addAttrOne(){
      if(this.addAttr.name&&this.attributesList.indexOf(this.addAttr.name)=== -1){
        this.attributesList.push(this.addAttr.name);
        //zidingyi
        this.attributesDataList.push({
          attrs: [],//标签值
          name: this.addAttr.name,//标签值
          inputVisible: false,
          inputValue: ''
        });
       }else{
        this.$message({
          message: '不能为空/重复',
          type: 'error'
        });
      }
    },
    //删除规格参数
    deleteAttrOne(index) {
      this.attributesList.splice(index, 1);
      this.attributesDataList.splice(index, 1);
    },
    //自定义显示
    showInput(item) {
      item.inputVisible = true;
    },
    //自定义输入
    handleInputConfirm(item) {
      let inputValue = item.inputValue;
      if (inputValue&&item.attrs.indexOf(inputValue)=== -1){
        item.attrs.push(inputValue);
      }
      item.inputVisible = false;
      item.inputValue = '';
    },
    //删除规格参数二级
    deleteAttrTwo(item,i){
      item.attrs.splice(i, 1);
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
.el-select .el-input {
  width: 130px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}

.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>

<template>
  <div class="app-container">
    <el-container style="height: 500px; border: 1px solid #eee;margin-top: 15px">
      <el-aside width="200px" style="padding:20px;">
         <el-tree :data="productCatList" :props="defaultProps" @node-click="handleNodeClick" :default-expand-all="true"></el-tree>
      </el-aside>
      <el-container>
          <div class="filter-container" shadow="never" style="margin-top: 15px;width:100%">
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

            <div slot="header" class="clearfix" style="margin-top: 15px">
              <el-button size="mini" class="btn-add" @click="handleAdd" v-has="'sys:productCat:add'">添加</el-button>
              <el-button size="mini" type="danger" @click="delall()" :disabled="this.multipleSelection.length===0" v-has="'sys:productCat:delete'">批量删除</el-button>
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

               <el-table-column  align="center" label="icon">
                <template slot-scope="scope">
                  <span>{{ scope.row.icon }}</span>
                </template>
              </el-table-column>
              <el-table-column  align="center" label="图片">
                <template slot-scope="scope">
                  <!-- <span>{{ scope.row.img }}</span> -->
                  <img v-bind:src="scope.row.img" style="height:50px;">
                </template>
              </el-table-column>
              <el-table-column  align="center" label="是否显示">
                <template slot-scope="scope">
                  <span>{{ is_showMap[scope.row.hiden]}}</span>
                </template>
              </el-table-column>
               <el-table-column  align="center" label="排序">
                <template slot-scope="scope">
                  <span>{{ scope.row.sort }}</span>
                </template>
              </el-table-column>

              <el-table-column align="center" label="操作" width="180">
                <template slot-scope="scope">
                  <el-button size="small" @click="handleEdit(scope.$index, scope.row)" v-has="'sys:productCat:edit'">编辑</el-button>
                  <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)" v-has="'sys:productCat:delete'">删除</el-button>
                </template>
              </el-table-column>
            </el-table>
            <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNo" :limit.sync="listQuery.pageSize" @pagination="getList" />
       </div>
      </el-container>
    </el-container>

		<!--新增界面-->
		<el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
			<el-tabs v-model="activeName">
      <el-tab-pane label="基本设置" name="first">
        <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
          <el-form-item label="名称" prop="name">
            <el-input v-model="addForm.name" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="icon" prop="icon">
            <el-input v-model="addForm.icon" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="图片" prop="img">
            <Upload v-model="imgList" :limit="1" :showFileList="true"/>
          </el-form-item>
          <el-form-item label="状态" prop="hiden">
            <el-select v-model="addForm.hiden" placeholder="请选择" size="mini">
                <el-option v-for="(item,index) in is_showList"
                      :key="index"
                      :label="item.name"
                      :value="item.value"></el-option>
              </el-select>
          </el-form-item>
          <el-form-item label="排序" prop="sort">
            <el-input v-model="addForm.sort" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input type="textarea" v-model="addForm.remark" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="其他参数" name="three">
        <el-form :model="addForm" label-width="100px" :rules="addFormRules" ref="addForm">
          <el-form-item label="其他参数">
            <el-input v-model="addAttr.group" placeholder="自定义" class="input-with-select">
                <el-input v-model="addAttr.name" value="group" slot="prepend" style="width:100px;"></el-input>
                <el-button slot="append" icon="el-icon-plus" @click.native="addItemOne">
            </el-button>
          </el-input>
          </el-form-item>
           <el-card class="box-card" :key="item.name" v-for="(item,index) in itemsDataList" style="margin-top:10px">
            <div slot="header" class="clearfix">
              <el-tag  closable @close="deleteItemOne(index)">{{item.group}}</el-tag>
             </div>

            <el-form-item
              v-for="(tag, index) in item.params"
              :label="tag.k"
              :key="tag.k"
             >
               <el-input v-model="tag.v" style="width:300px;"></el-input>
               <el-button @click.prevent="removeItem(item.params,index)">删除</el-button>
            </el-form-item>

            <el-input
              class="input-new-tag"
              v-if="item.inputVisible"
              v-model="item.inputValue"
              ref="saveTagInput"
              size="small"
              @keyup.enter.native="handleInputConfirmItem(item)"
              @blur="handleInputConfirmItem(item)">
            </el-input>
            <el-button v-else class="button-new-tag" size="small" @click="showInputItem(item)">+ 自定义</el-button>
          </el-card>
        </el-form>
      </el-tab-pane>
		  </el-tabs>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="addFormVisible=false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
			</div>
		</el-dialog>

    <!--编辑界面-->
		<el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
			<el-tabs v-model="activeName">
      <el-tab-pane label="基本设置" name="first">
        <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
            <el-form-item label="名称" prop="name">
            <el-input v-model="editForm.name" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="icon" prop="icon">
            <el-input v-model="editForm.icon" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="图片" prop="img">
            <Upload v-model="imgList" :limit="1" :showFileList="true"/>
          </el-form-item>
          <el-form-item label="状态" prop="hiden">
            <el-select v-model="editForm.hiden" placeholder="请选择" size="mini">
                <el-option v-for="(item,index) in is_showList"
                      :key="index"
                      :label="item.name"
                      :value="item.value"></el-option>
              </el-select>
          </el-form-item>
          <el-form-item label="排序" prop="sort">
            <el-input v-model="editForm.sort" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input type="textarea" v-model="editForm.remark" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="其他参数" name="three">
        <el-form :model="addForm" label-width="100px" :rules="addFormRules" ref="addForm">
          <el-form-item label="其他参数">
            <el-input v-model="addAttr.group" placeholder="自定义规格" class="input-with-select">
            <el-button slot="append" icon="el-icon-plus" @click.native="addItemOne"></el-button>
          </el-input>
          </el-form-item>
           <el-card class="box-card" :key="item.name" v-for="(item,index) in itemsDataList" style="margin-top:10px">
            <div slot="header" class="clearfix">
              <el-tag  closable @close="deleteItemOne(index)">{{item.group}}</el-tag>
             </div>

            <el-form-item
              v-for="(tag, index) in item.params"
              :label="tag.k"
              :key="tag.k"
             >
               <el-input v-model="tag.v" style="width:300px;"></el-input>
               <el-button @click.prevent="removeItem(item.params,index)">删除</el-button>
            </el-form-item>

            <el-input
              class="input-new-tag"
              v-if="item.inputVisible"
              v-model="item.inputValue"
              ref="saveTagInput"
              size="small"
              @keyup.enter.native="handleInputConfirmItem(item)"
              @blur="handleInputConfirmItem(item)">
            </el-input>
            <el-button v-else class="button-new-tag" size="small" @click="showInputItem(item)">+ 自定义</el-button>
          </el-card>
        </el-form>
      </el-tab-pane>
		  </el-tabs>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>
  </div>
</template>

<script>
import { fetchList,deleteEntity,removeEntity,addEntity,updateEntity,getNodes} from '@/api/productCat'

import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Upload from '@/components/Upload'

export default {
  name: 'productCatList',
  components: { Pagination,Upload},
  data() {
    return {
      activeName: 'first',
      pId:"",//当前选中的父节点id
      productCatList: [],//全部栏目分类
      defaultProps: {
        children: 'children',
        label:"name"
      },
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
      imgList:[],//方便文件上传使用
      is_showList:[],
      is_showMap:{},
       //新增规格参数使用
      addAttr: {
        name: 'group',
        group: '',
      },
      itemsDataList:[],//其他参数集合
    }
  },
  created() {
    this.getList();
    this.getProductCatList();//获取分类
    //获取数据字典
    this.get_dictionary_list('is_show').then(response => {
      this.is_showList=response.data;
      var map = {};
      this.is_showList.forEach(item=>{
          map[item.value+''] = item.name;
      });
      this.is_showMap=map;
    })
  },
  methods: {
    //全选
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    //列表
    getList() {
      this.listLoading = true
      this.listQuery.pId=this.pId//设置当前分类父节点
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
          this.getProductCatList();
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
            this.getProductCatList();
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
        hiden: '0',
      };
      this.imgList=[];
      this.itemsDataList=[];
    },
    //显示编辑界面
    handleEdit: function (index, row) {
      this.imgList=[];
      this.editFormVisible = true;
      this.addLoading = false;
      this.editForm = Object.assign({}, row);
      if(this.editForm.img){//如果图片不为空
        this.imgList=[{name:"",url:this.editForm.img}]
      }
      this.itemsDataList=[];//其他自定义参数
      if(this.editForm.itemParam){
        this.itemsDataList=JSON.parse(this.editForm.itemParam);
      }
    },
    //新增
    addSubmit: function () {
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.addLoading = true;
            //NProgress.start();
            this.addForm.parent={};
            this.addForm.parent.id=this.pId; //设置当前分类父节点
            if(this.imgList&&this.imgList.length>0){//图片不为空的时候
              this.addForm.img=this.imgList[0].url;
            }else{
               this.addForm.img=''
            }
            //自定义参数
            if(this.itemsDataList){
              this.addForm.itemParam=JSON.stringify(this.itemsDataList);
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
              this.getProductCatList();
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
            if(this.imgList&&this.imgList.length>0){//图片不为空的时候
              this.editForm.img=this.imgList[0].url;
            }else{
               this.editForm.img=''
            }
            //自定义参数
            if(this.itemsDataList){
              this.editForm.itemParam=JSON.stringify(this.itemsDataList);
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
              this.getProductCatList();
            });
          });
        }
      });
    },//点击分类
    handleNodeClick(data) {
      if(data&&data.id>0){
        this.pId=data.id
      }else{
        this.pId=''
      }
      this.getList();
    },
    //列表
    getProductCatList() {
      let para = {id:""}
      getNodes(para).then(response => {
          this.productCatList = response.data
      })
    },
    //下面是其他参数的设置
    //从这里开始
     addItemOne(){
      if(this.addAttr.group){
        let group='group';
        if(this.addAttr.name){
           group=this.addAttr.name;
        }else{
          group=group+this.itemsDataList.length;
        }
         //zidingyi
        this.itemsDataList.push({
          params: [],//标签值
          group: this.addAttr.group,//标签值
          inputVisible: false,
          inputValue: ''
        });
        this.addAttr.group='';
       }else{
        this.$message({
          message: '不能为空/重复',
          type: 'error'
        });
      }
    },
    //删除规格参数
    deleteItemOne(index) {
       this.itemsDataList.splice(index, 1);
     },
    //自定义显示
    showInputItem(item) {
      item.inputVisible = true;
      // this.$nextTick(() => {
      //   this.$refs.saveTagInput.$refs.input.focus();
      // });
     },
    //自定义输入
    handleInputConfirmItem(item) {
      let inputValue = item.inputValue;
      if (inputValue){
        item.params.push({
          k:inputValue,
          v:''
        });
      }
      item.inputVisible = false;
      item.inputValue = '';
     },
    //删除规格参数二级
    deleteItemTwo(item,i){
      item.params.splice(i, 1);
    },
    removeItem: function (item,index) {
      this.$confirm('确认删除吗?', '提示', {
        type: 'warning'
      }).then(() => {
          item.splice(index, 1);
      }).catch(() => {

      });
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

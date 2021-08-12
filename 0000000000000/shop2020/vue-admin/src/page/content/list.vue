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
          <el-button size="mini" class="btn-add" @click="handleAdd" v-has="'sys:content:add'">添加</el-button>
          <el-button size="mini" type="danger" @click="delall()" :disabled="this.multipleSelection.length===0" v-has="'sys:content:delete'">批量删除</el-button>
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
        <el-table-column  align="center" label="栏目">
          <template slot-scope="scope" v-if="scope.row.channel!=null">
            <span>{{ scope.row.channel.name }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="图片">
          <template slot-scope="scope">
            <!-- <span>{{ scope.row.img }}</span> -->
            <img v-bind:src="scope.row.img" style="height:50px;">
          </template>
        </el-table-column>
        <el-table-column  align="center" label="描述">
          <template slot-scope="scope">
            <span>{{ scope.row.description }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="浏览量">
          <template slot-scope="scope">
            <span v-if="scope.row.count">{{ scope.row.count.views }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="发布时间">
          <template slot-scope="scope">
            <span>{{ scope.row.dateline }}</span>
          </template>
        </el-table-column>

        <el-table-column align="center" label="操作" width="180">
          <template slot-scope="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)" v-has="'sys:content:edit'">编辑</el-button>
					  <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)" v-has="'sys:content:delete'">删除</el-button>
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
        <el-form-item label="栏目" prop="channelId">
           <tree-select :data="channelList"
                 :defaultProps="defaultProps"
                 :nodeKey="nodeKey" :checkedKeys="defaultCheckedKeys"
                 @popoverHide="popoverHide"></tree-select>
				</el-form-item>

        <el-form-item label="图片" prop="img">
           <Upload v-model="imgList" :limit="1" :showFileList="true"/>
  			</el-form-item>
        <el-form-item label="描述" prop="description">
					<el-input type="textarea" v-model="addForm.description" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="内容" prop="content">
            <Editor id="add1" @input="handelIncrease"  :value="addForm.content" v-model="addForm.content"></Editor>
				</el-form-item>
        <el-form-item label="发布时间" prop="dateline">
           <el-date-picker type="datetime" placeholder="选择日期" v-model="addForm.dateline" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
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
        <el-form-item label="栏目" prop="channelId">
				  <tree-select :data="channelList"
                 :defaultProps="defaultProps"
                 :nodeKey="nodeKey" :checkedKeys="defaultCheckedKeys"
                 @popoverHide="popoverHide"></tree-select>
				</el-form-item>
        <el-form-item label="图片" prop="img">
					<Upload v-model="imgList" :limit="1" :showFileList="true"/>
				</el-form-item>
        <el-form-item label="描述" prop="description">
					<el-input type="textarea" v-model="editForm.description" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="内容" prop="content">
           <Editor id="edit1"  :value="editForm.content" v-model="editForm.content"></Editor>
				</el-form-item>
        <el-form-item label="发布时间" prop="dateline">
          <el-date-picker type="datetime" placeholder="选择日期" v-model="editForm.dateline" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
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
import { fetchList,deleteEntity,removeEntity,addEntity,updateEntity} from '@/api/content'
import {getImgPath} from '@/utils/config'
import { getNodes} from '@/api/channel'

import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import TreeSelect from '@/components/tree-select'
import Editor from "@/components/Editor"
import Upload from '@/components/Upload'


export default {
  name: 'contentList',
  components: { Pagination,TreeSelect,Editor,Upload },
  data() {
    return {
      channelList: [],//全部栏目分类
      nodeKey: 'id',//栏目的id
      defaultCheckedKeys: [],//栏目默认选中的值
      defaultProps: {
        children: 'children',
        label:"text"
      },
      channelId:"",
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
        channelId: '',
        channel:{}
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
        channelId: '',
        channel:{},
        content: '',
      },
      imgList:[],//方便文件上传使用

    }
  },
  created() {
    this.getList()
    this.getChannelList();//获取分类
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
      Editor.value = '';
      this.addFormVisible = true;
      this.addLoading = false;
      this.addForm = {
        name: '',
        channel:{},
        content:""
      };
    },
    //显示编辑界面
    handleEdit: function (index, row) {
      Editor.value = '';
      this.imgList=[];
      this.editFormVisible = true;
      this.addLoading = false;
      this.editForm = Object.assign({}, row);
      if(this.editForm.channel){
        this.editForm.channelId=this.editForm.channel.id;
        //选中栏目
        this.defaultCheckedKeys = [this.editForm.channel.id];
      }
      if(this.editForm.img){//如果图片不为空
        this.imgList=[{name:"",url:this.editForm.img}]
      }
    },
    //新增
    addSubmit: function () {
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.addLoading = true;
            //NProgress.start();
            this.addForm.channel={};
            this.addForm.channel.id=this.addForm.channelId;
            if(this.imgList&&this.imgList.length>0){//图片不为空的时候
              this.addForm.img=this.imgList[0].url;
            }else{
               this.addForm.img=''
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
             this.editForm.channel={};
             this.editForm.channel.id=this.editForm.channelId;
             if(this.imgList&&this.imgList.length>0){//图片不为空的时候
                this.editForm.img=this.imgList[0].url;
              }else{
                this.editForm.img=''
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
    //栏目列表json
    getChannelList() {
      let para = {id:""}
      getNodes(para).then(response => {
          this.channelList = response
      })
    },
    //栏目选中
    popoverHide (checkedIds, checkedData) {
      if(this.addFormVisible){
        this.addForm.channelId=checkedIds;
      }else{
        this.editForm.channelId=checkedIds;
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

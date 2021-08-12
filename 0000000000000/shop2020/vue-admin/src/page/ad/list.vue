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
          <el-button size="mini" class="btn-add" @click="handleAdd" v-has="'sys:ad:add'">添加</el-button>
          <el-button size="mini" type="danger" @click="delall()" :disabled="this.multipleSelection.length===0" v-has="'sys:ad:delete'">批量删除</el-button>
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
        <el-table-column  align="center" label="广告位">
          <template slot-scope="scope">
            <span v-if="scope.row.adsense">{{ scope.row.adsense.name }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="状态">
          <template slot-scope="scope">
            <span>{{ is_showMap[scope.row.status] }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="图片">
          <template slot-scope="scope">
            <!-- <span>{{ scope.row.img }}</span> -->
            <img v-bind:src="scope.row.img" style="height:50px;">
          </template>
        </el-table-column>
        <el-table-column  align="center" label="url">
          <template slot-scope="scope">
            <span>{{ scope.row.url }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="开始时间">
          <template slot-scope="scope">
            <span>{{ scope.row.startTime }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="结束时间">
          <template slot-scope="scope">
            <span>{{ scope.row.endTime }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="排序">
          <template slot-scope="scope">
            <span>{{ scope.row.sort }}</span>
          </template>
        </el-table-column>


        <el-table-column align="center" label="操作" width="180">
          <template slot-scope="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)" v-has="'sys:ad:edit'">编辑</el-button>
					  <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)" v-has="'sys:ad:delete'">删除</el-button>
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
        <el-form-item label="广告位" prop="adsenseId">
           <el-select v-model="addForm.adsense.id" placeholder="请选择" size="mini">
              <el-option v-for="(item,index) in adsenseList"
                  :key="index"
                  :label="item.name"
                  :value="item.id"></el-option>
          </el-select>
				</el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker type="datetime" placeholder="选择日期" v-model="addForm.startTime" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
 				</el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker type="datetime" placeholder="选择日期" v-model="addForm.endTime" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
 				</el-form-item>
        <el-form-item label="状态" prop="status">
					<el-select v-model="addForm.status" placeholder="请选择" size="mini">
              <el-option v-for="(item,index) in is_showList"
                  :key="index"
                  :label="item.name"
                  :value="item.value"></el-option>
          </el-select>
				</el-form-item>
        <el-form-item label="代码" prop="code">
					<el-input type="textarea" v-model="addForm.code" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="图片" prop="img">
					<Upload v-model="imgList" :limit="1" :showFileList="true"/>
				</el-form-item>
        <el-form-item label="链接" prop="url">
					<el-input v-model="addForm.url" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="备注" prop="remark">
					<el-input type="textarea" v-model="addForm.remark" auto-complete="off"></el-input>
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
        <el-form-item label="广告位" prop="adsenseId">
           <el-select v-model="editForm.adsense.id" placeholder="请选择" size="mini">
              <el-option v-for="(item,index) in adsenseList"
                  :key="index"
                  :label="item.name"
                  :value="item.id"></el-option>
          </el-select>
				</el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker type="datetime" placeholder="选择日期" v-model="editForm.startTime" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
 				</el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-date-picker type="datetime" placeholder="选择日期" v-model="editForm.endTime" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
 				</el-form-item>
        <el-form-item label="状态" prop="status">
           <el-select v-model="editForm.status" placeholder="请选择" size="mini">
               <el-option v-for="(item,index) in is_showList"
                    :key="index"
                    :label="item.name"
                    :value="item.value"></el-option>
            </el-select>
				</el-form-item>
        <el-form-item label="代码" prop="code">
					<el-input type="textarea" v-model="editForm.code" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="图片" prop="img">
					<Upload v-model="imgList" :limit="1" :showFileList="true"/>
				</el-form-item>
        <el-form-item label="链接" prop="url">
					<el-input v-model="editForm.url" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="备注" prop="remark">
					<el-input type="textarea" v-model="editForm.remark" auto-complete="off"></el-input>
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
import { fetchList,deleteEntity,removeEntity,addEntity,updateEntity} from '@/api/ad'
import { findAll } from '@/api/adsense'


import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import Upload from '@/components/Upload'

export default {
  name: 'adList',
  components: { Pagination,Upload },
  data() {
    return {
      is_showList:[],
      is_showMap:{},
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
        status: '0',
        adsense:{
          id:""
        }
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
        adsense:{
          id:""
        }
      },
      adsenseList:[],//广告位
      imgList:[],//方便文件上传使用
    }
  },
  created() {
    this.getList()
    //获取数据字典
    this.get_dictionary_list('is_show').then(response => {
      this.is_showList=response.data;
      var map = {};
      for(var index in this.is_showList){
          map[this.is_showList[index].value] = this.is_showList[index].name;
      }
      this.is_showMap=map;
    })
    this.getAdsenseList()
  },
  methods: {
    //全选
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    //获取广告位
    getAdsenseList(){
       findAll({}).then(response => {
         this.adsenseList = response.data
        })
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
        status: '0',
        adsense:{
          id:1
        }
      };
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
     },
    //新增
    addSubmit: function () {
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.addLoading = true;
            //NProgress.start();
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

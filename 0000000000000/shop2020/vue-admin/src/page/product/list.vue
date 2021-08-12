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
          <el-button size="mini" class="btn-add" @click="handleAdd" v-has="'sys:product:add'">添加</el-button>
          <el-button size="mini" type="danger" @click="delall()" :disabled="this.multipleSelection.length===0" v-has="'sys:product:delete'">批量删除</el-button>
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
        <el-table-column  align="center" label="分类">
          <template slot-scope="scope">
            <span v-if="scope.row.productCat">{{ scope.row.productCat.name }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="图片">
          <template slot-scope="scope">
            <!-- <span>{{ scope.row.img }}</span> -->
            <img v-bind:src="scope.row.img" style="height:50px;">
          </template>
        </el-table-column>
        <el-table-column  align="center" label="价格">
          <template slot-scope="scope">
            <span style="color:red" v-if="scope.row.price">¥ {{ scope.row.price }}</span>
           </template>
        </el-table-column>
        <el-table-column  align="center" label="数量">
          <template slot-scope="scope">
            <span>{{ scope.row.num }}</span>
           </template>
        </el-table-column>
        <el-table-column  align="center" label="单位">
          <template slot-scope="scope">
            <span>{{ scope.row.unit }}</span>
           </template>
        </el-table-column>
        <el-table-column  align="center" label="发布时间">
          <template slot-scope="scope">
            <span>{{ scope.row.dateline }}</span>
          </template>
        </el-table-column>

        <el-table-column align="center" label="操作" width="180">
          <template slot-scope="scope">
            <el-button size="small" @click="handleEdit(scope.$index, scope.row)" v-has="'sys:product:edit'">编辑</el-button>
					  <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)" v-has="'sys:product:delete'">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.pageNo" :limit.sync="listQuery.pageSize" @pagination="getList" />



		<!--新增界面-->
		<el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
		<el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="基本设置" name="first">
        <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
          <el-form-item label="名称" prop="name">
            <el-input v-model="addForm.name" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="分类" prop="productCatId">
            <tree-select :data="productCatList"
                  :defaultProps="defaultProps"
                  :nodeKey="nodeKey" :checkedKeys="defaultCheckedKeys"
                  @popoverHide="popoverHide"></tree-select>
          </el-form-item>
          <el-form-item label="价格" prop="price">
            <el-input type="number" v-model="addForm.price" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="数量" prop="num">
            <el-input type="number" v-model="addForm.num" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="单位" prop="unit">
            <el-input v-model="addForm.unit" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input type="textarea" v-model="addForm.remark" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="缩略图片" prop="img">
            <Upload v-model="imgList" :limit="1" :showFileList="true"/>
          </el-form-item>
          <el-form-item label="图片集合" prop="img">
            <Upload v-model="addForm.imgList" :limit="9" :showFileList="true"/>
          </el-form-item>
          <el-form-item label="内容" prop="content">
            <Editor id="edit1"  :value="addForm.content" v-model="addForm.content"></Editor>
          </el-form-item>
          <el-form-item label="发布时间" prop="dateline">
            <el-date-picker type="datetime" placeholder="选择日期" v-model="addForm.dateline" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
          </el-form-item>
          <el-form-item label="排序" prop="sort">
            <el-input type="number" v-model="addForm.sort" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="浏览量" prop="unit">
            <el-input v-model="addForm.count.views" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="库存规格" name="second">
        <el-form :model="addForm" label-width="100px" :rules="addFormRules" ref="addForm">
          <el-form-item label="多规格">
            <el-input v-model="addAttr.name" placeholder="自定义规格" class="input-with-select">
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
              @close="deleteAttrTwo(item,i)">
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
            <el-button v-else class="button-new-tag" size="small" @click="showInput(item)">+ 自定义</el-button>
          </el-card>
          <!-- 动态的库存-->
          <el-table
             :data="allAttrList"
             style="width: 100%;marigin-top:10px" v-if="attributesDataList&&attributesDataList.length>0">
            <el-table-column v-bind:label="item.name" :key="item.name"  v-for="(item,index) in attributesDataList" style="margin-top:10px">
              <template slot-scope="scope">
                  <span>{{scope.row.attrs[index]}}</span>
              </template>
            </el-table-column>

            <el-table-column align="right">
              <template slot="header" slot-scope="scope">
                <el-input type="number" placeholder="价格" style="padding:2px"/>
              </template>
              <template slot-scope="scope">
                <el-input type="number" v-model="scope.row.price" auto-complete="off" style="padding:2px"></el-input>
              </template>
            </el-table-column>

            <el-table-column align="right">
              <template slot="header" slot-scope="scope">
                <el-input type="number"  placeholder="库存" style="padding:2px"/>
              </template>
              <template slot-scope="scope">
                <el-input type="number" v-model="scope.row.stock" auto-complete="off"></el-input>
              </template>
            </el-table-column>

            <el-table-column align="right">
              <template slot="header" slot-scope="scope">
                <el-input placeholder="icon"/>
              </template>
              <template slot-scope="scope">
                <el-input v-model="scope.row.icon" auto-complete="off"></el-input>
              </template>
            </el-table-column>

             <el-table-column align="right">
              <template slot="header" slot-scope="scope">
                <el-input  placeholder="name"/>
              </template>
              <template slot-scope="scope">
                <el-input v-model="scope.row.name" auto-complete="off"></el-input>
              </template>
            </el-table-column>


            <el-table-column align="right">
              <template slot="header" slot-scope="scope">
                <el-input  placeholder="img"/>
              </template>
              <template slot-scope="scope">
                <el-input v-model="scope.row.img" auto-complete="off"></el-input>
              </template>
            </el-table-column>

            <el-table-column align="center" label="操作" width="180">
              <template slot-scope="scope">
                 <el-button type="danger" size="small" @click="deleteAttrDetail(scope.$index, scope.row)">删除</el-button>
              </template>
            </el-table-column>

          </el-table>
        </el-form>
      </el-tab-pane>
       <el-tab-pane label="其他参数" name="three">
        <el-form :model="addForm" label-width="100px" :rules="addFormRules" ref="addForm">
          <el-form-item label="其他参数">
            <el-input v-model="addAttr.group" placeholder="自定义参数" class="input-with-select">
            <el-select v-model="addAttr.item" slot="prepend" placeholder="请选择" style="width:100px;"  @change="selectOneItem">
              <el-option v-for="(item,index) in allItemParamList"
                    :key="index"
                    :label="item.name"
                    :value="index"></el-option>
            </el-select>
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
				<el-button @click.native="addFormVisible=false">取消</el-button>
				<el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
			</div>
		</el-dialog>

    <!--编辑界面-->
		<el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
      <el-tabs v-model="activeName" @tab-click="handleClick">
      <el-tab-pane label="基本设置" name="first">
        <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
            <el-form-item label="名称" prop="name">
            <el-input v-model="editForm.name" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="分类" prop="productCatId">
            <tree-select :data="productCatList"
                  :defaultProps="defaultProps"
                  :nodeKey="nodeKey" :checkedKeys="defaultCheckedKeys"
                  @popoverHide="popoverHide"></tree-select>
          </el-form-item>
          <el-form-item label="价格" prop="price">
            <el-input type="number" v-model="editForm.price" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="数量" prop="num">
            <el-input type="number" v-model="editForm.num" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="单位" prop="unit">
            <el-input v-model="editForm.unit" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input type="textarea" v-model="editForm.remark" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="缩略图片" prop="img">
            <Upload v-model="imgList" :limit="1" :showFileList="true"/>
          </el-form-item>
          <el-form-item label="图片集合" prop="img">
            <Upload v-model="editForm.imgList" :limit="9" :showFileList="true"/>
          </el-form-item>
          <el-form-item label="内容" prop="content">
            <Editor id="edit1"  :value="editForm.content" v-model="editForm.content"></Editor>
          </el-form-item>
           <el-form-item label="发布时间" prop="dateline">
            <el-date-picker type="datetime" placeholder="选择日期" v-model="editForm.dateline" style="width: 100%;" value-format="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd HH:mm:ss"></el-date-picker>
          </el-form-item>
          <el-form-item label="排序" prop="sort">
            <el-input type="number" v-model="editForm.sort" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="库存规格" name="second">
        <el-form :model="editForm" label-width="100px" :rules="editFormRules" ref="editForm">
          <el-form-item label="多规格">
            <el-input v-model="addAttr.name" placeholder="自定义规格" class="input-with-select">
            <el-select v-model="addAttr.name"  slot="prepend" placeholder="请选择" style="width:100px;">
              <el-option label="尺寸" value="尺寸"></el-option>
              <el-option label="颜色" value="颜色"></el-option>
              <el-option label="大小" value="大小"></el-option>
            </el-select>
            <el-button slot="append" icon="el-icon-plus" @click.native="addAttrOne"></el-button>
          </el-input>
          </el-form-item>
          <!-- 动态的1级规格-->
          <el-card class="box-card" :key="item.name"  v-for="(item,index) in attributesDataList" style="margin-top:10px">
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
              @close="deleteAttrTwo(item,i)">
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
            <el-button v-else class="button-new-tag" size="small" @click="showInput(item)">+ 自定义</el-button>
          </el-card>
          <!-- 动态的库存-->
          <el-table
             :data="allAttrList"
             style="width: 100%;marigin-top:10px" v-if="attributesDataList&&attributesDataList.length>0">
            <el-table-column v-bind:label="item.name" :key="item.name"  v-for="(item,index) in attributesDataList" style="margin-top:10px">
              <template slot-scope="scope">
                  <span>{{scope.row.attrs[index]}}</span>
              </template>
            </el-table-column>

            <el-table-column align="right">
              <template slot="header" slot-scope="scope">
                <el-input type="number" placeholder="价格" style="padding:2px"/>
              </template>
              <template slot-scope="scope">
                <el-input type="number" v-model="scope.row.price" auto-complete="off" style="padding:2px"></el-input>
              </template>
            </el-table-column>

            <el-table-column align="right">
              <template slot="header" slot-scope="scope">
                <el-input type="number"  placeholder="库存"/>
              </template>
              <template slot-scope="scope">
                <el-input type="number" v-model="scope.row.stock" auto-complete="off"></el-input>
              </template>
            </el-table-column>

            <el-table-column align="right">
              <template slot="header" slot-scope="scope">
                <el-input  placeholder="icon"/>
              </template>
              <template slot-scope="scope">
                <el-input v-model="scope.row.icon" auto-complete="off"></el-input>
              </template>
            </el-table-column>

            <el-table-column align="right">
              <template slot="header" slot-scope="scope">
                <el-input  placeholder="name"/>
              </template>
              <template slot-scope="scope">
                <el-input v-model="scope.row.name" auto-complete="off"></el-input>
              </template>
            </el-table-column>

            <el-table-column align="right">
              <template slot="header" slot-scope="scope">
                <el-input  placeholder="img"/>
              </template>
              <template slot-scope="scope">
                <el-input v-model="scope.row.img" auto-complete="off"></el-input>
              </template>
            </el-table-column>

             <el-table-column align="center" label="操作" width="180">
              <template slot-scope="scope">
                 <el-button type="danger" size="small" @click="deleteAttrDetail(scope.$index, scope.row)">删除</el-button>
              </template>
            </el-table-column>

          </el-table>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="包装规格" name="three">
        <el-form :model="editForm" label-width="100px" :rules="editFormRules" ref="editForm">
          <el-form-item label="其他参数">
            <el-input v-model="addAttr.group" placeholder="自定义参数" class="input-with-select">
            <el-select v-model="addAttr.item" slot="prepend" placeholder="请选择" style="width:100px;"  @change="selectOneItem">
              <el-option v-for="(item,index) in allItemParamList"
                    :key="index"
                    :label="item.name"
                    :value="index"></el-option>
            </el-select>
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
              :key="index"
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
import { fetchList,deleteEntity,removeEntity,addEntity,updateEntity} from '@/api/product'
import { getNodes} from '@/api/productCat'
import { getAllItemParamList} from '@/api/itemParam'


import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import TreeSelect from '@/components/tree-select'
import Editor from "@/components/Editor"
import Upload from '@/components/Upload'

export default {
  name: 'productList',
  components: { Pagination,TreeSelect,Editor,Upload},
  data() {
    return {
      activeName: 'first',
      productCatList: [],//全部栏目分类
      nodeKey: 'id',//栏目的id
      defaultCheckedKeys: [],//栏目默认选中的值
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
        count:{
          views:0
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
      },
      imgList:[],//方便文件上传使用
      //新增规格参数使用
      addAttr: {
        name: '',
      },
      attributesList:[],//临时用于存放规格一级的类别
      attributesDataList:[],//自定义规格的使用工具类
      allAttrList:[],//所有的排列组合
      itemsDataList:[],//其他参数集合
      allItemParamList:[],//系统模版的共用集合
    }
  },
  created() {
    this.getList()
    this.getProductCatList();//获取分类
    this.getAllItemParam();//获取规格参数
  },
  methods: {
    //获取全部模版参数
    getAllItemParam() {
       getAllItemParamList({path:''}).then(response => {
         this.allItemParamList=response.data;
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
      Editor.value = '';
      this.imgList=[];
      this.addFormVisible = true;
      this.addLoading = false;
      this.addForm = {
        name: '',
        productCat:{},
        imgList:[],
        count:{
          views:0
        }
      };
      this.addAttr={
        name: '',
      }
      this.allAttrList=[]//所有的排列组合
      this.attributesDataList=[];//所有的规格
      this.attributesList=[];//临时规格
      this.itemsDataList=[];//其他自定义参数
    },
    //显示编辑界面
    handleEdit: function (index, row) {
      Editor.value = '';
      this.imgList=[];
      this.editFormVisible = true;
      this.addLoading = false;
      this.editForm = Object.assign({}, row);
      if(this.editForm.productCat){
         //选中栏目
        this.defaultCheckedKeys = [this.editForm.productCat.id];
      }
      if(this.editForm.img){//如果图片不为空
        this.imgList=[{name:"",url:this.editForm.img}]
      }
      if(this.editForm.imgs){
        this.editForm.imgList=JSON.parse(this.editForm.imgs);
      }else{
        this.editForm.imgList=[];
      }
      this.attributesList=[];//临时规格
      this.attributesDataList=[];
       //修改规格等参数
      if(this.editForm.attributes){
        let attributes=JSON.parse(this.editForm.attributes);//转数组
        attributes.map(item=>{
            this.attributesDataList.push({
              attrs: item.attrs,//标签值
              name: item.name,//标签值
              inputVisible: false,
              inputValue: ''
            });
            this.attributesList.push(item.name);
        })
      }
      this.allAttrList=[];
      //库存参数不为空
      if(this.editForm.productAttrList){
          this.editForm.productAttrList.map(item=>{
            this.allAttrList.push({
              attrs:JSON.parse(item.attributes),
              name:item.name,
              price:item.price,
              stock:item.stock,
              icon:item.icon,
              status:item.status,
            })
          })
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
            if(this.imgList&&this.imgList.length>0){//图片不为空的时候
              this.addForm.img=this.imgList[0].url;
            }else{
               this.addForm.img=''
            }
            this.addForm.imgs=null;
            if(this.addForm.imgList&&this.addForm.imgList.length>0){//图片不为空的时候
              let list=[];
              this.addForm.imgList.map(item=>{
                list.push({name:"",url:item.url});
              })
              this.addForm.imgs=list;
            }
            //如果增加了规格参数
            let attributesList=[];
            if(this.attributesDataList&&this.attributesDataList.length>0){
                this.attributesDataList.map(item=>{
                  attributesList.push({name:item.name,attrs:item.attrs});
                })
                this.addForm.attributesList=attributesList;
            }
            //生成的库存价格信息
            let productAttrList=[];
             if(this.allAttrList&&this.allAttrList.length>0){
                this.allAttrList.map(item=>{ //重新赋值
                  let data=item;
                  data.attributes=JSON.stringify(data.attrs);
                  delete data.attrs;//js方法
                  productAttrList.push(data);
                })
                this.addForm.productAttrList=productAttrList;
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
            this.editForm.imgs=null;
            if(this.editForm.imgList&&this.editForm.imgList.length>0){//图片不为空的时候
              let list=[];
              this.editForm.imgList.map(item=>{
                list.push({name:"",url:item.url});
              })
              this.editForm.imgs=list;
            }
            //如果增加了规格参数
            let attributesList=[];
            if(this.attributesDataList&&this.attributesDataList.length>0){
                this.attributesDataList.map(item=>{
                  attributesList.push({name:item.name,attrs:item.attrs});
                })
                this.editForm.attributesList=attributesList;
            }else{
               this.editForm.attributesList=null;//为空的时候直接给空
            }
            //生成的库存价格信息
            let productAttrList=[];
             if(this.allAttrList&&this.allAttrList.length>0){
                this.allAttrList.map(item=>{ //重新赋值
                  let data=item;
                  data.attributes=JSON.stringify(data.attrs);
                  delete data.attrs;//js方法
                  productAttrList.push(data);
                })
                this.editForm.productAttrList=productAttrList;
            }else{
               this.editForm.productAttrList=null;//为空的时候直接给空
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
            });
          });
        }
      });
    },
    //列表
    getProductCatList() {
      let para = {id:""}
      getNodes(para).then(response => {
          this.productCatList = response.data
      })
    },
    //选中
    popoverHide (checkedIds, checkedData) {
      if(this.addFormVisible){
        this.addForm.productCat={};
        this.addForm.productCat.id=checkedIds;
      }else{
        this.editForm.productCat={};
        this.editForm.productCat.id=checkedIds;
      }
    },
    handleClick(tab, event) {
        console.log(tab, event);
    },
    //添加商品规格参数
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
        this.autoAttr();
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
      this.autoAttr();
    },
    //自定义显示
    showInput(item) {
      item.inputVisible = true;
      // this.$nextTick(() => {
      //   this.$refs.saveTagInput.$refs.input.focus();
      // });
      this.autoAttr();
    },
    //自定义输入
    handleInputConfirm(item) {
      let inputValue = item.inputValue;
      if (inputValue&&item.attrs.indexOf(inputValue)=== -1){
        item.attrs.push(inputValue);
      }
      item.inputVisible = false;
      item.inputValue = '';
      this.autoAttr();
    },
    //删除规格参数二级
    deleteAttrTwo(item,i){
      item.attrs.splice(i, 1);
      this.autoAttr();
    },
    //自动生成规格
    autoAttr() {
        let lastList=this.allAttrList;//记录上次的内容
        this.allAttrList=[];
        let list=[];
        for (let index in this.attributesDataList) {//循环的次数
          let item=this.attributesDataList[index].attrs;
           if(item&&item.length>0&&item[0]){
             list.push(item);
           }
        }
        if(list&&list.length>0&&list[0]){
          this.getSkuData([],0,list,lastList);
        }

    },
    getSkuData(skuArr = [], i, list,lastList) {
      if(list){
        for (let j = 0; j < list[i].length; j++) {
         if (i < list.length - 1) {
           skuArr[i] = list[i][j]
           this.getSkuData(skuArr, i + 1, list,lastList) // 递归循环
         } else {
           let val = JSON.stringify([...skuArr, list[i][j]]);
           let addItem=null
           //如果已经存在修改过的值，那么保存下来
           if (lastList&&lastList.length>0){
              lastList.map(item=>{
                if(JSON.stringify(item.attrs) == val){
                  addItem=item;//可添加的数组
                }
              })
           }
           if(addItem){
             this.allAttrList.push(addItem);
           }else{
              // 扩展运算符，连接两个数组
            this.allAttrList.push({
              attrs:[...skuArr, list[i][j]],
              name:"",
              price:"",
              stock:"",
              icon:"",
              img:"",
              status:"0",
            })
           }
         }
       }
      }
    },//删除数组
    deleteAttrDetail: function (index, row) {
      this.$confirm('确认删除吗?', '提示', {
        type: 'warning'
      }).then(() => {
          this.allAttrList.splice(index, 1);
      }).catch(() => {

      });
    },
    //下面是其他参数的设置
    //从这里开始
     addItemOne(){
      if(this.addAttr.group){
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
    //选择规格模版
    selectOneItem:function(event){
        let tpl=this.allItemParamList[event];
        if(tpl&&tpl.content){
           let tpl_param=JSON.parse(tpl.content);
           tpl_param.map(item=>{
              let params=[];
              item.params.map(i=>{//循环input框
                params.push({
                  k:i,
                  v:''
                });
              })
              item.params=params
              this.itemsDataList.push({
                params: item.params,//标签值
                group: item.group,//标签值
                inputVisible: false,
                inputValue: ''
              });
           })
        }
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

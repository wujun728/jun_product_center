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
          <!-- <el-button size="mini" class="btn-add" @click="handleAdd" v-has="'sys:order:add'">添加</el-button> -->
          <el-button size="mini" type="danger" @click="delall()" :disabled="this.multipleSelection.length===0" v-has="'sys:order:delete'">批量删除</el-button>
        </div>
       <el-table @selection-change="handleSelectionChange" v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%;margin-top: 15px">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column align="center" label="ID">
          <template slot-scope="scope">
            <span>{{ scope.row.id }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="订单编号">
          <template slot-scope="scope">
            <span>{{ scope.row.orderNo }}</span>
          </template>
        </el-table-column>
        <!-- <el-table-column  align="center" label="名称">
          <template slot-scope="scope">
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column> -->
        <el-table-column  align="center" label="数量">
          <template slot-scope="scope">
            <span>{{ scope.row.num }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="金额">
          <template slot-scope="scope">
            ¥<span>{{ scope.row.price }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="状态">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.status=='-1'" type="info">{{ order_statusMap[scope.row.status] }}</el-tag>
            <el-tag v-if="scope.row.status=='1'" type="success">{{ order_statusMap[scope.row.status] }}</el-tag>
            <el-tag v-if="scope.row.status!='1'&&scope.row.status!='-1'" type="warning">{{ order_statusMap[scope.row.status] }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="支付">
          <template slot-scope="scope">
            <span>{{ order_payMap[scope.row.pay] }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="发货">
          <template slot-scope="scope">
            <span>{{ shipping_statusMap[scope.row.shippingStatus] }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="下单人">
          <template slot-scope="scope">
            <span>{{ scope.row.unifiedUser.username }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="下单时间">
          <template slot-scope="scope">
            <span>{{ scope.row.dateline }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="来源">
          <template slot-scope="scope">
            <span>{{ scope.row.platform }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作" width="180">
          <template slot-scope="scope">
            <el-button size="small" @click="handleView(scope.$index, scope.row)"  v-has="'sys:order:detail'">订单详情</el-button>
            <el-button size="small" @click="handleShipping(scope.$index, scope.row)" v-if="scope.row.pay=='1'&&(scope.row.shipping_status=='0'||scope.row.shipping_status=='1')" v-has="'sys:order:shipping'">物流发货</el-button>
            <!-- <el-button size="small" @click="handleEdit(scope.$index, scope.row)" v-has="'sys:order:edit'">编辑</el-button> -->
					  <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)" v-has="'sys:order:delete'">删除</el-button>
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
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>

    <!--编辑界面-->
		<el-dialog title="编辑" :visible.sync="shippingFormVisible" :close-on-click-modal="false">
			<el-form :model="shippingForm" label-width="80px" :rules="shippingFormRules" ref="shippingForm">
				<el-form-item label="快递名称" prop="name">
					<el-input v-model="shippingForm.name" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="快递单号" prop="express">
					<el-input v-model="shippingForm.express" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="shippingFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="shippingSubmit" :loading="shippingLoading">提交</el-button>
			</div>
		</el-dialog>
  </div>
</template>

<script>
import { fetchList,deleteEntity,removeEntity,addEntity,updateEntity,saveShippingEntity} from '@/api/order'

import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'orderList',
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
      order_payMap:{},//支付
      order_statusMap:{},//订单状态
      order_pay_typeMap:{},//支付方式
      shipping_statusMap:{},//物流状态
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
      shippingFormVisible: false,//编辑界面是否显示
      shippingLoading: false,
      shippingFormRules: {
        name: [
          { required: true, message: '请输入名称', trigger: 'blur' }
        ]
      },
      //编辑界面数据
      shippingForm: {
        id: 0,
        name: '',
      }

    }
  },
  created() {
    this.getList();
    this.get_dictionary_list('order_status').then(response => {
      this.order_statusList=response.data;
      var map = {};
      this.order_statusList.forEach(item=>{
          map[item.value+''] = item.name;
      });
      this.order_statusMap=map;
    })
    this.get_dictionary_list('order_pay').then(response => {
      this.order_payList=response.data;
      var map = {};
      this.order_payList.forEach(item=>{
          map[item.value+''] = item.name;
      });
      this.order_payMap=map;
    })
    this.get_dictionary_list('order_pay_type').then(response => {
      this.order_pay_typeList=response.data;
      var map = {};
      this.order_pay_typeList.forEach(item=>{
          map[item.value+''] = item.name;
      });
      this.order_pay_typeMap=map;
    })
    this.get_dictionary_list('shipping_status').then(response => {
      this.shipping_statusList=response.data;
      var map = {};
      this.shipping_statusList.forEach(item=>{
          map[item.value+''] = item.name;
      });
      this.shipping_statusMap=map;
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
    //显示详情节目
    handleView: function (index, row) {
        this.$router.push("/order/detail?id="+row.id);
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
    //显示编辑界面
    handleShipping: function (index, row) {
      this.shippingFormVisible = true;
      this.shippingLoading = false;
      this.shippingForm={};
      this.shippingForm.orderId=row.id;
    },
    shippingSubmit: function () {
      this.$refs.shippingForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.shippingLoading = true;
            //NProgress.start();
            let para = Object.assign({}, this.shippingForm);
             saveShippingEntity(para).then((res) => {
              this.shippingLoading = false;
              //NProgress.done();
              this.$message({
                message: '提交成功',
                type: 'success'
              });
              this.$refs['shippingForm'].resetFields();
              this.shippingFormVisible = false;
              this.getList();
            });
          });
        }
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
</style>

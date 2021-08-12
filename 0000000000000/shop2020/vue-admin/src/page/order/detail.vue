<template>
  <div class="detail-container">
    <el-card class="detail-container" shadow="never" style="margin-top: 15px">
      <el-steps :active="active" align-center finish-status="success">
        <el-step title="提交订单" description="2019-12-02 12:00"></el-step>
        <el-step title="支付订单"></el-step>
        <el-step title="平台发货"></el-step>
        <el-step title="确认收货"></el-step>
        <el-step title="完成评价"></el-step>
      </el-steps>

      <div class="el-card__body">
        <div slot="header" class="clearfix">
          <span class="color-info">订单状态：{{order_statusMap[orderInfo.status]}}</span>
            <el-button size="small" @click="handleOrderNote()" style="float: right;margin: 0px 3px;" v-has="'sys:order:remark'">备注订单</el-button>
            <el-button size="small" @click="cancel()"  style="float: right;margin: 0px 3px;" v-if="orderInfo.status == '0'" v-has="'sys:order:cancel'">取消订单</el-button>
            <!-- <el-button size="small" style="float: right;margin: 0px 3px;" v-if="orderInfo.status == '0'&&orderInfo.pay== '1'">物流发货</el-button> -->
            <el-button size="small" @click="handleEditAddress()" style="float: right;margin: 0px 3px;" v-if="orderInfo.status != '-1'" v-has="'sys:order:address'">修改地址</el-button>
            <el-dropdown style="float: right;margin: 0px 3px;" @command="pay" v-has="'sys:order:pay'">
              <el-button size="small" v-if="orderInfo.status != '-1'">修改付款状态<i class="el-icon-arrow-down el-icon--right"></i></el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item v-for="item in order_payList" :key="item.value" :command="item.value" :disabled="item.value==orderInfo.pay">{{item.name}}</el-dropdown-item>
               </el-dropdown-menu>
            </el-dropdown>
            <el-dropdown style="float: right;margin: 0px 3px;" @command="shipping" v-has="'sys:order:shipping_status'">
              <el-button size="small" v-if="orderInfo.pay != '0'">修改发货状态<i class="el-icon-arrow-down el-icon--right"></i></el-button>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item v-for="item in shipping_statusList" :key="item.value" :command="item.value" :disabled="item.value==orderInfo.shippingStatus">{{item.name}}</el-dropdown-item>
               </el-dropdown-menu>
            </el-dropdown>
          </div>
      </div>
      <h4 id="table-methods">
        <i class="el-icon-info"></i>基本信息
      </h4>
      <div class="table-layout">
        <div class="el-row">
          <div class="table-cell-title el-col el-col-4">订单编号</div>
          <div class="table-cell-title el-col el-col-4">订单类型</div>
          <div class="table-cell-title el-col el-col-4">支付方式</div>
          <div class="table-cell-title el-col el-col-4">下单时间</div>
          <div class="table-cell-title el-col el-col-4">支付时间</div>
          <div class="table-cell-title el-col el-col-4">用户账号</div>
        </div>
        <div class="el-row">
          <div class="table-cell el-col el-col-4">{{orderInfo.orderNo}}</div>
          <div class="table-cell el-col el-col-4">{{orderInfo.name}}</div>
          <div class="table-cell el-col el-col-4">{{order_pay_typeMap[orderInfo.payType]}}</div>
          <div class="table-cell el-col el-col-4">{{orderInfo.dateline}}</div>
          <div class="table-cell el-col el-col-4">{{orderInfo.payTime}}</div>
          <div class="table-cell el-col el-col-4">{{orderInfo.unifiedUser.username}}</div>
        </div>
        <div class="el-row">
          <div class="table-cell-title el-col el-col-4">是否支付</div>
          <div class="table-cell-title el-col el-col-4">是否发货</div>
          <div class="table-cell-title el-col el-col-4">快递名称</div>
          <div class="table-cell-title el-col el-col-4">快递单号</div>
          <div class="table-cell-title el-col el-col-4">自动确认收货时间</div>
          <div class="table-cell-title el-col el-col-4">是否评价</div>
        </div>
        <div class="el-row">
          <div class="table-cell el-col el-col-4">{{order_payMap[orderInfo.pay]}}</div>
          <div class="table-cell el-col el-col-4">{{shipping_statusMap[orderInfo.shippingStatus]}}</div>
          <div class="table-cell el-col el-col-4">
            <span v-if="orderInfo.shippings!=null&&orderInfo.shippings.length>0">{{orderInfo.shippings[0].name}}</span>
          </div>
          <div class="table-cell el-col el-col-4">
            <span v-if="orderInfo.shippings!=null&&orderInfo.shippings.length>0">{{orderInfo.shippings[0].express}}</span>
          </div>
          <div class="table-cell el-col el-col-4">{{orderInfo.shippingTime}}</div>
          <div class="table-cell el-col el-col-4">{{orderInfo.comment}}</div>
        </div>
      </div>
      <h4 id="table-methods">
        <i class="el-icon-info"></i>商品信息
      </h4>

      <el-table :data="orderInfo.productJSON.goodsData" border fit highlight-current-row style="width: 100%;margin-top: 15px">
         <el-table-column align="center" label="商品图片">
          <template slot-scope="scope">
             <img v-bind:src="scope.row.img" style="height:50px;">
          </template>
        </el-table-column>
        <el-table-column  align="center" label="商品名称">
          <template slot-scope="scope">
            <span>{{ scope.row.name }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="价格">
          <template slot-scope="scope">
            <span>{{ scope.row.price }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="规格">
          <template slot-scope="scope">
            <span>{{ scope.row.remark }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="数量">
          <template slot-scope="scope">
            <span>{{ scope.row.num }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="小计">
          <template slot-scope="scope">
            <span>¥{{ scope.row.price*scope.row.num }}</span>
          </template>
        </el-table-column>
      </el-table>

      <h4 id="table-methods">
        <i class="el-icon-info"></i>收货信息
      </h4>
      <div class="table-layout">
        <div class="el-row">
          <div class="table-cell-title el-col el-col-8">收货人</div>
          <div class="table-cell-title el-col el-col-8">手机号码</div>
          <div class="table-cell-title el-col el-col-8">收货地址</div>
          </div>
        <div class="el-row">
          <div class="table-cell el-col el-col-8">{{orderInfo.productJSON.addressData.name}}</div>
          <div class="table-cell el-col el-col-8">{{orderInfo.productJSON.addressData.phone}}</div>
          <div class="table-cell el-col el-col-8">{{orderInfo.productJSON.addressData.address}}{{orderInfo.productJSON.addressData.area}}</div>
        </div>
      </div>

      <h4 id="table-methods">
        <i class="el-icon-info"></i>备注
      </h4>
      <div class="clearfix">
        <span class="color-danger">{{orderInfo.remark}}</span>
      </div>

      <h4 id="table-methods">
        <i class="el-icon-info"></i>费用信息
      </h4>
      <div class="table-layout">
        <div class="el-row">
          <div class="table-cell-title el-col el-col-8">商品合计</div>
          <div class="table-cell-title el-col el-col-8">运费</div>
          <div class="table-cell-title el-col el-col-8">活动优惠</div>
         </div>
        <div class="el-row">
          <div class="table-cell el-col el-col-8">¥{{orderInfo.total}}</div>
          <div class="table-cell el-col el-col-8"></div>
          <div class="table-cell el-col el-col-8"></div>
        </div>
        <div class="el-row">
          <div class="table-cell-title el-col el-col-8">总金额</div>
          <div class="table-cell-title el-col el-col-8">应付款金额</div>
          <div class="table-cell-title el-col el-col-8">实际支付</div>
         </div>
        <div class="el-row">
          <div class="table-cell el-col el-col-8"><span class="color-danger">¥{{orderInfo.total}}</span></div>
          <div class="table-cell el-col el-col-8"><span class="color-danger">¥{{orderInfo.price}}</span></div>
          <div class="table-cell el-col el-col-8"></div>
        </div>
      </div>

      <h4 id="table-methods">
        <i class="el-icon-info"></i>操作日志
      </h4>
      <el-table :data="orderInfo.orderNotes" border fit highlight-current-row style="width: 100%;margin-top: 15px">
        <el-table-column  align="center" label="操作人员">
          <template slot-scope="scope">
            <span v-if="scope.row.user!=null">{{ scope.row.user.username }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="操作时间">
          <template slot-scope="scope">
            <span>{{ scope.row.dateline }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="状态">
          <template slot-scope="scope">
            <span v-if="scope.row.order!=null">{{ order_statusMap[scope.row.order.status] }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="支付状态">
          <template slot-scope="scope">
            <span v-if="scope.row.order!=null">{{ order_payMap[scope.row.order.pay] }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="是否发货">
          <template slot-scope="scope">
            <span v-if="scope.row.order!=null">{{ shipping_statusMap[scope.row.order.shipping_status] }}</span>
          </template>
        </el-table-column>
        <el-table-column  align="center" label="备注">
          <template slot-scope="scope">
            <span>{{ scope.row.remark }}</span>
          </template>
        </el-table-column>
      </el-table>


     </el-card>


    <!--编辑界面-->
		<el-dialog title="修改收货地址" :visible.sync="editFormVisible" :close-on-click-modal="false">
			<el-form :model="addressData" label-width="130px" :rules="editFormRules" ref="addressData">
				<el-form-item label="收货人姓名" prop="name">
					<el-input v-model="addressData.name" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="手机号码" prop="phone">
					<el-input v-model="addressData.phone" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="地址" prop="address">
					<el-input v-model="addressData.address" auto-complete="off"></el-input>
				</el-form-item>
        <el-form-item label="区域" prop="area">
					<el-input v-model="addressData.area" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="editFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="editAddressSubmit" :loading="editLoading">提交</el-button>
			</div>
		</el-dialog>

    <el-dialog title="备注" :visible.sync="orderNoteFormVisible" :close-on-click-modal="false">
			<el-form :model="orderNoteData" label-width="130px" ref="orderNoteData">
				<el-form-item label="订单备注" prop="remark">
					<el-input type="textarea" v-model="orderNoteData.remark" auto-complete="off"></el-input>
				</el-form-item>
			</el-form>
			<div slot="footer" class="dialog-footer">
				<el-button @click.native="orderNoteFormVisible = false">取消</el-button>
				<el-button type="primary" @click.native="orderNoteSubmit">提交</el-button>
			</div>
		</el-dialog>

  </div>
</template>

<script>
import { fetchList,getEntity,deleteEntity,removeEntity,addEntity,updateEntity,cancelEntity,payEntity,shippingEntity,updateAddressEntity,saveOrderNoteEntity} from '@/api/order'

export default {
  name: 'orderDetail',
  components: { },
  data() {
    return {
       active: 1,
       order_payMap:{},//支付
       order_statusMap:{},//订单状态
       order_pay_typeMap:{},//支付方式
       order_payMap:{},//支付
       order_payList:{},//支付
       shipping_statusMap:{},
       shipping_statusList:{},
       orderInfo:{
         unifiedUser:{},
         productJSON:{
           addressData:{},
           productsData:[]
         },
         orderNoteData:null
       },
       addressData:{},//收货人地址
       editFormVisible: false,//编辑是否显示
       editLoading: false,
       editFormRules: {
          name: [
            { required: true, message: '请输入名称', trigger: 'blur' }
          ]
       },
       orderNoteFormVisible:false,//订单备注
       orderNoteData:{
         remark:""
       }
    }
  },
  created() {
     this.getData();
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
    //列表
    getData() {
      getEntity(this.$route.query.id).then(response => {
        if(response.data){
          this.orderInfo=response.data;
          //设置状态
          if(parseInt(this.orderInfo.pay)>0){
            this.active=2;
          }
          if(parseInt(this.orderInfo.shipping_status)>0){
            this.active=3;
          }
          if(parseInt(this.orderInfo.shipping_status)==3){
            this.active=4;
          }
          if(this.orderInfo.orderComment){
            this.active=5;
          }
          if(this.orderInfo.orderNotes){
            this.orderInfo.orderNotes.map(item => {
              item.order=JSON.parse(item.content);
            })
          }
        }else{
           this.$message({
              message: '订单不存在',
              type: 'error'
            });
        }
      })
    },
    cancel(){
      this.$confirm('确认取消该笔订单吗?', '提示', {
        type: 'warning'
      }).then(() => {
        let para = {
          id:this.orderInfo.id
        }
        cancelEntity(para).then((res) => {
           //NProgress.done();
          if(res.code=='200'){
            this.$message({
              message: '取消成功',
              type: 'success'
            });
          }else{
            this.$message({
              message: res.message,
              type: 'error'
            });
          }
          this.getData();
        });
      });
    },
    pay(payStatus){
       this.$confirm('确认该笔订单标记'+this.order_payMap[payStatus]+'吗?', '提示', {
        type: 'warning'
      }).then(() => {
        let para = {
          id:this.orderInfo.id,
          pay:payStatus
        }
        payEntity(para).then((res) => {
           //NProgress.done();
          if(res.code=='200'){
            this.$message({
              message: res.message,
              type: 'success'
            });
          }else{
            this.$message({
              message: res.message,
              type: 'error'
            });
          }
          this.getData();
        });
      });
    },
    shipping(status){
       this.$confirm('确认该笔订单标记'+this.shipping_statusMap[status]+'吗?', '提示', {
        type: 'warning'
      }).then(() => {
        let para = {
          id:this.orderInfo.id,
          shipping_status:status
        }
        shippingEntity(para).then((res) => {
           //NProgress.done();
          if(res.code=='200'){
            this.$message({
              message: res.message,
              type: 'success'
            });
          }else{
            this.$message({
              message: res.message,
              type: 'error'
            });
          }
          this.getData();
        });
      });
    },
    handleEditAddress(){
      this.editFormVisible = true;
      this.addLoading = false;
      this.addressData=Object.assign({}, this.orderInfo.productJSON.addressData);
    },
    editAddressSubmit: function () {
      this.$refs.addressData.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.editLoading = true;
            //NProgress.start();
            let para = Object.assign({}, this.addressData);
             updateAddressEntity({id:this.orderInfo.id,addressData:para}).then((res) => {
              this.editLoading = false;
              //NProgress.done();
              this.$message({
                message: '提交成功',
                type: 'success'
              });
              this.$refs['addressData'].resetFields();
              this.editFormVisible = false;
              this.getData();
            });
          });
        }
      });
    },
    handleOrderNote(){
       this.orderNoteFormVisible = true;
    },
    //添加备注
    orderNoteSubmit(){
      this.$confirm('确认提交吗？', '提示', {}).then(() => {
           //NProgress.start();
          let para = Object.assign({}, this.orderNoteData);
          para.orderId=this.orderInfo.id;
          saveOrderNoteEntity(para).then((res) => {
             //NProgress.done();
            this.$message({
              message: '提交成功',
              type: 'success'
            });
            this.orderNoteFormVisible = false;
            this.getData();
          });
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
.table-layout{
 margin-top:20px;
 border-left:1px solid #dcdfe6;
 border-top:1px solid #dcdfe6
}
.table-cell {
 height:60px;
 line-height:40px;
 color:#606266;
 overflow:hidden
}
.table-cell-title,
.table-cell {
 border-right:1px solid #dcdfe6;
 border-bottom:1px solid #dcdfe6;
 padding:10px;
 font-size:14px;
 text-align:center
}
.table-cell-title {
 background:#f2f6fc;
 color:#303133
}
.el-dropdown {
  vertical-align: top;
}
.el-dropdown + .el-dropdown {
  margin-left: 15px;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>

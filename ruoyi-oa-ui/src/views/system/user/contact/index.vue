<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--部门数据-->
      <el-col :span="4" :xs="24">
        <div class="head-container">
          <el-input v-model="deptName" placeholder="请输入部门名称" clearable size="small" prefix-icon="el-icon-search" style="margin-bottom: 20px" />
        </div>
        <div class="head-container">
          <el-tree
            :data="deptOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :filter-node-method="filterNode"
            ref="tree"
            node-key="id"
            default-expand-all
            highlight-current
            @node-click="handleNodeClick"
          />
        </div>
      </el-col>
      <!--用户数据-->
      <el-col :span="20" :xs="24">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="68px" @submit.native.prevent>
          <el-form-item label="用户名" prop="nickName">
            <el-input
              v-model="queryParams.nickName"
              placeholder="请输入用户名"
              clearable
              style="width: 240px"
              @clear="handleQuery"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-divider />

        <el-table v-loading="loading" :data="userList" v-show="total>0">
          <el-table-column label align="center" prop="avatar" width="60">
            <template slot-scope="scope">
              <div class="avatar">
                <image-preview :src="scope.row.avatar" />
              </div>
            </template>
          </el-table-column>
          <el-table-column label="用户名" align="left" key="nickName" prop="nickName" width="160" show-overflow-tooltip />
          <el-table-column label="部门" align="left" key="deptName" prop="dept.deptName" show-overflow-tooltip />
          <el-table-column label="手机号码" align="left" key="phonenumber" prop="phonenumber" width="160" />
          <el-table-column label="邮箱" align="left" key="email" prop="email" width="220" />
          <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="120">
            <template slot-scope="scope">
              <el-button size="mini" type="text" icon="el-icon-view" @click="preview(scope.row)">详情</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />
      </el-col>
    </el-row>

    <DialogDetail :open="open" :userId="userId" @close="close" />
  </div>
</template>

<script>
import { listDeptUser, corpTree, getUser } from "@/api/system/user";
import DialogDetail from "./detail";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "ContactBook",
  dicts: ["sys_user_sex"],
  components: { Treeselect, DialogDetail },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      // 用户表格数据
      userList: null,
      // 部门树选项
      deptOptions: undefined,
      // 部门名称
      deptName: undefined,
      defaultProps: {
        children: "children",
        label: "label",
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        nickName: undefined,
        deptId: this.$store.state.user.userInfo.dept.deptId,
      },
      open: false,
      userId: null,
    };
  },
  watch: {
    // 根据名称筛选部门树
    deptName(val) {
      this.$refs.tree.filter(val);
    },
  },
  created() {
    this.getList();
    this.getTree();
  },
  methods: {
    /** 查询用户列表 */
    getList() {
      this.loading = true;
      listDeptUser(this.queryParams).then((response) => {
        this.userList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询部门下拉树结构 */
    getTree() {
      corpTree().then((response) => {
        this.deptOptions = response.data;
      });
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id;
      this.handleQuery();
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.queryParams.deptId = undefined;
      this.$refs.tree.setCurrentKey(null);
      this.handleQuery();
    },
    preview(row) {
      this.open = true;
      this.userId = row.userId;
    },
    close() {
      this.userId = null;
      this.open = false;
    },
  },
};
</script>

<style lang="scss" scoped>
.avatar {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  overflow: hidden;
  margin: 0 auto;

  img {
    width: 100%;
    height: 100%;
  }

  &:hover img {
    transform: scale(1.1);
  }
}
</style>
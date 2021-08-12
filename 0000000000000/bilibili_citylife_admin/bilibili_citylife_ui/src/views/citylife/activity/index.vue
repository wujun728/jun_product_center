<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="发布人id" prop="pbid">
        <el-input
          v-model="queryParams.pbid"
          placeholder="请输入发布人id"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入活动标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="举办时间" prop="time">
        <el-date-picker clearable size="small" style="width: 200px"
          v-model="queryParams.time"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择举办时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动地点" prop="position">
        <el-input
          v-model="queryParams.position"
          placeholder="请输入活动地点"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>


      <el-form-item>
        <el-button type="cyan" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['citylife:activity:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['citylife:activity:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['citylife:activity:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['citylife:activity:export']"
        >导出</el-button>
      </el-col>
      <div class="top-right-btn">
        <el-tooltip class="item" effect="dark" content="刷新" placement="top">
          <el-button size="mini" circle icon="el-icon-refresh" @click="handleQuery" />
        </el-tooltip>
        <el-tooltip class="item" effect="dark" :content="showSearch ? '隐藏搜索' : '显示搜索'" placement="top">
          <el-button size="mini" circle icon="el-icon-search" @click="showSearch=!showSearch" />
        </el-tooltip>
      </div>
    </el-row>

    <el-table v-loading="loading" :data="activityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动id" align="center" prop="id" />
      <el-table-column label="发布人id" align="center" prop="pbid" />
      <el-table-column label="活动标题" align="center" prop="title" />
      <el-table-column label="举办时间" align="center" prop="time" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.time, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动地点" align="center" prop="position" />
      <el-table-column label="活动标签" align="center" prop="label" />
      <el-table-column label="主图" align="center">
        <template slot-scope="scope">
          <el-image :src="scope.row.img" style="width: 50px;height: 50px" fit="cover"/>
        </template>
      </el-table-column>
      <el-table-column label="活动详情" align="center">
        <template slot-scope="scope">
          <span style="display: -webkit-box;
					-webkit-line-clamp: 6;
					-webkit-box-orient: vertical;
					overflow: hidden;">{{scope.row.detail}}</span>
        </template>
      </el-table-column>
      <el-table-column label="浏览数" align="center" prop="views" />
      <el-table-column label="收藏数" align="center" prop="collections" />
      <el-table-column label="点赞数" align="center" prop="likes" />
      <el-table-column label="点踩数" align="center" prop="unlikes" />
      <el-table-column label="评论数" align="center" prop="comments" />
      <el-table-column label="审核与否" align="center" prop="checked" :formatter="formatterLogic"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['citylife:activity:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['citylife:activity:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改活动对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="发布人id" prop="pbid">
          <el-input v-model="form.pbid" placeholder="请输入发布人id" />
        </el-form-item>
        <el-form-item label="活动标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入活动标题" />
        </el-form-item>
        <el-form-item label="举办时间" prop="time">
          <el-date-picker clearable size="small" style="width: 200px"
            v-model="form.time"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择举办时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动地点" prop="position">
          <el-input v-model="form.position" placeholder="请输入活动地点" />
        </el-form-item>
        <el-form-item label="活动标签" prop="label">
          <el-input v-model="form.label" placeholder="请输入活动标签" />
        </el-form-item>
        <el-form-item label="主图" prop="img">
          <el-input v-model="form.img" placeholder="请输入主图" />
        </el-form-item>
        <el-form-item label="活动详情" prop="detail">
          <el-input v-model="form.detail" placeholder="请输入活动详情" />
        </el-form-item>
        <el-form-item label="浏览数" prop="views">
          <el-input v-model="form.views" placeholder="请输入浏览数" />
        </el-form-item>
        <el-form-item label="收藏数" prop="collections">
          <el-input v-model="form.collections" placeholder="请输入收藏数" />
        </el-form-item>
        <el-form-item label="点赞数" prop="likes">
          <el-input v-model="form.likes" placeholder="请输入点赞数" />
        </el-form-item>
        <el-form-item label="点踩数" prop="unlikes">
          <el-input v-model="form.unlikes" placeholder="请输入点踩数" />
        </el-form-item>
        <el-form-item label="评论数" prop="comments">
          <el-input v-model="form.comments" placeholder="请输入评论数" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listActivity, getActivity, delActivity, addActivity, updateActivity, exportActivity } from "@/api/citylife/activity";

export default {
  name: "Activity",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 活动表格数据
      activityList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        pbid: null,
        title: null,
        time: null,
        position: null,
        label: null,
        img: null,
        detail: null,
        views: null,
        collections: null,
        likes: null,
        unlikes: null,
        comments: null,
        checked: null,
        deleted: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      logicOptions:[]
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_logic").then(response => {
      this.logicOptions = response.data;
    });
  },
  methods: {
    /** 查询活动列表 */
    getList() {
      this.loading = true;
      listActivity(this.queryParams).then(response => {
        this.activityList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        pbid: null,
        title: null,
        time: null,
        position: null,
        label: null,
        img: null,
        detail: null,
        views: null,
        collections: null,
        likes: null,
        unlikes: null,
        comments: null,
        checked: null,
        deleted: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加活动";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getActivity(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改活动";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateActivity(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              }
            });
          } else {
            addActivity(this.form).then(response => {
              if (response.code === 200) {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              }
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm('是否确认删除活动编号为"' + ids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delActivity(ids);
        }).then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        }).catch(function() {});
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有活动数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportActivity(queryParams);
        }).then(response => {
          this.download(response.msg);
        }).catch(function() {});
    },
    formatterLogic(row, column) {
      return row.checked ? '是' : '否';
    }
  }
};
</script>


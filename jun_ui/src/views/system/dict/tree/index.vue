<template>
  <div class="app-container">
    <el-form :model="queryParams" class="queryFormClass"  ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="类别" prop="struType">
        <!--  (0:平铺结构 1:树型结构)-->
        <el-select v-model="queryParams.struType" placeholder="请选择类别" clearable size="mini">
          <el-option
            v-for="dict in struTypeOptions"
            :key="dict.dictValue"
            :label="dict.dictLabel"
            :value="dict.dictValue"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          size="mini"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="编码" prop="code">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入编码"
          clearable
          size="mini"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> <el-divider></el-divider>


    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:tree:dict:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:tree:dict:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:tree:dict:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:tree:dict:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-divider></el-divider>

    <el-table stripe border v-loading="loading" class="tableClass"   :data="treeDictList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="名称" align="center" prop="name">
      </el-table-column>
      <el-table-column label="编码" align="center" prop="code">
        <template slot-scope="scope">
          <router-link :to="'/tree/dict/type/data/' + scope.row.code" class="link-type">
            <span>{{ scope.row.code }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="类别" align="center" prop="struType" :formatter="struTypeFormat" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" fixed="right"  align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:tree:dict:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:tree:dict:remove']"
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

    <!-- 添加或修改系统分组对话框 -->
    <el-dialog v-drag-dialog="true" :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-col :span="24">
          <el-form-item label="名称" prop="name" size="mini">
            <el-input v-model="form.name" placeholder="请输入名称" />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="编码" prop="code" size="mini">
            <el-input v-model="form.code" placeholder="请输入编码" />
          </el-form-item>
        </el-col>
        <el-col>
          <el-form-item label="类别" prop="struType" size="mini">
            <el-select v-model="form.struType" placeholder="请选择类别" clearable size="mini">
              <el-option
                v-for="dict in struTypeOptions"
                :key="dict.dictValue"
                :label="dict.dictLabel"
                :value="dict.dictValue"
              />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item label="备注" prop="remark" size="mini">
            <el-input type="textarea" v-model="form.remark" placeholder="请输入备注" />
          </el-form-item>
        </el-col>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :disabled="this.submitFlag" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTreeDict, getTreeDict, delTreeDict, addTreeDict, updateTreeDict, exportTreeDict } from "@/api/system/dict/tree/type";

export default {
  name: "sysTreeDict",
  components: {
  },
  data () {
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
      // 系统分组表格数据
      treeDictList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 类别(0：树型结构；1：平铺结构)字典
      struTypeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        name: null,
        code: null,
        struType: null,
        remark: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "名称不能为空", trigger: "blur" },
          { pattern: /^[\u4e00-\u9fa5]+$/, message: "请输入中文", trigger: ["blur", "change"] }

        ],
        code: [
          { required: true, message: "编码不能为空", trigger: "blur" },
          { pattern: /^[A-Za-z]/, message: "请输入英文", trigger: ["blur", "change"] }
        ],
        struType: [
          { required: true, message: "类型不能为空", trigger: "blur" }
        ],
        remark: [
          { min: 2, max: 100, message: "长度在 2 到 100 个字符", trigger: ["blur", "change"] }
        ],
      },
      submitFlag: false
    };
  },
  created() {
    this.getList();

    this.getDicts("tree_dict_stru_type").then(response => {
      this.struTypeOptions = response.data;
    });

  },
  methods: {
    /** 查询系统分类组列表 */
    getList() {
      this.loading = true;
      listTreeDict(this.queryParams).then(response => {
        this.treeDictList = response.rows;
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
        name: null,
        code: null,
        struType: null,
        remark: null
      };
      this.resetForm("form");
    },

    // 任务组名字典翻译
    struTypeFormat(row, column) {
      return this.selectDictLabel(this.struTypeOptions, row.struType);
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
      this.submitFlag = false;
      this.reset();
      this.open = true;
      this.title = "添加分类组";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.submitFlag = false;
      this.reset();
      const id = row.id || this.ids
      getTreeDict(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改系统分类组";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.submitFlag = true
          if (this.form.id != null) {
            updateTreeDict(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTreeDict(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const sids = row.id || this.ids;
      this.$confirm('是否确认删除系统分类组编号为"' + sids + '"的数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return delTreeDict(sids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有系统分类组数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportTreeDict(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>

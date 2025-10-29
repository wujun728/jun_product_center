<template>
  <div class="app-container">
    <el-form :model="queryParams" class="queryFormClass"  ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <!--      分类标识-->
      <el-form-item label="分类标识" prop="treeDict">
        <el-select v-model="queryParams.treeDict" placeholder="请选择分类组" clearable size="mini" :disabled=true>
          <el-option
            v-for="dict in treeDictOptions"
            :key="dict.treeDict"
            :label="dict.name"
            :value="dict.treeDict"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="父节点" prop="pid" size="mini">
        <el-input
          v-model="queryParams.pid"
          placeholder="请输入父节点"
          clearable
          size="mini"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="名称" prop="label">
        <el-input
          v-model="queryParams.label"
          placeholder="请输入名称"
          clearable
          size="mini"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="编码" prop="code">
        <el-input
          v-model="queryParams.code"
          placeholder="请输入编码"
          clearable
          size="mini"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item size="mini">
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
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:tree:dict:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:tree:dict:remove']"
        >删除
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-divider></el-divider>

    <el-table stripe border v-loading="loading" class="tableClass"
      :data="treeDictDataList"
      row-key="id"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="名称" prop="label"/>
      <el-table-column label="编码" align="center" prop="code"/>
      <el-table-column label="显示顺序" align="center" prop="orderNum"/>
      <el-table-column label="叶子节点" align="center" prop="isLeaf">
        <template slot-scope="scope">
          <el-tag v-if="scope.row.isLeaf === '1'" type="success">是</el-tag>
          <el-tag v-else type="danger">否</el-tag>
        </template>
      </el-table-column>
      <!--      <el-table-column label="层次码" align="center" prop="levelCode"/>-->
      <!--      <el-table-column label="层次" align="center" prop="levelDepth"/>-->
      <el-table-column label="备注" align="center" prop="remark"/>
      <el-table-column label="操作" fixed="right"  align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:tree:dict:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:tree:dict:remove']"
          >删除
          </el-button>
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

    <!-- 添加或修改树形字典数据对话框 -->
    <el-dialog v-drag-dialog="true" :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="24">
            <el-form-item label="父节点" prop="pid" size="small">
              <treeselect v-model="form.pid" :options="parentOptions" :show-count="true"
                          placeholder="请选择父节点"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="名称" prop="label" size="mini">
              <el-input v-model="form.label" placeholder="请输入名称"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="编码" prop="code" size="mini">
              <el-input v-model="form.code" placeholder="请输入编码"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="排序" prop="orderNum" size="mini">
              <el-input v-model="form.orderNum" placeholder="请输入排序"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="图标" prop="icon" size="mini">
              <el-input v-model="form.icon" placeholder="请输入图标"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="备注" prop="icon" size="mini">
              <el-input v-model="form.remark" placeholder="请输入图标"/>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="层次" prop="levelDepth" size="mini">
              <el-input v-model="form.levelDepth" placeholder="请输入层次" :disabled="true"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
  import {
    listTreeDictData,
    getTreeDictData,
    delTreeDictData,
    addTreeDictData,
    updateTreeDictData,
    exportTreeDictData,
    treeTreeDictData,
    genAutoInfo,
    checkUniqueByLabel,
    checkCode
  } from "@/api/system/dict/tree/data";

  import Treeselect from "@riophae/vue-treeselect";
  import {validChinese, validNumeric} from "@/utils/rules";
  import "@riophae/vue-treeselect/dist/vue-treeselect.css";

  export default {
    name: "SysTreeDictData",
    components: {
      Treeselect
    },
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 选中对象数组
        items: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 树形字典数据表格数据
        treeDictDataList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 是否展开，默认全部展开
        isExpandAll: true,
        // 主键字典
        sidOptions: [],
        // 树形类别字典
        treeDictOptions: [],
        // 分类名称字典
        nameOptions: [],
        // 节点的分类Key字典
        dataKeyOptions: [],
        // 类别(0:平铺结构 1:树型结构)字典
        struTypeOptions: [],
        // 父节点字典
        parentOptions: [],
        // 树形字典编码
        treeDictCode: "",
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          id: null,
          treeDict: null,
          pid: null,
          code: null,
          label: null,
          levelCode: null,
          levelDepth: null,
          orderNum: null,
          isLeaf: null,
          path: null,
          icon: null,
          remark: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
          delFlag: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          label: [
            {required: true, message: "名称不能为空", trigger: "blur"},
            {validator: validChinese, message: "请输入中文"},
            {validator: this.checkUniqueByLabel, trigger: "change"}
          ],
          code: [
            {required: true, message: "编码不能为空", trigger: "blur"},
            {validator: validNumeric, message: "编码必须为数字"},
            //{validator: this.checkCode, trigger: "blur"}
          ],
          orderNum: [
            {required: true, message: "排序不能为空", trigger: "blur"}
          ]
        }
      };
    },
    created() {
      const treeDictCode = this.$route.params && this.$route.params.dictId;
      this.queryParams.treeDict = treeDictCode;
      this.treeDictCode = treeDictCode;

      this.getList(treeDictCode);
    },
    methods: {
      /** 查询树形字典数据列表 */
      getList(dictId) {
        this.loading = true;
        this.queryParams.treeDict = dictId;
        listTreeDictData(this.queryParams).then(response => {
          this.treeDictDataList = response.data;
          this.loading = false;
        });
      },

      // 父节点字典翻译
      pidFormat(row, column) {
        return this.selectTreeDictLabel(this.parentOptions, row.pid);
      },

      // 取消按钮
      cancel() {
        this.open = false;
        this.reset(this.treeDictCode);
        this.getList(this.treeDictCode);
      },
      // 表单重置
      reset(treeDictCode) {
        this.form = {
          id: null,
          treeDict: treeDictCode,
          pid: null,
          code: null,
          label: null,
          levelCode: null,
          levelDepth: null,
          orderNum: null,
          isLeaf: null,
          path: null,
          icon: null
        };
        this.resetForm("form");
      },

      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList(this.treeDictCode);
      },

      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },

      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.items = selection.map(item => item)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },

      /** 新增按钮操作 */
      handleAdd() {
        this.reset(this.treeDictCode);
        this.getTreeSelect();
        //this.genAutoInfo();

        this.open = true;
        this.title = "添加树形字典数据";
      },
      /** fsd 修改按钮操作 */
      handleUpdate(row) {
        this.reset(this.treeDictCode);
        this.getTreeSelect();

        this.form = row;
        if (this.form.pid === '0') {
          this.form.pid = null;
        }
        this.open = true;
        this.title = "修改树形字典数据";
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateTreeDictData(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList(this.treeDictCode);
              });
            } else {
              addTreeDictData(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList(this.treeDictCode);
              });
            }
          }
        });
      },

      /** 删除按钮操作 */
      handleDelete(row) {
        let delFlag = true;

        const ids = row.id || this.ids;
        if (row.children) {
          this.$confirm('节点”' + row.label + '” 下存在子节点?', "警告", {
              confirmButtonText: "确定",
              cancelButtonText: "取消",
              type: "warning"
            }
          )
          delFlag = false;
        }
        if (this.items.length > 0) {
          this.items.forEach(item => {
            if (item.children) {
              this.$confirm('节点”' + item.label + '” 下存在子节点?', "警告", {
                  confirmButtonText: "确定",
                  cancelButtonText: "取消",
                  type: "warning"
                }
              )
              delFlag = false;
            }
          })
        }
        if (delFlag) {
          this.$confirm('是否确认删除树形字典数据?', "警告", {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }).then(function () {
            return delTreeDictData(ids);
          }).then(() => {
            this.getList(this.treeDictCode);
            this.$modal.msgSuccess("删除成功");
          })
        }
      },
      /** 导出按钮操作 */
      handleExport() {
        const queryParams = this.queryParams;
        this.$confirm('是否确认导出所有树形字典数据数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function () {
          return exportSysTreeDictData(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
      },

      /** fsd 查询下拉树结构 */
      getTreeSelect(treeDict) {
        if (!this.form.treeDict) {
          this.form = {"treeDict": treeDict}
        }
        treeTreeDictData(this.form).then(response => {
          this.parentOptions = response.data;
        });
      },

      /** fsd 自动生成代码 */
      genAutoInfo() {
        genAutoInfo(this.form).then(response => {
          this.form = response.data
        });
      },

      /** fsd 自动生成代码 */
      changeParent(pid) {
        this.form.pid = pid;

        genAutoInfo(this.form).then(response => {
          this.form = response.data
        });
      },

      /** fsd 检查名称唯一性 */
      checkUniqueByLabel(rule, value, callback) {
        checkUniqueByLabel(this.form).then(response => {
          if (response.code == 200) {
            callback();
          } else {
            callback(new Error(response.data))
          }
        });
      },

      /** fsd 校验层次码 */
      checkCode(rule, value, callback) {
        checkCode(this.form).then(response => {
          if (response.code == 200) {
            this.form = response.data
            callback();
          } else {
            callback(new Error(response.data))
          }
        });
      },
    }
  };
</script>

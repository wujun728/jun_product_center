<template>
  <div class="container">
    <div class="left-board">
      <div class="logo-wrapper">
        <div class="logo">
          <img :src="logo" alt="logo" /> 表单生成器
        </div>
      </div>
      <el-scrollbar class="left-scrollbar">
        <div class="components-list">
          <div v-for="(item, listIndex) in leftComponents" :key="listIndex">
            <div class="components-title">
              <svg-icon icon-class="component" />
              {{ item.title }}
            </div>
            <draggable
              class="components-draggable"
              :list="item.list"
              :group="{ name: 'componentsGroup', pull: 'clone', put: false }"
              :clone="cloneComponent"
              draggable=".components-item"
              :sort="false"
              @end="onEnd"
            >
              <div v-for="(element, index) in item.list" :key="index" class="components-item" @click="addComponent(element)">
                <div class="components-body">
                  <svg-icon :icon-class="element.__config__.tagIcon" />
                  {{ element.__config__.label }}
                </div>
              </div>
            </draggable>
          </div>
        </div>
      </el-scrollbar>
    </div>

    <div class="center-board">
      <div class="action-bar">
        <el-button icon="el-icon-plus" type="primary" size="mini" plain @click="handleForm">保存</el-button>
        <el-button icon="el-icon-view" type="success" size="mini" plain @click="showJson">查看json</el-button>
        <el-button icon="el-icon-download" type="warning" size="mini" plain @click="download">导出vue文件</el-button>
        <el-button class="copy-btn-main" icon="el-icon-document-copy" type="info" size="mini" plain @click="copy">复制代码</el-button>
        <el-button class="delete-btn" icon="el-icon-delete" type="danger" size="mini" plain @click="empty">清空</el-button>
      </div>
      <el-scrollbar class="center-scrollbar">
        <el-row class="center-board-row" :gutter="formConf.gutter">
          <el-form
            :size="formConf.size"
            :label-position="formConf.labelPosition"
            :disabled="formConf.disabled"
            :label-width="formConf.labelWidth + 'px'"
          >
            <draggable class="drawing-board" :list="drawingList" :animation="340" group="componentsGroup">
              <draggable-item
                v-for="(item, index) in drawingList"
                :key="item.renderKey"
                :drawing-list="drawingList"
                :current-item="item"
                :index="index"
                :active-id="activeId"
                :form-conf="formConf"
                @activeItem="activeFormItem"
                @copyItem="drawingItemCopy"
                @deleteItem="drawingItemDelete"
              />
            </draggable>
            <div v-show="!drawingList.length" class="empty-info">
              <div class="title1">
                <i class="el-icon-bottom"></i>
                <i class="el-icon-bottom"></i>
                <i class="el-icon-bottom"></i>
                请仔细阅读
                <i class="el-icon-bottom"></i>
                <i class="el-icon-bottom"></i>
                <i class="el-icon-bottom"></i>
              </div>
              <div class="title2">配置说明</div>
              <div>1、从左侧拖入或点选组件进行表单设计；</div>
              <div>2、请不要使用上传组件，模板配置中已提供单独的页签存放附件；如确有需求，请自行实现上传；</div>
              <div>
                3、每个动态表单，必须包含一个字段属性为:
                <span style="color:red; font-size: 20px;font-weight: 600;">title</span>的字段，用于在待办、已办、详情页展示；
              </div>
              <div>4、若无法满足需求，您可以先配置大部分表单属性，然后通过顶部按钮->导出vue，基于此文件补充。</div>
            </div>
          </el-form>
        </el-row>
      </el-scrollbar>
    </div>

    <right-panel :active-data="activeData" :form-conf="formConf" :show-field="!!drawingList.length" @tag-change="tagChange" @fetch-data="fetchData" />

    <form-drawer :visible.sync="drawerVisible" :form-data="formData" size="100%" :generate-conf="generateConf" />
    <json-drawer size="60%" :visible.sync="jsonDrawerVisible" :json-str="JSON.stringify(formData)" @refresh="refreshJson" />
    <code-type-dialog :visible.sync="dialogVisible" title="选择生成类型" :show-file-name="showFileName" @confirm="generate" />
    <input id="copyNode" type="hidden" />
    <!--表单配置详情-->
    <el-dialog :title="formTitle" :visible.sync="formOpen" width="800px" append-to-body>
      <el-divider />
      <el-form ref="form" :model="form" :rules="rules" label-width="108px">
        <el-form-item label="表单名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入表单名称" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" :rows="4" placeholder="请输入备注" />
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
import draggable from "vuedraggable";
import { debounce } from "throttle-debounce";
import { saveAs } from "file-saver";
import ClipboardJS from "clipboard";
import render from "@/components/render/render";
import FormDrawer from "./FormDrawer";
import JsonDrawer from "./JsonDrawer";
import RightPanel from "./RightPanel";
import { inputComponents, selectComponents, layoutComponents, orgTreeComponents, serialNoComponents, formConf } from "@/utils/generator/config";
import { exportDefault, beautifierConf, isNumberStr, titleCase, deepClone, isObjectObject } from "@/utils/index";
import { makeUpHtml, vueTemplate, vueScript, cssStyle } from "@/utils/generator/html";
import { makeUpJs } from "@/utils/generator/js";
import { makeUpCss } from "@/utils/generator/css";
import drawingDefault from "@/utils/generator/drawingDefault";
import logo from "@/assets/logo/logo.png";
import CodeTypeDialog from "./CodeTypeDialog";
import DraggableItem from "./DraggableItem";
import { getDrawingList, saveDrawingList, getIdGlobal, saveIdGlobal, getFormConf } from "@/utils/db";
import loadBeautifier from "@/utils/loadBeautifier";
import { getDynamicForm, addDynamicForm, updateDynamicForm } from "@/api/workflow/dynamicForm";
import request from "@/utils/request";

let beautifier;
const emptyActiveData = { style: {}, autosize: {} };
let oldActiveId;
let tempActiveData;
const drawingListInDB = getDrawingList();
const formConfInDB = getFormConf();
const idGlobal = getIdGlobal();

export default {
  components: {
    draggable,
    render,
    FormDrawer,
    JsonDrawer,
    RightPanel,
    CodeTypeDialog,
    DraggableItem,
  },
  data() {
    return {
      logo,
      idGlobal,
      formConf,
      inputComponents,
      selectComponents,
      layoutComponents,
      orgTreeComponents,
      serialNoComponents,
      labelWidth: 100,
      drawingList: drawingDefault,
      drawingData: {},
      activeId: drawingDefault[0].formId,
      drawerVisible: false,
      formData: {},
      dialogVisible: false,
      jsonDrawerVisible: false,
      generateConf: null,
      showFileName: false,
      activeData: drawingDefault[0],
      saveDrawingListDebounce: debounce(340, saveDrawingList),
      saveIdGlobalDebounce: debounce(340, saveIdGlobal),
      leftComponents: [
        {
          title: "输入型组件",
          list: inputComponents,
        },
        {
          title: "选择型组件",
          list: selectComponents,
        },
        {
          title: "布局型组件",
          list: layoutComponents,
        },
        {
          title: "机构及选人组件",
          list: orgTreeComponents,
        },
        {
          title: "编号组件",
          list: serialNoComponents,
        },
      ],
      formOpen: false,
      formTitle: "",
      // 表单参数
      form: {
        id: null,
        name: null,
        content: null,
        remark: null,
      },
      // 表单校验
      rules: {
        name: [{ required: true, message: "名称不能为空", trigger: "blur" }],
      },
    };
  },
  computed: {},
  watch: {
    "activeData.__config__.label": function (val, oldVal) {
      if (this.activeData.placeholder === undefined || !this.activeData.__config__.tag || oldActiveId !== this.activeId) {
        return;
      }
      this.activeData.placeholder = this.activeData.placeholder.replace(oldVal, "") + val;
    },
    activeId: {
      handler(val) {
        oldActiveId = val;
      },
      immediate: true,
    },
    drawingList: {
      handler(val) {
        this.saveDrawingListDebounce(val);
        if (val.length === 0) this.idGlobal = 100;
      },
      deep: true,
    },
    idGlobal: {
      handler(val) {
        this.saveIdGlobalDebounce(val);
      },
      immediate: true,
    },
  },

  mounted() {
    const that = this;
    if (Array.isArray(drawingListInDB) && drawingListInDB.length > 0) {
      that.drawingList = drawingListInDB;
    } else {
      that.drawingList = drawingDefault;
    }
    this.activeFormItem(that.drawingList[0]);
    that.drawingList = [];
    const formId = that.$route.query && that.$route.query.formId;
    if (formId) {
      getDynamicForm(formId).then((res) => {
        that.formConf = JSON.parse(res.data.content);
        that.drawingList = that.formConf.fields;
        that.form = res.data;
      });
    } else {
      if (formConfInDB) {
        that.formConf = formConfInDB;
      }
    }
    loadBeautifier((btf) => {
      beautifier = btf;
    });
    const clipboard = new ClipboardJS("#copyNode", {
      text: (trigger) => {
        const codeStr = this.generateCode();
        this.$notify({
          title: "成功",
          message: "代码已复制到剪切板，可粘贴。",
          type: "success",
        });
        return codeStr;
      },
    });
    clipboard.on("error", (e) => {
      this.$message.error("代码复制失败");
    });
  },
  methods: {
    setObjectValueReduce(obj, strKeys, data) {
      const arr = strKeys.split(".");
      arr.reduce((pre, item, i) => {
        if (arr.length === i + 1) {
          pre[item] = data;
        } else if (!isObjectObject(pre[item])) {
          pre[item] = {};
        }
        return pre[item];
      }, obj);
    },
    setRespData(component, resp) {
      const { dataPath, renderKey, dataConsumer } = component.__config__;
      if (!dataPath || !dataConsumer) return;
      const respData = dataPath.split(".").reduce((pre, item) => pre[item], resp);
      // 将请求回来的数据，赋值到指定属性。
      // 以el-tabel为例，根据Element文档，应该将数据赋值给el-tabel的data属性，所以dataConsumer的值应为'data';
      // 此时赋值代码可写成 component[dataConsumer] = respData；
      // 但为支持更深层级的赋值（如：dataConsumer的值为'options.data'）,使用setObjectValueReduce
      this.setObjectValueReduce(component, dataConsumer, respData);
      const i = this.drawingList.findIndex((item) => item.__config__.renderKey === renderKey);
      if (i > -1) this.$set(this.drawingList, i, component);
    },
    fetchData(component) {
      const { dataType, method, url } = component.__config__;
      if (dataType === "dynamic" && method && url) {
        this.setLoading(component, true);
        request({
          method: method,
          url: url,
        }).then((resp) => {
          this.setLoading(component, false);
          this.setRespData(component, resp);
        });
      }
    },
    setLoading(component, val) {
      const { directives } = component;
      if (Array.isArray(directives)) {
        const t = directives.find((d) => d.name === "loading");
        if (t) t.value = val;
      }
    },
    activeFormItem(currentItem) {
      this.activeData = currentItem;
      this.activeId = currentItem.__config__.formId;
    },
    onEnd(obj) {
      if (obj.from !== obj.to) {
        this.fetchData(tempActiveData);
        this.activeData = tempActiveData;
        this.activeId = this.idGlobal;
      }
    },
    addComponent(item) {
      const clone = this.cloneComponent(item);
      this.fetchData(clone);
      this.drawingList.push(clone);
      this.activeFormItem(clone);
    },
    cloneComponent(origin) {
      const clone = deepClone(origin);
      const config = clone.__config__;
      config.span = this.formConf.span; // 生成代码时，会根据span做精简判断
      this.createIdAndKey(clone);
      clone.placeholder !== undefined && (clone.placeholder += config.label);
      tempActiveData = clone;
      return tempActiveData;
    },
    createIdAndKey(item) {
      const config = item.__config__;
      config.formId = ++this.idGlobal;
      config.renderKey = `${config.formId}${+new Date()}`; // 改变renderKey后可以实现强制更新组件
      if (config.layout === "colFormItem") {
        item.__vModel__ = `field${this.idGlobal}`;
      } else if (config.layout === "rowFormItem") {
        config.componentName = `row${this.idGlobal}`;
        !Array.isArray(config.children) && (config.children = []);
        delete config.label; // rowFormItem无需配置label属性
      }
      if (Array.isArray(config.children)) {
        config.children = config.children.map((childItem) => this.createIdAndKey(childItem));
      }
      return item;
    },
    AssembleFormData() {
      this.formData = {
        ...this.formConf,
      };
      this.formData.fields = deepClone(this.drawingList);
    },
    generate(data) {
      const func = this[`exec${titleCase(this.operationType)}`];
      this.generateConf = data;
      func && func(data);
    },
    execRun(data) {
      this.AssembleFormData();
      this.drawerVisible = true;
    },
    execDownload(data) {
      const codeStr = this.generateCode();
      const blob = new Blob([codeStr], { type: "text/plain;charset=utf-8" });
      saveAs(blob, data.fileName);
    },
    execCopy(data) {
      document.getElementById("copyNode").click();
    },
    empty() {
      this.$confirm("确定要清空所有组件吗？", "提示", { type: "warning" }).then(() => {
        this.drawingList = [];
        this.idGlobal = 100;
      });
    },
    drawingItemCopy(item, list) {
      let clone = deepClone(item);
      clone = this.createIdAndKey(clone);
      list.push(clone);
      this.activeFormItem(clone);
    },
    drawingItemDelete(index, list) {
      list.splice(index, 1);
      this.$nextTick(() => {
        const len = this.drawingList.length;
        if (len) {
          this.activeFormItem(this.drawingList[len - 1]);
        }
      });
    },
    generateCode() {
      const { type } = this.generateConf;
      this.AssembleFormData();
      const script = vueScript(makeUpJs(this.formData, type));
      const html = vueTemplate(makeUpHtml(this.formData, type));
      const css = cssStyle(makeUpCss(this.formData));
      return beautifier.html(html + script + css, beautifierConf.html);
    },
    showJson() {
      this.AssembleFormData();
      this.jsonDrawerVisible = true;
    },
    download() {
      this.dialogVisible = true;
      this.showFileName = true;
      this.operationType = "download";
    },
    copy() {
      this.dialogVisible = true;
      this.showFileName = false;
      this.operationType = "copy";
    },
    tagChange(newTag) {
      newTag = this.cloneComponent(newTag);
      const config = newTag.__config__;
      newTag.__vModel__ = this.activeData.__vModel__;
      config.formId = this.activeId;
      config.span = this.activeData.__config__.span;
      this.activeData.__config__.tag = config.tag;
      this.activeData.__config__.tagIcon = config.tagIcon;
      this.activeData.__config__.document = config.document;
      if (typeof this.activeData.__config__.defaultValue === typeof config.defaultValue) {
        config.defaultValue = this.activeData.__config__.defaultValue;
      }
      Object.keys(newTag).forEach((key) => {
        if (this.activeData[key] !== undefined) {
          newTag[key] = this.activeData[key];
        }
      });
      this.activeData = newTag;
      this.updateDrawingList(newTag, this.drawingList);
    },
    updateDrawingList(newTag, list) {
      const index = list.findIndex((item) => item.__config__.formId === this.activeId);
      if (index > -1) {
        list.splice(index, 1, newTag);
      } else {
        list.forEach((item) => {
          if (Array.isArray(item.__config__.children)) this.updateDrawingList(newTag, item.__config__.children);
        });
      }
    },
    refreshJson(data) {
      this.drawingList = deepClone(data.fields);
      delete data.fields;
      this.formConf = data;
    },
    /** 表单基本信息 */
    handleForm() {
      this.formData = {
        ...this.formConf,
      };
      this.formData.fields = deepClone(this.drawingList);
      // 校验是否配置了title字段（title作为待办、已办以及详情页的展示字段，必须要配置）
      let isTitleField = this.formData.fields.some((item) => item.__vModel__ === "title");
      if (!isTitleField) {
        this.$modal.msgError("缺少字段属性：title，请添加！");
        return;
      }
      this.form.content = JSON.stringify(this.formData);
      this.formOpen = true;
      this.formTitle = "保存表单";
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        name: null,
        content: null,
        remark: null,
      };
      this.resetForm("form");
    },
    // 取消按钮
    cancel() {
      this.formOpen = false;
      this.reset();
    },
    /** 保存表单信息 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateDynamicForm(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
            });
          } else {
            addDynamicForm(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
            });
          }
          this.drawingList = [];
          this.idGlobal = 100;
          this.open = false;
          // 关闭当前标签页并返回上个页面
          const obj = { path: "/workflow/form/dynamic", query: { t: Date.now() } };
          this.$tab.closeOpenPage(obj);
        }
      });
    },
  },
};
</script>

<style lang='scss'>
@import "@/assets/custom_styles/home.scss";
.title1 {
  font-weight: 600;
}
</style>

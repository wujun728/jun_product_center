# 动态表单系统分析文档

> 基于页面 URL: `/workflow/flowForm/{timer}?templateId=9179A7B4144748F8A88A0D49111DF5C5&businessId=5C99E5611E7A4219A991826FC177326D&...`

## 1. 页面入口与路由

- **路由路径:** `/workflow/flowForm/:timer`
- **前端组件:** `ruoyi-vue-oa-ui/src/views/workflow/flow-form/index.vue`
- **URL 参数说明:**

| 参数 | 示例值 | 说明 |
|------|--------|------|
| timer | 1775038289540 | 时间戳（防缓存） |
| title | 11 | 表单标题 |
| pageType | 1 | 页面类型：0=新建, 1=审批, 2=查看 |
| todoId | E759AC10B0D74610B353BCB75049BE1C | 待办ID |
| businessId | 5C99E5611E7A4219A991826FC177326D | 业务数据ID（对应 t_workflow_form.id） |
| procInsId | 1082548 | Flowable 流程实例ID |
| templateId | 9179A7B4144748F8A88A0D49111DF5C5 | 模板ID |
| taskId | 1085004 | Flowable 任务ID |
| userId | 938A5DCF528F4A6FA7D27E43C8D2E363 | 当前用户ID |
| draft | 0 | 是否草稿：0=否, 1=是 |
| handleType | 1 | 处理类型：1=审批 |

## 2. "基本信息"标签加载流程

页面加载时依次调用以下接口：

### 2.1 获取模板配置
```
GET /template/template/{templateId}
```
- **Controller:** `TemplateController.java` → `getInfo()`
- **Service:** `ITemplateService.getTemplateById(id)`
- **表:** `t_template`
- **返回:** 模板配置（formType、formId、attachFlag、mainTextFlag 等）

### 2.2 获取表单数据（核心接口）
```
GET /biz/form/info?bizId={businessId}&templateId={templateId}
```
- **Controller:** `BizFormController.java` → `getBizForm()`
- **Service:** `IBizFormService.getBizForm(commonForm)`
- **表:** `t_workflow_form`（读取 form_data JSON）+ `t_template_dynamic_form`（读取表单结构 content）
- **返回:** `CommonForm` 对象，包含：
  - `formData` — 表单字段结构定义（JSON）
  - `valData` — 表单字段填写的值（Map）

### 2.3 获取待办/任务信息
```
GET /workflow/todo/{todoId}
GET /workflow/handle/info?taskId={taskId}
```
- **表:** `t_workflow_todo`

### 2.4 获取流程变量
```
GET /workflow/handle/processVariables/{taskId}
```
- **来源:** Flowable 引擎运行时变量

### 2.5 获取操作按钮
```
GET /biz/button/list?taskId={taskId}&procInsId={procInsId}&pageType={pageType}
```

## 3. 数据库表结构

### 3.1 t_template — 模板配置表

| 字段 | 类型 | 说明 |
|------|------|------|
| id | varchar(64) PK | 模板ID |
| name | varchar(200) | 模板名称 |
| type | varchar(50) | 模板类型 |
| def_key | varchar(400) | Flowable 流程定义 key |
| form_id | varchar(64) | 关联的动态表单ID → t_template_dynamic_form.id |
| form_key | varchar(64) | 业务表单 key |
| form_type | varchar(10) | 表单类型：1=动态表单, 2=业务表单, 3=自定义 |
| form_code | varchar(200) | 表单编码 |
| main_text_flag | char(1) | 是否有正文：0=无, 1=有 |
| attach_flag | char(1) | 是否有附件：0=无, 1=有 |
| message_notice_flag | char(1) | 是否需要消息通知 |
| enable_flag | char(1) | 启用状态 |
| del_flag | char(1) | 软删除标记 |
| create_id | varchar(64) | 创建者 |
| create_time | datetime | 创建时间 |
| update_id | varchar(64) | 更新者 |
| update_time | datetime | 更新时间 |

**Mapper XML:** `ruoyi-template/src/main/resources/mapper/template/TemplateMapper.xml`
**实体类:** `ruoyi-template/src/main/java/com/ruoyi/template/domain/Template.java`

### 3.2 t_template_dynamic_form — 动态表单定义表（核心）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | varchar(64) PK | 表单ID |
| name | varchar(200) | 表单名称 |
| content | longtext | **表单结构 JSON**（包含所有字段定义） |
| enable_flag | char(1) | 启用状态 |
| form_key | varchar(64) | 表单 key |
| del_flag | char(1) | 软删除标记 |
| create_id | varchar(64) | 创建者 |
| create_time | datetime | 创建时间 |
| update_id | varchar(64) | 更新者 |
| update_time | datetime | 更新时间 |

**Mapper XML:** `ruoyi-template/src/main/resources/mapper/template/TemplateDynamicFormMapper.xml`
**实体类:** `ruoyi-template/src/main/java/com/ruoyi/template/domain/TemplateDynamicForm.java`

> **重点：** `content` 字段存储的 JSON 定义了"基本信息"标签中所有表单字段的结构，包括字段名、类型、校验规则等。这是动态列表展示的关键数据源。

### 3.3 t_workflow_form — 表单数据存储表（核心）

| 字段 | 类型 | 说明 |
|------|------|------|
| id | varchar(64) PK | 数据ID（即 businessId） |
| title | varchar(400) | 表单标题 |
| form_data | text | **表单填写数据 JSON**（包含 formData + valData） |
| template_id | varchar(64) | 关联模板ID → t_template.id |
| create_id | varchar(64) | 创建者 |
| create_time | datetime | 创建时间 |
| update_id | varchar(64) | 更新者 |
| update_time | datetime | 更新时间 |

**Mapper XML:** `ruoyi-workflow/src/main/resources/mapper/workflow/FormMapper.xml`
**实体类:** `ruoyi-workflow/src/main/java/com/ruoyi/workflow/domain/Form.java`

> **重点：** `form_data` 字段存储用户填写的具体值，是 JSON 格式。需要解析此 JSON 来提取列值用于列表展示。

### 3.4 t_workflow_todo — 待办任务表

| 字段 | 类型 | 说明 |
|------|------|------|
| id | varchar(64) PK | 待办ID |
| title | varchar(400) | 标题 |
| cur_node | varchar(200) | 当前流程节点名称 |
| cur_handler | varchar(64) | 当前处理人ID |
| business_id | varchar(64) | 业务数据ID → t_workflow_form.id |
| task_id | varchar(64) | Flowable 任务ID |
| template_id | varchar(64) | 模板ID → t_template.id |
| proc_inst_id | varchar(64) | 流程实例ID |
| type | varchar(10) | 类型：1=待办, 2=只读 |
| handle_type | varchar(10) | 处理类型 |
| create_id | varchar(64) | 创建者 |
| create_time | datetime | 创建时间 |

### 3.5 t_workflow_done — 已办任务表

| 字段 | 类型 | 说明 |
|------|------|------|
| id | varchar(64) PK | 已办ID |
| title | varchar(400) | 标题 |
| business_id | varchar(64) | 业务数据ID |
| task_id | varchar(64) | 任务ID |
| template_id | varchar(64) | 模板ID |
| proc_inst_id | varchar(64) | 流程实例ID |
| create_id | varchar(64) | 创建者 |
| create_time | datetime | 创建时间 |

### 3.6 t_template_attachment — 附件配置表

| 字段 | 类型 | 说明 |
|------|------|------|
| id | varchar(64) PK | 附件配置ID |
| template_id | varchar(64) | 模板ID |
| name | varchar(200) | 附件名称 |
| limit_size | int | 大小限制 |
| limit_type | varchar(200) | 类型限制 |

### 3.7 t_workflow_attachment — 表单附件表

| 字段 | 类型 | 说明 |
|------|------|------|
| id | varchar(64) PK | ID |
| business_id | varchar(64) | 业务数据ID |
| file_id | varchar(64) | 文件ID |

## 4. 表关系图

```
t_template (模板配置)
  │
  ├──→ t_template_dynamic_form (表单字段结构定义)
  │       content JSON → 定义有哪些字段、类型、校验规则
  │
  ├──→ t_template_attachment (附件配置)
  │
  ├──→ t_template_main_text (正文配置)
  │
  └──→ t_workflow_form (表单数据)
          │   form_data JSON → 存储用户填写的值
          │
          ├──→ t_workflow_todo (待办任务)
          │
          ├──→ t_workflow_done (已办任务)
          │
          └──→ t_workflow_attachment (附件文件)
```

## 5. 动态表单字段映射（form_data JSON 结构）

### 5.1 表单结构定义（t_template_dynamic_form.content）

`content` 字段存储 VForm 设计器生成的 JSON，结构示例：

```json
{
  "widgetList": [
    {
      "type": "input",           // 字段类型：input/textarea/select/date/number/...
      "options": {
        "name": "field_name",    // 字段标识（作为列的 key）
        "label": "字段标签",      // 显示名称（作为列的表头）
        "defaultValue": "",      // 默认值
        "required": true,        // 是否必填
        "placeholder": "请输入"
      }
    },
    {
      "type": "select",
      "options": {
        "name": "status",
        "label": "状态",
        "optionItems": [
          {"label": "启用", "value": "1"},
          {"label": "禁用", "value": "0"}
        ]
      }
    }
  ],
  "formConfig": {
    "labelWidth": 100,
    "labelPosition": "left"
  }
}
```

### 5.2 表单数据（t_workflow_form.form_data）

`form_data` 字段存储的 JSON 包含两部分：

```json
{
  "formData": { ... },    // 表单结构（同 content 或子集）
  "valData": {            // 用户填写的值
    "field_name": "用户输入的值",
    "status": "1",
    "date_field": "2026-04-01"
  }
}
```

## 6. 动态列表查询方案设计

### 6.1 核心思路

根据 `templateId` 查询对应的动态表单定义（`t_template_dynamic_form.content`），解析 JSON 得到所有字段定义作为**列定义**；然后查询 `t_workflow_form` 中该模板下所有数据，解析每条记录的 `form_data` JSON 得到**列值**。

### 6.2 需要实现的接口

#### 接口1：获取动态列定义
```
GET /biz/form/columns?templateId={templateId}
```
**逻辑：**
1. 通过 `templateId` 查 `t_template.form_id`
2. 通过 `form_id` 查 `t_template_dynamic_form.content`
3. 解析 JSON，提取 `widgetList` 中每个字段的 `name`、`label`、`type`
4. 返回列定义数组

**返回示例：**
```json
{
  "code": 200,
  "data": [
    {"prop": "field_name", "label": "字段标签", "type": "input", "required": true},
    {"prop": "status", "label": "状态", "type": "select", "options": [...]},
    {"prop": "date_field", "label": "日期", "type": "date"}
  ]
}
```

#### 接口2：分页查询表单数据列表
```
GET /biz/form/list?templateId={templateId}&pageNum=1&pageSize=10
```
**逻辑：**
1. 分页查询 `t_workflow_form`，条件 `template_id = ?`
2. 对每条记录解析 `form_data` JSON，提取 `valData`
3. 将固定字段（id、title、create_time）与动态字段合并返回

**返回示例：**
```json
{
  "code": 200,
  "total": 50,
  "rows": [
    {
      "id": "5C99E5611E7A4219A991826FC177326D",
      "title": "11",
      "createTime": "2026-03-28 10:00:00",
      "field_name": "用户输入的值",
      "status": "1"
    }
  ]
}
```

#### 接口3：更新表单数据
```
POST /biz/form/update
Body: { "bizId": "xxx", "templateId": "xxx", "valData": { "field_name": "新值" } }
```

### 6.3 SQL 参考

```sql
-- 获取模板关联的动态表单定义
SELECT df.content
FROM t_template t
JOIN t_template_dynamic_form df ON t.form_id = df.id
WHERE t.id = #{templateId} AND t.del_flag = '0';

-- 分页查询某模板下所有表单数据
SELECT id, title, form_data, create_id, create_time
FROM t_workflow_form
WHERE template_id = #{templateId}
ORDER BY create_time DESC
LIMIT #{offset}, #{pageSize};

-- 查询总数
SELECT COUNT(1)
FROM t_workflow_form
WHERE template_id = #{templateId};
```

## 7. 关键文件索引

| 文件路径 | 说明 |
|----------|------|
| `ruoyi-vue-oa-ui/src/views/workflow/flow-form/index.vue` | 表单页面主组件 |
| `ruoyi-vue-oa-ui/src/api/workflow/form.js` | 前端表单 API |
| `ruoyi-vue-oa-ui/src/api/workflow/template.js` | 前端模板 API |
| `ruoyi-biz-sdk/src/main/java/com/ruoyi/biz/controller/BizFormController.java` | 表单数据 Controller |
| `ruoyi-biz-sdk/src/main/java/com/ruoyi/biz/service/IBizFormService.java` | 表单数据 Service 接口 |
| `ruoyi-biz-sdk/src/main/java/com/ruoyi/biz/domain/CommonForm.java` | 表单数据 DTO |
| `ruoyi-template/src/main/java/com/ruoyi/template/controller/TemplateController.java` | 模板 Controller |
| `ruoyi-template/src/main/java/com/ruoyi/template/controller/TemplateDynamicFormController.java` | 动态表单 Controller |
| `ruoyi-template/src/main/java/com/ruoyi/template/domain/TemplateDynamicForm.java` | 动态表单实体 |
| `ruoyi-workflow/src/main/java/com/ruoyi/workflow/domain/Form.java` | 表单数据实体 |
| `ruoyi-workflow/src/main/resources/mapper/workflow/FormMapper.xml` | 表单数据 Mapper |
| `ruoyi-template/src/main/resources/mapper/template/TemplateMapper.xml` | 模板 Mapper |
| `ruoyi-template/src/main/resources/mapper/template/TemplateDynamicFormMapper.xml` | 动态表单 Mapper |

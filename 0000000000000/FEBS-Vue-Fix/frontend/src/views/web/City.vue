<template>
  <a-card :bordered="false" class="card-area">
    <div :class="advanced ? 'search' : null">
      <a-form layout="horizontal">
        <div :class="advanced ? null: 'fold'">
          <a-row >
            <a-col :md="8" :sm="24" >
              <a-form-item
                label="城市名称"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}">
                <a-input v-model="queryParams.cityName"/>
              </a-form-item>
            </a-col>
            <a-col :md="8" :sm="24" >
              <a-form-item
                label="城市简介"
                :labelCol="{span: 5}"
                :wrapperCol="{span: 18, offset: 1}">
                <a-input v-model="queryParams.introduce"/>
              </a-form-item>
            </a-col>
          </a-row>
        </div>
        <span style="float: right; margin-top: 3px;">
          <a-button type="primary" @click="search">查询</a-button>
          <a-button style="margin-left: 8px" @click="reset">重置</a-button>
          <!--<a @click="toggleAdvanced" style="margin-left: 8px">
            {{advanced ? '收起' : '展开'}}
            <a-icon :type="advanced ? 'up' : 'down'" />
          </a>-->
        </span>
      </a-form>
    </div>
    <div>
      <div class="operator">
        <a-button v-hasPermission="['city:add']" type="primary" ghost @click="add">新增</a-button>
        <a-button v-hasPermission="['city:delete']" @click="batchDelete">删除</a-button>
        <a-dropdown v-hasPermission="['city:export']">
          <a-menu slot="overlay">
            <a-menu-item key="export-data" @click="exportExcel">导出Excel</a-menu-item>
            <a-menu-item type="primary" @click="downloadTemplate" >模板下载</a-menu-item>
          </a-menu>
          <a-button>
            更多操作 <a-icon type="down" />
          </a-button>
        </a-dropdown>
          <a-upload
            :fileList="fileList"
            :remove="handleRemove"
            :disabled="fileList.length === 1"
            :beforeUpload="beforeUpload">
            <a-button>
              <a-icon type="upload" /> 选择.xlsx文件
            </a-button>
            <a-button
              class="button-area"
              @click="handleUpload"
              :disabled="fileList.length === 0"
              :loading="uploading">
              {{uploading ? '导入中' : '导入Excel' }}
            </a-button>
          </a-upload>
      </div>
      <!-- 表格区域 -->
      <a-table ref="TableInfo"
               :columns="columns"
               :rowKey="record => record.cityId"
               :dataSource="dataSource"
               :pagination="pagination"
               :loading="loading"
               :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
               @change="handleTableChange"
               :scroll="{ x: 900 }">
        <template slot="remark" slot-scope="text, record">
          <a-popover placement="topLeft">
            <template slot="content">
              <div style="max-width: 200px">{{text}}</div>
            </template>
            <p style="width: 200px;margin-bottom: 0">{{text}}</p>
          </a-popover>
        </template>
        <template slot="operation" slot-scope="text, record">
          <a-icon v-hasPermission="['city:update']" type="setting" theme="twoTone" twoToneColor="#4a9ff5" @click="edit(record)" title="修改城市"></a-icon>
          <a-badge v-hasNoPermission="['city:update']" status="warning" text="无权限"></a-badge>
        </template>
      </a-table>
      <import-result
        @close="handleClose"
        :importData="importData"
        :errors="errors"
        :times="times"
        :importResultVisible="importResultVisible">
      </import-result>
    </div>
    <!-- 新增城市 -->
    <city-add
      @close="handleCityAddClose"
      @success="handleCityAddSuccess"
      :cityAddVisiable="cityAddVisiable">
    </city-add>
    <!-- 修改城市 -->
    <city-edit
      ref="cityEdit"
      @close="handleCityEditClose"
      @success="handleCityEditSuccess"
      :cityEditVisiable="cityEditVisiable">
    </city-edit>
  </a-card>
</template>

<script>
import CityAdd from './CityAdd'
import CityEdit from './CityEdit'
import ImportResult from './ImportResult'

export default {
  name: 'City',
  components: {CityAdd, CityEdit, ImportResult},
  data () {
    return {
      fileList: [],
      importData: [],
      times: '',
      errors: [],
      uploading: false,
      importResultVisible: false,
      advanced: false,
      dataSource: [],
      selectedRowKeys: [],
      paginationInfo: null,
      pagination: {
        pageSizeOptions: ['10', '20', '30', '40', '100'],
        defaultCurrent: 1,
        defaultPageSize: 10,
        showQuickJumper: true,
        showSizeChanger: true,
        showTotal: (total, range) => `显示 ${range[0]} ~ ${range[1]} 条记录，共 ${total} 条记录`
      },
      queryParams: {},
      cityAddVisiable: false,
      cityEditVisiable: false,
      loading: false
    }
  },
  computed: {
    columns () {
      return [{
        title: '城市名称',
        dataIndex: 'cityName'
      }, {
        title: '简介',
        dataIndex: 'introduce'
      }, {
        title: '经度',
        dataIndex: 'longitude'
      }, {
        title: '纬度',
        dataIndex: 'latitude'
      }, {
        title: '创建时间',
        dataIndex: 'createTime'
      }, {
        title: '操作',
        dataIndex: 'operation',
        scopedSlots: { customRender: 'operation' },
        fixed: 'right',
        width: 100
      }]
    }
  },
  mounted () {
    this.fetch()
  },
  methods: {
    handleClose () {
      this.importResultVisible = false
    },
    downloadTemplate () {
      this.$download('city/template', {}, 'City_导入模板.xlsx')
    },
    handleRemove (file) {
      if (this.uploading) {
        this.$message.warning('文件导入中，请勿删除')
        return
      }
      const index = this.fileList.indexOf(file)
      const newFileList = this.fileList.slice()
      newFileList.splice(index, 1)
      this.fileList = newFileList
    },
    beforeUpload (file) {
      this.fileList = [...this.fileList, file]
      return false
    },
    handleUpload () {
      const { fileList } = this
      const formData = new FormData()
      formData.append('file', fileList[0])
      this.uploading = true
      this.$upload('city/import', formData).then((r) => {
        let data = r.data.data
        if (data.data.length) {
          this.fetch()
        }
        this.importData = data.data
        this.errors = data.error
        this.times = data.time / 1000
        this.uploading = false
        this.fileList = []
        this.importResultVisible = true
      }).catch((r) => {
        console.error(r)
        this.uploading = false
        this.fileList = []
      })
    },
    onSelectChange (selectedRowKeys) {
      this.selectedRowKeys = selectedRowKeys
    },
    toggleAdvanced () {
      this.advanced = !this.advanced
      if (!this.advanced) {
        this.queryParams.fieldName = ''
      }
    },
    handleCityAddSuccess () {
      this.cityAddVisiable = false
      this.$message.success('新增城市成功')
      this.search()
    },
    handleCityAddClose () {
      this.cityAddVisiable = false
    },
    add () {
      this.cityAddVisiable = true
    },
    handleCityEditSuccess () {
      this.cityEditVisiable = false
      this.$message.success('修改城市成功')
      this.search()
    },
    handleCityEditClose () {
      this.cityEditVisiable = false
    },
    edit (record) {
      this.$refs.cityEdit.setFormValues(record)
      this.cityEditVisiable = true
    },
    batchDelete () {
      if (!this.selectedRowKeys.length) {
        this.$message.warning('请选择需要删除的记录')
        return
      }
      let that = this
      this.$confirm({
        title: '确定删除所选中的记录?',
        content: '当您点击确定按钮后，这些记录将会被彻底删除',
        centered: true,
        onOk () {
          let cityIds = that.selectedRowKeys.join(',')
          that.$delete('city/' + cityIds).then(() => {
            that.$message.success('删除成功')
            that.selectedRowKeys = []
            that.search()
          })
        },
        onCancel () {
          that.selectedRowKeys = []
        }
      })
    },
    exportExcel () {
      let cityIds = ''
      if (this.selectedRowKeys.length) {
        cityIds = this.selectedRowKeys.join(',')
      }
      this.$export('city/excel?cityIds=' + cityIds, {
        ...this.queryParams
      })
    },
    search () {
      this.selectData({
        ...this.queryParams
      })
    },
    reset () {
      // 取消选中
      this.selectedRowKeys = []
      // 重置分页
      this.$refs.TableInfo.pagination.current = this.pagination.defaultCurrent
      if (this.paginationInfo) {
        this.paginationInfo.current = this.pagination.defaultCurrent
        this.paginationInfo.pageSize = this.pagination.defaultPageSize
      }
      this.paginationInfo = null
      // 重置查询参数
      this.queryParams = {}
      this.fetch()
    },
    handleTableChange (pagination, filters, sorter) {
      this.paginationInfo = pagination
      this.fetch({
        ...this.queryParams
      })
    },
    fetch (params = {}) {
      this.loading = true
      if (this.paginationInfo) {
        // 如果分页信息不为空，则设置表格当前第几页，每页条数，并设置查询分页参数
        this.$refs.TableInfo.pagination.current = this.paginationInfo.current
        this.$refs.TableInfo.pagination.pageSize = this.paginationInfo.pageSize
        params.pageSize = this.paginationInfo.pageSize
        params.pageNum = this.paginationInfo.current
      } else {
        // 如果分页信息为空，则设置为默认值
        params.pageSize = this.pagination.defaultPageSize
        params.pageNum = this.pagination.defaultCurrent
      }
      this.$get('city', {
        ...params
      }).then((r) => {
        let data = r.data
        const pagination = { ...this.pagination }
        pagination.total = data.total
        this.loading = false
        this.dataSource = data.rows
        this.pagination = pagination
      })
    },
    selectData (params = {}) {
      this.loading = true
      // 如果分页信息为空，则设置为默认值
      this.$refs.TableInfo.pagination.current = this.pagination.defaultCurrent
      this.$refs.TableInfo.pagination.pageSize = this.pagination.defaultPageSize
      params.pageSize = this.pagination.defaultPageSize
      params.pageNum = this.pagination.defaultCurrent
      this.$get('city', {
        ...params
      }).then((r) => {
        let data = r.data
        const pagination = { ...this.pagination }
        pagination.total = data.total
        this.loading = false
        this.dataSource = data.rows
        this.pagination = pagination
      })
    }
  }
}
</script>

<style lang="less" scoped>
@import "../../../static/less/Common";
.option-area {
  display: inline-block;
  width: 100%;
  padding: 0 .9rem;
  margin: .5rem 0;
  .upload-area {
    display: inline;
    float: left;
    width: 50%
  }
  .button-area {
    margin-left: 1rem;
    display: inline;
    float: right;
    width: 30%;
  }
}
</style>

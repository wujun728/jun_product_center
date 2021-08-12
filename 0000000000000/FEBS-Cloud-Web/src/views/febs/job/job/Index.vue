<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="queryParams.beanName" :placeholder="$t('table.job.beanName')" class="filter-item search-item" />
      <el-input v-model="queryParams.methodName" :placeholder="$t('table.job.methodName')" class="filter-item search-item" />
      <el-button class="filter-item" type="primary" @click="search">
        {{ $t('table.search') }}
      </el-button>
      <el-button class="filter-item" type="success" @click="reset">
        {{ $t('table.reset') }}
      </el-button>
      <el-dropdown v-has-any-permission="['job:add','job:delete','job:resume','job:pause','job:run','job:export']" trigger="click" class="filter-item">
        <el-button>
          {{ $t('table.more') }}<i class="el-icon-arrow-down el-icon--right" />
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item v-has-permission="['job:add']" @click.native="add">{{ $t('table.job.add') }}</el-dropdown-item>
          <el-dropdown-item v-has-permission="['job:delete']" @click.native="batchDelete">{{ $t('table.job.delete') }}</el-dropdown-item>
          <el-dropdown-item v-has-permission="['job:resume']" @click.native="resume"> {{ $t('table.job.resume') }}</el-dropdown-item>
          <el-dropdown-item v-has-permission="['job:pause']" @click.native="pause">{{ $t('table.job.pause') }}</el-dropdown-item>
          <el-dropdown-item v-has-permission="['job:run']" @click.native="run">{{ $t('table.job.run') }}</el-dropdown-item>
          <el-dropdown-item v-has-permission="['job:export']" @click.native="exportExcel">{{ $t('table.export') }}</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
    <el-table
      ref="table"
      :key="tableKey"
      v-loading="loading"
      :data="list"
      border
      fit
      style="width: 100%;"
      @selection-change="onSelectChange"
      @sort-change="sortChange"
    >
      <el-table-column type="selection" align="center" width="40px" />
      <el-table-column :label="$t('table.job.beanName')" prop="beanName" :show-overflow-tooltip="true" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.beanName }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.job.methodName')" prop="methodName" :show-overflow-tooltip="true" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.methodName }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.job.params')" prop="params" :show-overflow-tooltip="true" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.params }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.job.cronExpression')" prop="cronExpression" :show-overflow-tooltip="true" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.cronExpression }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.job.remark')" prop="remark" :show-overflow-tooltip="true" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.remark }}</span>
        </template>
      </el-table-column>
      <el-table-column
        :label="$t('table.job.status')"
        prop="status"
        :show-overflow-tooltip="true"
        :filters="[{ text: $t('table.job.pause'), value: '1' }, { text: $t('table.job.normal'), value: '0' }]"
        :filter-method="filterStatus"
        align="center"
      >
        <template slot-scope="{row}">
          <el-tag :type="row.status | statusFilter">
            {{ row.status === '1' ? $t('table.job.pause'): $t('table.job.normal') }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.job.createTime')" prop="createTime" :show-overflow-tooltip="true" align="center" sortable="custom">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.operation')" align="center" min-width="100px" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <i v-hasPermission="['job:update']" class="el-icon-setting table-operation" style="color: #2db7f5;" @click="edit(row)" />
          <i v-hasPermission="['job:delete']" class="el-icon-delete table-operation" style="color: #f50;" @click="singleDelete(row)" />
          <el-link v-has-no-permission="['job:update','job:delete']" class="no-perm">
            {{ $t('tips.noPermission') }}
          </el-link>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="pagination.num" :limit.sync="pagination.size" @pagination="search" />
    <job-edit
      ref="edit"
      :dialog-visible="dialog.isVisible"
      :title="dialog.title"
      @success="editSuccess"
      @close="editClose"
    />
  </div>
</template>
<script>
import Pagination from '@/components/Pagination'
import JobEdit from './Edit'

export default {
  name: 'JobList',
  components: { Pagination, JobEdit },
  filters: {
    statusFilter(status) {
      const map = {
        0: 'success',
        1: 'danger'
      }
      return map[status]
    }
  },
  data() {
    return {
      tableKey: 0,
      loading: false,
      dialog: {
        isVisible: false,
        title: ''
      },
      list: null,
      total: 0,
      queryParams: {},
      sort: {},
      selection: [],
      pagination: {
        size: 10,
        num: 1
      }
    }
  },
  mounted() {
    this.fetch()
  },
  methods: {
    filterStatus(value, row) {
      return row.status === value
    },
    editClose() {
      this.dialog.isVisible = false
    },
    editSuccess() {
      this.search()
    },
    add() {
      this.dialog.title = this.$t('common.add')
      this.dialog.isVisible = true
    },
    edit(row) {
      this.$refs.edit.setJob(row)
      this.dialog.title = this.$t('common.edit')
      this.dialog.isVisible = true
    },
    batchDelete() {
      if (!this.selection.length) {
        this.$message({
          message: this.$t('tips.noDataSelected'),
          type: 'warning'
        })
        return
      }
      this.$confirm(this.$t('tips.confirmDelete'), this.$t('common.tips'), {
        confirmButtonText: this.$t('common.confirm'),
        cancelButtonText: this.$t('common.cancel'),
        type: 'warning'
      }).then(() => {
        const jobIds = []
        this.selection.forEach((j) => {
          jobIds.push(j.jobId)
        })
        this.delete(jobIds)
      }).catch(() => {
        this.clearSelections()
      })
    },
    resume() {
      this.execute('resume')
    },
    pause() {
      this.execute('pause')
    },
    run() {
      this.execute('run')
    },
    execute(method) {
      if (!this.selection.length) {
        this.$message({
          message: this.$t('tips.noDataSelected'),
          type: 'warning'
        })
        return
      }
      const jobIds = []
      this.selection.forEach((j) => {
        jobIds.push(j.jobId)
      })
      this.loading = true
      this.$get(`job/${method}/${jobIds.join(',')}`).then(() => {
        this.$message({
          message: this.$t('tips.executeSuccess'),
          type: 'success'
        })
        this.search()
      }).catch(r => {
        this.$message({
          message: this.$t('tips.executeFail'),
          type: 'error'
        })
        this.loading = false
      })
    },
    exportExcel() {
      this.$download('job/excel', {
        pageSize: this.pagination.size,
        pageNum: this.pagination.num,
        ...this.queryParams
      }, `job_${new Date().getTime()}.xlsx`)
    },
    clearSelections() {
      this.$refs.table.clearSelection()
    },
    singleDelete(row) {
      this.$refs.table.toggleRowSelection(row, true)
      this.batchDelete()
    },
    delete(jobIds) {
      this.loading = true
      this.$delete(`job/${jobIds}`).then(() => {
        this.$message({
          message: this.$t('tips.deleteSuccess'),
          type: 'success'
        })
        this.search()
      })
    },
    search() {
      this.fetch({
        ...this.queryParams,
        ...this.sort
      })
    },
    reset() {
      this.queryParams = {}
      this.sort = {}
      this.$refs.table.clearSort()
      this.$refs.table.clearFilter()
      this.search()
    },
    onSelectChange(selection) {
      this.selection = selection
    },
    sortChange(val) {
      this.sort.field = val.prop
      this.sort.order = val.order
      this.search()
    },
    fetch(params = {}) {
      params.pageSize = this.pagination.size
      params.pageNum = this.pagination.num
      if (this.queryParams.timeRange) {
        params.createTimeFrom = this.queryParams.timeRange[0]
        params.createTimeTo = this.queryParams.timeRange[1]
      }
      this.loading = true
      this.$get('job', {
        ...params
      }).then((r) => {
        const data = r.data.data
        this.total = data.total
        this.list = data.rows
        this.loading = false
      })
    }
  }
}
</script>

<template>
  <div class="app-container">
    <el-alert
      style="margin: -1rem 0 1.2rem 0;padding: 1rem;"
      :title="$t('table.routeUser.tips')"
      type="info"
      :closable="false"
    />
    <div class="filter-container">
      <el-input v-model="queryParams.username" :placeholder="$t('table.routeUser.username')" class="filter-item search-item" />
      <el-button class="filter-item" type="primary" @click="search">
        {{ $t('table.search') }}
      </el-button>
      <el-button class="filter-item" type="success" @click="reset">
        {{ $t('table.reset') }}
      </el-button>
      <el-dropdown trigger="click" class="filter-item">
        <el-button>
          {{ $t('table.more') }}<i class="el-icon-arrow-down el-icon--right" />
        </el-button>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item @click.native="add">{{ $t('table.add') }}</el-dropdown-item>
          <el-dropdown-item @click.native="batchDelete">{{ $t('table.delete') }}</el-dropdown-item>
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
      <el-table-column :label="$t('table.routeUser.username')" prop="username" :show-overflow-tooltip="true" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.username }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.routeUser.perm')" prop="roles" :show-overflow-tooltip="true" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.roles }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.routeUser.createTime')" prop="createTime" :show-overflow-tooltip="true" align="center" sortable="custom">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.operation')" align="center" min-width="60px" class-name="small-padding fixed-width">
        <template slot-scope="{row}">
          <i class="el-icon-setting table-operation" style="color: #2db7f5;" @click="edit(row)" />
          <i class="el-icon-delete table-operation" style="color: #f50;" @click="singleDelete(row)" />
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="pagination.num" :limit.sync="pagination.size" @pagination="search" />
    <RouteUserEdit
      ref="edit"
      :dialog-visible="dialog.isVisible"
      :title="dialog.title"
      @success="editSuccess"
      @close="editClose"
    />
  </div>
</template>
<script>
import r from '@/utils/route-request'
import axios from 'axios'
import Pagination from '@/components/Pagination'
import RouteUserEdit from './Edit'

export default {
  name: 'RouteUser',
  components: { Pagination, RouteUserEdit },
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
    clearSelections() {
      this.$refs.table.clearSelection()
    },
    edit(row) {
      const user = { ...row }
      let roles = []
      if (row.roles && typeof row.roles === 'string') {
        roles = row.roles.split(',')
        user.roles = roles
      }
      this.$refs.edit.setUser(user)
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
        const userIds = []
        this.selection.forEach((u) => {
          userIds.push(u.id)
        })
        this.delete(userIds)
      }).catch(() => {
        this.clearSelections()
      })
    },
    singleDelete(row) {
      this.$refs.table.toggleRowSelection(row, true)
      this.batchDelete()
    },
    delete(userIds) {
      this.loading = true
      r.delete('route/auth/user', { ids: userIds }).then(() => {
        this.$message({
          message: this.$t('tips.deleteSuccess'),
          type: 'success'
        })
        this.search()
      }).catch(r => {
        this.loading = false
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
      params.pageNum = this.pagination.num - 1
      this.loading = true
      axios.all([
        r.get('route/auth/user/data', { ...params }),
        r.get('route/auth/user/count', { ...params })
      ]).then(v => {
        this.total = v[1].data
        this.list = v[0].data
        this.loading = false
      }).catch(r => {
        this.loading = false
      })
    }
  }
}
</script>

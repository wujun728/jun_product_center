<template>
    <!--<table>
      <tr v-for="project in projects" :key="project.id">
        <td>
          <div class="project-avatar-wrapper">
            <a-avatar class="project-avatar">{{project.avatar}}</a-avatar>
          </div>
          <div class="project-detail">
            <div class="project-name">
              {{project.name}}
            </div>
            <div class="project-desc">
              <p>{{project.description}}</p>
            </div>
          </div>
        </td>
      </tr>
    </table>-->
    <a-table :bordered="true" :dataSource="projects" :rowKey="record => record.id" :showHeader="false" :pagination="{defaultPageSize:4,hideOnSinglePage:true}">
      <a-table-column dataIndex="id" key="id" >
        <template slot-scope="text, record">
          <div class="project-avatar-wrapper">
            <a-avatar class="project-avatar">{{record.avatar}}</a-avatar>
          </div>
          <div class="project-detail">
            <div class="project-name">
              {{record.name}}
            </div>
            <div class="project-desc">
              <p>{{record.description}}</p>
            </div>
          </div>
        </template>
      </a-table-column>
    </a-table>
</template>

<script>
export default {
  name: 'RunningTask',
  props: ['projects']
}
</script>

<style scoped>

</style>

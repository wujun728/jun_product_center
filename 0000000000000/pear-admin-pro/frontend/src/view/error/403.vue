<template>
  <div id="403">
    <page-layout>
      <a-card>
        <a-result status="403" title="403" sub-title="您没有权限访问！">
          <template #extra>
            <router-link to="/">
              <a-button type="primary"> 返回首页 </a-button>
            </router-link>
          </template>
        </a-result>
      </a-card>
    </page-layout>
    <page-footer></page-footer>
  </div>
</template>
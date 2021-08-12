<template>
  <div id="500">
    <page-layout>
      <a-card>
        <a-result status="500" title="500" sub-title="服务器开小差了">
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
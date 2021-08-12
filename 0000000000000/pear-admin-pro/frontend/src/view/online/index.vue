<template>
  <div>
    <page-header
      title="在 线 用 户"
      describe="用 户 Online 列 表，用 于 系 统 在 线 用 户 监 控."
    ></page-header>
    <page-layout>
      <a-card style="text-align: center">
        <a-row>
          <a-col :span="8">
            <a-statistic
              title="全部用户"
              :value="100"
            >
            </a-statistic>
          </a-col>
          <a-col :span="8">
            <a-statistic title="上线比例" :value="15" class="demo-class">
              <template #suffix>
                <span> / 100</span>
              </template>
            </a-statistic>
          </a-col>
          <a-col :span="8">
            <a-statistic title="在线用户" :value="15" class="demo-class"></a-statistic>
          </a-col>
        </a-row>
      </a-card>
    </page-layout>
    <page-layout>
      <a-card>
        <a-list :loading="loading" item-layout="horizontal" :data-source="data">
          <template v-slot:renderItem="{ item }">
            <a-list-item>
              <template v-slot:actions>
                <a>强制下线</a>
              </template>
              <a-list-item-meta
                description="Ant Design, a design language for background applications, is refined by Ant UED Team"
              >
                <template v-slot:title>
                  <a>{{ item.secureUser.username }}</a>
                </template>
                <template v-slot:avatar>
                  <a-avatar
                    src="https://portrait.gitee.com/uploads/avatars/user/1611/4835367_Jmysy_1578975358.png"
                  />
                </template>
              </a-list-item-meta>
              <div>{{ item.createTime }}</div>
            </a-list-item>
          </template>
        </a-list>
      </a-card>
    </page-layout>
  </div>
</template>
<script>

import { list } from "@/api/module/online";
import { ref } from 'vue';

export default {
  mounted() {
    this.getData((res) => {
      this.loading = false;
      this.data = res.results;
    });
  },
  methods: {
    getData(callback) {
      var response = list();
      callback(response);
    }
  },
  setup(){

    var data = ref();
    var loading = ref(true);

    const loadData = async function(){
      var response = await list();
      data.value = response.data;
      loading.value = false;
    }

    loadData();

    return {
      loading,
      data
    };
  }
};
</script>
<style>
.demo-loadmore-list {
  min-height: 350px;
}
</style>
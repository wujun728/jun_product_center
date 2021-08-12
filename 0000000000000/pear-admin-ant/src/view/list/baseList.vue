<template>
  <div>
    <page-header
      title="基础列表"
      describe="标准的列表，包含增删改查等基础操作."
    ></page-header>

    <page-layout>
      <a-card style="text-align: center">
        <a-row>
          <a-col :span="8">
            <a-statistic
              title="Feedback"
              :value="1128"
              style="margin-right: 50px"
            >
            </a-statistic>
          </a-col>
          <a-col :span="8">
            <a-statistic title="Unmerged" :value="93" class="demo-class">
              <template #suffix>
                <span> / 100</span>
              </template>
            </a-statistic>
          </a-col>
          <a-col :span="8">
            <a-statistic title="Teacher" :value="5433" class="demo-class">
            </a-statistic>
          </a-col>
        </a-row>
      </a-card>
    </page-layout>
    <page-layout>
      <a-card>
        <a-list :loading="loading" item-layout="horizontal" :data-source="data">
          <template v-slot:loadMore>
            <div
              v-if="showLoadingMore"
              :style="{
                textAlign: 'center',
                marginTop: '12px',
                height: '32px',
                lineHeight: '32px',
              }"
            >
              <a-spin v-if="loadingMore" />
              <a-button v-else @click="onLoadMore"> 加载更多 </a-button>
            </div>
          </template>
          <template v-slot:renderItem="{ item }">
            <a-list-item>
              <template v-slot:actions>
                <a>edit</a>
                <a>more</a>
              </template>
              <a-list-item-meta
                description="Ant Design, a design language for background applications, is refined by Ant UED Team"
              >
                <template v-slot:title>
                  <a href="https://www.antdv.com/">{{ item.name.last }}</a>
                </template>
                <template v-slot:avatar>
                  <a-avatar
                    src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
                  />
                </template>
              </a-list-item-meta>
              <div>content</div>
            </a-list-item>
          </template>
        </a-list>
      </a-card>
    </page-layout>
    <page-footer></page-footer>
  </div>
</template>
<script>
import reqwest from "reqwest";

const fakeDataUrl =
  "https://randomuser.me/api/?results=5&inc=name,gender,email,nat&noinfo";

export default {
  data() {
    return {
      loading: true,
      loadingMore: false,
      showLoadingMore: true,
      data: [],
    };
  },
  mounted() {
    this.getData((res) => {
      this.loading = false;
      this.data = res.results;
    });
  },
  methods: {
    getData(callback) {
      reqwest({
        url: fakeDataUrl,
        type: "json",
        method: "get",
        contentType: "application/json",
        success: (res) => {
          callback(res);
        },
      });
    },
    onLoadMore() {
      this.loadingMore = true;
      this.getData((res) => {
        this.data = this.data.concat(res.results);
        this.loadingMore = false;
        this.$nextTick(() => {
          window.dispatchEvent(new Event("resize"));
        });
      });
    },
  },
};
</script>
<style>
.demo-loadmore-list {
  min-height: 350px;
}
</style>
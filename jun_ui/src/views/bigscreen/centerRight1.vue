<template>
  <div id="centerRight1">
    <div class="bg-color-black">
      <div class="d-flex pt-2 pl-2">
        <span>
          <icon name="chart-line" class="text-icon"></icon>
        </span>
        <div class="d-flex">
          <span class="fs-xl text mx-2">任务完成排行榜</span>
        </div>
      </div>
      <div class="d-flex jc-center body-box">
        <dv-scroll-board class="dv-scr-board" :config="config" />
      </div>
    </div>
  </div>
</template>

<script>
import { listSimplePosts } from "@/api/system/post";

export default {
  data() {
    return {
      config: {
        header: ['名称', '编码', '状态'],
        rowNum: 7, //表格行数
        headerHeight: 35,
        headerBGC: '#0f1325', //表头
        oddRowBGC: '#0f1325', //奇数行
        evenRowBGC: '#171c33', //偶数行
        index: true,
        columnWidth: [50],
        align: ['center']
      }
    }
  },
  created() {
    listSimplePosts().then(response => {
        let data=response.data;
        let arr = data.map((item) => {
          let result=[item.postName,item.postCode,item.status=='0'?'正常':'停用']
          return result;
        })
        //多复制几份，查看大屏滚动效果
        let res=[];
        for(let i=0;i<5;i++){
          res.push(...arr);
        }
        this.config= {
            ...this.config,
            data: res
        };
    });
  },
}
</script>

<style lang="scss" scoped>
$box-height: 410px;
$box-width: 300px;
#centerRight1 {
  padding: 16px;
  padding-top: 20px;
  height: $box-height;
  width: $box-width;
  border-radius: 5px;
  .bg-color-black {
    height: $box-height - 30px;
    border-radius: 10px;
  }
  .text {
    color: #c3cbde;
  }
  .body-box {
    border-radius: 10px;
    overflow: hidden;
    .dv-scr-board {
      width: 270px;
      height: 340px;
    }
  }
}
</style>
<style lang="scss" scoped>
@import '@/assets/bigscreen/scss/style.scss';
</style>

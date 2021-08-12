<template>
  <div>
    <a-card style="border-radius:0px!important;">
      <a-avatar
        :size="64"
        src="https://portrait.gitee.com/uploads/avatars/user/2813/8441097_shaynas_1610801433.png!avatar200"
      >
      </a-avatar>
      <span style="margin-left: 30px; margin-top:0px; font-size: 18px"
        >&nbsp; 江湖无名，安心练剑 </span>
      <div style="float: right; width: 340px">
        <a-row
          :gutter="16"
          style="font-weight:500;margin-top: 18px; font-size: 18px"
        >
          <a-col :span="8" style="text-align: center;"> 项目 / 80 </a-col>
          <a-col :span="8" style="text-align: center;"> 排名 / 15 </a-col>
          <a-col :span="8" style="text-align: center;"> 访问 / 35 </a-col>
        </a-row>
      </div>
    </a-card>
    <page-layout>
      <a-row :gutter="[10, 10]">
        <a-col :xs="24" :lg="18" :xxl="18">
          <a-card title="项目">
            <a-card-grid
              style="
                width: 33.33%;
                height: 140px;
                line-height: 28px;
              "
              :hoverable="false"
            >
              <a-avatar
                src="https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png"
              ></a-avatar>
              <span style="margin-left:15px;font-weight:600;">Alipay</span>
              <div style="margin-top:10px">
                那是一种内在的东西， 他们到达不了，也无法触及的
              </div>
            </a-card-grid>
            <a-card-grid
              style="
                width: 33.33%;
                height: 140px;
                line-height: 28px;
              "
              :hoverable="false"
            >
              <a-avatar
                src="https://gw.alipayobjects.com/zos/rmsportal/zOsKZmFRdUtvpqCImOVY.png"
              ></a-avatar>
              <span style="margin-left:15px;font-weight:600;">Angular</span>
              <div style="margin-top:10px">
                希望是一个好东西，也许是最好的，好东西是不会消亡的
              </div>
            </a-card-grid>
            <a-card-grid
              style="
                width: 33.33%;
                height: 140px;
                line-height: 28px;
              "
              :hoverable="false"
            >
              <a-avatar
                src="https://gw.alipayobjects.com/zos/rmsportal/siCrBXXhmvTQGWPNLBow.png"
              ></a-avatar>
              <span style="margin-left:15px;font-weight:600;">Bootstrap</span>
              <div style="margin-top:10px">
                城镇中有那么多的酒馆，她却偏偏走进了我的酒馆
              </div>
            </a-card-grid>
            <a-card-grid
              style="
                width: 33.33%;
                height: 140px;
                line-height: 28px;
              "
              :hoverable="false"
            >
              <a-avatar
                src="https://gw.alipayobjects.com/zos/rmsportal/dURIMkkrRFpPgTuzkwnB.png"
              ></a-avatar>
              <span style="margin-left:15px;font-weight:600;">Ant Design</span>
              <div style="margin-top:10px">
                那时候我只会想自己想要什么，从不想自己拥有什么
              </div>
            </a-card-grid>
            <a-card-grid
              style="
                width: 33.33%;
                height: 140px;
                line-height: 28px;
              "
              :hoverable="false"
            >
              <a-avatar
                src="https://portrait.gitee.com/uploads/avatars/user/2813/8441097_shaynas_1610801433.png!avatar200"
              ></a-avatar>
              <span style="margin-left:15px;font-weight:600;">Shayna</span>
              <div style="margin-top:10px">
                接近，是我对一切的态度，是我对一切态度的距离
              </div>
            </a-card-grid>
            <a-card-grid
              style="
                width: 33.33%;
                height: 140px;
                line-height: 28px;
              "
              :hoverable="false"
            >
              <a-avatar
                src="https://gw.alipayobjects.com/zos/rmsportal/ComBAopevLwENQdKWiIn.png"
              ></a-avatar>
              <span style="margin-left:15px;font-weight:600;">Vue</span>
              <div style="margin-top:10px">
                生命就像一盒巧克力，结果往往出人意料
              </div>
            </a-card-grid>
          </a-card>
          <a-card title="动态" style="margin-top: 10px">
            <a-list item-layout="horizontal" :data-source="data">
              <template #renderItem="{ item }">
                <a-list-item>
                  <a-list-item-meta :description="item.info">
                    <template #title>
                      <a href="https://www.antdv.com/">{{ item.title }}</a>
                    </template>
                    <template #avatar>
                      <a-avatar :src="item.avatar" />
                    </template>
                  </a-list-item-meta>
                </a-list-item>
              </template>
            </a-list>
          </a-card>
        </a-col>
        <a-col :xs="24" :lg="6" :xxl="6">
          <a-card title="任务">
            <a-timeline>
              <a-timeline-item color="green">
                <p>阶段一：Technical testing 1</p>
                <p>阶段二：Technical testing 2</p>
                <p>阶段三：Technical testing 3</p>
              </a-timeline-item>
              <a-timeline-item>
                <p>阶段一：Technical testing 1</p>
                <p>阶段二：Technical testing 2</p>
                <p>阶段三：Technical testing 3</p>
                <p>阶段四：Technical testing 4</p>
              </a-timeline-item>
              <a-timeline-item color="red">
                <p>阶段一：Technical testing 1</p>
                <p>阶段二：Technical testing 2</p>
                <p>阶段三：Technical testing 3</p>
              </a-timeline-item>
              <a-timeline-item>
                <p>阶段一：Technical testing 1</p>
                <p>阶段二：Technical testing 2</p>
                <p>阶段三：Technical testing 3</p>
                <p>阶段四：Technical testing 4</p>
              </a-timeline-item>
              <a-timeline-item color="gray">
                <p>阶段一：Technical testing 1</p>
                <p>阶段二：Technical testing 2</p>
                <p>阶段三：Technical testing 3</p>
                <p>阶段四：Technical testing 4</p>
                <p>阶段五：Technical testing 5</p>
              </a-timeline-item>
            </a-timeline>
          </a-card>
        </a-col>
      </a-row>
    </page-layout>
    <page-footer></page-footer>
  </div>
</template>
<script>
export default {
  setup() {
    const data = [
      {
        avatar:
          "https://portrait.gitee.com/uploads/avatars/user/2813/8441097_shaynas_1610801433.png!avatar200",
        title: "就眠仪式",
        info: "城镇中有那么多的酒馆，她却偏偏走进了我的酒馆。"
      },
      {
        avatar:
          "https://portrait.gitee.com/uploads/avatars/user/2813/8441097_shaynas_1610801433.png!avatar200",
        title: "夏娜",
        info:
          "我跟自己和解，终究是做不会冷漠绝情的人类。本质就像感性动物的我，所以注定是要心碎不止一万次的。"
      },
      {
        avatar:
          "https://portrait.gitee.com/uploads/avatars/user/2813/8441097_shaynas_1610801433.png!avatar200",
        title: "风筝",
        info: "生命就像一盒巧克力，结果往往出人意料。"
      },
      {
        avatar:
          "https://portrait.gitee.com/uploads/avatars/user/2813/8441097_shaynas_1610801433.png!avatar200",
        title: "落小梅",
        info:
          "只向你撒了一回谎。我说：“人惯是薄凉的，昨日才说衷心爱你，怕是今日，明日便是不肯再爱了”。你看，我总是这般，唯恐爱使我的心成了破洞窟窿，便要紧紧裹住自己的尾巴先做逃。枉你说爱我的胆量和真心。"
      },
      {
        avatar:
          "https://portrait.gitee.com/uploads/avatars/user/2813/8441097_shaynas_1610801433.png!avatar200",
        title: "马克思",
        info: "没有锚的船当然也可以航行，只是紧张充满你的一生。"
      }
    ];

    return {
      data
    };
  }
};
</script>
<style>
.ant-list-item-meta-description {
  line-height: 28px;
}
</style>
<template>
  <div id="home">
    <n-space vertical size="large">
      <n-card>
        <n-avatar
          :size="64"
          src="https://portrait.gitee.com/uploads/avatars/user/2813/8441097_shaynas_1610801433.png!avatar200"
          style="vertical-align: middle"
        >
        </n-avatar>
        <span style="margin-left: 30px; margin-top: 0px; font-size: 18px"
          >&nbsp; 江湖无名，安心练剑</span
        >
        <div style="float: right; width: 340px">
          <n-grid
            x-gap="12"
            :cols="3"
            style="font-weight: 500; margin-top: 18px; font-size: 18px"
          >
            <n-gi> 项目 / 80 </n-gi>
            <n-gi> 排名 / 15 </n-gi>
            <n-gi> 访问 / 35 </n-gi>
          </n-grid>
        </div>
      </n-card>
      <n-grid x-gap="10" y-gap="10">
        <n-gi span="18">
          <n-card title="项目">
            <n-grid class="app-project-grid" :x-gap="12" :y-gap="8" :cols="3">
              <n-grid-item>
                <n-avatar
                  src="https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png"
                ></n-avatar>
                <span style="margin-left: 15px; font-weight: 600">Alipay</span>
                <div style="margin-top: 10px">
                  那是一种内在的东西， 他们到达不了，也无法触及的
                </div>
              </n-grid-item>
              <n-grid-item>
                <n-avatar
                  src="https://gw.alipayobjects.com/zos/rmsportal/zOsKZmFRdUtvpqCImOVY.png"
                ></n-avatar>
                <span style="margin-left: 15px; font-weight: 600">Angular</span>
                <div style="margin-top: 10px">
                  希望是一个好东西，也许是最好的，好东西是不会消亡的
                </div>
              </n-grid-item>
              <n-grid-item>
                <n-avatar
                  src="https://gw.alipayobjects.com/zos/rmsportal/siCrBXXhmvTQGWPNLBow.png"
                ></n-avatar>
                <span style="margin-left: 15px; font-weight: 600"
                  >Bootstrap</span
                >
                <div style="margin-top: 10px">
                  城镇中有那么多的酒馆，她却偏偏走进了我的酒馆
                </div>
              </n-grid-item>
              <n-grid-item>
                <n-avatar
                  src="https://gw.alipayobjects.com/zos/rmsportal/dURIMkkrRFpPgTuzkwnB.png"
                ></n-avatar>
                <span style="margin-left: 15px; font-weight: 600"
                  >Ant Design</span
                >
                <div style="margin-top: 10px">
                  那时候我只会想自己想要什么，从不想自己拥有什么
                </div>
              </n-grid-item>
              <n-grid-item>
                <n-avatar
                  src="https://portrait.gitee.com/uploads/avatars/user/2813/8441097_shaynas_1610801433.png!avatar200"
                ></n-avatar>
                <span style="margin-left: 15px; font-weight: 600">Shayna</span>
                <div style="margin-top: 10px">
                  接近，是我对一切的态度，是我对一切态度的距离
                </div>
              </n-grid-item>
              <n-grid-item>
                <n-avatar
                  src="https://gw.alipayobjects.com/zos/rmsportal/ComBAopevLwENQdKWiIn.png"
                ></n-avatar>
                <span style="margin-left: 15px; font-weight: 600">Vue</span>
                <div style="margin-top: 10px">
                  生命就像一盒巧克力，结果往往出人意料
                </div>
              </n-grid-item>
            </n-grid>
          </n-card>
        </n-gi>
        <n-gi span="6">
          <n-card title="任务">
            <n-timeline class="app-home-timeline">
              <n-timeline-item type="success" color="green">
                <p>阶段一：Technical testing 1</p>
                <p>阶段二：Technical testing 2</p>
                <p>阶段三：Technical testing 3</p>
              </n-timeline-item>
              <n-timeline-item type="warning">
                <p>阶段一：Technical testing 1</p>
                <p>阶段二：Technical testing 2</p>
                <p>阶段三：Technical testing 3</p>
                <p>阶段四：Technical testing 4</p>
              </n-timeline-item>
              <n-timeline-item type="info" color="red">
                <p>阶段一：Technical testing 1</p>
                <p>阶段二：Technical testing 2</p>
                <p>阶段三：Technical testing 3</p>
              </n-timeline-item>
              <n-timeline-item type="error">
                <p>阶段一：Technical testing 1</p>
                <p>阶段二：Technical testing 2</p>
                <p>阶段三：Technical testing 3</p>
                <p>阶段四：Technical testing 4</p>
              </n-timeline-item>
              <n-timeline-item color="gray">
                <p>阶段一：Technical testing 1</p>
                <p>阶段二：Technical testing 2</p>
                <p>阶段三：Technical testing 3</p>
                <p>阶段四：Technical testing 4</p>
                <p>阶段五：Technical testing 5</p>
              </n-timeline-item>
            </n-timeline>
          </n-card>
        </n-gi>
      </n-grid>
    </n-space>
  </div>
</template>

<script setup lang="tsx"></script>

<style scoped lang="less">
  .app-home-timeline {
    line-height: 30px;
  }
  .app-project-grid {
    .n-avatar {
      vertical-align: middle;
    }
  }
</style>

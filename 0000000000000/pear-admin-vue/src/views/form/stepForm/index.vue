<template>
  <page-container
    content="将一个冗长或用户不熟悉的表单任务分成多个步骤，指导用户完成。"
  >
    <a-card>
      <a-steps
        class="form-step"
        :current="current"
      >
        <a-step v-for="(item, key) in steps" :key="key" :title="item.title"/>
      </a-steps>
      <div class="steps-content">
        <component :is="steps[current].content" @next="next" @prev="prev" @finish="finish" />
      </div>
    </a-card>
  </page-container>
</template>

<script lang="ts">
import { defineComponent, ref } from 'vue'
import Step1 from './components/Step1.vue'
import Step2 from './components/Step2.vue'
import Step3 from './components/Step3.vue'

export default defineComponent({
  name: 'index',
  components: {
    Step1,
    Step2,
    Step3
  },
  setup () {
    const current = ref<number>(0)
    const next = () => {
      current.value++
    }
    const prev = () => {
      current.value--
    }
    const finish = () => {
      current.value = 0
    }
    return {
      current,
      steps: [
        {
          title: '填写转账信息',
          content: Step1
        },
        {
          title: '确认转账信息',
          content: Step2
        },
        {
          title: '完成',
          content: Step3
        }
      ],
      next,
      prev,
      finish
    }
  }
})
</script>

<style scoped lang="less">
.form-step {
  max-width: 750px;
  margin: 16px auto;
}
</style>

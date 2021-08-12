<template>
      <a-card class="failure">
        <a-result
    status="error"
    title="Submission Failed"
    sub-title="Please check and modify the following information before resubmitting."
  >
    <template #extra>
      <a-button key="console" type="primary">Go Console</a-button>
      <a-button key="buy">Buy Again</a-button>
    </template>

    <div class="desc">
      <p style="font-size: 16px">
        <strong>The content you submitted has the following error:</strong>
      </p>
      <p>
        <close-circle-outlined :style="{ color: 'red' }" />
        Your account has been frozen
        <a>Thaw immediately &gt;</a>
      </p>
      <p>
        <close-circle-outlined :style="{ color: 'red' }" />
        Your account is not yet eligible to apply
        <a>Apply Unlock</a>
      </p>
    </div>
  </a-result>
      </a-card>
</template>

<script lang="ts">
import { CloseCircleOutlined } from '@ant-design/icons-vue'
import { defineComponent } from 'vue'
export default defineComponent({
  name: 'index',
  components: {
    CloseCircleOutlined
  }
})
</script>

<style lang="less" scoped>
.failure{
  margin:10px
}
</style>

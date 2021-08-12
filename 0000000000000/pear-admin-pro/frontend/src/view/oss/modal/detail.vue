<template>
  <a-modal
    :visible="visible"
    title="查看"
    cancelText="取消"
    @ok="submit"
    @cancel="cancel"
  >
    <a-image :src="formState.path"></a-image>
  </a-modal>
</template>
<script>
  import { defineComponent, reactive, ref,watch } from "vue";
  export default defineComponent({
    props: {
      visible: {
        type: Boolean,
      },
      record: {
        type: Object
      }
    },
    emit: ["close"],
    setup(props, context) {

      watch(props, (props) => {
        formState.id = props.record.id;
        formState.path = props.record.path;
      })

      const formRef = ref();

      const formState = reactive({
        sort: 0,
        enable: "true",
      });

      const cancel = (e) => {
        context.emit("close", false);
      };

      const submit = (e) => {
        context.emit("close", false);
      };

      const tokenKey = localStorage.getItem("token_key");
      const token = localStorage.getItem("token")

      const headers = {
        "Authorization": token,
        "Authorization-key": tokenKey
      };

      return {
        cancel,
        submit,
        headers,
        formRef,
        formState,
        labelCol: { span: 6 },
        wrapperCol: { span: 18 },
      };
    },
  });
</script>

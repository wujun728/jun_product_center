<template>
  <a-modal
    :visible="visible"
    title="文件上传"
    cancelText="取消"
    okText="提交"
    @ok="submit"
    @cancel="cancel"
  >
    <a-upload
      action="http://localhost:9292/api/sys/oss/upload"
      list-type="picture"
      v-model:file-list="fileList"
      :headers="headers"
    >
      <a-button>
        <upload-outlined></upload-outlined>
        upload
      </a-button>
    </a-upload>
  </a-modal>
</template>
<script>
import { defineComponent, reactive, ref } from "vue";
export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
  },
  emit: ["close"],
  setup(props, context) {
    const formRef = ref();

    const formState = reactive({
      sort: 0,
      enable: "true",
    });

    const formRules = {
      name: [{ required: true, message: "请输入岗位名称", trigger: "blur" }],
      code: [{ required: true, message: "请输入岗位编号", trigger: "blur" }],
    };

    const saveKey = "save";

    const submit = (e) => {
      
    };


    const fileList = ref([
      {
        uid: '-1',
        name: 'xxx.png',
        status: 'done',
        url: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
        thumbUrl: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
      },
      {
        uid: '-2',
        name: 'yyy.png',
        status: 'done',
        url: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
        thumbUrl: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
      },
      {
        uid: '-3',
        name: 'xxx.png',
        status: 'done',
        url: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
        thumbUrl: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
      },
      {
        uid: '-4',
        name: 'yyy.png',
        status: 'done',
        url: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
        thumbUrl: 'https://zos.alipayobjects.com/rmsportal/jkjgkEfvpUPVyRjUImniVslZfWPnJuuZ.png',
      },
    ]);

    const cancel = (e) => {
      context.emit("close", false);
    };

    const tokenKey = localStorage.getItem("token_key");
    const token = localStorage.getItem("token")

    const headers = {
        "Authorization": token,
        "Authorization-key": tokenKey
    };

    return {
      submit,
      cancel,
      headers,
      formRef,
      fileList,
      formState,
      formRules,
    
      labelCol: { span: 6 },
      wrapperCol: { span: 18 },
    };
  },
});
</script>
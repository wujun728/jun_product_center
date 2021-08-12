<template>
  <a-modal
    :visible="visible"
    title="修改岗位"
    cancelText="取消"
    okText="提交"
    @ok="submit"
    @cancel="cancel"
  >
    <a-form
       ref="formRef"
      :model="formState"
      :rules="formRules"
      :label-col="labelCol"
      :wrapper-col="wrapperCol"
    >
      <!-- 当前部门为根部门, 则不提供上级部门选择 -->
      <a-form-item label="上级" name="parent" v-if="formState.parent != 0 ">
          <a-tree-select
            v-model:value="formState.parent"
            style="width: 100%"
            :tree-data="state.depts"
            placeholder="所属部门"
            replace
            tree-default-expand-all
            :replaceFields="replaceFields"
          >
          </a-tree-select>
      </a-form-item>
      <a-form-item ref="name" label="名称" name="name">
        <a-input v-model:value="formState.name" />
      </a-form-item>
      <a-form-item ref="sort" label="排序" name="sort">
        <a-input-number v-model:value="formState.sort" />
      </a-form-item>
      <a-form-item label="状态" name="state">
        <a-switch v-model:checked="formState.enable" />
      </a-form-item>
      <a-form-item ref="address" label="地址" name="address">
        <a-input v-model:value="formState.address" />
      </a-form-item>
      <a-form-item label="备注" name="remark">
        <a-textarea v-model:value="formState.remark" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script>
import { message } from 'ant-design-vue';
import { edit, tree } from "@/api/module/dept";
import { defineComponent, reactive, ref, toRaw, watch } from "vue";
export default defineComponent({
  props: {
    visible: {
      type: Boolean,
    },
    record: {
      type: Object,
    }
  },
  emit: ["close"],
  setup(props, context) {

    const state = reactive({ 
      depts: [], 
    })
    
    const formRef = ref();
    
    const formState = reactive({
      sort: 0,
      enable: true,
    });

    watch(props,(props) => {
        formState.id = props.record.id
        formState.name = props.record.name
        formState.sort = props.record.sort
        formState.parent = props.record.parent
        formState.remark = props.record.remark
        formState.enable = props.record.enable
        formState.address = props.record.address
    })

    const formRules = {
      name: [ { required: true, message: '请输入部门名称', trigger: 'blur'} ],
      address: [ { required: true, message: '请输入详细地址', trigger: 'blur'} ],
      parent: [ { required: true, message: '请选择上级部门', trigger: 'change'} ]
    };

    const submit = (e) => {
      formRef.value
        .validate()
        .then(() => {
          edit(toRaw(formState)).then((response)=>{
              if(response.success){
                message.success({ content: '保存成功', duration: 1 }).then(()=>{
                  cancel();
                });
              }else{
                message.success({ content: '保存失败', duration: 1 }).then(()=>{
                  cancel();
                });
              }
          });
        })
        .catch(error => {
          console.log('error', error);
        });
    };

    const cancel = (e) => {
      formRef.value.resetFields();
      context.emit("close", false);
    };

    const loadTree = () => {
      tree({}).then((response)=>{
        state.depts = response.data;
      })
    }

    /// 加载部门
    loadTree();

    return {
      state,
      submit,
      cancel,
      formRef,
      formState,
      formRules,
      
      labelCol: { span: 6 },
      wrapperCol: { span: 18 },

      replaceFields: {children:'children', title:'name', key:'id', value: 'id' }
    };
  },
});
</script>
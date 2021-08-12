<template>
  <div class="p-switch" :class="[type ? 'p-switch-'+type:'']">
    <input type="checkbox"
      :checked="modelValue"
      :disabled="disabled"
      @change="changeHandle"/>
  </div>
</template>
<script>
import "./index.scss"
export default {
  name: "p-switch",
  props: {
    disabled:{
      type:Boolean,
      default:false
    },
    options:{
      type:Object
    },
    type:{
      type:String,
      default:"primary"
    },
    modelValue:{
      type:String,
      default:"false"
    }
  },
  emits:["change"],
  setup(props,context){
      const changeHandle = function(e){
          const isCheck = e.target.checked
          context.emit("change",isCheck);
          context.emit("update:modelValue",isCheck);
      }
      return {
          changeHandle
      }
  }
}
</script>
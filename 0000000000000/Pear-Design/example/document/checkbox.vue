<template>
    <div class="checkboxs">
        <h3>基础</h3>
        <p-checkbox v-model="check" value="10">阅读</p-checkbox>&nbsp;
        <p-checkbox v-model="check" value="20">游泳</p-checkbox>&nbsp;
        <p-checkbox v-model="check" value="30">读书</p-checkbox>
        <h3>禁用</h3>
        <p-checkbox v-model="check" value="10">阅读</p-checkbox>&nbsp;
        <p-checkbox v-model="check" value="20">游泳</p-checkbox>&nbsp;
        <p-checkbox v-model="check" value="30">读书</p-checkbox>&nbsp;
        <p-checkbox v-model="check" value="40" disabled>运动</p-checkbox>
        <h3>多选框组</h3>
        <p-checkbox-group v-model="anotherValue">
        <p-checkbox value="10">阅读</p-checkbox>&nbsp;
        <p-checkbox value="20">游泳</p-checkbox>&nbsp;
        <p-checkbox value="30">读书</p-checkbox>&nbsp;
        <p-checkbox value="40" disabled>运动</p-checkbox>
        </p-checkbox-group>
    </div>
</template>
<script>
export default{
    data(){
        return {
            check:true,
            uncheck:false,
            anotherValue: ["10","20"]
        }
    }
}
</script>
<style>
h3{
  color: rgba(0, 0, 0, 0.65);
}
</style>
<template>
    <div>
        <h3>基础</h3>
        <p-radio v-model="check1" value="1">音乐</p-radio>&nbsp;
        <p-radio v-model="check1" value="2">阅读</p-radio>&nbsp;
        <p-radio v-model="check1" value="3">运动</p-radio>
        <h3>禁用</h3>
        <p-radio v-model="check2" value="1">音乐</p-radio>&nbsp;
        <p-radio v-model="check2" value="2">阅读</p-radio>&nbsp;
        <p-radio v-model="check2" value="3">运动</p-radio>&nbsp;
        <p-radio v-model="check2" value="4" disabled>游泳</p-radio>
        <h3>单选组</h3>
        <p-radio-group v-model="check3">
            <p-radio  value="1">音乐</p-radio>&nbsp;
            <p-radio  value="2">阅读</p-radio>&nbsp;
            <p-radio  value="3">运动</p-radio>
        </p-radio-group>
    </div>
</template>
<script>
export default{
    data(){
        return{
            check1:1,
            check2:2,
            check3:3
        }
    }
}
</script>
<style>
h3{
  color: rgba(0, 0, 0, 0.65);
}
</style>
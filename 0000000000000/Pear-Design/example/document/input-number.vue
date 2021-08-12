<template>
    <div>
        <h3>基础</h3>
        <p-input-number v-model="num1"></p-input-number>
        <h3>范围</h3>
        <p-input-number v-model="num2" min="20" max="30"></p-input-number>
        <h3>步长</h3>
        <p-input-number v-model="num3" step="10"></p-input-number>
    </div>
</template>
<script>
export default {
    data(){
        return {
            num1:1,
            num2:30,
            num3:25
        }
    }
}
</script>
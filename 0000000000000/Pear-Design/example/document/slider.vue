<template>
    <div>
        <h3>基础</h3>
        <p-slider v-model="num1"></p-slider>
        <h3>范围</h3>
        <p-slider min="200" max="400" v-model="num2"></p-slider>
        <h3>步长</h3>
        <p-slider step="10" min="200" max="400" v-model="num3"></p-slider>
    </div>
</template>
<script>
export default{
    data(){
        return {
            num1:20,
            num2:300,
            num3:300
        }
    }
}
</script>
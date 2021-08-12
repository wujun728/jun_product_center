<template>
    <lable class="p-button-container">
        <slot></slot>
    </lable>
</template>
<script>
import "./index.scss";
export default{
    name:"p-button-container",
    props:{
        space:String
    }
}
</script>
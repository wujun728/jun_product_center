<template>
    <div class="p-row" :class="[space?'p-col-space'+space:'']">
        <slot></slot>
    </div>
</template>

<script>
import "./index.scss";
export default{
    name: "p-row",
    props:{
        space:String
    }
}
</script>
<template>
    <div class="p-card" :class="[shadow ? 'p-card-shadow' : '',type ? 'p-card-' + type : '']">
        <div class="p-card-header" v-if="title">{{title}}</div>
        <div class="p-card-body">
            <slot></slot>
        </div>
    </div>
</template>
<script>
import "./index.scss"
export default{
    name:"p-card",
    props:{
        title: String,
        shadow: Boolean,
        type: String
    }
}
</script>
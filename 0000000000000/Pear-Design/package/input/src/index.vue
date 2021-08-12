<template>
    <div class="p-input">
        <input :type="type" :placeholder="placeholder" :disabled="disabled">
    </div>
</template>
<script>
import "./index.scss";

export default{
    name: "p-input",
    props: {
        type: {
            type: String,
            default: "text"
        },
        placeholder: {
            type: String,
            default: "Enter"
        },
        disabled: Boolean
    }    
}
</script>
<style lang="scss">
.p-input input{
    box-sizing: border-box;
    margin: 0;
    font-variant: tabular-nums;
    list-style: none;
    font-feature-settings: "tnum";
    position: relative;
    display: inline-block;
    width: 100%;
    height: 36px;
    padding: 4px 11px;
    color: rgba(0,0,0,.65);
    font-size: 14px;
    line-height: 1.5;
    background-color: #fff;
    background-image: none;
    border: 1px solid #d9d9d9;
    border-radius: 3px;
    transition: all .3s;
    outline: none;
}
.p-input input:focus{ 
    outline: none;
    border: 1px solid #00c58e; 
}
.p-input input[disabled]{
    cursor:not-allowed;
    opacity: 0.6;  
}
</style>

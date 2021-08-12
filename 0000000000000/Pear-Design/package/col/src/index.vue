<template>
  <div
    class="p-col"
    :class="[span?'p-col-span'+span:'',
             md?'p-col-md'+md:'',
             xs?'p-col-xs'+xs:'',
             lg?'p-col-lg'+lg:'',
             sm?'p-col-sm'+sm:'',
             offset?'p-col-span-offset'+offset:'',
             mdOffset?'p-col-md-offset'+mdOffset:'',
             smOffset?'p-col-sm-offset'+smOffset:'',
             xsOffset?'p-col-xs-offset'+xsOffset:'',
             lgOffset?'p-col-lg-offset'+lgOffset:'']">
    <slot></slot>
  </div>
</template>
<script>
import "./index.scss";
export default{
  name: "p-col",
  props: {
    span: String,
    md: String,
    xs: String,
    lg: String,
    sm: String,
    offset: String,
    mdOffset: String,
    smOffset: String,
    xsOffset: String,
    lgOffset: String,
  }
};
</script>
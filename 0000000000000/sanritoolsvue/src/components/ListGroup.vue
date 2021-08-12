<template>
    <div>
      <ul class="list-group" style="min-height: 1px" >
        <li :class="item.active ? 'active list-group-item':'list-group-item'" v-for="(item,index) in listFilter" @click="clickItem(item,index)" :title="item.value">{{item.value}}</li>
      </ul>
    </div>
</template>

<script>

  export default {
    name: 'index',
    props:{
      list:{
        type: Array,
        request: true
      },
      props: {
        type: Object,
        require: false
      }
    },
    data(){
      return {
        activeIndex : 0,
        view: {
          show: true
        }
      }
    },
    created(){
      if (this.props){
        this.activeIndex = this.props.active || 0;
      }
    },
    mounted(){
    },
    methods:{
      clickItem(item,index){
        this.activeIndex = index;
        item.active = true;
        this.$emit('click-item',item.value,index)
      }
    },
    computed:{
      listFilter(){
        let listWrap = [];
        for (let i = 0; i < this.list.length; i++) {
          listWrap.push({value:this.list[i],active:false});
        }

        // 查找 active
        if (listWrap.length > this.activeIndex){
          listWrap[this.activeIndex].active = true;
        }

        return listWrap;
      }
    },
    watch:{
      list(){
        this.activeIndex = 0;
      }
    }
  }
</script>

<style scoped>
  @import "../assets/bootstrap.css";

  .list-group-item{
    overflow-x: hidden;
    text-overflow: ellipsis;
  }
</style>

<template>
    <div>
      <el-row>
        <el-select v-model="input.serizlizer" filterable clearable placeholder="序列化" size="small">
          <el-option v-for="serizlizer in serializers" :key="serizlizer" :label="serizlizer" :value="serizlizer" />
        </el-select>
        <el-select v-model="input.classloader" filterable clearable placeholder="类加载器" size="small">
          <el-option v-for="classloader in classloaders" :key="classloader" :label="classloader" :value="classloader" />
        </el-select>
        加载数量 <el-input-number v-model="input.loadCount" :min="1" :max="100" label="加载数量" size="small"/>
        <el-button  type="primary" icon="el-icon-search" @click="dataShowChange" size="small">查询</el-button>
        <el-button  plain icon="el-icon-arrow-right" @click="nextPartition" size="small">下一分区</el-button>
      </el-row>
      <el-row >
        <el-table
          v-loading="loading"
          :data="handleDatas"
          border
          stripe
          height="500"
          size="mini">
          <el-table-column
            type="index"
            width="50"/>
          <el-table-column
            prop="partition"
            label="partition"
            width="70"/>
          <el-table-column
            prop="offset"
            label="offset"
            width="100"/>
          <el-table-column
            prop="time"
            label="time"
            width="150"/>
          <el-table-column
            prop="data"
            label="data"/>
          <el-table-column
            fixed="right"
            label="操作"
            width="100">
            <template slot-scope="scope">
              <el-button type="text" size="small" @click="showJsonData(scope.row)">展示为 JSON</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-row>

      <el-drawer
        :visible.sync="drawer.visible"
        :with-header="false"
        direction="rtl" append-to-body>
        <json-editor :json="drawer.data" />
      </el-drawer>
    </div>
</template>

<script>
  import core from '../../api/core'
  import {parseTime} from '@/utils'
  import JsonEditor from '../../components/JsonEditor'

  export default {
    name: 'DataView',
    components: {JsonEditor},
    data(){
      return {
        serializers: [],
        classloaders: [],
        input: {
          serizlizer: 'string',
          classloader: null,
          loadCount : 10
        },
        drawer: {
          visible: false,
          data: null
        }
      }
    },
    props:{
      datas: {
        type: Array,
        require: true
      },
      configs: {
        type: Object
      },
      loading: {
        type: Boolean
      }
    },
    methods:{
      dataShowChange(){
        this.$emit('update-data-show',this.input.serizlizer,this.input.classloader,this.input.loadCount)
      },
      nextPartition(){
        this.$emit('next-partition',this.input.serizlizer,this.input.classloader,this.input.loadCount)
      },
      showJsonData(row){
        this.drawer.visible = true;
        this.drawer.data = JSON.parse(row.data)
      }
    },
    mounted() {
      core.serializers().then(res => {
        this.serializers = res.data;
        // if (this.serializers && this.serializers.length > 0){
        //   this.input.serizlizer = this.serializers[0];
        // }
      })
      core.classloaders().then(res => {
        this.classloaders = res.data;
        // if (this.classloaders && this.classloaders.length > 0){
        //   this.input.classloader = this.classloaders[0];
        // }
      });

      this.dataShowChange();
    },
    computed: {
      handleDatas(){
        return this.datas.map(item => Object.assign({time: parseTime(item.timestamp)},item));
      }
    }
  }
</script>

<style scoped>

</style>

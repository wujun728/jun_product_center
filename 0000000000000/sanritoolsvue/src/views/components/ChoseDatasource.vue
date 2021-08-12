<template>
  <div>
    <el-row>
      <el-col :span="24">
        <el-select v-model="input.connect" filterable clearable placeholder="选择连接" @change="switchConnection" size="small">
          <el-option
                  v-for="connection in connections"
                  :key="connection"
                  :label="connection"
                  :value="connection">
          </el-option>
        </el-select>

        <el-select class="margin-left" v-model="input.catalog" filterable clearable placeholder="选择库" @change="switchCatalog" size="small">
          <el-option v-for="catalogMeta in catalogs"
                     :key="catalogMeta.catalog" :label="catalogMeta.catalog" :value="catalogMeta.catalog"
          />
        </el-select>
      </el-col>
    </el-row>
    <el-row v-if="schemas && schemas.length > 0">
      <el-col :span="24">
        <div class=" padding-top text-forestgreen">当前 schema 数量: {{schemas.length}} </div>
        <el-radio-group v-model="input.schema" @change="datasourceChange" >
          <el-radio-button size="small" v-for="schema in schemas" :value="schema" :label="schema" :key="schema"/>
        </el-radio-group>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import database from "../../api/database";

  export default {
    name: "ChoseDatasource",
    props:{
      connect: String,
      catalog: String
    },
    data(){
      return {
        input: {
          connect: null,
          catalog: null,
          schema: null
        },
        schemas: [],
        catalogs: [],
        connections: []
      }
    },
    methods: {
      datasourceChange(){
        this.$emit('change',this.input);
      },
      switchConnection(value){
        this.input.connect = value;
        // 获取当前连接信息
        if (value){
          database.catalogs(value).then(res => {
            this.catalogs = res.data;
            if (res.data && res.data.length > 0){
              if (this.catalog){
                this.switchCatalog(this.catalog);
              }else{
                this.switchCatalog(res.data[0].catalog)
              }
            }
          });
        }
      },
      switchCatalog(catalog){
        this.input.catalog = catalog;
        this.datasourceChange();
        // 加载当前 catalog 的 schemas
        if (this.catalogs){
          let currentCatalogFilter = this.catalogs.filter(item => item.catalog === catalog);
          if (currentCatalogFilter && currentCatalogFilter.length > 0){
            this.schemas = currentCatalogFilter[0].schemas;
          }
        }
      },
      refreshConnect(){
        database.connections().then(res => {
          this.connections = res.data;
          if (res.data && res.data.length> 0){
            console.log(this.connect,'connect')
            if (this.connect){
              this.switchConnection(this.connect)
            }else{
              this.switchConnection(res.data[0]);
            }

          }
        })
      }
    },
    mounted() {
      this.refreshConnect();
      if (this.connect) {

      }
    }
  }
</script>

<style scoped>

</style>

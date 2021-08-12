<template>
  <div class="panel panel-default">
    <div class="panel-heading">包路径设置</div>
    <div class="panel-body">
      <el-form size="mini" label-width="120px" >
          <el-form-item label="基础包">
            <el-input v-model="form.base" @keyup.native="autoCompleteConfig" placeholder="基础包，自动生成下面的包数据"></el-input>
          </el-form-item>
          <el-form-item label="项目名">
            <el-input v-model="form.projectName" placeholder="项目名称"></el-input>
          </el-form-item>
          <el-form-item label="entity+mapper">
            <el-col :span="11">
              <el-input v-model="form.entity" placeholder="entity"></el-input>
            </el-col>
            <el-col :span="11" class="margin-left">
              <el-input v-model="form.mapper" placeholder="mapper"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="action+service">
            <el-col :span="11">
              <el-input v-model="form.controller" placeholder="controller"></el-input>
            </el-col>
            <el-col :span="11" class="margin-left">
              <el-input v-model="form.service" placeholder="service"></el-input>
            </el-col>
            
          </el-form-item>
        
        <el-form-item label="vo+dto" >
          <el-col :span="11">
            <el-input v-model="form.vo" placeholder="vo"></el-input>
          </el-col>
          <el-col :span="11" class="margin-left">
            <el-input v-model="form.dto" placeholder="dto"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="param">
          <el-input v-model="form.param" placeholder="param"></el-input>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
  export default {
    name: "PackageConfig",
    data(){
      return {
        form: {
          entity: null,
          mapper: null,
          controller: null,
          service: null,
          vo: null,
          dto: null,
          param: null,
          base: null,
          projectName: null
        }
      }
    },
    methods: {
      getPackageConfig(){
        return this.form;
      },
      autoCompleteConfig(){
        if(this.form.base){
          let base  = this.form.base;
          //如果基础输入有值，则自动填充其它输入框
          let projectName = base;
          let split = base.split('.');
          if(split.length > 0){
            projectName = split[split.length - 1];
          }
          Object.assign(this.form,{
            entity:base+'.mapper.entity',
            mapper:base+'.mapper',
            controller:base+'.web.controller',
            service:base+'.service',
            vo:base+'.dto.vo',
            dto:base+'.dto',
            param:base+'.dto.param',
            groupId:base,
            artifactId: projectName,
            projectName: projectName
          })
        }
      }
    }
  }
</script>

<style scoped>
  /*.el-form-item .el-input{*/
  /*  margin-left: 10px;*/
  /*}*/
</style>
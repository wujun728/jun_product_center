<template>
  <div class="app-container">
    <el-row>
      <el-col>
        <el-select v-model="input.connect" @change="switchConnect" size="small">
          <el-option v-for="connect in connects" :key="connect" :value="connect" :label="connect" />
        </el-select>
      </el-col>
    </el-row>
    <el-row class="margin-top">
      <el-col :span="6">
        <list-group :list="services" />
      </el-col>
      <el-col :span="17" class="margin-left">
        <el-row>
          <el-col :span="24">
            <p>当前服务: {{input.service}}</p>
            <el-table
                    :data="providers"
                    border
                    stripe
                    highlight-current-row
                    size="mini"
                    style="width: 100%"
            >
              <el-table-column type="expand">
                <template slot-scope="props">
                  <div class="key-value-property">
                    <p><label>提供者地址 </label>{{ props.row.address }}</p>
                    <p><label >对应应用 </label>{{ props.row.application }}</p>
                    <p><label >提供者分组 </label> {{ props.row.group }}</p>
                    <p><label>提供者版本 </label> {{ props.row.version }}</p>
                    <p><label >方法列表</label> {{ props.row.methods }}</p>
                    <p><label>dubbo 版本</label> {{ props.row.dubbo }}</p>
                    <p><label>注册时间</label> {{formatTime(props.row.timestamp)}}</p>
                    <p><label>提供类</label> {{ props.row.serviceClassName }}</p>
                  </div>

                </template>
              </el-table-column>
              <el-table-column type="index"  width="50"/>
              <el-table-column
                      prop="address"
                      label="地址"
                      width="150"/>
              <el-table-column
                      prop="application"
                      label="应用"
                      width="180"/>
              <el-table-column
                      prop="group"
                      label="分组"
                      width="150"/>
              <el-table-column
                      prop="version"
                      label="版本"
                      width="70"/>
<!--              <el-table-column-->
<!--                      prop="methods"-->
<!--                      label="方法列表"-->
<!--                      width="100"/>-->
<!--              <el-table-column-->
<!--                      prop="dubbo"-->
<!--                      label="dubbo 版本"-->
<!--                      width="100"/>-->
              <el-table-column
                      label="注册时间">
                <template slot-scope="scope">
                  <span>{{formatTime(scope.row.timestamp)}}</span>
                </template>
              </el-table-column>
<!--              <el-table-column-->
<!--                      prop="serviceClassName"-->
<!--                      label="提供类"/>-->
            </el-table>
          </el-col>
        </el-row>
        <el-row class="margin-top margin-bottom">
          <el-col :span="24">
            <el-select v-model="input.classloaderName" @change="switchClassloader" size="small">
              <el-option v-for="classloader in classloaders" :key="classloader" :value="classloader" :label="classloader" />
            </el-select>
            <template v-if="!findClass"> {{input.classloaderName}} 没有找到当前接口</template>
            <template v-if="findClass">
              {{input.provider.serviceClassName}}
              <el-select v-model="input.method"  size="small">
                <el-option v-for="method in methods" :key="method.name" :value="method.name" :label="method.name" />
              </el-select>
              <p>
                {{showCurrentMethod()}}

                <el-button-group>
                  <el-button size="small" type="success" @click="buildParams">构建参数</el-button>
                  <el-button size="small" type="primary" @click="doInvoke">发起调用</el-button>
                </el-button-group>
              </p>
            </template>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <p>入参</p>
            <vue-json-editor v-model="input.params" :show-btns="false" lang="zh"  />
          </el-col>
          <el-col :span="12">
            <p>返回值</p>
            <vue-json-editor v-model="invokeResult" :show-btns="false" lang="zh"  />
          </el-col>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import core from "../../api/core";
  import dubbo from "../../api/dubbo";

  import ListGroup from "../../components/ListGroup";
  import vueJsonEditor from 'vue-json-editor'

  import {parseTime} from "../../utils";

  export default {
    name: 'dubbo',
    components: {ListGroup, vueJsonEditor},
    data(){
      return {
        input: {
          connect: null,
          service: null,
          provider: null,
          method: null,
          classloaderName: null,
          className: null,
          params: null
        },
        connects: [],
        services: [],
        providers: [],
        methods: [],
        classloaders: [],
        loadClasses: [],
        invokeResult: null
      }
    },
    methods: {
      doInvoke(){
        let params = {
          connName: this.input.connect,
          serviceName: this.input.service,
          classloaderName: this.input.classloaderName,
          methodName: this.input.method,
          args: this.input.params,
          providerURL: this.input.provider.origin
        };
        dubbo.invoke(params).then(res => {
          this.invokeResult = res.data;
        })
      },
      showCurrentMethod(){
        for (let i = 0; i < this.methods.length; i++) {
          if (this.methods[i].name === this.input.method){
            return this.formatMethod(this.methods[i]);
          }
        }
        return '';
      },
      formatMethod(method){
        let args = [];
        if (method.args){
          for (let j = 0; j < method.args.length ; j++) {
            let arg = method.args[j];
            args.push(arg.type +' '+arg.name);
          }
        }
        let methodInfo = method.returnType +' '+method.name+'('+args.join(',')+')';
        return methodInfo;
      },
      formatTime(timestamp){
        return parseTime(timestamp)
      },
      switchConnect(connect){
        this.input.connect = connect;
        dubbo.services(connect).then(res => {
          this.services = res.data;
          if (res.data && res.data.length > 0){
            this.switchService(res.data[0]);
          }
        })
      },
      switchService(service){
        this.input.service = service;
        dubbo.providers(this.input.connect,service).then(res => {
          this.providers = res.data;
          if (res.data && res.data.length > 0){
            this.switchProvider(res.data[0]);
          }
        })
      },
      switchProvider(provider){
        this.input.provider = provider;
      },
      switchMethod(method){
        this.input.method = method;
        // this.buildParams();
      },
      switchClassloader(classloader){
        this.input.classloaderName = classloader;
        core.listLoadedClasses(classloader).then(res => {
          this.loadClasses = res.data;
          if (this.trueClassloader()){
            // 加载类对应的方法列表
            if (res.data && res.data.length > 0){
              this.switchClassName(res.data[0]);
            }
          }
        })
      },
      trueClassloader(){
        if (this.loadClasses && this.loadClasses.length > 0 && this.input.provider){
          if (this.loadClasses.indexOf(this.input.provider.serviceClassName) != -1){
            return true;
          }
        }
        return false;
      },
      switchClassName(className){
        this.input.className = className;

        core.classStruct(this.input.classloaderName,this.input.className).then(res => {
          this.methods = res.data.methods;
          if (this.methods && this.methods.length > 0){
            this.switchMethod(this.methods[0].name);
          }
        })
      },
      buildParams(){
        core.buildMethodParams(this.input.classloaderName,this.input.className,this.input.method).then(res => {
          this.input.params = res.data;
        })
      }
    },
    mounted() {
      // core.moduleConnects('zookeeper').then(res => {
      //   this.connects = res.data;
      //   if (res.data && res.data.length > 0){
      //     this.switchConnect(res.data[0]);
      //   }
      // });
      dubbo.connects().then(res => {
        this.connects = res.data;
        if (res.data && res.data.length > 0){
          this.switchConnect(res.data[0])
        }
      })

      // 加载类加载器和类
      core.classloaders().then(res => {
        this.classloaders = res.data;
        if (res.data && res.data.length > 0){
          this.switchClassloader(res.data[0]);
        }
      })
    },
    computed: {
      findClass(){
        return this.trueClassloader();
      },
      methodNames(){
        if (this.methods && this.methods.length > 0){
          let methodInfos = [];
          for (let i = 0; i < this.methods.length; i++) {
            let method = this.methods[i];
            methodInfos.push(this.formatMethod(method));
          }
          return methodInfos;
        }
        return [];
      }
    }
  }
</script>

<style scoped>
.key-value-property label{
  width: 80px;
  text-align: right;
  line-height: 1.5;
  margin-right: 10px;
  display: inline-block;
}
</style>

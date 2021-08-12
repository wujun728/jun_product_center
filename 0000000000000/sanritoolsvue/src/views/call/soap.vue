<template>
  <div class="app-container">
    <el-row>
      <el-col>
        <el-input placeholder="请输入 WSDL 地址" size="small" v-model="input.wsdl" @change="loadPorts"  class="input-with-select">
          <el-select v-model="input.wsdl" size="small" @change="loadPorts" slot="prepend" placeholder="请选择服务">
            <el-option v-for="example in examples" :label="example.name" :key="example.name" :value="example.wsdl" />
          </el-select>
        </el-input>
      </el-col>
    </el-row>
    <el-row class="margin-top margin-bottom">
      <el-col>
        <el-select v-model="input.port" size="small" @change="loadMethods">
          <el-option v-for="port in ports" :key="port" :label="port" :value="port"/>
        </el-select>
        <el-select v-model="input.method"  size="small" @change="loadMethodDetail">
          <el-option v-for="method in methods" :key="method" :label="method" :value="method"/>
        </el-select>
        <el-button-group>
          <el-button type="primary" size="small" @click="build">构建 SOAP 消息</el-button>
          <el-button type="success" size="small" @click="invoke">发送数据</el-button>
        </el-button-group>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="11" class="">
        <el-row>
          <p>入参结构</p>
          <div class="struct-style">
            <json-view :json="inputStruct" line-height="35" />
          </div>
        </el-row>
        <el-row class="margin-top">
          <p>出参结构</p>
          <div class="struct-style">
            <json-view :json="outputStruct" line-height="35" />
          </div>

        </el-row>
      </el-col>

      <el-col :span="12" class="margin-left">
        <el-input
          type="textarea"
          :autosize="{ minRows: 8, maxRows: 12}"
          v-model="input.request"
          placeholder="入参数据"
        />
        <el-row>
          <p>响应数据</p>
          <pre class="text" style="height: 400px;overflow-y: scroll"><code class="xml hljs">{{response}}</code></pre>
        </el-row>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import core from '../../api/core'
  import soap from '../../api/soap'
  import Vue from 'vue'

  import JsonView from '../../components/JsonView'

  import vkbeautify from 'vkbeautify'

  export default {
    name: 'soap',
    components: { JsonView },
    data(){
      return {
        input: {
          wsdl: null,
          port: null,
          method: null,
          request: null
        },
        examples:[{
            name:'天气数据',
            wsdl:'http://www.webxml.com.cn/WebServices/WeatherWebService.asmx?wsdl'
          },{
            name:'中国邮政编码',
            wsdl:'http://ws.webxml.com.cn/WebServices/ChinaZipSearchWebService.asmx?wsdl'
          },{
            name:'ip 地址来源',
            wsdl:'http://ws.webxml.com.cn/WebServices/IpAddressSearchWebService.asmx?wsdl'
          }
        ],
        ports: [],
        methods: [],
        inputStruct: {},
        outputStruct: {},
        response: null
      }
    },
    methods: {
      loadPorts(){
        soap.ports(this.input.wsdl).then(res => {
          this.ports = res.data;
        });
      },
      loadMethods(){
        soap.methods(this.input.wsdl,this.input.port).then(res => {
          this.methods = res.data;
        })
      },
      loadMethodDetail(){
        soap.input(this.input.wsdl,this.input.port,this.input.method).then(res => {
          this.inputStruct = res.data;
        })
        soap.output(this.input.wsdl,this.input.port,this.input.method).then(res => {
          this.outputStruct = res.data;
        })
      },
      build(){
        soap.build(this.input.wsdl,this.input.port,this.input.method).then(res => {
          this.input.request = res.data;
        })
      },
      invoke(){
        soap.invoke(this.input.wsdl,this.input.port,this.input.method,this.input.request).then(res => {
          this.response = res.data;
          if (this.response){
            this.response = vkbeautify.xml(this.response);
          }
        })
      }
    },
    computed: {

    }
  }
</script>

<style scoped>
 .struct-style{
   height: 320px;
   overflow-y: scroll;
   line-height: 1.5;
 }

 .el-select .el-input {
   width: 130px;
 }
 .input-with-select .el-input-group__prepend {
   background-color: #fff;
 }
</style>

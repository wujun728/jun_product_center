<template>
  <div class="app-container">
    <div class="panel panel-default">
      <div class="panel-heading">设置分词工具和翻译工具</div>
      <div class="panel-body">
        <el-radio-group v-model="input.tokenizer" >
          <el-radio v-for="tokenizer in tokenizers" :key="tokenizer" :label="tokenizer"/>
        </el-radio-group>
        <el-checkbox-group class="margin-top margin-bottom" v-model="input.englishs" >
          <el-checkbox v-for="english in englishs" :key="english" :label="english"/>
        </el-checkbox-group>
      </div>
    </div>

    <el-row>
      <el-col :span="8">
        <div class="panel panel-default">
          <div class="panel-heading">业务词翻译</div>
          <div class="panel-body">
            <el-input size="small" v-model="input.keyword" placeholder="输入业务名来获取英语名称" suffix-icon="el-icon-search"
                      @keyup.enter.native="queryName"/>
            <p v-if="translates && translates.length > 0" v-for="translate in translates" :key="translate">{{translate}}</p>
            <p v-if="!translates || translates.length === 0">暂无结果</p>
          </div>
        </div>
      </el-col>
      <el-col :span="15" class="margin-left">
        <div class="panel panel-default">
          <div class="panel-heading">业务词配置</div>
          <div class="panel-body">
            <el-button type="text"  @click="dialog.visible = true"><i class="fa fa-plus"></i> 新业务</el-button>
            <small class="text-forestgreen ">同名直接覆盖</small>
            <el-row>
              <el-col :span="8">
                <el-table
                  :data="bizTableData"
                  border
                  stripe
                  style="width: 100%"
                  @selection-change="choseBizs"
                  size="mini">
                  <el-table-column type="selection" width="55" />
                  <el-table-column
                    prop="biz"
                    label="biz">
                  </el-table-column>
                </el-table>
              </el-col>
              <el-col :span="15" class="margin-left">
<!--                <el-button type="text" icon="el-icon-edit" >保存</el-button>-->
                <el-input
                  type="textarea"
                  :autosize="{ minRows: 5, maxRows: 10}"
                  placeholder="当前业务词"
                  v-model="contentMerge"
                />
              </el-col>
            </el-row>
          </div>
        </div>
      </el-col>
    </el-row>

    <el-dialog  :visible.sync="dialog.visible" title="新业务">
      <el-button type="primary" size="small" @click="commitBiz">保存</el-button>
      <el-input size="small" class="margin-top margin-bottom" placeholder="当前业务名称" v-model="dialog.input.biz" />
      <el-input
        type="textarea"
        :autosize="{ minRows: 5, maxRows: 10}"
        placeholder="业务词映射"
        v-model="dialog.input.content"
      />
    </el-dialog>
  </div>
</template>

<script>
  import nametool from '../../api/nametool'

  export default {
    name: 'translate',
    data(){
      return {
        input: {
          tokenizer: null,
          englishs: [],
          keyword: null,
          selectedBizs: []
        },
        bizs: [],
        englishs: [],
        tokenizers: [],
        translates: [],
        contentMerge: null,
        dialog: {
          visible: false,
          input: {
            biz:null,
            content: null
          }
        }
      }
    },
    mounted() {
      this.reloadBizs();

      // 加载所有的分词器
      nametool.tokenizers().then(res => {
        this.tokenizers = res.data
        if (this.tokenizers.length > 0) {
          this.input.tokenizer = this.tokenizers[0]
        }
      })
      // 加载所有的英语翻译
      nametool.englishs().then(res => {
        this.englishs = res.data
        if (this.englishs.length > 0) {
          this.input.englishs = [this.englishs[0]]
        }
      })
    },
    methods: {
      queryName() {
        if (this.input.keyword) {
          nametool.translate(this.input.keyword, this.input.tokenizer, this.input.englishs,this.input.selectedBizs).then(res => {
            this.translates = res.data
          })
        }
      },
      reloadBizs(){
        // 加载所有的业务标签
        nametool.bizs().then(res => {
          this.bizs = res.data
        })
      },
      commitBiz(){
        if (!this.dialog.input.biz || !this.dialog.input.content){
          this.$message('输入业务标题和内容');
          return ;
        }
        nametool.writeBizContent(this.dialog.input.biz,this.dialog.input.content).then(res => {
          this.dialog.visible = false;
          this.reloadBizs();
        });
      },
      choseBizs(selection){
        let bizs = selection.map(item => item.biz);
        this.input.selectedBizs = bizs;
        nametool.contentMerge(bizs).then(res => {
          this.contentMerge = res.data.join('\n')
        })
      }
    },
    computed: {
      bizTableData(){
        return this.bizs.map(biz => ({biz:biz}))
      }
    }
  }
</script>

<style scoped>
  @import "../../assets/bootstrap.css";

</style>

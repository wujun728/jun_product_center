<template>
  <div class="app-container">
    <el-row>
      <el-col :span="12">
        <div class="panel panel-primary">
          <div class="panel-heading">随机生成身份证</div>
          <div class="panel-body">
            <el-form ref="form" size="small"  label-width="80px">
              <el-form-item label="出生地">
                <el-cascader
                  v-model="left.place"
                  :options="places"
                  @change="chosePlace"  style="width: 380px" />
              </el-form-item>
              <el-form-item label="出生日期">
                <el-col :span="11">
                  <el-date-picker type="date" placeholder="选择日期" v-model="left.birthday" style="width: 100%;"></el-date-picker>
                </el-col>
              </el-form-item>
              <el-form-item label="性别">
                <el-radio-group v-model="left.gender">
                  <el-radio-button label="1">男</el-radio-button>
                  <el-radio-button label="2">女</el-radio-button>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="生成数量">
                <el-input-number v-model="left.length"></el-input-number>
              </el-form-item>
              <el-form-item>
                <el-button-group>
                  <el-button type="primary" @click="generate" >生成</el-button>
                  <el-button type="warning" @click="left.output = null" >清空</el-button>
                </el-button-group>
              </el-form-item>
            </el-form>

            <el-input
                    type="textarea"
                    :autosize="{ minRows: 5, maxRows: 10}"
                    placeholder="生成好的身份证在这里"
                    v-model="left.output"
            ></el-input>
          </div>
        </div>
      </el-col>
      <el-col :span="11" class="margin-left">
        <div class="panel panel-primary">
          <div class="panel-heading">身份证严格验证</div>
          <div class="panel-body">
            <div class="alert alert-success">此处检验身份证后会在左边生成城市和生日性别信息</div>
            <el-input placeholder="输入身份证" v-model="right.idcard" >
              <el-button slot="append" @click="validate" >检验身份证</el-button>
            </el-input>
          </div>
        </div>
        <div class="panel panel-primary">
          <div class="panel-heading">身份证猜测</div>
          <div class="panel-body">
            <div class="alert alert-success">至少输入前面14位,猜后面数字</div>
            <el-form size="small" label-width="80px">
              <el-form-item label="性别">
                <el-radio-group v-model="guess.gender">
                  <el-radio-button :label="1">男</el-radio-button>
                  <el-radio-button :label="0">女</el-radio-button>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="已知">
                <el-input placeholder="输入身份证前面的位数" v-model="guess.idcard" >
                  <el-button slot="append" @click="guessIdcard" >获取可能的身份证列表</el-button>
                </el-input>
              </el-form-item>
            </el-form>

          </div>
        </div>
        <div class="panel panel-info">
          <div class="panel-heading">身份证最后一位生成</div>
          <div class="panel-body">
            <el-input placeholder="输入身份证" v-model="right.idcard17" >
              <el-button slot="append" @click="lastNum" >获取尾数</el-button>
            </el-input>
            <p class="form-control-static text-info">{{right.idcard17}}<b>{{right.last}}</b></p>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import options from "../../assets/country-data";
  import {idcard,getVerify} from "../../utils/generate";
  import {parseTime} from "../../utils";

  export default {
    name: "idcard",
    data(){
      return {
        places: options[0],
        left: {
          place: [],
          birthday: null,
          gender: null,
          length: 10,
          output: null
        },
        right: {
          idcard: "430124199311163606",
          idcard17: "43012419931116360",
          last: '?'
        },
        guess: {
          idcard: '43012419931116',
          gender: 1
        }
      }
    },
    mounted(){
      this.validate()
    },
    methods: {
      chosePlace(){
        console.log(this.left.place)
      },
      generate(){
        let outputs = [];
        let birthday = parseTime(this.left.birthday.getTime(),'{y}{m}{d}');
        for (let i = 0; i < this.left.length; i++) {
          let currentIdcard = idcard(this.left.place[2],birthday,this.left.gender === 1);
          outputs.push(currentIdcard);
        }
        this.left.output = outputs.join('\n');
      },
      guessIdcard(){
        if (this.guess.idcard){
          if (this.guess.idcard.length < 14 ){
            this.$message("位数不足 14 位");
            return ;
          }
          let input = this.guess.idcard;

          let idcards = [];

          switch (input.length) {
            case 14:
              for (let i = 0;i< 9;i++){
                for (let j = 0;j < 9 ;j++){
                  for (let k = this.guess.gender;k < 9 ;k+= 2){
                    let idCard17 = input+''+i+j+k;
                    let lastNum = getVerify(idCard17);
                    idcards.push(idCard17+lastNum);
                  }
                }
              }
              break;
            case 15:
              for (let j = 0;j < 9 ;j++){
                for (let k = this.guess.gender;k < 9 ;k+= 2){
                  let idCard17 = input+''+j+k;
                  let lastNum = getVerify(idCard17);
                  idcards.push(idCard17+lastNum);
                }
              }
              break;
            case 16:
              for (let k = this.guess.gender;k < 9 ;k+= 2){
                let idCard17 = input+''+k;
                let lastNum = getVerify(idCard17);
                idcards.push(idCard17+lastNum);
              }
              break;
            case 17:
              let lastNum = getVerify(input);
              idcards.push(input+lastNum);
              break;
          }

          this.left.output = idcards.join('\n');
        }

        // this.$message("未实现");
      },
      validate(){
        if (this.right.idcard){
          if (this.right.idcard.length !== 18){
            this.$message("位数不足 18 位");
            return ;
          }
          let input = this.right.idcard;

          let input17 = input.substring(0,17);
          let last = input.substring(17,18);

          if(!/^\d{17}$/.test(input17)){
            this.$message('前 17 位必须是数字');
            return ;
          }

          let realLast = getVerify(input17);
          if(last.toLocaleLowerCase() != realLast){
            this.$message('检验码不匹配 '+realLast+' != '+last);
            return ;
          }

          let province = input.substring(0,2);
          let city = input.substring(0,4);
          let area = input.substring(0,6);
          this.left.place = [province,city,area];

          let year = input.substring(6,10);
          let month = input.substring(10,12);
          let day = input.substring(12,14);
          this.left.birthday = new Date(year,month - 1,day);

          let seriaCode = input.substring(14,17);
          let gender = seriaCode % 2 === 0;
          this.left.gender = gender ? 2 : 1 ;
        }
      },
      lastNum(){
        let input17 = this.right.idcard17;
        if (input17.length !== 17){
          this.$message("位数不足 17 位");
          return ;
        }
        if(!/^\d{17}$/.test(input17)){
          this.$message('前 17 位必须是数字');
          return ;
        }
        this.right.last = getVerify(input17);
      }

    }
  }
</script>

<style scoped>
  @import "../../assets/bootstrap.css";
</style>

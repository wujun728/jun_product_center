<template>
  <div style=" overflow: hidden;">
      <el-row>
        <el-col :span="18" :offset="3">
          <div
            id="dataShow"
            onmouseover="this.style.overflow='overlay'"
            onmouseout="this.style.overflow='hidden'"
            :class="[browser==='Chrome'? 'dataShowCM': 'dataShowFF']"
          >
            <div
              v-for="(item,index) in messageList"
              :key="index"
              style="background-color: #f5f5f5; padding:24px;"
            >
              <el-row v-if="item.from_unifiedUser!=null&&item.from_unifiedUser.id==unifiedUser.id">
                <el-row style="margin-bottom: 5px">
                  <el-col :span="10" :offset="10">
                    <span style="font-size: 16px;color: #f5f5f5;background-color: #dadada;">
                      {{ item.dateline }}
                    </span>
                  </el-col>
                </el-row>
                <el-col :span="20" :offset="3">
                  <span class="fr">
                    {{ item.content }}
                  </span>
                </el-col>
                <el-col :span="1">
                  <span>
                      <svg-icon slot="reference" icon-class="girl" style="font-size: 40px" />{{unifiedUser.username}}<!--对应头像-->
                  </span>
                </el-col>
              </el-row>
              <el-row v-else>
                <el-col :span="1">
                  <span>
                      <svg-icon slot="reference" icon-class="person" style="font-size: 40px" />{{item.from_unifiedUser.username}}
                  </span>
                </el-col>
                <el-col :span="20">
                  <span class="fl">
                    {{ item.content }}
                  </span>
                </el-col>
              </el-row>
            </div>
          </div>

          <div style="background-color: white;">
              <el-row>
                <el-col :span="22" :offset="1">
                  <el-input
                    id="condition"
                    type="textarea"
                    v-model="message.content"
                    autofocus
                    clearable
                    maxlength="100"
                    show-word-limit
                    :autosize="{minRows:3,maxRows:6}"
                    resize="none"
                    placeholder="在这里输入..."
                   />
                </el-col>
              </el-row>
              <el-row style="margin: 4px 4px">
                <el-button
                  id="submit"
                  onmouseover="this.style.backgroundColor='#129611';this.style.color='#f5f5f5'"
                  onmouseout="this.style.backgroundColor='#f5f5f5';this.style.color='#606060'"
                   @click="submitMessage"
                 >
                  <svg-icon icon-class="enter" />发送
                </el-button>
              </el-row>
          </div>

        </el-col>
      </el-row>
  </div>
</template>

<script>
import { setStore,getStore } from '/utils/storage'
import { fetchTalkList,save_talkEntity} from '@/api/message'

export default {
  name: 'userChat',
  components: { },
  data() {
    return {
      unifiedUser: {id:""},
      visible: false,
      message:{
        userId:this.$route.query.id,
        content:"",
        type:"USER",//消息类型
        name:"MESSAGE"//用户消息
      },
      messageList: [],
      listQuery: { //查询分页
        name:"",
        pageNo: 1,
        pageSize: 10
      },
      isShow: true,
      isButton: false,
      browser:'Chrome'
    }
  },
  created() {
    this.getList();
    let user=getStore("unifieduser");
    if(user){
      this.unifiedUser=JSON.parse(user);
    };
    this.browser = this.myBrowser()
  },
  computed:{
      chat() {
          return this.$store.getters.onEvent('chat');
      }
  },
  watch: {
      chat: function (a, b) {
          if (a!=b&&a) {
              this.messageList.push(a);
          }
      }
  },
  methods: {
    scrollToBottom() {
      this.$nextTick(() => {
        const div = document.getElementById('dataShow')
        div.scrollTop = div.scrollHeight
      })
    },
    //列表
    getList() {
       fetchTalkList(this.listQuery).then(response => {
         this.messageList = response.data.datas
       })
    },
    clearBr(key) {
      key = key.replace(/<\/?.+?>/g, '')
      key = key.replace(/[\r\n]/g, '')
      return key
    },
    submitMessage(){
       this.message.content = this.clearBr(this.message.content);
       if(!this.message.content){
        this.$message.error('当前没有输入。。。')
        this.scrollToBottom()
       }
       save_talkEntity(this.message).then((res) => {
         if(res.code=='200'){
            this.$message({
              message: '提交成功',
              type: 'success'
            });
            this.message.content="";
            this.messageList.push(res.data);
          }
        });

    },
    myBrowser() {
      var userAgent = navigator.userAgent // 取得浏览器的userAgent字符串
      var isOpera = userAgent.indexOf('Opera') > -1 // 判断是否Opera浏览器
      var isIE = userAgent.indexOf('compatible') > -1 &&
        userAgent.indexOf('MSIE') > -1 && !isOpera // 判断是否IE浏览器
      var isEdge = userAgent.indexOf('Edge') > -1 // 判断是否IE的Edge浏览器
      var isFF = userAgent.indexOf('Firefox') > -1 // 判断是否Firefox浏览器
      var isSafari = userAgent.indexOf('Safari') > -1 &&
        userAgent.indexOf('Chrome') === -1 // 判断是否Safari浏览器
      var isChrome = userAgent.indexOf('Chrome') > -1 &&
        userAgent.indexOf('Safari') > -1 // 判断Chrome浏览器

      if (isIE) {
        var reIE = new RegExp('MSIE (\\d+\\.\\d+);')
        reIE.test(userAgent)
        var fIEVersion = parseFloat(RegExp['$1'])
        if (fIEVersion === 7) {
          return 'IE7'
        } else if (fIEVersion === 8) {
          return 'IE8'
        } else if (fIEVersion === 9) {
          return 'IE9'
        } else if (fIEVersion === 10) {
          return 'IE10'
        } else if (fIEVersion === 11) {
          return 'IE11'
        } else {
          return '0'
        }// IE版本过低
        // eslint-disable-next-line no-unreachable
        return 'IE'
      }
      if (isOpera) {
        return 'Opera'
      }
      if (isEdge) {
        return 'Edge'
      }
      if (isFF) {
        return 'FF'
      }
      if (isSafari) {
        return 'Safari'
      }
      if (isChrome) {
        return 'Chrome'
      }
    }
  }
}
</script>

<style scoped>
.font{
    font-size: 16px;
    font-weight: bold;
    font-family: "微软雅黑 Light";
  }
/*  <!--非Chrome 隐藏滚动条-->*/
  .dataShowFF{
    height:auto;
    overflow-y: scroll;
    overflow-x: hidden;
    margin-bottom: -14px;
    margin-top: 0px;
    background-color: #f5f5f5;
    scrollbar-width: none;
  }
/*  <!--Chrome 隐藏滚动条-->*/
  .dataShowCM{
    height:auto;
    overflow-y: scroll;
    overflow-x: hidden;
    margin-bottom: -14px;
    margin-top: 0px;
    background-color: #f5f5f5;
  }::-webkit-scrollbar {
     display: none;
   }
  .fr{
    float: right;
    position: relative;
    display: inline-block;
    max-width: calc(40%);
    min-height: 35px;
    line-height: 2.1;
    font-size: 15px;
    padding: 6px 10px;
    text-align: left;
    word-break: break-all;
    background-color: #9eea6a;
    color: #000;
    border-radius: 4px;
    box-shadow: 0px 1px 7px -5px #000;
    border:0px solid #000;
    margin-top: 0;
    margin-right: 10px;
  }
  .fr:after {
    content: "";
    border-top: solid 5px #00800000;
    border-left: solid 10px #9eea6a;
    border-right: solid 10px #00800000;
    border-bottom: solid 5px #00800000;
    position: absolute;
    top: 10px;
    left: 100%;
  }
  .fl{
    display: inline-block;
    line-height:30px;
    font-style: normal;
    background-color: white;
    letter-spacing: 2px;
    position: relative;
    max-width: calc(40%);
    min-height: 35px;
    line-height: 2.1;
    font-size: 15px;
    padding: 6px 10px;
    text-align: left;
    word-break: break-all;
    border-radius: 4px;
    color: #000;
    box-shadow: 0px 1px 7px -5px #000;
    border:0px solid #000;
    margin-top: 0;
    margin-left: 10px;
  }
  .fl:after {
    content: "";
    border-top: solid 5px #00800000;
    border-left: solid 10px #00800000;
    border-right: solid 10px white;
    border-bottom: solid 5px #00800000;
    position: absolute;
    top: 10px;
    right: 100%;

  }
</style>

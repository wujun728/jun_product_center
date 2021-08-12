<template>
  <div class="dashboard-container">
    <p>工具数量: {{plugins.length}}</p>
    <el-row>
      <div v-for="(plugin,key,index) in pluginsWrap "  :key="plugin._hashCode"  class="tool-card">
        <el-card style="height:280px;position: relative;" >
          <img :src="plugin.pluginDto.logo" class="image cursor-point" @click="openPlugin(plugin)"/>
          <div class="text-center caption" @click="openPlugin(plugin)">
            <h4>
              <a style="display: block">{{plugin.pluginDto.module}}:{{plugin.pluginDto.name}}</a>
              <small>{{plugin.pluginDto.author}}</small>
            </h4>
            <el-popover
              placement="right"
              width="200"
              trigger="hover"
              :content="plugin.pluginDto.desc"
              v-if="plugin.pluginDto.desc && plugin.pluginDto.desc !== ''"
            >
              <p slot="reference" style="height: 1.5em;overflow: hidden;white-space: nowrap;text-overflow: ellipsis;font-size: 15px;margin: 0">{{plugin.pluginDto.desc}}</p>
            </el-popover>
          </div>

          <div class="text-center" v-if="plugin.pluginDto.help">
            <el-link class="" type="info" @click="loadPluginDetail(plugin)">插件详情</el-link>
          </div>

          <div class="bottom clearfix" style="font-size: 12px">
            <b class="total-calls">{{plugin.totalCalls}}</b>
            <time class="time">{{plugin.time}}</time>
          </div>
        </el-card>
      </div>
    </el-row>

    <el-dialog  :visible.sync="view.dialog" fullscreen
                :title="pluginDto.module +':'+pluginDto.name+' 插件详情'">
      <mavon-editor :editable="false"
                    :subfield="false"
                    :toolbarsFlag="false"
                    defaultOpen="preview"
                    style="height: 100%;width: 100%;"
                    v-model="pluginDto.helpContent" />
    </el-dialog>
  </div>
</template>

<script>
  import core from '../../api/core'
  import { parseTime } from '@/utils/index.js'
  import { mavonEditor } from 'mavon-editor'
  import 'mavon-editor/dist/css/index.css'
export default {
  name: 'Dashboard',
  components: {mavonEditor},
  data(){
    return {
      view: {
        dialog: false
      },
      pluginDto: {},
      plugins:[]
    }
  },
  computed: {
    pluginsWrap(){
      let res = [];
      for (let i = 0; i < this.plugins.length; i++) {
        let plugin = this.plugins[i];
        plugin.time = parseTime(plugin.lastCallTime);
        let item = plugin.pluginDto;
        let subRoute = item.name.replace(/([A-Z])/g,"/$1").toLowerCase();
        plugin.route = item.module + '/'+subRoute;
        if (item.logo){
          item.logo = require('@/assets/plugin_images/'+item.logo);
        }else {
          item.logo = require('@/assets/plugin_images/null.png');
        }
        res.push(this.plugins[i]);

      }
      return res;
    }
  },
  mounted() {
    core.plugins().then(res => {
      this.plugins = res.data;
    })
  },
  methods:{
    loadPluginDetail(plugin){
      this.view.dialog = true;
      this.pluginDto = plugin.pluginDto;
      core.pluginDetail(plugin.pluginDto.module+':'+plugin.pluginDto.name).then(res => {
        this.pluginDto = res.data;
      });
    },
    openPlugin(plugin){
      this.$router.push(plugin.route)
    }
  }
}
</script>

<style>
  .el-card .el-card__body{
    padding: 5px 10px;
    overflow-y: hidden;
  }
</style>
<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}

.tool-card {
  width: 240px;
  margin-right: 10px;
  margin-bottom: 10px;
  display: inline-block;
}

.caption p {
  font-size: 14px;
}

.image {
  margin: 0 auto;
  display: block;
  width: 100%;

  height: 140px;
}

.bottom > .total-calls {
  position: absolute;
  left: 5px;
  bottom: 2px;
}

.bottom > .time {
  position: absolute;
  right: 5px;
  bottom: 2px;
}
</style>

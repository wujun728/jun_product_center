<template>
  <div class="app-container">
    <el-row>
      <el-col>
        <el-button class="margin-right" type="text" icon="el-icon-refresh" @click="reloadAllConnects"/>

        <el-select v-model="input.connect" @change="refreshZookeeperTree" size="small">
          <el-option v-for="connect in connects" :key="connect" :value="connect" :label="connect" />
        </el-select>
        <el-button size="small" plain @click="addFavorite">添加收藏</el-button>
        <span>
          <el-tag type="info" closable
                  v-for="favorite in favorites" :key="favorite.name"
                  class="cursor-point margin-left" @click="openPath(favorite)"
                  @close="deleteFavorite(favorite)">{{favorite.name}}</el-tag>
        </span>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="8">
        <el-tree ref="nodeTree"
                 lazy
                 :load="loadNode"
                 node-key="xpath"
                 :props="view.treeProps"
                 :highlight-current="true"
                 :show-checkbox="false"
                 :expand-on-click-node="false"
                 :default-expanded-keys="view.treeConfig.expandedKeys"
                 @node-click="clickTreeNode"
        ></el-tree>
      </el-col>
      <el-col :span="16">
        <p>xpath: {{unescape(input.xpath)}}</p>
        <el-tabs  v-model="view.activeTabName" @tab-click="switchTab">
          <el-tab-pane label="节点数据" name="first">
           {{nodeData}}
          </el-tab-pane>
          <el-tab-pane label="节点属性" name="second">
            <el-table
                    :data="meta"
                    border
                    stripe
                    style="width: 100%"
                    size="mini">
              <el-table-column
                      prop="key"
                      label="属性名"
                      width="180">
              </el-table-column>
              <el-table-column
                      prop="value"
                      label="属性值"
                      width="180">
              </el-table-column>
              <el-table-column
                      prop="remark"
                      label="备注">
              </el-table-column>
            </el-table>
          </el-tab-pane>
          <el-tab-pane label="节点 ACL" name="third">
            <el-table
                    :data="acls"
                    border
                    stripe
                    style="width: 100%"
                    size="mini">
              <el-table-column
                      prop="schema"
                      label="schema"
                      width="60">
              </el-table-column>
              <el-table-column
                      prop="id"
                      label="id"
                      width="80">
              </el-table-column>
              <el-table-column
                      prop="perms"
                      label="perms"
                      width="40">
              </el-table-column>
              <el-table-column
                      prop="permsString"
                      label="权限列表">
              </el-table-column>
            </el-table>
          </el-tab-pane>
        </el-tabs>
      </el-col>
    </el-row>
  </div>
</template>

<script>
  import zookeeper from '../../api/zookeeper'
  import core from '../../api/core'

  let sleep = function(time) {
    var startTime = new Date().getTime() + parseInt(time, 10);
    while(new Date().getTime() < startTime) {}
  };

  export default {
    name: 'zookeeper',
    data(){
      return {
        input: {
          connect: null,
          xpath: null
        },
        view: {
          treeProps:{
            children: 'children',
            label: 'label'
          },
          treeConfig: {
            expandedKeys: []
          },
          activeTabName: 'second',
        },
        connects:[],
        meta: [],
        acls: [],
        nodeData: null,
        favorites: []
      }
    },
    mounted() {
      this.reloadAllConnects();
    },
    methods: {
      reloadAllConnects(){
        core.moduleConnects('zookeeper').then(res => {
          this.connects = res.data;
          if (this.connects && this.connects.length > 0){
            this.refreshZookeeperTree(this.connects[0]);
          }
        });
      },
      unescape(value){
        return window.unescape(value);
      },
      deleteFavorite(tag){
        zookeeper.removeFavorite(this.input.connect,tag.name).then(res => {
          let index = this.favorites.findIndex(item => item.name === tag.name)
          this.favorites.splice(index,1);
        })
      },
      addFavorite(){
        let nodeData = this.$refs.nodeTree.getCurrentNode();
        if (nodeData === null){
          this.$message({
            type: 'error',
            message: '添加收藏失败,没有选中节点'
          });

          return ;
        }
        let currentNode = this.$refs.nodeTree.getNode(nodeData);
        this.$prompt('取一个好记的名称', '提示', {
          confirmButtonText: '确定'
        }).then(({ value }) => {
          zookeeper.addFavorite(this.input.connect,value,currentNode.data.xpath).then(res => {
            this.$message('添加收藏成功');
            this.favorites.push({name:value,path:currentNode.data.xpath})
          })
        }).catch(res => {});
      },
      switchTab(tab,event){

      },
      clickTreeNode(data,node,el){
        this.input.xpath = data.xpath;

        // 节点数据
        zookeeper.readData(this.input.connect,data.xpath).then(res => {
          this.nodeData = res.data;
        });

        // 节点  acl
        zookeeper.acls(this.input.connect,data.xpath).then(res => {
          let acls = res.data;
          this.acls.length = 0;
          for (let i = 0; i < acls.length; i++) {
            let acl = acls[i];
            acl.permsString = acl.permsParser.join('\t');
            this.acls.push(acl);
          }
        });
        // 节点 meta
        zookeeper.meta(this.input.connect,data.xpath).then(res => {
         let stat = res.data;
          this.meta.length = 0 ;

          for (let key in stat) {
            let currAttr = { key: key, value: stat[key]};
            switch (key) {
              case 'version':
                currAttr['remark'] = '数据版本';
                break;
              case 'cversion':
                currAttr['remark'] = '子节点版本';
                break;
              case 'aversion':
                currAttr['remark'] = 'ACL 版本';
                break;
              case 'dataLength':
                currAttr['remark'] = '数据长度';
                break;
              case 'ctime':
                currAttr['remark'] = '节点创建时间';
                // currAttr['value'] = stat[key] + '(' + util.FormatUtil.dateFormat(stat[key], 'yyyy-MM-dd HH:mm:ss') + ')';
                break;
              case 'mtime':
                currAttr['remark'] = '节点最后一次被修改的时间';
                // currAttr['value'] = stat[key] + '(' + util.FormatUtil.dateFormat(stat[key], 'yyyy-MM-dd HH:mm:ss') + ')';
                break;
              case 'numChildren':
                currAttr['remark'] = '子节点个数';
                break;
              case 'emphemeralOwner':
                currAttr['remark'] = '节点拥有者会话ID';
                break;
            }
            this.meta.push(currAttr)
          }
        })
      },
      openPath(favorite){
        let paths = favorite.path.split('/');
        if (paths.length < 3){
          return ;
        }

        for (let i = 1; i < paths.length; i++) {
          let xpath = '';
          for (let j = 0; j <= i; j++) {
            xpath += ('/'+paths[j]);
          }
          xpath = xpath.substring(1);
          this.view.treeConfig.expandedKeys.push(xpath)
          setTimeout(res => {
            this.$refs.nodeTree.setCurrentKey(xpath);
            let currentNode = this.$refs.nodeTree.getNode(xpath);
            this.clickTreeNode(currentNode.data,currentNode,null)
          },500)
        }
      },
      refreshZookeeperTree(value){
        this.input.connect = value;
        let root = this.$refs.nodeTree.getNode('/');
        root.loaded = false;
        root.expand();

        // 加载当前连接所有收藏的路径
        zookeeper.favorites(this.input.connect).then(res => {
          this.favorites = res.data;
        })
      },
      loadNode(node,resolve){
        if (node.level === 0){
          let context = [{name:'/',label:'/',xpath:'/'}]
          return resolve(context);
        }
        let xpath = node.data.xpath;
        zookeeper.childrens(this.input.connect,xpath).then(res => {
          if (xpath === '/'){xpath = '';}
          let children = res.data.map(text => {
            // console.log(decodeURIComponent(text))
            // text = decodeURIComponent(text)
            return {name:text,label:unescape(text),xpath: xpath+'/'+text};
          })
          return resolve(children);
        })
      }
    }
  }
</script>

<style scoped>

</style>

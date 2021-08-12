<template>
    <div>
        <!-- 图片上传组件辅助-->
      <el-upload
        class="avatar-uploader"
        :action="serverUrl"
        name="file"
        :headers="header"
        :show-file-list="false"
        :on-success="uploadSuccess"
        :on-error="uploadError"
        :before-upload="beforeUpload">
      </el-upload>

        <quill-editor
        class="editor"
        v-model="content"
        ref="myQuillEditor"
        :options="editorOption"
        @blur="onEditorBlur($event)" @focus="onEditorFocus($event)"
        @change="onEditorChange($event)" style="width:100%">
        </quill-editor>
    </div>

</template>
<script>
// 工具栏配置
const toolbarOptions = [
  ["bold", "italic", "underline", "strike"], // 加粗 斜体 下划线 删除线
  ["blockquote", "code-block"], // 引用  代码块
  [{ header: 1 }, { header: 2 }], // 1、2 级标题
  [{ list: "ordered" }, { list: "bullet" }], // 有序、无序列表
  [{ script: "sub" }, { script: "super" }], // 上标/下标
  [{ indent: "-1" }, { indent: "+1" }], // 缩进
  // [{'direction': 'rtl'}],                         // 文本方向
  [{ size: ["small", false, "large", "huge"] }], // 字体大小
  [{ header: [1, 2, 3, 4, 5, 6, false] }], // 标题
  [{ color: [] }, { background: [] }], // 字体颜色、字体背景颜色
  [{ font: [] }], // 字体种类
  [{ align: [] }], // 对齐方式
  ["clean"], // 清除文本格式
  //["link", "image","video"] // 链接、图片、视频
  ["link", "image"], // 链接、图片
  ['sourceEditor']
];
import Quill from 'quill'
import { quillEditor } from "vue-quill-editor";
 // 新增下面代码：
import resizeImage from 'quill-image-resize-module' // 调整大小组件。
Quill.register('modules/imageResize', resizeImage)
// quillEditor.register('modules/imageDrop', ImageDrop)
import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";

export default {
  props: {
    /*编辑器的内容*/
    value: {
      type: String
    },
    /*图片大小*/
    maxSize: {
      type: Number,
      default: 4000 //kb
    }
  },
  components: {
    quillEditor
  },
  data() {
    return {
      content: this.value,
      quillUpdateImg: false, // 根据图片上传状态来确定是否显示loading动画，刚开始是false,不显示
      editorOption: {
        placeholder: "",
        theme: "snow", // or 'bubble'
        placeholder: "您想说点什么？",
        modules: {
          toolbar: {
            container: toolbarOptions,
            // container: "#toolbar",
            handlers: {
              image: function(value) {
                if (value) {
                  // 触发input框选择图片文件
                  document.querySelector(".avatar-uploader input").click();
                } else {
                  this.quill.format("image", false);
                }
              },
              // link: function(value) {
              //   if (value) {
              //     var href = prompt('请输入url');
              //     this.quill.format("link", href);
              //   } else {
              //     this.quill.format("link", false);
              //   }
              // },
              // 添加工具方法
              shadeBox: null,
              sourceEditor: function () {
                  const container = this.container;
                  const firstChild = container.nextElementSibling.firstChild;
                  // 在第一次点击源码编辑的时候，会在整个工具条上加一个div，层级比工具条高，再次点击工具条任意位置，就会退出源码编辑。可以在下面cssText里面加个背景颜色看看效果。
                  if (!this.shadeBox) {
                      let shadeBox = this.shadeBox = document.createElement('div');

                      shadeBox.style.cssText = 'position:absolute; top:0; left:0; width:100%; height:100%; cursor:pointer';
                      container.style.position = 'relative';
                      container.appendChild(shadeBox);
                      firstChild.innerText = firstChild.innerHTML;

                      shadeBox.addEventListener('click', function () {
                          this.style.display = 'none';
                          firstChild.innerHTML = firstChild.innerText.trim();
                      }, false);
                  } else {
                      this.shadeBox.style.display = 'block';
                      firstChild.innerText = firstChild.innerHTML;
                  }
              }
            }
          },
          imageResize: { //调整大小组件。
             displayStyles: {
                 backgroundColor: 'black',
                 border: 'none',
                 color: 'white'
             },
             modules: [ 'Resize', 'DisplaySize', 'Toolbar' ]
         }
        },
        // 在使用的页面中初始化按钮样式
        initButton: function () {
            // 样式随便改
            const sourceEditorButton = document.querySelector('.ql-sourceEditor');
              sourceEditorButton.style.cssText = "width:80px; border:1px solid #ccc; border-radius:5px;";
            // 加了elementui的icon
            sourceEditorButton.classList.add('el-icon-edit');
            // 鼠标放上去显示的提示文字
            sourceEditorButton.innerText = '源码编辑';
         },
        register(q){
          //注册标签(因为在富文本编辑器中是没有div,table等标签的，需要自己去注册自己需要的标签)
          class div extends q.import('blots/block/embed') {}
          class table extends q.import('blots/block/embed') {}
          class tr extends q.import('blots/block/embed') {}
          class td extends q.import('blots/block/embed') {}
          div.blotName =div.tagName='div';
          table.blotName =table.tagName='table';
          tr.blotName =tr.tagName='tr';
          td.blotName =td.tagName='td';
          q.register(div);
          q.register(table);
          q.register(tr);
          q.register(td);
        }
      },
      serverUrl: "/api/file/image_upload", // 这里写你要上传的图片服务器地址
      header: {
        // token: sessionStorage.token
      } // 有的图片服务器要求请求头需要有token
    };
  },

  methods: {
    onEditorBlur() {
      //失去焦点事件
    },
    onEditorFocus() {
      //获得焦点事件
    },
    onEditorChange() {
      //内容改变事件
      this.$emit("input", this.content);
    },

    // 富文本图片上传前
    beforeUpload(file) {
      const is10M = file.size / 1024 / 1024 < 10; // 限制小于10M
      if (!is10M) {
        this.$message.error('大小不可超过10M');
        return false;
      }
      // 显示loading动画
      this.quillUpdateImg = true;
    },
    uploadSuccess(res, file) {
      console.log(res);
      console.log(file)
      // res为图片服务器返回的数据
      // 获取富文本组件实例
      let quill = this.$refs.myQuillEditor.quill;
      // 如果上传成功
      if (res.code == 200) {
         // 获取光标所在位置
        let length = quill.getSelection().index;
        // 插入图片  res.url为服务器返回的图片地址
        quill.insertEmbed(length, "image", res.data);
        // 调整光标到最后
        quill.setSelection(length + 1);
      } else {
        this.$message.error("图片插入失败");
      }
      // loading动画消失
      this.quillUpdateImg = false;
    },
    // 富文本图片上传失败
    uploadError() {
      // loading动画消失
      this.quillUpdateImg = false;
      this.$message.error("图片插入失败");
    }
  },
  watch: {
    value: function() {
      this.content = this.value;
    }
  },
  mounted: function() {
    this.content = this.value;
  },
};
</script>

<style>
.editor {
  line-height: normal !important;
  width: 930px;
  margin: 0 auto;
}
.ql-container {
  height: 400px !important;
}
.ql-snow .ql-tooltip[data-mode=link]::before {
  content: "请输入链接地址:";
}
.ql-snow .ql-tooltip.ql-editing a.ql-action::after {
    border-right: 0px;
    content: '保存';
    padding-right: 0px;
}

.ql-snow .ql-tooltip[data-mode=video]::before {
    content: "请输入视频地址:";
}

.ql-snow .ql-picker.ql-size .ql-picker-label::before,
.ql-snow .ql-picker.ql-size .ql-picker-item::before {
  content: '14px';
}
.ql-snow .ql-picker.ql-size .ql-picker-label[data-value=small]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value=small]::before {
  content: '10px';
}
.ql-snow .ql-picker.ql-size .ql-picker-label[data-value=large]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value=large]::before {
  content: '18px';
}
.ql-snow .ql-picker.ql-size .ql-picker-label[data-value=huge]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value=huge]::before {
  content: '32px';
}

.ql-snow .ql-picker.ql-header .ql-picker-label::before,
.ql-snow .ql-picker.ql-header .ql-picker-item::before {
  content: '文本';
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="1"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="1"]::before {
  content: '标题1';
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="2"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="2"]::before {
  content: '标题2';
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="3"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="3"]::before {
  content: '标题3';
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="4"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="4"]::before {
  content: '标题4';
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="5"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="5"]::before {
  content: '标题5';
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="6"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="6"]::before {
  content: '标题6';
}

.ql-snow .ql-picker.ql-font .ql-picker-label::before,
.ql-snow .ql-picker.ql-font .ql-picker-item::before {
  content: '标准字体';
}
.ql-snow .ql-picker.ql-font .ql-picker-label[data-value=serif]::before,
.ql-snow .ql-picker.ql-font .ql-picker-item[data-value=serif]::before {
  content: '衬线字体';
}
.ql-snow .ql-picker.ql-font .ql-picker-label[data-value=monospace]::before,
.ql-snow .ql-picker.ql-font .ql-picker-item[data-value=monospace]::before {
  content: '等宽字体';
}
</style>

<script>
import draggable from "vuedraggable";
import render from "@/components/render/render";

const components = {
  itemBtns(h, currentItem, index, list) {
    const { copyItem, deleteItem } = this.$listeners;
    return [
      <span
        class="drawing-item-copy"
        title="复制"
        onClick={(event) => {
          copyItem(currentItem, list);
          event.stopPropagation();
        }}
      >
        <i class="el-icon-copy-document" />
      </span>,
      <span
        class="drawing-item-delete"
        title="删除"
        onClick={(event) => {
          deleteItem(index, list);
          event.stopPropagation();
        }}
      >
        <i class="el-icon-delete" />
      </span>,
    ];
  },
};
const layouts = {
  colFormItem(h, currentItem, index, list) {
    const { activeItem } = this.$listeners;
    const config = currentItem.__config__;
    const child = renderChildren.apply(this, arguments);
    let className = this.activeId === config.formId ? "drawing-item active-from-item" : "drawing-item";
    if (this.formConf.unFocusedComponentBorder) className += " unfocus-bordered";
    let labelWidth = config.labelWidth ? `${config.labelWidth}px` : null;
    if (config.showLabel === false) labelWidth = "0";
    if (currentItem.tooltip !== undefined && currentItem.tooltip !== "") {
      let tooltipStyle = " white-space: normal !important; word-break: break-word; max-width: 500px;line-height: 24px; ";
      return (
        <el-col
          span={config.span}
          class={className}
          nativeOnClick={(event) => {
            activeItem(currentItem);
            event.stopPropagation();
          }}
        >
          <el-form-item label-width={labelWidth} required={config.required}>
            <span slot="label">
              {config.label}
              <el-tooltip placement="bottom">
                <div slot="content" style={tooltipStyle}>
                  {currentItem.tooltip
                    .replace(/(\\r\\n|\\n|\\r)/g, "\n") // 处理所有常见转义形式
                    .split("\n")
                    .filter((line) => line.trim())
                    .map((line, index) => (
                      <div key={index}>{line}</div>
                    ))}
                </div>
                <i class="el-icon-info" style="margin-left:2px;color:#409EFF"></i>
              </el-tooltip>
            </span>
            <render
              key={config.renderKey}
              conf={currentItem}
              onInput={(event) => {
                this.$set(config, "defaultValue", event);
              }}
            >
              {child}
            </render>
          </el-form-item>
          {components.itemBtns.apply(this, arguments)}
        </el-col>
      );
    } else {
      return (
        <el-col
          span={config.span}
          class={className}
          nativeOnClick={(event) => {
            activeItem(currentItem);
            event.stopPropagation();
          }}
        >
          <el-form-item label-width={labelWidth} label={config.showLabel ? config.label : ""} required={config.required}>
            <render
              key={config.renderKey}
              conf={currentItem}
              onInput={(event) => {
                this.$set(config, "defaultValue", event);
              }}
            >
              {child}
            </render>
          </el-form-item>
          {components.itemBtns.apply(this, arguments)}
        </el-col>
      );
    }
  },
  rowFormItem(h, currentItem, index, list) {
    const { activeItem } = this.$listeners;
    const config = currentItem.__config__;
    const className = this.activeId === config.formId ? "drawing-row-item active-from-item" : "drawing-row-item";
    let child = renderChildren.apply(this, arguments);
    if (currentItem.type === "flex") {
      child = (
        <el-row type={currentItem.type} justify={currentItem.justify} align={currentItem.align}>
          {child}
        </el-row>
      );
    }
    return (
      <el-col span={config.span}>
        <el-row
          gutter={config.gutter}
          class={className}
          nativeOnClick={(event) => {
            activeItem(currentItem);
            event.stopPropagation();
          }}
        >
          <span class="component-name">{config.componentName}</span>
          <draggable list={config.children || []} animation={340} group="componentsGroup" class="drag-wrapper">
            {child}
          </draggable>
          {components.itemBtns.apply(this, arguments)}
        </el-row>
      </el-col>
    );
  },
  raw(h, currentItem, index, list) {
    const config = currentItem.__config__;
    const child = renderChildren.apply(this, arguments);
    return (
      <render
        key={config.renderKey}
        conf={currentItem}
        onInput={(event) => {
          this.$set(config, "defaultValue", event);
        }}
      >
        {child}
      </render>
    );
  },
};

function renderChildren(h, currentItem, index, list) {
  const config = currentItem.__config__;
  if (!Array.isArray(config.children)) return null;
  return config.children.map((el, i) => {
    const layout = layouts[el.__config__.layout];
    if (layout) {
      return layout.call(this, h, el, i, config.children);
    }
    return layoutIsNotFound.call(this);
  });
}

function layoutIsNotFound() {
  throw new Error(`没有与${this.currentItem.__config__.layout}匹配的layout`);
}

export default {
  components: {
    render,
    draggable,
  },
  props: ["currentItem", "index", "drawingList", "activeId", "formConf"],
  render(h) {
    const layout = layouts[this.currentItem.__config__.layout];

    if (layout) {
      return layout.call(this, h, this.currentItem, this.index, this.drawingList);
    }
    return layoutIsNotFound.call(this);
  },
};
</script>

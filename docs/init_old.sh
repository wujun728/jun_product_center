#!/bin/bash
echo "=== my-project 环境检查 ==="

# 检查工作目录
echo "[1/6] 检查工作目录..."
if [ ! -d "jun_ui_sa_admin" ]; then
  echo "  ❌ 新前端目录 jun_ui_sa_admin 不存在"
  exit 1
fi
if [ ! -d "ruoyi-vue-oa-ui" ]; then
  echo "  ❌ 原前端目录 ruoyi-vue-oa-ui 不存在"
  exit 1
fi
if [ ! -d "ruoyi-vue-oa" ]; then
  echo "  ❌ 后端目录 ruoyi-vue-oa 不存在"
  exit 1
fi
echo "  ✅ 三个项目目录均存在"

# 检查新前端核心文件
echo "[2/6] 检查新前端核心文件..."
MISSING=0
for f in "jun_ui_sa_admin/index.html" "jun_ui_sa_admin/sa-frame/index/index.js" "jun_ui_sa_admin/sa-frame/sa-code.js" "jun_ui_sa_admin/static/sa.js"; do
  if [ ! -f "$f" ]; then
    echo "  ❌ 缺失: $f"
    MISSING=1
  fi
done
if [ $MISSING -eq 0 ]; then
  echo "  ✅ 新前端核心文件完整"
fi

# 检查原前端核心文件
echo "[3/6] 检查原前端核心文件..."
if [ ! -d "ruoyi-vue-oa-ui/src/views" ]; then
  echo "  ❌ 原前端 views 目录不存在"
else
  VIEW_COUNT=$(find ruoyi-vue-oa-ui/src/views -name "*.vue" | wc -l)
  echo "  ✅ 原前端共 $VIEW_COUNT 个 .vue 文件"
fi
if [ ! -d "ruoyi-vue-oa-ui/src/api" ]; then
  echo "  ❌ 原前端 api 目录不存在"
else
  API_COUNT=$(find ruoyi-vue-oa-ui/src/api -name "*.js" | wc -l)
  echo "  ✅ 原前端共 $API_COUNT 个 API 文件"
fi

# 检查后端
echo "[4/6] 检查后端项目..."
if [ -f "ruoyi-vue-oa/pom.xml" ]; then
  echo "  ✅ 后端 pom.xml 存在"
else
  echo "  ❌ 后端 pom.xml 不存在"
fi

# 检查开发工具
echo "[5/6] 检查开发工具..."
for cmd in java mvn node npm git; do
  if command -v $cmd &>/dev/null; then
    echo "  ✅ $cmd: $(command -v $cmd)"
  else
    echo "  ⚠️  $cmd 未安装"
  fi
done

# 统计新前端现有页面
echo "[6/6] 新前端页面统计..."
NEW_VUE=$(find jun_ui_sa_admin/sa-view -name "*.vue" 2>/dev/null | wc -l)
NEW_HTML=$(find jun_ui_sa_admin/sa-view -name "*.html" 2>/dev/null | wc -l)
echo "  新前端: $NEW_VUE 个 .vue 文件, $NEW_HTML 个 .html 文件"

echo ""
echo "=== 检查完成 ==="
echo "任务清单: task_list.json ($(grep -c '"id"' task_list.json) 个任务)"
echo "进度文件: claude-progress.txt"

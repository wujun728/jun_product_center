#!/bin/bash

# jun-oa-frontend-upgrade 环境检查和初始化脚本
# 用途：一键检查前端升级环境，安装依赖，执行基础验证

set -e  # 遇到错误立即退出

echo "========================================"
echo "  jun-oa-frontend-upgrade 环境检查"
echo "========================================"
echo ""

# 颜色定义
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 工作目录
WORK_DIR="$(cd "$(dirname "$0")" && pwd)"
OLD_FRONTEND_DIR="$WORK_DIR/ruoyi-vue-oa-ui"
NEW_FRONTEND_DIR="$WORK_DIR/yudao-ui-admin-vue3"
BACKEND_DIR="$WORK_DIR/ruoyi-vue-oa"

echo "工作目录: $WORK_DIR"
echo ""

# 1. 检查Node.js版本
echo ">>> [1/8] 检查 Node.js 版本..."
if command -v node >/dev/null 2>&1; then
    NODE_VERSION=$(node -v | cut -d'v' -f2 | cut -d'.' -f1)
    echo -e "${GREEN}✓${NC} Node.js 版本: $(node -v)"
    if [ "$NODE_VERSION" -lt 16 ]; then
        echo -e "${RED}✗ 错误: Node.js 版本需要 >= 16, 当前版本: $(node -v)${NC}"
        exit 1
    fi
else
    echo -e "${RED}✗ 错误: 未安装 Node.js${NC}"
    exit 1
fi

# 2. 检查npm和pnpm
echo ""
echo ">>> [2/8] 检查 npm 和 pnpm..."
if command -v npm >/dev/null 2>&1; then
    echo -e "${GREEN}✓${NC} npm 版本: $(npm -v)"
else
    echo -e "${RED}✗ 错误: 未安装 npm${NC}"
    exit 1
fi

if command -v pnpm >/dev/null 2>&1; then
    echo -e "${GREEN}✓${NC} pnpm 版本: $(pnpm -v)"
else
    echo -e "${YELLOW}⚠ 警告: 未安装 pnpm，正在安装...${NC}"
    npm install -g pnpm
    echo -e "${GREEN}✓${NC} pnpm 安装完成: $(pnpm -v)"
fi

# 3. 检查Java和Maven（后端依赖）
echo ""
echo ">>> [3/8] 检查 Java 和 Maven（后端依赖）..."
if command -v java >/dev/null 2>&1; then
    echo -e "${GREEN}✓${NC} Java 版本: $(java -version 2>&1 | head -n 1)"
else
    echo -e "${YELLOW}⚠ 警告: 未安装 Java（后端需要）${NC}"
fi

if command -v mvn >/dev/null 2>&1; then
    echo -e "${GREEN}✓${NC} Maven 版本: $(mvn -v | head -n 1)"
else
    echo -e "${YELLOW}⚠ 警告: 未安装 Maven（后端需要）${NC}"
fi

# 4. 检查目录结构
echo ""
echo ">>> [4/8] 检查项目目录结构..."
if [ -d "$OLD_FRONTEND_DIR" ]; then
    echo -e "${GREEN}✓${NC} 原前端目录存在: $OLD_FRONTEND_DIR"
    OLD_VUE_COUNT=$(find "$OLD_FRONTEND_DIR/src/views" -name "*.vue" 2>/dev/null | wc -l)
    echo "   原前端Vue文件数量: $OLD_VUE_COUNT"
else
    echo -e "${RED}✗ 错误: 原前端目录不存在: $OLD_FRONTEND_DIR${NC}"
    exit 1
fi

if [ -d "$NEW_FRONTEND_DIR" ]; then
    echo -e "${GREEN}✓${NC} 新前端目录存在: $NEW_FRONTEND_DIR"
    NEW_VUE_COUNT=$(find "$NEW_FRONTEND_DIR/src/views" -name "*.vue" 2>/dev/null | wc -l)
    echo "   新前端Vue文件数量: $NEW_VUE_COUNT"
else
    echo -e "${RED}✗ 错误: 新前端目录不存在: $NEW_FRONTEND_DIR${NC}"
    exit 1
fi

if [ -d "$BACKEND_DIR" ]; then
    echo -e "${GREEN}✓${NC} 后端目录存在: $BACKEND_DIR"
else
    echo -e "${YELLOW}⚠ 警告: 后端目录不存在: $BACKEND_DIR${NC}"
fi

# 5. 安装原前端依赖
echo ""
echo ">>> [5/8] 安装原前端依赖（ruoyi-vue-oa-ui）..."
cd "$OLD_FRONTEND_DIR"
if [ -f "package.json" ]; then
    if [ ! -d "node_modules" ]; then
        echo "   正在安装依赖..."
        npm install --legacy-peer-deps || npm install --force
        echo -e "${GREEN}✓${NC} 原前端依赖安装完成"
    else
        echo -e "${GREEN}✓${NC} 原前端依赖已存在，跳过安装"
    fi
else
    echo -e "${RED}✗ 错误: package.json 不存在${NC}"
    exit 1
fi

# 6. 安装新前端依赖
echo ""
echo ">>> [6/8] 安装新前端依赖（yudao-ui-admin-vue3）..."
cd "$NEW_FRONTEND_DIR"
if [ -f "package.json" ]; then
    if [ ! -d "node_modules" ]; then
        echo "   正在安装依赖..."
        pnpm install
        echo -e "${GREEN}✓${NC} 新前端依赖安装完成"
    else
        echo -e "${GREEN}✓${NC} 新前端依赖已存在，跳过安装"
    fi
else
    echo -e "${RED}✗ 错误: package.json 不存在${NC}"
    exit 1
fi

# 7. 检查后端编译（可选）
echo ""
echo ">>> [7/8] 检查后端编译（可选）..."
if [ -d "$BACKEND_DIR" ] && [ -f "$BACKEND_DIR/pom.xml" ]; then
    cd "$BACKEND_DIR"
    echo "   尝试编译后端..."
    if mvn clean compile -DskipTests -q 2>&1 | tail -n 5; then
        echo -e "${GREEN}✓${NC} 后端编译成功"
    else
        echo -e "${YELLOW}⚠ 警告: 后端编译失败或跳过${NC}"
    fi
else
    echo -e "${YELLOW}⚠ 跳过后端编译检查${NC}"
fi

# 8. 生成环境报告
echo ""
echo ">>> [8/8] 生成环境报告..."
cd "$WORK_DIR"
cat > env-report.md <<EOF
# jun-oa-frontend-upgrade 环境检查报告

**检查时间:** $(date '+%Y-%m-%d %H:%M:%S')

## 环境信息
- Node.js: $(node -v)
- npm: $(npm -v)
- pnpm: $(pnpm -v)
- Java: $(java -version 2>&1 | head -n 1 || echo "未安装")
- Maven: $(mvn -v 2>&1 | head -n 1 || echo "未安装")

## 项目结构
- 原前端: $OLD_FRONTEND_DIR
  - Vue文件数量: $OLD_VUE_COUNT
  - package.json: $([ -f "$OLD_FRONTEND_DIR/package.json" ] && echo "存在" || echo "不存在")
  - node_modules: $([ -d "$OLD_FRONTEND_DIR/node_modules" ] && echo "已安装" || echo "未安装")

- 新前端: $NEW_FRONTEND_DIR
  - Vue文件数量: $NEW_VUE_COUNT
  - package.json: $([ -f "$NEW_FRONTEND_DIR/package.json" ] && echo "存在" || echo "不存在")
  - node_modules: $([ -d "$NEW_FRONTEND_DIR/node_modules" ] && echo "已安装" || echo "未安装")

- 后端: $BACKEND_DIR
  - pom.xml: $([ -f "$BACKEND_DIR/pom.xml" ] && echo "存在" || echo "不存在")

## 下一步
1. 查看 task_list.json 了解任务清单
2. 启动原前端: cd ruoyi-vue-oa-ui && npm run dev
3. 启动新前端: cd yudao-ui-admin-vue3 && pnpm dev
4. 启动后端: cd ruoyi-vue-oa && mvn spring-boot:run
EOF

echo -e "${GREEN}✓${NC} 环境报告已生成: env-report.md"

# 完成
echo ""
echo "========================================"
echo -e "${GREEN}✓ 环境检查完成！${NC}"
echo "========================================"
echo ""
echo "后续步骤:"
echo "  1. 查看任务清单: cat task_list.json | jq '.meta'"
echo "  2. 查看环境报告: cat env-report.md"
echo "  3. 启动原前端: cd ruoyi-vue-oa-ui && npm run dev"
echo "  4. 启动新前端: cd yudao-ui-admin-vue3 && pnpm dev"
echo "  5. 启动后端: cd ruoyi-vue-oa && mvn spring-boot:run"
echo ""

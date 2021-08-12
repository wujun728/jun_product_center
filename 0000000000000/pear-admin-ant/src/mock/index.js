import { isNotProduction } from "@/tools/common";

// const useMock = isNotProduction()

const useMock = true // 后端暂无接口使，一直使用mock(保证线上预览能正常使用)

if (useMock) {
  const Mock = require('mockjs2')
  require('./services/user')

  Mock.setup({
    timeout: 1000
  })
}


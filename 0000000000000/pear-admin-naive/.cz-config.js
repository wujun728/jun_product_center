/**
 *
 * author: Jobin
 * Email: 425605679@qq.com
 * Desc:
 * version: 1.0.0
 */
'use strict'

module.exports = {
  types: [
    {
      value: 'WIP',
      name: '💪  WIP: 暂存工作。'
    },
    {
      value: 'feat',
      name: '✨  feat: 新增功能。'
    },
    {
      value: 'fix',
      name: '🐞  fix: 修复 bug。'
    },
    {
      value: 'refactor',
      name: '🛠  refactor: 重构、优化代码，不是新功能或是修復 bug。'
    },
    {
      value: 'perf',
      name: 'perf: 性能优化'
    },
    {
      value: 'build',
      name: 'build: 构建流程、外部依赖变更，比如升级 npm 包、修改 webpack 配置'
    },
    { value: 'ci', name: 'ci: 修改了 CI 配置、脚本' },
    {
      value: 'docs',
      name: '📚  docs: 修改文件。'
    },
    {
      value: 'test',
      name: '🏁  test: 新增或修改改有的测试'
    },
    {
      value: 'chore',
      name: '🗯  chore: 修改构建流程、包管理、构建过程或辅助工具的变动。不包含修改测试文件、src 里的文件 。'
    },
    {
      value: 'style',
      name: '💅  style: 修改代码格式，不会对项目有任何的功能变动 (空格、格式化、分号...等)。'
    },
    {
      value: 'revert',
      name: '⏪  revert: 撤销、恢复一次 git commit。'
    }
  ],

  scopes: [
    ['components', '组件相关'],
    ['composable', 'composable相关'],
    ['utils', 'utils 相关'],
    ['naive', '对 naive ui 的调整'],
    ['styles', '样式相关'],
    ['deps', '项目依赖'],
    ['auth', '对 auth 修改'],
    ['other', '其他修改'],
    // 如果选择 custom ,后面会让你再输入一个自定义的 scope , 也可以不设置此项， 把后面的 allowCustomScopes 设置为 true
    ['custom', '自定义']
  ].map(([value, description]) => {
    return {
      value,
      name: `${value.padEnd(30)} (${description})`
    }
  }),

  messages: {
    type: '<type> 用于说明 commit 的类别，只允许使用下面 9 个标签: \n',
    scope:
      '<scope> 影响范围，比如会影响到哪个模块/性能/哪一个（类务层，持久层，缓存，rpc），如果是特性代码，可以写特性名称 (可选): \n',
    customScope: '<scope> 自定影响范围，请精简扼要但不失原意: \n',
    subject: '<subject> 目的的简短描述，不超过 100 个字符: \n',
    body: '<body> 对本次 commit 的详细描述，使用第一人称，应该说明代码变动的动机，以及与以前行为的对比，可以使用 "|" 分成多行 (可选):\n',
    breaking:
      '<breaking> 对破坏性变动(Breaking Change)的描述、以及变动理由和迁移方法 (可选):\n',
    footer: '<footer> 针对的 issue，比如：#520, #1314 (可选):\n',
    confirmCommit: '<confirm commit> 请确认以上描述。'
  },
  upperCaseSubject: true,
  footerPrefix: 'Related issue:',
  allowCustomScopes: true,
  allowBreakingChanges: ['feat', 'fix', 'refactor']
}

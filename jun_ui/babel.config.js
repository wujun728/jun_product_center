module.exports = {
  presets: [
    // https://github.com/vuejs/vue-cli/tree/master/packages/@vue/babel-preset-app
    '@vue/cli-plugin-babel/preset'
  ],
  "plugins": [
    [
      'component',
      {
        libraryName: '$ui',
        libDir: 'components',
        styleLibraryName: '~node_modules/@xdh/my/ui/lib/styles',
        ext: '.scss'
      },
      '$ui'
    ],
    [
      'component',
      {
        libraryName: '$ui/charts',
        libDir: 'packages',
        style: false
      },
      '$ui/charts'
    ],
    [
      'component',
      {
        libraryName: '$ui/map',
        libDir: 'packages',
        style: false
      },
      '$ui/map'
    ],
    [
      'component',
      {
        libraryName: '$ui/dv',
        libDir: 'packages',
        style: false
      },
      '$ui/dv'
    ]
  ],
  'env': {
    'development': {
      // babel-plugin-dynamic-import-node plugin only does one thing by converting all import() to require().
      // This plugin can significantly increase the speed of hot updates, when you have a large number of pages.
      'plugins': ['dynamic-import-node']
    }
  }
}
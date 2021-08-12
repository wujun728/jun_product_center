interface PearConfig {
  defaultLanguage: 'zh-CN' | 'en-US';
  primaryColor: string;
  routeMode: 'history' | 'hash';
  routeAnimation: string;
}

const pearConfig: PearConfig = {
  defaultLanguage: 'zh-CN',
  primaryColor: '#36b368',
  routeMode: 'hash',
  routeAnimation: 'fade-top'
}

export { pearConfig as default }
export {
  pearConfig
}

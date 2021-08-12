import defaultSettings from '@/settings'

const title = defaultSettings.title || '9420 tools'

export default function getPageTitle(pageTitle) {
  if (pageTitle) {
    return `${pageTitle} - ${title}`
  }
  return `${title}`
}

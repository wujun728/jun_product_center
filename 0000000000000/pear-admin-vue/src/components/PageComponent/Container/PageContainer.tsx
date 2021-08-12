import { defineComponent, watch, reactive } from 'vue'
import { PageHeaderProps } from 'ant-design-vue/es/page-header'
import { getAntdComponentProps } from '@/components/_utils'
import './index.less'
import { useRoute } from 'vue-router'
import { useI18n } from 'vue-i18n'

interface BreadRoute {
  path: string;
  breadcrumbName: string;
  meta: any;
  children?: Array<BreadRoute>;
}

interface Breadcrumb {
  routes: BreadRoute [];
  itemRender: Function | undefined;
}

interface PageContainerState {
  breadcrumb: Breadcrumb;
  title: string;
}

export default defineComponent({
  name: 'PageContainer',
  props: Object.assign({}, PageHeaderProps, {
    content: {
      type: String
    }
  }),
  setup (props, ctx) {
    const defaultPageHeaderProps = getAntdComponentProps(PageHeaderProps, props)
    const route = useRoute()
    const pageContainerState = reactive({
      breadcrumb: {
        routes: [],
        itemRender: undefined
      },
      title: ''
    } as PageContainerState)

    const i18n = useI18n()
    const { t } = i18n
    watch(() => i18n, () => {
      pageContainerState.breadcrumb.routes.forEach(it => {
        it.breadcrumbName = it.path === '/' ? t('menu.home') : t(it.meta?.i18nTitle)
      })
      const title = route?.meta?.i18nTitle as string
      pageContainerState.title = t(title)
    }, { deep: true })

    const handleRouteChange = () => {
      // 设置面包屑
      pageContainerState.breadcrumb = {
        routes: route.matched.map(it => {
          return {
            name: it.name,
            path: it.path,
            meta: it.meta,
            breadcrumbName: it.path === '/' ? t('menu.home') : t(it.meta?.i18nTitle as string) // it.meta?.title// i18nTitle
          }
        }) as BreadRoute[],
        itemRender: ({
          route
        }) => {
          return (
            route.path !== '/' ? (
              <span>{route.breadcrumbName}</span>
            ) : (
              <router-link to={{
                name: route.name
              }}>
                {route.breadcrumbName}
              </router-link>
            )
          )
        }
      }
      // 设置标题title
      const title = route?.meta?.i18nTitle as string
      pageContainerState.title = t(title)
    }
    watch(() => route.fullPath, handleRouteChange, { immediate: true })
    return () => {
      const pageDefaultSlot = Object.keys(ctx.slots).includes('default') ? ctx.slots : null
      const slots = Object.keys(ctx.slots).reduce((slots, name) => {
        switch (name) {
          case 'content':
            // eslint-disable-next-line @typescript-eslint/ban-ts-ignore
            // @ts-ignore
            slots.default = ctx.slots[name]
            return slots
          case 'default':
            return slots
          default:
            return {
              ...slots,
              [name]: ctx.slots[name]
            }
        }
      }, {})
      return (
        <div class="app-page-container">
          <a-page-header
            {...{
              ...defaultPageHeaderProps,
              breadcrumb: pageContainerState.breadcrumb,
              title: props.title ? props.title : pageContainerState.title
            }}
            class="app-page-container-head"
            v-slots={slots}
          >
            {!Object.keys(ctx.slots).includes('content') && props.content ? (
              <div>{props.content}</div>
            ) : null}
          </a-page-header>
          <div class="app-page-container-content">
            { pageDefaultSlot && pageDefaultSlot.default?.() }
          </div>
        </div>
      )
    }
  }
})

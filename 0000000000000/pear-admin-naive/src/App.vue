<template>
  <n-config-provider v-bind="uiConfig">
    <n-global-style />
    <n-theme-editor v-if="isDevelopment()" />
    <n-notification-provider>
      <n-dialog-provider>
        <n-message-provider>
          <!-- 内部挂在notification message 到windows对象上 -->
          <Interaction />
          <router-view v-slot="{ Component }">
            <template v-if="Component">
              <transition name="fade">
                <!--              <keep-alive>-->
                <suspense timeout="0">
                  <component :is="Component"></component>
                  <template #fallback>
                    <div
                      class="
                        flex flex-row
                        items-center
                        justify-center
                        w-screen
                        h-screen
                      "
                    >
                      <n-element
                        tag="h2"
                        class="text-4xl"
                        style="
                          color: var(--primary-color);
                          transition: 0.3s var(--cubic-bezier-ease-in-out);
                        "
                      >
                        Pear Admin
                      </n-element>
                      <Icon size="32" class="mt-20 rotating-animate">
                        <LoadingOutlined />
                      </Icon>
                    </div>
                  </template>
                </suspense>
                <!--              </keep-alive>-->
              </transition>
            </template>
          </router-view>
        </n-message-provider>
      </n-dialog-provider>
    </n-notification-provider>
  </n-config-provider>
</template>

<script lang="ts" setup>
  import { NThemeEditor, NGlobalStyle } from 'naive-ui'
  import useUIConfigs from '@/config/useUIConfigs'
  import { isDevelopment } from '@/utils/utils'
  import Interaction from '@/components/Application/Interaction'
  import { LoadingOutlined } from '@vicons/antd'
  import { Icon } from '@vicons/utils'
  const { cfg: uiConfig } = useUIConfigs()
</script>

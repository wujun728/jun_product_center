import { useMessage, useNotification } from 'naive-ui'

export default function useInteraction(): void {
  const message = useMessage()
  const notification = useNotification()
  if (window) {
    window.$message = message
    window.$notification = notification
  }
}

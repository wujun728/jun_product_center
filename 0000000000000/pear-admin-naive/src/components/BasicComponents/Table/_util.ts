export function getFunctionAttrs(attrs: Record<string, unknown>): string[] {
  const result: string[] = []
  Object.keys(attrs).forEach((it) => {
    if (typeof attrs[it] === 'function') {
      result.push(it)
    }
  })
  return result
}

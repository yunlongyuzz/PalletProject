import Vue from 'vue'

const doResize = (el, binding, vnode) => {
  let remainHeight = binding.value
  if (remainHeight === undefined) {
    remainHeight = 80
  }
  const parentEl = el.offsetParent
  if(parentEl){
    let height = parentEl.clientHeight - el.offsetTop - remainHeight
    if (height < 500) height = 500
    if (vnode.componentInstance.height !== height) {
      vnode.componentInstance.layout.setHeight(height)
      vnode.componentInstance.doLayout()
    }
  }
}

// v-table-auto-height: 表格自动大小
Vue.directive('table-auto-height', {
  bind (el, binding, vnode) {
    el.resizeListener = () => {
      doResize(el, binding, vnode)
    }
    window.addEventListener('resize', el.resizeListener)
  },
  update (el, binding, vnode) {
    doResize(el, binding, vnode)
  },
  unbind (el) {
    window.removeEventListener('resize', el.resizeListener)
  }
})

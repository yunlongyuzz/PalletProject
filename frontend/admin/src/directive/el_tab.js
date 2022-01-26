import Vue from 'vue'

// v-tab-hide-head: 只有一个头的时候自动隐藏头
Vue.directive('tab-hide-head', {
  bind (el, binding, vnode) {
    const secondPanel = el.querySelector('#pane-second')
    const tabHeader = el.querySelector('.el-tabs__header')
    if (secondPanel == null) {
      tabHeader.style.display = 'none'
    } else {
      tabHeader.style.display = 'block'
      tabHeader.style.marginTop = '-20px'
    }
  }
})

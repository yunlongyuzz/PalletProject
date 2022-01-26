import Vue from 'vue'

import d2Container from './d2-container'

// 注意 有些组件使用异步加载会有影响
Vue.component('d2-container', d2Container)
Vue.component('d2-icon', () => import('./d2-icon'))
Vue.component('d2-icon-svg', () => import('./d2-icon-svg/index.vue'))

Vue.component('input-select', () => import('./input/select.vue'))
Vue.component('input-radio', () => import('./input/radio.vue'))
Vue.component('input-checkbox', () => import('./input/checkbox.vue'))
Vue.component('input-transfer', () => import('./input/transfer.vue'))
Vue.component('input-image', () => import('./input/image.vue'))
Vue.component('input-mimage', () => import('./input/mimage.vue'))
Vue.component('input-editor', () => import('./input/editor.vue'))
Vue.component('pagination', () => import('./input/pagination.vue'))


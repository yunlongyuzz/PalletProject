// import { find, assign } from 'lodash'

export default ({ service, request, tools }) => ({
  getAllList(controller, data) {
    return request({
      url: '/' + controller.toLocaleLowerCase() + '/getAllList',
      method: 'post',
      data: data
    })
  },
  getList(controller, data) {
    return request({
      url: '/' + controller.toLocaleLowerCase() + '/getList',
      method: 'post',
      data: data
    })
  },
  getCount(controller, data) {
    return request({
      url: '/' + controller.toLocaleLowerCase() + '/getCount',
      method: 'post',
      data: data
    })
  },
  getInfo(controller, data) {
    return request({
      url: '/' + controller.toLocaleLowerCase() + '/getInfo',
      method: 'post',
      data: data
    })
  },
  addInfo(controller, data) {
    return request({
      url: '/' + controller.toLocaleLowerCase() + '/addInfo',
      method: 'post',
      data: data
    })
  },
  saveInfo(controller, data) {
    return request({
      url: '/' + controller.toLocaleLowerCase() + '/saveInfo',
      method: 'post',
      data: data
    })
  },
  delInfo(controller, data) {
    return request({
      url: '/' + controller.toLocaleLowerCase() + '/delInfo',
      method: 'post',
      data: data
    })
  },
  updateField(controller, data) {
    return request({
      url: '/' + controller.toLocaleLowerCase() + '/updateField',
      method: 'post',
      data: data
    })
  },
  getFieldList(controller, data) {
    return request({
      url: '/' + controller.toLocaleLowerCase() + '/getFieldList',
      method: 'post',
      data: data
    })
  },
  getFieldParam(controller, data) {
    return request({
      url: '/' + controller.toLocaleLowerCase() + '/getFieldParam',
      method: 'post',
      data: data
    })
  }
})

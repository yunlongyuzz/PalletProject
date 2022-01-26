// import { find, assign } from 'lodash'

export default ({ service, request, tools }) => ({
  /**
   * @description 登录
   * @param {Object} data 登录携带的信息
   */
  login(data = {}) {
    return request({
      url: '/token/client/get',
      method: 'post',
      data
    })
  },
  getInfo() {
    return request({
      url: '/token/client/getInfo',
      method: 'post',
      data: {
      }
    })
  },
  logout() {
    return request({
      url: '/token/client/logout',
      method: 'post',
      data: {
      }
    })
  }
})

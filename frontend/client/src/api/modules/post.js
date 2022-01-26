// import { find, assign } from 'lodash'

export default ({ service, request, tools }) => ({
  postData(url, data) {
    return request({
      url: '/' + url,
      method: 'post',
      data: data
    })
  }
})

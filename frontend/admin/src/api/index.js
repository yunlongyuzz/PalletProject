// import { find, assign } from 'lodash'
import { service, request } from './service'
import * as tools from './tools'

const files = require.context('./modules', false, /\.js$/)
const modules = {}
files.keys().forEach(key => {
  modules[key.replace(/(\.\/|\.js)/g, '')] = files(key).default({
    service,
    request,
    tools
  })
})
export default modules

import { uniqueId } from 'lodash'

/**
 * @description 给菜单数据补充上 path 字段
 * @description https://github.com/d2-projects/d2-admin/issues/209
 * @param {Array} menu 原始的菜单数据
 */
function supplementPath(menu) {
  return menu.map(e => ({
    ...e,
    path: e.path || uniqueId('d2-menu-empty-'),
    ...e.children ? {
      children: supplementPath(e.children)
    } : {}
  }))
}

export const menuHeader = supplementPath([
  { path: '/index', title: '首页', icon: 'home' },
])

export const menuAside = supplementPath([
  {
    title: '销售统计',
    icon: 'folder-o',
    children: [
      { path: '/report_category', title: '品类销售统计' },
      { path: '/report_24hour', title: '24小时销售统计' },
      { path: '/report_device', title: '设备销售统计' },
      { path: '/report_devicemonth', title: '设备月销售统计' },
    ]
  },
  {
    title: '订单管理',
    icon: 'folder-o',
    children: [
      { path: '/default_order_index', title: '订单管理' },
      { path: '/default_order_refund_index', title: '退款管理' },
    ]
  },
  {
    title: '促销活动',
    icon: 'folder-o',
    path: '/default_promotion_index',
  },
  {
    title: '商户管理',
    icon: 'folder-o',
    children: [
      { path: '/default_client_index', title: '商户管理' },
      { path: '/default_client_area_index', title: '分区管理' },
      { path: '/default_client_user_store_index', title: '补货员' },
    ]
  },
  {
    title: '设备管理',
    icon: 'folder-o',
    children: [
      { path: '/default_device_index', title: '设备管理' },
      { path: '/default_device_model_index', title: '型号管理' },
      { path: '/default_device_qrcode_index', title: '二维码' },
      { path: '/default_device_sn_index', title: 'SN码' },
      { path: '/default_device_product_index', title: '设备库存' },
    ]
  },
  {
    title: '商品管理',
    icon: 'folder-o',
    children: [
      { path: '/default_product_index', title: '商品管理' },
      { path: '/default_product_cate_index', title: '分类管理' },
    ]
  },
  {
    title: '开门日志',
    icon: 'folder-o',
    path: '/default_activity_index',
  },
  {
    title: '商品进出',
    icon: 'folder-o',
    path: '/default_tran_index',
  },
  {
    title: '用户管理',
    icon: 'folder-o',
    children: [
      { path: '/default_user_check_index', title: '审核员' },
      { path: '/default_admin_user_index', title: '系统管理员' },
      { path: '/default_admin_role_index', title: '系统角色' },
      { path: '/default_client_user_index', title: '商户管理员' },
      { path: '/default_client_user_role_index', title: '商户角色管理' },
    ]
  },
  {
    title: '仓库管理',
    icon: 'folder-o',
    children: [
      { path: '/default_warehouse_supplier_index', title: '供应商管理' },
      { path: '/default_warehouse_index', title: '仓库管理' },
      { path: '/default_warehouse_storage_index', title: '库位管理' },
      { path: '/default_warehouse_stockin_index', title: '入库单管理' },
      { path: '/default_warehouse_stockout_index', title: '出库单管理' },
      { path: '/default_warehouse_inventory_index', title: '仓库盘存' },
    ]
  },
])

import request from '@/utils/request'

// 查询订单信息列表
export function listOrder_information(query) {
  return request({
    url: '/mine/order_information/list',
    method: 'get',
    params: query
  })
}

// 查询订单信息详细
export function getOrder_information(orderId) {
  return request({
    url: '/mine/order_information/' + orderId,
    method: 'get'
  })
}

// 新增订单信息
export function addOrder_information(data) {
  return request({
    url: '/mine/order_information',
    method: 'post',
    data: data
  })
}

// 修改订单信息
export function updateOrder_information(data) {
  return request({
    url: '/mine/order_information',
    method: 'put',
    data: data
  })
}

// 删除订单信息
export function delOrder_information(orderId) {
  return request({
    url: '/mine/order_information/' + orderId,
    method: 'delete'
  })
}

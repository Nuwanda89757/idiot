import request from '@/utils/request'

// 查询月度财报信息列表
export function listReport_information(query) {
  return request({
    url: '/mine/report_information/list',
    method: 'get',
    params: query
  })
}

// 查询月度财报信息详细
export function getReport_information(reportId) {
  return request({
    url: '/mine/report_information/' + reportId,
    method: 'get'
  })
}

// 新增月度财报信息
export function addReport_information(data) {
  return request({
    url: '/mine/report_information',
    method: 'post',
    data: data
  })
}

// 修改月度财报信息
export function updateReport_information(data) {
  return request({
    url: '/mine/report_information',
    method: 'put',
    data: data
  })
}

// 删除月度财报信息
export function delReport_information(reportId) {
  return request({
    url: '/mine/report_information/' + reportId,
    method: 'delete'
  })
}

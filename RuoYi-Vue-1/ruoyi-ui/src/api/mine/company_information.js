import request from '@/utils/request'

// 查询公司信息列表
export function listCompany_information(query) {
  return request({
    url: '/mine/company_information/list',
    method: 'get',
    params: query
  })
}

// 查询公司信息详细
export function getCompany_information(companyId) {
  return request({
    url: '/mine/company_information/' + companyId,
    method: 'get'
  })
}

// 新增公司信息
export function addCompany_information(data) {
  return request({
    url: '/mine/company_information',
    method: 'post',
    data: data
  })
}

// 修改公司信息
export function updateCompany_information(data) {
  return request({
    url: '/mine/company_information',
    method: 'put',
    data: data
  })
}

// 删除公司信息
export function delCompany_information(companyId) {
  return request({
    url: '/mine/company_information/' + companyId,
    method: 'delete'
  })
}

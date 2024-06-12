import request from '@/utils/request'

// 查询员工信息列表
export function listEmployee_information(query) {
  return request({
    url: '/mine/employee_information/list',
    method: 'get',
    params: query
  })
}

// 查询员工信息详细
export function getEmployee_information(employeeId) {
  return request({
    url: '/mine/employee_information/' + employeeId,
    method: 'get'
  })
}

// 新增员工信息
export function addEmployee_information(data) {
  return request({
    url: '/mine/employee_information',
    method: 'post',
    data: data
  })
}

// 修改员工信息
export function updateEmployee_information(data) {
  return request({
    url: '/mine/employee_information',
    method: 'put',
    data: data
  })
}

// 删除员工信息
export function delEmployee_information(employeeId) {
  return request({
    url: '/mine/employee_information/' + employeeId,
    method: 'delete'
  })
}

//批量导入数据
/*
export function ImportFile(data){
  return request({
    url: '/mine/employee_information/import',
    method: 'post',
    data: data
  })
}

 */

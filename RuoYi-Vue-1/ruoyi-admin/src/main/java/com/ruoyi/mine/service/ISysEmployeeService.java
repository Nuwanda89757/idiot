package com.ruoyi.mine.service;

import java.util.List;
import com.ruoyi.mine.domain.SysEmployee;

/**
 * 员工信息Service接口
 *
 * @author ruoyi
 * @date 2024-05-25
 */
public interface ISysEmployeeService
{
    /**
     * 查询员工信息
     *
     * @param employeeId 员工信息主键
     * @return 员工信息
     */
    public SysEmployee selectSysEmployeeByEmployeeId(Long employeeId);

    /**
     * 查询员工信息列表
     *
     * @param sysEmployee 员工信息
     * @return 员工信息集合
     */
    public List<SysEmployee> selectSysEmployeeList(SysEmployee sysEmployee);

    /**
     * 新增员工信息
     *
     * @param sysEmployee 员工信息
     * @return 结果
     */
    public int insertSysEmployee(SysEmployee sysEmployee);

    /**
     * 修改员工信息
     *
     * @param sysEmployee 员工信息
     * @return 结果
     */
    public int updateSysEmployee(SysEmployee sysEmployee);

    /**
     * 批量删除员工信息
     *
     * @param employeeIds 需要删除的员工信息主键集合
     * @return 结果
     */
    public int deleteSysEmployeeByEmployeeIds(Long[] employeeIds);

    /**
     * 删除员工信息信息
     *
     * @param employeeId 员工信息主键
     * @return 结果
     */
    public int deleteSysEmployeeByEmployeeId(Long employeeId);

    /**
     * [导入]第七步:更新现有表格数据相关↓
     *
     * @param linshiList      用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName        操作用户
     * @return 结果
     */
    public String importUser(List<SysEmployee> linshiList, Boolean isUpdateSupport, String operName);
    //    [导入]第七步:更新现有表格数据相关↑
}

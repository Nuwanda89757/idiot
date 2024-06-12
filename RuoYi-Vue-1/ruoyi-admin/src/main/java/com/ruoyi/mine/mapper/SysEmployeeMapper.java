package com.ruoyi.mine.mapper;

import java.util.List;
import com.ruoyi.mine.domain.SysEmployee;
import org.springframework.stereotype.Repository;

/**
 * 员工信息Mapper接口
 *
 * @author ruoyi
 * @date 2024-05-25
 */
@Repository
public interface SysEmployeeMapper
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
     * 删除员工信息
     *
     * @param employeeId 员工信息主键
     * @return 结果
     */
    public int deleteSysEmployeeByEmployeeId(Long employeeId);

    /**
     * 批量删除员工信息
     *
     * @param employeeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysEmployeeByEmployeeIds(Long[] employeeIds);
}

package com.ruoyi.mine.service.impl;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mine.mapper.SysEmployeeMapper;
import com.ruoyi.mine.domain.SysEmployee;
import com.ruoyi.mine.service.ISysEmployeeService;

/**
 * 员工信息Service业务层处理
 *
 * @author ruoyi
 * @date 2024-05-25
 */
@Service
public class SysEmployeeServiceImpl implements ISysEmployeeService
{
    @Autowired
    private SysEmployeeMapper sysEmployeeMapper;

    //    [导入]第八步↓
    @Autowired
    private ISysConfigService configService;
    //[导入]第八步↑

    /**
     * 查询员工信息
     *
     * @param employeeId 员工信息主键
     * @return 员工信息
     */
    @Override
    public SysEmployee selectSysEmployeeByEmployeeId(Long employeeId)
    {
        return sysEmployeeMapper.selectSysEmployeeByEmployeeId(employeeId);
    }

    /**
     * 查询员工信息列表
     *
     * @param sysEmployee 员工信息
     * @return 员工信息
     */
    @Override
    public List<SysEmployee> selectSysEmployeeList(SysEmployee sysEmployee)
    {
        return sysEmployeeMapper.selectSysEmployeeList(sysEmployee);
    }

    /**
     * 新增员工信息
     *
     * @param sysEmployee 员工信息
     * @return 结果
     */
    @Override
    public int insertSysEmployee(SysEmployee sysEmployee)
    {
        return sysEmployeeMapper.insertSysEmployee(sysEmployee);
    }

    /**
     * 修改员工信息
     *
     * @param sysEmployee 员工信息
     * @return 结果
     */
    @Override
    public int updateSysEmployee(SysEmployee sysEmployee)
    {
        return sysEmployeeMapper.updateSysEmployee(sysEmployee);
    }

    /**
     * 批量删除员工信息
     *
     * @param employeeIds 需要删除的员工信息主键
     * @return 结果
     */
    @Override
    public int deleteSysEmployeeByEmployeeIds(Long[] employeeIds)
    {
        return sysEmployeeMapper.deleteSysEmployeeByEmployeeIds(employeeIds);
    }

    /**
     * 删除员工信息信息
     *
     * @param employeeId 员工信息主键
     * @return 结果
     */
    @Override
    public int deleteSysEmployeeByEmployeeId(Long employeeId)
    {
        return sysEmployeeMapper.deleteSysEmployeeByEmployeeId(employeeId);
    }

    /**
     * [导入]第十步:导入数据
     *
     * @param userList        用户数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName        操作用户
     * @return 结果
     */
    @Override
    public String importUser(List<SysEmployee> userList, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(userList) || userList.size() == 0) {
            throw new ServiceException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        String password = configService.selectConfigByKey("sys.user.initPassword");
        for (SysEmployee user : userList) {
            try {
                // 验证是否存在这个用户
                SysEmployee tempSearch = new SysEmployee();
                tempSearch.setEmployeePosition(user.getEmployeePosition());
                tempSearch.setEmployeeName(user.getEmployeeName());
                List<SysEmployee> us = sysEmployeeMapper.selectSysEmployeeList(tempSearch);
                SysEmployee u = null;
                if (us != null && us.size() > 0) {
                    u = us.get(0);
                }
                // SysEmployee u = sysEmployeeMapper.selectSysEmployeeById(user.getId());
                if (StringUtils.isNull(u)) {
                    // 这部分注释掉的代码是跟验证token权限有关的,根据自己的需求后续自行去修改即可↓
                    // BeanValidators.validateWithException(validator, user);
                    // user.setPassword(SecurityUtils.encryptPassword(password));
                    // 这部分注释掉的代码是跟验证token权限有关的,根据自己的需求后续自行去修改即可↑
                    user.setCreateBy(operName);
                    // 设置数据库中的某些字段在导入的时候不管导入的表格内这个字段是否有数据都会在导入后变成空值↓
                    // user.set备注(null);
                    // 设置数据库中的某些字段在导入的时候不管导入的表格内这个字段是否有数据都会在导入后变成空值↑
                    sysEmployeeMapper.insertSysEmployee(user);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、姓名 " + user.getEmployeeName() + " 导入成功");
                } 
                else if (isUpdateSupport) {
                    // 这部分注释掉的代码是跟验证token权限有关的,根据自己的需求后续自行去修改即可↓
                    // BeanValidators.validateWithException(validator, user);
                    // checkUserAllowed(u);
                    // checkUserDataScope(u.getUserId());
                    // user.setUserId(u.getUserId());
                    // user.setUpdateBy(operName);
                    // 这部分注释掉的代码是跟验证token权限有关的,根据自己的需求后续自行去修改即可↑
                    // 设置数据库中的某些字段在导入的时候不管导入的表格内这个字段是否有数据都会在导入后变成空值↓
                    // user.set备注(null);
                    // 设置数据库中的某些字段在导入的时候不管导入的表格内这个字段是否有数据都会在导入后变成空值↑
                    sysEmployeeMapper.updateSysEmployee(user);
                    successNum++;
//                    后面的(successMsg.append)可以自定义修改提示的文本 在其后面修改样式即可
                    successMsg.append("<br/>" + successNum + "、姓名 " + user.getEmployeeName() + "(" + user.getEmployeePosition() + ")" + " 更新成功");
                } 
                else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、姓名 " + user.getEmployeeName() + "(" + user.getEmployeePosition() + ")" + " 已存在");
                }
            } 
            catch (Exception e) {
                failureNum++;
                String msg = "<br/>" + failureNum + "、姓名 " + user.getEmployeeName() + "(" + user.getEmployeePosition() + ")" + "导入失败：";
                failureMsg.append(msg + e.getMessage());
                // log.error(msg, e);
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "导入成功" + successNum + "条" + "<br/>" + "导入失败" + failureNum + " 条，失败原因如下：");
            throw new ServiceException(failureMsg.toString());
        } else {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return "恭喜您，数据已全部导入成功！共 " + successNum + " 条";
    }
    //    [导入]第十步:导入数据
}

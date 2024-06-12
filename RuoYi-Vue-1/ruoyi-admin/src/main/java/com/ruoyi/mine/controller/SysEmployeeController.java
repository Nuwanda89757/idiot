package com.ruoyi.mine.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.mine.domain.SysEmployee;
import com.ruoyi.mine.service.ISysEmployeeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 员工信息Controller
 *
 * @author ruoyi
 * @date 2024-05-25
 */
@RestController
@RequestMapping("/mine/employee_information")
public class SysEmployeeController extends BaseController
{
    @Autowired
    private ISysEmployeeService sysEmployeeService;

    /**
     * 查询员工信息列表
     */
    @PreAuthorize("@ss.hasPermi('mine:employee_information:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysEmployee sysEmployee)
    {
        startPage();
        List<SysEmployee> list = sysEmployeeService.selectSysEmployeeList(sysEmployee);
        return getDataTable(list);
    }

    /**
     * 导出员工信息列表
     */
    @PreAuthorize("@ss.hasPermi('mine:employee_information:export')")
    @Log(title = "员工信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysEmployee sysEmployee)
    {
        List<SysEmployee> list = sysEmployeeService.selectSysEmployeeList(sysEmployee);
        ExcelUtil<SysEmployee> util = new ExcelUtil<SysEmployee>(SysEmployee.class);
        util.exportExcel(response, list, "员工信息数据");
    }

    /**
     * 获取员工信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mine:employee_information:query')")
    @GetMapping(value = "/{employeeId}")
    public AjaxResult getInfo(@PathVariable("employeeId") Long employeeId)
    {
        return success(sysEmployeeService.selectSysEmployeeByEmployeeId(employeeId));
    }

    /**
     * 新增员工信息
     */
    @PreAuthorize("@ss.hasPermi('mine:employee_information:add')")
    @Log(title = "员工信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysEmployee sysEmployee)
    {
        return toAjax(sysEmployeeService.insertSysEmployee(sysEmployee));
    }

    /**
     * 修改员工信息
     */
    @PreAuthorize("@ss.hasPermi('mine:employee_information:edit')")
    @Log(title = "员工信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysEmployee sysEmployee)
    {
        return toAjax(sysEmployeeService.updateSysEmployee(sysEmployee));
    }

    /**
     * 删除员工信息
     */
    @PreAuthorize("@ss.hasPermi('mine:employee_information:remove')")
    @Log(title = "员工信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{employeeIds}")
    public AjaxResult remove(@PathVariable Long[] employeeIds)
    {
        return toAjax(sysEmployeeService.deleteSysEmployeeByEmployeeIds(employeeIds));
    }

    /**
     * [导入]第六步:处理Excel文件导入
     */

    @Log(title = "表格导入", businessType = BusinessType.IMPORT) // todo
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysEmployee> util = new ExcelUtil<>(SysEmployee.class); // todo
        List<SysEmployee> linshiList = util.importExcel(file.getInputStream()); // todo
        String operName = getUsername();
        String message = sysEmployeeService.importUser(linshiList, updateSupport, operName); // todo
        return AjaxResult.success(message);
    }
    //处理模板导出↓
    @PostMapping("/importTemplate")
    public void importTemplate(HttpServletResponse response)
    {
        ExcelUtil<SysEmployee> util = new ExcelUtil<>(SysEmployee.class);
        util.importTemplateExcel(response, "表格模板");
    }
    //处理模板导出↑
    //    [导入]第六步:处理Excel文件导入
}

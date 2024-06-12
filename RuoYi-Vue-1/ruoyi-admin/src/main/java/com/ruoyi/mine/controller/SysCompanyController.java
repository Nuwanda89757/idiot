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
import com.ruoyi.mine.domain.SysCompany;
import com.ruoyi.mine.service.ISysCompanyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公司信息Controller
 * 
 * @author ruoyi
 * @date 2024-05-26
 */
@RestController
@RequestMapping("/mine/company_information")
public class SysCompanyController extends BaseController
{
    @Autowired
    private ISysCompanyService sysCompanyService;

    /**
     * 查询公司信息列表
     */
    @PreAuthorize("@ss.hasPermi('mine:company_information:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysCompany sysCompany)
    {
        startPage();
        List<SysCompany> list = sysCompanyService.selectSysCompanyList(sysCompany);
        return getDataTable(list);
    }

    /**
     * 导出公司信息列表
     */
    @PreAuthorize("@ss.hasPermi('mine:company_information:export')")
    @Log(title = "公司信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCompany sysCompany)
    {
        List<SysCompany> list = sysCompanyService.selectSysCompanyList(sysCompany);
        ExcelUtil<SysCompany> util = new ExcelUtil<SysCompany>(SysCompany.class);
        util.exportExcel(response, list, "公司信息数据");
    }

    /**
     * 获取公司信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mine:company_information:query')")
    @GetMapping(value = "/{companyId}")
    public AjaxResult getInfo(@PathVariable("companyId") Long companyId)
    {
        return success(sysCompanyService.selectSysCompanyByCompanyId(companyId));
    }

    /**
     * 新增公司信息
     */
    @PreAuthorize("@ss.hasPermi('mine:company_information:add')")
    @Log(title = "公司信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCompany sysCompany)
    {
        return toAjax(sysCompanyService.insertSysCompany(sysCompany));
    }

    /**
     * 修改公司信息
     */
    @PreAuthorize("@ss.hasPermi('mine:company_information:edit')")
    @Log(title = "公司信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCompany sysCompany)
    {
        return toAjax(sysCompanyService.updateSysCompany(sysCompany));
    }

    /**
     * 删除公司信息
     */
    @PreAuthorize("@ss.hasPermi('mine:company_information:remove')")
    @Log(title = "公司信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{companyIds}")
    public AjaxResult remove(@PathVariable Long[] companyIds)
    {
        return toAjax(sysCompanyService.deleteSysCompanyByCompanyIds(companyIds));
    }
}

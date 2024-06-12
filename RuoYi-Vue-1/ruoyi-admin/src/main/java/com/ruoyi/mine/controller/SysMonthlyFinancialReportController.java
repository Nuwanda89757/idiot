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
import com.ruoyi.mine.domain.SysMonthlyFinancialReport;
import com.ruoyi.mine.service.ISysMonthlyFinancialReportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 月度财报信息Controller
 * 
 * @author ruoyi
 * @date 2024-05-26
 */
@RestController
@RequestMapping("/mine/report_information")
public class SysMonthlyFinancialReportController extends BaseController
{
    @Autowired
    private ISysMonthlyFinancialReportService sysMonthlyFinancialReportService;

    /**
     * 查询月度财报信息列表
     */
    @PreAuthorize("@ss.hasPermi('mine:report_information:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysMonthlyFinancialReport sysMonthlyFinancialReport)
    {
        startPage();
        List<SysMonthlyFinancialReport> list = sysMonthlyFinancialReportService.selectSysMonthlyFinancialReportList(sysMonthlyFinancialReport);
        return getDataTable(list);
    }

    /**
     * 导出月度财报信息列表
     */
    @PreAuthorize("@ss.hasPermi('mine:report_information:export')")
    @Log(title = "月度财报信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysMonthlyFinancialReport sysMonthlyFinancialReport)
    {
        List<SysMonthlyFinancialReport> list = sysMonthlyFinancialReportService.selectSysMonthlyFinancialReportList(sysMonthlyFinancialReport);
        ExcelUtil<SysMonthlyFinancialReport> util = new ExcelUtil<SysMonthlyFinancialReport>(SysMonthlyFinancialReport.class);
        util.exportExcel(response, list, "月度财报信息数据");
    }

    /**
     * 获取月度财报信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mine:report_information:query')")
    @GetMapping(value = "/{reportId}")
    public AjaxResult getInfo(@PathVariable("reportId") Long reportId)
    {
        return success(sysMonthlyFinancialReportService.selectSysMonthlyFinancialReportByReportId(reportId));
    }

    /**
     * 新增月度财报信息
     */
    @PreAuthorize("@ss.hasPermi('mine:report_information:add')")
    @Log(title = "月度财报信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysMonthlyFinancialReport sysMonthlyFinancialReport)
    {
        return toAjax(sysMonthlyFinancialReportService.insertSysMonthlyFinancialReport(sysMonthlyFinancialReport));
    }

    /**
     * 修改月度财报信息
     */
    @PreAuthorize("@ss.hasPermi('mine:report_information:edit')")
    @Log(title = "月度财报信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysMonthlyFinancialReport sysMonthlyFinancialReport)
    {
        return toAjax(sysMonthlyFinancialReportService.updateSysMonthlyFinancialReport(sysMonthlyFinancialReport));
    }

    /**
     * 删除月度财报信息
     */
    @PreAuthorize("@ss.hasPermi('mine:report_information:remove')")
    @Log(title = "月度财报信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reportIds}")
    public AjaxResult remove(@PathVariable Long[] reportIds)
    {
        return toAjax(sysMonthlyFinancialReportService.deleteSysMonthlyFinancialReportByReportIds(reportIds));
    }
}

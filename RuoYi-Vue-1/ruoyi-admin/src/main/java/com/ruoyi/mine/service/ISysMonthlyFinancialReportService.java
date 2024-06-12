package com.ruoyi.mine.service;

import java.util.List;
import com.ruoyi.mine.domain.SysMonthlyFinancialReport;

/**
 * 月度财报信息Service接口
 * 
 * @author ruoyi
 * @date 2024-05-26
 */
public interface ISysMonthlyFinancialReportService 
{
    /**
     * 查询月度财报信息
     * 
     * @param reportId 月度财报信息主键
     * @return 月度财报信息
     */
    public SysMonthlyFinancialReport selectSysMonthlyFinancialReportByReportId(Long reportId);

    /**
     * 查询月度财报信息列表
     * 
     * @param sysMonthlyFinancialReport 月度财报信息
     * @return 月度财报信息集合
     */
    public List<SysMonthlyFinancialReport> selectSysMonthlyFinancialReportList(SysMonthlyFinancialReport sysMonthlyFinancialReport);

    /**
     * 新增月度财报信息
     * 
     * @param sysMonthlyFinancialReport 月度财报信息
     * @return 结果
     */
    public int insertSysMonthlyFinancialReport(SysMonthlyFinancialReport sysMonthlyFinancialReport);

    /**
     * 修改月度财报信息
     * 
     * @param sysMonthlyFinancialReport 月度财报信息
     * @return 结果
     */
    public int updateSysMonthlyFinancialReport(SysMonthlyFinancialReport sysMonthlyFinancialReport);

    /**
     * 批量删除月度财报信息
     * 
     * @param reportIds 需要删除的月度财报信息主键集合
     * @return 结果
     */
    public int deleteSysMonthlyFinancialReportByReportIds(Long[] reportIds);

    /**
     * 删除月度财报信息信息
     * 
     * @param reportId 月度财报信息主键
     * @return 结果
     */
    public int deleteSysMonthlyFinancialReportByReportId(Long reportId);
}

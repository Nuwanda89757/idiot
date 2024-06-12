package com.ruoyi.mine.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mine.mapper.SysMonthlyFinancialReportMapper;
import com.ruoyi.mine.domain.SysMonthlyFinancialReport;
import com.ruoyi.mine.service.ISysMonthlyFinancialReportService;

/**
 * 月度财报信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-26
 */
@Service
public class SysMonthlyFinancialReportServiceImpl implements ISysMonthlyFinancialReportService 
{
    @Autowired
    private SysMonthlyFinancialReportMapper sysMonthlyFinancialReportMapper;

    /**
     * 查询月度财报信息
     * 
     * @param reportId 月度财报信息主键
     * @return 月度财报信息
     */
    @Override
    public SysMonthlyFinancialReport selectSysMonthlyFinancialReportByReportId(Long reportId)
    {
        return sysMonthlyFinancialReportMapper.selectSysMonthlyFinancialReportByReportId(reportId);
    }

    /**
     * 查询月度财报信息列表
     * 
     * @param sysMonthlyFinancialReport 月度财报信息
     * @return 月度财报信息
     */
    @Override
    public List<SysMonthlyFinancialReport> selectSysMonthlyFinancialReportList(SysMonthlyFinancialReport sysMonthlyFinancialReport)
    {
        return sysMonthlyFinancialReportMapper.selectSysMonthlyFinancialReportList(sysMonthlyFinancialReport);
    }

    /**
     * 新增月度财报信息
     * 
     * @param sysMonthlyFinancialReport 月度财报信息
     * @return 结果
     */
    @Override
    public int insertSysMonthlyFinancialReport(SysMonthlyFinancialReport sysMonthlyFinancialReport)
    {
        return sysMonthlyFinancialReportMapper.insertSysMonthlyFinancialReport(sysMonthlyFinancialReport);
    }

    /**
     * 修改月度财报信息
     * 
     * @param sysMonthlyFinancialReport 月度财报信息
     * @return 结果
     */
    @Override
    public int updateSysMonthlyFinancialReport(SysMonthlyFinancialReport sysMonthlyFinancialReport)
    {
        return sysMonthlyFinancialReportMapper.updateSysMonthlyFinancialReport(sysMonthlyFinancialReport);
    }

    /**
     * 批量删除月度财报信息
     * 
     * @param reportIds 需要删除的月度财报信息主键
     * @return 结果
     */
    @Override
    public int deleteSysMonthlyFinancialReportByReportIds(Long[] reportIds)
    {
        return sysMonthlyFinancialReportMapper.deleteSysMonthlyFinancialReportByReportIds(reportIds);
    }

    /**
     * 删除月度财报信息信息
     * 
     * @param reportId 月度财报信息主键
     * @return 结果
     */
    @Override
    public int deleteSysMonthlyFinancialReportByReportId(Long reportId)
    {
        return sysMonthlyFinancialReportMapper.deleteSysMonthlyFinancialReportByReportId(reportId);
    }
}

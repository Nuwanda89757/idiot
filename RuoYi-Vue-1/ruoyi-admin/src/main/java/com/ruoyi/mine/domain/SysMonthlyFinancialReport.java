package com.ruoyi.mine.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 月度财报信息对象 sys_monthly_financial_report
 * 
 * @author ruoyi
 * @date 2024-05-26
 */
public class SysMonthlyFinancialReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 报表ID */
    private Long reportId;

    /** 公司ID */
    @Excel(name = "公司ID")
    private Long companyId;

    /** 年份 */
    @Excel(name = "年份")
    private Long year;

    /** 月份 */
    @Excel(name = "月份")
    private Long month;

    /** 营业收入 */
    @Excel(name = "营业收入")
    private BigDecimal revenue;

    /** 净利润 */
    @Excel(name = "净利润")
    private BigDecimal profit;

    public void setReportId(Long reportId) 
    {
        this.reportId = reportId;
    }

    public Long getReportId() 
    {
        return reportId;
    }
    public void setCompanyId(Long companyId) 
    {
        this.companyId = companyId;
    }

    public Long getCompanyId() 
    {
        return companyId;
    }
    public void setYear(Long year) 
    {
        this.year = year;
    }

    public Long getYear() 
    {
        return year;
    }
    public void setMonth(Long month) 
    {
        this.month = month;
    }

    public Long getMonth() 
    {
        return month;
    }
    public void setRevenue(BigDecimal revenue) 
    {
        this.revenue = revenue;
    }

    public BigDecimal getRevenue() 
    {
        return revenue;
    }
    public void setProfit(BigDecimal profit) 
    {
        this.profit = profit;
    }

    public BigDecimal getProfit() 
    {
        return profit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reportId", getReportId())
            .append("companyId", getCompanyId())
            .append("year", getYear())
            .append("month", getMonth())
            .append("revenue", getRevenue())
            .append("profit", getProfit())
            .toString();
    }
}

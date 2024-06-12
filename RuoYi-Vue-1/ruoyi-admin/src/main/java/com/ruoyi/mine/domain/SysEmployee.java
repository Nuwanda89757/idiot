package com.ruoyi.mine.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 员工信息对象 sys_employee
 *
 * @author ruoyi
 * @date 2024-05-25
 */
public class SysEmployee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 员工ID */
    private Long employeeId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String employeeName;

    /** 职务 */
    @Excel(name = "职务")
    private String employeePosition;

    /** 入职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 离职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "离职日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    public void setEmployeeId(Long employeeId)
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId()
    {
        return employeeId;
    }
    public void setEmployeeName(String employeeName)
    {
        this.employeeName = employeeName;
    }

    public String getEmployeeName()
    {
        return employeeName;
    }
    public void setEmployeePosition(String employeePosition)
    {
        this.employeePosition = employeePosition;
    }

    public String getEmployeePosition()
    {
        return employeePosition;
    }
    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getStartDate()
    {
        return startDate;
    }
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("employeeId", getEmployeeId())
                .append("employeeName", getEmployeeName())
                .append("employeePosition", getEmployeePosition())
                .append("startDate", getStartDate())
                .append("endDate", getEndDate())
                .toString();
    }
}

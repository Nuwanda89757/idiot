package com.ruoyi.mine.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单信息对象 sys_order
 * 
 * @author ruoyi
 * @date 2024-05-26
 */
public class SysOrder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID (OrderID) */
    private Long orderId;

    /** 公司ID (CompanyID) */
    @Excel(name = "公司ID (CompanyID)")
    private Long companyId;

    /** 订单日期 (OrderDate) */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "订单日期 (OrderDate)", width = 30, dateFormat = "yyyy-MM-dd")
    private Date orderDate;

    /** 订单金额 (Amount) */
    @Excel(name = "订单金额 (Amount)")
    private BigDecimal amount;

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setCompanyId(Long companyId) 
    {
        this.companyId = companyId;
    }

    public Long getCompanyId() 
    {
        return companyId;
    }
    public void setOrderDate(Date orderDate) 
    {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() 
    {
        return orderDate;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("companyId", getCompanyId())
            .append("orderDate", getOrderDate())
            .append("amount", getAmount())
            .toString();
    }
}

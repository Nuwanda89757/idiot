package com.ruoyi.mine.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 公司信息对象 sys_company
 * 
 * @author ruoyi
 * @date 2024-05-26
 */
public class SysCompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 公司ID */
    private Long companyId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 公司地址 */
    @Excel(name = "公司地址")
    private String companyAddress;

    /** 行业分类 */
    @Excel(name = "行业分类")
    private String industry;

    /** 公司规模 */
    @Excel(name = "公司规模")
    private String companySize;

    public void setCompanyId(Long companyId) 
    {
        this.companyId = companyId;
    }

    public Long getCompanyId() 
    {
        return companyId;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setCompanyAddress(String companyAddress) 
    {
        this.companyAddress = companyAddress;
    }

    public String getCompanyAddress() 
    {
        return companyAddress;
    }
    public void setIndustry(String industry) 
    {
        this.industry = industry;
    }

    public String getIndustry() 
    {
        return industry;
    }
    public void setCompanySize(String companySize) 
    {
        this.companySize = companySize;
    }

    public String getCompanySize() 
    {
        return companySize;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("companyId", getCompanyId())
            .append("companyName", getCompanyName())
            .append("companyAddress", getCompanyAddress())
            .append("industry", getIndustry())
            .append("companySize", getCompanySize())
            .toString();
    }
}

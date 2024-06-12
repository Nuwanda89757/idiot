package com.ruoyi.mine.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mine.mapper.SysCompanyMapper;
import com.ruoyi.mine.domain.SysCompany;
import com.ruoyi.mine.service.ISysCompanyService;

/**
 * 公司信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-26
 */
@Service
public class SysCompanyServiceImpl implements ISysCompanyService 
{
    @Autowired
    private SysCompanyMapper sysCompanyMapper;

    /**
     * 查询公司信息
     * 
     * @param companyId 公司信息主键
     * @return 公司信息
     */
    @Override
    public SysCompany selectSysCompanyByCompanyId(Long companyId)
    {
        return sysCompanyMapper.selectSysCompanyByCompanyId(companyId);
    }

    /**
     * 查询公司信息列表
     * 
     * @param sysCompany 公司信息
     * @return 公司信息
     */
    @Override
    public List<SysCompany> selectSysCompanyList(SysCompany sysCompany)
    {
        return sysCompanyMapper.selectSysCompanyList(sysCompany);
    }

    /**
     * 新增公司信息
     * 
     * @param sysCompany 公司信息
     * @return 结果
     */
    @Override
    public int insertSysCompany(SysCompany sysCompany)
    {
        return sysCompanyMapper.insertSysCompany(sysCompany);
    }

    /**
     * 修改公司信息
     * 
     * @param sysCompany 公司信息
     * @return 结果
     */
    @Override
    public int updateSysCompany(SysCompany sysCompany)
    {
        return sysCompanyMapper.updateSysCompany(sysCompany);
    }

    /**
     * 批量删除公司信息
     * 
     * @param companyIds 需要删除的公司信息主键
     * @return 结果
     */
    @Override
    public int deleteSysCompanyByCompanyIds(Long[] companyIds)
    {
        return sysCompanyMapper.deleteSysCompanyByCompanyIds(companyIds);
    }

    /**
     * 删除公司信息信息
     * 
     * @param companyId 公司信息主键
     * @return 结果
     */
    @Override
    public int deleteSysCompanyByCompanyId(Long companyId)
    {
        return sysCompanyMapper.deleteSysCompanyByCompanyId(companyId);
    }
}

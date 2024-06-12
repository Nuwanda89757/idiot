package com.ruoyi.mine.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.mine.mapper.SysOrderMapper;
import com.ruoyi.mine.domain.SysOrder;
import com.ruoyi.mine.service.ISysOrderService;

/**
 * 订单信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-26
 */
@Service
public class SysOrderServiceImpl implements ISysOrderService 
{
    @Autowired
    private SysOrderMapper sysOrderMapper;

    /**
     * 查询订单信息
     * 
     * @param orderId 订单信息主键
     * @return 订单信息
     */
    @Override
    public SysOrder selectSysOrderByOrderId(Long orderId)
    {
        return sysOrderMapper.selectSysOrderByOrderId(orderId);
    }

    /**
     * 查询订单信息列表
     * 
     * @param sysOrder 订单信息
     * @return 订单信息
     */
    @Override
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder)
    {
        return sysOrderMapper.selectSysOrderList(sysOrder);
    }

    /**
     * 新增订单信息
     * 
     * @param sysOrder 订单信息
     * @return 结果
     */
    @Override
    public int insertSysOrder(SysOrder sysOrder)
    {
        return sysOrderMapper.insertSysOrder(sysOrder);
    }

    /**
     * 修改订单信息
     * 
     * @param sysOrder 订单信息
     * @return 结果
     */
    @Override
    public int updateSysOrder(SysOrder sysOrder)
    {
        return sysOrderMapper.updateSysOrder(sysOrder);
    }

    /**
     * 批量删除订单信息
     * 
     * @param orderIds 需要删除的订单信息主键
     * @return 结果
     */
    @Override
    public int deleteSysOrderByOrderIds(Long[] orderIds)
    {
        return sysOrderMapper.deleteSysOrderByOrderIds(orderIds);
    }

    /**
     * 删除订单信息信息
     * 
     * @param orderId 订单信息主键
     * @return 结果
     */
    @Override
    public int deleteSysOrderByOrderId(Long orderId)
    {
        return sysOrderMapper.deleteSysOrderByOrderId(orderId);
    }
}

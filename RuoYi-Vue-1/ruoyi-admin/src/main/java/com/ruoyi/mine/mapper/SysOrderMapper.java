package com.ruoyi.mine.mapper;

import java.util.List;
import com.ruoyi.mine.domain.SysOrder;

/**
 * 订单信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-26
 */
public interface SysOrderMapper 
{
    /**
     * 查询订单信息
     * 
     * @param orderId 订单信息主键
     * @return 订单信息
     */
    public SysOrder selectSysOrderByOrderId(Long orderId);

    /**
     * 查询订单信息列表
     * 
     * @param sysOrder 订单信息
     * @return 订单信息集合
     */
    public List<SysOrder> selectSysOrderList(SysOrder sysOrder);

    /**
     * 新增订单信息
     * 
     * @param sysOrder 订单信息
     * @return 结果
     */
    public int insertSysOrder(SysOrder sysOrder);

    /**
     * 修改订单信息
     * 
     * @param sysOrder 订单信息
     * @return 结果
     */
    public int updateSysOrder(SysOrder sysOrder);

    /**
     * 删除订单信息
     * 
     * @param orderId 订单信息主键
     * @return 结果
     */
    public int deleteSysOrderByOrderId(Long orderId);

    /**
     * 批量删除订单信息
     * 
     * @param orderIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysOrderByOrderIds(Long[] orderIds);
}

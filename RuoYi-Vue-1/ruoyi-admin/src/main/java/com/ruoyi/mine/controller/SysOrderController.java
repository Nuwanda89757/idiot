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
import com.ruoyi.mine.domain.SysOrder;
import com.ruoyi.mine.service.ISysOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单信息Controller
 * 
 * @author ruoyi
 * @date 2024-05-26
 */
@RestController
@RequestMapping("/mine/order_information")
public class SysOrderController extends BaseController
{
    @Autowired
    private ISysOrderService sysOrderService;

    /**
     * 查询订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('mine:order_information:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysOrder sysOrder)
    {
        startPage();
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        return getDataTable(list);
    }

    /**
     * 导出订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('mine:order_information:export')")
    @Log(title = "订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysOrder sysOrder)
    {
        List<SysOrder> list = sysOrderService.selectSysOrderList(sysOrder);
        ExcelUtil<SysOrder> util = new ExcelUtil<SysOrder>(SysOrder.class);
        util.exportExcel(response, list, "订单信息数据");
    }

    /**
     * 获取订单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('mine:order_information:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") Long orderId)
    {
        return success(sysOrderService.selectSysOrderByOrderId(orderId));
    }

    /**
     * 新增订单信息
     */
    @PreAuthorize("@ss.hasPermi('mine:order_information:add')")
    @Log(title = "订单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysOrder sysOrder)
    {
        return toAjax(sysOrderService.insertSysOrder(sysOrder));
    }

    /**
     * 修改订单信息
     */
    @PreAuthorize("@ss.hasPermi('mine:order_information:edit')")
    @Log(title = "订单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysOrder sysOrder)
    {
        return toAjax(sysOrderService.updateSysOrder(sysOrder));
    }

    /**
     * 删除订单信息
     */
    @PreAuthorize("@ss.hasPermi('mine:order_information:remove')")
    @Log(title = "订单信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable Long[] orderIds)
    {
        return toAjax(sysOrderService.deleteSysOrderByOrderIds(orderIds));
    }
}

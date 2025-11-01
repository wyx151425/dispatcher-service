package com.polarbookshop.dispatcherservice;

/**
 * @Author: WangZhenqi
 * @Description: 表示接受订单时间的 DTO
 * @Date: Created in 2025-11-01 23:04
 * @Modified By:
 */
// 包含订单标识符 (Long 类型的字段) 的 DTO
public record OrderAcceptedMessage(
        Long orderId
) {
}

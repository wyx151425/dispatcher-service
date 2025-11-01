package com.polarbookshop.dispatcherservice;

/**
 * @Author: WangZhenqi
 * @Description: 表示派送订单事件的 DTO
 * @Date: Created in 2025-11-01 23:11
 * @Modified By:
 */
// 包含订单标识符 (Long 类型的字段) 的 DTO
public record OrderDispatchedMessage(
        Long orderId
) {
}

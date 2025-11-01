package com.polarbookshop.dispatcherservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;

/**
 * @Author: WangZhenqi
 * @Description: 以函数的形式实现 “pack” 操作
 * @Date: Created in 2025-11-01 23:06
 * @Modified By:
 */
// 在配置类中定义函数
@Configuration
public class DispatchingFunctions {

    private static final Logger log = LoggerFactory.getLogger(DispatchingFunctions.class);

    // 将函数定义为 bean，以便于 Spring Cloud Function 发现和管理它们
    @Bean
    // 实现订单打包业务逻辑的函数
    public Function<OrderAcceptedMessage, Long> pack() {
        // 以 OrderAcceptedMessage 对象作为输入
        return orderAcceptedMessage -> {
            log.info("The order with id {} is packed.", orderAcceptedMessage.orderId());
            // 返回订单标识符 (Long 类型) 作为输出
            return orderAcceptedMessage.orderId();
        };
    }

    // 订单标记业务逻辑的函数实现
    @Bean
    public Function<Flux<Long>, Flux<OrderDispatchedMessage>> label() {
        // 以订单标识符 (Long) 作为输入
        return orderFlux -> orderFlux.map(orderId -> {
            log.info("The order with id {} is labeled.", orderId);
            // 返回 OrderDispatchedMessage 作为输出
            return new OrderDispatchedMessage(orderId);
        });
    }
}

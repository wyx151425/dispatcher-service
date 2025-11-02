package com.polarbookshop.dispatcherservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author: WangZhenqi
 * @Description:
 * @Date: Created in 2025-11-02 10:15
 * @Modified By:
 */
@SpringBootTest
// 配置 test binder
@Import(TestChannelBinderConfiguration.class)
class FunctionsStreamIntegrationTests {

    // 代表了输入绑定 packlabel-in-0
    @Autowired
    private InputDestination input;

    // 代表了输出绑定 packlabel-out-0
    @Autowired
    private OutputDestination output;

    // 使用 Jackson 将 JSON 消息载荷反序列化为 Java 对象
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void whenOrderAcceptedThenDispatched() throws IOException {
        long orderId = 121;
        Message<OrderAcceptedMessage> inputMessage = MessageBuilder
                .withPayload(new OrderAcceptedMessage(orderId)).build();
        Message<OrderDispatchedMessage> expectedOutputMessage = MessageBuilder
                .withPayload(new OrderDispatchedMessage(orderId)).build();

        // 发送消息至输入通道
        this.input.send(inputMessage);
        assertThat(objectMapper.readValue(output.receive().getPayload(), OrderDispatchedMessage.class))
                // 在输出通道接收消息并对其进行断言
                .isEqualTo(expectedOutputMessage.getPayload());
    }
}

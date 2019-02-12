package zool.rabbitmq.consumer.consumer;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import zool.rabbitmq.consumer.entity.Order;

import java.io.IOException;
import java.util.Map;

/**
 * @author：zoolye
 * @date：2019-02-05：21:49
 * @description：
 */

@Component
public class OrderReceiver {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue", durable = "true"),
            exchange = @Exchange(value = "order-exchange", durable = "true", type = "topic"),
            key = "order.*"))
    @RabbitHandler
    public void onOrderMessage(@Payload JSONObject object,
                               @Headers Map<String, Object> headers,
                               Channel channel) throws IOException {
        System.err.println("-------- 收到消息，开始消费 --------");

        JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(object));
        Order order = json.toJavaObject(Order.class);

        System.err.println("商品信息ID：" + order.getOrderId());

        System.err.println(order);

        Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);

        channel.basicAck(deliveryTag, false);
    }

}

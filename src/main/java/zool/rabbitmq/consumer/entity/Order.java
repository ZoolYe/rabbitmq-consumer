package zool.rabbitmq.consumer.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author：zoolye
 * @date：2019-02-05：19:32
 * @description：
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

    private static final long serialVersionUID = -3903453418424003657L;

    private Integer id;

    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;

    /**
     * 存储消息发送的唯一标识
     */
    private String messageId;

}

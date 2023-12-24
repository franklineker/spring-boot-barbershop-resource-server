package com.drnavalhabarbershop.resourceserver.mapper;

import com.drnavalhabarbershop.resourceserver.domain.Order;
import com.drnavalhabarbershop.resourceserver.web.dto.OrderRequest;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OrderMapper {

    public static Order toOrder(OrderRequest request) {

        return request != null ? Order
                .builder()
                .price(request.getPrice())
                .description(request.getDescription())
                .title(request.getTitle())
                .image(request.getImage())
                .build() : null;
    }

    public static OrderRequest toOrderRequest(Order order) {
        return OrderRequest.builder()
                .title(order.getTitle())
                .price(order.getPrice())
                .description(order.getDescription())
                .image(order.getImage())
                .build();
    }

}

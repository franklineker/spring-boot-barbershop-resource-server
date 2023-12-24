package com.drnavalhabarbershop.resourceserver.service;

import com.drnavalhabarbershop.resourceserver.domain.Order;
import com.drnavalhabarbershop.resourceserver.mapper.OrderMapper;
import com.drnavalhabarbershop.resourceserver.repository.OrderRepository;
import com.drnavalhabarbershop.resourceserver.web.dto.OrderRequest;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order save(OrderRequest request) throws IOException {
        if(Objects.equals(request.getImage(), null)) {
            byte[] array = Files.readAllBytes(Paths.get("src/main/resources/static/images/image-placeholder.png"));
            Binary defaultImage = new Binary(BsonBinarySubType.BINARY, array);
            request.setImage(defaultImage);
        }
        return orderRepository.save(OrderMapper.toOrder(request));
    }

    public Order findById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found."));
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order update(String id, OrderRequest request) {

        checkOrderExixts(id);

        Order currentOrder = findById(id);
        Binary image = request.getImage() == null ? currentOrder.getImage() : request.getImage();

        Order order = OrderMapper.toOrder((request));
        order.setId(id);
        order.setImage(image);

        return orderRepository.save(order);
    }

    public Order delete(String id) {

        checkOrderExixts(id);
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException(("Order not found.")));

        orderRepository.deleteById(id);

        return order;
    }

    private void checkOrderExixts(String id) {
        if(orderRepository.findById(id).isEmpty()){
            throw new RuntimeException("Order not found.");
        }
    }
}

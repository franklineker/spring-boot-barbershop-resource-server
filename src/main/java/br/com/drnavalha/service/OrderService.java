package br.com.drnavalha.service;

import br.com.drnavalha.domain.Order;
import br.com.drnavalha.mapper.OrderMapper;
import br.com.drnavalha.repository.OrderRepository;
import br.com.drnavalha.web.dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order save(OrderRequest request) {
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

        Order order = OrderMapper.toOrder((request));
        order.setId(id);

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

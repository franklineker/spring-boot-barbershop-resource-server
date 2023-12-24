package com.drnavalhabarbershop.resourceserver.web.controller;

import com.drnavalhabarbershop.resourceserver.domain.Order;
import com.drnavalhabarbershop.resourceserver.mapper.OrderMapper;
import com.drnavalhabarbershop.resourceserver.service.OrderService;
import com.drnavalhabarbershop.resourceserver.web.dto.OrderRequest;
import jakarta.validation.Valid;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@Validated
@CrossOrigin (origins = "*" , exposedHeaders = "**")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Order createOrder(@Valid @RequestBody OrderRequest request) throws IOException {

        return orderService.save(request);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Order findOrderById(@Valid @PathVariable String id){
        return orderService.findById(id);
    }

    @GetMapping(value = "/client")
    public String client(Authentication authentication) {
        return "Olá Sr. " + authentication.getName();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Order> findOrders(){
        return orderService.findAll();
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Order updateOrder(@Valid @PathVariable String id,
            @Valid @RequestBody OrderRequest request) {
        return orderService.update(id, request);
    }

    @PutMapping(value = "/updateImage/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public Order updateOrderImage(
            @Valid @PathVariable String id,
            @Valid @RequestParam(value = "file", required = false) Optional<MultipartFile> file) throws Exception {

        OrderRequest order = OrderMapper.toOrderRequest(orderService.findById(id));
        if(file.isPresent()){
            order.setImage(new Binary(BsonBinarySubType.BINARY,file.get().getBytes()));
        }

        return orderService.update(id, order);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public Order deleteOrder(@Valid @PathVariable String id) {

        Order order = orderService.delete(id);

        System.out.println(order);

        return order;

    }
}

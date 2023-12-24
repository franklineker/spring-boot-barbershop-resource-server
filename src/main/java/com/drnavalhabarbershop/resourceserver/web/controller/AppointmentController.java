package com.drnavalhabarbershop.resourceserver.web.controller;


import com.drnavalhabarbershop.resourceserver.domain.Appointment;
import com.drnavalhabarbershop.resourceserver.domain.Barber;
import com.drnavalhabarbershop.resourceserver.domain.Client;
import com.drnavalhabarbershop.resourceserver.domain.Order;
import com.drnavalhabarbershop.resourceserver.service.*;
import com.drnavalhabarbershop.resourceserver.web.dto.AppointmentRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/appointments")
@Validated
@CrossOrigin("*")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private BarberService barberService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/save")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Appointment createAppointment(@Valid @RequestBody AppointmentRequest request) {
        Barber barber = barberService.findById(request.getBarberId());
        Order order = orderService.findById(request.getOrderId());
        Client client = clientService.findClientById(request.getClientId());
        Date date = request.getDate();

        Appointment appointment = new Appointment();
        appointment.setBarber(barber);
        appointment.setOrder(order);
        appointment.setClient(client);
        appointment.setDate(date);
        appointment.setCreatedDate(LocalDateTime.now());
        return appointmentService.save(appointment);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Appointment> findAppointments() {
        return appointmentService.findAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Appointment findAppointmentById(@Valid @PathVariable String id) {
        return appointmentService.findById(id);
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Appointment> findAppointmentByUserId(@Valid @PathVariable String id) {

        List<Appointment> appointments = appointmentService.findByClientId(id);

        return appointments;
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Appointment updateAppointment(
            @Valid @RequestBody AppointmentRequest request,
            @Valid @PathVariable String id) {

        return appointmentService.update(request, id);
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteAppointment(@Valid @PathVariable String id) {
        appointmentService.delete(id);
    }

}

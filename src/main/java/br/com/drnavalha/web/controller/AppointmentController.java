package br.com.drnavalha.web.controller;

import br.com.drnavalha.domain.Appointment;
import br.com.drnavalha.service.AppointmentService;
import br.com.drnavalha.web.dto.AppointmentRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@Validated
@CrossOrigin("*")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping(value = "/save")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Appointment createAppointment(@Valid @RequestBody AppointmentRequest request) {

        return appointmentService.save(request);
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

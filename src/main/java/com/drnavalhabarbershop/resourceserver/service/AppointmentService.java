package com.drnavalhabarbershop.resourceserver.service;

import com.drnavalhabarbershop.resourceserver.domain.Appointment;
import com.drnavalhabarbershop.resourceserver.mapper.AppointmentMapper;
import com.drnavalhabarbershop.resourceserver.repository.AppointmentRepository;
import com.drnavalhabarbershop.resourceserver.web.dto.AppointmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ClientService clientService;

    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> findAll(){
        return appointmentRepository.findAll();
    }

    public Appointment findById(String id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Apppointment Not Found."));
    }

    public List<Appointment> findByClientId(String id) {
        String clientId = clientService.findClientById(id).getId();

        List<Appointment> appointments = appointmentRepository.findAll();
        List<Appointment> clientAppointments = appointments.stream().filter(appointment -> {
           return appointment.getClient().getId() == clientId;
        }).collect(Collectors.toList());

        return clientAppointments;
    }

    public Appointment update(AppointmentRequest request, String id) {
        checkAppointmentExist(id);

        Appointment appointment = AppointmentMapper.toAppointment(request);
        appointment.setId(id);

        return appointmentRepository.save(appointment);
    }

    public void delete(String id){

        checkAppointmentExist(id);

        appointmentRepository.deleteById(id);
    }

    private void checkAppointmentExist(String id) {
        if(appointmentRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Appointment not found.");
        }
    }
}

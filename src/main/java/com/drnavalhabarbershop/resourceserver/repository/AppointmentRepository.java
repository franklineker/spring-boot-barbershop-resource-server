package com.drnavalhabarbershop.resourceserver.repository;

import com.drnavalhabarbershop.resourceserver.domain.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends MongoRepository<Appointment, String> {

}

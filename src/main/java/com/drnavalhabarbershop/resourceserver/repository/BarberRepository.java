package com.drnavalhabarbershop.resourceserver.repository;


import com.drnavalhabarbershop.resourceserver.domain.Barber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberRepository extends MongoRepository<Barber, String> {


}

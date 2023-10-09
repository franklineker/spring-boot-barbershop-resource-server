package br.com.drnavalha.repository;

import br.com.drnavalha.domain.Barber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberRepository extends MongoRepository<Barber, String> {


}

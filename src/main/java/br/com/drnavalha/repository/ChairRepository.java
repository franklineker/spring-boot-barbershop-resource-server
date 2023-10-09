package br.com.drnavalha.repository;

import br.com.drnavalha.domain.Chair;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairRepository extends MongoRepository<Chair, String> {

}

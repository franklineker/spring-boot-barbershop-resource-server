package com.drnavalhabarbershop.resourceserver.repository;

import com.drnavalhabarbershop.resourceserver.domain.Chair;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChairRepository extends MongoRepository<Chair, String> {

}

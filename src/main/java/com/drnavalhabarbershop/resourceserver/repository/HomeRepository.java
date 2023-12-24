package com.drnavalhabarbershop.resourceserver.repository;

import com.drnavalhabarbershop.resourceserver.domain.Home;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeRepository extends MongoRepository<Home, String> {
}

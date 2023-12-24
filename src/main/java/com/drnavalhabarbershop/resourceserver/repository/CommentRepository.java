package com.drnavalhabarbershop.resourceserver.repository;

import com.drnavalhabarbershop.resourceserver.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

}

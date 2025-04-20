package com.crio.VideoRentalBasicAuthWithMongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crio.VideoRentalBasicAuthWithMongo.model.User;

public interface UserRepository extends MongoRepository<User, String> {

    User findByEmail(String email);

}

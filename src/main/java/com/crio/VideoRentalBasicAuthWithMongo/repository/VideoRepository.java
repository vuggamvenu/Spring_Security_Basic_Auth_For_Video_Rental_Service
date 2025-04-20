package com.crio.VideoRentalBasicAuthWithMongo.repository;

import com.crio.VideoRentalBasicAuthWithMongo.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface VideoRepository extends MongoRepository<Video, String> {

    List<Video> findByAvailabilityStatus(Boolean status);
}

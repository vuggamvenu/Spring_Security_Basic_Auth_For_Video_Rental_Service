package com.crio.VideoRentalBasicAuthWithMongo.service;

import com.crio.VideoRentalBasicAuthWithMongo.model.Video;
import com.crio.VideoRentalBasicAuthWithMongo.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final VideoRepository videoRepository;

    public List<Video> getAvailableVideos() {
        return videoRepository.findByAvailabilityStatus(true);
    }

    public Video addVideo(Video video) {
        video.setAvailabilityStatus(true);
        return videoRepository.save(video);
    }

    public ResponseEntity<?> updateVideo(String id, Video updatedVideo) {
        Optional<Video> existing = videoRepository.findById(id);
        if (existing.isEmpty())
            return ResponseEntity.notFound().build();

        Video video = existing.get();
        video.setTitle(updatedVideo.getTitle());
        video.setDirector(updatedVideo.getDirector());
        video.setGenre(updatedVideo.getGenre());
        video.setAvailabilityStatus(updatedVideo.isAvailabilityStatus());

        return ResponseEntity.ok(videoRepository.save(video));
    }

    public ResponseEntity<?> deleteVideo(String id) {
        if (!videoRepository.existsById(id))
            return ResponseEntity.notFound().build();
        videoRepository.deleteById(id);
        return ResponseEntity.ok("Video deleted successfully");
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }
}

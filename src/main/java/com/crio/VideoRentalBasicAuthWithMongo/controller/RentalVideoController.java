package com.crio.VideoRentalBasicAuthWithMongo.controller;

import com.crio.VideoRentalBasicAuthWithMongo.model.Video;
import com.crio.VideoRentalBasicAuthWithMongo.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class RentalVideoController {

    private final VideoService videoService;

    // Public: anyone can access
    @GetMapping("/public")
    public ResponseEntity<String> publicEndpoint() {
        return ResponseEntity.ok("This is a public endpoint. Anyone can access.");
    }

    // Private: only authenticated users can access
    @GetMapping("/available")
    public ResponseEntity<List<Video>> getAvailableVideos() {
        return ResponseEntity.ok(videoService.getAvailableVideos());
    }

    // ADMIN only: add new video
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Video> addVideo(@RequestBody Video video) {
        return ResponseEntity.ok(videoService.addVideo(video));
    }

    // ADMIN only: update video
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateVideo(@PathVariable String id, @RequestBody Video updatedVideo) {
        return videoService.updateVideo(id, updatedVideo);
    }

    // ADMIN only: delete video
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteVideo(@PathVariable String id) {
        return videoService.deleteVideo(id);
    }

    // ADMIN only: get all videos
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Video>> getAllVideos() {
        return ResponseEntity.ok(videoService.getAllVideos());
    }
}

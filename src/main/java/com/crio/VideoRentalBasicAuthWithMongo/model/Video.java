package com.crio.VideoRentalBasicAuthWithMongo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "videos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video {

    @Id
    private String id;

    private String title;
    private String director;
    private String genre;

    // e.g., "AVAILABLE FOR rent or not true or false"
    private boolean availabilityStatus;

}

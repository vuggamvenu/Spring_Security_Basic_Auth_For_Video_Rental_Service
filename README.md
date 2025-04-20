# Video Rental System (Check in Master branch)

This project implements a RESTful API for a Video Rental System using Spring Boot and MongoDB, with Basic Authentication and Authorization. It allows users to manage video rentals, including user registration, login, and video management.

## Running the Project

To run the project, follow these steps:

1. Ensure you have [MongoDB](https://www.mongodb.com/try/download/community) installed and running locally on `localhost:27017`.
2. Clone this repository to your local machine.
3. Open a terminal and navigate to the project directory.
4. Run the following command:
    ```
    ./gradlew bootrun
    ```

## AuthController

The `AuthController` class manages user authentication and registration.

- `/register`: Endpoint for user registration.
- `/login`: Endpoint for user login.

## RentalVideoController

The `RentalVideoController` class manages video-related functionality, including adding, updating, deleting, and viewing available videos.

- `/api/videos/public`: Public endpoint, anyone can access.
- `/api/videos/available`: Endpoint for retrieving available videos. Accessible by authenticated users.
- `/api/videos/add`: Endpoint for adding a new video. Only accessible by users with the ADMIN role.
- `/api/videos/update/{id}`: Endpoint for updating an existing video. Only accessible by users with the ADMIN role.
- `/api/videos/delete/{id}`: Endpoint for deleting a video. Only accessible by users with the ADMIN role.
- `/api/videos/all`: Endpoint for retrieving all videos. Only accessible by users with the ADMIN role.

## Request Objects

### AuthRequest
- `email`: User's email address.
- `password`: User's password.

### RegisterRequest
- `name`: User's name.
- `email`: User's email address.
- `password`: User's password.
- `role`: User's role. Default role is `CUSTOMER` if not specified.

### Video
- `title`: Title of the video.
- `director`: Director of the video.
- `genre`: Genre of the video.
- `availabilityStatus`: Whether the video is available for rent (`true` or `false`).

## Response Objects

### AuthResponse
- `message`: Success message.

### VideoResponse
- `id`: The unique identifier of the video.
- `title`: The title of the video.
- `director`: The director of the video.
- `genre`: The genre of the video.
- `availabilityStatus`: The availability status of the video.

## SpringsecuritydemoApplication

The main entry point of the application.

- `/`: Welcomes authenticated users.
- `/admins`: Welcomes authenticated users with the `ADMIN` role.

## PreAuthorize Annotation

The `@PreAuthorize("hasAuthority('ADMIN')")` annotation is used to restrict access to certain endpoints to users with the `ADMIN` role. For example, the `/api/videos/add`, `/api/videos/update/{id}`, and `/api/videos/delete/{id}` endpoints are restricted to admins.

## Dependencies

- Spring Boot
- Spring Security
- Spring Data MongoDB
- BCrypt for password hashing

## Troubleshooting

- Ensure MongoDB is installed and running.
- Make sure you have the correct Java version installed (Java 8 or higher).
- If you encounter any issues, check the application logs for detailed error messages.

## License

This project is licensed under the MIT License.

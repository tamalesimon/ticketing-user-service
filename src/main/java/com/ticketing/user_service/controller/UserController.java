package com.ticketing.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.user_service.entity.Role;
import com.ticketing.user_service.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/v1/users")
@Tag(name = "User", description = "API for managing users")
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @Operation(summary = "Get all users", description = "This endpoint gets all users")
    public ResponseEntity<Map<String, String>> getAllUsers() {
        Map<String, String> response = Map.of("users", this.userService.getAllUsers().toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gets user by id", description = "This endpoint gets user by id")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable String id) {
        try {
            UUID uuid = UUID.fromString(id);

            return userService.findById(uuid)
                    .map(user -> buildSuccessResponse(Map.of("user", user.toString())))
                    .orElse(buildErrorResponse(HttpStatus.NOT_FOUND, "User not found"));
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request");
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createUser(@RequestParam Map<String, Object> request) {
        try {
            String name = (String) request.get("name");
            String email = (String) request.get("email");
            String password = (String) request.get("password");
            Role role = (Role) request.get("role");
            userService.createUser(email, password, name, role);

            return buildSuccessResponse(Map.of("user", "user creation success"));
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request");
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    @PostMapping("/delete")
    @Operation(summary = "Delete user by id", description = "This endpoint deletes user by id")
    public ResponseEntity<Map<String, Object>> deleteUserById(@RequestParam Map<String, String> request) {
        try {
            UUID uuid = UUID.fromString(request.get("user_id"));
            userService.delete(uuid);
            // Check if the user exists before deleting
            if (userService.findById(uuid).isPresent()) {
                userService.delete(uuid);
                return buildSuccessResponse(Map.of("user", "User deletion was successful"));
            } else {
                return buildErrorResponse(HttpStatus.NOT_FOUND, "User you are trying to delete does not exist");
            }
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request");
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update user by id", description = "This endpoint updates user by id")
    public ResponseEntity<Map<String, Object>> updateUserById(@PathVariable String id, @RequestParam Map<String, Object> request) {
        try {
            UUID uuid = UUID.fromString(id);
            
            return userService.findById(uuid)
            .map(user -> {
                // Dynamically update fields
                request.forEach((key, value) -> {
                    try {
                        Field field = User.class.getDeclaredField(key); // Get the field by name
                        field.setAccessible(true); // Make the field accessible
                        field.set(user, convertValue(field.getType(), value)); // Set the field value
                    } catch (NoSuchFieldException e) {
                        throw new IllegalArgumentException("Invalid field: " + key);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Failed to update field: " + key, e);
                    }
                });

                // Save the updated user
                userService.save(user);

                return buildSuccessResponse(Map.of("user", "User updated successfully"));
            })
            .orElse(buildErrorResponse(HttpStatus.NOT_FOUND, "User not found"));
        } catch (IllegalArgumentException e) {
            return buildErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request");
        } catch (Exception e) {
            return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
        }
    }

    // @GetMapping("/{email}")
    // @Operation(summary = "Gets user by email", description = "This endpoint gets
    // user by email")
    // public ResponseEntity<Map<String, String>> getUserByEmail(@PathVariable
    // String email) {
    // Map<String, String> response = Map.of("user",
    // this.userService.getUserByEmail(email).toString());
    // return ResponseEntity.ok(response);

    // }

    // @PostMapping("/create")
    // @Operation(summary = "Create new user", description = "This endpoint creates
    // new user")
    // public ResponseEntity createUser(@RequestParam Object request) {
    // String name = (String) request.get("name");
    // String email = (String) request.get("email");
    // String password = (String) request.get("password");
    // Role role = (Role) request.get("role");
    // this.userService.createUser(name, email, password, role);
    // return ResponseEntity.ok("User created");
    // }

    private ResponseEntity<Map<String, Object>> buildSuccessResponse(Map<String, Object> body) {
        return ResponseEntity.ok(body);
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(Map.of(
                        "error", message,
                        "status", status.value(),
                        "timeStamp", new Date(System.currentTimeMillis())));
    }

}

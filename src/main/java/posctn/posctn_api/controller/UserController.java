package posctn.posctn_api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import posctn.posctn_api.dto.ApiResponseDto;
import posctn.posctn_api.dto.request.UserRequestDto;
import posctn.posctn_api.dto.response.UserResponseDto;
import posctn.posctn_api.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "User Management System", description = "Endpoints for managing user staff accounts and profiles")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<UserResponseDto>>> getUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        ApiResponseDto<List<UserResponseDto>> response = new ApiResponseDto<>(200,
                "Data staff berhasil diambil", users);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> createUser(
            @Valid @RequestBody UserRequestDto request) {
        UserResponseDto userResponseDto = userService.createUser(request);
        ApiResponseDto<UserResponseDto> response = new ApiResponseDto<>(200,
                "Staff ditambahkan", userResponseDto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDto request) {
        UserResponseDto userResponseDto = userService.updateUser(id, request);
        ApiResponseDto<UserResponseDto> response = new ApiResponseDto<>(200,
                "Staff dengan nama " + request.getFullname() + " berhasil di perbarui", userResponseDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        ApiResponseDto<Void> response = new ApiResponseDto<>(200,
                "Staff dengan id " + id + " berhasil dihapus", null);
        return ResponseEntity.ok(response);
    }
}

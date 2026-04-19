package posctn.posctn_api.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import posctn.posctn_api.dto.ApiResponseDto;
import posctn.posctn_api.dto.request.UserCreateRequestDto;
import posctn.posctn_api.dto.response.UserResponseDto;
import posctn.posctn_api.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<UserResponseDto>>> getUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        ApiResponseDto<List<UserResponseDto>> response = new ApiResponseDto<>(200, "Success", users);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create-user")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> createUser(
            @Valid @RequestBody UserCreateRequestDto request) {
        UserResponseDto userResponseDto = userService.createUser(request);
        ApiResponseDto<UserResponseDto> response = new ApiResponseDto<>(200, "Success", userResponseDto);
        return ResponseEntity.ok(response);
    }
}

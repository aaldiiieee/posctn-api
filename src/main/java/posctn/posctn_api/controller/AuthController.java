package posctn.posctn_api.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import posctn.posctn_api.dto.request.LoginRequestDto;
import posctn.posctn_api.dto.response.ApiResponseDto;
import posctn.posctn_api.dto.response.LoginResponseDto;
import posctn.posctn_api.dto.response.UserResponseDto;
import posctn.posctn_api.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "Endpoints for authentication")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<LoginResponseDto>> login(
            @RequestBody LoginRequestDto request) {
        LoginResponseDto data = authService.login(request);
        ApiResponseDto<LoginResponseDto> response = new ApiResponseDto<>(
                200, "Login berhasil", data);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponseDto<UserResponseDto>> getCurrentUser(
            @AuthenticationPrincipal UserDetails userDetails) {
        UserResponseDto data = authService.getCurrentUser(userDetails.getUsername());
        ApiResponseDto<UserResponseDto> response = new ApiResponseDto<>(
                200, "User berhasil", data
        );
        return ResponseEntity.ok(response);
    }
}

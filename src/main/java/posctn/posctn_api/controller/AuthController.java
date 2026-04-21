package posctn.posctn_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import posctn.posctn_api.dto.request.LoginRequestDto;
import posctn.posctn_api.dto.response.ApiResponseDto;
import posctn.posctn_api.dto.response.LoginResponseDto;
import posctn.posctn_api.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
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
}

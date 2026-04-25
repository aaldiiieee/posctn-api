package posctn.posctn_api.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import posctn.posctn_api.config.JwtConfig;
import posctn.posctn_api.dto.request.LoginRequestDto;
import posctn.posctn_api.dto.response.LoginResponseDto;
import posctn.posctn_api.dto.response.UserResponseDto;
import posctn.posctn_api.exception.AuthenticationFailedException;
import posctn.posctn_api.exception.NotFoundException;
import posctn.posctn_api.mapper.UserMapper;
import posctn.posctn_api.model.UserModel;
import posctn.posctn_api.repository.UserRepository;
import posctn.posctn_api.service.AuthService;
import posctn.posctn_api.util.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final JwtConfig jwtConfig;
    private final UserMapper userMapper;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtUtil jwtUtil,
                           UserRepository userRepository, JwtConfig jwtConfig, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.jwtConfig = jwtConfig;
        this.userMapper = userMapper;
    }

    @Override
    public LoginResponseDto login(LoginRequestDto request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()));
        } catch (AuthenticationException e) {
            throw new AuthenticationFailedException("Username atau password salah");
        }

        UserModel user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));

        String role = String.valueOf(user.getRole());
        String token = jwtUtil.generateToken(
                user.getId(), user.getUsername(), role, user.getFullname());

        return new LoginResponseDto(
                token,
                "Bearer",
                jwtConfig.getExpiration()
        );
    }

    @Override
    public UserResponseDto getCurrentUser(String username) {
        UserModel user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User tidak ditemukan"));

        return userMapper.toDto(user);
    }
}

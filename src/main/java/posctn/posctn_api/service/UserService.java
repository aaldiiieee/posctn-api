package posctn.posctn_api.service;

import org.springframework.stereotype.Service;
import posctn.posctn_api.dto.request.UserCreateRequestDto;
import posctn.posctn_api.dto.response.UserResponseDto;

import java.util.List;

@Service
public interface UserService {

    /**
     * Get all users data
     */
    List<UserResponseDto> getAllUsers();

    /**
     * Create user
     */
    UserResponseDto createUser(UserCreateRequestDto request);

    /**
     * Update user
     */
    UserResponseDto updateUser(UserCreateRequestDto request);
}

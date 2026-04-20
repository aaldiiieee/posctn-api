package posctn.posctn_api.service;

import org.springframework.stereotype.Service;
import posctn.posctn_api.dto.request.UserRequestDto;
import posctn.posctn_api.dto.response.UserResponseDto;

import java.util.List;

@Service
public interface UserService {

    /**
     * Get all users
     *
     * @return
     */
    List<UserResponseDto> getAllUsers();

    /**
     * Create user data
     *
     * @param request
     * @return
     */
    UserResponseDto createUser(UserRequestDto request);

    /**
     * Update user
     *
     * @param userId
     * @param request
     * @return
     */
    UserResponseDto updateUser(Long userId, UserRequestDto request);

    /**
     * Delete user
     *
     * @param userId
     */
    void deleteUser(Long userId);
}

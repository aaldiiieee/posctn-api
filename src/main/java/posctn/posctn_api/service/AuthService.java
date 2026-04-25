package posctn.posctn_api.service;

import posctn.posctn_api.dto.request.LoginRequestDto;
import posctn.posctn_api.dto.response.LoginResponseDto;
import posctn.posctn_api.dto.response.UserResponseDto;

public interface AuthService {

    /**
     * Login user
     *
     * @param loginRequestDto
     * @return
     */
    LoginResponseDto login(LoginRequestDto loginRequestDto);

    /**
     * Get current user
     *
     * @param username
     * @return
     */
    UserResponseDto getCurrentUser(String username);
}

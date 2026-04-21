package posctn.posctn_api.service;

import posctn.posctn_api.dto.request.LoginRequestDto;
import posctn.posctn_api.dto.response.LoginResponseDto;

public interface AuthService {

    /**
     * Login user
     *
     * @param loginRequestDto
     * @return
     */
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}

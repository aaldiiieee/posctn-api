package posctn.posctn_api.mapper;

import org.springframework.stereotype.Component;
import posctn.posctn_api.dto.request.UserCreateRequestDto;
import posctn.posctn_api.dto.response.UserResponseDto;
import posctn.posctn_api.model.UserModel;

@Component
public class UserMapper {

    public UserResponseDto toDto(UserModel userModel) {
        if (userModel == null) return null;

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(userModel.getId());
        userResponseDto.setFullname(userModel.getFullname());
        userResponseDto.setUsername(userModel.getUsername());
        userResponseDto.setRole(userModel.getRole());
        userResponseDto.setActive(userModel.isActive());
        userResponseDto.setCreatedAt(userModel.getCreatedAt());

        return userResponseDto;
    }

    public UserModel toEntity(UserCreateRequestDto request) {
        if (request == null) return null;

        UserModel userModel = new UserModel();
        userModel.setFullname(request.getFullname());
        userModel.setUsername(request.getUsername());
        userModel.setPassword(request.getPassword());
        userModel.setRole(request.getRole());
        userModel.setActive(request.active);

        return userModel;
    }
}

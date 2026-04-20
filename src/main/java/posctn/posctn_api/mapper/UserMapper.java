package posctn.posctn_api.mapper;

import org.springframework.stereotype.Component;
import posctn.posctn_api.dto.request.UserRequestDto;
import posctn.posctn_api.dto.response.UserResponseDto;
import posctn.posctn_api.model.UserModel;

@Component
public class UserMapper {

    public UserResponseDto toDto(UserModel userModel) {
        if (userModel == null) return null;

        UserResponseDto response = new UserResponseDto();
        response.setId(userModel.getId());
        response.setFullname(userModel.getFullname());
        response.setUsername(userModel.getUsername());
        response.setRole(userModel.getRole());
        response.setActive(userModel.isActive());
        response.setCreatedAt(userModel.getCreatedAt());

        return response;
    }

    public UserModel toEntity(UserRequestDto request) {
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

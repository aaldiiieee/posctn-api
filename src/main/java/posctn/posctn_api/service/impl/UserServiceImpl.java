package posctn.posctn_api.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import posctn.posctn_api.dto.request.UserCreateRequestDto;
import posctn.posctn_api.dto.response.UserResponseDto;
import posctn.posctn_api.enums.RoleEnum;
import posctn.posctn_api.exception.AlreadyExistsException;
import posctn.posctn_api.mapper.UserMapper;
import posctn.posctn_api.model.UserModel;
import posctn.posctn_api.repository.UserRepository;
import posctn.posctn_api.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Override
    public UserResponseDto createUser(UserCreateRequestDto request) {
        UserModel user = userMapper.toEntity(request);

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new AlreadyExistsException("Username sudah terdaftar, coba masukan username lain");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserModel createdUser = userRepository.save(user);
        return userMapper.toDto(createdUser);
    }


}

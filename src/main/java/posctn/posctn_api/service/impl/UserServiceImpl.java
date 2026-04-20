package posctn.posctn_api.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import posctn.posctn_api.dto.request.UserRequestDto;
import posctn.posctn_api.dto.response.UserResponseDto;
import posctn.posctn_api.exception.AlreadyExistsException;
import posctn.posctn_api.exception.NotFoundException;
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
    public UserResponseDto createUser(UserRequestDto request) {
        UserModel user = userMapper.toEntity(request);

        if (userRepository.existsByUsername(user.getUsername())) {
            throw new AlreadyExistsException("Username sudah terdaftar, coba masukan username lain");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserModel createdUser = userRepository.save(user);
        return userMapper.toDto(createdUser);
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserRequestDto request) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Staff tidak ditemukan dengan id" + userId));

        if (!user.getUsername().equals(request.getUsername()) &&
                userRepository.existsByUsername(request.getUsername())) {
            throw new AlreadyExistsException("Username staff sudah ada");
        }

        user.setFullname(request.getFullname());
        user.setUsername(request.getUsername());
        user.setRole(request.getRole());
        user.setActive(request.isActive());

        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        UserModel updatedUser = userRepository.save(user);

        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new NotFoundException("Staff tidak ditemukan dengan id " + userId);
        }

        userRepository.deleteById(userId);
    }
}

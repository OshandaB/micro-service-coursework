package lk.ijse.gdse.highwayTicketSystem.user_service.service.impl;

import lk.ijse.gdse.highwayTicketSystem.user_service.dto.UserDTO;
import lk.ijse.gdse.highwayTicketSystem.user_service.entity.User;
import lk.ijse.gdse.highwayTicketSystem.user_service.repository.UserRepository;
import lk.ijse.gdse.highwayTicketSystem.user_service.service.UserService;
import lk.ijse.gdse.highwayTicketSystem.user_service.service.exception.DuplicateRecordException;
import lk.ijse.gdse.highwayTicketSystem.user_service.service.exception.InvalidCredentialException;
import lk.ijse.gdse.highwayTicketSystem.user_service.service.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    PasswordEncoder passwordEncoder;



    @Override
    public List<UserDTO> getAllUsers() {
        return List.of();
    }

    @Override
    public void createUser(UserDTO userDTO) {
        String userDTOId = userDTO.getId();
        if (userDTOId != null && userRepository.existsById(userDTOId)) {
            throw new DuplicateRecordException("User with ID " + userDTOId + " already exists.");
        }
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        String userDTOId = userDTO.getId();
        if (!userRepository.existsById(userDTOId)) {
            throw new NotFoundException("User with ID " + userDTOId + " does not exist.");
        }

        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);

    }

    @Override
    public UserDTO checkCredentials(String username, String password) {
        Optional<User> userOpt = userRepository.findByEmail(username);
        if (userOpt.isEmpty()) {
            throw new NotFoundException("User not found with username: " + username);
        }
        User user = userOpt.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialException("Invalid password for username: " + username);
        }
       return modelMapper.map(user, UserDTO.class);

    }

    @Override
    public UserDTO findUser(String id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new NotFoundException("User not found with ID: " + id);
        }
        return modelMapper.map(userOpt.get(), UserDTO.class);
    }
}

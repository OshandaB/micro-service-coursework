package lk.ijse.gdse.highwayTicketSystem.user_service.service;

import lk.ijse.gdse.highwayTicketSystem.user_service.dto.UserDTO;
import lk.ijse.gdse.highwayTicketSystem.user_service.entity.User;

import java.util.List;

public interface UserService {

    public List<UserDTO> getAllUsers();
    public  void createUser(UserDTO userDTO);
    public UserDTO updateUser(UserDTO userDTO);
    public UserDTO checkCredentials(String username, String password);

    UserDTO findUser(String id);

}

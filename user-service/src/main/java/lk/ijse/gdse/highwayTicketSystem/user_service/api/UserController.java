package lk.ijse.gdse.highwayTicketSystem.user_service.api;

import jakarta.validation.Valid;
import lk.ijse.gdse.highwayTicketSystem.user_service.dto.ResponseDTO;
import lk.ijse.gdse.highwayTicketSystem.user_service.dto.SignInRequest;
import lk.ijse.gdse.highwayTicketSystem.user_service.dto.UserDTO;
import lk.ijse.gdse.highwayTicketSystem.user_service.service.UserService;
import lk.ijse.gdse.highwayTicketSystem.user_service.service.exception.InvalidCredentialException;
import lk.ijse.gdse.highwayTicketSystem.user_service.service.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseDTO register(@RequestBody @Valid UserDTO userDTO) {
        userService.createUser(userDTO);
        return new ResponseDTO(HttpStatus.CREATED, "User registered successfully", null);
    }
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO login(@RequestBody SignInRequest signInRequest) {

            UserDTO userDTO = userService.checkCredentials(signInRequest.getEmail(), signInRequest.getPassword());
            return new ResponseDTO(HttpStatus.valueOf(200),"User login successful!", userDTO);

    }
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDTO update(@RequestBody @Valid UserDTO userDTO) {
        UserDTO updateUser = userService.updateUser(userDTO);
        return new ResponseDTO(HttpStatus.valueOf(204), "User updated successfully", updateUser);
    }
    @GetMapping(value = "/findUser/{id}")
    public ResponseDTO findUser(@PathVariable String id) {
        UserDTO userDTO = userService.findUser(id);
        return new ResponseDTO(HttpStatus.valueOf(200), "User found successfully", userDTO);
    }
}

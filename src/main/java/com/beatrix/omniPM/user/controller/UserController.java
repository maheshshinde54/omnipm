package com.beatrix.omniPM.user.controller;

import com.beatrix.omniPM.user.dto.UserDTO;
import com.beatrix.omniPM.user.exceptions.ResourceNotFoundException;
import com.beatrix.omniPM.user.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/user")
public class UserController
{
    UserService userService;

    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId)
    {
        return userService.getUserById(userId)
                .map(ResponseEntity::ok)
        .orElseThrow(()->new ResourceNotFoundException("User not found with id : "+userId));
    }

    @GetMapping(path = "/all")
    public List<UserDTO> getAllUsers()
    {
        return userService.getAllUser();
    }

    @PostMapping("/add")
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserDTO userDTO)
    {
        //return new UserDTO(1L, "Mahesh", "mahesh@gamil.com", 30, LocalDate.of(2024, 11, 8), true);
        UserDTO savedUser = userService.addNewUser(userDTO);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId)
    {
        String deletedUser = userService.deleteUser(userId);
        return new ResponseEntity<>(deletedUser, HttpStatus.OK);
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO,
                                              @PathVariable Long userId)
    {
        UserDTO updatedUser = userService.updateWholeUser(userDTO, userId);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }




}

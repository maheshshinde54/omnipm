package com.beatrix.omniPM.user.services;

import com.beatrix.omniPM.user.dto.UserDTO;
import com.beatrix.omniPM.user.entity.UserEntity;
import com.beatrix.omniPM.user.exceptions.ResourceNotFoundException;
import com.beatrix.omniPM.user.respositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository,
                       ModelMapper modelMapper)
    {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void isExist(Long userId)
    {
        boolean userCheck = userRepository.existsById(userId);
        if (!userCheck)
            throw new ResourceNotFoundException("Employee not found with id: " + userId);
    }

    public List<UserDTO> getAllUser()
    {
        return userRepository.findAll()
                             .stream()
                             .map(UserEntity -> modelMapper.map(UserEntity, UserDTO.class))
                             .toList();
    }


    public Optional<UserDTO> getUserById(Long userId)
    {
        isExist(userId);
        return userRepository.findById(userId)
                             .map(UserEntity -> modelMapper.map(UserEntity, UserDTO.class));
    }

    public UserDTO addNewUser(UserDTO inputUserDTO)
    {
        UserEntity toSaveNewUser = modelMapper.map(inputUserDTO, UserEntity.class);
        UserEntity savedUser = userRepository.save(toSaveNewUser);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    public String deleteUser(Long userId)
    {
        //isExist(userId);
        userRepository.deleteById(userId);
        return ("User deleted with id : " + userId);
    }

    public UserDTO updateWholeUser(UserDTO userDTO,
                                   Long userId)
    {
        //isExist(userId);
        UserEntity userToBeUpdate = userRepository.findById(userId)
                                                  .orElseThrow();

        UserEntity updatedUser = userRepository.save(modelMapper.map(userDTO,UserEntity.class));
        return modelMapper.map(updatedUser, UserDTO.class);
    }
}


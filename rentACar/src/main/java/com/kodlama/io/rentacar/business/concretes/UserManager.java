package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.UserService;
import com.kodlama.io.rentacar.business.dto.responses.get.GetUserResponse;
import com.kodlama.io.rentacar.entities.User;
import com.kodlama.io.rentacar.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserManager(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User getByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
       // GetUserResponse getUserResponse = modelMapper.map(user, GetUserResponse.class);

        return user;
    }

    @Override
    public User getByResetToken(String token) {
        User user = userRepository.findByResetToken(token).orElseThrow();
        return user;
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }
}

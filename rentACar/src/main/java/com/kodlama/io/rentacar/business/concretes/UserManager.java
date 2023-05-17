package com.kodlama.io.rentacar.business.concretes;

import com.kodlama.io.rentacar.business.abstracts.UserService;
import com.kodlama.io.rentacar.business.dto.responses.get.GetUserResponse;
import com.kodlama.io.rentacar.business.rules.UserBusinessRules;
import com.kodlama.io.rentacar.core.exceptions.BusinessException;
import com.kodlama.io.rentacar.entities.User;
import com.kodlama.io.rentacar.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManager implements UserService {
    private final UserRepository userRepository;

    private final UserBusinessRules userBusinessRules;
    @Override
    public User getByEmail(String email) {
        userBusinessRules.checkIfUserExistsByEmail(email);
        User user = userRepository.findByEmail(email).orElseThrow();

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

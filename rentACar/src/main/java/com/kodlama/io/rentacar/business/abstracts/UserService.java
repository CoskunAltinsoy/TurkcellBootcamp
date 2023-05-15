package com.kodlama.io.rentacar.business.abstracts;

import com.kodlama.io.rentacar.business.dto.responses.get.GetRoleResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetUserResponse;
import com.kodlama.io.rentacar.entities.User;

public interface UserService {
    User getByEmail(String email);
    User getByResetToken(String token);
    void add(User user);
}

package com.kodlama.io.rentacar.business.abstracts;

import com.kodlama.io.rentacar.business.dto.requests.create.CreateRoleRequest;
import com.kodlama.io.rentacar.business.dto.requests.update.UpdateRoleRequest;
import com.kodlama.io.rentacar.business.dto.responses.create.CreateRoleResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetAllRolesResponse;
import com.kodlama.io.rentacar.business.dto.responses.get.GetRoleResponse;
import com.kodlama.io.rentacar.business.dto.responses.update.UpdateRoleResponse;

import java.util.List;

public interface RoleService {
    List<GetAllRolesResponse> getAll();
    GetRoleResponse getById(int id);
    CreateRoleResponse add(CreateRoleRequest createRoleRequest);
    UpdateRoleResponse update(UpdateRoleRequest updateRoleRequest);
    void delete(int id);

}

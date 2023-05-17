package leave.mgt.service;

import leave.mgt.model.Users;

import java.util.List;

public interface UsersService {
    Users registerUser(Users users);
    Users updateUser(Users users);
    Users voidUser(Users users);
    List<Users> allRegisterUser(Users users);

    Users searchUserById(int parseInt);
}

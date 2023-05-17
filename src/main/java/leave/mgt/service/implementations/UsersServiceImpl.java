package leave.mgt.service.implementations;

import leave.mgt.model.Users;
import leave.mgt.repository.UsersRepository;
import leave.mgt.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired private UsersRepository repo;

    @Override
    public Users registerUser(Users users) {
        users.setActive(true);
        return repo.save(users);
    }

    @Override
    public Users updateUser(Users users) {
        return repo.save(users);
    }

    @Override
    public Users voidUser(Users users) {
        users.setActive(false);
        return repo.save(users);
    }

    @Override
    public List<Users> allRegisterUser(Users users) {
        return repo.findAll();
    }

    @Override
    public Users searchUserById(int id) {
        return repo.findById(id).get();
    }
}

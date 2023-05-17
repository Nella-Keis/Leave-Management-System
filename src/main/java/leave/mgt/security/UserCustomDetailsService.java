package leave.mgt.security;


import leave.mgt.model.Users;
import leave.mgt.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserCustomDetailsService implements UserDetailsService {
    @Autowired private UsersRepository repo;
//    private UserServiceImpl userService;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Users user  = new Users();
        if(email != null){
            System.out.println("AM IN !!!!!!!!!>>>> Security");
            user = repo.findByEmail(email);
        }
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
        return new UserCustomDetails(user);
    }
}

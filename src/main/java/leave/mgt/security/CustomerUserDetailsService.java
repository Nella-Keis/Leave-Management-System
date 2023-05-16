package leave.mgt.security;


import leave.mgt.model.Users;
import leave.mgt.repository.UsersRepository;
import leave.mgt.service.implementations.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersServiceImpl userRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Users user = userRepo.getUserByUsername(username);
//        System.out.println("=====================DEBUG===================");
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
//        System.out.println(user.hasRole("Manager"));
//        if(user !=null){
//            System.out.println("=============Reached on Customer details ready to load custo one=========");
//            return new CustomerUserDetails(user);
//        }
        throw  new UsernameNotFoundException("Could not find user by username: "+username);
    }

}

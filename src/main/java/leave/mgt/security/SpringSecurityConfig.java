package leave.mgt.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

public class SpringSecurityConfig {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .usernameParameter("username")
//                .successHandler(loginSuccessHandler)
//                .permitAll()
//                .and()
//                .logout().permitAll();
//    }
//
//    @Autowired
//    private LoginSuccessHandler loginSuccessHandler;
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new CustomerUserDetailsService();
//    }
}

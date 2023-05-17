package leave.mgt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        try{
            http.authorizeRequests()
                    .antMatchers("/home").hasAnyAuthority("HR")
                    .antMatchers("/").hasAnyAuthority("HR")
                    .antMatchers("/employeeProfile").hasAnyAuthority("HR","EMPLOYEE")
                    .antMatchers("/all_employee").hasAnyAuthority("HR")
                    .antMatchers("/all-employee-leave").hasAnyAuthority("HR")
                    .anyRequest().permitAll()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .successHandler(loginSuccessHandler)
                    .permitAll()
                    .and()
                    .logout().permitAll()
                    .and()
                    .rememberMe()
                    .key("123456789_abcdefghijkLMnopQRstuvWxYZ")
                    .tokenValiditySeconds(14 * 24 * 60 *60)
            ;
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/images/**","/js/**","/webjars/**");
    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new CustomerUserDetailsService();
//    }
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserCustomDetailsService();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}

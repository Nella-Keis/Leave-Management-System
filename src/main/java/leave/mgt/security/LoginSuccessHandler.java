package leave.mgt.security;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        CustomerUserDetails userDetails = (CustomerUserDetails) authentication.getPrincipal();

        System.out.println("=============Reached on successHandler=========");
        String redirectURL = request.getContextPath();

        if (userDetails.hasRole("Manager")) {
            redirectURL = "manager_drinks";
        } else if (userDetails.hasRole("Barman")) {
            redirectURL = "barman";
        } else if (userDetails.hasRole("Kitchen")) {
            redirectURL = "kitchen";
        }
        response.sendRedirect(redirectURL);
    }

}
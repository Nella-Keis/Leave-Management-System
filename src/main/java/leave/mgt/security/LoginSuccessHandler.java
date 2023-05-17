package leave.mgt.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        UserCustomDetails userDetails = (UserCustomDetails) authentication.getPrincipal();

        System.out.println("=============Reached on successHandler=========");
        String redirectURL = request.getContextPath();

        if (userDetails.hasRole("ADMIN")) {
            redirectURL = "";
        } else if (userDetails.hasRole("EMPLOYEE")) {
            redirectURL = "employeeProfile";
        } else if (userDetails.hasRole("HR")) {
            redirectURL = "all_employee";
        }
        response.sendRedirect(redirectURL);
    }
}

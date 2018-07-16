package dk.eplusi.dbHelper.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// handle custom403.html page
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static Logger logger = LoggerFactory.getLogger(CustomAccessDeniedHandler.class);

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException {

        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            logger.warn("User '" + auth.getName()
                    + "' attempted to access the protected URL: "
                    + request.getRequestURI());
        }

        logger.warn("Error: " + e.getMessage());
        response.sendRedirect(request.getContextPath() + "/403");
    }
}

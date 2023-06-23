package ge.shota.blog.service;

import ge.shota.blog.exception.GeneralException;
import ge.shota.blog.storage.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Service
@Slf4j
public class AuthenticationService implements HandlerInterceptor {


    public User getUser() {

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                return (User) authentication.getPrincipal();
            } else {
                throw new GeneralException.ProfileNotFound();
            }
        }catch (Exception e) {
            e.getStackTrace();
            log.error("Unknown error while getting user: {}", e.getMessage());
        }

        return null;

    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getPrincipal() instanceof User) {
                User user = (User) authentication.getPrincipal();
                return user != null;
            } else {
                throw new GeneralException.ProfileNotFound();
            }

        }catch (Exception e) {
            e.getStackTrace();
            log.error("Unknown error while getting user: {}", e.getMessage());
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // This method is called after the request has been processed by the controller
        // You can add additional logic if needed
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // This method is called after the complete request has finished, including view rendering
        // You can add additional logic if needed
    }
}

package xyz.fmcy.foh.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import xyz.fmcy.foh.reflect.annotation.WebInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 付高宏
 * @see xyz.fmcy.foh.config.WebInterceptorConfig
 */

@WebInterceptor(
        pathPatterns = "/*",
        exclude = {"/login", "/user/login", "/user/register", "/register"}
)
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }
        return true;
    }
}

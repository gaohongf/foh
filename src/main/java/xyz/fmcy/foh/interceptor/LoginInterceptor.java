package xyz.fmcy.foh.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import xyz.fmcy.foh.reflect.interceptor.annotation.WebInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 付高宏
 * @see xyz.fmcy.foh.config.WebInterceptorConfig
 */
@WebInterceptor(
        pathPatterns = {"/*","/avatar/**","/user/**","/topic/**"},
        exclude = {"/welcome","/login", "/user/sign/*", "/register"}
)
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/welcome");
            return false;
        }
        return true;
    }
}

package xyz.fmcy.foh.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import xyz.fmcy.foh.reflect.interceptor.annotation.WebInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 付高宏
 * @date 2022/6/19 16:35
 * 拦截服务器特地隐藏的一些页面请求
 * 例如一些模板
 */
@WebInterceptor(
        pathPatterns = {"/hide/**"},
        exclude = {}
)
public class HidePageInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return false;
    }
}

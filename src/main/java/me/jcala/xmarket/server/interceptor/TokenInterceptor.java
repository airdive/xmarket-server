package me.jcala.xmarket.server.interceptor;

import me.jcala.xmarket.server.entity.configuration.ApplicationInfo;
import me.jcala.xmarket.server.entity.configuration.TokenVerifyResult;
import me.jcala.xmarket.server.utils.CustomValidator;
import me.jcala.xmarket.server.utils.RespFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * 用于验证JWT Token
 */
@Service
public class TokenInterceptor implements HandlerInterceptor {

    private ApplicationInfo info;

    @Autowired
    public TokenInterceptor(ApplicationInfo info) {
        this.info = info;
    }

    public TokenInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

         String jwt=request.getHeader("x-access-token");
         if (jwt==null){
             response.setStatus(401);
             return false;
         }

         TokenVerifyResult result=CustomValidator.JwtVerify(info.getJwtKey(),jwt);
         if (result==TokenVerifyResult.success){
             return true;
         }else if (result==TokenVerifyResult.expired){
             response.setContentType("application/json;charset=UTF-8");
             response.setStatus(200);
             response.getWriter().write("{\"code\":101,\"msg\":\"token过期\",\"data\":null}");
            return false;
        }else {
             response.setStatus(401);
             return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
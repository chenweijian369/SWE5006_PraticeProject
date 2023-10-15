package com.nus.interceptor;

import com.nus.constant.JwtClaimsConstant;
import com.nus.constant.LoginConstant;
import com.nus.context.BaseContext;
import com.nus.properties.JwtProperties;
import com.nus.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;

/**
 * jwt token interceptor
 */
@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * check jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //1、get token from request head
        String token = request.getHeader(jwtProperties.getTokenName());

        //2、check token
        try {
            log.info("Check JWT: {}", token);

            Claims claims = JwtUtil.parseJWT(jwtProperties.getSecretKey(), token);
            Iterator<String> iterator = claims.keySet().iterator();
            String type = iterator.hasNext() ? iterator.next() : null;

            if (type.equals(LoginConstant.ADMIN_LOGIN) || type.equals(LoginConstant.USER_LOGIN) || type.equals(LoginConstant.CHEF_LOGIN)){
                setCurrentUserIdWhenJWT(claims, type);
            }

            //3、pass
            return true;
        } catch (Exception ex) {
            //4、not pass
            log.info("Invalid JWT");
            response.setStatus(401);
            return false;
        }
    }

    public void setCurrentUserIdWhenJWT(Claims claims, String type){
        Long id = Long.valueOf(claims.get(type).toString());
        log.info("Current Person ID：{}", id);
        BaseContext.setCurrentId(id);
    }

}

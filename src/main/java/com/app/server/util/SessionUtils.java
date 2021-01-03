package com.app.server.util;

import com.app.server.model.User;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class SessionUtils {

    // 鉴权类型
    @Value("${auth.type}")
    private String authType;

    // 超时时间
    @Value("${server.servlet.session.timeout2}")
    public int EXPIRE_TIME;

    // jwt对象
    @Resource
    private JWTUtils jwtUtils;

    // session key
    private final String SESSION_USER_KEY = "SESSION_USER";

    private static final String USER_HEADER = "USER_HEADER";

    // 缓存 (模仿redis)
    private final Cache<Object, Object> guavaCache = CacheBuilder.newBuilder()
            .expireAfterAccess(EXPIRE_TIME, TimeUnit.MINUTES)
            .build();

    // 保存当前登录用户
    public String saveAuth(User value) {
        if (AuthTypes.COOKIE.getType().equals(authType)) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            servletRequestAttributes.getRequest().getSession().setAttribute(SESSION_USER_KEY, value);
            return servletRequestAttributes.getSessionId();
        }
        if (AuthTypes.TOKEN.getType().equals(authType)) {
            // 生成token令牌
            String token = UUIDUtils.generateUuid();
            // 存入token
            guavaCache.put(token, value); ;
            return token;
        }
        if (AuthTypes.JWT.getType().equals(authType)) {
            return jwtUtils.getToken(value);
        }
        return null;
    }

    // 返回当前登录用户
    public User getAuth() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if (AuthTypes.COOKIE.getType().equals(authType)) {
            return (User)servletRequestAttributes.getRequest().getSession().getAttribute(SESSION_USER_KEY);
        }
        if (AuthTypes.TOKEN.getType().equals(authType)) {
            return (User) guavaCache.getIfPresent(servletRequestAttributes.getRequest().getHeader(USER_HEADER));
        }
        if (AuthTypes.JWT.getType().equals(authType)) {
            return jwtUtils.checkToken(servletRequestAttributes.getRequest().getHeader(USER_HEADER));
        }
        return null;
    }

}

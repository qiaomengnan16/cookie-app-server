package com.app.server.service;

import com.app.server.util.AuthTypes;
import com.app.server.util.UUIDUtils;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

@Service
public class SessionService {

    @Value("${auth.type}")
    private String authType;


    /**
     * @Fields : token超时时间
     * @author qiaomengnan
     */
    public static final int EXPIRE_TIME = 30 * 60;

    private final Cache<Object, Object> guavaCache = CacheBuilder.newBuilder()
            .expireAfterAccess(EXPIRE_TIME, TimeUnit.SECONDS)
            .build();

    public void save(Object key, Object value) {
        guavaCache.put(key, value);
    }

    public Object get(Object key) {
        return guavaCache.getIfPresent(key);
    }

    public void delete(Object key) {
        guavaCache.put(key, null);
    }

    public String saveAuth(Object value) {
        if (AuthTypes.COOKIE.getType().equals(authType)) {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            String sessionId = servletRequestAttributes.getSessionId();
            guavaCache.put(servletRequestAttributes.getSessionId(), value); ;
            return sessionId;
        }
        if (AuthTypes.TOKEN.getType().equals(authType)) {
            // 生成token令牌
            String token = UUIDUtils.generateUuid();
            // 存入token
            guavaCache.put(token, value); ;
            return token;
        }
        if (AuthTypes.JWT.getType().equals(authType)) {

            return null;
        }
        return null;
    }

}

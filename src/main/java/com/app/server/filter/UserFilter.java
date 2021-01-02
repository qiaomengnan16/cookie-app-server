package com.app.server.filter;

import com.alibaba.fastjson.JSON;
import com.app.server.model.User;
import com.app.server.util.SessionUtils;
import com.app.server.util.UrlMatchingUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class UserFilter implements Filter {

    @Resource
    private SessionUtils sessionUtils;

    private String[] prefixIgnores = new String[]{"/user/register", "/user/login"};

    private static final String USER_HEADER = "USER_HEADER";

    private static final String CURRENT_USER = "CURRENT_USER";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        try{
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            boolean result = false;
            // 判断是否需要进行鉴权
            String requestUri = request.getRequestURI();
            boolean valid = UrlMatchingUtils.matching(requestUri,prefixIgnores);
            if(valid){
                //拿到token
                String userHeader = request.getHeader(USER_HEADER);
                if(!StringUtils.isEmpty(userHeader)){
                    User user = sessionUtils.getAuth(userHeader);
                    // token验证成功得到用户信息
                    if(user != null){
                        request.setAttribute(CURRENT_USER, user);
                        result = true;
                    }
                }
            }else{
                result = true;
            }
            if(result)
                filterChain.doFilter(servletRequest,servletResponse);
            else{
                //鉴权失败返回401
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }
        }catch (Exception ex){
            ex.printStackTrace();
            log.error(ex.getMessage());
            //鉴权失败返回401
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    @Override
    public void destroy() {

    }

}

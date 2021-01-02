package com.app.server.util;

import com.alibaba.fastjson.JSON;
import com.app.server.model.User;
import com.app.server.util.RSAKeyHelper;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

/**
 * @author qiaomengnan
 * @ClassName: JWTService
 * @Description:
 * @date 2021/1/2
 */
@Component
public class JWTUtils {

    private final Algorithm algorithm = Algorithm.RSA256(RSAKeyHelper.getRSAPublicKey(), RSAKeyHelper.getRSAPrivateKey());

    private final JWTVerifier verifier = JWT.require(algorithm).build();

    // 构建token
    public String getToken(User user) {
        return JWT.create()
                .withSubject(JSON.toJSONString(user))
                .sign(algorithm);
    }

    // 验证token并返回用户信息
    public User checkToken(String token) {
        DecodedJWT jwt = verifier.verify(token);
        return JSON.parseObject(jwt.getSubject(), User.class);
    }

}

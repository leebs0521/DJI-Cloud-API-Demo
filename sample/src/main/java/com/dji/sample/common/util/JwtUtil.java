package com.dji.sample.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.dji.sample.common.model.CustomClaim;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * JWT 토큰 유틸리티 클래스
 * 
 * JWT 토큰의 생성, 검증, 파싱 기능을 제공하는 유틸리티 클래스입니다.
 * HMAC256 알고리즘을 사용하여 토큰을 서명하고, 커스텀 클레임을 포함할 수 있습니다.
 * 
 * @author sean.zhou
 * @date 2021/11/16
 * @version 0.1
 */
@Slf4j
@Component
public class JwtUtil {

    /** JWT 발급자 */
    private static String issuer;

    /** JWT 주체 */
    private static String subject;

    /** JWT 만료 시간 (밀리초) */
    private static long age;

    /** JWT 서명 시크릿 키 */
    private static String secret;

    /** JWT 서명 알고리즘 */
    public static Algorithm algorithm;

    /**
     * JWT 발급자를 설정합니다.
     * 
     * @param issuer JWT 발급자
     */
    @Value("${jwt.issuer: DJI}")
    private void setIssuer(String issuer) {
        JwtUtil.issuer = issuer;
    }

    /**
     * JWT 주체를 설정합니다.
     * 
     * @param subject JWT 주체
     */
    @Value("${jwt.subject: CloudApiSample}")
    private void setSubject(String subject) {
        JwtUtil.subject = subject;
    }

    /**
     * JWT 만료 시간을 설정합니다.
     * 
     * @param age JWT 만료 시간 (초)
     */
    @Value("${jwt.age: 86400}")
    private void setAge(long age) {
        JwtUtil.age = age * 1000;
    }

    /**
     * JWT 서명 시크릿 키를 설정합니다.
     * 
     * @param secret JWT 서명 시크릿 키
     */
    @Value("${jwt.secret: CloudApiSample}")
    private void setSecret(String secret) {
        JwtUtil.secret = secret;
        setAlgorithm();
    }

    /**
     * JWT 서명 알고리즘을 설정합니다.
     */
    private void setAlgorithm() {
        JwtUtil.algorithm = Algorithm.HMAC256(secret);
    }

    /**
     * 기본 생성자 (private)
     */
    private JwtUtil() {

    }

    /**
     * 커스텀 정보를 기반으로 JWT 토큰을 생성합니다.
     * 
     * @param claims 커스텀 정보 (클레임)
     * @return 생성된 JWT 토큰
     */
    public static String createToken(Map<String, ?> claims) {
        return JwtUtil.createToken(claims, age, algorithm, subject, issuer);
    }

    /**
     * 지정된 파라미터를 사용하여 JWT 토큰을 생성합니다.
     * 
     * @param claims 커스텀 정보 (클레임)
     * @param age 토큰 만료 시간 (초)
     * @param algorithm 서명 알고리즘
     * @param subject JWT 주체
     * @param issuer JWT 발급자
     * @return 생성된 JWT 토큰
     */
    public static String createToken(Map<String, ?> claims, Long age, Algorithm algorithm, String subject, String issuer) {
        if (Objects.isNull(algorithm)) {
            throw new IllegalArgumentException();
        }

        Date now = new Date();
        JWTCreator.Builder builder = JWT.create();
        // 토큰의 페이로드 세그먼트에 커스텀 정보를 추가합니다
        claims.forEach((k, v) -> {
            if (Objects.nonNull(v.getClass().getClassLoader())) {
                log.error("claim can't be set to a custom object.");
                return;
            }
            if (v instanceof Map) {
                builder.withClaim(k, (Map) v);
            } else if (v instanceof List) {
                builder.withClaim(k, (List) v);
            } else {
                builder.withClaim(k, String.valueOf(v));
            }
        });

        if (StringUtils.hasText(subject)) {
            builder.withSubject(subject);
        }

        if (StringUtils.hasText(issuer)) {
            builder.withIssuer(issuer);
        }

        if (Objects.nonNull(age)) {
            builder.withExpiresAt(new Date(now.getTime() + age));
        }

        String token = builder
                .withIssuedAt(now)
                .withNotBefore(now)
                .sign(algorithm);
        log.debug("token created. " + token);
        return token;
    }

    /**
     * 토큰의 유효성을 검증합니다.
     * 
     * @param token 검증할 JWT 토큰
     * @return 디코딩된 JWT 객체
     * @throws TokenExpiredException 토큰이 만료된 경우
     */
    public static DecodedJWT verifyToken(String token) {
        return JWT.require(algorithm).build().verify(token);
    }

    /**
     * 토큰의 커스텀 정보를 CustomClaim 객체로 파싱합니다.
     * 
     * @param token 파싱할 JWT 토큰
     * @return 커스텀 클레임 객체 (Optional)
     */
    public static Optional<CustomClaim> parseToken(String token) {
        DecodedJWT jwt;
        try {
            jwt = verifyToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.of(new CustomClaim(jwt.getClaims()));
    }
}

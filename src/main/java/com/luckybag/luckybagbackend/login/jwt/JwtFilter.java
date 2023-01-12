package com.luckybag.luckybagbackend.login.jwt;

import io.jsonwebtoken.lang.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends GenericFilter {
    private final TokenProvider tokenProvider;
    private final RedisTemplate redisTemplate;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("인증 시도");
        String token = resolveToken((HttpServletRequest) request);
        log.info("resolvetoken = {}");
        if (Strings.hasText(token) && tokenProvider.validateToken(token)) {
            String isLogout = (String) redisTemplate.opsForValue().get(token);
            log.info("isLogout={}", isLogout);
            //redis에 해당 accessToken logout 여부 확인 후 토큰이 유효할 경우 토큰에서 Authentication 객체를 가지고 와서 SecurityContext에 저장
            if (ObjectUtils.isEmpty(isLogout)) {
                Authentication authentication = tokenProvider.getAuthentication(token);
                log.info("doFilter authenticationName={}", authentication.getName());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        log.info("doFilter");
        chain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        log.info("bearerToken ={}", bearerToken.substring(7));
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}

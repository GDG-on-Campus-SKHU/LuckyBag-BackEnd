package com.luckybag.luckybagbackend.login.interceptor;

import com.luckybag.luckybagbackend.login.domain.GrantType;
import com.luckybag.luckybagbackend.login.error.BusinessException;
import com.luckybag.luckybagbackend.login.error.ErrorCode;
import com.luckybag.luckybagbackend.login.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
@RequiredArgsConstructor
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final TokenProvider tokenProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("인증 진입");

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // 0. 헤더 있는지 체크
        if (authorizationHeader.length() <1){
            throw new BusinessException(ErrorCode.NOT_EXISTS_AUTHORIZATION);
        }

        //  1. authorization Header의 TokenType Bearer 체크
        String[] authorizations = authorizationHeader.split(" ");

        if( authorizations.length < 2 || ( !GrantType.BEARER.getType().equals(authorizations[0]))){
            throw new BusinessException(ErrorCode.NOT_VALID_BEARER_GRANT_TYPE);
        }

        //  2. 토큰 검증
        String token = authorizations[1];   // token 변수는 액세스 토큰의 몸통 부분.
        if( !tokenProvider.validateToken(token)){
            throw new BusinessException(ErrorCode.NOT_VALID_TOKEN);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("AuthenticationInterceptor : postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("AuthenticationInterceptor : afterCompletion");
    }
}
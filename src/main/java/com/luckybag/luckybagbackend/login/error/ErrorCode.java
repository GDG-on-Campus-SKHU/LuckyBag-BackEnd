package com.luckybag.luckybagbackend.login.error;

import lombok.Getter;

@Getter
public enum ErrorCode {

    /**
     *  에러처리 안내용 ENUM
     */
    HTTP_REQUEST_METHOD_NOT_SUPPORTED(405, "지정하신 URL은 사용하신 HTTP 메소드를 지원하지 않습니다."),
    METHOD_ARGUMENT_TYPE_MISMATCH(400, "유효하지 않은 요청값입니다"),

    // 회원
    MISMATCHED_PASSWORD(400, "비밀번호 입력이 일치하지 않습니다."),
    PASSWORD_NOT_MATCHES(400, "비밀번호를 다시 입력해 주세요."),
    DUPLICATED_USERNAME(400, "이미 존재하는 회원명입니다"),

    //  회원 인증
    NOT_VALID_TOKEN(401, "유효하지 않은 토큰입니다."),
    NOT_EXISTS_AUTHORIZATION(401, "Authorization Header가 없습니다."),
    NOT_VALID_BEARER_GRANT_TYPE(401, "인증 타입이 Bearer 타입이 아닙니다.");

    private int status;
    private String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
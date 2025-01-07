package uz.result.primemedicalcentre.domain.dto;

import uz.result.primemedicalcentre.security.Token;

public class ApiResponseWithToken extends ApiResponse<Token> {

    public ApiResponseWithToken(String message, Token data) {
        super(message, data);
    }

}

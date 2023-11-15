package com.projectss.theUltimateTodo.OAuth;

public record LoginResponseDto (

    String token_type,
    String access_token,
    String id_token,
    String refresh_token,
    Integer expires_in,
    Integer refresh_token_expires_in,
    String scope
){
}
